package org.treasury.puzzle.cube;

import org.treasury.puzzle.cube.domain.HappyCube;
import org.treasury.puzzle.cube.domain.PuzzlePart;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.treasury.puzzle.cube.domain.HappyCube.EDGE_NUM;
import static org.treasury.puzzle.cube.domain.HappyCube.PUZZLE_NUM;

/**
 * Created by kuzmende on 7/12/15.
 */
public class PuzzleSolver {

    public HappyCube buildCube(List<PuzzlePart> pieces) {
        HappyCube happyCube = new HappyCube();
        buildCube(pieces, new Stack<PuzzlePart>(), happyCube);
        return happyCube;
    }

    private void buildCube(List<PuzzlePart> pieces, Stack<PuzzlePart> puzzleParts, HappyCube happyCube) {
        for (PuzzlePart puzzlePart : pieces) {
            buildCube(pieces, puzzleParts, puzzlePart, happyCube);

            if (!happyCube.isComplete()) {
                puzzlePart.changeSide();
                buildCube(pieces, puzzleParts, puzzlePart, happyCube);
            }
        }
    }

    private void buildCube(List<PuzzlePart> pieces, Stack<PuzzlePart> puzzleParts, PuzzlePart puzzlePart, HappyCube happyCube) {
        for (int i = 0; i < EDGE_NUM; i++) {

            if (matches(puzzleParts, puzzlePart)) {
                puzzleParts.push(puzzlePart);

                if (puzzleParts.size() == PUZZLE_NUM) {
                    happyCube.setPuzzleParts(puzzleParts);
                    break;

                } else {
                    List<PuzzlePart> sublist = new ArrayList<>(pieces);
                    sublist.remove(puzzlePart);

                    buildCube(sublist, puzzleParts, happyCube);
                    if (happyCube.isComplete()) {
                        break;
                    }
                }
                puzzleParts.pop();
            }
            puzzlePart.rotateRight();
        }
    }

    private boolean matches(Stack<PuzzlePart> puzzleParts, PuzzlePart puzzlePart) {
        switch (puzzleParts.size()) {
            case 0:
                return true;

            case 1:
                return puzzleParts.get(0).getRightEdge().matches(puzzlePart.getLeftEdge());

            case 2:
                return puzzleParts.get(1).getRightEdge().matches(puzzlePart.getLeftEdge());

            case 3:
                return puzzleParts.get(0).getBottomEdge().matches(puzzlePart.getLeftEdge().reverse())
                        && puzzleParts.get(1).getBottomEdge().matches(puzzlePart.getTopEdge())
                        && puzzleParts.get(2).getBottomEdge().matches(puzzlePart.getRightEdge())

                        && matchVertices(
                            puzzleParts.get(0).isDownRightVertexSet(),
                            puzzleParts.get(1).isDownLeftVertexSet(),
                            puzzlePart.isUpperLeftVertexSet())

                        && matchVertices(
                            puzzleParts.get(1).isDownRightVertexSet(),
                            puzzleParts.get(2).isDownLeftVertexSet(),
                            puzzlePart.isUpperRightVertexSet());
            case 4:
                return puzzleParts.get(0).getLeftEdge().matches(puzzlePart.getLeftEdge().reverse())
                        && puzzleParts.get(2).getRightEdge().matches(puzzlePart.getRightEdge().reverse())
                        && puzzleParts.get(3).getBottomEdge().matches(puzzlePart.getTopEdge())

                        && matchVertices(
                            puzzleParts.get(0).isDownLeftVertexSet(),
                            puzzleParts.get(3).isDownLeftVertexSet(),
                            puzzlePart.isUpperLeftVertexSet())

                        && matchVertices(
                            puzzleParts.get(2).isDownRightVertexSet(),
                            puzzleParts.get(3).isDownRightVertexSet(),
                            puzzlePart.isUpperRightVertexSet());
            case 5:
                return puzzleParts.get(0).getTopEdge().matches(puzzlePart.getLeftEdge())
                        && puzzleParts.get(1).getTopEdge().matches(puzzlePart.getBottomEdge())
                        && puzzleParts.get(2).getTopEdge().matches(puzzlePart.getRightEdge().reverse())
                        && puzzleParts.get(4).getBottomEdge().matches(puzzlePart.getTopEdge())

                        && matchVertices(
                            puzzleParts.get(0).isUpperLeftVertexSet(),
                            puzzleParts.get(4).isDownLeftVertexSet(),
                            puzzlePart.isUpperLeftVertexSet())

                        && matchVertices(
                            puzzleParts.get(2).isUpperRightVertexSet(),
                            puzzleParts.get(4).isDownRightVertexSet(),
                            puzzlePart.isUpperRightVertexSet())

                        && matchVertices(
                            puzzleParts.get(0).isUpperRightVertexSet(),
                            puzzleParts.get(1).isUpperLeftVertexSet(),
                            puzzlePart.isDownLeftVertexSet())

                        && matchVertices(
                            puzzleParts.get(1).isUpperRightVertexSet(),
                            puzzleParts.get(2).isUpperLeftVertexSet(),
                            puzzlePart.isDownRightVertexSet());
        }
        return false;
    }

    private boolean matchVertices(boolean vertex1, boolean vertex2, boolean vertex3) {
        return (vertex1 && !(vertex2 || vertex3))
                || (vertex2 && !(vertex1 || vertex3))
                || (vertex3 && !(vertex1 || vertex2));
    }

}
