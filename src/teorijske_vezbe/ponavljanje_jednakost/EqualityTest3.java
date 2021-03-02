package teorijske_vezbe.ponavljanje_jednakost;

import static org.svetovid.Svetovid.out;

import java.util.HashSet;
import java.util.Set;

/**
 * Jednakost objekata u Javi, test 3, ispravno resenje.
 * 
 * @author Dejan Mitrovic
 */

class Number3 {

	private int n;

	// Koristimo poseban "id" objekta cisto da bismo u konzoli videli koji metod je kada pozvan
	private String id;

	public Number3(int n, String id) {
		this.n = n;
		this.id = id;
	}

	@Override
	public String toString() {
		return n + "(" + id + ")";
	}

	/**
	 * Pravilo 1: kada vam je potrebno utvrdjivanje jednakosti objekata,
	 * implementirajte metod "equals"
	 * 
	 * Pravilo 2: kada implementirate metod "equals", implementirajte i
	 * metod "hashCode"
	 * 
	 * Ukoliko objekte ubacujete u (Linked)HashSet, koristite ih kao kljuceve u
	 * (Linked)HashMap ili cete pozivati metode "contains", "indexOf" itd. klasa
	 * Array/LinkedList, implementacija metoda "equals" i "hashCode" je
	 * obavezna!
	 */

	@Override
	public int hashCode() {
		out.println("In hashCode() of " + id);
		// (manje-vise) standardan pristup:
		// 1. odaberite neki prost broj
		// 2. za svako polje "p" koje identifikuje objekat:
		// - result = prime * result + p
		// - ukoliko je "p" objekat, tada
		// - result = prime * result + p.hashCode()
		// i nadajte se da je onaj koji je implementirao klasu objekta "p"
		// bio dovoljno pametan da implementira metod "hashCode"
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Number3 other = (Number3) obj;
		out.printf("In equals() of %s, comparing with %s", id, other.id);
		if (n != other.n) {
			return false;
		}
		return true;
	}
}

public class EqualityTest3 {

	public static void main(String[] args) {

		Set<Number3> set = new HashSet<Number3>();

		out.println("Adding a...");
		set.add(new Number3(5, "a"));
		out.println("Added a.\n");

		out.println("Adding b...");
		set.add(new Number3(5, "b"));
		out.println("Added b.\n");

		out.println("Adding c...");
		set.add(new Number3(6, "c"));
		out.println("Added c.\n");

		out.println(set);

	}
}
