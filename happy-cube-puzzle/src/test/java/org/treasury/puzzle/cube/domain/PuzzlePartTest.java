package org.treasury.puzzle.cube.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kuzmende on 7/12/15.
 */
public class PuzzlePartTest {

	@Test
	public void changeSideTest() {

		String[] frontSide = {
				"o oo ",
				" oooo",
				" ooo ",
				"oooo ",
				" oo o"};

		String[] backSide = {
				" oo o",
				"oooo ",
				" ooo ",
				" oooo",
				"o oo "};

		PuzzlePart frontPuzzleSide = new PuzzlePart(frontSide);
		PuzzlePart backPuzzleSide = new PuzzlePart(backSide);

		assertFalse(frontPuzzleSide.equals(backPuzzleSide));

		frontPuzzleSide.changeSide();
		assertTrue(frontPuzzleSide.equals(backPuzzleSide));
	}

	@Test
	public void rotateRightTest() {

		String[] puzzle = {
				"o oo ",
				" oooo",
				" ooo ",
				"oooo ",
				" oo o"};

		String[] rotatedRight = {
				" o  o",
				"oooo ",
				"ooooo",
				" oooo",
				"o  o "};

		PuzzlePart puzzlePart = new PuzzlePart(puzzle);
		PuzzlePart rotatedRightPart = new PuzzlePart(rotatedRight);

		assertFalse(puzzlePart.equals(rotatedRightPart));

		puzzlePart.rotateRight();
		assertTrue(puzzlePart.equals(rotatedRightPart));
	}

	@Test
	public void isVertexSetTest(){

		String[] puzzle = {
				"o oo ",
				" oooo",
				" ooo ",
				"oooo ",
				" oo o"};

		PuzzlePart puzzlePart = new PuzzlePart(puzzle);

		assertTrue(puzzlePart.isUpperLeftVertexSet());
		assertFalse(puzzlePart.isUpperRightVertexSet());
		assertTrue(puzzlePart.isDownRightVertexSet());
		assertFalse(puzzlePart.isDownLeftVertexSet());

		puzzlePart.changeSide();

		assertFalse(puzzlePart.isUpperLeftVertexSet());
		assertTrue(puzzlePart.isUpperRightVertexSet());
		assertFalse(puzzlePart.isDownRightVertexSet());
		assertTrue(puzzlePart.isDownLeftVertexSet());
	}

	@Test
	public void getEdgeTest(){

		String[] puzzle = {
				"o oo ",
				" oooo",
				" ooo ",
				"oooo ",
				" oo o"};

		PuzzlePart puzzlePart = new PuzzlePart(puzzle);

		assertTrue(puzzlePart.getTopEdge().equals(
				new PuzzleEdge("o oo ".toCharArray())));

		assertTrue(puzzlePart.getBottomEdge().equals(
				new PuzzleEdge(" oo o".toCharArray())));

		assertTrue(puzzlePart.getRightEdge().equals(
				new PuzzleEdge(" o  o".toCharArray())));

		assertTrue(puzzlePart.getLeftEdge().equals(
				new PuzzleEdge("o  o ".toCharArray())));
	}

}
