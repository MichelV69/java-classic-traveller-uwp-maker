/*
 * class implementation to express Classic Traveller UWP as a data object.
 * Includes validations of SET inputs to prevent bogus content.
 */

import java.io.*;
import java.util.*;

public class UWP {

	private char starport ;
	private boolean navalBase;
	private boolean scoutBase;
	private boolean gasGiant;
	private int size;
	private int atmosphere;
	private int hydrographics;
	private int population;
	private int lawLevel;
	private int governmentType;
	private int technologicalLevel;

	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	private int roll1d6() {
		return getRandomNumber(1, 6);
	}
	
	private int roll2d6() {
		return getRandomNumber(1, 6)+getRandomNumber(1, 6);
	}	

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
		String valid_chars = "ABCDEF";
		char result = 'z';
		
		char c = Character.toUpperCase(data);
		String s = Character.toString(c);
		if (valid_chars.contains(s)) {
			result = c;
		}
		
		return result;
	}
	
	private int valdiateIntData(int data)
	{
		final int  MINIMUM = 0;
		final int  MAXIMUM = 16; 
				
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

	public void setRandomStarport() {
		this.starport = 'X';
		switch (roll2d6()) {
		case 2:
		case 3:
		case 4:			
			this.starport = 'A';
			break;
		case 5:
		case 6:
			this.starport = 'B';
			break;			
		case 7:
		case 8:
			this.starport = 'C';
			break;
		case 9:
			this.starport = 'D';
			break;
		case 10:
		case 11:
			this.starport = 'E';
			break;			
		case 12:
			this.starport = 'F';
			break;	
		default:
			this.starport = 'z';
		}

	}
}
