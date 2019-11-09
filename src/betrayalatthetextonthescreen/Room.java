/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

/**
 *
 * Room Class - each instance is a room<br>
 * each room has a name (initialized in constructor)
 * each room has a reference # - no assumption in room class re: range, other than an integer
 * each room currently has 3 doors (ToDo - variable # of doors)
 * door is initially not applicable, but initialized to point to another room (possibly itself)
 * assumptions:
 *  external code initializing rooms initializes either two or three doors
 *  if doors, always door 1 and door 2 are initialized, door 3 remains NA
 *  external code ensures there is a valid path through all rooms
 * <P>
 * ToDos
 *  refactor to variable number of rooms
 *  change hardcoding doorOne, etc. to a door array[# of doors]
 *  add method ifDoorExists() based on door number
 * @author Pippy
 * 
 */
public class Room 
{
    private static final int NON_APPLICABLE = -1;
    private String roomName;
    private String roomDescription;
    private int roomNumber;
    private int doorOne;
    private int doorTwo;
    private int doorThree;
    
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
        doorOne = NON_APPLICABLE;
        doorTwo = NON_APPLICABLE;
        doorThree = NON_APPLICABLE;
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
    
    public int getDoorOne()
    {
        return this.doorOne;
    }
    
    public void setDoorOne(int door)
    {
        this.doorOne = door;
    }
    
    public int getDoorTwo()
    {
        return this.doorTwo;
    }
    
    public void setDoorTwo(int door)
    {
        this.doorTwo = door;
    }
    
    public int getDoorThree()
    {
        return this.doorThree;
    }
    
    public void setDoorThree(int door)
    {
        this.doorThree = door;
    }
    
    public boolean ifDoorThreeExists()
    {
        if(this.doorThree != NON_APPLICABLE)
            return true;
        else 
            return false;
    }
    
    public String roomInfoString()
    {
        String returnString;
        if (this.ifDoorThreeExists()) 
            returnString = "Room number: " + roomNumber + "\tRoom name: " + roomName + "\tDoor one: " + doorOne + "\tDoor two: " + doorTwo + "\tDoor three: " + doorThree;
        else
            returnString = "Room number: " + roomNumber + "\tRoom name: " + roomName + "\tDoor one: " + doorOne + "\tDoor two: " + doorTwo + "\tDoor three: NA";
        return returnString;
    }
}
