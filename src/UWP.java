/*
 * class implementation to express Classic Traveller UWP as a data object.
 * Includes validations of SET inputs to prevent bogus content.
 */

import java.util.ArrayList;

public class UWP {

	private char starport;
	private boolean navalBase;
	private boolean scoutBase;
	private boolean gasGiant;
	private TravelZones travelZoneCode;
	private int size;
	private int atmosphere;
	private int hydrographics;
	private int population;
	private int lawLevel;
	private int governmentType;
	private int technologicalLevel;

	public void setStarport(char data) {
		this.starport = validateStarportData(data);
	}

	public void setNavalBase(boolean data) {
		this.navalBase = data;
	}

	public void setScoutBase(boolean data) {
		this.scoutBase = data;
	}

	public void setGasGiant(boolean data) {
		this.gasGiant = data;
	}

	public void setSize(int data) {
		this.size = valdiateIntData(data);
	}

	public void setAtmosphere(int data) {
		this.atmosphere = valdiateIntData(data);
	}

	public void setHydrographics(int data) {
		this.hydrographics = valdiateIntData(data);
	}

	public void setPopulation(int data) {
		this.population = valdiateIntData(data);
	}

	public void setLawLevel(int data) {
		this.lawLevel = valdiateIntData(data);
	}

	public void setGovernmentType(int data) {
		this.governmentType = valdiateIntData(data);
	}

	public void setTechnologicalLevel(int data) {
		this.technologicalLevel = valdiateIntData(data);
	}

	private char validateStarportData(char data) {
		String validChars = "ABCDEF";
		char result = 'z';

		char c = Character.toUpperCase(data);
		String s = Character.toString(c);
		if (validChars.contains(s)) {
			result = c;
		}

		return result;
	}

	private int valdiateIntData(int data) {
		final int MINIMUM = 0;
		final int MAXIMUM = 16;

		int result = data;

		if (data < MINIMUM) {
			result = MINIMUM;
		}
		if (data > MAXIMUM) {
			result = MAXIMUM;
		}
		return result;
	}

	public char getStarport() {
		return this.starport;
	}

	public Integer getAtmosphere() {
		return this.atmosphere;
	}

	public boolean getNavalBase() {
		return this.navalBase;
	}

	public boolean getScoutBase() {
		return this.scoutBase;
	}

	public void setTravelZone(TravelZones travelZone) {
		this.travelZoneCode = travelZone;
	}

	public int getSize() {
		return this.size;
	}

	public int getPopulation() {
		return this.population;
	}

	public int getGovernment() {
		return this.governmentType;
	}

	public int getHydrographics() {
		return this.hydrographics;
	}

	/*
	 * Code DRYer. Handles repetitive less-than and greater-than boolean tests
	 */
	private boolean isBetween(int test, int low, int high) {
		return (low <= test && test <= high);
	}

	// the traveller book, chart from pp85
	public String isAgricultural() {
		String text = "";

		if (isBetween(this.atmosphere, 4, 9) && isBetween(this.hydrographics, 4, 8)
				&& isBetween(this.population, 5, 7)) {
			text = "Agricultural";
		}

		if (this.atmosphere <= 3 && this.hydrographics <= 3 && this.population >= 6) {
			text = "Non-Agricultural";
		}

		return text;
	}

	// the traveller book, chart from pp85
	public String isIndustrial() {
		String text = "";

		if (this.population >= 9
				&& (isBetween(this.atmosphere, 0, 4) || this.atmosphere == 7 || this.atmosphere == 9)) {
			text = "Industrial";
		}

		if (this.population <= 6) {
			text = "Non-Industrial";
		}

		return text;
	}

	// the traveller book, chart from pp85
	public String isRich() {
		String text = "";

		if ((this.atmosphere == 6 || this.atmosphere == 8) && isBetween(this.population, 6, 8)
				&& isBetween(this.governmentType, 4, 9)) {
			text = "Rich";
		}

		if (this.hydrographics <= 3 && isBetween(this.atmosphere, 2, 5)) {
			text = "Poor";
		}

		return text;
	}

