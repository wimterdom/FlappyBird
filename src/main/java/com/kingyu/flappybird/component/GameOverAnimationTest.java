package com.kingyu.flappybird.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameOverAnimationTest {

    private BufferedImage bufImg;

    @BeforeEach
    void setUp() {
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    }


    @Test
    void draw() {
        final int CYCLE = 30; // 闪烁周期

        assertDoesNotThrow(() -> {
            Bird bird = new Bird();
            GameOverAnimation animation = new GameOverAnimation();

            for (int i = 0; i < CYCLE * 2; i++) {
                animation.draw(bufImg.getGraphics(), bird);
            }
        });
    }

}