
public class classicTravellerRules {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// simple basic min/max random number roller
	private static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

	// simulate the roll of one six-sided dice
	@SuppressWarnings("unused")
	private static int roll1d6() {
		return getRandomNumber(1, 6);
	}

	// simulate the roll of two six-sided dice
	private static int roll2d6() {
		return getRandomNumber(1, 6)+getRandomNumber(1, 6);
	}		

	// the traveller book, chart from pp84
	public static char rollRandomStarport() {
		char starport= 'X';

		switch (roll2d6()) {
		case 2:
		case 3:
		case 4:			
			starport = 'A';
			break;
		case 5:
		case 6:
			starport = 'B';
			break;			
		case 7:
		case 8:
			starport = 'C';
			break;
		case 9:
			starport = 'D';
			break;
		case 10:
		case 11:
			starport = 'E';
			break;			
		case 12:
			starport = 'F';
			break;	
		default:
			starport = 'z';
		}
		return starport;
	}

	// the traveller book, chart from pp84
	public static boolean rollForNavalBase(char starport) {
		boolean hasStarport = false;
		
		String skipList = "CDEX";
		String s = Character.toString(starport);
		if (skipList.contains(s)) {
			return false;
		}

		if (roll2d6()>7 ) {
			hasStarport = true;
		}
		
		return hasStarport;
	}

	public static boolean rollForScoutBase(char starport) {
		boolean hasScoutBase = false;
		int dieModifier = 0;
		int totalRoll = 0;
		
		if (starport == 'E'|| starport == 'X') {
			return false;
		}

		if (starport == 'C') {
			dieModifier = -1;
 		}

		if (starport == 'B') {
			dieModifier = -2;
 		}		

		if (starport == 'B') {
			dieModifier = -3;
 		}		
				
		totalRoll = roll2d6()+dieModifier;
		
		if(totalRoll >= 7) {
			hasScoutBase = true;
		}
		
		return hasScoutBase;
	}

	public static boolean rollForGasGiant() {
		boolean hasGasGiant = false;
		if(roll2d6()<10) {
			hasGasGiant = true;
		}
		return hasGasGiant;
	}	
	
}
