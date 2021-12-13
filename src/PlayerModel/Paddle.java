package PlayerModel;

import javafx.scene.paint.Color;

public class Paddle extends Player{

    private static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    private static final Color INNER_COLOR = Color.GREEN;
    private static final int PaddleWidth = 150;
    private static final int PaddleHeight = 10;
    private static final int DEF_MOVE_AMOUNT = 1;
    private static final int ArcWidth =15;
    private static final int ArcHeight=15;

    public Paddle(int LayoutX, int LayoutY){
        super(LayoutX,LayoutY,PaddleHeight,PaddleWidth,ArcWidth,ArcHeight,INNER_COLOR,BORDER_COLOR,DEF_MOVE_AMOUNT);
    }
}

