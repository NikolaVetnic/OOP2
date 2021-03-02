package teorijske_vezbe.ponavljanje_stringovi;

import static org.svetovid.Svetovid.out;

/**
 * Najznacajnije operacije klase StringBuilder koje ne postoje u klasi String.
 *
 * @author Dejan Mitrovic
 */
public class StrBuilderOps {

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder("The Quick Brown Fox Jumps Over The Lazy Dog.");

		out.print("Dodajem \"OOP2\" na kraj: ");
		sb.append("OOP2");
		out.println(sb);

		out.print("Sad brisem to sto sam dodao: ");
		sb.delete(44, sb.length());
		out.println(sb);

		out.print("Dodajem \"Fast/\" pre reci \"Quick\": ");
		int n = sb.indexOf("Quick");
		sb.insert(n, "Fast/");
		out.println(sb);

		out.print("Pretvaram mala slova u velika, i obrnuto: ");
		for (int i = 0; i < sb.length(); i++) {
			char ch = sb.charAt(i);
			sb.setCharAt(i, Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch));
		}
		out.println(sb);

		out.print("Obrcem string: ");
		sb.reverse();
		out.println(sb);

	}
}
