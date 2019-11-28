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
    private static List<String> roomNames = new ArrayList<String>(Arrays.asList("Kitchen", "Bathroom", "Main Hall", "Bedroom", "Living Room", "Study", "Observatory"));
    private static List<String> roomDescs = new ArrayList<String>(Arrays.asList("Kitchen Description", "Bathroom Description", "Main Hall Description", "Bedroom Description", "Living Room Description", "Study Description", "Observatory Description"));
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
