package domaci_zadaci.z02_p01.time;

public enum Dani {
	
	PON("MO"),
	UTO("TU"),
	SRE("WE"),
	CET("TH"),
	PET("FR"),
	SUB("SA"),
	NED("SU");
	
	private String inEnglish;
	
	private Dani(String inEnglish) {
		this.inEnglish = inEnglish;
	}
	
	public String inEnglish() { return inEnglish; };
}
