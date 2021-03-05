package domaci_zadaci.z01_p01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RasporedProgram1 {
	
	
	private static Set<Item> predmeti = new TreeSet<Item>();
	

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
					predmeti.add(new Item(DTSTART, DTEND, RRULE, SUMMARY));
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
		
		Item out = null;
		Iterator<Item> it = predmeti.iterator();
		
		while (it.hasNext()) {
			Item curr = it.next();
			
			if (curr.predmet().toLowerCase().contains(name.toLowerCase()) && curr.tip().equalsIgnoreCase("p")) {
				out = curr;
				break;
			}
		}
		
		if (out != null)
			System.out.println(out);
		else
			System.out.println("Predavanja iz unetog predmeta ne postoje u rasporedu.");
	}
}
