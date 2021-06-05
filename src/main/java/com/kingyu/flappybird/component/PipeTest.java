package com.kingyu.flappybird.component;

import com.kingyu.flappybird.util.Constant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.kingyu.flappybird.util.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

class PipeTest {

    private BufferedImage bufImg;

    @BeforeEach
    void setUp() {
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    }


    @Test
    void setAttribute() {
        final int x = 5;
        final int y = 10;
        final int height = 30;
        final int type = Pipe.TYPE_TOP_NORMAL;

        Pipe pipe = new Pipe();
        pipe.setAttribute(x, y, height, Pipe.TYPE_TOP_NORMAL, true);

        assertEquals(x, pipe.x);
        assertEquals(y, pipe.y);
        assertEquals(height, pipe.height);
        assertEquals(type, pipe.type);
        assertEquals(true, pipe.visible);
    }

    @Test
    void setRectangle() {
        final int x = 5;
        final int y = 10;
        final int height = 30;

        Pipe pipe = new Pipe();
        pipe.setRectangle(x, y, height);
        Rectangle rect = pipe.getPipeRect();

        assertEquals(x, rect.x);
        assertEquals(y, rect.y);
        assertEquals(height, rect.height);
    }

    @Test
    void isVisible() {
        Bird bird = new Bird();
        Pipe pipe = new Pipe();
        int x = 10;

        // At this point, it should still be within the frame.
        pipe.setAttribute(x, 50, 50, Pipe.TYPE_TOP_NORMAL, true);
        assertTrue(pipe.isInFrame());

        // Let this pipe shift left...
        for (int i = 0; i < 100; i++) {
            pipe.draw(bufImg.getGraphics(), bird);
        }

        // At this point, it should have gone out of the frame.
        assertFalse(pipe.isVisible());
    }

    @Test
    void draw() {
        Bird bird = new Bird();
        Pipe pipe = new Pipe();

        for (int i = Pipe.TYPE_TOP_NORMAL; i < 6; i++) {
            pipe.setAttribute(30, 30, 50, i, true);
            assertDoesNotThrow(() -> pipe.draw(bufImg.getGraphics(), bird));
        }

        // Additional test case: the bird is dead
        bird.deadBirdFall();
        assertDoesNotThrow(() -> pipe.draw(bufImg.getGraphics(), bird));
    }

    @Test
    void isInFrame() {
        Bird bird = new Bird();
        Pipe pipe = new Pipe();
        int x = FRAME_WIDTH;

        // At this point, it should still be within the frame.
        pipe.setAttribute(x, 50, 50, Pipe.TYPE_TOP_NORMAL, true);
        assertFalse(pipe.isInFrame());

        // Let this pipe shift left...
        for (int i = 0; i < 20; i++) {
            pipe.draw(bufImg.getGraphics(), bird);
        }

        // At this point, it should have gone out of the frame.
        assertTrue(pipe.isInFrame());
    }

    @Test
    void getX() {
        Bird bird = new Bird();
        Pipe pipe = new Pipe();
        int x = 50;

        pipe.setAttribute(x, 50, 50, Pipe.TYPE_TOP_NORMAL, true);
        assertEquals(x, pipe.getX());

        pipe.draw(bufImg.getGraphics(), bird);
        assertEquals(x - Constant.GAME_SPEED, pipe.getX());
    }

    @Test
    void getPipeRect() {
        final int x = 5;
        final int y = 10;
        final int height = 30;

        Pipe pipe = new Pipe();
        pipe.setAttribute(x, y, height, Pipe.TYPE_TOP_NORMAL, true);

        Rectangle rect = pipe.getPipeRect();
        assertEquals(x, rect.x);
        assertEquals(y, rect.y);
        assertEquals(height, rect.height);
    }

}