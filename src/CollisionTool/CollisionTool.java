package CollisionTool;

import BallModel.RubberBall;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CollisionTool {

    Pane pane;
    Circle Ball;
    Rectangle Paddle;

    /**
     * Constructs a collision tool used to detect all  of the collisions in this game.
     * @param pane Takes the pane.
     * @param Ball Takes a ball object.
     * @param Paddle Takes a paddle object.
     */

    public CollisionTool(Pane pane, Circle Ball, Rectangle Paddle) {
        this.pane=pane;
        this.Ball = Ball;
        this.Paddle = Paddle;
    }



    /**
     * Checks if the ball makes contact with the left or right wall.
     * @return True if the ball makes contact with the left or right wall.
     */

    public boolean ballLeftRightWall() {
        return ((Ball.getCenterX()- Ball.getRadius())<=0||
                (Ball.getCenterX()+ Ball.getRadius()>=pane.getPrefWidth()));
    }

    /**
     * Checks if the ball makes contact with the bottom boundary.
     * @return True if the ball makes contact with the bottom boundary.
     */

    public boolean ballBottomWall() {
        return (Ball.getCenterY()>=(pane.getPrefHeight()- Ball.getRadius()));
    }

    /**
     * Checks if the ball makes contact with the top boundary.
     * @return True if the ball makes contact with the top boundary.
     */

    public boolean ballTopWall() { return (Ball.getCenterY()<=(Ball.getRadius())); }


    /**
     * Checks if the paddle makes contact with the left wall.
     * @return True if the paddle makes contact with the left wall.
     */

    public boolean paddleLeftWall() {
        return (Paddle.getLayoutX()<-Paddle.getWidth());
    }

    /**
     * Checks if the paddle makes contact with the right wall.
     * @return True if the paddle makes contact with the right wall.
     */

    public boolean paddleRightWall() {
        return(Paddle.getLayoutX()>pane.getPrefWidth());
    }

    /**
     * Checks if there is contact between the paddle and the ball.
     * @return True if there is contact between the paddle and the ball.
     */

    public boolean hitPaddleObject() {
        return (Ball.getBoundsInParent().intersects((Paddle.getBoundsInParent())));

    }

    /**
     * Checks if the ball hit the center of the paddle.
     * @return True if the ball hit the center of the paddle.
     */

    public boolean centerZoneCollision() {
        return (Ball.getCenterX()+ Ball.getRadius()> Paddle.getLayoutX()+(Paddle.getWidth()/3)&&
                Ball.getCenterX()+ Ball.getRadius()<(Paddle.getLayoutX()+((Paddle.getWidth()/3)*2)));
    }

    /**
     * Checks if the ball hit the left side of the paddle.
     * @return True if the ball hit the left side of the paddle.
     */
    public boolean leftZoneCollision() {
        return (Ball.getCenterX()+ Ball.getRadius()<((Paddle.getWidth()/3)+ Paddle.getLayoutX()));
    }

    /**
     * Checks if the ball hit the right side of the paddle.
     * @return True if the ball hit the right side of the paddle.
     */
    public boolean rightZoneCollision() {
        return (Ball.getCenterX()+ Ball.getRadius()>((Paddle.getWidth()/3)*2)+ Paddle.getLayoutX());
    }
    /**
     * Checks if there is collision between the brick and the wall.
     * @param brick
     * @return
     */
    public boolean hitBrickCollision(Rectangle brick) {
        return (Ball.getBoundsInParent().intersects(brick.getBoundsInParent()));
    }

    /**
     * Checks if the ball hit the center of the brick.
     * @param brick Rectangle object for brick.
     * @return True if the ball hit the center of the brick.
     */

    public boolean brickCenterCollision(Rectangle brick) {
        return (Ball.getCenterX()+ Ball.getRadius()>brick.getX()+(brick.getWidth()/3)&&
                Ball.getCenterX()+ Ball.getRadius()<(brick.getX()+((brick.getWidth()/3)*2)));
    }

    /**
     * Checks if the ball hit the left side  of the brick.
     * @param brick Rectangle object for brick.
     * @return True if the ball hit the left side  of the brick.
     */

    public boolean brickLeftZoneCollision(Rectangle brick) {
        return (Ball.getCenterX()+ Ball.getRadius()<((brick.getWidth()/3)+brick.getX()));
    }

    /**
     * Checks if the ball hit the right of the brick.
     * @param brick Rectangle object for brick.
     * @return True if the ball hit the right of the brick.
     */

    public boolean brickRightZoneCollision(Rectangle brick) {
        return (Ball.getCenterX()+ Ball.getRadius()>((brick.getWidth()/3)*2)+brick.getX());
    }

}
