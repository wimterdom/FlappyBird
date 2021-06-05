package com.kingyu.flappybird.component;

import com.kingyu.flappybird.util.Constant;
import com.kingyu.flappybird.util.GameUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.kingyu.flappybird.component.Bird.IMG_COUNT;
import static com.kingyu.flappybird.component.Bird.STATE_COUNT;
import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.*;

class BirdTest {
    private static BufferedImage BackgroundImg;
    private BufferedImage bufImg;
    private Graphics bufG;
    private BufferedImage image;
    private BufferedImage[][] birdImages; // 小鸟的图片数组对象
    private int velocity = 0;
    public static final int ACC_FLAP = 14;
    public int x;

    private int state;
    public static final int BIRD_NORMAL = 0;
    public static final int BIRD_UP = 1;
    public static final int BIRD_FALL = 2;
    public static final int BIRD_DEAD_FALL = 3;
    public static final int BIRD_DEAD = 4;

    BirdTest() {
    }

    @BeforeEach
    void setUp() {
        BackgroundImg = GameUtil.loadBufferedImage(Constant.BG_IMG_PATH);
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        bufG = bufImg.getGraphics();
        birdImages = new BufferedImage[STATE_COUNT][IMG_COUNT];
        for (int j = 0; j < STATE_COUNT; j++) {
            for (int i = 0; i < IMG_COUNT; i++) {
                birdImages[j][i] = GameUtil.loadBufferedImage(Constant.BIRDS_IMG_PATH[j][i]);
            }
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void draw() {
        Bird bird = new Bird();
        assertDoesNotThrow(()->{bird.draw(bufG);});
        bird.birdFall();
        assertDoesNotThrow(()->{bird.draw(bufG);});

        Bird bird1 = new Bird();
        bird1.deadBirdFall();
        assertTrue(bird1.isDead());
        assertDoesNotThrow(()->{bird1.draw(bufG);});

        Bird bird2 = new Bird();//use extra method for test coverage
        bird2.birdFall();
        bird2.setbirdcollision(600);
        assertDoesNotThrow(()->{bird2.draw(bufG);});

        Bird bird3 = new Bird(); // use extra method for test coverage
        bird3.setVelocity(5);
        assertDoesNotThrow(()->{bird3.draw(bufG);});
    }

    @Test
    void birdFlap() {
        Bird bird = new Bird();
        bird.keyReleased();
        bird.keyIsReleased();
        assertDoesNotThrow(()->{bird.birdFlap();});
        Bird bird1 = new Bird();
        bird1.deadBirdFall();
        assertTrue(bird1.isDead());
        assertDoesNotThrow(()->{bird1.birdFlap();});
    }

    @Test
    void birdFall() {
        Bird bird = new Bird();
        Bird bird1 = new Bird();
        bird.deadBirdFall();
        assertDoesNotThrow(()->{bird.birdFall();});

        bird1.birdFall();
        assertEquals(2, bird1.getState());//use extra method to check state value
    }

    @Test
    void deadBirdFall() {
        Bird bird = new Bird();
        bird.deadBirdFall();
        assertEquals(3, bird.getState());
        assertEquals(0,bird.getVelocity());
    }

    @Test
    void isDead() {
        Bird bird = new Bird();
        bird.deadBirdFall();
        assertTrue(bird.isDead());
        assertEquals(3,bird.getState());
    }

    @Test
    void reset() {
        Bird bird = new Bird();
        bird.deadBirdFall();
        assertTrue(bird.isDead());
        bird.reset();
        assertFalse(bird.isDead());

    }

    @Test
    void keyPressed() {
        Bird bird = new Bird();
        bird.keyPressed();
        assertFalse(bird.getkeyflag());
    }

    @Test
    void keyReleased() {
        Bird bird = new Bird();
        assertDoesNotThrow(()->{bird.keyReleased();});

        bird.keyReleased();
        assertTrue(bird.getkeyflag());//use extra method to check keyflag value
    }

    @Test
    void keyIsReleased() {
        Bird bird = new Bird();
        bird.keyPressed();
        assertFalse(bird.keyIsReleased());
    }

    @Test
    void getCurrentScore() {
        Bird bird = new Bird();
        assertEquals(0,bird.getCurrentScore());
    }

    @Test
    void getBestScore() {
        Bird bird = new Bird();
        bird.reset();
        assertEquals(0, bird.getBestScore());
    }

    @Test
    void getBirdX() {
        Bird bird = new Bird();
        x = FRAME_WIDTH >> 2;
        assertEquals(x,bird.getBirdX());
    }

    @Test
    void getBirdCollisionRect() {
        Bird bird = new Bird();
        assertDoesNotThrow(()->{bird.getBirdCollisionRect();});
    }
}