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
 * Main Class<br>
 * <P>
 * ToDo: Move map builder into another file.<br>
 * ToDo: Move parser handler into another file.<br>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class BetrayalAtTheTextOnTheScreen 
{
    final static int NUMBER_OF_ROOMS = 7;
    static Room[] rooms = new Room[NUMBER_OF_ROOMS];
    static Player player;
    static Debug debug;
    static List<String> roomNames = new ArrayList<String>(Arrays.asList("Kitchen", "Bathroom", "Main Hall", "Bedroom", "Living Room", "Study", "Observatory"));
    static List<String> roomDescs = new ArrayList<String>(Arrays.asList("Kitchen Description", "Bathroom Description", "Main Hall Description", "Bedroom Description", "Living Room Description", "Study Description", "Observatory Description"));
    static GUI playerGui = new GUI();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        debug = new Debug();
        buildMap();
        player = new Player("Player 1");
        player.addInventoryItem("no tea");
        playerGui.writeGUI(rooms[player.getPlayerLocation()].enterRoomDescription()); 
    }
    
    /**
     * Parse Method<br>
     * Initializes instance of the parser class.<br>
     * Saves user input processed by the parser and responds accordingly.<br> 
     * <P>
     * TODO:<br>
     * Exception for if door number is incorrect to prevent crash.<br>
     * Accept alternate methods of specifying door to move through.<br>
     * Move out of main and into another file.<br>
     */
    static void parse(String[] userInputArray)
    {
        if(!userInputArray[0].equals("quit"))
        {
            switch (userInputArray[0])
            {
                case "inventory":
                    playerGui.writeGUI(player.getPlayerInventory().toString());
                    break;
                case "drop":
                case "put":
                    debug.debug(userInputArray[1]);
                    if(player.canRemoveInventoryItem(userInputArray[1]))
                    {
                        player.removeInventoryItem(userInputArray[1]);
                        rooms[player.getPlayerLocation()].addInventoryItem(userInputArray[1]);
                        rooms[player.getPlayerLocation()].appendRoomDescription("There is a " + userInputArray[1] + " on the floor here.");
                        playerGui.writeGUI("You are no longer carrying " + userInputArray[1] + ".");
                    }
                    else
                        playerGui.writeGUI("You are not carrying " + userInputArray[1] + ".");
                    break;
                case "look":
                case "view":
                    playerGui.writeGUI(rooms[player.getPlayerLocation()].getRoomDescription());
                    break;
                case "pick":
                case "take":
                    if(rooms[player.getPlayerLocation()].ifRoomHasItem(userInputArray[1]))
                    {
                        if (player.canAddInventoryItem()) 
                        {
                            player.addInventoryItem(userInputArray[1]);
                            rooms[player.getPlayerLocation()].removeInventoryItem(userInputArray[1]);
                            playerGui.writeGUI(userInputArray[1] + " has been added to your inventory.");
                        }
                        else
                        {
                            playerGui.writeGUI("You can't carry anything more.  You leave the " + userInputArray[1] + " where it is.");
                        }    
                    }
                    else
                    {
                        playerGui.writeGUI("You don't see " + userInputArray[1] + " here.");
                    }
                    break;
                case "move":
                case "go":
                    try
                    {
                        if(rooms[player.getPlayerLocation()].ifDoorExists(Integer.parseInt(userInputArray[1]) - 1))
                        {
                            player.setPlayerLocation(rooms[player.getPlayerLocation()].getDoor(Integer.parseInt(userInputArray[1])));
                            playerGui.writeGUI(rooms[player.getPlayerLocation()].enterRoomDescription()); 
                        }
                        else
                            playerGui.writeGUI("That is not a door.");
                    }
                    catch(NumberFormatException exception)
                    {
                        playerGui.writeGUI("Please specify the number of the door you would like to go through.");
                    }
                    break;
                default:
            }        
            System.out.println();
            //userInputArray = parser.parseInput();
        }
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
    
}
