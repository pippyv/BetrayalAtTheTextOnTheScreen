
package betrayalatthetextonthescreen;

/**
 *
 * Room Class - instantiated for each room.<br>
 * Constructor initializes room name, reference number integer, and destination for three doors (defaulted to NON_APPLICABLE).<br>
 * Reference number has no range restrictions.<br>
 * Assumes external code will initialize destinations for doors.<br>
 * Assumes external code handles pathing.<br>
 * Instance variables: room name, room description, doors array, and room reference number<br>
 * Methods: get/set room number, get/set room name, get/set door, get/set/append room description, if door exists, get number of doors, to string<br>
 * <P>
 * TODO<br>  
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 *
 */

public class Room 
{
    private static final int NON_APPLICABLE = -1;
    private static final int MAX_NUMBER_OF_DOORS = 5;
    private String roomName;
    private String roomDescription;
    private int[] doors = new int[MAX_NUMBER_OF_DOORS];
    private int roomNumber;
    
    /**
     * Room Constructor 
     * Initializes room name and number
     * Initializes all three doors to non-applicable
     * @param name room name 
     * @param number room number: any integer
     */
    Room(String name, int number)
    {
        roomNumber = number;
        roomName = name;
        for (int index = 0; index < MAX_NUMBER_OF_DOORS; index++) 
            doors[index] = NON_APPLICABLE;
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
    
    /**
     * To String Method<br>
     * Creates and returns a string containing the name, reference number, door destinations, and description for the room.<br>
     * @return String - all information regarding the Room and its variables.<br>
     */
    public String toString()
    {
        String returnString;
        returnString = "Room number: " + this.roomNumber;
        returnString += "\tRoom name: " + this.roomName;
        returnString += "\tRoom description: " + this.roomDescription;
        for (int i = 0; i < MAX_NUMBER_OF_DOORS; i++) 
        {
            if (this.ifDoorExists(i))   
            {
                returnString += "\tDoor " + (i + 1) + ": " + this.doors[i];
            }
            else
                returnString += "\tDoor " + (i + 1) + ": NA";
        }
        returnString += "\n";
       return returnString;
    }
}
