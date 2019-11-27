/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

/**
 * Main Class<br>
 * <P>
 * ToDo: Move map builder into another file.<br>
 * ToDo: Move parser handler into another file.<br>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class BetrayalAtTheTextOnTheScreen 
{
    static Player player, player2;
    static Debug debug;
    static GUI playerGui = new GUI();
    static Map map;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        debug = new Debug();
        //buildMap();
        map = new Map();
        player = new Player("Player 1", map);
        player2 = new Player("Player 2", map);
        player.addInventoryItem("no tea");
        playerGui.writeGUI(map.rooms[player.getPlayerLocation()].enterRoomDescription()); 
    }
    
    /**
     * Parse Method<br>
     * Initializes instance of the parser class.<br>
     * Saves user input processed by the parser and responds accordingly.<br> 
     * <P>
     * TODO:<br>
     * Exception for if door number is incorrect to prevent crash.<br>
     * Accept alternate methods of specifying door to move through.<br>
     * Move out of main and into another file.<br>
     */
    static void parse(String[] userInputArray)
    {
        if(!userInputArray[0].equals("quit"))
        {
            switch (userInputArray[0])
            {
                case "inventory":
                    playerGui.writeGUI(player.getPlayerInventory().toString());
                    break;
                case "drop":
                case "put":
                    debug.debug(userInputArray[1]);
                    if(player.canRemoveInventoryItem(userInputArray[1]))
                    {
                        player.removeInventoryItem(userInputArray[1]);
                        map.putDown(player.getPlayerLocation(), userInputArray[1]);
                        playerGui.writeGUI("You are no longer carrying " + userInputArray[1] + ".");
                    }
                    else
                        playerGui.writeGUI("You are not carrying " + userInputArray[1] + ".");
                    break;
                case "look":
                case "view":
                    playerGui.writeGUI(map.look(player.getPlayerLocation()));
                    break;
                case "pick":
                case "take":
                    if(map.ifRoomHasItem(player.getPlayerLocation(), userInputArray[1]))
                    {
                        if (player.canAddInventoryItem()) 
                        {
                            player.addInventoryItem(userInputArray[1]);
                            map.removeInventoryItem(player.getPlayerLocation(), userInputArray[1]);
                            playerGui.writeGUI(userInputArray[1] + " has been added to your inventory.");
                        }
                        else
                        {
                            playerGui.writeGUI("You can't carry anything more.  You leave the " + userInputArray[1] + " where it is.");
                        }    
                    }
                    else
                    {
                        playerGui.writeGUI("You don't see " + userInputArray[1] + " here.");
                    }
                    break;
                case "move":
                case "go":
                    try
                    {
                        if(map.ifDoorExists(player.getPlayerLocation(), Integer.parseInt(userInputArray[1]) - 1))
                        {
                            player.setPlayerLocation(map.getDoor(player.getPlayerLocation(), Integer.parseInt(userInputArray[1])));
                            playerGui.writeGUI(map.enterRoomDescription(player.getPlayerLocation())); 
                        }
                        else
                            playerGui.writeGUI("That is not a door.");
                    }
                    catch(NumberFormatException exception)
                    {
                        playerGui.writeGUI("Please specify the number of the door you would like to go through.");
                    }
                    break;
                default:
            }        
            System.out.println();
            //userInputArray = parser.parseInput();
        }
    }
}
