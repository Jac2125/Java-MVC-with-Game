/* @Author: Jaemin Park
 * @Date: 2022/01/21
 * @Description: From this class, 
 * program will display the frame containing the contents
 * 
 * Game Rule: Player will enter an integer between -20 and 20. 
 * By typing in the numbers, player needs to send a marble into a pit.
 * The current location shows where the marble is and pits range represents where the player should aim
 * Program will add number the player typed in and get a random number to add from a computer.
 * A random number is specified through a range, and the computer will randomly pick up one number and add to your location.
 * Total turn is 10, player is required to finish the game within the rounds.
*/

package JAVA_MVC;

import javax.swing.JFrame;

//Class GameStartUp implements a main method
public class GameStartUp {

    //main method generates a frame containing contents
    public static void main(String[] args) {
        
    //initialize the View and Model instantce
    GameModel model = new GameModel();            //The Game Model
    GameView main = new GameView(model);  //The View view
    
    //Initialize the Frame
    JFrame f = new JFrame("J's Fun Game");
    f.setSize(700,200);
    f.setLocation(300,200);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setContentPane(main);
    f.setVisible(true);

    }//end main method
}//end class GameStartUp