package BrickModel;

import javafx.scene.paint.Color;

public abstract class Brick {
    private static Color InnerColor;
    private static Color BorderColor;
    private static double BrickProbability;

    /**
     * Construct a brick
     * @param InnerColor Color of the brick.
     * @param BorderColor Border color of the brick.
     * @param BrickBreakProbability Probability of the brick to be broken.
     */
    Brick(Color InnerColor, Color BorderColor, double BrickBreakProbability){
        this.InnerColor = InnerColor;
        this.BorderColor = BorderColor;
        this.BrickProbability = BrickBreakProbability;
    }

    /**
     * Retrieves the color of the brick.
     * @return Color of the brick.
     */
    public Color getInnerColor(){ return InnerColor; }

    /**
     * Retrieves the border color of the brick.
     * @return Border color of the brick.
     */
    public Color getBorderColor(){ return BorderColor; }

    /**
     * Retrieves the probability of the brick to be broken.
     * @return Probability of the brick to be broken.
     */
    public  double getBrickProbability(){ return BrickProbability; }

}
