/* @Author: Jaemin Park
 * @Date: 2022/01/21
 * @Description: The program returns values for operation of the game 
 * and determines the winner
*/
package JAVA_MVC;

//class GameModel starts
public class GameModel extends Object{

    //GUI instance variable
    private GameView gameGUI;

    //integer instance variables
    private int forceNum;
    private int boxDistance = 0;
    private int turn;
    private int currentLoca;
    private int pits;
    private int rounds;
    private int userWin;
    private int comWin;
    //integer array variables
    private int [] pitArr = new int[3];
    private int [] rangeArr = new int [5];

    //String instance variables
    private String playTurn;
    private String winner = "NA";

    //boolean variable for determining if game is over of not
    private boolean roundOver = false;
    private boolean gameOver = false;

    //default constructor
    public GameModel(){

        super();
        this.forceNum = 0;
        this.turn = 10;
        this.playTurn = "User";
        this.setMarble();
        this.makePits();
        this.generateRange();
        this.userWin = 0;
        this.comWin = 0;
    }//end GameModel()

    //method sets the GUI passed
    public void setGUI(GameView gameGUI){

        this.gameGUI = gameGUI;

    }// end setGUI()

    //setNum() sets the user input
    public void setNum(int forceVal){

        this.forceNum = forceVal;
    }//end setNum()

    //setRound() sets the total rounds
    public void setRound(int roundsNum){

        this.rounds = roundsNum;
    }
    //method initialize the range of pits
    public int[] makePits(){

        pits = (int)(Math.random()*(-51)+26); //get number between -25 and 25
        
        for(int i = 0; i<3; i++){

            pitArr[i] = pits-2;
            pits++;

        }
        return pitArr;
    }//end makePits()

    //setMarble() initialize marble's location
    public void setMarble(){

        do{
            this.currentLoca = (int)(Math.random()*(-201)+101);
        }while(this.currentLoca>=(-75)&&this.currentLoca<=(75)); //get a number between 100 and 75 or -100 and -75

    }// setMarble() ends

    //returns the remaining turns of game
    public int getTurn(){

        return this.turn;
    }//end getTurn()

    //return current rounds
    public int getRound(){

        return this.rounds;
    }//end getRound()

    //return userWin
    public int getUserWins(){

        return this.userWin;
    }//end getUserWins()

    //return comWin
    public int getComWins(){

        return this.comWin;
    }//end getComWins
    
    //getPits() return a range of pits
    public String getPits(){

        String pitsRange = "";
        
        for(int i = 0; i<3; i++){

            pitsRange += pitArr[i]+" ";

        }

        return pitsRange;
    }//end getPits()

    //method gets a location of marble
    public int getLocation(){

        return this.currentLoca;
    }//end getLocation()

    //method gets a distance between a marble and pits
    public int getDistance(){

            if(this.currentLoca>pitArr[2]){ //when it is located right side of pits

                this.boxDistance = this.currentLoca - pitArr[2];
            }else{
                if(this.currentLoca<pitArr[0]){ //when located left side of pits
                    this.boxDistance = pitArr[0] - this.currentLoca;
                }else{

                    this.boxDistance = 0; //when it exactly stands on the pits
                }
            }
        return this.boxDistance;
    }//end getDistance()
    
    //method gets a range of computers force
    public int[] generateRange(){

        int a = (int)(Math.random()*(-51)+26); // number from -25 to 25
        
        for(int i = 0; i<5; i++){

            rangeArr[i] = a-2;
            a++;

        }
        return rangeArr;
    }//end generateRange()

    //method returns computer's range as a string value
    public String getRange(){

        String range = rangeArr[0]+" to "
        + rangeArr[4];

        return range;

    }//end getRange()

    //method gets a random number in computer's range
    public int getNum(){

        int a =(int)(Math.random()*5);
        return rangeArr[a];
    }//end getNum()
    
    //returns user input
    public int getForceNum(){

        return this.forceNum;
    }//end getForceNum

    //method returns player's turn
    public String getPlayTurn(){

        return this.playTurn;
    }//end getPlayTurn()

    //method return winner's name
    public String getWinner(){

        return this.winner;
    }//end getWinner()

