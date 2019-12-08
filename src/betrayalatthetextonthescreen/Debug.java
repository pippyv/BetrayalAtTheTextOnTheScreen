package betrayalatthetextonthescreen;

/**
 * Debug class - instantiated for every other class.<br>
 * Constructor initializes absolutely nothing.<br>
 * <P>
 * Methods: debug<br>
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Debug 
{
    private final boolean DEBUG = true;
    
    /**
     * Constructor<br>
     * Initializes debug instance.<br>
     */
    Debug()
    {}
    
    /**
     * Debug Method<br>
     * Prints specified message to the console if debugging is turned on.<br>
     * @param message String to be printed<br>
     */
    public void debug(String message)
    {
        if(DEBUG)
            System.out.println(message);
    }
}
