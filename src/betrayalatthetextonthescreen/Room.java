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
 * <P>
 * Instance variables: room name, base room description, room description, 
 * doors array, room reference number, room inventory, and debug<br>
 * Methods: constructor, get/set room number, get/set room name, get/set door,
 * get/set room visited, get/set/append base room description, get/set/append/enter room description, 
 * if door exists, get number of doors, get/set room inventory, add/remove inventory item, 
 * if room has item, and to string<br>
 * <P>
 * Todo:<br>  
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
    private Inventory roomInventory;
    private Boolean roomVisited;
    private Debug debug;
    private List<String> items;
    
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
        roomInventory = new Inventory();
        roomVisited = false;
        debug = new Debug();
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
        return this.doors[door];
    }
    
    public void setDoor(int door, int destination)
    {
        this.doors[door] = destination;
    }
    
    public Boolean getRoomVisited()
    {
        return this.roomVisited;
    }
    
    public void setRoomVisited(Boolean visited)
    {
        this.roomVisited = visited;
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
     * @param description String to be appended.<br>
     */
    public void appendRoomDescription(String description)
    {
        this.roomDescription += "\n" + description;
    }
    
    /**
     * Append Base Room Description Method<br>
     * Appends a provided String onto the base room description on a new line.<br>
     * For use of added permanent fixtures in a room.<br>
     * @param description String to be appended.<br>
     */
    public void appendBaseRoomDescription(String description)
    {
        this.baseRoomDescription += "\n" + description;
    }
    
    /**
     * Enter Room Description Method<br>
     * Returns a description of the room the player is entering.<br>
     * If player has been in room before returns a short description, else long description.<br>
     * Otherwise returns the full room description.<br>
     * @param visited boolean value of whether or not the player has entered this room before.<br>
     * @return String - Description to be printed upon entering the room.<br>
     */
    public String enterRoomDescription(boolean visited)
    {
        if (visited) 
        {
            return "You are in the " + this.roomName;
        }
        else
        {
            return this.roomDescription;
        }
    }
    
    /**
     * If Door Exists Method<br>
     * Checks if there is a non-default destination for the specified door.  Returns a boolean.<br>
     * @param door integer number of door in question<br>
     * @return boolean value - false if door is default, else true.<br>
     */
    public boolean ifDoorExists(int door)
    {
        if((door >= 0) && (door < MAX_NUMBER_OF_DOORS))
        {
            if(this.doors[door] != NON_APPLICABLE)
                return true;
            else 
                return false;
        }
        else
            return false;
    }
    
     /**
     * Get Number Of Doors Method<br>
     * Counts the number of doors in the room that have viable destinations.<br>
     * @return int - Number of doors in the room<br>
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
    
    public Inventory getRoomInventory()
    {
        return this.roomInventory;
    }
    
    public void setRoomInventory(List<String> items)
    {
        this.roomInventory.clearInventory();
                if (getRoomNumber()== 7)
                {
                    items.add("key");
                }
        this.roomInventory.setInventory(items);
    }
    
    /**
     * Add Inventory Item Method<br>
     * Adds specified item to the room's inventory.  Room inventories have no item limits.<br>
     * @param item specified item to be added to room's inventory<br>
     */
    public void addInventoryItem(String item)
    {
        if (getRoomNumber() == 7)
        {
            this.roomInventory.addInventoryItem("key");
        }
        this.roomInventory.addInventoryItem(item);
        debug.debug("dropped " + item + " hit the floor");
    }
    
    /**
     * Remove Inventory Item Method<br>
     * Checks if the room's inventory contains specified item and, if so, removes it.<br>
     * Then regenerates the room description to not include removed item.<br>
     * @param item specified item to be removed from room's inventory<br>
     */
    public void removeInventoryItem(String item)
    {
        if(this.roomInventory.ifHasItem(item))
        {
            this.roomInventory.removeInventoryItem(item);
            this.roomDescription = this.baseRoomDescription;
            appendRoomDescription("There are " + getNumberOfDoors() + " doors here.");
            List<String> inventory = this.roomInventory.getInventory();
            for(String roomInventoryItem : inventory)
            {
                appendRoomDescription("There is a " + roomInventoryItem + " on the floor here.");
            }
        }
    }
    
    /**
     * If Room Has Item Method<br>
     * Checks if room's inventory contains specified item.<br>
     * @param item specified item to be searched for<br>
     * @return boolean value - true if room's inventory contains item, else false.<br>
     */
    public boolean ifRoomHasItem(String item)
    {
        return this.roomInventory.ifHasItem(item);
    }
    
    /**
     * To String Method<br>
     * Creates and returns a string containing the name, reference number, 
     * door destinations, base description, room inventory contents, 
     * and current description for the room.<br>
     * @return String - All information regarding the Room and its variables.<br>
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
        returnString += this.roomInventory;
        returnString += "\n";
        return returnString;
    }
}
