package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameRunnerTest {

	@Test
	public void run_a_game() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);

		PrintStream oldOut = System.out;
		System.setOut(printStream);

		GameRunner.main(null);

		System.setOut(oldOut);
		Approvals.verify(outputStream.toString());
	}
}
