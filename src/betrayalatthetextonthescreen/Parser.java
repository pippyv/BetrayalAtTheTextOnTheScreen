
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
    private Debug debug;
    
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
    public String[] parseInput() {
        String temp;
        String input;
        
        System.out.println ("Please enter menu command, followed by object/direction");
               // + ", seperated by a comma");
        temp = scan.nextLine();
        temp = temp.toLowerCase();
             
        
        input = temp.trim().replaceAll(" +", " ");
        //String cmd [] = temp.split(",");   
        String cmd [] = input.split(" ");
        System.out.println ("Commands: " + Arrays.toString(cmd));    
        
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
                    cmd[1] = cmd[2];
                    pickUp(cmd[1]);
                    break;
            case "put":
                    cmd[1] = cmd[2];
                    putDown(cmd[1]);    
                break;
            case "drop":
                putDown(cmd[1]);
                break;
            case "go":
            case "move":
                moveDirection(cmd[1]);
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