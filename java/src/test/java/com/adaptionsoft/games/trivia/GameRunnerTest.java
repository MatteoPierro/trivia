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
				new Players(),
				new Players("Chet"),
				new Players("Chet", "Pat"),
				new Players("Chet", "Pat", "Sue"),
				new Players("Chet", "Pat", "Sue", "Foo"),
				new Players("Chet", "Pat", "Sue", "Foo", "Bar"),
				new Players("Chet", "Pat", "Sue", "Foo", "Bar", "Bizz"),
				new Players("Chet", "Pat", "Sue", "Foo", "Bar", "Bizz", "Dho"),
		};

		CombinationApprovals.verifyAllCombinations(
				this::runGame,
				seeds,
				playerCombinations
		);
	}

	@Test
	public void extracts_all_questions() {
		Approvals.verify(run(endlessGame()));
	}

	public Runnable endlessGame() {
		return () -> {
			try {
				Random random = new Random() {
					private int seed = 7;

					@Override
					public int nextInt(int bound) {
						seed = seed == 7 ? 4 : 7;
						return seed;
					}
				};

				GameRunner.run(random, "Chet");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}

	private String runGame(int seed, Players players) {
		return run(gameWith(seed, players));
	}

	private Runnable gameWith(int seed, Players players) {
		return () -> GameRunner.run(new Random(seed), players.values());
	}

	private String run(Runnable game) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);
		PrintStream oldOut = System.out;
		System.setOut(printStream);

		game.run();

		System.setOut(oldOut);
		return outputStream.toString();
	}

	private static class Players {
		private final String[] players;

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
