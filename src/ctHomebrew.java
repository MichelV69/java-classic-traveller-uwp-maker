
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

		if (starPort == 'X') {
			dieModifier = +2;
		}
		
		totalRoll = Dice.roll2d6() + dieModifier;

		switch (totalRoll) {
		case 10:
		case 11:
			localZone = TravelZones.AMBER;
			break;
		case 12:
		case 13:
		case 14:
			localZone = TravelZones.RED;
			break;
		default:
			localZone = TravelZones.GREEN;
		}

		return localZone;
	}

}
