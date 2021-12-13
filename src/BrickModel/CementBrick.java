package BrickModel;

import javafx.scene.paint.Color;

public class CementBrick extends Brick {

    private static final Color DEF_INNER = Color.rgb(147, 147, 147);
    private static final Color DEF_BORDER =Color.rgb(217, 199, 175);
    private static final double CEMENT_PROBABILITY = 0.5;

    /**
     * Constructs a cement brick.
     */
    public CementBrick(){

        super(DEF_INNER,DEF_BORDER,CEMENT_PROBABILITY);

    }
}
