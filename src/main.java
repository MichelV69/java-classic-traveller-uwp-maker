/*
 *  uses the GDW Classic Traveller Rules to create the UWP (Universal World
 *    Profile) from that TTRPG.
 *    
 *  A "Functional" programming approach is used, while the created data is
 *    sorted as a Class/record.
 *    
 *    @author : michel@wolfstar.ca
 *    @date   : 09 sept 2025
 */

public class main {

	/*
	 * handles the primary program flow, including the creation of class
	 *   instances, recording generated data, and presenting formated output
	 *   to the User.
	 */
	public static void main(String[] args) {
		// Create a Blank UWP
		UWP braveNewWorld = new UWP();
		
		// Capture System Contents
		braveNewWorld.setStarport(classicTravellerRules.rollRandomStarport()); 
		braveNewWorld.setNavalBase(classicTravellerRules.rollForNavalBase(braveNewWorld.getStarport()));
		braveNewWorld.setScoutBase(classicTravellerRules.rollForScoutBase(braveNewWorld.getStarport()));
		braveNewWorld.setGasGiant(classicTravellerRules.rollForGasGiant());
		
		// Decide on Travel Zone
		braveNewWorld.setTravelZone(ctHomebrew.rollForTravelZone(braveNewWorld.getStarport()));
		
		// Determine Planetary Size
		
		// Determine Planetary Atmosphere
		
		// Hydrographic Percentage
		
		// Population Level
		
		// Planetary Government Type
		
		// Law Level
		
		// Technological Level
		
		// Present formatted result to the User
		
	}

}
