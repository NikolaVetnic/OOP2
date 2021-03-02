package teorijske_vezbe.ponavljanje_stringovi;

import static org.svetovid.Svetovid.out;

import java.util.Arrays;

/**
 * Najznacajnije operacije klase String.
 *
 * @author Dejan Mitrovic
 */
public class StringOps {

	public static void main(String[] args) {

		final String str = "The Quick Brown Fox Jumps Over The Lazy Dog.";

		out.println("Duzina: " + str.length());
		out.println("Karakter na poziciji 1: " + str.charAt(1));
		out.println("Indeks pod-reci \"Quick\": " + str.indexOf("Quick")); // -1 ukoliko ne postoji
		out.println("Indeks poslednje praznine: " + str.lastIndexOf(" ")); // efikasnije: ' '
		out.println("Da li je prefiks \"The\"? " + str.startsWith("The"));
		out.println("Da li je sufiks \"Fox\"? " + str.endsWith("Fox"));

		out.println();
		out.println("Pod-string od karaktera na poziciji 5: " + str.substring(5)); // do kraja
		out.println("Pod-string [4, 6): " + str.substring(4, 6));
		out.println("Velikim slovima: " + str.toUpperCase()); // malim slovima: str.toLowerCase()

		// ubaci 'i' umesto svih samoglasnika
		String str2 = str;
		char[] vowels = { 'a', 'e', 'o', 'u' };
		for (char ch : vowels) {
			str2 = str2.replace(ch, 'i'); // zamenjuje SVE pojave prvog parametra drugim parametrom
		}
		out.println("Samo samoglasnik 'i': " + str2);

		out.println();
		izdeli("a,b,c,d", ",");
		izdeli("abc123x123y", "123");
		izdeli("abcd", ",");
		izdeli("a,,c,d", ",");
		izdeli(",,c,d", ",");
		izdeli("a,b,,", ",");
		izdeli("123", "123");

		out.println();
		// brise nevidljive karaktere s krajeva stringa:
		str2 = "  neki tekst \t\n";
		out.println("Trimujem sledeci string:\n\"" + str2 + "\"");
		out.println("-------------------------\nRezultat: " + str2.trim());

	}

	private static void izdeli(String str, String separator) {
		String[] elems = str.split(separator);
		out.printf("\"%s\".split(%s) --> %s\n", str, separator, Arrays.toString(elems));
	}
}
