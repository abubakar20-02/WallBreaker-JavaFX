package PlayerModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestPaddleTest {
    Paddle TestPaddle;
    @BeforeEach
    void setUp() {
        TestPaddle= new Paddle(225,425);
    }

    @Test
    void moveLeft() {
        TestPaddle.moveLeft();
        assertEquals(-1,TestPaddle.getPlayerVelocity());
    }

    @Test
    void moveRight() {
        TestPaddle.moveRight();
        assertEquals(1,TestPaddle.getPlayerVelocity());
    }

    @Test
    void getHeight() {
        assertEquals(10,TestPaddle.getHeight());
    }

    @Test
    void reducePaddleSize() {
        TestPaddle.ReducePaddleSize();
        assertEquals(135,TestPaddle.getWidth());
    }

    @Test
    void increasePaddleSize() {
        TestPaddle.IncreasePaddleSize();
        assertEquals(165,TestPaddle.getWidth());
    }

    @Test
    void resetPaddleSize() {
        TestPaddle.IncreasePaddleSize();
        TestPaddle.ResetPaddleSize();
        assertEquals(150,TestPaddle.getWidth());
    }

}