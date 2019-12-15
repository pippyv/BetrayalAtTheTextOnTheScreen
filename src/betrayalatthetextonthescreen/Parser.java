
package betrayalatthetextonthescreen;
import java.util.Arrays;
/**
 * Parser Class - instantiated for each GUI (currently 1 GUI).<br>
 * Constructor initializes a debug and saves a pointer to the associated GUI.<br>
 * <P>
 * Instance variables: pointer to GUI and debug<br>
 * Methods: parse input, pick up, put down, check inventory, check surrounding<br>
 * <P>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Parser 
{
    private final String action [] = {"pick up", "put down", "drop", "go", "move",
                "open", "look", "view", "check inventory", "inventory", "quit"};
    GUI gui;
    private static Debug debug;
    
    Parser(GUI inputGui)
    {
        debug = new Debug();
        gui = inputGui;
    }
    
    
    /**
     * Parse Input Method<br>
     * Takes input from the GUI and splits it into commands that can be handled elsewhere.<br>
     * Prints error messages to GUI under certain circumstances.<br>
     * @param temp String to be parsed<br>
     * @return String Array - Array of individual commands as Strings<br>
     */
    public String[] parseInput(String temp) 
    {
        String input;
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
                {
                    gui.writeGUI("I don't understand that.");
                    cmd[0] = "else";
                }
                break;
            case "put":
                if(cmd[1].equals("down"))
                {
                    cmd[1] = "";
                    cmd = putDown(cmd);    
                }
                else
                {
                    gui.writeGUI("I don't understand that.");
                    cmd[0] = "else";
                }
                break;
            case "drop":
                cmd = putDown(cmd);
                break;
            case "take":
                cmd = pickUp(cmd);
                break;
            case "open":
            case "check":
                switch(cmd[1])
                {
                    case "inventory":
                        for (int index = 1; index < cmd.length; index++) 
                        {
                            cmd[index-1] = cmd[1];
                        }
                        break;
                    case "door":
                        if(cmd.length >= 3)
                        {
                            cmd[0] = "go";
                            cmd[1] = cmd[2];
                        }
                        break;
                    case "locked":
                        if(cmd[2].equals("door"))
                        {
                            cmd[0] = "unlock"; 
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "go":
            case "move":
                if(cmd.length > 3)
                {
                    gui.writeGUI("That's too many words for me to understand.");
                    cmd[0] = "else";
                }
                else if (cmd.length > 2)
                {
                    if(cmd[1].equals("door"))
                    {
                        cmd[1] = cmd[2];
                    }
                    else if(cmd[1].equals("locked"))
                    {
                        if(cmd[2].equals("door"))
                        {
                            cmd[0] = "unlock";
                        }
                    }
                }
                else
                {
                    gui.writeGUI("Please specify where you want to go.");
                    cmd[0] = "else";
                }
                break;
            case "quit":
                gui.writeGUI("Thank you for playing.");
                break;
            case "sit":
                break;
            case "unlock":
                break;
            case "help":
                if (cmd.length != 1) 
                {
                    gui.writeGUI("I can't help with that.\n"
                            + "Type 'help' if you would like player instructions.");
                    cmd[0] = "else";
                }
                break;
            default:
                gui.writeGUI("I don't understand that.");
        }
        return cmd;
    }
    
    /**
     * Put Down Method<br>
     * Condenses all words after the command "put down" into one string to account for multi word item names.<br>
     * @param cmd String Array of command followed by other words input from user<br>
     * @return String Array - Command followed by item name<br>
     */
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
    
    /**
     * Pick Up Method<br>
     * Condenses all words after the command "pick up" into one string to account for multi word item names.<br>
     * @param cmd String Array of command followed by other words input from user<br>
     * @return String Array - Command followed by item name<br>
     */
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
    
    /**
     * Check Surrounding Method<br>
     * Prints message to debug that the player has checked their surroundings.<br>
     */
    public void checkSurrounding()
    {
        debug.debug("Checked Surrounding");
    }
    
    /**
     * Check Surrounding Method<br>
     * Prints message to debug that the player has checked their inventory.<br>
     */
    public void checkInventory ()
    {
        debug.debug("Checked Inventory");
    }
}