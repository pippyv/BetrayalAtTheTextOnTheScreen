/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author Pippy
 */
public class BetrayalAtTheTextOnTheScreen 
{
    final static int NUMBER_OF_ROOMS = 7;
    static Room[] rooms = new Room[NUMBER_OF_ROOMS];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        buildMap();
        // TODO code application logic here
    }
    
    static void buildMap()
    {
        //initialize randomizer
        Random rand = new Random();
        int randomNumber;
        //create list to hold the fixed loop of rooms that keeps all rooms accessible
        List<Integer> roomList = new ArrayList<Integer>();
        roomList.add(NUMBER_OF_ROOMS - 2);
        roomList.add(NUMBER_OF_ROOMS - 1);
        for (int index = 0; index < NUMBER_OF_ROOMS - 2; index++) 
            roomList.add(index);
        //create rooms
        for (int index = 0; index < NUMBER_OF_ROOMS; index++) 
        {
            rooms[index] = new Room(("" + index), index);
            //if random equals true the room will have two doors, else three
            if(rand.nextBoolean()) //for two door rooms
            {
                System.out.println("Room " + index + " has two doors.");
                //if random equals true progression will be gaurenteed through door one, else door two
                //set doors
                if(rand.nextBoolean())
                {
                    System.out.println("Room " + index + "'s door one progresses.");
                    rooms[index].setDoorOne(roomList.get(index));
                    rooms[index].setDoorTwo(rand.nextInt(NUMBER_OF_ROOMS - 1));
                }
                else
                {
                    System.out.println("Room " + index + "'s door two progresses.");
                    rooms[index].setDoorTwo(roomList.get(index));
                    rooms[index].setDoorOne(rand.nextInt(NUMBER_OF_ROOMS - 1));
                }
                System.out.println(rooms[index].ifDoorThreeExists());
            }
            else //for three door rooms
            {
                System.out.println("Room " + index + " has three doors.");
                randomNumber = rand.nextInt(3);
                //if random equals 0 progression will be gaurenteed through door one, if 1 then door two, if 2 then door three
                //set doors
                switch(randomNumber)
                {
                    case 0:
                        System.out.println("Room " + index + "'s door one progresses.");
                        rooms[index].setDoorOne(roomList.get(index));
                        rooms[index].setDoorTwo(rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoorThree(rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 1:
                        System.out.println("Room " + index + "'s door two progresses.");
                        rooms[index].setDoorTwo(roomList.get(index));
                        rooms[index].setDoorOne(rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoorThree(rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                    case 2:
                        System.out.println("Room " + index + "'s door three progresses.");
                        rooms[index].setDoorThree(roomList.get(index));
                        rooms[index].setDoorOne(rand.nextInt(NUMBER_OF_ROOMS - 1));
                        rooms[index].setDoorTwo(rand.nextInt(NUMBER_OF_ROOMS - 1));
                        break;
                }
                System.out.println(rooms[index].ifDoorThreeExists());
            }
        }
        //rooms[NUMBER_OF_ROOMS - 1].setRoomName("Front Hall");
        //print generated map to console
        for (int index = 0; index < NUMBER_OF_ROOMS; index++) 
        {
            System.out.println(rooms[index].roomInfoString());
        }
    }
    
}
