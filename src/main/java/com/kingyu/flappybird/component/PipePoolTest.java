package com.kingyu.flappybird.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.kingyu.flappybird.util.Constant.FRAME_HEIGHT;
import static com.kingyu.flappybird.util.Constant.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.*;
import com.kingyu.flappybird.component.Pipe;
import com.kingyu.flappybird.component.MovingPipe;
import com.kingyu.flappybird.component.PipePool;

class PipePoolTest {

    @Test
    void get() {
        Pipe get_type = PipePool.get("Pipe");
        assertEquals(get_type.getClass(), Pipe.class);

        get_type = PipePool.get("MovingPipe");
        assertEquals(get_type.getClass(), MovingPipe.class);
    }

    @Test
    void giveback() {
        PipePool p = new PipePool();

        Pipe pipe = new Pipe();
        MovingPipe movingpipe = new MovingPipe();

        List<Pipe> want_pipe = p.get_pipe();
        List<MovingPipe> want_movingpipe = p.get_movingpipe();

        p.giveBack(pipe);
        List<Pipe> return_pipe = p.get_pipe();
        List<MovingPipe> return_moving_pipe = p.get_movingpipe();

        want_pipe.add(pipe);
        assertTrue(return_pipe.size() == want_pipe.size() &&
                return_pipe.containsAll(want_pipe) &&
                want_pipe.containsAll(return_pipe) &&
                return_moving_pipe.size() == want_movingpipe.size() &&
                return_moving_pipe.containsAll(want_movingpipe) &&
                want_movingpipe.containsAll(return_moving_pipe));

        p.giveBack(movingpipe);
        want_movingpipe.add(movingpipe);
        assertTrue(return_pipe.size() == want_pipe.size() &&
                return_pipe.containsAll(want_pipe) &&
                want_pipe.containsAll(return_pipe) &&
                return_moving_pipe.size() == want_movingpipe.size() &&
                return_moving_pipe.containsAll(want_movingpipe) &&
                want_movingpipe.containsAll(return_moving_pipe));

    }
}