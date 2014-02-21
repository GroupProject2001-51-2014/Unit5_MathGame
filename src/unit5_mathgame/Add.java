
package unit5_mathgame;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/** 
 * Provided a means to calculate the results of two randomly generated numbers
 * with a method to check the answer as well as provide the appropriate response.
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
    private static final Object LOCK = new Object();    // Constant to be used to sync
      
    public Add() {} // default constractor
    
    /**
     * Constructor that takes the {@code ConcurrentHashMap<DefineValue, T> valuePairInput}
     * and sets the math game variables accordingly
     * 
     * @param valuePairInput Keeps track of the variables that drive the math game 
     */
    private Add(ConcurrentHashMap<DefineValue, T> valuePairInput){
        if (valuePairInput == null || valuePairInput.isEmpty()) { 
            throw new IllegalArgumentException("Parameter is null or not initialized");
        }
        this.setConcurrentHashMap(valuePairInput); //setConcurrentHashMap(valuePairInput);
    }

    /**
     * Determines the results of the Addition of <b> FIRSTVALUE </b> and <b> SECONDVALUE </b>
     */
    @Override
    public final void result(){
        ConcurrentHashMap<DefineValue, T> _result = this.result(this.valuePair);
        this.setConcurrentHashMap(_result);
    }
    
    /**
     * Calculates the result of adding two variables contained in the parameter {@code ConcurrentHashMap<DefineValue, T>} <b>valuePairInput</b><br/> 
     * Those variables are <b>FIRSTVALUE</b> and <b>SECONDVALUE</b>
     * 
     * @param valuePairInput {@code ConcurrentHashMap<DefineValue, T>} All the variables required to run the Math Game
     * @return {@code ConcurrentHashMap<DefineValue, T>} which contains the data structure for the 
     *          Math Game variables.
     */
    private ConcurrentHashMap<DefineValue, T> result(ConcurrentHashMap<DefineValue, T> valuePairInput) {
        synchronized(LOCK){
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
            
            return valuePairInput;
        }
    }

    /**
     * Checks if the {@code input} parameter value provide matches the expected {@code DefineValue.ANSWER}
     * @param input The user input of their answer to the question presented.
     * @return {@code boolean} - If the user has provided the correct answer then {@code true} else {@code false}
     */
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

    /**
     * Provides an appropriate response to keep the user engaged.
     * @param isCorrect Is the user input correct?
     * @return type of <b>U</> class which contains the response message. 
     *         The upper bound of <b>U</> is of type {@code String}
     */
    @Override
    public U response(boolean isCorrect) {
        Random _randNumGen = new Random();
        synchronized(LOCK){
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
    
    /**
     * Sets the global variable from the parameter
     * @param inputConcurrentHashMap {@code ConcurrentHashMap<DefineValue, T>} Are the Math Game parameters
     */
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
