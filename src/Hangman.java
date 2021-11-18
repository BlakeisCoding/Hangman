import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {
	public static boolean nichtgeloest = true;

	@SuppressWarnings({ "resource" })
	public static void main(String[] aflkchn) {
		Scanner in = new Scanner(System.in);
		int fzaehler = 1;
		String zuwo = suchwort();
		String loesung = loesung(zuwo);
		String loesungl = "";
		String eingaben = "";
		String eingabenz = "";

		System.out.println("Spielen wir Hangman!\n");
		System.out.println(bilder(1));
		System.out.println(loesungl(loesung));

		System.out.println(loesungl);
		while (nichtgeloest) {
			System.out.print("\nRaten sie einen Buchstaben: ");
			String e = in.nextLine().toUpperCase();
			if (e.length() > 0)
				eingabenz += e + ", ";
			while (e.length() < 1 | e.length() > 1) {
				System.out.println("Deine Eingabe ist falsch!" + "\n Mach eine neue Eingabe: ");
				e = in.nextLine().toUpperCase();
				if (e.length() > 0)
					eingabenz += e + ", ";
			}
			loesung = hngmn(zuwo, e, loesung);
			if (zuwo.contains(e) && (!eingaben.contains(e))) {
				loesungl = "";
				System.out.println("\n" + loesungl(loesung) + "\nBereits gemachte Eingaben: " + eingabenz);
			} // if-Abfrage
			else {
				loesungl = "";
				fzaehler++;
				System.out.println("\n" + bilder(fzaehler) + "\nDer Buchstabe ist falsch!\n");
				System.out.println("\n" + loesungl(loesung) + "\nBereits gemachte Eingaben: " + eingabenz);
			} // else-Anweisung
			eingaben += e;

			if (fzaehler >= 7) {
				nichtgeloest = false;
			}
			if (loesung.equals(zuwo)) {
				nichtgeloest = false;
			}

		} // while-Schleife
		if (loesung.equals(zuwo))
			System.out.println("\nDu hast das Wort mit nur " + (fzaehler - 1) + " Fehlern richtig erraten!");
		if (fzaehler >= 7)
			System.out.println("\nHangman haengt!\nMein Wort lautete: " + zuwo);
	}// main

	/**
	 * Erzeugt einen String aus einem reingegebenen Wortes, welcher alle Zeichen
	 * durch ein Leerzeichen trennt.
	 *
	 * @param loesung Ein String, dessen Zeichen durch Leerzeichen getrennt werden
	 *                sollen.
	 * @return Gibt einen Sting zurueck, der die Zeichen eines in die Methode
	 *         gegebenen Wortes durch Leerzeichen trennt.
	 */
	public static String loesungl(String loesung) {
		String loesungl = "";
		for (char c : loesung.toCharArray()) {
			loesungl += c + " ";
		}
		return loesungl;
	}

	/**
	 * Erzeugt einen String aus Unterstrichen, welcher die Laenge des Zufallswortes
	 * hat.
	 * 
	 * @param zuwo - Ein String, der die Laenge des auszugebenden Strings bestimmt.
	 * @return Gibt einen String aus Unterstrichen mit der Laenge des in die Methode
	 *         gegebenen Wortes zurueck.
	 */
	public static String loesung(String zuwo) {
		String loesung = "";
		for (int i = 0; i < zuwo.length(); i++) {
			loesung += "_";
		} // for-Schleife
		return loesung;
	}

	/**
	 * Erzeugt ein Wort, welches aus einer Liste zufaellig ausgesucht wird.
	 * 
	 * @return Gibt einen String mit einem Zufallswort zurueck.
	 */
	public static String suchwort() {
		String ausgabe = "";
		List<String> words = Arrays.asList("Adler");
		ausgabe = (words.get((int) (Math.random() * words.size())).toUpperCase());
		return ausgabe;
	}

	/**
	 * Erstellt einen String unter Beruecksichtigung des Zufallswortes. Falls der
	 * Buchstabe der Stelle null des eingegebene Strings im Zufallswort enthalten
	 * ist, wird er an den String an der geprueften Stelle eingesetzt. Falls nicht,
	 * wird stattdessen die Stelle mit dem gleichen Index aus dem loesung-String an
	 * dem String eingefuegt.
	 * 
	 * @param zuwo    - Aus vorgegebener Liste generiertes Zufallswort als
	 * @param e       - Nicht leerer, vom Nutzer eingegebener String.
	 * @param loesung - String mit der Laenge des Zufallswortes, welcher zum Start
	 *                des Programms aus Unterstrichen besteht. Die Unterstriche
	 *                werden nach und nach durch richtige Nutzereingaben an den
	 *                richtigen index-Stellen ersetzt.@return String, der sich aus
	 *                den richtigen Nutzereingaben zusammensetzt, bzw an den
	 *                uebrigen oder bei falscher Eingabe aus Unterstrichen.
	 */
	public static String hngmn(String zuwo, String e, String loesung) {

		String ausgabe = "";
		char ebuch = e.charAt(0);
		int index = -1;
		for (char c : zuwo.toCharArray()) {
			index++;
			if (ebuch == c) { // Eingabe gleich geprueften Buchstabe
				ausgabe += c; // Wenn ja, Ausgabestring + diesen Buchstaben
			} else {
				ausgabe += loesung.charAt(index);
				// Wenn nein, Ausgabestring + die Indexstelle des Unterstrichstring
			}
		}
		return ausgabe;
	}

	/**
	 * Erzeugt einen String, der je nach Eingabe die einzelnen Stadien des
	 * Hangman-Galgen beinhaltet.
	 * 
	 * @param i Eine ganze Zahl zwischen 1 und 7 die bestimmt, welches Stadium des
	 *          Hangman-Galgen in dem Ausgabe-String eingefuegt werden soll.
	 * @return Gibt einen String zurueck, welcher den Hangman-Galgen des
	 *         entsprechend festgelegten Stadiums enthaelt.
	 */
	public static String bilder(int i) {
		String ausgabe = "";
		if (i == 1) {
			ausgabe += (" +-----+" + "\n");
			ausgabe += (" |/" + "\n");
			ausgabe += (" |" + "\n");
			ausgabe += (" |" + "\n");
			ausgabe += (" |" + "\n");
			ausgabe += (" ***" + "\n");
			ausgabe += ("************" + "\n");
		}
		// ...
		return ausgabe;
	}
}