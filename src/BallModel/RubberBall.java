package BallModel;

import javafx.scene.paint.Color;
public class RubberBall extends ball {

    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR =  Color.rgb(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = Color.BLACK;

    /**
     * Constructs a rubber ball at the given coordinates.
     * @param centerX X coordinate of the center of the rubber ball.
     * @param centerY Y coordinate of the center of the rubber ball.
     */
    public RubberBall(int centerX, int centerY){
        super(centerX,centerY,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }


}


