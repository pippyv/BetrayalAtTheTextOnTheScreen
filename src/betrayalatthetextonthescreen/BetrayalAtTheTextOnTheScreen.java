/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

/**
 * Main Class<br>
 * 
 * <P>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class BetrayalAtTheTextOnTheScreen 
{
    static Player player1, player2;
    static Debug debug;
    static Map map;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        debug = new Debug();
        map = new Map();
        player1 = new Player("Player 1", map);
        //player2 = new Player("Player 2", map);
        player1.addInventoryItem("no tea");
        
        
    }
}
