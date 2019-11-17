/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Inventory 
{
    private static final int NON_APPLICABLE = -1;
    private int maxInventorySize;
    private List<String> inventory;
    
    Inventory()
    {
        this(NON_APPLICABLE);
    }
    
    Inventory(int max)
    {
        inventory = new ArrayList<String>();
        maxInventorySize = max;
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
    
    public void clearInventory()
    {
        this.inventory.clear();
    }
    
    public boolean canAddInventoryItem()
    {
        if((maxInventorySize == NON_APPLICABLE) || (inventory.size() < maxInventorySize)) 
            return true;
        else
            return false;
    }
    
    public void addInventoryItem(String item)
    {
        if(this.canAddInventoryItem())
        {
            inventory.add(item);
        }
    }
    
    public boolean ifHasItem(String item)
    {
        boolean hasItem = false;
        for(String inventoryItem : this.inventory)
        {
            if(inventoryItem.toLowerCase().equals(item.toLowerCase()))
                hasItem = true;
        }
        return hasItem;
    }
    
    public void removeInventoryItem(String item)
    {
        if(ifHasItem(item))
        {
            this.inventory.remove(item);
        }
    }
    
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
    
    public int getInventoryLength()
    {
        return inventory.size();
    }
    
    public String toString()
    {
        String returnString = "Inventory contains:";
        for(String item : this.inventory)
            returnString += "\n" + item;
        return returnString;
    }
}
