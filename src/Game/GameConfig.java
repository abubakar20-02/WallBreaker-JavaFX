package Game;

import BallModel.RubberBall;
import CollisionTool.CollisionTool;
import PlayerModel.Paddle;
import WallModel.Wall;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import java.util.Scanner;

import java.io.*;
import java.security.SecureRandom;

public class GameConfig {
    @FXML
    private Rectangle paddle;

    @FXML
    private Pane pane;

    @FXML
    protected Scene scene;


    @FXML
    private Circle ball;

    @FXML
    private Label score;

    @FXML
    private Label lives;

    @FXML
    private Button Continue;

    @FXML
    protected Button Restart;

    @FXML
    protected Button Exit;

    @FXML
    protected Button SetChanges;

    @FXML
    protected Button ResetLives;

    @FXML
    protected Slider BallSpeed;

    @FXML
    protected Button NextLevel;

    @FXML
    protected Button PreviousLevel;

    @FXML
    private Label BallSpeedText;

    @FXML
    private Label livesText;

    @FXML
    private Label scoreText;

    @FXML
    private Label CongratsMessage;

    @FXML
    private Button Start;

    @FXML
    private Label welcomeText;

    @FXML
    private Button Info;

    @FXML
    private Label highScore;

    @FXML
    private Label highScoreText;

    @FXML
    private Button back;

    @FXML
    private Label InfoTitle1;

    @FXML
    private Label InfoTitle2;

    @FXML
    private Label instruct1;

    @FXML
    private Label instruct2;

    @FXML
    private Label instruct3;

    @FXML
    private Label instruct4;

    @FXML
    private Label instruct5;

    @FXML
    private Label HighScoreText;

    @FXML
    private Label NameText;

    @FXML
    protected TextField Name;

    @FXML
    private Button SubmitName;

    @FXML
    private Label HighestScoreText;

    @FXML
    private Label HighestScore;

    @FXML
    private Label HighestScoreName;

    protected final int MaxLives=3;
    AnimationTimer timer;
    protected String HighScoreName;
    protected boolean GameOver;
    public boolean isPaused=true;
    protected boolean isDebugPanelActive;
    //reset is true when there are no more bricks on the field and only when the tracker is false
    //(to prevent the reset button from appearing when the paddle is controlled by the computer
    protected boolean reset = false;
    protected boolean isInPauseMenu=false;
    protected boolean isInStartMenu=true;
    protected boolean resetWall=false;
    protected boolean removeWall=false;
    protected boolean showInfo =false;
    boolean levelComplete=false;
    protected int livesKeeper = MaxLives;
    protected static int scoreKeeper=0;
    protected static int HighScore=0;
    protected boolean isNewHighScore=false;
    double elapsedTime;
    RubberBall Ball = new RubberBall(300,400);
    Paddle Paddle = new Paddle(225,425);
    double scale;
    int level=1;
    int totalBrokenBricks;
    boolean ShowWall;
    boolean EndOfGameMenu;
    protected Wall bricks;

