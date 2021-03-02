package teorijske_vezbe.ponavljanje_jednakost;

import static org.svetovid.Svetovid.out;

import java.util.HashSet;
import java.util.Set;

/**
 * Jednakost objekata u Javi, test 1, neispravno resenje.
 * 
 * @author Dejan Mitrovic
 */

// Objekte ove klase cemo ubacivati u kolekciju
class Number1 {

	private int n;

	public Number1(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return n + "";
	}
}

public class EqualityTest1 {

	public static void main(String[] args) {
		// Sadrzaj skupa ce biti [5, 5], jer nismo implementirali mehanizam
		// za poredjenje objekata klase Number1. po default-u, metod "equals"
		// poredi reference
		Set<Number1> set = new HashSet<>();
		set.add(new Number1(5));
		set.add(new Number1(5));
		out.println(set);
	}
}
