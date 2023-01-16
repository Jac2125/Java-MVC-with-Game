/* @Author: Jaemin Park
 * @Date: 2022/01/21
 * @Description: The Program initialize the GUI components of the game
*/

package JAVA_MVC;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//GameView sets GUI of the game
public class GameView extends JPanel {
    
    //Model instance
    private GameModel model;

    //label instances
    private JLabel winner = new JLabel("Winner: ");
    private JLabel turn = new JLabel("turn(s) left      ");
    private JLabel range =  new JLabel("Range is: ");
    private JLabel currentLoca = new JLabel("Current Location is: ");
    private JLabel boxDistance = new JLabel("Marble is far away from the box by: ");
    private JLabel player = new JLabel("Who's turn?");
    private JLabel pits = new JLabel("Pit range: ");
    private JLabel forceLabel = new JLabel("Put a value here");
    private JLabel userWins = new JLabel("User wins: ");
    private JLabel comWins = new JLabel("Computer wins: ");

    //text field instances
    private JTextField forceF = new JTextField(25);
    private JTextField winnerF = new JTextField(20);
    private JTextField turnF = new JTextField("");
    private JTextField rangeF = new JTextField("");
    private JTextField locationF = new JTextField("");
    private JTextField boxDistF = new JTextField("");
    private JTextField playerName = new JTextField("");
    private JTextField pitsRange = new JTextField("");
    private JTextField roundsField = new JTextField(10);
    private JTextField userRound = new JTextField("0",10);
    private JTextField comRound = new JTextField("0",10);

    //button instances
    private JButton calc = new JButton("Go");
    private JButton end = new JButton("End");
    private JButton rounds = new JButton("Round(s)");

    //panel instances
    private JPanel buttons = new JPanel();           
    private JPanel numbers = new JPanel();          
    private JPanel input = new JPanel();
    private JPanel info = new JPanel();

    //Constructor
    public GameView(GameModel model){

        //initialize an object
        super();
        this.model = model;
        this.model.setGUI(this);
        this.layoutView();
        this.update();
        this.registerController();

    }//end GameView()

    //initialize the panel
    private void layoutView(){

        //buttons
        buttons.setLayout(new FlowLayout());
        buttons.add(calc);
        buttons.add(end);

        //game information
        numbers.setLayout(new GridLayout(5, 2));
        numbers.add(player);
        numbers.add(playerName);
        numbers.add(range);
        numbers.add(rangeF);
        numbers.add(currentLoca);
        numbers.add(locationF);
        numbers.add(pits);
        numbers.add(pitsRange);
        numbers.add(boxDistance);
        numbers.add(boxDistF);

        //turns and winner
        info.setLayout(new FlowLayout());
        info.add(turnF);
        info.add(turn);
        info.add(winner);
        info.add(winnerF);

        //user inputs
        input.setLayout(new GridLayout(4,2));
        input.add(roundsField);
        input.add(rounds);
        input.add(forceLabel);
        input.add(forceF);
        input.add(userWins);
        input.add(userRound);
        input.add(comWins);        
        input.add(comRound);
        
        //attach panels 
        this.setLayout(new BorderLayout());
        this.add(buttons, BorderLayout.SOUTH);
        this.add(info, BorderLayout.NORTH);
        this.add(numbers, BorderLayout.EAST);
        this.add(input, BorderLayout.CENTER);

    }//end layoutView()

    //method registers the controllers
    public void registerController(){

        GameController controller = new GameController(this.model, this.forceF,this.roundsField);
        this.calc.addActionListener(controller); //calculation button
        this.end.addActionListener(controller); //end button
        this.rounds.addActionListener(controller);
    }//end registerController

    //method updates the view
    public void update(){
        
        //update game information
        this.winnerF.setText(this.model.getWinner());
        this.turnF.setText(Integer.toString(this.model.getTurn()));
        this.boxDistF.setText(Integer.toString(this.model.getDistance()));
        this.rangeF.setText(this.model.getRange());
        this.forceF.setText(Integer.toString(this.model.getForceNum()));
        this.locationF.setText(Integer.toString(this.model.getLocation()));
        this.playerName.setText(this.model.getPlayTurn());
        this.pitsRange.setText(this.model.getPits());
        this.roundsField.setText(Integer.toString(this.model.getRound()));
        this.userRound.setText(Integer.toString(this.model.getUserWins()));
        this.comRound.setText(Integer.toString(this.model.getComWins()));
    }// end update()

}//end GameView
