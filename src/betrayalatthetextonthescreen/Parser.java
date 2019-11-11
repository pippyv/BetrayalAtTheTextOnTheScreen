/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Parser 
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        String temp;
        
        String action [] = {"pick up", "put down", "drop", "go", "move",
                "open", "look", "view", "view inventory", "inventory"};
        
        System.out.println ("Please enter menu command, followed by object/direction"
                + ", seperated by a comma");
        temp = scan.nextLine();
        temp = temp.toLowerCase();
             
        
        String cmd [] = temp.split(",");      
        System.out.println ("Commands: " + Arrays.toString(cmd));   
        
        switch (cmd[0])
        {
            case "inventory":
            case "view inventory":
            case "open":
                checkInventory();
                break;
            case "look":
            case "view":
                checkSurrounding();
            case "pick up":
                pickUp(cmd[1]);
                break;
            case "put down":
            case "drop":
                putDown(cmd[1]);
                break;
            case "go":
            case "move":
                moveDirection(cmd[1]);
                break;
            default:
                System.out.println ("Invalid command");
        
        }    

    }
    
    public static void moveDirection (String dir)
    {
        System.out.println ("Moved: " +dir);
    }
    
    public static void putDown(String item)
    {
        System.out.println ("Put down: " +item);
    }
    
    public static void pickUp(String item)
    {
        System.out.println ("Picked Up: " +item);
    }
    
    public static void checkSurrounding()
    {
        System.out.println ("Checked Surrounding");
    }
    
    public static void checkInventory ()
    {
        System.out.println ("Checked Inventory");
    }
}
