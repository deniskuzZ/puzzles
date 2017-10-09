package org.treasury.puzzle.cube;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by kuzmende on 7/12/15.
 */
@RunWith(Parameterized.class)
public class LauncherTest {

    private String puzzleFile;

    @Parameterized.Parameters
    public static Collection<String[]> data() {
        return Arrays.asList(
                new String[]{"puzzle1.txt"},
                new String[]{"puzzle2.txt"},
                new String[]{"puzzle3.txt"},
                new String[]{"puzzle4.txt"});
    }

    public LauncherTest(String puzzleFile) {
        this.puzzleFile = puzzleFile;
    }

    @Test
    public void testSolvePuzzle() throws IOException, URISyntaxException {
        Launcher.main(new String[]{puzzleFile});
    }
}
