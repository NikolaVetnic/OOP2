package teorijske_vezbe.tv01_z01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Dokumentacija vezana za temu:
// https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/regex/Pattern.html

public class RegexExample {

	public static void main(String[] args) {
		
		String tekst0 = "Neki kratak tekst. U njemu se rece tekst javlja vise puta.";
		
		// poredjenje stringova
		String tekst1 = "Neki kratak tekst. U njemu se rece tekst javlja vise puta.";
		
		if (tekst0 == tekst1) System.out.println("Jednaki su");			// porede se reference a ne sadrzaj
		else				  System.out.println("Nisu jednaki");
		
		if (tekst0.equals(tekst1)) System.out.println("Jednaki su");	// poredi se sadrzaj
		else					   System.out.println("Nisu jednaki");
		
		// sadrzavanje podstringa
		String rec = "kratak";
		
		if (tekst0.contains(rec)) System.out.println("Sadrzi");
		else					  System.out.println("Ne sadrzi");
		
		System.out.println("Indeks znaka na kojem se prvi put pojavljuje rec : " + tekst0.indexOf(rec));
		
		// sadrzavanje nezavisno od broja, roda, padeza
		if (tekst0.contains("kratak") || tekst0.contains("kratka")) System.out.println("Sadrzi");
		else					  									System.out.println("Ne sadrzi");
		
		// koriscenje regex-a
		String[] reci = tekst0.split("[.]");
		for (String r : reci) System.out.print(r + " // ");
		System.out.println();
		
		String tekst2 = tekst0.replace("a", "e");
		System.out.println(tekst2);
		
		//
		String regex = "\\b(?<prvoslovo>k).*?(?<poslednjeslovo>.)\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tekst0);
		
		while (matcher.find()) {
			System.out.println("Nadjeno : " + matcher.start() + " - " + matcher.end());
			System.out.println("Ceo izraz : " + matcher.group(0));
			System.out.println("Prvo slovo : " + matcher.group(1) + " ; Poslednje slovo : " + matcher.group(2));
			System.out.println("Prvo slovo : " + matcher.group("prvoslovo") + " ; Poslednje slovo : " + matcher.group("poslednjeslovo"));
		}
		
		if (matcher.matches()) System.out.println("Ceo izraz se poklapa.");	// da li se ceo izraz poklapa sa stringom
		if (matcher.lookingAt()) System.out.println("Da.");					// da li trenutno gledamo na nesto sto zadovoljava regex 
		
		String registracija = "\\b[A-Z]{2} \\d{3}-[A-Z]{2}\\b";
		Pattern regPat = Pattern.compile(registracija);
		
		
	}
}
