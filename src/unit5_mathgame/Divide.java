
package unit5_mathgame;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * <p/>
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 * John Mambo          02/23/2014      Removed all other method implementation except {@code generateValuePair()} and {@code result()}<br/>
 *                                     They are abstracted to the abstract Operator class.
 * 
 * @author Brian Bagwell
 * @param <T> Template type parameter with {@code Double} upper bound 
 * @param <U> Template type parameter with {@code String} upper bound 
 */
public class Divide<T extends Double, U extends String> extends Operator<T, U>{
    
    public Divide(){}
    
    @Override
    public void generateValuePair() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet.");
        // TODO Brian Bagwell: completed the logic here and delete line 25 above^
    }//end generateValuePair()
    
    @Override
    public void result()  throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet.");
        // TODO Brian Bagwell: completed the logic here and delete line 31 above^
    }//end result() 
}
