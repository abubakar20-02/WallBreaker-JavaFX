/**
 * Provides set of classes to make the ball.
 */
package BallModel;

import javafx.scene.paint.Color;

public abstract class ball {

    private Color border;
    private Color inner;
    private int radius;
    private int centerX;
    private int centerY;
    private double speedX;
    private double speedY;

    /**
     * Creates a new instance of ball with the given attributes.
     * @param centerX X coordinate of the center of the ball.
     * @param centerY Y coordinate of the center of the ball.
     * @param radius Radius of the ball.
     * @param inner Color of the ball.
     * @param border Border color of the ball.
     */
    public ball(int centerX, int centerY, int radius, Color inner, Color border){
        this.radius=radius;
        this.centerX=centerX;
        this.centerY=centerY;
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * Setting the horizontal speed of the ball.
     * @param Speed speed of the ball.
     */
    public void setXSpeed(int Speed){
        speedX = Speed;
    }

    /**
     * Setting the vertical speed of the ball.
     * @param Speed speed of the ball.
     */
    public void setYSpeed(int Speed){
        speedY = Speed;
    }

    /**
     * Changes the horizontal speed of the ball.
     * @param SpeedMultiplier Determines the magnitude of change in speed.
     */
    public void ChangeXSpeed(double SpeedMultiplier){
        speedX *= SpeedMultiplier;
    }
    /**
     * Changes the vertical speed of the ball.
     * @param SpeedMultiplier Determines the magnitude of change in speed.
     */
    public void ChangeYSpeed(double SpeedMultiplier){
        speedY *= SpeedMultiplier;
    }

    /**
     * Reverses the horizontal direction the ball was going towards.
     */
    public void reverseX(){
        speedX *= -1;
    }
    /**
     * Reverses the vertical direction the ball was going towards.
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Retrieves the border color of the ball.
     * @return Border color of the ball.
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Retrieves the color of the ball.
     * @return Color of the ball.
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Retrieves the horizontal speed of the ball;
     * @return Horizontal speed of the ball.
     */
    public double getSpeedX(){
        return speedX;
    }

    /**
     * Retrieves the vertical speed of the ball.
     * @return Vertical speed of the ball.
     */
    public double getSpeedY(){
        return speedY;
    }

    /**
     * Retrieves the X coordinate of the center of the ball.
     * @return X coordinate of the center of the ball.
     */
    public int getCenterX(){return centerX;}
    /**
     * Retrieves the Y coordinate of the center of the ball.
     * @return Y coordinate of the center of the ball.
     */
    public int getCenterY(){return centerY;}

    /**
     * Retrieves the radius of the ball.
     * @return Radius of the ball.
     */
    public int getRadius(){return radius;}

}