	// the traveller book, chart from pp85
	public String isWaterWorld() {
		String text = "";
		if (this.hydrographics == 10) {
			text = "Water World";
		}
		return text;
	}

	// the traveller book, chart from pp85
	public String isDesertWorld() {
		String text = "";
		if (this.hydrographics == 0 && this.atmosphere >= 2) {
			text = "Desert World";
		}
		return text;
	}

	// the traveller book, chart from pp85
	public String isVaccumWorld() {
		String text = "";
		if (this.atmosphere == 0) {
			text = "Vaccum World";
		}
		return text;
	}

	// the traveller book, chart from pp85
	public String isAsteroidColony() {
		String text = "";
		if (this.size == 0) {
			text = "Asteroid Colony";
		}
		return text;
	}

	// the traveller book, chart from pp85
	public String isIceCapped() {
		String text = "";
		if ((this.atmosphere == 0 || this.atmosphere == 1) && this.hydrographics >= 1) {
			text = "has Polar Ice-caps";
		}
		return text;
	}

	/*
	 * provide meaningful formated output to the user that is immediately valuable
	 * at the gaming table.
	 */
	public String toString() {
		String output = "";
		String uwp = "";
		String bases = "";
		String gasGiants = "";
		String tcList = "";

		output = "-----------------------------------------------------\n";
		output += String.format("%-33s%-33s\n\n", "Subsector:", "Hex:");
		output += "MAIN WORLD NAME: \n\n";
		output += "-----------------------------------------------------\n";

		uwp = String.format("UWP: %s-%X%X%X%X%X%X-%X", this.starport, this.size, this.atmosphere, this.hydrographics,
				this.population, this.governmentType, this.lawLevel, this.technologicalLevel);

		output += String.format("%-33s%-33s\n", uwp, this.travelZoneCode.toString() + " Zone");

		if (this.navalBase) {
			bases += "Naval";
		}
		if (this.scoutBase) {
			if (bases.length() > 1) {
				bases += " & ";
			}
			bases += "Scout";
		}
		if (bases.length() < 1) {
			bases += "(none)";
		}

		if (this.gasGiant) {
			gasGiants = "Yes";
		} else {
			gasGiants = "(none)";
		}
		output += String.format("%-33s%-33s\n", "Bases: " + bases, "Gas Giant: " + gasGiants);

		// throw the results of trade-category self-tests into an array to
		// make it easier to ignore nulls and format the total output
		// neatly.
		ArrayList<String> tradeCategories = new ArrayList<String>();
		tradeCategories.add(isRich());
		tradeCategories.add(isAgricultural());
		tradeCategories.add(isIndustrial());
		tradeCategories.add(isWaterWorld());
		tradeCategories.add(isDesertWorld());
		tradeCategories.add(isVaccumWorld());
		tradeCategories.add(isIceCapped());
		tradeCategories.add(isAsteroidColony());

		for (String tc : tradeCategories) {
			if (tc.length() > 1) {
				tcList += "[" + tc + "] ";
			}
			;
		}

		if (tcList.length() < 1) {
			tcList = "(none)";
		}
		output += "Trade Classifications: " + tcList;

		// build an array to loop for output of the planet Library Data details
		ArrayList<String> uwpLibraryData = new ArrayList<String>();
		uwpLibraryData.add(classicTravellerRules.LibraryData.uwp_Starport(this.starport));

		uwpLibraryData.add(classicTravellerRules.LibraryData.uwp_Size(this.size));
		uwpLibraryData.add(classicTravellerRules.LibraryData.uwp_Atmosphere(this.atmosphere) + " atmosphere.");
		uwpLibraryData.add(classicTravellerRules.LibraryData.uwp_Hydrographics(this.hydrographics));

		uwpLibraryData.add(classicTravellerRules.LibraryData.uwp_Population(this.population));
		uwpLibraryData.add(classicTravellerRules.LibraryData.uwp_LawLevel(this.lawLevel));

		output += "\n \n Details :";
		for (String ld : uwpLibraryData) {
			output += "\n + " + ld;
		}
		// -------
		return output;
	}

}
