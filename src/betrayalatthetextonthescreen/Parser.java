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
    private final String action [] = {"pick up", "put down", "drop", "go", "move",
                "open", "look", "view", "view inventory", "inventory", "quit"};
    Scanner scan;
    
    Parser()
    {
        scan = new Scanner(System.in);
    }
    
    
    /**
     * parseInput prints prompt on standard output (ie console)
     * reads next line
     * parses the command
     * 
     * @return command entered by user, including "quit" = quit game
     */
    public String parseInput() {
        String temp;
        
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
                break;
            case "pick up":
                // check size of cmd[] array, if not two or >, invalid
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
            case "quit":
                break;
            default:
                System.out.println ("Invalid command");
        
        }    
        return cmd[0];

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
