package statistics;
/**
 * Created by syrovo01 on 21.10.2016.
 */
public enum AgeRange {
	Ten(1),
	Twenties(2),
	Thirties(3),
	Forties(4),
	Fifties(5),
	Sixties(6),
	Seventies(7),
	Eighties(8),
	Nineties(9);

	private final int value;

	AgeRange(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static String getStringFromOrdinal(int ordinal) {
		for( AgeRange status : AgeRange.values() ) {
			if( status.getValue() == ordinal ) {
				return status.toString();
			}
		}
		return null;
	}
}
