
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

		hydrographics = roll2d6 - 7 + worldSize + dieModifier;

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

	/*
	 * Compartmentalizing purely narrative / "color" output / content into an inner
	 * class. In the TTRPG, such information was called "Library Data", hence the
	 * class name.
	 */
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

		public static String uwp_Starport(char starport) {
			switch (starport) {
			case 'A':
				return "Excellent quality installation."
						+ "\n\t Refined fuel available. \n\t Annual maintenance overhaul available. \n\t Shipyard capable of constructing starships and "
						+ "non-starships present. \n\t Naval base and/or scout base may be present";
			case 'B':
				return "Good quality installation."
						+ "\n\t Refined fuel available. \n\t Annual maintenance overhaul available. \n\t Shipyard capable of constructing non-starships "
						+ "present. \n\t Naval base and/or scout base may be present";
			case 'C':
				return "Routine quality installation."
						+ "\n\t Only unrefined fuel available. \n\t Reasonable repair facilities present. \n\t Scout base may be present.";
			case 'D':
				return "Poor quality installation."
						+ "\n\t Only unrefined fuel available. \n\t No repair or shipyard facilities present. \n\t Scout base may be present";
			case 'E':
				return "Frontier Installation."
						+ "\n\t Essentially a marked spot of bedrock with no fuel, facilities, or bases present";
			case 'X':
				return "No starport. No provision is made for any ship landings.";
			}
			return "ERROR02 - should never reach this";
		}

		public static String uwp_Population(int population) {
			switch (population) {
			case 0:
				return "No permanent inhabitants.";
			case 1:
				return "Tens to dozens of permanent inhabitants.";
			case 2:
				return "Hundreds of inhabitants.";
			case 3:
				return "Thousands of inhabitants. Possibly multiple permanent settlements.";
			case 4:
				return "Tens of thousands of inhabitants and multiple permanent settlements.";
			case 5:
				return "Hundreds of thousands. At least one major city.";
			case 6:
				return "Millions of inhabitants. Multiple major cities.";
			case 7:
				return "Tens of millions. At least one metropolis.";
			case 8:
				return "Hundreds of millions. Multiple metropoli.";
			case 9:
				return "Billions of inhabitants. Possible archologies.";
			case 10:
				return "Tens of billions. Multiple archologies.";

			}
			return "ERROR03 - should never reach this";
		}

		public static String uwp_LawLevel(int lawLevel) {
			switch (lawLevel) {
			case 0:
				return "No prohibitions.";
			case 1:
				return "Body pistols undetectable by standard detectors, explosives (bombs, grenades), and poison gas prohibited.";
			case 2:
				return "Portable energy weapons (laser carbine, laser rifle) prohibited. Ship's gunnery not affected";
			case 3:
				return "Weapons of a strict military nature (machine guns, automatic rifles) prohibited.";
			case 4:
				return "Light assault weapons (submachineguns) prohibited.";
			case 5:
				return "Personal concealable firearms (such as pistols and revolvers) prohibited.";
			case 6:
				return "Most firearms (all except shotguns) prohibited. The carrying of any type of weapon openly is discouraged.";
			case 7:
				return "Shotguns are prohibited.";
			case 8:
				return "Long bladed weapons (all but daggers) are controlled, and open possession is prohibited.";
			case 9:
				return "Possession of any weapon outside one's residence is prohibited";
			case 10:
				return "Possession of any weapon is prohibited.";
			}
			return "ERROR04 - should never reach this";
		}

	}

}
