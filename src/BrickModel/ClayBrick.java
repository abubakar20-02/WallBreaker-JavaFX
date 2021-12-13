package BrickModel;

import javafx.scene.paint.Color;

public class ClayBrick extends Brick {
    private static final Color DEF_INNER = Color.rgb(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final double CLAY_PROBABILITY = 1;

    /**
     * Constructs a clay brick.
     */
    public ClayBrick(){

        super(DEF_INNER,DEF_BORDER,CLAY_PROBABILITY);

    }
}
