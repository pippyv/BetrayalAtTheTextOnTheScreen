
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Player Class - instantiated for each player (currently 1 player).<br>
 * Constructor initializes player name (if no name is specified, should default to DEFAULT_PLAYER_NAME), current player location (defaulted to room number 0), and player inventory.<br>
 * Instance variables: player name, current player location, and player inventory<br>
 * Methods: get/set player name, get/set player location, get/set player inventory, clear player inventory, if player has item, add/remove inventory item.<br>
 * <P>
 * TODO:<br>
 * Default player name constructor<br>
 * Possibly make addInventoryItem a boolean?<br>
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 * 
 */
public class Player {
    static final String DEFAULT_PLAYER_NAME = "I'm a default";
    static final int MAX_INVENTORY_SIZE = 10;
    private String playerName;
    private int playerLocation;
    private List<String> playerInventory;
    
    /**
     * Player constructor<br>
     * Initializes player name and inventory<br>
     * Initializes player location to 0<br>
     * @param name - player name<br>
     */
    Player (String name) 
    {
        playerName = name;
        playerLocation = 0;
        playerInventory = new ArrayList<String>();
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
    
    public List<String> getPlayerInventory()
    {
        return this.playerInventory;
    }
    
    public void setPlayerInventory(List<String> items)
    {
        this.playerInventory.clear();
        this.playerInventory = items;
    }
    
    /**
     * Clear Player Inventory Method<br>
     * Removes all strings from the playerInventory list.<br>
     */
    public void clearPlayerInventory()
    {
        this.playerInventory.clear();
    }
    
    /**
     * If Player Has Item Method<br>
     * Checks if the player's inventory contains specified items.  Returns a boolean.<br>
     * @param item - item to be searched for<br>
     * @return boolean value - true if player has specified item, else false<br>
     */
    public boolean ifPlayerHasItem (String item)
    {
        boolean playerHasItem = false;
        for (String playerInventoryItem : this.playerInventory) 
        {
            System.out.println(playerInventoryItem.toLowerCase());
            if (playerInventoryItem.toLowerCase().equals(item)) 
            {
                playerHasItem = true;
            }
        }
        return playerHasItem;
    }

    /**
     * Add Inventory Item<br>
     * Checks if the player's inventory has enough space to hold another item and then either adds the specified item or informs the player that their inventory is full.<br>
     * @param item - item to be added to the inventory<br>
     * @return String - String to be printed informing the player if the operation was successful<br>
     */
    public String addInventoryItem(String item)
    {
        if(this.playerInventory.size() < MAX_INVENTORY_SIZE)
        {
            this.playerInventory.add(item);
            return item + " has been added to your inventory.";
        }
        else
        {
            return "Your inventory is full.  You'll have to drop something first.";
        }
    }
    
    /**
     * Remove Inventory Item Method<br>
     * Checks if the player's inventory contains the specified item and removes the item if present.<br>
     * @param item - item to be removed from the inventory<br>
     * @return Boolean - True if the specified item was in the player's inventory, else false<br>
     */
    public Boolean removeInventoryItem(String item)
    {
        boolean hasItem = false;
        System.out.println(item);
        if(ifPlayerHasItem(item))
        {
            this.playerInventory.remove(item);
            hasItem = true;
        }
        return hasItem;
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
        returnString += "/tLocation: " + this.playerLocation + "/tInventory contains: ";
        for(String inventoryItem : this.playerInventory)
        {
            returnString += inventoryItem + "/t";
        }
        return returnString;
    }
    // other methods to follow
    
}
