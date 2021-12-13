package BallModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {

    private RubberBall TestBall;

    @BeforeEach
    void setUp() {
        TestBall=new RubberBall(300,400);
    }

    @Test
    void getXSpeed() {
        TestBall.setXSpeed(10);
        int expResult=10;
        assertEquals(expResult,TestBall.getSpeedX());
    }

    @Test
    void getYSpeed() {
        TestBall.setYSpeed(5);
        int expResult=5;
        assertEquals(expResult,TestBall.getSpeedY());
    }

    @Test
    void changeXSpeed() {
        TestBall.setXSpeed(10);
        TestBall.ChangeXSpeed(1.1);
        int expResult=11;
        assertEquals(expResult,TestBall.getSpeedX());
    }

    @Test
    void increaseYSpeed() {
        TestBall.setYSpeed(10);
        TestBall.ChangeYSpeed(1.1);
        int expResult=11;
        assertEquals(expResult,TestBall.getSpeedY());
    }

    @Test
    void reverseX() {
        TestBall.setXSpeed(10);
        TestBall.reverseX();
        int expResult=-10;
        assertEquals(expResult,TestBall.getSpeedX());
    }

    @Test
    void reverseY() {
        TestBall.setYSpeed(10);
        TestBall.reverseY();
        int expResult=-10;
        assertEquals(expResult,TestBall.getSpeedY());
    }
}
