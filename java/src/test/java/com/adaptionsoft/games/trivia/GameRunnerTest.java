package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GameRunnerTest {

	@Test
	public void run_a_game() throws Exception {
		Integer[] seeds = {1,2};
		Players[] playerCombinations = new Players[] {
				new Players("Chet", "Pat", "Sue")
		};

		CombinationApprovals.verifyAllCombinations(
				this::runGame,
				seeds,
				playerCombinations
		);
	}

	private String runGame(int seed, Players players) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);
		PrintStream oldOut = System.out;
		System.setOut(printStream);

		GameRunner.run(new Random(seed), players.values());

		System.setOut(oldOut);
		return outputStream.toString();
	}

	private class Players {
		private String[] players;

		public Players(String ... players) {
			this.players = players;
		}

		public String[] values() {
			return players;
		}

		@Override
		public String toString() {
			return String.join(",", players);
		}
	}
}
