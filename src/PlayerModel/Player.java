package PlayerModel;

import javafx.scene.paint.Color;

abstract class Player {

    private Color border;
    private Color inner;
    private int Height;
    private int Width;
    private int LayoutX;
    private int LayoutY;
    private int DEF_MOVE_AMOUNT;
    private double PlayerVelocity;
    private int ArcWidth;
    private int ArcHeight;
    private int ChangedWidth;

    public Player(int LayoutX, int LayoutY, int Height, int Width,int ArcWidth, int ArcHeight, Color inner, Color border,int DEF_MOVE_AMOUNT) {

        this.border = border;
        this.inner = inner;
        this.Height = Height;
        this.Width = Width;
        this.LayoutX = LayoutX;
        this.LayoutY = LayoutY;
        this.DEF_MOVE_AMOUNT = DEF_MOVE_AMOUNT;
        this.ArcWidth = ArcWidth;
        this.ArcHeight = ArcHeight;
        ChangedWidth=Width;
    }

    /**
     * Moves the paddle to the left.
     */

    public void moveLeft(){
        PlayerVelocity-= DEF_MOVE_AMOUNT;
    }

    /**
     * Moves the paddle to the right.
     */

    public void moveRight(){
        PlayerVelocity+=DEF_MOVE_AMOUNT;
    }

    /**
     * Retrieves the border color of the paddle.
     * @return Border color of the paddle.
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Retrieves the color of the paddle.
     * @return Color of the paddle.
     */

    public Color getInnerColor(){ return inner; }

    /**
     * Retrieves the height of the paddle.
     * @return Height of the paddle.
     */

    public int getHeight(){ return Height; }

    /**
     * Reduces the size of the paddle.
     */

    public void ReducePaddleSize(){ChangedWidth *= 0.9;}

    /**
     * Increases the size of the paddle.
     */

    public void IncreasePaddleSize(){ChangedWidth *=1.1;}

    /**
     * Resets the size of the paddle.
     */

    public void ResetPaddleSize(){ ChangedWidth=Width; }

    /**
     * Retrieves the width of the paddle.
     * @return Width of the paddle.
     */

    public int getWidth(){return ChangedWidth ;}

    /**
     * Retrieves the left most X coordinate of the paddle.
     * @return
     */

    public  int getLayoutX() { return LayoutX;}

    /**
     * Retrieves the upper most Y coordinate of the paddle.
     * @return
     */

    public int getLayoutY() { return LayoutY;}

    /**
     * Retrieves the arc width of the paddle.
     * @return
     */

    public int getArcWidth() { return ArcWidth;}

    /**
     * Retrieves the arc height of the paddle.
     * @return
     */

    public int getArcHeight() { return ArcHeight;}

    /**
     * Retrieves the velocity of the ball.
     * @return Velocity of the ball.
     */

    public double getPlayerVelocity(){ return PlayerVelocity; }

    /**
     * Applies frictional effect to the paddle.
     */

    public void setFrictionEffect(){this.PlayerVelocity = 0.95*PlayerVelocity; }

}
