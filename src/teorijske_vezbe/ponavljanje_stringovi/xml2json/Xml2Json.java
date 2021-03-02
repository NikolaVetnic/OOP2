package teorijske_vezbe.ponavljanje_stringovi.xml2json;

import static org.svetovid.Svetovid.in;
import static org.svetovid.Svetovid.out;

/**
 * (Veoma) Rudimentaran XML --> JSON konverter.
 *
 * @author Dejan Mitrovic
 */
public class Xml2Json {

	public static void main(String[] args) {

		String str = in(Xml2Json.class.getResource("person.xml").toString()).readAll();
		StringBuilder xml = new StringBuilder(str);

		// Prvo ukloni zatvorene tagove
		int i = xml.indexOf("</");
		while (i >= 0) {
			int j = xml.indexOf(">", i + 2);
			xml.replace(i, j + 1, "}"); // <Name>John</Name> --> <Name>John}
			// Sledeci zatvoreni tag
			i = xml.indexOf("</", i + 1);
		}

		// Ukloni otvorene tagove
		i = xml.indexOf("<");
		while (i >= 0) {
			xml.setCharAt(i, '{'); // <Name>John} --> {Name>John}
			i = xml.indexOf(">", i + 1);
			xml.setCharAt(i, ':'); // {Name>John} --> {Name:John}
			// Sledeci otvoreni tag
			i = xml.indexOf("<", i + 1);
		}

		out.println(xml);
	}
}
