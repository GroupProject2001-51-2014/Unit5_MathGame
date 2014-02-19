
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
 * 
 * @author John Mambo <john.mambo@gmail.com>
 * @param <T> Template type parameter with {@code Double} upper bound 
 * @param <U> Template type parameter with {@code String} upper bound 
 * 
 */
public class Add<T extends Double, U extends String> extends Operator<T, U> {
    private static final Object lock = new Object();
      
    public Add() {}
    
    public Add(ConcurrentHashMap<DefineValue, T> valuePairInput){
        if (valuePairInput == null || valuePairInput.isEmpty()) { 
            throw new IllegalArgumentException("Parameter is null or not initialized");
        }
        this.setConcurrentHashMap(valuePairInput); //setConcurrentHashMap(valuePairInput);
    }


    @Override
    public final void result(){
        ConcurrentHashMap<DefineValue, T> _result = this.result(this.valuePair);
        this.setConcurrentHashMap(_result);
    }
    
    public final ConcurrentHashMap<DefineValue, T> result(ConcurrentHashMap<DefineValue, T> valuePairInput) {
        synchronized(lock){
            Double _result;
            if (valuePairInput == null || valuePairInput.isEmpty()) {
                throw new IllegalArgumentException("Parameter is null or not initialized");
            }
            if (valuePairInput.get(DefineValue.UPPERLIMIT) == null 
                || valuePairInput.get(DefineValue.LOWERLIMIT) == null 
                || valuePairInput.get(DefineValue.FIRSTVALUE) == null 
                || valuePairInput.get(DefineValue.SECONDVALUE) == null) {
                throw new IllegalArgumentException("One of the values in the parameter is null");
            }
            if (valuePairInput.get(DefineValue.LOWERLIMIT) > valuePairInput.get(DefineValue.UPPERLIMIT)) {
                throw new IllegalArgumentException("Lower Limit cannot be greater than the Upper Limit of the range.");
            }
            if (valuePairInput.get(DefineValue.FIRSTVALUE) < valuePairInput.get(DefineValue.LOWERLIMIT) || valuePairInput.get(DefineValue.FIRSTVALUE) > valuePairInput.get(DefineValue.UPPERLIMIT)) {
                throw new IllegalArgumentException("First Value is out of Range.");
            }
            if (valuePairInput.get(DefineValue.SECONDVALUE) < valuePairInput.get(DefineValue.LOWERLIMIT) || valuePairInput.get(DefineValue.SECONDVALUE) > valuePairInput.get(DefineValue.UPPERLIMIT)) {
                throw new IllegalArgumentException("second Value is out of Range.");
            }
            
            _result = valuePairInput.get(DefineValue.FIRSTVALUE) + valuePairInput.get(DefineValue.SECONDVALUE);
            valuePairInput.put(DefineValue.ANSWER, (T)_result);
            
            //this.setConcurrentHashMap(valuePairInput);
            
            return valuePairInput;
        }
    }

    @Override
    public boolean isCorrect(T input) {
        boolean _isCorrect = false;
        try{
            _isCorrect = (this.valuePair.get(DefineValue.ANSWER).toString() == null ? T.valueOf(input).toString() == null : this.valuePair.get(DefineValue.ANSWER).toString().equals(T.valueOf(input).toString()));
        }
        catch(Exception ex){
            // for debuging - normally would handle the error here.
            System.out.printf("Error occured while creating 'Add': %s%n", ex);
        }
        return _isCorrect;
    }

    @Override
    public U response(boolean isCorrect) {
        Random _randNumGen = new Random();
        synchronized(lock){
            int _num = _randNumGen.nextInt(5);
            if (isCorrect) {
               switch(_num){
                    case 0: return (U)"Very good!";
                    case 1: return (U)"Excellent!";
                    case 2: return (U)"Nice work!";
                    case 3: return (U)"Keep up the good work!";
                    case 4: return (U)"Hoorah!";
                    default: return null; 
                } 
            }
            else{
                switch(_num){
                    case 0: return (U)"No. Please try again!";
                    case 1: return (U)"Wrong. Try once more!";
                    case 2: return (U)"Don't give up!";
                    case 3: return (U)"No. Keep trying!";
                    case 4: return (U)"Dig in a little deeper. It will pay-off!";
                    default: return null; 
                }                
            } 
        }//end Sync block
    }//end response
    
    private void setConcurrentHashMap(ConcurrentHashMap<DefineValue, T> inputConcurrentHashMap){
        if (inputConcurrentHashMap == null || inputConcurrentHashMap.isEmpty()) {
            throw new IllegalArgumentException("Parameter is null or not initialized");
        }
        else{
            if (inputConcurrentHashMap.containsKey(DefineValue.ANSWER)) {   this.valuePair.put(DefineValue.ANSWER, inputConcurrentHashMap.get(IOperation.DefineValue.ANSWER)); }
            if (inputConcurrentHashMap.containsKey(DefineValue.FIRSTVALUE)) {   this.valuePair.put(DefineValue.FIRSTVALUE, inputConcurrentHashMap.get(IOperation.DefineValue.FIRSTVALUE)); }
            if (inputConcurrentHashMap.containsKey(DefineValue.LOWERLIMIT)) {   this.valuePair.put(DefineValue.LOWERLIMIT, inputConcurrentHashMap.get(IOperation.DefineValue.LOWERLIMIT)); }
            if (inputConcurrentHashMap.containsKey(DefineValue.SECONDVALUE)) {   this.valuePair.put(DefineValue.SECONDVALUE, inputConcurrentHashMap.get(IOperation.DefineValue.SECONDVALUE)); }
            if (inputConcurrentHashMap.containsKey(DefineValue.UPPERLIMIT)) {   this.valuePair.put(DefineValue.UPPERLIMIT, inputConcurrentHashMap.get(IOperation.DefineValue.UPPERLIMIT)); }
        }
    }//end setConcurrentHashMap
}//end class
