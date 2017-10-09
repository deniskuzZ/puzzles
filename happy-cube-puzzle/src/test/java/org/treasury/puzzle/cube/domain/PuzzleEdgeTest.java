package org.treasury.puzzle.cube.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kuzmende on 7/12/15.
 */
public class PuzzleEdgeTest {

    @Test
    public void testEdgeMatch() {
        PuzzleEdge edge1 = new PuzzleEdge(" o oo ".toCharArray());
        PuzzleEdge edge2 = new PuzzleEdge("o o  o".toCharArray());

        assertTrue(edge1.matches(edge2));

        PuzzleEdge edge3 = new PuzzleEdge("o o   ".toCharArray());
        assertTrue(edge1.matches(edge3));

        PuzzleEdge edge4 = new PuzzleEdge("  o  o".toCharArray());
        assertTrue(edge1.matches(edge4));
    }

    @Test
    public void testEdgeNotMatch() {
        PuzzleEdge edge1 = new PuzzleEdge(" o oo".toCharArray());
        PuzzleEdge edge2 = new PuzzleEdge("o o o".toCharArray());

        assertFalse(edge1.matches(edge2));
    }
}
