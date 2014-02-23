
package unit5_mathgame;


import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * <p/>
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 *
 * @author Brian Bagwell
 * @param <T> Template type parameter with {@code Double} upper bound 
 * @param <U> Template type parameter with {@code String} upper bound 
 * 
 */
public class Multiply<T extends Double, U extends String> extends Operator<T, U> {

    public Multiply (){}
    
    @Override
    public void result() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCorrect(T input) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public U response(boolean isCorrect) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playResponse(boolean isCorrect) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
