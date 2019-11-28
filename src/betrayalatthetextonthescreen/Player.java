
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Player Class - instantiated for each player (currently 1 player).<br>
 * Constructor initializes player name (if no name is specified, should default 
 * to DEFAULT_PLAYER_NAME), current player location (defaulted to room number 0), 
 * gui, rooms visited array, and player inventory.<br>
 * <P>
 * Instance variables: player name, current player location, player inventory,
 * pointer to map, player gui, rooms visited array, and debug<br>
 * Methods: get/set player name, get/set player location, get/set/clear player inventory, 
 * get/set player visited, get player inventory index, can add/add/remove/can remove inventory item, 
 * parse, and to string.<br>
 * <P>
 * ToDo:<br>
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 * 
 */
public class Player {
    static final String DEFAULT_PLAYER_NAME = "I'm a default";
    static final int MAX_INVENTORY_SIZE = 10;
    private String playerName;
    private int playerLocation;
    private Inventory playerInventory;
    private Debug debug;
    private Map playerMap;
    private GUI playerGui;
    private boolean[] roomsVisited;
    
    /**
     * Player constructor<br>
     * Calls second player constructor with the default player name<br>
     */
    
    Player(Map map)
    {
        this(DEFAULT_PLAYER_NAME, map);
    }
    
    /**
     * Player constructor<br>
     * Initializes player name, gui, room visited array. and debug<br>
     * Initializes player location to 0<br>
     * Initializes empty player inventory with specified max size<br>
     * Saves pointer to the map<br>
     * Prints initial room description and sets the starting room to visited<br>
     * @param name player name<br>
     */
    
    Player (String name, Map map) 
    {
        playerName = name;
        playerLocation = 0;
        playerInventory = new Inventory(MAX_INVENTORY_SIZE);
        debug = new Debug();
        playerMap = map;
        playerGui = new GUI(this, playerName);
        playerGui.writeGUI(map.enterRoomDescription(playerLocation, false));
        roomsVisited = new boolean[map.getNumberOfRooms()];
        for(boolean visited : roomsVisited)
        {
            visited = false;
        }
        this.setPlayerVisited();
    }
    
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    public void setPlayerName(String name)
    {
        this.playerName = name;
    }
    
    public int getPlayerLocation()
    {
        return this.playerLocation;
    }
    
    public void setPlayerLocation(int room)
    {
        this.playerLocation = room;
    }
    
    public Inventory getPlayerInventory()
    {
        return this.playerInventory;
    }
    
    public void setPlayerInventory(List<String> items)
    {
        this.playerInventory.clearInventory();
        this.playerInventory.setInventory(items);
    }
    
    public boolean getPlayerVisited()
    {
        return this.roomsVisited[this.playerLocation];
    }
    
    public void setPlayerVisited()
    {
        this.roomsVisited[playerLocation] = true;
    }
    
    /**
     * Clear Player Inventory Method<br>
     * Removes all strings from the playerInventory list.<br>
     */
    public void clearPlayerInventory()
    {
        this.playerInventory.clearInventory();
    }
    
    /**
     * Get Player Inventory Index<br>
     * Returns the index within the player's inventory of the specified item.<br>
     * @param item item to be searched for<br>
     * @return int - index of specified item<br>
     */
    public int getPlayerInventoryIndex (String item)
    {
        int index = this.playerInventory.getInventoryItemIndex(item);
        return index;
    }

    /**
     * Add Inventory Item Method<br>
     * Adds specified item to the player's inventory.<br>
     * Prints a debug of how many items are in the player's inventory.<br>
     * @param item item to be added to the inventory<br>
     */
    public void addInventoryItem(String item)
    {
        debug.debug("There are " + playerInventory.getInventoryLength() + " items in player's inventory.");
        this.playerInventory.addInventoryItem(item);
    }
    
    /**
     * Can Add Inventory Item Method<br>
     * Checks if there is room for another item in the player's inventory.<br>
     * @return boolean value - True if an item can be added to the inventory, else false<br>
     */
    public boolean canAddInventoryItem()
    {
        return this.playerInventory.canAddInventoryItem();
    }
    
    /**
     * Remove Inventory Item Method<br>
     * Removes the specified item from the player's inventory.<br>
     * @param item item to be removed from the inventory<br>
     */
    public void removeInventoryItem(String item)
    {
        this.playerInventory.removeInventoryItem(item);
    }
    
    /**
     * Can Remove Inventory Item Method<br>
     * Checks if the player has the specified item.<br>
     * @param item item to be checked<br>
     * @return boolean value - True if player's inventory contains specified item, else false<br>
     */
    public boolean canRemoveInventoryItem(String item)
    {
        return this.playerInventory.ifHasItem(item);
    }
    
    /**
     * Parse Method<br>
     * Saves user input processed by the parser and responds accordingly.<br> 
     * <P>
     * TODO:<br>
     * Accept alternate methods of specifying door to move through.<br>
     */
    void parse(String[] userInputArray)
    {
        if(!userInputArray[0].equals("quit"))
        {
            switch (userInputArray[0])
            {
                case "inventory":
                    playerGui.writeGUI(getPlayerInventory().toString());
                    break;
                case "drop":
                case "put":
                    debug.debug(userInputArray[1]);
                    if(canRemoveInventoryItem(userInputArray[1]))
                    {
                        removeInventoryItem(userInputArray[1]);
                        playerMap.putDown(getPlayerLocation(), userInputArray[1]);
                        playerGui.writeGUI("You are no longer carrying " + userInputArray[1] + ".");
                    }
                    else
                        playerGui.writeGUI("You are not carrying " + userInputArray[1] + ".");
                    break;
                case "look":
                case "view":
                    playerGui.writeGUI(playerMap.look(getPlayerLocation()));
                    break;
                case "pick":
                case "take":
                    if(playerMap.ifRoomHasItem(getPlayerLocation(), userInputArray[1]))
                    {
                        if (canAddInventoryItem()) 
                        {
                            addInventoryItem(userInputArray[1]);
                            playerMap.removeInventoryItem(getPlayerLocation(), userInputArray[1]);
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
                        int doorNumber = Integer.parseInt(userInputArray[1]) - 1;
                        if(playerMap.ifDoorExists(getPlayerLocation(), doorNumber))
                        {
                            setPlayerLocation(playerMap.getDoor(getPlayerLocation(), doorNumber));
                            playerGui.writeGUI(playerMap.enterRoomDescription(getPlayerLocation(), this.roomsVisited[this.playerLocation]));
                            setPlayerVisited();
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
     * To String Method<br>
     * Creates and returns a string containing the name, location, and inventory contents for the player.<br>
     * @return String - all information regarding the player and its variables <br>
     */
    public String toString()
    {
        String returnString = "";
        returnString += "Name: " + this.playerName;
        returnString += "\tLocation: " + this.playerLocation + "\n";
        returnString += this.playerInventory + "\n";
        for(int index = 0; index < this.roomsVisited.length; index++)
        {
            returnString += index + ": " + roomsVisited[index] + "\t";
        }
        returnString += "\n";
        return returnString;
    }
    // other methods to follow
    
}
