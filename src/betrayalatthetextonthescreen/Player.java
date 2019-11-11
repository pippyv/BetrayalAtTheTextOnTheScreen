
package betrayalatthetextonthescreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Player Class <br>
 *    Each instance is a player <br>
 *    Player initialized with name, default name is "I am confused" <br>
 * <br>
 * Instance variables: player name <br>
 * Methods getPlayerName and setPlayerName <br>
 * add other class variables and methods as discussed <br>
 * @author pippy
 */
public class Player {
    static final String DEFAULT_PLAYER_NAME = "I'm a default";
    static final int MAX_INVENTORY_SIZE = 10;
    private String playerName;
    private int playerLocation;
    private List<String> playerInventory;
    
    Player (String name) 
    {
        playerName = name;
        playerLocation = 0;
        playerInventory = new ArrayList<String>();
    }
    
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    public int getPlayerLocation()
    {
        return this.playerLocation;
    }
    
    public void setPlayerLocation(int room)
    {
        this.playerLocation = room;
    }
    
    public List<String> getPlayerInventory()
    {
        return this.playerInventory;
    }
    
    public void clearPlayerInventory()
    {
        this.playerInventory.clear();
    }
    
    public boolean ifPlayerHasItem (String item)
    {
        boolean playerHasItem = false;
        for (String playerInventoryItem : this.playerInventory) 
        {
            if (playerInventoryItem.equals(item)) 
            {
                playerHasItem = true;
            }
        }
        return playerHasItem;
    }
    
    public String addInventoryItem(String item)
    {
        if(this.playerInventory.size() < MAX_INVENTORY_SIZE)
        {
            this.playerInventory.add(item);
            return item + " has been added to your inventory.";
        }
        else
        {
            return "Your inventory is full.  You'll have to drop something first.";
        }
    }
    // other methods to follow
    
}
