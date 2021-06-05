package com.kingyu.flappybird.component;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.*;

class GameBackgroundTest {
    private static BufferedImage BackgroundImg;
    private BufferedImage bufImg;
    private Graphics bufG;

    @BeforeEach
    void setUp() {
        BackgroundImg = GameUtil.loadBufferedImage(Constant.BG_IMG_PATH);
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        bufG = bufImg.getGraphics();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void draw() {
        GameBackground gbg = new GameBackground();
        Bird bird = new Bird();
        assertDoesNotThrow(()->{gbg.draw(bufG, bird);});
        bird.deadBirdFall();
        assertTrue(bird.isDead());
        assertDoesNotThrow(()->{gbg.draw(bufG, bird);});

        GameBackground gbg1 = new GameBackground();
        gbg1.setlayerx(600);//use extra method for test coverage

        assertDoesNotThrow(()->{gbg1.draw(bufG, bird);});
        gbg1.draw(bufG, bird);
        assertTrue(gbg1.getLayerX()>gbg1.getbwidth());
        assertEquals(0, gbg1.getLayerX());
    }
}