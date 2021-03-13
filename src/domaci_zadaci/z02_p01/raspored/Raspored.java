package domaci_zadaci.z02_p01.raspored;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import domaci_zadaci.z02_p01.time.Dani;
import domaci_zadaci.z02_p01.time.Time;

public class Raspored implements Iterable<Cas> {

	
	private Map<Dani, TreeSet<Cas>> raspored;
	
	
	// konstruktori
	
	
	private Raspored(Map<Dani, TreeSet<Cas>> raspored) {
		this.raspored = raspored;
	}
	
	
	public static Raspored hm(Map<Dani, TreeSet<Cas>> raspored) {
		return new Raspored(raspored);
	}
	
	
	// pristup
	
	
	public TreeSet<Cas> zaDan(Dani d) 	{ 
		return raspored.get(d); 	
	}
	
	
	public LinkedList<Cas> listaCasova() {
		
		LinkedList<Cas> l = new LinkedList<Cas>();
		
		for (Dani d : Dani.values())
			if (raspored.get(d) != null) l.addAll(raspored.get(d));
		
		return l;
	}
	
	
	// izmena
	
	
	public boolean dodajCas(Cas c) {
		
		if (raspored.get(c.dan()) == null)
			raspored.put(c.dan(), new TreeSet<Cas>());
		
		raspored.get(c.dan()).add(c);
		
		return true;
	}
	
	
	private Cas pronadjiCas(String predmet) {
		
		for (Cas c : listaCasova())
			if (c.predmet().equalsIgnoreCase(predmet))
				return c;
		
		return null;
	}
	
	
	public boolean setStart(String predmet, int h, int m) { 
		
		Cas c;
		if ((c = pronadjiCas(predmet)) == null)
			return false;
		
		c.setStart(new Time(h, m));
		
		return true;
	}
	
	
	public boolean setEnd(String predmet, int h, int m) { 
		
		Cas c;
		if ((c = pronadjiCas(predmet)) == null)
			return false;
		
		c.setEnd(new Time(h, m));
		
		return true;
	}
	
	
	public boolean setDan(String predmet, int ord) { 
		
		Cas c;
		if ((c = pronadjiCas(predmet)) == null)
			return false;
		
		switch (ord) {
			case 1:  c.setDan(Dani.PON); return true;
			case 2:  c.setDan(Dani.UTO); return true;
			case 3:  c.setDan(Dani.SRE); return true;
			case 4:  c.setDan(Dani.CET); return true;
			case 5:  c.setDan(Dani.PET); return true;
			case 6:  c.setDan(Dani.SUB); return true;
			case 7:  c.setDan(Dani.NED); return true;
			default: return false;
		}
	}
	
	
	public boolean setPredmet(String predmet, String p) { 
		
		Cas c;
		if ((c = pronadjiCas(predmet)) == null)
			return false;
		
		c.setPredmet(p);
		
		return true;
	}
	
	
	public boolean setNastavnik(String predmet, String n) { 
		
		Cas c;
		if ((c = pronadjiCas(predmet)) == null)
			return false;
		
		c.setNastavnik(n);
		
		return true;
	}
	
	
	public boolean setTip(String predmet, String t) { 
		
		Cas c;
		if ((c = pronadjiCas(predmet)) == null)
			return false;
		
		c.setTip(t);
		
		return true;
	}
	
	
	public boolean setSala(String predmet, String s) { 
		
		Cas c;
		if ((c = pronadjiCas(predmet)) == null)
			return false;
		
		c.setSala(s);
		
		return true;
	}
	
	
	// prikaz
	
	
	public void stampaj() {
		
		for (Dani d : Dani.values())
			stampajZaDan(d);
	}
	
	
	public void stampajZaDan(Dani d) {
		
		if (raspored.get(d) != null)
			for (Cas c : raspored.get(d)) System.out.println(c);
		else
			System.out.println("Raspored je prazan za dan " + d + ".");

		System.out.println();
	}
	
	
	// ostalo


	@Override
	public Iterator<Cas> iterator() {
		return listaCasova().iterator();
	}
	

	public void testHash() {
		
		Set<Cas> casovi = new HashSet<Cas>();
		Set<Dani> keys = raspored.keySet();
		
		Iterator<Dani> it0 = keys.iterator();
		while (it0.hasNext()) casovi.addAll(raspored.get(it0.next()));
		
		Set<Integer> hashCodeSet = new HashSet<Integer>();
		
		Iterator<Cas> it1 = casovi.iterator();
		while (it1.hasNext()) hashCodeSet.add(it1.next().hashCode());
		
		System.out.println("Broj stavki u prosledjenom spisku / broj razlicitih hash vrednosti: " 
				+ casovi.size() + " / " + hashCodeSet.size());
	}
}
