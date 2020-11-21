package model;

public enum FlightStatus {
	B("Arrival by bus at Concourse B"),
	D("Diverted"),
	I("Undefined late arrival or departure"),
	L("Aborted departure"),
	M("Flight delayed until tomorrow"),
	S("Definitively canceled flight"),
	X("Canceled flight for which there may be a replacement"),
	Y("Return to stand"),
	Z("Returned to apron");
	
	String text;
	
	FlightStatus(String text) {
		this.text = text;
	}
	
	public static String[] getValues() {
		return new String[] {
				B.text, 
				D.text,
				I.text,
				M.text,
				L.text,
				S.text,
				X.text,
				Y.text,
				Z.text,
		};
		
	}
	
	public static FlightStatus valueOfIndex(int index) {
		switch(index) {
			case 0:
				return FlightStatus.B;
			case 1:
				return FlightStatus.D;
			case 2:
				return FlightStatus.I;
			case 3:
				return FlightStatus.M;
			case 4:
				return FlightStatus.L;
			case 5:
				return FlightStatus.S;
			case 6:
				return FlightStatus.X;
			case 7:
				return FlightStatus.Y;
			case 8:
				return FlightStatus.Z;
			default:
				return null;
				
		}
	}
	
	public String getText() {
		return text;
	}
}
