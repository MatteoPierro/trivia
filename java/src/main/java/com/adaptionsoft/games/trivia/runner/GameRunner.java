
package com.adaptionsoft.games.trivia.runner;
import java.util.Arrays;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		run(new Random(), "Chet", "Pat", "Sue");
	}

	public static void run(Random rand, String... players) {
		Game aGame = new Game();

		for (String player : players) {
			aGame.add(player);
		}

		do {

			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}



		} while (notAWinner);
	}
}
