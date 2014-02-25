
package unit5_mathgame;

import java.security.InvalidParameterException;
import java.util.concurrent.ConcurrentHashMap;
/*
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 * John Mambo          02/23/2014      Removed all other method implementation except {@code generateValuePair()} and {@code result()}<br/>
 *                                     They are abstracted to the abstract Operator class.
*/

/**
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
        
//<editor-fold defaultstate="collapsed" desc="Suggested Approach">
        /*
        super.generateValuePair();
        Double _numerator;
        Double _denominator;
        boolean _replacedFirstValue;
        boolean _replacedSecondValue;
        Double _firstValue = this.valuePair.get(IOperation.DefineValue.FIRSTVALUE);
        Double _secondValue = this.valuePair.get(IOperation.DefineValue.SECONDVALUE);
        
        _denominator = _secondValue == 0 ? 1 : _secondValue;
        _numerator = (_firstValue == 0 ? 1 : _firstValue) * _denominator;
        
        _replacedFirstValue = this.valuePair.replace(DefineValue.FIRSTVALUE, (T)_firstValue, (T)_numerator);
        _replacedSecondValue = this.valuePair.replace(DefineValue.SECONDVALUE, (T)_secondValue, (T)_denominator);
        
        if (!_replacedFirstValue || !_replacedSecondValue) {
        throw new InvalidParameterException();
        }
        */
//</editor-fold>
    }//end generateValuePair()
    
    @Override
    public void result()  throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet.");
        // TODO Brian Bagwell: completed the logic here and delete line 54 above^
    
//<editor-fold defaultstate="collapsed" desc="Suggested Approach">
        /*
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
        if (this.valuePair.get(DefineValue.SECONDVALUE) < this.valuePair.get(DefineValue.LOWERLIMIT) || this.valuePair.get(DefineValue.SECONDVALUE) > this.valuePair.get(DefineValue.UPPERLIMIT)) {
        throw new IllegalArgumentException("second Value is out of Range.");
        }
        
        _result = this.valuePair.get(DefineValue.FIRSTVALUE) / this.valuePair.get(DefineValue.SECONDVALUE);
        
        this.valuePair.put(DefineValue.ANSWER, (T)_result);
        */
//</editor-fold>
    }//end result() 
}
