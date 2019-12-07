
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
    GUI gui;
    private static Debug debug;
    
    Parser(GUI inputGui)
    {
        scan = new Scanner(System.in);
        debug = new Debug();
        gui = inputGui;
    }
    
    
    /**
     * parseInput prints prompt on standard output (ie console)
     * reads next line
     * parses the command
     * TODO<br>
     * 
     * @return command entered by user, including "quit" = quit game
     */
    public String[] parseInput(String temp) 
    {
        String input;
        //gui.writeGUI("What would you like to do?");
        temp = temp.toLowerCase();
        input = temp.trim().replaceAll(" +", " ");
        String cmd [] = input.split(" ");
        debug.debug("Commands: " + Arrays.toString(cmd));    
        
        switch (cmd[0])
        {
            case "inventory":
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
                    gui.writeGUI("Invalid command.");
                break;
            case "put":
                if(cmd[1].equals("down"))
                {
                    cmd[1] = "";
                    cmd = putDown(cmd);    
                }
                else
                    gui.writeGUI("Invalid command.");
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
                    gui.writeGUI("Please specify where you want to go.");
                    cmd[0] = "else";
                }
                break;
            case "quit":
                gui.writeGUI("Thank you for Playing");
                break;
            case "sit":
                break;
            case "open":
            case "check":
                if ("inventory".equals(cmd[1]))
                {
                    for (int index = 1; index < cmd.length; index++) 
                    {
                        cmd[index-1] = cmd[1];
                    }
                }
                break;
            default:
                gui.writeGUI("Invalid command");
        }
        return cmd;
    }
    
    public void moveDirection (String dir)
    {
        debug.debug("Moved: " + dir);
    }
    
    public String[] putDown(String[] cmd)
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
            gui.writeGUI("You need to specify an item to put down.");
            cmd[0] = "else";
        }
        return cmd;
    }
    
    public String[] pickUp(String[] cmd)
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
            gui.writeGUI("You need to specify an item to pick up.");
            cmd[0] = "else";
        }
        return cmd;
    }
    
    public void checkSurrounding()
    {
        debug.debug("Checked Surrounding");
    }
    
    public void checkInventory ()
    {
        debug.debug("Checked Inventory");
    }
}