
enum TravelZones {
	GREEN, AMBER, RED
}

public class classicTravellerRules {

	// the traveller book, chart from pp84
	public static char rollRandomStarport() {
		char starport = 'X';

		switch (Dice.roll2d6()) {
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

		if (Dice.roll2d6() > 7) {
			hasStarport = true;
		}

		return hasStarport;
	}

	// the traveller book, chart from pp84
	public static boolean rollForScoutBase(char starport) {
		boolean hasScoutBase = false;
		int dieModifier = 0;
		int totalRoll = 0;

		if (starport == 'E' || starport == 'X') {
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

		totalRoll = Dice.roll2d6() + dieModifier;

		if (totalRoll >= 7) {
			hasScoutBase = true;
		}

		return hasScoutBase;
	}

	// the traveller book, chart from pp84
	public static boolean rollForGasGiant() {
		boolean hasGasGiant = false;
		if (Dice.roll2d6() < 10) {
			hasGasGiant = true;
		}
		return hasGasGiant;
	}

	// the traveller book, chart from pp85
	public static int createAtmosphere(int roll2d6, Integer worldSize) {
		int atmosphere = 0;

		atmosphere = roll2d6 - 7 + worldSize;

		if (atmosphere < 0) {
			atmosphere = 0;
		}

		if (worldSize == 0) {
			atmosphere = 0;
		}

		return atmosphere;
	}

	// the traveller book, chart from pp85
	public static int computeHydrographics(int roll2d6, Integer worldSize, Integer atmosphere) {
		int hydrographics = 0;
		int dieModifier = 0;

		switch (atmosphere) {
		case 0:
		case 1:
			dieModifier = -4;
			break;
		case 10:
			dieModifier = -4;
			break;
		}

		hydrographics = roll2d6 - 7 + worldSize;

		if (hydrographics < 0) {
			hydrographics = 0;
		}

		return hydrographics;
	}

	// the traveller book, chart from pp85
	public static int computeTL(int roll1d6, UWP uwp) {
		int techLevel = roll1d6;
		int dieModifier = 0;

		if(uwp.getPopulation()==0) {
			return 0;
		}
		
		switch (uwp.getStarport()) {
		case 'A':
			dieModifier = +6;
			break;
		case 'B':
			dieModifier = +4;
			break;
		case 'C':
			dieModifier = +2;
			break;
		case 'X':
			dieModifier = -4;
			break;
		}

		techLevel = techLevel + dieModifier;
		dieModifier = 0;

		if (uwp.getSize() < 1) {
			dieModifier = +2;
		}
		if (uwp.getSize() > 1 && uwp.getSize() > 5) {
			dieModifier = +1;
		}

		techLevel = techLevel + dieModifier;
		dieModifier = 0;

		if (uwp.getAtmosphere() < 4 || uwp.getAtmosphere() > 9) {
			dieModifier = +1;
		}

		techLevel = techLevel + dieModifier;
		dieModifier = 0;

		if(uwp.getHydrographics()==9) {
			dieModifier = +1;
		}
		if(uwp.getHydrographics()==10) {
			dieModifier = +2;
		}
		
		techLevel = techLevel + dieModifier;
		dieModifier = 0;		
		
		if(uwp.getPopulation()<6 && uwp.getPopulation()>0) {
			dieModifier = +1;
		}
		if(uwp.getPopulation()==9) {
			dieModifier = +1;
		}
		if(uwp.getPopulation()==10) {
			dieModifier = +2;
		}
		
		techLevel = techLevel + dieModifier;
		dieModifier = 0;		
		
		switch(uwp.getGovernment()) {
		case 0:
			dieModifier = +1;
			break;
		case 5:
			dieModifier = +1;
			break;
		case 13:
			dieModifier = -2;
			break;
		}
		techLevel = techLevel + dieModifier;
		
		return techLevel;
	}

}
