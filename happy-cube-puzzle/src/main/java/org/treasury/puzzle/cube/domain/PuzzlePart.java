package org.treasury.puzzle.cube.domain;

import java.util.Arrays;

import static org.treasury.puzzle.cube.domain.HappyCube.EMPTY_CELL_ID;
import static org.treasury.puzzle.cube.domain.HappyCube.MAX_CELLS;

/**
 * Created by kuzmende on 7/12/15.
 */
public class PuzzlePart {

    private char[][] puzzlePart;

    public PuzzlePart(final String[] rows) {
        puzzlePart = new char[MAX_CELLS][];

        for (int i = 0; i < MAX_CELLS; i++) {
            puzzlePart[i] = rows[i].toCharArray();
        }
    }

    public PuzzleEdge getTopEdge() {
        return new PuzzleEdge(puzzlePart[0]);
    }

    public PuzzleEdge getBottomEdge() {
        return new PuzzleEdge(puzzlePart[MAX_CELLS - 1]);
    }

    public PuzzleEdge getLeftEdge() {
        char[] leftEdge = new char[MAX_CELLS];
        for (int i = 0; i < MAX_CELLS; i++) {
            leftEdge[i] = puzzlePart[i][0];
        }
        return new PuzzleEdge(leftEdge);
    }

    public PuzzleEdge getRightEdge() {
        char[] rightEdge = new char[MAX_CELLS];
        for (int i = 0; i < MAX_CELLS; i++) {
            rightEdge[i] = puzzlePart[i][MAX_CELLS - 1];
        }
        return new PuzzleEdge(rightEdge);
    }

    public void changeSide() {
        for (int i = 0; i < MAX_CELLS / 2; i++) {
            char buffer = puzzlePart[0][i];
            // reverse topEdge
            puzzlePart[0][i] = puzzlePart[0][MAX_CELLS - 1 - i];
            puzzlePart[0][MAX_CELLS - 1 - i] = buffer;

            buffer = puzzlePart[MAX_CELLS - 1][i];
            // reverse bottomEdge
            puzzlePart[MAX_CELLS - 1][i] = puzzlePart[MAX_CELLS - 1][MAX_CELLS - 1 - i];
            puzzlePart[MAX_CELLS - 1][MAX_CELLS - 1 - i] = buffer;
        }

        for (int i = 1; i < MAX_CELLS - 1; i++) {
            char buffer = puzzlePart[i][0];
            // swap rightEdge with leftEdge
            puzzlePart[i][0] = puzzlePart[i][MAX_CELLS - 1];
            puzzlePart[i][MAX_CELLS - 1] = buffer;
        }
    }

    public void rotateRight() {
        for (int i = 0; i < MAX_CELLS - 1; i++) {
            char buffer = puzzlePart[0][i];

            puzzlePart[0][i] = puzzlePart[MAX_CELLS - 1 - i][0];
            puzzlePart[MAX_CELLS - 1 - i][0] = puzzlePart[MAX_CELLS - 1][MAX_CELLS - 1 - i];
            puzzlePart[MAX_CELLS - 1][MAX_CELLS - 1 - i] = puzzlePart[i][MAX_CELLS - 1];
            puzzlePart[i][MAX_CELLS - 1] = buffer;
        }
    }

    public boolean isUpperLeftVertexSet() {
        return puzzlePart[0][0] != EMPTY_CELL_ID;
    }

    public boolean isUpperRightVertexSet() {
        return puzzlePart[0][MAX_CELLS - 1] != EMPTY_CELL_ID;
    }

    public boolean isDownLeftVertexSet() {
        return puzzlePart[MAX_CELLS - 1][0] != EMPTY_CELL_ID;
    }

    public boolean isDownRightVertexSet() {
        return puzzlePart[MAX_CELLS - 1][MAX_CELLS - 1] != EMPTY_CELL_ID;
    }

    public String[] toTextArray() {
        String[] result = new String[MAX_CELLS];
        for (int i = 0; i < MAX_CELLS; i++) {
            result[i] = new String(puzzlePart[i]);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PuzzlePart)) {
            return false;
        }
        PuzzlePart that = (PuzzlePart) o;
        return Arrays.deepEquals(puzzlePart, that.puzzlePart);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(puzzlePart);
    }

}
