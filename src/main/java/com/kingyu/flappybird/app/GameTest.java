package com.kingyu.flappybird.app;

import static org.junit.jupiter.api.Assertions.*;

import com.kingyu.flappybird.component.Bird;
import com.kingyu.flappybird.component.GameElementLayer;
import com.kingyu.flappybird.component.WelcomeAnimation;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.kingyu.flappybird.util.Constant.*;

class GameTest {
    private static int gameState; // 游戏状态
    public static final int GAME_READY = 0; // 游戏未开始
    public static final int GAME_START = 1; // 游戏开始
    public static final int STATE_OVER = 2;
    private BufferedImage bufImg;

    Game g = new Game();

    @BeforeEach
    void setUp() {
        bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void update() {
        WelcomeAnimation wa = new WelcomeAnimation();
        GameElementLayer ge = new GameElementLayer();
        g.setGameState(GAME_READY);
        Graphics bufG = bufImg.getGraphics();
        assertDoesNotThrow(()->{g.update(bufG);});
        assertDoesNotThrow(()->{wa.draw(bufG);});
        g.setGameState(GAME_START);
        assertDoesNotThrow(()->{Bird bird= new Bird();ge.draw(bufG,bird);});
    }

    @Test
    void setGameState() {
        g.setGameState(g.GAME_READY);
        assertEquals(0, g.getGameState()); //use extra method to check state value
        g.setGameState(g.GAME_START);
        assertEquals(1, g.getGameState()); //use extra method to check state value
    }
}