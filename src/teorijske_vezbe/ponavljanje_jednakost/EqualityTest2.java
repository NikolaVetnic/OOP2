package teorijske_vezbe.ponavljanje_jednakost;

import static org.svetovid.Svetovid.out;

import java.util.HashSet;
import java.util.Set;

/**
 * Jednakost objekata u Javi, test 2, neispravno resenje.
 * 
 * @author Dejan Mitrovic
 */

class Number2 {

	private int n;

	public Number2(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return n + "";
	}

	@Override
	// Standardan pristup za poredjenje objekata u Javi
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
		Number2 other = (Number2) obj;
		if (n != other.n) {
			return false;
		}
		return true;
	}
}

public class EqualityTest2 {
	public static void main(String[] args) {
		Set<Number2> set = new HashSet<Number2>();
		set.add(new Number2(5));
		set.add(new Number2(5));
		out.println(set);
	}
}
