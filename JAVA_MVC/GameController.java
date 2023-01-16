/* @Author: Jaemin Park
 * @Date: 2022/01/21
 * @Description: The program gets an action command from user via frame
*/

package JAVA_MVC;

import javax.swing.*;
import java.awt.event.*;

//GameController implements ActionListener
public class GameController implements ActionListener{

    //GameModel instance
    private GameModel model;

    //text field instance
    private JTextField force;
    private JTextField rounds;

    //Constructor
    public GameController(GameModel model2, JTextField forceField, JTextField roundsField){

        this.model = model2;
        this.force = forceField;
        this.rounds = roundsField;

    }//end constructor

    //methods interacts with user
    public void actionPerformed(ActionEvent e){
    
        //initialize number outside a range (-20 to 20)
        int forceNum = 100;
        int roundsNum = 10;

        try{
            //get rounds integer
            roundsNum = Integer.parseInt(this.rounds.getText());
                if(roundsNum > 0){
                    this.model.setRound(roundsNum);   
                    this.model.action3(e.getActionCommand());
                }else{
                    //if not valid range, set text as Invalid
                    this.rounds.setText("Invalid");
                }
        }catch(NumberFormatException ex){
            
            //if not valid format, set text as Invalid
            this.rounds.setText("Invalid");
        }

        try{

            //get an integer from text field
            forceNum = Integer.parseInt(this.force.getText());

            //it should be from -20 to 20
            if(forceNum>=-20 && forceNum<=20){

                //set it as a model num if it falls into the range
                this.model.setNum(forceNum);
                //perform action using an action command
                this.model.action(e.getActionCommand());

            }else{
                //if not, set an error message
                this.force.setText("Invalid");
            }

        }catch (NumberFormatException ex){

            //if the value is not valid, show "invalid"
            this.force.setText("Invalid");
            this.model.action2(e.getActionCommand()); //still available to implement "End" button
        }

        

    }//end actionPerformed()

}//end class GameController