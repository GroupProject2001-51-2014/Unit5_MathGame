
package unit5_mathgame;

/**
 *  
 * <p/> 
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 *

 * @author Alhassan Ahmed
 * @param <T>
 * @param <U> 
 * 
 */
public class Subtract<T extends Double, U extends String> extends Operator<T, U> {

    private int num1;
    private int num2;
    private int result;

    public Subtract(){
        num1 = 0;
        num2 = 0;
        result = 0;
    }
    
//create a constructors
    public Subtract( int number1, int number2, int resul){
            number1 = num1;
            number2 = num2;
            resul = result;
     }
    
             //Mutator method   
    public void setFirstNumber(int number1) 
     { 
         this.num1 = number1 ; 
     }
    
    public void setsecondNumber(int number2) 
    { 
      this.num2 = number2; 
    }
    
      
    // Accessor methods 

    public int getfirstNumber() 
    { 
    return num1; 
    } 

    public int getsecondNumber() 
    {  return num2; 
    } 
    public int getSubtraction(){
    result = num1 - num2;

    return result;
    }
 
        @Override
    public void result() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCorrect(T input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public U response(boolean isCorrect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playResponse(boolean isCorrect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
