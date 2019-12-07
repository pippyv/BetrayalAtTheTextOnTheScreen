/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Map Class
 * Constructor creates a new map.<br>
 * <P>
 * Instance variables: rooms array, room names and descriptions array lists,
 * and debug.<br>
 * Methods: Constructor, build map, put down, look, if room has item, remove inventory item,
 * if door exists, get door, enter room description, get number of rooms, and to string.<br>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Map 
{
    private final static int NUMBER_OF_ROOMS = 7;
    public static Room[] rooms = new Room[NUMBER_OF_ROOMS];
    private static List<String> roomNames = new ArrayList<String>(Arrays.asList(
                "Kitchen",          //1
                "Powder Room",      //2
                "Main Hall",        //3
                "Guest Bedroom",    //4
                "Living Room",      //5
                "Study",            //6
                "Observatory",      //7
                "Ballroom",         //8
                "Dining Room",      //9
                "Billiard Room",    //10
                "Library",          //11
                "Den",              //12
                "Theater",          //13
                "Balcony"));        //14
    private static List<String> roomDescs = new ArrayList<String>(Arrays.asList(
                "You are standing in a spacious kitchen.\n"
                        + "The tiled floor is uncomfortably new, gleamingly shiny,"
                        + " and accentuates the almost antique appliances that litter the room."
                        + "  Most likely none of them work anymore.\n"
                        + "The counters are empty but covered with a thick layer of dust.\n"
                        + "A rank stench is wafting aggressively out of the looming refrigerator.",  //Kitchen Description
                "You are in a small powder room.\n"
                        + "The walls are papered a pealing, sickly pink that "
                        + "threatens to pull you in and swallow you.\n"
                        + "The toilet and sink are dingy and yellowed but reak of bleach.", //Bathroom Description
                "You find yourself in the remnants of a once grand main hall.\n"
                        + "The ceilings are remarkably high and a balcony from "
                        + "an upper floor peers down from above.",    //Main Hall Description
                "You are standing in a small guest bedroom.\n"
                        + "There is a worn four-poster bed in the center of the "
                        + "small room with immaculate hospital corners.",  //Guest Bedroom Description
                "You are in a formerly well-kept living room.\n"
                        + "There are two well-worn couches and a partially torn armchair"
                        + " arranged around a fileplace that is spilling ash and dust "
                        + "onto the rug.\n"
                        + "In the back corner there is a rocking chair facing an empty wall.\n"
                        + "The rocking chair is rocking slowly.",  //Living Room Description
                "You find yourself standing in a small study.\n"
                        + "There is a small wooden desk chair pushed under a simple writing desk.\n"
                        + "The papers on the desk look old enough to crumble in your hands.",    //Study Description
                "You are standing in a small observatory.\n"
                        + "Part of the roof is open with telescopes and other equipment "
                        + "sitting in the open air beneath the hole.\n"
                        + "The equipment all seems to have been rained on.",  //Observatory Description
                "You are in a large ballroom.\n"
                        + "The floor is dusty but in decent shape and makes a satisfying"
                        + " tap under your shoes.\n"
                        + "The room is almost empty save for a black painted grand piano"
                        + " against a far wall.\n"
                        + "A chandelier hangs dauntingly above you.",     //Ballroom Description
                "You find yourself in a traditional dining room.\n"
                        + "A decent-sized dining table sits in the middle of the room "
                        + "surrounded by old, ornate dining chairs.\n"
                        + "A metal chandelier hangs over the center of the table where"
                        + " there is a bowl of rotting fruit.",  //Dining Room Description
                "You are standing in a heavily carpeted billiard room.\n"
                        + "There is a bulky billiard table in the center of the room.\n"
                        + "Several pool cues in various amounts of distress "
                        + "are stacked against a wall.",    //Billiard Room Description
                "You are in an old, private library.\n"
                        + "The walls are lined with thousands of musty books from "
                        + "floor to ceiling in a myriad of colors that are all somehow"
                        + "similar to grey.\n"
                        + "The room smells like old books.  And maybe kerosene.",  //Library Description
                "You find yourself in a once cozy den.\n"
                        + "There are several sunken couches around a small coffee "
                        + "table covered in barely legible coffee table books.",  //Den Description
                "You are standing in a dusty home theater.\n"
                        + "Several couches and other various seating options are arranged"
                        + " facing a dingy and yellowed sheet that hangs from the ceiling.\n"
                        + "An old projector is setup against the back wall.  Chances of it working are slim.",  //Theater Description
                "You are on a thin balcony overlooking the house's main hall.\n"
                        + "Funny.  You don't remember climbing any stairs"));    //Balcony Description
    private static Debug debug;
    
    /**
     * Map Constructor<br>
     * Initializes debug and calls build map method.<br>
     */
    Map()
    {
        debug = new Debug();
        buildMap();
    }
    
    
    /**
     * Map Builder Method<br>
     * Creates as many rooms as specified by NUMBER_OF_ROOMS constant.<br>
     * Randomly selects a name for each room and sets a corresponding room description.<br>
     * Randomizes number of doors for each room (2-3) and appends a sentence onto the room description stating how many doors are in the room.<br>
     * Selects one door per room at random to lead to a predetermined route that ensures every room is accessible.<br>
     * Randomizes the destination for any remaining doors (can lead to themselves).<br>
     * Prints debug to console.<br>
     * Assumes NUMBER_OF_ROOMS is defined within file<br>
     * <P>
     * TODO<br> 
     * Move out of main and into another file.<br>
     */
    static void buildMap()
    {
        Random rand = new Random();
        int randomNumber;
        List<Integer> roomList = new ArrayList<Integer>();
        roomList.add(NUMBER_OF_ROOMS - 2);
        roomList.add(NUMBER_OF_ROOMS - 1);
        for (int index = 0; index < NUMBER_OF_ROOMS - 2; index++) 
            roomList.add(index);
        for (int index = 0; index < NUMBER_OF_ROOMS; index++) 
        {
            rooms[index] = new Room(("" + index), index);
            randomNumber = rand.nextInt(roomNames.size());
            rooms[index].setRoomName(roomNames.get(randomNumber));
            rooms[index].setBaseRoomDescription(roomDescs.get(randomNumber));            
            rooms[index].setRoomDescription(roomDescs.get(randomNumber));
            roomNames.remove(randomNumber);
            roomDescs.remove(randomNumber);
            if(rand.nextBoolean())
            {
                debug.debug("Room " + index + " has two doors.");
                rooms[index].appendRoomDescription("There are 2 doors here.");
                if(rand.nextBoolean())
                {
                    debug.debug("Room " + index + "'s door one progresses.");
                    rooms[index].setDoor(0, roomList.get(index));
                    rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                }
                else
                {
                    debug.debug("Room " + index + "'s door two progresses.");
                    rooms[index].setDoor(1, roomList.get(index));
                    rooms[index].setDoor(0, rand.nextInt(NUMBER_OF_ROOMS - 1));
                }
            }
            else
            {
                debug.debug("Room " + index + " has three doors here.");
                rooms[index].appendRoomDescription("There are 3 doors here.");
                randomNumber = rand.nextInt(3);
                switch(randomNumber)
                {
                    case 0:
                        debug.debug("Room " + index + "'s door one progresses.");
                        rooms[index].setDoor(0, roomList.get(index));
                        rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 1:
                        debug.debug("Room " + index + "'s door two progresses.");
                        rooms[index].setDoor(1, roomList.get(index));
                        rooms[index].setDoor(0, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 2:
                        debug.debug("Room " + index + "'s door three progresses.");
                        rooms[index].setDoor(2, roomList.get(index));
                        rooms[index].setDoor(0, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                }
            }
        }
        for (int index = 0; index < NUMBER_OF_ROOMS; index++) 
        {
            debug.debug(rooms[index].toString());
        }
    }
    
    /**
     * Put Down Method<br>
     * Adds specified inventory item to the specified rooms inventory and updates
     * the rooms description to contain account for the new item.<br>
     * @param playerLocation int reference number for the room the player is currently in<br>
     * @param item String item to be put down in room<br>
     */
    public static void putDown(int playerLocation, String item)
    {
        rooms[playerLocation].addInventoryItem(item);
        rooms[playerLocation].appendRoomDescription("There is a " + item + " on the floor here.");
    }
    
    public String getRoomName(int playerLocation)
    {
        return rooms[playerLocation].getRoomName();
    }
    
    /**
     * Look Method<br>
     * Returns the room description for the specified room.<br> 
     * @param playerLocation int reference number for the room the player is currently in.<br>
     * @return String - room description.<br>
     */
    public static String look(int playerLocation)
    {
        return rooms[playerLocation].getRoomDescription();
    }
    
    /**
     * If Room Has Item Method<br>
     * Calls if room has item method in the room class for specified room and item.<br>
     * @param playerLocation int reference number for the room the player is currently in.<br>
     * @param item String item to be checked for.<br>
     * @return boolean - true if specified room has specified item, else false.<br>
     */
    public static boolean ifRoomHasItem(int playerLocation, String item)
    {
        return rooms[playerLocation].ifRoomHasItem(item);
    }
    
    /**
     * Remove Inventory Item Method<br>
     * Calls remove inventory item method in the room class for specified room and item.<br>
     * @param playerLocation int reference number for the room the player is currently in.<br>
     * @param item String item to be removed from the room.<br>
     */
    public static void removeInventoryItem(int playerLocation, String item)
    {
        rooms[playerLocation].removeInventoryItem(item);
    }
    
    /**
     * If Door Exists Method<br>
     * Calls if door exists method in the room class for the specified room and door.<br>
     * @param playerLocation int reference number for the room the player is currently in.<br>
     * @param door int reference number for the door to be checked.<br>
     * @return boolean - true if specified room has specified door, else false.<br>
     */
    public static boolean ifDoorExists(int playerLocation, int door)
    {
        return rooms[playerLocation].ifDoorExists(door);
    }
    
    /**
     * Get Door Method<br>
     * Calls get door method in the room class for the specified room and door.<br>
     * @param playerLocation int reference number for the room the player is currently in.<br>
     * @param door int reference number for the door to be checked.<br>
     * @return int - reference number for the destination of specified door in specified room.<br>
     */
    public static int getDoor(int playerLocation, int door)
    {
        return rooms[playerLocation].getDoor(door);
    }
    
    /**
     * Enter Room Description Method<br>
     * Calls the enter room description method in the room class for the specified room.<br>
     * @param playerLocation int reference number for the room the player is entering.<br>
     * @param visited boolean value of whether or not the player has visited specified room before.<br>
     * @return String - description for specified room.<br>
     */
    public static String enterRoomDescription(int playerLocation, boolean visited)
    {
        return rooms[playerLocation].enterRoomDescription(visited);
    }
    
    /**
     * Get Number Of Rooms Method<br>
     * @return int - number of rooms in the map.<br>
     */
    public static int getNumberOfRooms()
    {
        return NUMBER_OF_ROOMS;
    }
    
    /**
     * To String Method<br>
     * Creates and returns a string containing the information for all rooms in the map.<br>
     * @return String - All information regarding the rooms in the map.<br>
     */
    public String toString()
    {
        String returnString = "";
        for(Room room : rooms)
        {
            returnString += room.toString() + "/n/n";
        }
        return returnString;
    }
    
}
