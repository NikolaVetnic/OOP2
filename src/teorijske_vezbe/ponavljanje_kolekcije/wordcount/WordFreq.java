package teorijske_vezbe.ponavljanje_kolekcije.wordcount;

import static org.svetovid.Svetovid.in;
import static org.svetovid.Svetovid.out;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 * (Uproscen) Program koji utvrdjuje n najcesce koriscenih reci srpskog jezika.
 * 
 * @author Dejan Mitrovic
 * @author Ivan Pribela
 */

public class WordFreq {

	public static void main(String[] args) throws IOException {

		out.print("Reading files... ");
		List<String> reci = processFile("text.txt");
		out.printf("read %d lines\n", reci.size());

		// Rec -> broj pojavljivanja
		out.print("Counting words... ");
		Map<String, Integer> mapa = countWords(reci);
		out.printf("counted %d distinct words\n", mapa.size());

		// Mapu je moguce sortirati samo po kljucu, a nama treba po vrednostima
		out.print("Sorting...");
		Set<Word> skup = sort(mapa);

		// Ispis
		out.println("\n");
		Iterator<Word> i = skup.iterator();
		int j = 0;
		while (i.hasNext() && (j < 100)) { // prvih 100
			Word w = i.next();
			out.printf("%-10s %d\n", w.getText(), w.getCount());
			j++;
		}
	}

	/**
	 * Iscitava sve reci iz fajla u listu.
	 */
	private static List<String> processFile(String fileName) {

		// Procitamo sav sadrzaj
		String sadrzaj = in(WordFreq.class.getResource(fileName).toString()).readAll();

		// Zamenimo svako ne-slovo znakom za novi red
		sadrzaj = sadrzaj.toLowerCase().replaceAll("[^a-z]", " ");

		// Zamenimo jednu ili vise praznina jednim razmakom
		sadrzaj = sadrzaj.replaceAll("[\\s]{2,}", " ");

		// Uklonimo viskove praznina sa pocetka i kraja
		sadrzaj = sadrzaj.trim();

		// Dodamo pojedinacne reci u rezultujucu listu
		List<String> reci = new ArrayList<>();
		reci.addAll(Arrays.asList(sadrzaj.split(" ")));
		return reci;

	}

	/**
	 * Prebrojava koliko se puta svaka pojedinacna rec pojavljuje u listi.
	 */
	private static Map<String, Integer> countWords(List<String> reci) {

		// Prvo napravimo praznu mapu
		Map<String, Integer> mapa = new HashMap<>();

		// Idemo po svimrecima
		for (String rec : reci) {

			// Koliko puta se ova rec pojavila do sada?
			Integer n = mapa.get(rec);

			// Prvo pojavljivanje (kljuc ne postoji u mapi)
			if (n == null) {
				mapa.put(rec, 1);
			} else {
				mapa.put(rec, n + 1);
			}
		}
		
		// Vratimo mapu
		return mapa;

	}

	private static Set<Word> sort(Map<String, Integer> mapa) {
		Set<Word> skup = new TreeSet<Word>();
		// Entry<String, Integer> predstavlja jedan par kljuc-vrednost u nasoj mapi
		for (Entry<String, Integer> entry : mapa.entrySet()) {
			Word w = new Word(entry.getValue(), entry.getKey());
			skup.add(w);
		}
		return skup;
	}
}
