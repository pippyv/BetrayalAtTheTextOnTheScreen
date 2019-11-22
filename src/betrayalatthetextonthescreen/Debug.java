/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    Debug()
    {}
    
    public void debug(String message)
    {
        if(DEBUG)
            System.out.println(message);
    }
}
