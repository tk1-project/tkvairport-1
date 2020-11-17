package model;

public enum FlightStatus {
	B,
	D,
	I,
	L,
	M,
	S,
	X,
	Y,
	Z;
	
	public static String getTextStatus(FlightStatus s) {
		switch(s) {
			case B:
				return "Arrival by bus at Concourse B";
			case D:
				return "Diverted";
			case I:
				return "Undefined late arrival or departure";
			case L:
				return "Aborted departure";
			case M:
				return "Flight delayed until tomorrow";
			case S:
				return "Definitively canceled flight";
			case X:
				return "Canceled flight for which there may be a replacement";
			case Y:
				return "Return to stand";
			case Z:
				return "Returned to apron";
			default:
				return "";
		}
	}
}
