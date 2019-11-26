
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
                "open", "look", "view", "check inventory", "inventory", "quit"};
    Scanner scan;
    private static Debug debug;
    
    Parser()
    {
        scan = new Scanner(System.in);
        debug = new Debug();
    }
    
    
    /**
     * parseInput prints prompt on standard output (ie console)
     * reads next line
     * parses the command
     * TODO<br>
     * Get rid of comma reliance
     * Trim Strings before sending them to other classes
     * 
     * @return command entered by user, including "quit" = quit game
     */
    public String[] parseInput(String temp) 
    {
        //String temp;
        String input;
        
        System.out.println ("Please enter menu command, followed by object/direction");
               // + ", seperated by a comma");
        //temp = scan.nextLine();
        temp = temp.toLowerCase();
             
        
        input = temp.trim().replaceAll(" +", " ");
        //String cmd [] = temp.split(",");   
        String cmd [] = input.split(" ");
        debug.debug("Commands: " + Arrays.toString(cmd));    
        
        switch (cmd[0])
        {
            case "inventory":
            case "open": 
            case "check":
                checkInventory();
                break;
            case "view":
            case "look":
                checkSurrounding();
                break;
            case "pick":
                if(cmd[1].equals("up"))
                {
                    cmd[1] = "";
                    cmd = pickUp(cmd);
                }
                else
                    System.out.println("Invalid command.");
                break;
            case "put":
                if(cmd[1].equals("down"))
                {
                    cmd[1] = "";
                    cmd = putDown(cmd);    
                }
                else
                    System.out.println("Invalid command.");
                break;
            case "drop":
                cmd = putDown(cmd);
                break;
            case "take":
                cmd = pickUp(cmd);
                break;
            case "go":
            case "move":
                if(cmd.length == 2)
                    moveDirection(cmd[1]);
                else
                {
                    System.out.println("Please specify where you want to go.");
                    cmd[0] = "else";
                }
                break;
            case "quit":
                System.out.println ("Thank you for Playing");
                break;
            default:
                System.out.println ("Invalid command");
        }
        return cmd;
    }
    
    public static void moveDirection (String dir)
    {
        debug.debug("Moved: " + dir);
    }
    
    public static String[] putDown(String[] cmd)
    {
        if(cmd.length >= 2)
        {
            for(int index = 2; index < cmd.length; index++)
            {
                cmd[1] += " " + cmd[index];
            }
            cmd[1] = cmd[1].trim();
            debug.debug("Put down: " + cmd[1]);
        }
        else
        {
            System.out.println("You need to specify an item to put down.");
            cmd[0] = "else";
        }
        return cmd;
    }
    
    public static String[] pickUp(String[] cmd)
    {
        if(cmd.length >= 2)
        {
            for(int index = 2; index < cmd.length; index++)
            {
                cmd[1] += " " + cmd[index];
            }
            cmd[1] = cmd[1].trim();
            debug.debug("Picked Up: " + cmd[1]);
        }
        else
        {
            System.out.println("You need to specify an item to pick up.");
            cmd[0] = "else";
        }
        return cmd;
    }
    
    public static void checkSurrounding()
    {
        debug.debug("Checked Surrounding");
    }
    
    public static void checkInventory ()
    {
        debug.debug("Checked Inventory");
    }
}