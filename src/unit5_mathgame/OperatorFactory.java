
package unit5_mathgame;

/*
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 * Brian Bagwell       02/19/2014      Modified the switch value to take the enum
*/

/**   
 * A Factory for generating the appropriate {@link Operator} concrete class object
 * using decomposition.
 * @author John Mambo <john.mambo@gmail.com>
 * @see Operator
 * @see Add
 * @see Subtract
 * @see Multiply
 * @see Divide
 */
public final class OperatorFactory {
    /**
     * Creates the Appropriate object based on the {@link IOperation.Operation}
     * @param operation
     * @return Object of type {@link IOperation}
     * @see IOperation 
     * @see IOperation.Operation
     * @see Add
     * @see Subtract
     * @see Multiply
     * @see Divide
     */
    public IOperation CreateOperator(IOperation.Operation operation){
        
        switch(operation){
            case ADD:         return new Add();
            case SUBTRACT:    return new Subtract();
            case MULTIPLY:    return new Multiply();
            case DIVIDE:      return new Divide();
            default: return null;
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Needs constructor implimentation in the concrete classes.">
    /*
    
    public IOperation CreateOperator(IOperation.Operation operation, ConcurrentHashMap<IOperation.DefineValue, Double> valuePairInput){
    
    switch(operation.name()){
    case "ADD":         return new Add(valuePairInput);
    case "SUBTRACT":    return new Subtract(valuePairInput);
    case "MULTIPLY":    return new Multiply(valuePairInput);
    case "DIVIDE":      return new Divide(valuePairInput);
    default: return null;
    }
    }*/
//</editor-fold>
}
