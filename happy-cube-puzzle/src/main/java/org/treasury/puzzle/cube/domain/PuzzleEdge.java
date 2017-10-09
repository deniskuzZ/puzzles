package org.treasury.puzzle.cube.domain;

import java.util.Arrays;

import static org.treasury.puzzle.cube.domain.HappyCube.EMPTY_CELL_ID;
import static org.treasury.puzzle.cube.domain.HappyCube.MAX_CELLS;

/**
 * Created by kuzmende on 7/12/15.
 */
public class PuzzleEdge {

    private char[] puzzleEdge;

    public PuzzleEdge(char[] puzzleEdge) {
        this.puzzleEdge = puzzleEdge;
    }

    public boolean matches(PuzzleEdge edge) {
       return matchEdgesWoVertices(edge) && matchVertices(edge);
    }
    
    public PuzzleEdge reverse() {
        char[] reverseEdge = new char[MAX_CELLS];
        for (int i = 0; i < MAX_CELLS; i++) {
            reverseEdge[MAX_CELLS - 1 - i] = puzzleEdge[i];
        }
        return new PuzzleEdge(reverseEdge);
    }

    private boolean matchEdgesWoVertices(PuzzleEdge edge){
        for (int i = 1; i < MAX_CELLS - 1; i++) {
            if (puzzleEdge[i] == edge.puzzleEdge[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean matchVertices(PuzzleEdge edge){
        return (puzzleEdge[0] == EMPTY_CELL_ID || edge.puzzleEdge[0] == EMPTY_CELL_ID)
                && (puzzleEdge[MAX_CELLS - 1] == EMPTY_CELL_ID || edge.puzzleEdge[MAX_CELLS - 1] == EMPTY_CELL_ID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PuzzleEdge)) {
            return false;
        }
        PuzzleEdge that = (PuzzleEdge) o;
        return Arrays.equals(puzzleEdge, that.puzzleEdge);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(puzzleEdge);
    }
}
