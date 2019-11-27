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
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Map 
{
    private final static int NUMBER_OF_ROOMS = 7;
    public static Room[] rooms = new Room[NUMBER_OF_ROOMS];
    private static List<String> roomNames = new ArrayList<String>(Arrays.asList("Kitchen", "Bathroom", "Main Hall", "Bedroom", "Living Room", "Study", "Observatory"));
    private static List<String> roomDescs = new ArrayList<String>(Arrays.asList("Kitchen Description", "Bathroom Description", "Main Hall Description", "Bedroom Description", "Living Room Description", "Study Description", "Observatory Description"));
    private static Debug debug;
    
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
                    rooms[index].setDoor(1, roomList.get(index));
                    rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                }
                else
                {
                    debug.debug("Room " + index + "'s door two progresses.");
                    rooms[index].setDoor(2, roomList.get(index));
                    rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
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
                        rooms[index].setDoor(1, roomList.get(index));
                        rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(3, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 1:
                        debug.debug("Room " + index + "'s door two progresses.");
                        rooms[index].setDoor(2, roomList.get(index));
                        rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(3, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 2:
                        debug.debug("Room " + index + "'s door three progresses.");
                        rooms[index].setDoor(3, roomList.get(index));
                        rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                }
            }
        }
        for (int index = 0; index < NUMBER_OF_ROOMS; index++) 
        {
            debug.debug(rooms[index].toString());
        }
    }
    
    public static void putDown(int playerLocation, String item)
    {
        rooms[playerLocation].addInventoryItem(item);
        rooms[playerLocation].appendRoomDescription("There is a " + item + " on the floor here.");
    }
    
    public static String look(int playerLocation)
    {
        return rooms[playerLocation].getRoomDescription();
    }
    
    public static boolean ifRoomHasItem(int playerLocation, String item)
    {
        return rooms[playerLocation].ifRoomHasItem(item);
    }
    
    public static void removeInventoryItem(int playerLocation, String item)
    {
        rooms[playerLocation].removeInventoryItem(item);
    }
    
    public static boolean ifDoorExists(int playerLocation, int door)
    {
        return rooms[playerLocation].ifDoorExists(door);
    }
    
    public static int getDoor(int playerLocation, int door)
    {
        return rooms[playerLocation].getDoor(door);
    }
    
    public static String enterRoomDescription(int playerLocation)
    {
        return rooms[playerLocation].enterRoomDescription();
    }
    
}
