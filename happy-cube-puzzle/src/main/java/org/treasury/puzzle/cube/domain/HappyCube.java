package org.treasury.puzzle.cube.domain;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by kuzmende on 7/12/15.
 */
public class HappyCube {

	public static final int MAX_CELLS = 5;

	public static final int EDGE_NUM = 4;
	public static final int PUZZLE_NUM = 6;

	public static final char EMPTY_CELL_ID = ' ';

	private static final String OFFSET = CharBuffer.allocate(MAX_CELLS).toString().replace('\0', EMPTY_CELL_ID);

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private List<PuzzlePart> puzzleParts = new ArrayList<>();

	public String toUnfoldedFormatText() {
		StringBuilder result = new StringBuilder();

		if (isComplete()) {
			String[] puzzle0 = puzzleParts.get(0).toTextArray();
			String[] puzzle1 = puzzleParts.get(1).toTextArray();
			String[] puzzle2 = puzzleParts.get(2).toTextArray();

			for (int i = 0; i < MAX_CELLS; i++) {
				result.append(puzzle0[i]); result.append(puzzle1[i]); result.append(puzzle2[i]);
				result.append(LINE_SEPARATOR);
			}

			for (int puzzleId = 3; puzzleId < 6; puzzleId++) {
				String[] puzzle = puzzleParts.get(puzzleId).toTextArray();

				for (int i = 0; i < MAX_CELLS; i++) {
					result.append(OFFSET); result.append(puzzle[i]); result.append(OFFSET);
					result.append(LINE_SEPARATOR);
				}
			}
		}

		return result.toString();
	}

	public boolean isComplete() {
		return puzzleParts.size() == PUZZLE_NUM;
	}

	public void setPuzzleParts(final Stack<PuzzlePart> puzzleParts) {
		this.puzzleParts = new ArrayList<>(puzzleParts);
	}
}
