/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory Class - instantiated for each room and player.<br>
 * Constructor initializes an empty player inventory.<br>
 * <P>
 * Instance variables: inventory, max inventory size, and debug
 * Methods: get/set/clear inventory, can add/add/remove inventory item,
 * if has item, get inventory item index, get inventory length, and to string.<br>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Inventory 
{
    private static final int NON_APPLICABLE = -1;
    private int maxInventorySize;
    private List<String> inventory;
    private Debug debug;
    
    Inventory()
    {
        this(NON_APPLICABLE);
    }
    
    Inventory(int max)
    {
        inventory = new ArrayList<String>();
        maxInventorySize = max;
        debug = new Debug();
    }
    
    public List<String> getInventory()
    {
        return this.inventory;
    }
    
    public void setInventory(List<String> items)
    {
        this.inventory.clear();
        this.inventory = items;
    }
    
    /**
     * Clear Inventory Method<br>
     * Clears all items in inventory.<br>
     */
    public void clearInventory()
    {
        this.inventory.clear();
    }
    
    /**
     * Can Add Inventory Item Method<br>
     * Checks if inventory has a max inventory size and, if so, if it has reached the max inventory size.<br>
     * @return boolean value - True if there is room in the inventory, else false<br>
     */
    public boolean canAddInventoryItem()
    {
        if((maxInventorySize == NON_APPLICABLE) || (inventory.size() < maxInventorySize)) 
            return true;
        else
            return false;
    }
    
    /**
     * Add Inventory Item Method<br>
     * Checks if an item can be added to inventory, then adds specified item to inventory.<br>
     * @param item item to be added to inventory<br>
     */
    public void addInventoryItem(String item)
    {
        if(this.canAddInventoryItem())
        {
            inventory.add(item);
        }
    }
    
    /**
     * If Inventory Has Item Method<br>
     * Checks if inventory has specified item.<br>
     * @param item item to be searched for<br>
     * @return boolean value - True if inventory contains specified item, else false<br>
     */
    public boolean ifHasItem(String item)
    {
        boolean hasItem = false;
        for(String inventoryItem : this.inventory)
        {
            if(inventoryItem.toLowerCase().equals(item.toLowerCase().trim()))
            {
                debug.debug("Inventory has " + item);
                hasItem = true;
            }
        }
        return hasItem;
    }
    
    /**
     * Remove Inventory Item Method<br>
     * Checks if the specified item can be removed then removes it.<br>
     * @param item item to be removed
     */
    public void removeInventoryItem(String item)
    {
        if(ifHasItem(item))
        {
            this.inventory.remove(item);
            debug.debug(item + " is removed.");
        }
    }
    
    /**
     * Get Inventory Item Index Method<br>
     * Returns the index of the specified item if it is in inventory, or -1/NON_APPLICABLE if it is not in inventory.<br>
     * @param item item to be searched for<br>
     * @return int - index of specified item<br>
     */
    public int getInventoryItemIndex(String item)
    {
        int returnIndex = NON_APPLICABLE;
        for(int index = 0; index < this.inventory.size(); index++) 
        {
            if(item.toLowerCase().equals(inventory.get(index).toLowerCase()))
                returnIndex = index;
        }
        return returnIndex;
    }
    
    /**
     * Get Inventory Length Method<br>
     * @return int - number of items in inventory<br>
     */
    public int getInventoryLength()
    {
        return inventory.size();
    }
    
    /**
     * To String Method<br>
     * Creates and returns a string containing the inventory contents.<br>
     * @return String - String containing all contents of inventory<br>
     */
    public String toString()
    {
        String returnString = "Inventory contains:";
        for(String item : this.inventory)
            returnString += "\n" + item;
        return returnString;
    }
}