    //operate methods based on action command
    public void action(String cmd){

        switch(cmd){

            case "Go": 
            if(this.playTurn == "User" && rounds>0){

                this.movePlayer(); //when it is a player's turn

            }else{
                if(this.playTurn == "Computer" && rounds>0){

                    this.moveCom(); //when it is a computer's turn
                }
            }
            break;

            case "End":
            this.resetView(); //if user ends the game, it resets the game
            break;

            default:

        }

    }

    //method when the text field has non-integer value
    public void action2 (String cmd){

        //only "End" button is available
        if(cmd=="End"){
            this.updateView(); //if user ends the game, it resets the game
        }
    }//end action2()

    //method when setting rounds
    public void action3 (String cmd){

        //only "Round(s)" button is allowed
        if(cmd == "Round(s)"){

            this.updateView();
        }
    }//end method action3

    //when computer's turn
    private void moveCom() {

        if(!isGameOver() && !isRoundOver()){

            this.currentLoca += this.getNum(); //add computer's number
            this.playTurn = "User"; //change player's turn
            this.generateRange(); //get a new range
            this.checkRoundWinner(); //update a winner
            this.checkWinner(); //check
            this.updateView(); //updateview
        }
    }//end method moveCom()

    //when player's turn
    private void movePlayer(){
        
        if(!isGameOver() && !isRoundOver()){

            this.currentLoca+=this.forceNum; //add an user input
            this.turn--; //decrease the remaining turn
            this.playTurn = "Computer"; //change it to computer's turn
            this.updateView(); //update GUI view
        }

    }//end movePlayer()

    private boolean isGameOver() {
        return gameOver;
    }

    //method check who's winner
    private void checkRoundWinner(){

        if(this.isPassing()){ //see if it is on the pit and if user wins a game

            this.roundOver = true;
            this.rounds--;
            this.userWins();
            this.resetView2();
        }else{
            if(this.turn==0 && this.playTurn == "User"){ //if remaining turn is zero and it is user's turn, user loses

                this.roundOver = true;
                this.comWins();
                this.rounds--;
                this.resetView2();
            }
        }
    }//end checkWinner()

    //method sees if who wins a game at the end of each round
    private void checkWinner() {

        int minimum = (int)(Math.floor(rounds/2)+1);
        //when user wins
        if(this.userWin > minimum){
            
            //game is over
            this.winner = "User";
            this.gameOver = true;
        }else{
            
            //when computer wins
            if(this.comWin > minimum){
                this.winner = "Computer";
                this.gameOver = true;

            }else{

                //when tied game
                if(this.comWin ==  this.userWin && this.rounds == 0){
                    this.gameOver = true;
                    this.winner = "Draw";
                }
            }
        }
    }//end checkWinner()

    //add one to userWin
    private void userWins(){

        if(this.turn == 0){

            this.userWin++;
        }
    }//end userWins()

    //add one to comWin
    private void comWins(){

        if(this.turn == 0){

            this.comWin++;
        }
    }//end comWin

    //returns a roundOver
    private boolean isRoundOver() {

        return roundOver;
    }//end isGameOver()

    //see if it is on the pits
    private boolean isPassing(){

        //determine the distance between a marble and pits
        if(this.getDistance() == 0){

                return true;
            }else{
                return false;
            }
        }//end getDistance()

    //update GUI
    public void updateView(){

        this.gameGUI.update();
    }//end updateView

    //reset view when user wants to end the game
    public void resetView(){

        this.forceNum = 0;
        this.turn = 10;
        this.playTurn = "User";
        this.setMarble();
        this.makePits();
        this.roundOver = false;
        this.gameOver = false;
        this.winner = "NA";
        this.userWin = 0;
        this.comWin = 0;
        this.generateRange();
        this.setNum(0);
        this.updateView();

    }//end resetView()

    //reset view when round is over
    public void resetView2(){

        this.forceNum = 0;
        this.turn = 10;
        this.playTurn = "User";
        this.setMarble();
        this.makePits();
        this.roundOver = false;
        this.winner = "NA";
        this.generateRange();
        this.setNum(0);
        this.updateView();

    }//end resetView()

}//end GameModel
