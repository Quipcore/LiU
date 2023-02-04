package yatzy;

import java.util.Random;

public class Dice {

	private Object[][] dice = new Object[5][2]; // First pos for value and second pos for enabled/disabled when rolling
	Random rng = new Random();

	public Dice() {
		setStartDice();
	}

	// -----------------------------------------------------------------------------------

	// Needed?
	private void setStartDice() {
		for (int i = 0; i < dice.length; i++) {
			dice[i][1] = true;
			dice[i][0] = rng.nextInt(6) + 1;
		}
		printDice();
	}

	// -----------------------------------------------------------------------------------

	public void rollDice() { //Setter
		for (int i = 0; i < dice.length; i++) {
			if ((boolean) dice[i][1]) {
				dice[i][0] = rng.nextInt(6) + 1;
			}
		}
	}

	// -----------------------------------------------------------------------------------

	// For debugging only
	public void printDice() {
		for (int i = 0; i < dice.length; i++) {
			// System.out.println("d" + (i + 1) + " " + dice[i][0] + " " + dice[i][1]);
		}
		// System.out.println("");
	}

	// -----------------------------------------------------------------------------------

	public Object[][] getDice() { //Getter
		return dice;
	}

	// -----------------------------------------------------------------------------------
	public void enableDice(boolean d1, boolean d2, boolean d3, boolean d4, boolean d5) {
		dice[0][1] = d1;
		dice[1][1] = d2;
		dice[2][1] = d3;
		dice[3][1] = d4;
		dice[4][1] = d5;
	}

	// -----------------------------------------------------------------------------------
	public int size() {
		return dice.length;
	}
}
