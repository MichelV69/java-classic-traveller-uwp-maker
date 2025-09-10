
public class ctHomebrew {

	public static TravelZones rollForTravelZone(char starPort) {
		TravelZones localZone = TravelZones.GREEN;
		int dieModifier = 0;
		int totalRoll = 0;

		if (starPort == 'A') {
			dieModifier = -3;
		}

		if (starPort == 'B') {
			dieModifier = -1;
		}

		totalRoll = classicTravellerRules.roll2d6() + dieModifier;

		switch (totalRoll) {
		case 10:
		case 11:
			localZone = TravelZones.AMBER;
			break;
		case 12:
			localZone = TravelZones.RED;
			break;
		default:
			localZone = TravelZones.GREEN;
		}

		return localZone;
	}

}
