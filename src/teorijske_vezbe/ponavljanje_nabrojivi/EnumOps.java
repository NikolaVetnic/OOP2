package teorijske_vezbe.ponavljanje_nabrojivi;

import static org.svetovid.Svetovid.in;
import static org.svetovid.Svetovid.out;

import java.io.IOException;

/**
 * Osnovne operacije za rad sa nabrojivim tipovima podataka.
 * 
 * @author Dejan Mitrovic
 * @author Ivan Pribela
 */

enum Fruit {
	APPLE, ORANGE, PEAR
}

public class EnumOps {

	public static void main(String[] args) throws IOException {

		// Fruit.values() vraca niz svih konstanti (u redosledu u kom su navedene gore)
		out.print("Available fruit: ");
		for (Fruit f : Fruit.values()) {
			out.print(f + " "); // poziva metod toString()
		}

		out.println("\n");

		// ordinal() - redni broj konstante
		out.print("Ordinal values: ");
		for (Fruit f : Fruit.values())
			out.printf("%s: %d; ", f.toString(), f.ordinal());

		out.println("\n");

		// valueOf(String name) - vraca konstantu sa imenom "name", baca izuzetak ukoliko konstanta ne postoji
		String name = in.readLine("Field name?");
		Fruit f;
		try {
			f = Fruit.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException ex) {
			out.println("No such fruit - " + name);
			return;
		}

		// Nabrojivi tip se moze koristiti u switch naredbi
		// u 'case' grani je dozvoljeno navesti samo ime konstante
		// u svim ostalim situacijama mora oblik Tip.Konstanta (npr. Fruit.APPLE)
		switch (f) {
		case APPLE:
			out.println("It's an apple");
			break;
		case ORANGE:
			out.println("It's an orange");
			break;
		case PEAR:
			out.println("It's a pear");
			break;
		}

	}
}
