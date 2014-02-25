
package unit5_mathgame;

/*
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 * John Mambo           02/23/2014      Took over the implementation of the class Subtract from Alhassan Ahmed
 *
 */

/**
 * Provided a means to calculate the results of subtracting two randomly generated numbers
 * with a method to check the answer as well as provide the appropriate response.
 * @author John Mambo
 * @param <T> Template type parameter with {@code Double} upper bound 
 * @param <U> Template type parameter with {@code String} upper bound 
 * 
 */
public class Subtract<T extends Double, U extends String> extends Operator<T, U> {
    
    /**
     * Calculates the result of Subtracting two variables contained in the parameter {@code ConcurrentHashMap<DefineValue, T>} <b>valuePairInput</b><br/> 
     * Those variables are <b>FIRSTVALUE</b> and <b>SECONDVALUE</b>
     *
     * @throws UnsupportedOperationException 
     */
        @Override
    public final void result() throws UnsupportedOperationException{
        Double _result;
        
        if (this.valuePair == null || this.valuePair.isEmpty()) {
            throw new IllegalArgumentException("Parameter is null or not initialized");
        }
        if (this.valuePair.get(DefineValue.UPPERLIMIT) == null 
            || this.valuePair.get(DefineValue.LOWERLIMIT) == null 
            || this.valuePair.get(DefineValue.FIRSTVALUE) == null 
            || this.valuePair.get(DefineValue.SECONDVALUE) == null) {
            throw new IllegalArgumentException("One of the values in the parameter is null");
        }
        if (this.valuePair.get(DefineValue.LOWERLIMIT) > this.valuePair.get(DefineValue.UPPERLIMIT)) {
            throw new IllegalArgumentException("Lower Limit cannot be greater than the Upper Limit of the range.");
        }
        if (this.valuePair.get(DefineValue.FIRSTVALUE) < this.valuePair.get(DefineValue.LOWERLIMIT) || this.valuePair.get(DefineValue.FIRSTVALUE) > this.valuePair.get(DefineValue.UPPERLIMIT)) {
            throw new IllegalArgumentException("First Value is out of Range.");
        }
        if (this.valuePair.get(DefineValue.SECONDVALUE) < this.valuePair.get(DefineValue.LOWERLIMIT) || this.valuePair.get(DefineValue.SECONDVALUE) > this.valuePair.get(DefineValue.UPPERLIMIT)) {
            throw new IllegalArgumentException("second Value is out of Range.");
        }
        
        _result = this.valuePair.get(DefineValue.FIRSTVALUE) - this.valuePair.get(DefineValue.SECONDVALUE);

        this.valuePair.put(DefineValue.ANSWER, (T)_result);
    }//end result()
}//end class Subtract<T extends Double, U extends String> extends Operator<T, U>
