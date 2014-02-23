
package unit5_mathgame;

import java.util.concurrent.ConcurrentHashMap;

//<editor-fold defaultstate="collapsed" desc="Group 1 Math Game Assignment">
/**
 *
 * III Graded Group Programming Exercise Math Game 6.35+6.36+6.39 with enhancements
 * Read the assignment description on pages 238-239.
 * Before starting working on the assignment, see Math Games (6.31&6.35b) HOF examples at
 * {@link http://www.tvolk.net/vc/2001HOF/index.html}
 * Working as a team, create a project Math Game covering requirements in 6.35+6.36+6.39 and enhancements below.
 * Project enhancements:<br/>
 * 1. Modify the program to allow the user to pick the type of arithmetic problems he or she wishes to study. Option ‘a’ and ‘A’ mean (code should allow a user to enter capital or small letter) addition problems only, ‘d’ and ‘D’ means division problems only and ‘r’ and ‘R’ mean a random mixture of problems of all these types.<br/>
 * Since Java switch statement now allows using a String variable as case selector, you could use String “Addition”, or “subtraction”, etc), not the character and not the numbers 1 through 4. Code should allow a user not to think about capitalization in those strings.<br/>
 * 2. An elementary school student would be given only two chances to answer each question correctly. After he/she typed in 2 incorrect answers for the same question, program should display the correct answer.<br/>
 * 3. The project is a kind of educational game. The game requires good GUI: fun looking, in color, with good feedback. Drawing or images should be added. You could use different Layout Managers (not a FlowLayout)<br/>
 * 4. Properly document your code using javadoc.<br/>
 * 5. You can earn extra points by adding sound to the project.<br/>
 * Every student will be graded accordingly to his/her contribution to your group project, the same approach as in grading of individual projects. You should articulate /post in the Group Forum what is your contribution, your code, and your design ideas. Every group members should post their commented code fragments in Group Discussion for the team leaders’ reviews.<br/>
 * Your group leader will initiate the conversation, and divide the project. Alternatively, any group member could start the conversation by introducing himself and sharing his schedule in Group Discussion Forum. After deciding on the algorithm and user interface, you could divide the project between team members. For example:<br/>
 * <pre>
 *  GUI<br/>
 *  event handling<br/>
 *  images and sound<br/>
 *  javadoc<br/>
 * </pre>
 * Group leader will zip the project and upload the resulting file to the Group's dropbox by due date.
 * <p>
 * ---------------------------------------- TEXT BOOK ----------------------------------------------<br/>
 * 6.35 (Computer-Assisted Instruction) The use of computers in education is referred to as computer-
 * assisted instruction (CAI). Write a program that will help an elementary school student learn
 * multiplication. Use a Random object to produce two positive one-digit integers. The program should
 * then prompt the user with a question, such as
 * How much is 6 times 7?
 * The student then inputs the answer. Next, the program checks the student’s answer. If it’s correct,
 * display the message "Very good!" and ask another multiplication question. If the answer is wrong,
 * display the message "No. Please try again." and let the student try the same question repeatedly
 * until the student finally gets it right. A separate method should be used to generate each new question.
 * This method should be called once when the application begins execution and each time the
 * user answers the question correctly.
 * <p>
 * 6.36 (Computer-Assisted Instruction: Reducing Student Fatigue) One problem in CAI environments
 * is student fatigue. This can be reduced by varying the computer’s responses to hold the student’s
 * attention. Modify the program of Exercise 6.35 so that various comments are displayed for
 * each answer as follows:
 * Possible responses to a correct answer:<br/>
 
 *      &nbsp;&nbsp;Very good!<br/>
 *      &nbsp;&nbsp;Excellent!<br/>
 *      &nbsp;&nbsp;Nice work!<br/>
 *      &nbsp;&nbsp;Keep up the good work!<br/>
 * Possible responses to an incorrect answer:<br/>
 *      &nbsp;&nbsp;No. Please try again.<br/>
 *      &nbsp;&nbsp;Wrong. Try once more.<br/>
 *      &nbsp;&nbsp;Don't give up!<br/>
 *      &nbsp;&nbsp;No. Keep trying.<br/>
 * Use random-number generation to choose a number from 1 to 4 that will be used to select
 * one of the four appropriate responses to each correct or incorrect answer. Use a switch statement to
 * issue the responses.
 * --------------------------------- END TEXT BOOK ----------------------------------------------------- 
 * 
 * <p/>
 * Revision History:<br/>
 * Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change:<br/>
 * ------------------  -------------   ---------<br/>
 *
 * 
 * @author John Mambo <john.mambo@gmail.com>
 * 
 */
//</editor-fold>
public class Unit5_MathGame {

    /**
     * This is the entry point of the Application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        /**
         * DEBUG ONLY
         */    
        final boolean DEBUG = false;
    
        if (DEBUG) {
            IOperation.Operation operation= IOperation.Operation.ADD;   //indicate the desired Math operation.
            OperatorFactory factory = new OperatorFactory();            //invoke the factory class
            IOperation myOperation = factory.CreateOperator(operation); //Create Object from the factory class
            myOperation.range(IOperation.Level.MODERATE, operation);    //Set the range boundaries based on the level of difficlulty and the Math operation
            myOperation.generateValuePair();                            //Set the First and Second values to be presented to the user as a Math Problem in the Game
            myOperation.result();                                       //Calculate the expected results.
            
            /**
             * Print out the values at the consol when in Debug mode.
             */
            System.out.println("Check Answer");
            double _userAnswer = 3d; // Simulate user's input
            boolean isAnswerCorrect = myOperation.isCorrect(_userAnswer);
            String response = myOperation.response(isAnswerCorrect);

            System.out.printf("Answer is %s%n", isAnswerCorrect? "Correct": "Incorrect");
            System.out.printf("%s%n", response);
            System.out.println("--------------------"); 

            ConcurrentHashMap vp = myOperation.getConcurrentHashMap();
            System.out.printf(" Upper Limit: %s%n Lower Limit: %s%n First value: %s%n Second Value: %s%n Answer: %s%n" , vp.get(IOperation.DefineValue.UPPERLIMIT), vp.get(IOperation.DefineValue.LOWERLIMIT), vp.get(IOperation.DefineValue.FIRSTVALUE), vp.get(IOperation.DefineValue.SECONDVALUE), vp.get(IOperation.DefineValue.ANSWER));
            System.out.println("--------------------");
        } 
        else {
            
        }
    
        /**
         * Running the GUI
         */
        MathGameGUI ui = null;
        try{
            ui = new MathGameGUI();
        }
        catch(Exception ex){
            if(ui == null){
            } else {
                ui.dispose();
            }
        }
    
    }  
}
