package teorijske_vezbe.ponavljanje_sortiranje;

import static org.svetovid.Svetovid.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Po default-u, stringovi se sortiraju rastuce. Ako zelimo drugacije ponasanje,
 * moramo implementirati Comparator, u posebnoj klasi, jer klasu String ne
 * mozemo menjati.
 *
 * @author Dejan Mitrovic
 */
class StringComp implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		return -s1.compareTo(s2);
	}
}

public class StringSort {

	public static void main(String[] args) {

		// Sortiranje pomocu skupa.
		// U konstruktoru zadajemo Comparator objekat koji ce skup pozivati prilikom sortiranja stringova
		Set<String> set = new TreeSet<>(new StringComp());
		set.add("pera");
		set.add("aca");
		set.add("zika");
		out.println(set);

		// Sortiranje listi
		List<String> list = new ArrayList<>();
		list.add("pera");
		list.add("aca");
		list.add("zika");
		Collections.sort(list, new StringComp());
		out.println(list);

	}
}
