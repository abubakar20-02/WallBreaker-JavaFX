package BrickModel;

import javafx.scene.paint.Color;

public class SteelBrick extends Brick {

    private static final Color DEF_INNER =  Color.rgb(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final double STEEL_PROBABILITY = 0.1;

    /**
     * Constructs a steel brick.
     */
    public SteelBrick(){

        super(DEF_INNER,DEF_BORDER,STEEL_PROBABILITY);

    }
}

