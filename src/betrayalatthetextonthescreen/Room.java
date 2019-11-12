
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Room Class - instantiated for each room.<br>
 * Constructor initializes room name, reference number integer, and destination 
 * for three doors (defaulted to NON_APPLICABLE).<br>
 * Reference number has no range restrictions.<br>
 * Assumes external code will initialize destinations for doors.<br>
 * Assumes external code handles pathing.<br>
 * Instance variables: room name, base room description, room description, 
 * doors array, room reference number, and room inventory array list<br>
 * Methods: constructor, get/set room number, get/set room name, get/set door, 
 * get/set base room description, get/set/append room description, if door exists, 
 * get number of doors, get/set room inventory, add/remove inventory item, 
 * if room has item, and to string<br>
 * <P>
 * TODO<br>  
 * Room visited variable<br>
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 *
 */

public class Room 
{
    private static final int NON_APPLICABLE = -1;
    private static final int MAX_NUMBER_OF_DOORS = 5;
    private String roomName;
    private String baseRoomDescription;
    private String roomDescription;
    private int[] doors = new int[MAX_NUMBER_OF_DOORS];
    private int roomNumber;
    private List<String> roomInventory;
    
    /**
     * Room Constructor 
     * Initializes room name and number
     * Initializes all three doors to non-applicable
     * Initializes empty room inventory
     * @param name room name 
     * @param number room number: any integer
     */
    Room(String name, int number)
    {
        roomNumber = number;
        roomName = name;
        for (int index = 0; index < MAX_NUMBER_OF_DOORS; index++) 
            doors[index] = NON_APPLICABLE;
        roomInventory = new ArrayList<String>();
    }
    
    public int getRoomNumber()
    {
        return this.roomNumber;
    }
    
    public void setRoomNumber(int number)
    {
        this.roomNumber = number;
    }
    
    public String getRoomName()
    {
        return this.roomName;
    }
    
    public void setRoomName(String name)
    {
        this.roomName = name;
    }
    
    public int getDoor(int door)
    {
        return this.doors[door - 1];
    }
    
    public void setDoor(int door, int destination)
    {
        this.doors[door - 1] = destination;
    }
    
    public String getBaseRoomDescription()
    {
        return this.baseRoomDescription;
    }
    
    public void setBaseRoomDescription(String description)
    {
        this.baseRoomDescription = description;
    }
   
    public String getRoomDescription()
    {
        return this.roomDescription;
    }
    
    public void setRoomDescription(String description)
    {
        this.roomDescription = description;
    }
        
    /**
     * Append Room Description Method<br>
     * Appends a provided String onto the room description on a new line.<br>
     * @param description - String to be appended.<br>
     */
    public void appendRoomDescription(String description)
    {
        this.roomDescription += "\n" + description;
    }
    
    /**
     * If Door Exists Method<br>
     * Checks if there is a non-default destination for the specified door.  Returns a boolean.<br>
     * @param door - integer number of door in question<br>
     * @return boolean value - false if door is default, else true.<br>
     */
    public boolean ifDoorExists(int door)
    {
        if(this.doors[door] != NON_APPLICABLE)
            return true;
        else 
            return false;
    }
    
     /**
     * Get Number Of Doors Method<br>
     * Counts the number of doors in the room that have viable destinations.<br>
     * @return int Number of doors in the room<br>
     */
    public int getNumberOfDoors()
    {
        int returnInt = 0;
        for(int door : doors)
        {
            if (door != NON_APPLICABLE)
            {
                returnInt++;
            }
        }
        return returnInt;
    }
    
    public List<String> getRoomInventory()
    {
        return this.roomInventory;
    }
    
    public void setRoomInventory(List<String> items)
    {
        roomInventory.clear();
        roomInventory = items;
    }
    
    /**
     * Add Inventory Item Method<br>
     * Adds specified item to the room's inventory.  Room inventories have no item limits.<br>
     * @param item specified item to be added to room's inventory<br>
     */
    public void addInventoryItem(String item)
    {
        this.roomInventory.add(item);
        //System.out.println("dropped " + item + " hit the floor"); <- Debug
    }
    
    /**
     * Remove Inventory Item Method<br>
     * Checks if the room's inventory contains specified item and, if so, removes it.<br>
     * Then regenerates the room description to not include removed item.<br>
     * @param item specified item to be removed from room's inventory<br>
     * @return boolean value true if operation was successful, else false<br>
     */
    public boolean removeInventoryItem(String item)
    {
        boolean hasItem = false;
        if(ifRoomHasItem(item))
        {
            hasItem = true;
            this.roomInventory.remove(item);
            this.roomDescription = this.baseRoomDescription;
            appendRoomDescription("There are " + getNumberOfDoors() + " doors here.");
            for(String roomInventoryItem : this.roomInventory)
            {
                appendRoomDescription("There is a " + roomInventoryItem + " on the floor here.");
            }
        }
        return hasItem;
    }
    
    /**
     * If Room Has Item Method<br>
     * Checks if room's inventory contains specified item.<br>
     * @param item specified item to be searched for<br>
     * @return boolean value true if room's inventory contains item, else false.<br>
     */
    public boolean ifRoomHasItem(String item)
    {
        boolean hasItem = false;
        for(String roomInventoryItem : this.roomInventory)
        {
            System.out.println(roomInventoryItem);
            System.out.println(item);
            if (roomInventoryItem.toLowerCase().equals(item.toLowerCase())) 
            {
                hasItem = true;
                //System.out.println("room has " + item); <-Debug
            }
        }
        return hasItem;
    }
    
    /**
     * To String Method<br>
     * Creates and returns a string containing the name, reference number, 
     * door destinations, base description, room inventory contents, 
     * and current description for the room.<br>
     * @return String all information regarding the Room and its variables.<br>
     */
    public String toString()
    {
        String returnString;
        returnString = "Room number: " + this.roomNumber;
        returnString += "\tRoom name: " + this.roomName;
        returnString += "\nBase room description: " + this.baseRoomDescription;
        returnString += "\nRoom description: \n" + this.roomDescription;
        for (int i = 0; i < MAX_NUMBER_OF_DOORS; i++) 
        {
            if (this.ifDoorExists(i))   
            {
                returnString += "\tDoor " + (i + 1) + ": " + this.doors[i];
            }
            else
                returnString += "\tDoor " + (i + 1) + ": NA";
        }
        returnString += "Room contains: ";
        for(String item : this.roomInventory)
        {
            returnString += "\n" + item;
        }
        returnString += "\n";
       return returnString;
    }
}