    protected int rowsInBrickField=3;
    SecureRandom random = new SecureRandom();
    public void initialize() {
        elapsedTime=0;
        EndOfGameMenu=false;
        ShowWall=true;
        Paddle.ResetPaddleSize();
        livesKeeper = MaxLives;
        EndOfGameMenu(false);
        totalBrokenBricks = 0;
        levelComplete = false;
        isDebugPanelActive=false;
        isPaused=true;
        BallInitialization();
        PaddleInitialization();
        bricks = new Wall(pane, rowsInBrickField,level);
        BrickInitialization(bricks);
        ShowPauseMenu(false);
        ShowDebugMenu(false);
        ShowStartMenu(isInStartMenu);
        if(showInfo) {
            ShowInfo(true);
            showInfo =!showInfo;
        }
        final int randomSpeedMultiplier = random.nextInt(5);
        Ball.setXSpeed(2 + random.nextInt(4));
        Ball.setYSpeed(2 + random.nextInt(4));
        timer = new AnimationTimer() {
            double velocity = BallSpeed.getValue();
            long previousTime = System.nanoTime();
            final CollisionTool collisions = new CollisionTool(pane, ball, paddle);

            @Override
            public void handle(long now) {
                CheckScore();
                CheckName();
                highScore.setText(Integer.toString(HighScore));
                ShowWall(ShowWall);
                elapsedTime = (now - previousTime) / 1000000000.0;
                previousTime = now;
                if(isPaused){
                    velocity=0;
                }
                else{
                    velocity=BallSpeed.getValue();
                }
                scale = elapsedTime * velocity;
                ball.setCenterX(ball.getCenterX() + Ball.getSpeedX() * scale);
                ball.setCenterY(ball.getCenterY() + Ball.getSpeedY() * scale);
                paddle.setLayoutX((paddle.getLayoutX()+Paddle.getPlayerVelocity()));
                Paddle.setFrictionEffect();
                if(!GameOver) {
                    score.setText(Integer.toString(scoreKeeper));
                    lives.setText(Integer.toString(livesKeeper));
                    HighestScore.setText(Integer.toString(HighScore));
                    HighestScoreName.setText(HighScoreName);
                }
                if(livesKeeper<=0) {
                    ShowWall(false);
                    GameEndScreen();
                }
                if (collisions.ballLeftRightWall()) {
                    Ball.reverseX();
                }
                if (collisions.ballBottomWall()) {
                    RestPaddleBall();
                    isPaused=true;
                    if(!GameOver) {
                        livesKeeper-=1;
                    }
                }



                if(collisions.ballTopWall()||collisions.ballBottomWall()) {
                    Ball.reverseY();
                }
                if(collisions.hitPaddleObject()) {
                    if(collisions.centerZoneCollision()) {
                        Ball.ChangeYSpeed(-1.1);
                        Ball.ChangeXSpeed(1.05);
                        if(paddle.getWidth()<pane.getWidth()/4) {
                            Paddle.IncreasePaddleSize();
                            paddle.setWidth(Paddle.getWidth());
                        }
                    }
                    else if(collisions.leftZoneCollision()) {
                        Ball.ChangeXSpeed(1.01);
                        Ball.reverseY();
                    }
                    else if(collisions.rightZoneCollision()) {
                        Ball.ChangeXSpeed(1.01);
                        Ball.reverseY();
                    }
                    ball.setCenterY((paddle.getLayoutY()-ball.getRadius()));
                }
                if(collisions.paddleLeftWall()) {
                    paddle.setLayoutX(pane.getPrefWidth());
                }
                if(collisions.paddleRightWall()) {
                    paddle.setLayoutX(-paddle.getWidth());
                }
                if(ball.getCenterX()-ball.getRadius()<=0) {
                    ball.setCenterX(ball.getRadius());
                }
                else if(ball.getCenterX()+ball.getRadius()>=pane.getPrefWidth()) {
                    ball.setCenterX(ball.getCenterX()-ball.getRadius());
                }
                for(int i = 0; i<bricks.brickFieldArray.length;i++) {
                    if(bricks.brickFieldArrayBroken[i]||isInStartMenu){
                        bricks.brickFieldArray[i].setVisible(false);
                    }
                }
                for(int i = 0; i<bricks.brickFieldArray.length;i++) {
                    if (!bricks.brickFieldArrayBroken[i]) {
                        if (collisions.hitBrickCollision(bricks.brickFieldArray[i])) {
                            if (collisions.brickCenterCollision(bricks.brickFieldArray[i])) {
                                Ball.ChangeXSpeed(0.9);
                                Ball.ChangeYSpeed(1.05);
                                IncreasePaddleSize();
                            }
                            if (collisions.brickLeftZoneCollision(bricks.brickFieldArray[i])) {
                                Ball.ChangeXSpeed(1.01);
                                Ball.ChangeYSpeed(0.95);
                            }
                            if (collisions.brickRightZoneCollision(bricks.brickFieldArray[i])) {
                                Ball.ChangeXSpeed(1.01);
                                Ball.ChangeYSpeed(0.95);
                            }
                            Ball.reverseY();
                            if(random.nextInt(100)<=bricks.brickFieldArrayProbability[i]*100){
                                bricks.brickFieldArrayBroken[i] = true;
                                totalBrokenBricks++;
                            }
                            scoreKeeper += 1;
                            ReducePaddleSize();
                            int randomSpeedBoost = random.nextInt(10);
                            if (randomSpeedBoost == randomSpeedMultiplier) {
                                Ball.ChangeXSpeed(1.03);
                                Ball.ChangeYSpeed(-1.03);
                            }
                        }
                        if (resetWall) {
                            ResetWall();
                            resetWall = false;
                        }
                        if (removeWall) {
                            RemoveWall();
                            removeWall = !removeWall;
                        }
                    }
                    if(totalBrokenBricks==bricks.brickFieldArray.length){
                        levelComplete=true;
                    }
                }
                if(levelComplete){
                    /*if(level>=0&&level<4){
                        level++;
                        ResetPaddleSize();
                        initialize();
                    }
                     */
                    GameEndScreen();
                }
                /*if(level==1 && levelComplete){
                    GameEndScreen();
                }

                 */
            }

            /**
             * Determines the visibility of the wall.
             * @param Visible Visibility of the wall.
             */

            private void ShowWall(boolean Visible){
                for (int i = 0; i < bricks.brickFieldArray.length; i++) {
                    bricks.brickFieldArray[i].setVisible(Visible);
                }
            }

            /**
             * Resets the wall by fixing the broken bricks.
             */

            private void ResetWall() {
                for (int i = 0; i < bricks.brickFieldArray.length; i++) {
                    bricks.brickFieldArray[i].setVisible(true);
                    bricks.brickFieldArrayBroken[i] = false;
                }
            }

            /**
             * Breaks every single brick in the game at that level.
             */

            private void RemoveWall() {
                for (int i = 0; i < bricks.brickFieldArray.length; i++) {
                    pane.getChildren().remove(bricks.brickFieldArray[i]);
                    bricks.brickFieldArrayBroken[i]=true;
                }
            }
        };
        if(reset) {
            ShowPauseMenu(false);
            timer.stop();
        }
        timer.start();
    }

