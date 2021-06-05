package com.kingyu.flappybird.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.kingyu.flappybird.component.GameForeground;
import com.kingyu.flappybird.component.Bird;
import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class GameForegroundTest {

    private GameForeground foreground;
    private BufferedImage bufImg;
    private Graphics bufG;
    @BeforeEach
    void setUp() {
        foreground = new GameForeground();
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        bufG = bufImg.getGraphics();
    }
    @Test
    void draw() {
        assertDoesNotThrow(() -> {
            Bird bird = new Bird();
            foreground.draw(bufG, bird);
        });

    }
}