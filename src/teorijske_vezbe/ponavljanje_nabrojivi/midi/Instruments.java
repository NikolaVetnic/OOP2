package teorijske_vezbe.ponavljanje_nabrojivi.midi;

/**
 * Nekoliko instrumenata MIDI specifikacije.
 * 
 * @author Dejan Mitrovic
 * @author Ivan Pribela
 */
public enum Instruments {

	PIANO(0, "Acoustic Grand Piano"),
	MARIMBA(12, "Marimba"),
	ACCORDION(21, "Accordion"),
	GUITAR(29, "Overdriven Guitar"), 
	CELLO(42, "Cello"),
	CONTRABASS(43, "Contrabass"),
	CLARINET(71, "Clarinet"),
	FLUTE(73, "Flute"),
	BIRD(123, "Bird Tweet"),
	HELICOPTER(125, "Helicopter");

	// Po nepisanom pravilu, sva polja enum-a bi trebalo da budu final
	private final int patch; // ID instrumenta u MIDI specifikaciji
	private final String desc; // user-friendly opis

	// Konstruktor moze biti samo private
	private Instruments(int patch, String desc) {
		this.patch = patch;
		this.desc = desc;
	}

	public int getPatch() {
		return patch;
	}

	@Override
	public String toString() {
		return desc;
	}
}