    /**
     * Takes user input if a high score was set, otherwise it shows the end of game menu.
     */

    private void GameEndScreen() {
        if (isNewHighScore) {
            EndOfGameMenu(false);
            NewHighScoreScreen(true);
        } else {
            EndOfGameMenu(true);
            NewHighScoreScreen(false);
        }
    }

    /**
     * Shows information about the game such as how to play and the objective of the game.
     * @param visible Determines the visibility of the information screen.
     */

    protected void ShowInfo(boolean visible){
        welcomeText.setVisible(!visible);
        Start.setVisible(!visible);
        Restart.setVisible(!visible);
        Exit.setVisible(!visible);
        Info.setVisible(!visible);
        back.setVisible(visible);
        InfoTitle1.setVisible(visible);
        InfoTitle2.setVisible(visible);
        instruct1.setVisible(visible);
        instruct2.setVisible(visible);
        instruct3.setVisible(visible);
        instruct4.setVisible(visible);
        instruct5.setVisible(visible);
    }

    /**
     * Shows the menu when the game ends in the event of no lives remaining or completing the game.
     * @param visible Determines the visibility of the end game screen screen.
     */

    protected void EndOfGameMenu(boolean visible) {
        isPaused=true;
        paddle.setVisible(!visible);
        ball.setVisible(!visible);
        lives.setVisible(!visible);
        score.setVisible(!visible);
        livesText.setVisible(!visible);
        scoreText.setVisible(!visible);
        lives.setVisible(!visible);
        score.setVisible(!visible);
        CongratsMessage.setVisible(visible);
        Restart.setVisible(visible);
        Exit.setVisible(visible);
        back.setVisible(false);
        highScore.setVisible(false);
        highScoreText.setVisible(false);
        HighestScoreText.setVisible(visible);
        HighestScore.setVisible(visible);
        HighestScoreName.setVisible(visible);
    }

    /**
     * Resets the position of both the paddle and the ball, as well as resets paddle size.
     */

    private void RestPaddleBall() {
        ball.setCenterX(300);
        ball.setCenterY(400);
        paddle.setLayoutY(425);
        paddle.setLayoutX(225);
        ResetPaddleSize();
    }

    /**
     * Reset paddle Size.
     */

    private void ResetPaddleSize(){
        Paddle.ResetPaddleSize();
        paddle.setWidth(Paddle.getWidth());
    }

    /**
     * Increase paddle size.
     */

    private void IncreasePaddleSize() {
        if(Paddle.getWidth()<=pane.getWidth()/4) {
            Paddle.IncreasePaddleSize();
            paddle.setWidth(Paddle.getWidth());
        }
    }

    /**
     * Reduce paddle size.
     */

    private void ReducePaddleSize() {
        if(Paddle.getWidth()>=pane.getWidth()/8) {
            Paddle.ReducePaddleSize();
            paddle.setWidth(Paddle.getWidth());
        }
    }

    /**
     * Show pause menu given that visibility is true.
     * @param visible Determines the visibility of the pause menu.
     */

    protected void ShowPauseMenu(boolean visible) {
        Continue.setVisible(visible);
        Restart.setVisible(visible);
        Exit.setVisible(visible);
    }

    /**
     * Show start menu given that visibility is true.
     * @param visible Determines the visibility of the start menu.
     */

