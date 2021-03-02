package teorijske_vezbe.ponavljanje_kolekcije_spa2.knjige;

import java.util.Arrays;
import java.util.Comparator;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;
import org.svetovid.io.SvetovidWriter;

// Klasa koja opisuje jednu knjigu
class Knjiga implements Comparable<Knjiga> {

	public final int id;
	public final String naslov;
	public final String autor;

	public Knjiga(int id, String naslov, String autor) {
		this.id = id;
		this.naslov = naslov;
		this.autor = autor;
	}

	public String toString() {
		return id + " " + autor + ": " + naslov;
	}

	public int compareTo(Knjiga that) {
		return this.id - that.id;
	}
}

// Glani program
public class Biblioteka {

	public static void main(String[] arguments) {

		// Ucitavamo knjige iz fajla
		String imeFajla = Svetovid.in.readLine("Unesite ime fajla sa knjigama:");
		Knjiga[] niz = ucitajKnjige(imeFajla);

		// Ako nismo ucitali knjige, zavrsavamo program sa return
		if (niz == null) {
			Svetovid.out.println("Ne moze da se cita iz fajla " + imeFajla + ".");
			return;
		}

		// Pitamo korisnika za kriterijum sortiranja
		Svetovid.out.println("Opcija 1: standardan sort bez komparatora");
		Svetovid.out.println("Opcija 2: sort sa opadajucim id knjige");
		Svetovid.out.println("Opcija 3: sort po naslovu");
		Svetovid.out.println("Opcija 4: sort po autoru, a zatim po naslovu");
		Svetovid.out.println("Za ostale opcije niz nece biti sortiran");
		Svetovid.out.print("Unesite opciju 1-4:");
		int opcija = Svetovid.in.readInt();

		// Sortiramo po odabranom kriterijumu
		switch (opcija) {
		case 1:
			Arrays.sort(niz);
			break;
		case 2:
			sortirajNiz(niz, new ObrnutiKomparator(new PrirodniKomparator()));
			break;
		case 3:
			sortirajNiz(niz, new KomparatorPoNaslovu());
			break;
		case 4:
			sortirajNiz(niz, new KomparatorPoAutoruIPotomNaslovu());
			break;
		default:
			Svetovid.out.println("Pogresna opcija! Niz nece biti sortiran.");
		}

		// Stampamo niz
		stampajKnjige("Niz knjiga", niz);

		// Sacuvamo (sortirani) niz u novi fajl
		imeFajla = Svetovid.in.readLine("Unesite ime fajla za snimanje: ");
		boolean sacuvano = sacuvajKnjige(imeFajla, niz);

		// Ako nismo sacuvali knjige, prijavimo to korisniku
		if (!sacuvano) {
			Svetovid.out.println("Ne moze da se pise u fajl " + imeFajla + ".");
		}

	}

	public static Knjiga[] ucitajKnjige(String imeFajla) {

		// Ako ne mozemo da otvorimo fajl, ne ucitavamo knjige
		if (!Svetovid.testIn(imeFajla)) {
			return null;
		}

		// Ako mozemo, napravimo precicu 'in' da ne bi morali da
		// svaki put kuvamo 'Svetovid.in(imeFajla)'
		SvetovidReader in = Svetovid.in(imeFajla);

		// Koliko ima knjiga u fajlu?
		int br = in.readInt();

		// Napravimo niz odgovarajuce velicine
		Knjiga[] rez = new Knjiga[br];

		// Ucitamo knjige
		for (int i = 0; i < br; i++) {

			// Ucitamo podatke o knjizi
			int id = in.readInt();
			String naslov = in.readLine();
			String autor = in.readLine();

			// Napravimo knjigu
			Knjiga knjiga = new Knjiga(id, naslov, autor);

			// Dodamo knjigu u niz
			rez[i] = knjiga;

		}

		// Zatvorimo fajl
		in.close();

		// Vratimo ucitani niz
		return rez;

	}

