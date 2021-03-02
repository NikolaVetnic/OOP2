package teorijske_vezbe.ponavljanje_stringovi;

import static org.svetovid.Svetovid.out;

/**
 * Poredi efikasnosti klasa String i StringBuilder.
 *
 * @author Dejan Mitrovic
 */
public class SpeedTest {

	private static final String SAMPLE = "The Quick Brown Fox Jumps Over The Lazy Dog.";
	private static final int COUNT = 5000;

	/**
	 * Testira efikasnost klase String.
	 *
	 * @return Ukupno vreme izvrsavanja.
	 */
	private static double testString() {

		// Zapamti pocetno vreme izvrsavanja
		long start = System.nanoTime();

		// Ignorisi upozorenje "The value of the local variable str is not used"
		@SuppressWarnings("unused")
		String str = "";

		for (int i = 0; i < COUNT; i++) {
			str += SAMPLE;
		}

		// Koliko je ukupno vremena proslo?
		long total = System.nanoTime() - start;

		// Nanosekunde u milisekunde
		return total / 1000000.0;

	}

	/**
	 * Testira efikasnost klase StringBuilder, bez upotrebe inicijalnog kapaciteta.
	 *
	 * @return Ukupno vreme izvrsavanja.
	 */
	private static double testSBNoCapacity() {

		long start = System.nanoTime();

		StringBuilder str = new StringBuilder();

		for (int i = 0; i < COUNT; i++) {
			str.append(SAMPLE);
		}

		long total = System.nanoTime() - start;

		return total / 1000000.0;

	}

	/**
	 * Testira efikasnost klase StringBuilder uz upotrebu inicijalnog kapaciteta.
	 *
	 * @return Ukupno vreme izvrsavanja.
	 */
	private static double testSBWithCapacity() {

		long start = System.nanoTime();

		// Znamo konacnu duzinu stringa -> postizemo tacno jednu alokaciju memorije
		StringBuilder str = new StringBuilder(COUNT * SAMPLE.length());

		for (int i = 0; i < COUNT; i++) {
			str.append(SAMPLE);
		}

		long total = System.nanoTime() - start;

		return total / 1000000.0;

	}

	public static void main(String[] args) {
		out.println("String:           " + testString() + " ms");
		out.println("SB No Capacity:   " + testSBNoCapacity() + " ms");
		out.println("SB With Capacity: " + testSBWithCapacity() + " ms");
	}
}
