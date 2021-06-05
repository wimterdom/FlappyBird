package com.kingyu.flappybird.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.kingyu.flappybird.component.MovingPipe;
import com.kingyu.flappybird.component.Bird;

import static com.kingyu.flappybird.component.Pipe.TYPE_TOP_HARD;
import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.*;

class MovingPipeTest {

    private BufferedImage bufImg;
    private Graphics bufG;
    private MovingPipe pipe;

    @BeforeEach
    void setUp() {
        pipe = new MovingPipe();
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        bufG = bufImg.getGraphics();
    }
    @Test
    void draw() {
        assertDoesNotThrow(() -> {
            Bird bird = new Bird();
            pipe.draw(bufG, bird);
        });
    }

    @Test
    void setAttribute() {
        assertDoesNotThrow(() -> {
            pipe.setAttribute(0, 0,0, TYPE_TOP_HARD, true);
            pipe.setAttribute(0, 0,0, TYPE_TOP_HARD, false);
        });
    }
}