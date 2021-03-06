
package unit5_mathgame;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

 /* 
 *Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 * 
 */

/**
 *
 * Abstracting the logic needed for making mathematical calculations
 * 
 * @author John Mambo <john.mambo@gmail.com>
 * @param <T> Template type parameter with {@code Double} upper bound 
 * @param <U> Template type parameter with {@code String} upper bound
 * @see IOperation
 * @see Add
 * @see Subtract
 * @see Multiply
 * @see Divide
 * 
 */
public abstract class Operator<T extends Double, U extends String> implements IOperation<T,U> {
    private static final Object LOCK = new Object();
    private U lastResponse;
    public final ConcurrentHashMap<DefineValue, T> valuePair = new ConcurrentHashMap<>(1,1f,1);
    
    /**
     * Provides the means to generate the Appropriate {@code FIRSTVALUE} and  {@code SECONDVALUE}
     * @param valuePair {@code ConcurrentHashMap<DefineValue, T>} Which contains all the variables of the Math Game.
     * @return {@code ConcurrentHashMap<DefineValue, T>} Adds the FIRSTVALUE AND SECONDVALUE to the Math Game variables.
     * @throws  IllegalArgumentException  if the parent argument is {@code null}<br/>
     *                                  or if {@code DefineValue.UPPERLIMIT} is {@code null}<br/>
     *                                  or if {@code DefineValue.LOWERLIMIT} is {@code null}<br/>
     *                                  or if {@code DefineValue.LOWERLIMIT} is greater than the {@code DefineValue.UPPERLIMIT} is {@code null}<br/>
     * @see generateValuePair
     * @see IOperation.DefineValue
     * @see ConcurrentHashMap
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
    
    /**
     * 
     * @param valuePairInput
     * @param level
     * @param operation
     * @return ConcurrentHashMap Which contains the Math Game variables.
     * 
     * @see range(Level level, Operation operation)
     * @see IOperation.Level
     * @see ConcurrentHashMap
     */
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
    
    /**
     * Provides an internal means of setting the Math Variables.
     * @param inputConcurrentHashMap {@code ConcurrentHashMap<DefineValue, T>} Which is contains the Math Game variables.
     * @see IOperation.DefineValue
     * @see ConcurrentHashMap
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
    
    /**
     * Calculates the results based on the values generated by {@code generateValuePair()}:<br/>&nbsp; {@code DefineValue.FIRSTVALUE} and {@code DefineValue.SECONDVALUE}<br/>
     * and puts the results in {@code DefineValue.ANSWER}
     * @param level The level of ease
     * @param operation The Mathematical Operation
     */
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
        U _response;
        Random _randNumGen = new Random();
        int _num = _randNumGen.nextInt(5);
        if (isCorrect) {
           switch(_num){
                case 0: _response = (U)"Very good!";
                    break;
                case 1: _response = (U)"Excellent!";
                    break;
                case 2: _response = (U)"Nice work!";
                    break;
                case 3: _response = (U)"Keep up the good work!";
                    break;
                case 4: _response = (U)"Hoorah!";
                    break;
                default: return null; 
            } 
        }else{
            switch(_num){
                case 0: _response = (U)"No. Please try again!";
                    break;
                case 1: _response = (U)"Wrong. Try once more!";
                    break;
                case 2: _response = (U)"Don't give up!";
                    break;
                case 3: _response = (U)"No. Keep trying!";
                    break;
                case 4: _response = (U)"Dig in a little deeper. It will pay-off!";
                    break;
                default: return null; 
            }                
        }
        /**
         * Eliminate repeating the same response consecutively.
         */
        if(this.lastResponse != null && this.lastResponse.equalsIgnoreCase(_response) ){
            this.response(isCorrect);
        }
        
        this.lastResponse = (U)_response;
        return (U)_response;     
    }//end response
    
    /**
     * Plays a sound corresponding to the answer provided.<br/>
     * The aim is to keep the user engaged.
     * @param isCorrect {@code boolean} Which indicates if the user's answer to the question is correct.
     * @throws UnsupportedOperationException When the method is not implemented
     * @see Clip
     * @see AudioSystem
     */
     @Override
    public synchronized void playResponse(boolean isCorrect) throws UnsupportedOperationException{
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream;
                if (isCorrect) {
                   inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/Sound/fanfare.wav")); 
                }else{
                    inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/Sound/matchover.wav"));
                }

            clip.open(inputStream);
            clip.start(); 
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
          System.out.println(ex);
        }
    }//end playResponse
}//end class Operator
