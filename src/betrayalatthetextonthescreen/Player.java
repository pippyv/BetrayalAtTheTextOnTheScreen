
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Player Class - instantiated for each player (currently 1 player).<br>
 * Constructor initializes player name (if no name is specified, should default 
 * to DEFAULT_PLAYER_NAME), current player location (defaulted to room number 0), 
 * and player inventory.<br>
 * <P>
 * Instance variables: player name, current player location, player inventory, and debug<br>
 * Methods: get/set player name, get/set player location, get/set/clear player inventory, 
 * get player inventory index, can add/add/remove/can remove inventory item, 
 * and to string.<br>
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
     * Initializes player name and inventory<br>
     * Initializes player location to 0<br>
     * @param name player name<br>
     */
    
    Player (String name, Map map) 
    {
        playerName = name;
        playerLocation = 0;
        playerInventory = new Inventory(MAX_INVENTORY_SIZE);
        debug = new Debug();
        playerMap = map;
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
     * To String Method<br>
     * Creates and returns a string containing the name, location, and inventory contents for the player.<br>
     * @return String - all information regarding the player and its variables <br>
     */
    public String toString()
    {
        String returnString = "";
        returnString += "Name: " + this.playerName;
        returnString += "/tLocation: " + this.playerLocation + "/n";
        returnString += this.playerInventory;
        return returnString;
    }
    // other methods to follow
    
}
