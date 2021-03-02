package teorijske_vezbe.ponavljanje_sortiranje;

import static org.svetovid.Svetovid.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Posto cemo objekte klase Person sortirati, neophodno je da implementiramo
 * interfejs Comparable.
 *
 * @author Dejan Mitrovic
 */
class Person implements Comparable<Person> {
	private String ime;

	public Person(String ime) {
		this.ime = ime;
	}

	@Override
	public int compareTo(Person o) {
		return ime.compareTo(o.ime);
	}

	@Override
	public String toString() {
		return ime;
	}
}

public class CustomObjSort {

	public static void main(String[] args) {

		// Sortiranje pomocu skupa.
		// Elementi koje ubacujemo u TreeSet moraju implementirati Comparable,
		// ili konstruktoru klase mora biti prosledjen Comparator (videti primer StringSort)
		Set<Person> set = new TreeSet<>();
		set.add(new Person("pera"));
		set.add(new Person("aca"));
		set.add(new Person("zika"));
		out.println(set);

		// Sortiranje listi
		List<Person> list = new ArrayList<>();
		list.add(new Person("pera"));
		list.add(new Person("aca"));
		list.add(new Person("zika"));

		// Slicno kao kod skupa, elementi liste moraju implementirati Comparable,
		// ili se kao drugi parametar mora zadati Comparator (videti primer StringSort)
		Collections.sort(list);
		out.println(list);

	}
}
