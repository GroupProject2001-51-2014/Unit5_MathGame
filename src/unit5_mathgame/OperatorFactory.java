
package unit5_mathgame;

//import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * <p/>
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 *
 * 
 * @author John Mambo <john.mambo@gmail.com>
 */
public final class OperatorFactory {
    
    public IOperation CreateOperator(IOperation.Operation operation){
        
        switch(operation.name()){
            case "ADD":         return new Add();
            case "SUBTRACT":    return new Subtract();
            case "MULTIPLY":    return new Multiply();
            case "DIVIDE":      return new Divide();
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
