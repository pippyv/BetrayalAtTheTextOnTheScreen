
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
 * Instance variables: player name, current player location, and player inventory<br>
 * Methods: get/set player name, get/set player location, get/set player inventory, 
 * clear player inventory, if player has item, add/remove inventory item, 
 * and to string.<br>
 * <P>
 * TODO:<br>
 * Default player name constructor<br>
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
     * @param item item to be searched for<br>
     * @return - boolean value true if player has specified item, else false<br>
     */
    public int ifPlayerHasItem (String item)
    {
        int playerHasItem = -1;
        for (int index = 0; index < this.playerInventory.size(); index ++) 
        {
            //System.out.println(this.playerInventory.get(index).toLowerCase()); <- Debug
            if (playerInventory.get(index).toLowerCase().equals(item)) 
            {
                playerHasItem = index;
            }
        }
        return playerHasItem;
    }

    /**
     * Add Inventory Item<br>
     * Checks if the player's inventory has enough space to hold another item and then either adds the specified item or informs the player that their inventory is full.<br>
     * @param item item to be added to the inventory<br>
     * @return - boolean value true if player can hold another item, else false<br>
     */
    public boolean addInventoryItem(String item)
    {
        boolean canAddItem = false;
        //System.out.println("There are " + playerInventory.size() + " items in player's inventory."); <- Debug
        
        if(this.playerInventory.size() < MAX_INVENTORY_SIZE)
        {
            this.playerInventory.add(item);
            canAddItem = true;
        }
        return canAddItem;
    }
    
    /**
     * Remove Inventory Item Method<br>
     * Checks if the player's inventory contains the specified item and removes the item if present.<br>
     * @param item item to be removed from the inventory<br>
     * @return boolean value - True if the specified item was in the player's inventory, else false<br>
     */
    public Boolean removeInventoryItem(String item)
    {
        boolean hasItem = false;
        //System.out.println(item); <- Debug
        if(ifPlayerHasItem(item) != -1)
        {
            this.playerInventory.remove(ifPlayerHasItem(item));
            //System.out.println(item + " has been removed."); <- Debug
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
