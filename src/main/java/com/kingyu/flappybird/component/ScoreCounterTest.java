package com.kingyu.flappybird.component;

import com.kingyu.flappybird.util.Constant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCounterTest {

    @BeforeEach
    void setUp() {
        // Write 0 to `resources/score`
        try {
            File file = new File(Constant.SCORE_FILE_PATH);
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            dos.writeLong(0);
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ScoreCounter.getInstance().setBestScore(0);
    }


    @Test
    void saveScore() {
        Bird bird = new Bird();
        ScoreCounter sc = ScoreCounter.getInstance();
        final int expectedScore = 3;

        sc.reset();
        assertEquals(0, sc.getBestScore());

        for (int i = 0; i < expectedScore; i++) {
            sc.score(bird);
        }
        sc.saveScore();
        assertEquals(expectedScore, sc.getBestScore());
    }

    @Test
    void score() {
        Bird bird = new Bird();
        ScoreCounter sc = ScoreCounter.getInstance();

        // Bird is alive, should increment score
        long score = sc.getCurrentScore();
        sc.score(bird);
        assertEquals(score + 1, sc.getCurrentScore());

        // Bird is dead, shouldn't increment score
        score = sc.getCurrentScore();
        bird.deadBirdFall();
        sc.score(bird);
        assertEquals(score, sc.getCurrentScore());
    }

    @Test
    void getBestScore() {
        Bird bird = new Bird();
        ScoreCounter sc = ScoreCounter.getInstance();

        sc.reset();
        sc.saveScore();
        assertEquals(0, sc.getBestScore());

        sc.reset();
        sc.score(bird);
        sc.score(bird);
        sc.score(bird);
        sc.saveScore();
        assertEquals(3, sc.getBestScore());

        sc.reset();
        sc.score(bird);
        sc.score(bird);
        sc.saveScore();
        assertEquals(3, sc.getBestScore());

        sc.reset();
        sc.score(bird);
        sc.score(bird);
        sc.score(bird);
        sc.score(bird);
        sc.saveScore();
        assertEquals(4, sc.getBestScore());
    }

    @Test
    void getCurrentScore() {
        Bird bird = new Bird();
        ScoreCounter sc = ScoreCounter.getInstance();
        sc.reset();
        sc.score(bird);
        sc.score(bird);
        sc.score(bird);
        assertEquals(3, sc.getCurrentScore());
    }

    @Test
    void reset() {
        ScoreCounter sc = ScoreCounter.getInstance();
        sc.reset();
        assertEquals(0, sc.getCurrentScore());
    }
}