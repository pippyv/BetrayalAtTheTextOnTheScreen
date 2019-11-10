
package betrayalatthetextonthescreen;

/**
 *
 * Room Class - instantiated for each room.<br>
 * Constructor initializes room name, reference number integer, and destination for three doors (defaulted to NON_APPLICABLE).<br>
 * Reference number has no range restrictions.<br>
 * Assumes external code will initialize destinations for doors.<br>
 * Assumes external code handles pathing.<br>
 * <P>
 * TODO<br>  
 * Move door variables to an integer array to allow for a more easily variable number of doors.<br>
 * Add ifDoorExists() method to accept a door number and return a boolean value based on if that door has a destination.<br>
 * Change name of roomInfoString() method to toString().<br>
 *
 * @author Pippy V.
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
     * To String Method<br>
     * Creates and returns a string containing the name, reference number, door destinations, and description for the room.<br>
     * @return String - all information regarding the Room and its variables.<br>
     */
    public String roomInfoString()
    {
        String returnString;
        returnString = "Room number: " + this.roomNumber;
        returnString += "\tRoom name: " + this.roomName;
        returnString += "\tRoom description: " + this.roomDescription;
//        for (int i = 0; i < MAX_NUMBER_OF_DOORS; i++) 
//        {
//            if (this.ifDoorExists(i))   
//            {
//                returnString = "\tDoor " + i + 1 ": " + this.doors[i];
//            }
//            else
//                returnString += "\tDoor " + i + 1 ": NA";
//        }
        if (this.ifDoorExists(0))            
            returnString += "\tDoor one: " + this.doors[0];
        else
            returnString += "\tDoor one: NA";
        if (this.ifDoorExists(1))            
            returnString += "\tDoor two: " + this.doors[1];
        else
            returnString += "\tDoor two: NA";
        if (this.ifDoorExists(2))            
            returnString += "\tDoor three: " + this.doors[2];
        else
            returnString += "\tDoor three: NA";
        returnString += "\n";
       return returnString;
    }
}
