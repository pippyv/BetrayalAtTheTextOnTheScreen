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
    static Map map;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        debug = new Debug();
        map = new Map();
        player = new Player("Player 1", map);
        player2 = new Player("Player 2", map);
        player.addInventoryItem("no tea");
        
    }
}
