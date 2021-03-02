package teorijske_vezbe.ponavljanje_kolekcije_spa2.automobili;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import org.svetovid.Svetovid;
import org.svetovid.io.SvetovidReader;

// Tip podataka za predstavljanje automobila
class Automobil implements Comparable<Automobil> {

    // Osobine koje se ne mogu menjati
    public final String model;
    public final int godiste;
    public final Motor motor;

    // Osobine koje su slobodno izmenljive
    public String boja;

    // Osobine koje su zasticene
    private int kilometraza;

    public Automobil(String model, int godiste, Motor motor, String boja, int kilometraza) {
        this.model = model;
        this.godiste = godiste;
        this.motor = motor;
        this.boja = boja;
        this.kilometraza = kilometraza;
    }

    // Kolometraza moze slobodno da se cita
    public int getKilometraza() {
        return kilometraza;
    }

    // Prilikom promene, moze samo da se poveca
    public void vozi(int duzina) {

    	// Ako neko zeli da vrati kilometrazu unazad
    	// prijavimo da parametar nije dobar
    	if (duzina <= 0) {
    		throw new IllegalArgumentException();
    	}

    	// Inace samo povecamo vrednost
    	this.kilometraza = this.kilometraza + duzina;

    }

    @Override
    public String toString() {

        // Tekstualna reprezentacija automobila oblika:
        // Zastava 101 (1999) Crvena boja, presao 231000 km
        return model + " (" + godiste + ") " + boja + " boja, presao " + kilometraza + " km";

    }

    @Override
    public int compareTo(Automobil that) {

        // Prvo poredimo naziv modela i ne uzimamo u obzir razlike izmedju velikih i malih slova
        int rezultat = Objects.compare(this.model, that.model, String.CASE_INSENSITIVE_ORDER);

        // Ako je model isti, poredimo godiste
        if (rezultat == 0) {
            rezultat = this.godiste - that.godiste;
        }

        // Vracamo rezultat poredjenja
        return rezultat;

    }
}

// Tip podataka za predstavljanje motora automobila
class Motor implements Comparable<Motor> {

    // Osobine koje se ne mogu menjati
    public final String gorivo;
    public final double snaga;

    public Motor(String gorivo, double snaga) {
        this.gorivo = gorivo;
        this.snaga = snaga;
    }

    @Override
    public String toString() {

        // Tekstualna reprezentacija motora automobila oblika:
        // Motor [Dizel, 52KW / 69.73 hp]
        return "Motor [" + gorivo + ", " + snaga + " KW / " + snaga * 1.34102 + " hp]";

    }

    @Override
    public int compareTo(Motor that) {

        // Poredimo motore iskljucivo po snazi
        return (int) Math.signum(this.snaga - that.snaga);

    }
}

// Glavni program
public class AutomobiliProgram {

    public static void main(String[] args) {

        // Ucitavanje automobila
        String fajl = Svetovid.in.readLine("Unesite ime fajla sa automobilima:");
        Automobil[] niz = ucitajAutomobile(fajl);

        // Ako nismo uspesno ucitali niz, zvrsavamo program
        if (niz == null) {
            Svetovid.out.println("Dati fajl nije mogao da se procita! Proverite da li postoji.");
            return;
        }

        // Stampanje ucitanog niza
        stampajNiz("Nesortirani niz:", niz);

        // Sortiranje niza
        sortirajNiz(niz);
        stampajNiz("Sortirani niz:", niz);

        // Sortiranje niza po drugom kriterijumu
        sortirajNizAlt(niz);
        stampajNiz("Sortirani niz po drugom kriterijumu:", niz);

    }

    public static Automobil[] ucitajAutomobile(String fajl) {

        // Ako ne mozemo da otvorimo fajl, ne ucitavamo automobile
        if (!Svetovid.testIn(fajl)) {
            return null;
        }

        // Ako mozemo, napravimo precicu 'in' da ne bi morali da
        // svaki put kucamo 'Svetovid.in(fajl)'
        SvetovidReader in = Svetovid.in(fajl);

        // Koliko ima automobila u fajlu?
        int br = in.readInt();

        // Napravimo niz odgovarajuce velicine
        Automobil[] rez = new Automobil[br];

        // Ucitamo automobile
        for (int i = 0; i < br; i++) {

            // Ucitamo podatke o automobilu
            in.readLine();
            String model = in.readLine();
            int godiste = in.readInt();
            String gorivo = in.readLine();
            double snaga = in.readDouble();
            String boja = in.readLine();
            int kilometraza = in.readInt();

            // Napravimo automobil
            Motor motor = new Motor(gorivo, snaga);
            Automobil automobil = new Automobil(model, godiste, motor, boja, kilometraza);

            // Dodamo automobil u niz
            rez[i] = automobil;

        }

        // Zatvorimo fajl
        Svetovid.closeIn(fajl);

        // Vratimo ucitani niz
        return rez;

    }

    // Stampamo niz automobila kao tabelu
    // Pogledati dokumentaciju za printf() metod i uputstvo na
    // https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
    public static void stampajNiz(String naslov, Automobil[] niz) {
        Svetovid.out.printf("%n%s%n%n", naslov);
        for (Automobil a : niz) {
            Svetovid.out.printf("%20s (%4d) %8s %8.2f KW %8s %8d km %n", a.model, a.godiste, a.motor.gorivo, a.motor.snaga, a.boja, a.getKilometraza());
        }
    }

    private static void sortirajNiz(Automobil[] niz) {

        // Sortiramo niz pomocu ugradjenog metoda
        Arrays.sort(niz);

    }

    private static class GodisteIKmKomparator implements Comparator<Automobil> {

        @Override
        public int compare(Automobil a1, Automobil a2) {

            // Poredimo automobile prvo po godistu
            // Vece godiste je bolje u ovom slucaju
            int rezultat = a2.godiste - a1.godiste;

            // Ako su godista ista, poredimo kilometrazu
            // Manje predjenih kilometara je bolje
            if (rezultat == 0) {
                rezultat = a1.getKilometraza() - a2.getKilometraza();
            }

            // Vratimo izracunati rezultat poredjenja
            return rezultat;

        }
    }

    private static void sortirajNizAlt(Automobil[] niz) {

        // Sortiramo niz pomocu ugradjenog metoda
        Arrays.sort(niz, new GodisteIKmKomparator());

    }
}
