
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
 * @param <T>
 * @param <U>
 */
public abstract class Operator<T extends Double, U extends String> implements IOperation<T,U> {
    private static final Object LOCK = new Object();
    public final ConcurrentHashMap<DefineValue, T> valuePair = new ConcurrentHashMap<>(1,1f,1);
    
    /**
     * 
     * @param valuePair
     * @return 
     * @throws  IllegalArgumentException  if the parent argument is {@code null}<br/>
     *                                  or if {@code DefineValue.UPPERLIMIT} is {@code null}<br/>
     *                                  or if {@code DefineValue.LOWERLIMIT} is {@code null}<br/>
     *                                  or if {@code DefineValue.LOWERLIMIT} is greater than the {@code DefineValue.UPPERLIMIT} is {@code null}<br/>
     */
    private ConcurrentHashMap<DefineValue, T> generateValuePair(ConcurrentHashMap<DefineValue, T> valuePair) {
        synchronized(LOCK){
            if (valuePair.get(DefineValue.UPPERLIMIT) == null 
                || valuePair.get(DefineValue.LOWERLIMIT) == null) {
                throw new IllegalArgumentException("Upper Limit or Lower Limit as defined in the parameter is null");
            }
            if (valuePair.get(DefineValue.LOWERLIMIT) > valuePair.get(DefineValue.UPPERLIMIT)) 
            {
                throw new IllegalArgumentException("Lower Limit cannot be greater than the Upper Limit of the range.");
            }
           
            Random randomNumberGenerator = new Random();

            double range = valuePair.get(DefineValue.UPPERLIMIT) - valuePair.get(DefineValue.LOWERLIMIT);
            Double firstNumber = (int)(range * randomNumberGenerator.nextDouble()) + valuePair.get(DefineValue.LOWERLIMIT);
            Double secondNumber = (int)(range * randomNumberGenerator.nextDouble()) + valuePair.get(DefineValue.LOWERLIMIT);
            
            /**
             * Since @code(ConcurrentHashMap<DefineValue, T>) is immutable, we can use the parameter directly
             * without worrying about changes outside the method via a pointer.
             */
            valuePair.put(DefineValue.FIRSTVALUE, (T)firstNumber);
            valuePair.put(DefineValue.SECONDVALUE, (T)secondNumber);
                    
            return valuePair;
        }
    }
    
    
    public final ConcurrentHashMap<DefineValue, T> range(ConcurrentHashMap<DefineValue, T> valuePairInput, Level level, Operation operation) {
        synchronized(LOCK){
            ConcurrentHashMap<DefineValue, Double> _valuePair = new ConcurrentHashMap<>(1, 3.0f, 3);
            double _multiplier = Math.pow(10, level.index());
            //double _multiplier = Math.pow(10, operation.index()) * Math.pow(10, level.index() - 1);
            switch(operation){
                case ADD:
                case SUBTRACT:  _valuePair.put(DefineValue.UPPERLIMIT, 1d * _multiplier);
                                _valuePair.put(DefineValue.LOWERLIMIT, 0d * _multiplier);
                    break;
                case MULTIPLY:
                case DIVIDE:    _valuePair.put(DefineValue.UPPERLIMIT, 0.7d * _multiplier);
                                _valuePair.put(DefineValue.LOWERLIMIT, 0.1d * _multiplier);
                    break;
            }
            return (ConcurrentHashMap<DefineValue, T>) _valuePair;
        }   
    }
    
    @Override
    public ConcurrentHashMap<DefineValue, T> getConcurrentHashMap(){
        if (this.valuePair == null || this.valuePair.isEmpty()) {
            throw new IllegalArgumentException("Parameter is null or not initialized");
        }
        else{
            ConcurrentHashMap<DefineValue, T> _getConcurrentHashMap = new ConcurrentHashMap<>(1,1f,3);
            if (this.valuePair.containsKey(DefineValue.ANSWER)) {   _getConcurrentHashMap.put(DefineValue.ANSWER, this.valuePair.get(IOperation.DefineValue.ANSWER)); }
            if (this.valuePair.containsKey(DefineValue.FIRSTVALUE)) {   _getConcurrentHashMap.put(DefineValue.FIRSTVALUE, this.valuePair.get(IOperation.DefineValue.FIRSTVALUE)); }
            if (this.valuePair.containsKey(DefineValue.LOWERLIMIT)) {   _getConcurrentHashMap.put(DefineValue.LOWERLIMIT, this.valuePair.get(IOperation.DefineValue.LOWERLIMIT)); }
            if (this.valuePair.containsKey(DefineValue.SECONDVALUE)) {   _getConcurrentHashMap.put(DefineValue.SECONDVALUE, this.valuePair.get(IOperation.DefineValue.SECONDVALUE)); }
            if (this.valuePair.containsKey(DefineValue.UPPERLIMIT)) {   _getConcurrentHashMap.put(DefineValue.UPPERLIMIT, this.valuePair.get(IOperation.DefineValue.UPPERLIMIT)); }
            
            return _getConcurrentHashMap;
        } 
    }
    
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
    
    @Override
    public final void range(Level level, Operation operation) {
        ConcurrentHashMap<DefineValue, T> _rangeValues = this.range(this.valuePair, level, operation);
        this.setConcurrentHashMap(_rangeValues);
    }
    
    @Override
    public void generateValuePair(){
        ConcurrentHashMap<DefineValue, T> _generatevaluePair = generateValuePair(this.valuePair);
        this.setConcurrentHashMap(_generatevaluePair);
    }
    

    @Override
    public abstract void result() throws UnsupportedOperationException;

    /**
     * Checks if the {@code input} parameter value provide matches the expected {@code DefineValue.ANSWER}
     * @param input The user input of their answer to the question presented.
     * @return {@code boolean} - If the user has provided the correct answer then {@code true} else {@code false} 
     */
    @Override
    public synchronized boolean isCorrect(T input){
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
    public synchronized U response(boolean isCorrect){
        Random _randNumGen = new Random();
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
    }//end response
    
    @Override
    public void playResponse(boolean isCorrect) throws UnsupportedOperationException{
        // Brian: Use this method to provide sound feedback
    }
}
