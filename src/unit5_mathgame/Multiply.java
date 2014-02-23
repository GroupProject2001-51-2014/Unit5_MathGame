
package unit5_mathgame;

/**
 * 
 * <p/>
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 * John Mambo          02/23/2014      Removed all other method implementation except public void {@code result()}<br/>
 *                                     They are abstracted to the abstract Operator class.
 * 
 * @author Brian Bagwell
 * @param <T> Template type parameter with {@code Double} upper bound 
 * @param <U> Template type parameter with {@code String} upper bound 
 * 
 */
public class Multiply<T extends Double, U extends String> extends Operator<T, U> {
    
    public Multiply(){}
    
    @Override
    public void result() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet.");
        // TODO Brian Bagwell: completed the logic here and delete line 24 above^
    }//end result()
}
