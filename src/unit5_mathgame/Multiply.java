package unit5_mathgame;

/*
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 * John Mambo          02/23/2014      Removed all other method implementation except public void {@code result()}<br/>
 *                                     They are abstracted to the abstract Operator class.
 * 
 * Brian Bagwell       02/24/2014       Modified the results method to coincide with the operation of template interface.
 * 
 */

/**
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
        
        _result = this.valuePair.get(DefineValue.FIRSTVALUE) * this.valuePair.get(DefineValue.SECONDVALUE);

        this.valuePair.put(DefineValue.ANSWER, (T)_result);

    }//end result()
}
