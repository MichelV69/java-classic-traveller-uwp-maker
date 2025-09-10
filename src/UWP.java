/*
 * class implementation to express Classic Traveller UWP as a data object.
 * Includes validations of SET inputs to prevent bogus content.
 */

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

	public String toString() {
		String output = "";
		String uwp = "";
		String bases = "";
		String gasGiants = "";

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

		return output;
	}

}
