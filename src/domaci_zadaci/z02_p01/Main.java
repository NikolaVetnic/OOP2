package domaci_zadaci.z02_p01;

/*
 * Napisati program koji ispisuje raspored casova.
 *
 * 1) Napraviti klasu "Cas" koja predstavlja jedan cas. Klasa od o-
 *    sobina treba da ima bar naziv predmeta, ime predavaca, tip c-
 *    asa, vreme i mesto odrzavanja. Takodje, ova klasa implementi-
 *    ra interfejs Comparable kao i metode equals(), hashCode(), t-
 *    oString().
 *
 * 2) Napraviti nabrojivi tip "Dan" koji predstavlja dane u nedelji.
 *
 * 3) Napraviti klasu Raspored koja sadrzi mapu ciji su kljucevi da-
 *    ni u nedelji a vrednosti liste casova koji se odrzavaju tog d-
 *    ana. Ove liste su sortirane po vremenu pocetka casa, a takodje
 *    implementirati i potrebne metode za pristup i izmenu podataka.
 *
 * 4) Pitati korisnika za koju godinu IT smera zeli raspored. Ident-
 *    ifikatori Google kalendara za odgovarajuce godine su:
 *        1 godina: g3khre7jrsih1idp5b5ahgm1f8
 *        2 godina: hu93vkklcv692mikqvm17scnv4
 *        3 godina: 6ovsf0fqb19q10s271b9e59ttc
 *        4 godina: a730slcmbr9c6dii94j9pbdir4
 *
 * 5) Ucitati odgovarajuci raspored casova, izvuci neophodne podatke
 *    u instance klase "Cas" i smestiti ih u jednu instancu klase R-
 *    aspored.
 *
 * 6) Pitati korisnika za koji dan u nedelji zeli raspored i ispisa-
 *    ti casove koji se odrzavaju tog dana.
 *
 * Metod za ucitavanje rasporeda sa interneta je vec dat, kao i kako
 * treba da izgleda URL za Google kalendar sa datim identifikatorom. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import domaci_zadaci.z02_p01.util.EditDistance;
import domaci_zadaci.z02_p01.raspored.Cas;
import domaci_zadaci.z02_p01.raspored.Raspored;
import domaci_zadaci.z02_p01.time.Dani;

public class Main {
	
	
	private static Raspored r;
	
	
	private static Map<Integer, Set<String>> moguciOdgovori;
	private static Map<Integer, String> identifikatori;
	private static Set<String> nazivi = new TreeSet<String>();
	
	
	static {
		
		identifikatori = new HashMap<Integer, String>();
		
		identifikatori.put(1, "g3khre7jrsih1idp5b5ahgm1f8");
		identifikatori.put(2, "hu93vkklcv692mikqvm17scnv4");
		identifikatori.put(3, "6ovsf0fqb19q10s271b9e59ttc");
		identifikatori.put(4, "a730slcmbr9c6dii94j9pbdir4");
		
		moguciOdgovori = new HashMap<Integer, Set<String>>();
		
		moguciOdgovori.put(1, new HashSet<String>());
		moguciOdgovori.get(1).addAll(Arrays.asList(new String[] { "1", "1.", "i", "prva", "jedan" }));
		
		moguciOdgovori.put(2, new HashSet<String>());
		moguciOdgovori.get(2).addAll(Arrays.asList(new String[] { "2", "2.", "ii", "druga", "dva" }));
		
		moguciOdgovori.put(3, new HashSet<String>());
		moguciOdgovori.get(3).addAll(Arrays.asList(new String[] { "3", "4.", "iii", "treca", "tri" }));
		
		moguciOdgovori.put(4, new HashSet<String>());
		moguciOdgovori.get(4).addAll(Arrays.asList(new String[] { "4", "4.", "iv", "cetvrta", "cetiri" }));
	}
	

	public static void main(String[] args) throws IOException {
		
		
		try (
				Scanner sc = new Scanner(System.in);
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		) {
			
			// ucitavanje rasporeda za trazenu godinu i prikaz za trazeni dan
			r = Raspored.hm(check(readFile(getCalendar(getId(sc)))));
			r.stampajZaDan(getDan(sc));
			
			// pretraga predavanja po imenu predmeta sa tolerancijom gresaka
			System.out.print("Unesite naziv predmeta: ");
			String unos = in.readLine();
			print(unos);
			
		} catch (IOException e) {
			System.err.println("Greska prilikom citanja fajla -> " + e.getMessage());
		}
	}
	
	
	public static String getId(Scanner sc) {
		
		String identifikator = null;
		
		do {
			System.out.print("Godina za koju zelite raspored : ");
			String op = sc.nextLine();
			
			for (int i = 1; i < 5; i++) 
				if (moguciOdgovori.get(i).contains(op.toLowerCase()))
					identifikator = identifikatori.get(i);
			
			if (identifikator == null) System.out.println("Pogresan unos!");
		} while (identifikatori == null);
		
		return identifikator;
	}
	
	
	public static Dani getDan(Scanner sc) {
		
		int op = 0;
		
		do {
			System.out.print("Redni broj dana (1 - 7) za koji zelite raspored : ");
			op = sc.nextInt();
			
			if (!(1 <= op && op <= 7)) 
				System.out.println("Pogresan unos!");
		} while (!(1 <= op && op <= 7));
		
		switch (op) {
			case 1:  return Dani.PON;
			case 2:  return Dani.UTO;
			case 3:  return Dani.SRE;
			case 4:  return Dani.CET;
			case 5:  return Dani.PET;
			case 6:  return Dani.SUB;
			case 7:  return Dani.NED;
			default: return Dani.PON;
		}
	}
	
	
	public static String getCalendar(String id) throws IOException {
		return readURL(new URL(
				"https://calendar.google.com/calendar/ical/" + id +
				"%40group.calendar.google.com/public/basic.ics") );
	}
	

	private static String readURL(URL url) throws IOException {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
			StringBuilder text = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
			return text.toString();
		}
	}
	

	private static String[] readFile(String file) throws IOException {
		
		List<String> lines = new ArrayList<>();
		lines.addAll(Arrays.asList(file.split("\n")));
		
		return lines.toArray(new String[lines.size()]);
	}
	
	
	private static Map<Dani, TreeSet<Cas>> check(String[] lines) {
		
		Map<Dani, TreeSet<Cas>> out = new HashMap<Dani, TreeSet<Cas>>();
		
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
					Cas item = new Cas(DTSTART, DTEND, RRULE, SUMMARY);
				
					if (!out.containsKey(item.dan())) 
						out.put(item.dan(), new TreeSet<Cas>());

					out.get(item.dan()).add(item);
					nazivi.add(item.predmet().toLowerCase());
					
				} catch (NumberFormatException e) {
					System.err.printf("Linija %05d -> %s \n", (i + count), e.getMessage());
				} catch (IllegalArgumentException e) {
					System.err.printf("Linija %05d -> %s \n", (i + count), e.getMessage());
				}
				
				i += count;
			}
		}
		
		return out;
	}
	
	
	private static void print(String name) {

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
		 * 
		 * UPDATE : dodao sam poredjenje svake reci naziva predmeta sa uno-
		 * som, pa se uzima najmanja pronadjena string distanca za citav p-
		 * redmet.
		 */ 
		
		name = name.toLowerCase();
		
		Iterator<Cas> it = r.iterator();
		
		Map<Cas, Integer> m = new HashMap<Cas, Integer>();
		
		while (it.hasNext()) {
			
			Cas curr = it.next();
			if (!curr.tip().equalsIgnoreCase("p")) continue;
			
			String[] tokens = curr.predmet().toLowerCase().split("\\s|-");
			
			Map<String, Integer> temp = new HashMap<String, Integer>();
			
			for (String s : tokens) temp.put(s, new EditDistance(name, s).getDistance());
			
			List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(temp.entrySet());
			Collections.sort(listOfEntries, (e1, e2) -> e1.getValue() - e2.getValue());
			
			m.put(curr, listOfEntries.get(0).getValue());
		}
		
		List<Entry<Cas, Integer>> listOfEntries = new ArrayList<Entry<Cas, Integer>>(m.entrySet());
		Collections.sort(listOfEntries, (e1, e2) -> e1.getValue() - e2.getValue());
		
		if (listOfEntries.get(0).getValue() == 0) {
			System.out.println(listOfEntries.get(0).getKey());
		} else {
			System.out.println("Trazeni predmet nije pronadjen; prikaz tri najpribliznija rezultata:");
			for (int i = 0; i < 3; i++) System.out.println(listOfEntries.get(i).getKey());
		}
	}
}
