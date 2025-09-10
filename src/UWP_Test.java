import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UWP_Test {

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
			test.setRandomStarport();
			assertNotEquals(shouldNotBe, test.getStarport());
		}
	}
	
}