    protected void ShowStartMenu(boolean visible) {
        paddle.setVisible(!visible);
        ball.setVisible(!visible);
        lives.setVisible(!visible);
        score.setVisible(!visible);
        livesText.setVisible(!visible);
        scoreText.setVisible(!visible);
        lives.setVisible(!visible);
        score.setVisible(!visible);
        CongratsMessage.setVisible(false);
        welcomeText.setVisible(visible);
        Start.setVisible(visible);
        Restart.setVisible(false);
        Exit.setVisible(visible);
        Info.setVisible(visible);
        highScore.setVisible(!visible);
        highScoreText.setVisible(!visible);
        back.setVisible(false);
        InfoTitle1.setVisible(false);
        InfoTitle2.setVisible(false);
        instruct1.setVisible(false);
        instruct2.setVisible(false);
        instruct3.setVisible(false);
        instruct4.setVisible(false);
        instruct5.setVisible(false);
        HighScoreText.setVisible(false);
        NameText.setVisible(false);
        Name.setVisible(false);
        SubmitName.setVisible(false);
        HighestScoreText.setVisible(false);
        HighestScore.setVisible(false);
        HighestScoreName.setVisible(false);
    }

    /**
     * Show debug menu given that visibility is true.
     * @param visible Visibility.
     */

    protected void ShowDebugMenu(boolean visible) {
        BallSpeed.setVisible(visible);
        NextLevel.setVisible(visible);
        PreviousLevel.setVisible(visible);
        ResetLives.setVisible(visible);
        SetChanges.setVisible(visible);
        BallSpeedText.setVisible(visible);
    }

    /**
     * Show high score input screen.
     * @param visible Determines visibility of the high score screen.
     */

    private void NewHighScoreScreen(boolean visible){
        HighScoreText.setVisible(visible);
        Name.setVisible(visible);
        NameText.setVisible(visible);
        paddle.setVisible(false);
        ball.setVisible(false);
        paddle.setVisible(false);
        livesText.setVisible(false);
        lives.setVisible(false);
        score.setVisible(false);
        scoreText.setVisible(false);
        SubmitName.setVisible(visible);
    }




    /**
     * Initializing ball in fxml.
     */

    private void BallInitialization() {
        ball.setCenterX(Ball.getCenterX());
        ball.setCenterY(Ball.getCenterY());
        ball.setRadius(Ball.getRadius());
        ball.setFill(Ball.getInnerColor());
        ball.setStroke(Ball.getBorderColor());
        ball.setStrokeType(StrokeType.INSIDE);
    }

    /**
     * Initializing paddle in fxml.
     */

    private void PaddleInitialization(){
        paddle.setLayoutX(Paddle.getLayoutX());
        paddle.setLayoutY(Paddle.getLayoutY());
        paddle.setHeight(Paddle.getHeight());
        paddle.setWidth(Paddle.getWidth());
        paddle.setStroke(Paddle.getBorderColor());
        paddle.setFill(Paddle.getInnerColor());
        paddle.setStrokeType(StrokeType.INSIDE);
        paddle.setArcWidth(Paddle.getArcWidth());
        paddle.setArcHeight(Paddle.getArcHeight());
    }

    /**
     * Constructing a brick wall in the game.
     * @param bricks Bricks in the wall.
     */

    private void BrickInitialization(Wall bricks){
        bricks.MakeWall();
        for(int i = 0; i<bricks.brickFieldArray.length;i++) {
            pane.getChildren().add(bricks.brickFieldArray[i]);
        }
    }

    /**
     * Reads the score from the file and if theres a new high score, updates high score.
     */

    private void CheckScore() {
        try {
            File HighScoreFile = new File("HighScore.txt");
            File HighScoreNameFile = new File("HighScoreName.txt");
            Scanner myReader = new Scanner(HighScoreFile);
            Scanner myReader1 = new Scanner(HighScoreNameFile);
            while (myReader.hasNextLine()) {
                String score = myReader.nextLine();
                String name = myReader1.nextLine();
                HighScoreName=name;
                HighScore=Integer.parseInt(score);
                if(HighScore<scoreKeeper){
                    isNewHighScore=true;
                    HighScore=scoreKeeper;
                    updateScore();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the score to the file if there is a new high score.
     */

    private void updateScore() throws IOException {
        BufferedWriter out = null;

        try {
            FileWriter FileWriter = new FileWriter("HighScore.txt", false); //true tells to append data.
            out = new BufferedWriter(FileWriter);
            out.write(String.valueOf(HighScore));
        }

        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            if(out != null) {
                out.close();
            }
        }
    }

    /**
     * Reads the name from the file and if theres a new high score, updates name.
     */

    private void CheckName() {
        try {
            File HighScoreFile = new File("HighScoreName.txt");
            Scanner myReader = new Scanner(HighScoreFile);
            while (myReader.hasNextLine()) {
                HighScoreName= myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * updates the name to the file if there is a new high score.
     */
    protected void updateName() throws IOException {
        BufferedWriter out = null;

        try {
            FileWriter FileWriter = new FileWriter("HighScoreName.txt", false); //true tells to append data.
            out = new BufferedWriter(FileWriter);
            out.write(HighScoreName);
        }

        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            if(out != null) {
                out.close();
            }
        }
    }
}