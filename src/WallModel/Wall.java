package WallModel;

import BrickModel.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Wall {
    private int brickRowCount;
    private final int brickColumnCount = 10;
    private double brickWidth;
    private double brickHeight;
    private int brickFieldArraySize;
    public boolean[] brickFieldArrayBroken;
    public Rectangle[] brickFieldArray;
    public double[] brickFieldArrayProbability;
    Rectangle brick;
    Pane pane;
    int level;

    public Wall(Pane pane, int rows, int level) {
        this.pane=pane;
        this.brickWidth = ((pane.getPrefWidth() / brickColumnCount));
        this.brickHeight = (this.brickWidth / 3);
        this.brickRowCount = rows;
        this.brickFieldArraySize = (brickRowCount * brickColumnCount);
        brickFieldArray = new Rectangle[brickFieldArraySize];
        brickFieldArrayBroken= new boolean[brickFieldArraySize];
        brickFieldArrayProbability = new double[brickFieldArraySize];
        this.level=level;
    }

    /**
     * Generates a wall for a certain level.
     */
    public void MakeWall() {
        Random random = new Random();
        int i = 0;
        for (int c = 0; c < brickColumnCount; c++) {
            for (int r = 0; r < brickRowCount; r++) {
                int SelectingBrick =random.nextInt(2);
                brick = new Rectangle();

                brick.setWidth(brickWidth);
                brick.setHeight(brickHeight);
                double brickX = (c * (brickWidth))+1;

                double brickY = (r * (brickHeight))+1;
                brick.setX(brickX);
                brick.setY(brickY);
                Levels(SelectingBrick,i);
                this.brickFieldArray[i] = brick;
                i++;
            }
        }

    }

    /**
     * Makes different bricks according to the level.
     * @param SelectingBrick Random number between 0 and 1 to select brick type.
     * @param i Index of the brick.
     */

    private void Levels(int SelectingBrick,int i) {
        switch(level){
            case 1:
                Level1(i);
                break;
            case 2:
                Level2(SelectingBrick,i);
                break;
            case 3:
                Level3(SelectingBrick,i);
                break;
            case 4:
                Level4(SelectingBrick,i);
                break;
            case 5:
                Level5(i);
        }
    }

    /**
     * Makes a cement brick.
     * @param i Index of the brick.
     */

    private void CementBrick(int i){
        CementBrick cementBrick= new CementBrick();
        brick.setFill(cementBrick.getInnerColor());
        brick.setStroke(cementBrick.getBorderColor());
        brickFieldArrayProbability[i]= cementBrick.getBrickProbability();
    }

    /**
     * Makes a gold brick.
     * @param i Index of the brick.
     */

    private void GoldBrick(int i){
        GoldBrick goldBrick= new GoldBrick();
        brick.setFill(goldBrick.getInnerColor());
        brick.setStroke(goldBrick.getBorderColor());
        brickFieldArrayProbability[i]= goldBrick.getBrickProbability();
    }

    /**
     * Makes a clay brick.
     * @param i Index of the brick.
     */

    private void ClayBrick(int i){
        ClayBrick clayBrick=new ClayBrick();
        brick.setFill(clayBrick.getInnerColor());
        brick.setStroke(clayBrick.getBorderColor());
        brickFieldArrayProbability[i]= clayBrick.getBrickProbability();
    }

    /**
     * Makes a steel brick.
     * @param i Index of the brick.
     */

    private void SteelBrick(int i){
        SteelBrick steelBrick= new SteelBrick();
        brick.setFill(steelBrick.getInnerColor());
        brick.setStroke(steelBrick.getBorderColor());
        brickFieldArrayProbability[i]= steelBrick.getBrickProbability();
    }

    /**
     * Generate level 1;
     * @param i Index of the brick.
     */
    private void Level1(int i){
        ClayBrick(i);
    }

    /**
     * Generate level 2;
     * @param SelectingBrick Random number between 0 and 1 to select brick type.
     * @param i Index of the brick.
     */

    private void Level2(int SelectingBrick, int i){
        switch(SelectingBrick){
            case 0:
                ClayBrick(i);
                break;
            case 1:
                CementBrick(i);
                break;
        }
    }

    /**
     * Generate level 3;
     * @param SelectingBrick Random number between 0 and 1 to select brick type.
     * @param i Index of the brick.
     */

    private void Level3(int SelectingBrick, int i){
        switch(SelectingBrick) {
            case 0:
                ClayBrick(i);
                break;
            case 1:
                SteelBrick(i);
                break;
        }
    }

    /**
     * Generate level 4;
     * @param SelectingBrick Random number between 0 and 1 to select brick type.
     * @param i Index of the brick.
     */

    private void Level4(int SelectingBrick, int i){
        switch(SelectingBrick) {
            case 0:
                SteelBrick(i);
                break;
            case 1:
                CementBrick(i);
                break;
        }
    }

    /**
     * Generate level 5;
     * @param i Index of the brick.
     */
    private void Level5(int i){
        GoldBrick(i);
    }
}
