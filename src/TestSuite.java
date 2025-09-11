import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestSuite {

	@DisplayName("Validation tests for typical set requests")
	@Test
	void spaceport_tooBig() {
		char shouldBe = 'z';
		UWP test = new UWP();
		test.setStarport('H');
		assertEquals(shouldBe, test.getStarport());
	}

	@Test
	void intData_tooSmall() {
		int shouldBe = 0;
		UWP test = new UWP();
		test.setAtmosphere(-3);
		assertEquals(shouldBe, test.getAtmosphere());
	}

	@Test
	void intData_tooBig() {
		int shouldBe = 16;
		UWP test = new UWP();
		test.setAtmosphere(23);
		assertEquals(shouldBe, test.getAtmosphere());
	}

	@Test
	void randomStarport_neverQ() {
		char shouldNotBe = 'z';
		for (int i = 0; i < 999; i++) {
			UWP test = new UWP();
			test.setStarport(classicTravellerRules.rollRandomStarport());
			assertNotEquals(shouldNotBe, test.getStarport());
		}
	}

	@Test
	void randomNavalBase_neverLowStarport() {
		String noRollList = "CDEX";
		for (int i = 0; i < noRollList.length(); i++) {
			UWP test = new UWP();
			test.setNavalBase(classicTravellerRules.rollForNavalBase(noRollList.charAt(i)));
			assertFalse(test.getNavalBase());
		}
	}

	@Test
	void randomNavalBase_neverLowScoutBase() {
		String noRollList = "EX";
		for (int i = 0; i < noRollList.length(); i++) {
			UWP test = new UWP();
			test.setScoutBase(classicTravellerRules.rollForScoutBase(noRollList.charAt(i)));
			assertFalse(test.getScoutBase());
		}
	}

	@Test
	void randomGasGiant_neverMoreNo() {
		boolean shouldBe = true;
		int hasGG = 0;
		int noGG = 0;
		for (int i = 0; i < 999; i++) {
			if (classicTravellerRules.rollForGasGiant()) {
				hasGG++;
			} else {
				noGG++;
			}
		}
		assert (hasGG > noGG);
		assert (hasGG > 1);
		assert (noGG > 1);
	}

	@Test
	void homebrewTravelZone_isValid() {

		char starportCode = 'A';

		for (int i = 0; i < 999; i++) {
			assertNotEquals(ctHomebrew.rollForTravelZone(starportCode), TravelZones.RED);
			assertNotEquals(ctHomebrew.rollForTravelZone(starportCode), TravelZones.AMBER);
		}

		starportCode = 'B';
		for (int i = 0; i < 999; i++) {
			assertNotEquals(ctHomebrew.rollForTravelZone(starportCode), TravelZones.RED);
		}
	}

}