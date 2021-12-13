package BrickModel;

import javafx.scene.paint.Color;

public class GoldBrick extends Brick {
    private static final Color DEF_INNER = Color.rgb(255, 215, 0);
    private static final Color DEF_BORDER = Color.GRAY;
    private static final double GOLD_PROBABILITY = 0.1;

    /**
     * Constructs a gold brick.
     */
    public GoldBrick(){

        super(DEF_INNER,DEF_BORDER,GOLD_PROBABILITY);

    }
}
