package org.treasury.puzzle.cube;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.treasury.puzzle.cube.domain.HappyCube;
import org.treasury.puzzle.cube.domain.PuzzlePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.treasury.puzzle.cube.domain.HappyCube.LINE_SEPARATOR;

/**
 * Created by kuzmende on 7/12/15.
 */
public class PuzzleSolverTest {

	private static final Logger LOG = Logger.getLogger(PuzzleSolverTest.class);

	@Test
	public void test() throws URISyntaxException, IOException {
		URL solutionUrl = getClass().getClassLoader().getResource("test_result.txt");
		Path solutionFile = Paths.get(solutionUrl.toURI());

		BufferedReader reader = Files.newBufferedReader(solutionFile, Charset.defaultCharset());
		StringBuilder expected_result = new StringBuilder();

		String line;
		while ((line = reader.readLine()) != null) {
			expected_result.append(line);
		}

		List<PuzzlePart> puzzleParts = Launcher.getPuzzlePartsFromFile("test_puzzle.txt");
		PuzzleSolver puzzleSolver = new PuzzleSolver();
		HappyCube result = puzzleSolver.buildCube(puzzleParts);
		
		assertNotNull(result);

		assertEquals(
				expected_result.toString(),
				result.toUnfoldedFormatText().replace(LINE_SEPARATOR, ""));

		LOG.info(result.toUnfoldedFormatText());
	}

}
