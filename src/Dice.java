
public class Dice {
	// simple basic min/max random number roller
	private static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

	// simulate the roll of one six-sided dice
	public static int roll1d6() {
		return getRandomNumber(1, 6);
	}

	// simulate the roll of two six-sided dice
	public static int roll2d6() {
		return getRandomNumber(1, 6)+getRandomNumber(1, 6);
	}	
}
