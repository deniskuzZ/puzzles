package org.treasury.puzzle.cube;

import org.apache.log4j.Logger;
import org.treasury.puzzle.cube.domain.HappyCube;
import org.treasury.puzzle.cube.domain.PuzzlePart;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.treasury.puzzle.cube.domain.HappyCube.MAX_CELLS;
import static org.treasury.puzzle.cube.domain.HappyCube.PUZZLE_NUM;

/**
 * Created by kuzmende on 7/12/15.
 */
public class Launcher {

    private static final Logger LOG = Logger.getLogger(Launcher.class);

    public static final String MATCH_NOT_FOUND = "Match not found";
    public static final int ROWS_NUM = 2;

    public static void main(String[] args) throws IOException, URISyntaxException {
        if (args.length != 1) return;

        List<PuzzlePart> puzzleParts = getPuzzlePartsFromFile(args[0]);

        PuzzleSolver puzzleSolver = new PuzzleSolver();
        HappyCube happyCube = puzzleSolver.buildCube(puzzleParts);

        if (happyCube != null) {
            LOG.info(happyCube.toUnfoldedFormatText());

        } else {
            LOG.error(MATCH_NOT_FOUND);
        }
    }

    public static List<PuzzlePart> getPuzzlePartsFromFile(String file) throws URISyntaxException, IOException {
        URL puzzleUrl = Launcher.class.getClassLoader().getResource(file);
        Path puzzleFile = Paths.get(puzzleUrl.toURI());

        List<PuzzlePart> puzzleParts = new ArrayList<>();
        List<String> puzzle = Files.readAllLines(puzzleFile, Charset.defaultCharset());

        for (int j = 0; j < ROWS_NUM; j++) {
            for (int k = 0; k < PUZZLE_NUM / ROWS_NUM; k++) {
                String[] puzzlePart = new String[MAX_CELLS];

                for (int i = j * MAX_CELLS; i < (j + 1) * MAX_CELLS; i++) {
                    char[] row = new char[MAX_CELLS];

                    for (int l = k * MAX_CELLS; l < (k + 1) * MAX_CELLS; l++) {
                        row[l - k * MAX_CELLS] = puzzle.get(i).charAt(l);
                    }
                    puzzlePart[i - j * MAX_CELLS] = new String(row);
                }
                puzzleParts.add(new PuzzlePart(puzzlePart));
            }
        }

        return puzzleParts;
    }

}
