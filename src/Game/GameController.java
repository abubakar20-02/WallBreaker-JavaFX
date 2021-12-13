package Game;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class GameController extends GameConfig  {
    //SCO: *JAVAFX controller classes are not permitted to have
    //parameters in the constructor when fx:controller is declared in the fxml file.
    /*
     *SFM: rectKey controls the movement of the paddle
     *rectangleVelocity is added each frame of the Animation, so don't hold it down!
     *Use quick taps, the friction will slow it down
     */

    @FXML
    private void KeyEvents(KeyEvent event)  {
        if(event.getCode()==KeyCode.D) {
            if(isPaused!=true) {
                Paddle.moveRight();
            }
        }
        if(event.getCode()==KeyCode.A) {
            if(isPaused!=true) {
                Paddle.moveLeft();
            }
        }
        if(event.getCode()==KeyCode.SPACE){
            isPaused=!isPaused;
        }
        if(event.getCode()==KeyCode.ESCAPE){
            isPaused=true;
            if(isDebugPanelActive==false){
                isInPauseMenu=!isInPauseMenu;
                if(isInPauseMenu==true) {
                    ShowPauseMenu(true);
                }
                else{
                    ShowPauseMenu(false);
                }
            }
        }
        if(event.getCode()==KeyCode.ALT){
            if(isInPauseMenu==false&&isPaused==true){
                isDebugPanelActive=!isDebugPanelActive;
                if(isDebugPanelActive==true){
                    ShowDebugMenu(true);
                }
                else{
                    ShowDebugMenu(false);
                }
            }
        }
    }

    /**
     * When the user clicks the continue button, the pause menu is closed and the ball is paused.
     */

    @FXML
    private void Continue() {
        isInPauseMenu = false;
        ShowPauseMenu(false);
    }

    /**
     * Starts the game.
     */

    @FXML
    private void Start() {
        isInStartMenu=false;
        removeWall=true;
        initialize();
    }

    /**
     * Closes the game.
     */

    @FXML
    private void Exit(){
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    /**
     * Restarts the game.
     */

    @FXML
    public void Restart() {
        removeWall=true;
        scoreKeeper = 0;
        livesKeeper=MaxLives;
        EndOfGameMenu=false;
        //ShowWall=true;
        level=1;
        initialize();
        Continue();
    }

    /**
     * When the user clicks submit button. It adds the users name into the high score file.
     */

    @FXML
    private void Submit() throws IOException {
        HighScoreName=Name.getText();
        isNewHighScore=false;
        updateName();
    }

    /**
     * Closes the debug menu.
     */

    @FXML
    private void SetChanges(){
        ShowDebugMenu(false);
        isDebugPanelActive=!isDebugPanelActive;
    }

    /**
     * When the info button is pressed. it shows how to play the game.
     */

    @FXML
    private void Info(){
        showInfo =true;
        removeWall=true;
        initialize();
    }

    /**
     * When the go back button is pressed. it goes back to the starting menu of the game.
     */

    @FXML
    private void GoBackInfo(){
        isInStartMenu=true;
        removeWall=true;
        initialize();
    }

    /**
     * Resets the lives to max capacity.
     */

    @FXML
    private void ResetLives(){
        livesKeeper = MaxLives;
    }

    /**
     * Starts the next level of the game.
     */

    @FXML
    private void NextLevel(){
        if(level>=0&&level<5){
            removeWall=true;
            level++;
            scoreKeeper=0;
            initialize();
        }
    }

    /**
     * Starts previous level.
     */

    @FXML
    private void PreviousLevel(){
        if(level>1&&level<6){
            removeWall=true;
            level--;
            scoreKeeper=0;
            initialize();
        }
    }
}