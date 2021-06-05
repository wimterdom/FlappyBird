package com.kingyu.flappybird.util;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;
import org.junit.jupiter.api.Test;
import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;

class GameUtilTest {

    @Test
    void loadBufferedImage() {
        assertNull(GameUtil.loadBufferedImage(""));
        assertNotEquals(null, GameUtil.loadBufferedImage(Constant.AGAIN_IMG_PATH));
        assertNotEquals(null, GameUtil.loadBufferedImage(Constant.NOTICE_IMG_PATH));
        assertNotEquals(null, GameUtil.loadBufferedImage(Constant.BG_IMG_PATH));
        assertNotEquals(null, GameUtil.loadBufferedImage(Constant.TITLE_IMG_PATH));
        assertNotEquals(null, GameUtil.loadBufferedImage(Constant.OVER_IMG_PATH));
        assertNotEquals(null, GameUtil.loadBufferedImage(Constant.SCORE_IMG_PATH));

        for (String imgPath : Constant.CLOUDS_IMG_PATH)
            assertNotEquals(null, GameUtil.loadBufferedImage(imgPath));

        for (String imgPath : Constant.PIPE_IMG_PATH)
            assertNotEquals(null, GameUtil.loadBufferedImage(imgPath));

        for (String[] imgArray : Constant.BIRDS_IMG_PATH)
            for (String imgPath : imgArray)
                assertNotEquals(null, GameUtil.loadBufferedImage(imgPath));
    }

    @Test
    void isInProbability() throws Exception {
        assertThrows(Exception.class, () -> GameUtil.isInProbability(0, 0));
        assertThrows(Exception.class, () -> GameUtil.isInProbability(1, 0));
        assertThrows(Exception.class, () -> GameUtil.isInProbability(0, 1));
        assertDoesNotThrow(() -> GameUtil.isInProbability(1, 1));

        assertTrue(GameUtil.isInProbability(5, 3));
        assertTrue(GameUtil.isInProbability(5, 5));
        assertTrue(GameUtil.isInProbability(5, 5));

        // Can't test this because it yields a non-deterministic result...
        GameUtil.isInProbability(5, 7);
    }

    @Test
    void getRandomNumber() {
        int num1 = GameUtil.getRandomNumber(3, 5);
        int num2 = GameUtil.getRandomNumber(0, 0);
        int num3 = GameUtil.getRandomNumber(-5, -3);

        assertTrue(3 <= num1 && num1 <= 5);
        assertEquals(0, num2);
        assertTrue(-5 <= num3 && num3 <= -3);
    }

    @Test
    void getStringWidth() {
        int width1 = GameUtil.getStringWidth(Constant.CURRENT_SCORE_FONT, "fook");
        int width2 = GameUtil.getStringWidth(Constant.CURRENT_SCORE_FONT, "wulearn");
        assertEquals(74, width1);
        assertEquals(132, width2);
    }

    @Test
    void getStringHeight() {
        int height1 = GameUtil.getStringHeight(Constant.CURRENT_SCORE_FONT, "fook");
        int height2 = GameUtil.getStringHeight(Constant.CURRENT_SCORE_FONT, "omg\nhaha");
        assertEquals(37, height1);
        assertEquals(37, height2);
    }

    @Test
    void drawImage() {
        assertDoesNotThrow(() -> {
            BufferedImage bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics bufG = bufImg.getGraphics();
            GameUtil.drawImage(bufImg, 0, 0, bufG);
        });
    }
}