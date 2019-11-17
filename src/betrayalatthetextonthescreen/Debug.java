/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betrayalatthetextonthescreen;

/**
 *
 * @author Pippy Vallone, Trinity Headen, and Michael Elijius
 */
public class Debug 
{
    private final boolean debug = false;
    
    Debug()
    {    
    }
    
    public void debug(String message)
    {
        if(debug)
            System.out.println(message);
    }
}