	public static void stampajKnjige(String naslov, Knjiga[] niz) {

		// Odstampamo naslov
		Svetovid.out.println();
		Svetovid.out.println(naslov);
		Svetovid.out.println();

		// I potom sve knjige redom
		for (Knjiga knjiga : niz) {
			Svetovid.out.println(knjiga);
		}

	}

	public static boolean sacuvajKnjige(String imeFajla, Knjiga[] niz) {

		// Ako ne mozemo da otvorimo fajl, ne upisujemo knjige
		if (!Svetovid.testOut(imeFajla)) {
			return false;
		}

		// Ako mozemo, napravimo precicu 'out' da ne bi morali da
		// svaki put kuvamo 'Svetovid.out(imeFajla)'
		SvetovidWriter fajl = Svetovid.out(imeFajla);

		// Upisemo ukupan broj knjiga
		fajl.println(niz.length);

		// I potom podatke za svaku knjigu
		for (Knjiga k : niz) {
			fajl.println(k.id);
			fajl.println(k.naslov);
			fajl.println(k.autor);
		}

		// Zatvorimo fajl
		Svetovid.closeOut(imeFajla);

		// Javimo da smo uspesno sacuvali knjige
		return true;

	}

	public static void sortirajNiz(Knjiga[] niz, Comparator<Knjiga> c) {
		Knjiga tmp;
		for (int i = niz.length - 1; i > 0; i--)
			for (int j = 0; j < i; j++)
				if (c.compare(niz[j], niz[i]) > 0) {
					tmp = niz[j];
					niz[j] = niz[i];
					niz[i] = tmp;
				}
	}

}

// Koparator koji poredi knjige po naslovu
class KomparatorPoNaslovu implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga knjiga1, Knjiga knjiga2) {
		int rezultat = knjiga1.naslov.compareTo(knjiga2.naslov);
		return rezultat;
	}
}

// Koparator koji poredi knjige po naslovu
class KomparatorPoAutoru implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga knjiga1, Knjiga knjiga2) {
		int rezultat = knjiga1.autor.compareTo(knjiga2.autor);
		return rezultat;
	}
}

// Koparator koji koristi prirodni poredak, tj. samo poziva compareTo()
class PrirodniKomparator implements Comparator<Knjiga> {

	@Override
	public int compare(Knjiga knjiga1, Knjiga knjiga2) {
		return knjiga1.compareTo(knjiga2);
	}
}

// Komparator koji daje obrnuti poredak u odnosu na originalni komparator
class ObrnutiKomparator implements Comparator<Knjiga> {

	private final Comparator<Knjiga> originalni;

	public ObrnutiKomparator(Comparator<Knjiga> originalni) {
		this.originalni = originalni;
	}

	@Override
	public int compare(Knjiga knjiga1, Knjiga knjiga2) {

		return -originalni.compare(knjiga1, knjiga2);
	}
}

// Komparator koji prvo poredi pomocu primarnog komparatora a potom po
// sekundarnom
class KompozitniKomparator implements Comparator<Knjiga> {

	private final Comparator<Knjiga> primarni;
	private final Comparator<Knjiga> sekundarni;

	public KompozitniKomparator(Comparator<Knjiga> primarni,
			Comparator<Knjiga> sekundarni) {
		this.primarni = primarni;
		this.sekundarni = sekundarni;
	}

	@Override
	public int compare(Knjiga knjiga1, Knjiga knjiga2) {
		int rezultat = primarni.compare(knjiga1, knjiga2);
		if (rezultat == 0) {
			rezultat = sekundarni.compare(knjiga1, knjiga2);
		}
		return rezultat;
	}
}

// Komparator koji poredi po autoru, a za iste autore, po naslovu
class KomparatorPoAutoruIPotomNaslovu extends KompozitniKomparator {

	public KomparatorPoAutoruIPotomNaslovu() {
		super(new KomparatorPoAutoru(), new KomparatorPoNaslovu());
	}
}
