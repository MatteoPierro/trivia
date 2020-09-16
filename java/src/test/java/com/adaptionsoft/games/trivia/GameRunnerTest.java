package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GameRunnerTest {

	@Test
	public void run_a_game() throws Exception {
		Integer[] seeds = {1,2};
		Approvals.verifyAll(seeds,seed -> runGame(seed));
	}

	private String runGame(int seed) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);
		PrintStream oldOut = System.out;
		System.setOut(printStream);

		GameRunner.run(new Random(seed));

		System.setOut(oldOut);
		return outputStream.toString();
	}
}
