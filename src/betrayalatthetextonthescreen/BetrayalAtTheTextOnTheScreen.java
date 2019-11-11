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
 * main class
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class BetrayalAtTheTextOnTheScreen 
{
    final static int NUMBER_OF_ROOMS = 7;
    static Room[] rooms = new Room[NUMBER_OF_ROOMS];
    static Player player;
    static List<String> roomNames = new ArrayList<String>(Arrays.asList("Kitchen", "Bathroom", "Main Hall", "Bedroom", "Living Room", "Study", "Observatory"));
    static List<String> roomDescs = new ArrayList<String>(Arrays.asList("KitchenDesc", "BathroomDesc", "Main HallDesc", "BedroomDesc", "Living RoomDesc", "StudyDesc", "ObservatoryDesc"));
    static boolean debug = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        buildMap();
        player = new Player("Player 1");
        player.addInventoryItem("No Tea");
        Parser parser = new Parser();
        String userInput = parser.parseInput();
        while (!userInput.equals("quit")) {
            switch (userInput)
            {
                case "inventory":
                    System.out.println(player.getPlayerInventory());
                    break;
                default:
            }
            userInput = parser.parseInput();
        }
        // TODO code application logic here
    }
    
    static void debug(String message)
    {
        if (debug) 
            System.out.println(message);
    }
    
    /**
     *
     * Map Builder Method<br>
     * Creates as many rooms as specified by NUMBER_OF_ROOMS constant.<br>
     * Randomly selects a name for each room and sets a corresponding room description.<br>
     * Randomizes number of doors for each room (2-3) and appends a sentence onto the room description stating how many doors are in the room.<br>
     * Selects one door per room at random to lead to a predetermined route that ensures every room is accessible.<br>
     * Randomizes the destination for any remaining doors (can lead to themselves).<br>
     * Prints debug to console.<br>
     * Assumes NUMBER_OF_ROOMS is defined within file
     * <P>
     * TODO<br> 
     * Move out of main and into another file.<br>
     *
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
            rooms[index].setRoomDescription(roomDescs.get(randomNumber));
            roomNames.remove(randomNumber);
            roomDescs.remove(randomNumber);
            if(rand.nextBoolean())
            {
                debug("Room " + index + " has two doors.");
                rooms[index].appendRoomDescription("There are two doors here.");
                if(rand.nextBoolean())
                {
                    debug("Room " + index + "'s door one progresses.");
                    rooms[index].setDoor(1, roomList.get(index));
                    rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                }
                else
                {
                    debug("Room " + index + "'s door two progresses.");
                    rooms[index].setDoor(2, roomList.get(index));
                    rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                }
            }
            else
            {
                debug("Room " + index + " has three doors.");
                rooms[index].appendRoomDescription("There are three doors here.");
                randomNumber = rand.nextInt(3);
                switch(randomNumber)
                {
                    case 0:
                        debug("Room " + index + "'s door one progresses.");
                        rooms[index].setDoor(1, roomList.get(index));
                        rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(3, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 1:
                        debug("Room " + index + "'s door two progresses.");
                        rooms[index].setDoor(2, roomList.get(index));
                        rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(3, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 2:
                        debug("Room " + index + "'s door three progresses.");
                        rooms[index].setDoor(3, roomList.get(index));
                        rooms[index].setDoor(1, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoor(2, rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                }
            }
        }
        for (int index = 0; index < NUMBER_OF_ROOMS; index++) 
        {
            debug(rooms[index].toString());
        }
    }
    
}
