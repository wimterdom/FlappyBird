package com.kingyu.flappybird.component;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.*;

class CloudTest {

    private List<Cloud> clouds;
    private BufferedImage[] cloudImages;
    private BufferedImage bufImg;

    @BeforeEach
    void setUp() {
        clouds = new ArrayList<>();
        cloudImages = new BufferedImage[Constant.CLOUD_IMAGE_COUNT];
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

        // Load image resources
        for (int i = 0; i < Constant.CLOUD_IMAGE_COUNT; i++) {
            cloudImages[i] = GameUtil.loadBufferedImage(Constant.CLOUDS_IMG_PATH[i]);
        }
    }


    @Test
    void draw() {
        Bird bird = new Bird();

        // Bird is alive
        assertDoesNotThrow(() -> new Cloud(cloudImages[0], 0, 0).draw(bufImg.getGraphics(), bird));

        // Bird is dead
        bird.deadBirdFall();
        assertTrue(bird.isDead());
        assertDoesNotThrow(() -> new Cloud(cloudImages[0], 0, 0).draw(bufImg.getGraphics(), bird));
    }

    @Test
    void isOutFrame() {
        Bird bird = new Bird();
        Cloud cloud = new Cloud(cloudImages[0], 0, 0);

        // At this point, it should still be within the frame.
        assertFalse(cloud.isOutFrame());

        // Let this cloud shift left...
        for (int i = 0; i < 50; i++) {
            cloud.draw(bufImg.getGraphics(), bird);
        }

        // At this point, it should have gone out of the frame.
        assertTrue(cloud.isOutFrame());
    }

}