package betrayalatthetextonthescreen;

/**
 * Main Class<br>
 * <P>
 * Instance variables: player1, map, and debug.<br>
 * Methods: main method.<br>
 * <P>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class BetrayalAtTheTextOnTheScreen 
{
    static Player player1;
    static Debug debug;
    static Map map;
    static Room rm;
    
    /**
     * Main Method<br>
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        debug = new Debug();
        rm = new Room ("Observatory", 7);
        rm.addInventoryItem("key");
        map = new Map();
        player1 = new Player("Player 1", map);
        player1.addInventoryItem("no tea");
        player1.winCondition(player1);
        
    }
}
