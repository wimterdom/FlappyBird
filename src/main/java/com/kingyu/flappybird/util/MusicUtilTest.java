package com.kingyu.flappybird.util;

import com.kingyu.flappybird.util.MusicUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicUtilTest {

    @Test
    void playFly() {
        assertDoesNotThrow(() -> MusicUtil.playFly());
    }

    @Test
    void playCrash() {
        assertDoesNotThrow(() -> MusicUtil.playCrash());
    }

    @Test
    void playScore() {
        assertDoesNotThrow(() -> MusicUtil.playScore());
    }
}