package domaci_zadaci.z01_p01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import domaci_zadaci.z01_p01.util.EditDistance;

public class RasporedProgram1 {
	
	
    /***************************
     * NIKOLA VETNIC 438/19 IT *
     ***************************/
	
	
	private static Set<Item> predmeti = new TreeSet<Item>();
	private static Set<String> nazivi = new TreeSet<String>();
	

	public static void main(String[] args) throws IOException {

		String[] lines = readFile();
		
		check(lines);
		
		System.out.println("SPISAK PREDMETA : ");
		Iterator<Item> it = predmeti.iterator();
		while (it.hasNext()) System.out.println(it.next());

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Unesite naziv predmeta: ");
		String unos = in.readLine();
		print(lines, unos);

	}
	

	private static String[] readFile() throws IOException {
		
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(
						RasporedProgram1.class.getResourceAsStream("res/raspored.ics")))) {
			
			List<String> lines = new ArrayList<>();
			String line;
			
			while ((line = in.readLine()) != null) lines.add(line);
			
			return lines.toArray(new String[lines.size()]);
		}
	}
	

	private static void check(String[] lines) {
		
		for (int i = 0; i < lines.length; i++) {
			
			if (lines[i].equals("BEGIN:VEVENT")) {
				
				List<String> temp = new LinkedList<String>();
				int count = 0;
				
				String DTSTART = null, DTEND = null, RRULE = null, SUMMARY = null;
				
				while (!lines[i + count].equals("END:VEVENT")) {
					
					if (lines[i + count].charAt(0) == ' ') 
						temp.set(temp.size() - 1, temp.get(temp.size() - 1) + lines[i + count].trim());
					else 
						temp.add(lines[i + count]);
					
					String currTag = temp.get(temp.size() - 1).split("[:;]")[0].trim();
					
					if 		(currTag.equals("DTSTART"))
						DTSTART = temp.get(temp.size() - 1);
					else if (currTag.equals("DTEND"))
						DTEND 	= temp.get(temp.size() - 1);
					else if (currTag.equals("RRULE"))
						RRULE 	= temp.get(temp.size() - 1);
					else if (currTag.equals("SUMMARY"))
						SUMMARY = temp.get(temp.size() - 1);
					
					count++;
				}
				
				try {
					Item item = new Item(DTSTART, DTEND, RRULE, SUMMARY);
					
					predmeti.add(item);
					nazivi.add(item.predmet().toLowerCase());
				} catch (NumberFormatException e) {
					System.err.printf("Linija %05d -> %s \n", (i + count), e.getMessage());
				} catch (IllegalArgumentException e) {
					System.err.printf("Linija %05d -> %s \n", (i + count), e.getMessage());
				}
				
				i += count;
			}
		}
	}
	

	private static void print(String[] lines, String name) {
		
		/*
		 * Ideja je bila da se svi nazivi iz predmeta ucitanih u TreeSet p-
		 * orede sa unosom i da se za taj par stringova racuna string dist-
		 * anca, koja ce se zajedno sa Item objektom smestati u mapu; ta m-
		 * apa se zatim pretvara u listu i sortira prema vrednostima (pret-
		 * postavio sam da bi moglo da se desi da razliciti predmeti dobiju
		 * istu distancu u odnosu na unos pa zato ne bi bilo dobro da te v-
		 * rednosti budu kljucevi), a zatim se prikazuje samo prvi rezultat
		 * ukoliko je distanca 0 (savrseno poklapanje), inace prikazujem p-
		 * rva tri rezultata (nesto poput "did you mean?" na Google-u).
		 */ 
		
		name = name.toLowerCase();
		
		Iterator<Item> it = predmeti.iterator();
		
		Map<Item, Integer> m = new HashMap<Item, Integer>();
		
		while (it.hasNext()) {
			
			Item curr = it.next();
			if (!curr.tip().equalsIgnoreCase("p")) continue;
			
			m.put(curr, new EditDistance(name, curr.predmet().toLowerCase()).getDistance());
		}
		
		List<Entry<Item, Integer>> listOfEntries = new ArrayList<Entry<Item, Integer>>(m.entrySet());
		Collections.sort(listOfEntries, (e1, e2) -> e1.getValue() - e2.getValue());
		
		if (listOfEntries.get(0).getValue() == 0) {
			System.out.println(listOfEntries.get(0).getKey());
		} else {
			System.out.println("Trazeni predmet nije pronadjen; prikaz tri najpribliznija rezultata:");
			for (int i = 0; i < 3; i++) System.out.println(listOfEntries.get(i).getKey());
		}
	}
}