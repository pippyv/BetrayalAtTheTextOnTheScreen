/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

/**
 *
 * @author Pippy
 */
public class Room 
{
    private String roomName;
    private String roomDescription;
    private int roomNumber;
    private int doorOne;
    private int doorTwo;
    private int doorThree;
    
    Room(String name, int number)
    {
        roomNumber = number;
        roomName = name;
        doorOne = -1;
        doorTwo = -1;
        doorThree = -1;
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
    
    public String roomInfoString()
    {
        String returnString;
        returnString = "Room number: " + roomNumber + "\tRoom name: " + roomName + "\tDoor one: " + doorOne + "\tDoor two: " + doorTwo + "\tDoor three: " + doorThree;
        return returnString;
    }
}
