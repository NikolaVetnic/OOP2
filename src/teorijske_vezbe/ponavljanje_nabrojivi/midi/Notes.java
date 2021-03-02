package teorijske_vezbe.ponavljanje_nabrojivi.midi;

/**
 * Nekoliko nota MIDI specifikacije.
 * 
 * @author Dejan Mitrovic
 */
public enum Notes {

	C(60, "C"),
	CS(61, "C#/Db"),
	D (62, "D"),
	DS(63, "D#/Eb"),
	E (64, "E"),
	F (65, "F"),
	FS(66, "F#/Gb"),
	G (67, "G"),
	GS(68, "G#/Ab"),
	A (69, "A"); 

	// Po nepisanom pravilu, sva polja enum-a bi trebalo da budu final
	private final int num; // ID note u MIDI specifikaciji
	private final String desc; // user-friendly opis

	// Konstruktor moze biti samo private
	private Notes(int num, String desc) {
		this.num = num;
		this.desc = desc;
	}

	public final int getNoteNum() {
		return num;
	}

	@Override
	public String toString() {
		return desc;
	}
}
