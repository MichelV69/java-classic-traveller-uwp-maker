
enum TravelZones {
	GREEN {
		@Override
		public String toString() {
			return "Green";
		}
	},
	AMBER {
		@Override
		public String toString() {
			return "Amber";
		}
	},
	RED {
		@Override
		public String toString() {
			return "Red";
		}
	}
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

		if (uwp.getPopulation() == 0) {
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

		if (uwp.getHydrographics() == 9) {
			dieModifier = +1;
		}
		if (uwp.getHydrographics() == 10) {
			dieModifier = +2;
		}

		techLevel = techLevel + dieModifier;
		dieModifier = 0;

		if (uwp.getPopulation() < 6 && uwp.getPopulation() > 0) {
			dieModifier = +1;
		}
		if (uwp.getPopulation() == 9) {
			dieModifier = +1;
		}
		if (uwp.getPopulation() == 10) {
			dieModifier = +2;
		}

		techLevel = techLevel + dieModifier;
		dieModifier = 0;

		switch (uwp.getGovernment()) {
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

	public static class LibraryData {
		public static String uwp_Size(int size) {
			return String.format("%d miles (%d km) diameter.", size * 1000, size * 1600);
		}

		public static String uwp_Atmosphere(int atmosphere) {
			switch (atmosphere) {
			case 0:
				return "No";
			case 1:
				return "Trace";
			case 2:
				return "Very thin, tainted";
			case 3:
				return "Very thin";
			case 4:
				return "Thin, tainted";
			case 5:
				return "Thin";
			case 6:
				return "Standard";
			case 7:
				return "Standard, tainted";
			case 8:
				return "Dense";
			case 9:
				return "Dense, tainted";
			case 10:
				return "Exotic";
			case 11:
				return "Corrosive";
			case 12:
				return "Insideous";

			}
			return "ERROR01 - should never reach this";
		}

		public static String uwp_Hydrographics(int hydrographics) {
			if (hydrographics == 0)
				return "No free standing water on surface.";
			if (hydrographics == 10)
				return "No substantial land masses.";
			return String.format("%d%c+ water-covered.", hydrographics * 10, '%');

		}

		public static String uwp_starport(char starport) {
			switch (starport) {
			case 'A':
				return "Excellent quality installation.\n"
						+ "Refined fuel available. Annual maintenance overhaul available. Shipyard capable of constructing starships and "
						+ "non-starships present. Naval base and/or scout base may be present";
			case 'B':
				return "Good quality installation.\n"
						+ "Refined fuel available. Annual maintenance overhaul available. Shipyard capable of constructing non-starships "
						+ "present. Naval base and/or scout base may be present";
			case 'C':
				return "Routine quality installation.\n"
						+ "Only unrefined fuel available. Reasonable repair facilities present. Scout base may be present.";
			case 'D':
				return "Poor quality installation.\n"
						+ "Only unrefined fuel available. No repair or shipyard facilities present. Scout base may be present";
			case 'E':
				return "Frontier Installation.\n"
						+ "Essentially a marked spot of bedrock with no fuel, facilities, or bases present";
			case 'X':
				return "No starport. No provision is made for any ship landings.";
			}
			return "ERROR02 - should never reach this";
		}
	}

}
