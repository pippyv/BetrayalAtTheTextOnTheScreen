
package betrayalatthetextonthescreen;

/**
 * Player Class <br>
 *    Each instance is a player <br>
 *    Player initialized with name, default name is "I am confused" <br>
 * <br>
 * Instance variables: player name <br>
 * Methods getPlayerName & setPlayerName <br>
 * @author pippy
 */
public class Player {
    static final String DEFAULT_PLAYER_NAME = "I am confused";
    String playerName;
    
    Player (String name) {
        this.playerName = name;
    }
    
    Player () {
        this (DEFAULT_PLAYER_NAME);
    }
    
    public String getPlayerName() {
        return this.playerName;
    }
    
    public String toString() {
        return("Player: player name = " + getPlayerName());
    }
    

    
    // other methods to follow
    
}
