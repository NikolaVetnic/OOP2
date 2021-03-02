package teorijske_vezbe.ponavljanje_kolekcije.wordcount;

/**
 * Pomocna klasa, predstavlja par "rec -> broj pojavljivanja". Koristimo je da
 * bismo sortirali reci opadajuce po broju pojavljivanja.
 * 
 * @author Dejan Mitrovic
 * @author Ivan Pribela
 */
public class Word implements Comparable<Word> {

	private final int count;
	private final String text;

	public Word(int count, String text) {
		this.count = count;
		this.text = text;
	}

	@Override
	public int compareTo(Word that) {

		// Sortiramo opadajuce po 'count' 
		int result = that.count - this.count;

		// Ukoliko je broj pojavljivanja dve reci isti, sortiramo ih rastuce po tekstu.
		// Ovaj korak je obavezan! ukoliko se, na primer, reci "da" i "ne" pojavljuju
		// po 100 puta, i mi ovde vratimo 0, za TreeMap ce to znaciti da su reci
		// jednake i (posto je u pitanju skup), drugu rec nece ubaciti!
		if (result == 0) {
			result = this.text.compareTo(that.text);
		}

		return result;

	}

	public String getText() {
		return text;
	}

	public int getCount() {
		return count;
	}
}
