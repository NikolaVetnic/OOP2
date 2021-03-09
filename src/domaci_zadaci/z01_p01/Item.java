package domaci_zadaci.z01_p01;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Item implements Comparable<Item> {
	
	
	static class Time {
		
		int h, m;
		
		public Time(int h, int m) {
			this.h = h;
			this.m = m;
		}
		
		public int h() { return h; }
		public int m() { return m; }
		
		public String toString() {
			return String.format("%02d:%02d", h, m);
		}
	}
	
	
	static enum Dani { PON, UTO, SRE, CET, PET, SUB, NED }
	
	
	static final String[] days = { "MO", "TU", "WE", "TH", "FR", "SA", "SU" };
	
	
	static final Set<String> tipovi = new HashSet<String>();
	
	
	static {
		tipovi.add("P");
		tipovi.add("V");
		tipovi.add("PV");
		tipovi.add("P+V");
		tipovi.add("RV");
	}
	

	private Time start;
	private Time end;
	private Item.Dani dan;
	private String predmet;
	private String nastavnik;
	private String tip;
	private String sala;
	
	
	public Item() { }
	
	
	public Item(String DTSTART, String DTEND, String RRULE, String SUMMARY) {
		this.start = parseTime(DTSTART);
		this.end = parseTime(DTEND);
		this.dan = parseDay(RRULE);
		parseSummary(SUMMARY);
	}
	
	
	private Time parseTime(String in) throws NumberFormatException {
		
		String tag = in.split(";")[0].trim();
		
		String hh = in.substring(in.length() - 6, in.length() - 4);
		String mm = in.substring(in.length() - 4, in.length() - 2);
		
		if (!isInteger(hh) || !isInteger(mm))
			throw new NumberFormatException("Greska u osobini " + tag);
		
		int h = Integer.parseInt(hh),
			m = Integer.parseInt(mm);
		
		if (h < 0 || h >= 24 || m < 0 || m >= 60)
			throw new NumberFormatException("Greska u osobini " + tag);
		
		return new Time(h, m);
	}
	
	
	private static boolean isInteger(String num) {
		
		if (num == null) return false;
		
		try {
			int i = Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		
		return true;
	}


	private Item.Dani parseDay(String in) throws IllegalArgumentException {
		
		if (in == null)
			throw new IllegalArgumentException("Greska u osobini RRULE -> nedostaje informacija");
		
		String day = in.substring(in.length() - 2, in.length());
		
		Item.Dani[] dani = Item.Dani.values();
		
		for (int i = 0; i < dani.length; i++)
			if (day.equals(days[i])) return dani[i];
		
		throw new IllegalArgumentException("Greska u osobini RRULE -> " + in);
	}
	
	
	private void parseSummary(String in) throws IllegalArgumentException {
		
		String[] t0 = in.split(":");
		String[] tokens = t0[1].split("\\,");
		
		if (tokens.length != 4)
			throw new IllegalArgumentException("Greska u osobini SUMMARY -> " + in);
		
		tokens[0] = tokens[0].trim().substring(0, tokens[0].length() - 1).trim();
		tokens[1] = tokens[1].trim().substring(0, tokens[1].length() - 2).trim();
		tokens[2] = tokens[2].trim().substring(1, tokens[2].length() - 3).trim();
		tokens[3] = tokens[3].trim();
		
		if (!tipovi.contains(tokens[2]))
			throw new IllegalArgumentException("Greska u osobini SUMMARY -> " + in);
		
		for (String s : tokens) if (s.trim().isEmpty())
			throw new IllegalArgumentException("Greska u osobini SUMMARY -> " + in);
		
		setRest(tokens);
	}
	
	
	private void setRest(String[] tokens) {
		this.predmet 	= tokens[0];
		this.nastavnik 	= tokens[1];
		this.tip 		= tokens[2];
		this.sala 		= tokens[3];
	}


	public Time start() 		{ return start; 	}
	public Time end() 			{ return end; 		}
	public Item.Dani dan() 		{ return dan; 		}
	public String predmet() 	{ return predmet; 	}
	public String nastavnik() 	{ return nastavnik; }
	public String tip() 		{ return tip; 		}
	public String sala() 		{ return sala; 		}


	public String toString() {
		return String.format("%50s %30s %5s %20s %3s [ %02d:%02d - %02d:%02d ]", predmet, nastavnik, tip, sala, dan, start.h(), start.m(), end.h(), end.m());
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		
		if (o == null)
			return false;
		
		if (this.getClass() != o.getClass())
			return false;
		
		Item i = (Item) o;
		
		if (!Objects.equals(start(), i.start()) 		||
			!Objects.equals(end(), i.end())				||
			!Objects.equals(dan(), i.dan())				||
			!Objects.equals(predmet(), i.predmet())		||
			!Objects.equals(nastavnik(), i.nastavnik())	||
			!Objects.equals(tip(), i.tip())				||
			!Objects.equals(sala(), i.sala())			 )
			return false;
		else
			return true;
	}
	
	@Override
	public int hashCode() {
		
		return 	7  * toMinutes(start()) 	+ 
				11 * toMinutes(end()) 		+ 
				13 * dan().ordinal() 		+
				17 * predmet().hashCode() 	+ 
				19 * nastavnik().hashCode() + 
				23 * tip().hashCode()		+
				29 * sala().hashCode()		;
	}


	private int toMinutes(Time t) {
		return t.h() * 60 + t.m();
	}


	@Override
	public int compareTo(Item i) {
		return predmet.compareTo(i.predmet());
	}
}
