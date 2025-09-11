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
	 * handles the primary program flow, including the creation of class instances,
	 * recording generated data, and presenting formated output to the User.
	 */
	public main() {
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
		braveNewWorld.setSize(Dice.roll2d6() - 2);

		// Determine Planetary Atmosphere
		braveNewWorld.setAtmosphere(classicTravellerRules.createAtmosphere(Dice.roll2d6(), braveNewWorld.getSize()));

		// Hydrographic Percentage
		braveNewWorld.setHydrographics(classicTravellerRules.computeHydrographics(Dice.roll2d6(),
				braveNewWorld.getSize(), braveNewWorld.getAtmosphere()));

		// Population Level
		braveNewWorld.setPopulation(Dice.roll2d6() - 2);

		// Planetary Government Type
		braveNewWorld.setGovernmentType(Dice.roll2d6() - 7 + braveNewWorld.getPopulation());

		// Law Level
		braveNewWorld.setLawLevel(Dice.roll2d6() - 7 + braveNewWorld.getGovernment());

		// Technological Level
		braveNewWorld.setTechnologicalLevel(classicTravellerRules.computeTL(Dice.roll1d6(), braveNewWorld));

		// Present formatted result to the User
		System.out.println(braveNewWorld.toString());
	}

}
