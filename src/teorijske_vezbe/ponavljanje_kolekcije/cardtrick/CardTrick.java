package teorijske_vezbe.ponavljanje_kolekcije.cardtrick;

import static org.svetovid.Svetovid.in;
import static org.svetovid.Svetovid.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementacija trika sa kartama. Demonstrira osnovne operacije za rad sa
 * kolekcijama.
 * 
 * @author Dejan Mitrovic
 * @author Ivan Pribela
 */
public class CardTrick {

	private static final int NUM_CARDS = 27;
	private static final int NUM_ROWS = 3;
	private static final int NUM_STEPS = 3;

	public static void main(String[] args) {

		// Spil karata
		List<Integer> deck = new ArrayList<>(NUM_CARDS);

		// Izmesamo karte
		for (int i = 0; i < NUM_CARDS; i++) {
			deck.add(i);
		}
		Collections.shuffle(deck);

		// Spil delimo u tri reda (nije moguce napraviti niz generickih klasa)
		List<List<Integer>> rows = new ArrayList<>(NUM_ROWS);
		for (int i = 0; i < NUM_ROWS; i++) {
			// Svaki red je lista od 9 karata
			rows.add(new ArrayList<Integer>(NUM_CARDS / NUM_ROWS)); 
		}

		in.readLine("Zamislite jedan broj iz intervala od 1 do 27, i pritisnite Enter za nastavak...");

		for (int i = 0; i < NUM_STEPS; i++) {

			split(deck, rows);

			// Ispisemo redove
			out.println();
			for (int j = 0; j < rows.size(); j++) {
				out.println(rows.get(j));
			}

			// Pitamo korisnika gde je njegova karta
			int selRow = in.readInt("U kom redu se nalazi vas broj (1/2/3)? "); 

			// Indeksi u kolekciji pocinju od 0
			selRow = selRow - 1;
			
			merge(deck, rows, selRow);

		}

		out.println("Zamislili ste broj: " + deck.get(13));
	}

	private static void split(List<Integer> deck, List<List<Integer>> rows) {
		// spil se deli u tri reda na sledeci nacin:
		// [1, 4, 7, ...]
		// [2, 5, 8, ...]
		// [3, 6, 9, ...]
		for (int i = 0; i < rows.size(); i++)
			rows.get(i).clear(); // prvo obrisemo redove...
		for (int i = 0; i < deck.size(); i++) {
			int row = i % NUM_ROWS;
			rows.get(row).add(deck.get(i));
		}
	}

	private static void merge(List<Integer> deck, List<List<Integer>> rows, int selRow) {
		// spajamo sva tri reda u pocetni spil. bitno je jedino da red
		// koji je korisnik odabrao bude u sredini
		deck.clear();
		switch (selRow) {
		case 0:
			// addAll dodaje prosledjenu kolekciju na kraj
			deck.addAll(rows.get(1));
			deck.addAll(rows.get(0));
			deck.addAll(rows.get(2));
			break;
		case 1:
			deck.addAll(rows.get(0));
			deck.addAll(rows.get(1));
			deck.addAll(rows.get(2));
			break;
		case 2:
			deck.addAll(rows.get(0));
			deck.addAll(rows.get(2));
			deck.addAll(rows.get(1));
			break;
		}
	}
}
