package CollisionTool;

import BallModel.RubberBall;
import PlayerModel.Paddle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollisionToolTest {
    private RubberBall TestBall;
    private Circle ball;
    private Rectangle paddle;
    private Paddle TestPaddle;
    private CollisionTool TestCollisions;
    private Pane TestPane;
    private boolean Ans;
    @BeforeEach
    void setUp() {
        TestBall=new RubberBall(300,400);
        TestPaddle = new Paddle(225,425);
        ball= new Circle();
        paddle= new Rectangle();
        TestPane=new Pane();
        TestPane.setPrefHeight(450);
        TestPane.setPrefWidth(600);

        ball.setCenterX(TestBall.getCenterX());
        ball.setCenterY(TestBall.getCenterY());
        ball.setRadius(TestBall.getRadius());
        ball.setFill(TestBall.getInnerColor());
        ball.setStroke(TestBall.getBorderColor());
        ball.setStrokeType(StrokeType.INSIDE);

        paddle.setLayoutX(TestPaddle.getLayoutX());
        paddle.setLayoutY(TestPaddle.getLayoutY());
        paddle.setHeight(TestPaddle.getHeight());
        paddle.setWidth(TestPaddle.getWidth());
        paddle.setStroke(TestPaddle.getBorderColor());
        paddle.setFill(TestPaddle.getInnerColor());
        paddle.setStrokeType(StrokeType.INSIDE);
        paddle.setArcWidth(TestPaddle.getArcWidth());
        paddle.setArcHeight(TestPaddle.getArcHeight());

        TestCollisions = new CollisionTool(TestPane, ball, paddle);
    }

    @Test
    void ballLeftRightWall() {
        Ans=TestCollisions.ballLeftRightWall();
        assertEquals(false,Ans);
    }

    @Test
    void ballBottomWall() {
        Ans=TestCollisions.ballBottomWall();
        assertEquals(false,Ans);
    }

    @Test
    void ballTopWall() {
        Ans=TestCollisions.ballTopWall();
        assertEquals(false,Ans);
    }

    @Test
    void paddleLeftWall() {
        Ans= TestCollisions.paddleLeftWall();
        assertEquals(false,Ans);
    }

    @Test
    void paddleRightWall() {
        Ans= TestCollisions.paddleRightWall();
        assertEquals(false,Ans);
    }

    @Test
    void hitPaddleObject() {
        Ans=TestCollisions.hitPaddleObject();
        assertEquals(false,Ans);
    }

    @Test
    void centerZoneCollision() {
        Ans=TestCollisions.centerZoneCollision();
        assertEquals(true,Ans);
    }

    @Test
    void leftZoneCollision() {
        Ans=TestCollisions.leftZoneCollision();
        assertEquals(false,Ans);
    }

    @Test
    void rightZoneCollision() {
        Ans=TestCollisions.rightZoneCollision();
        assertEquals(false,Ans);
    }
}