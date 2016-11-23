package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.io.Serializable;

/**
 * Das Interface analog zum BruchInterface aus den Kursunterlagen. Hat bis auf
 * ein paar Ausnahmen die gleichen Methoden.
 * 
 * Alle Methoden die mit "should be static" kommentiert waren, wurden entfernt,
 * weil der Client sowiese keinen Zugriff auf deren Implementierung hat.
 * 
 * Ausserdem wurden alle Accessoren entfernt, da ein Interface diese nicht
 * vorschreiben sollte, und auch nicht kann.
 * 
 * Neu erben wir noch von Serializable, damit das Objekt übers Netzwerk
 * verschickt werden kann.
 */
public interface FractionInterface extends Serializable {

	// Setter and getter
	int getZaehler();

	int getNenner();

	void setZaehler(int zaehler);

	void setNenner(int nenner);

	// toString methods
	String toString();

	String toStringGekuerzt();

	double toStringAsDecimal();

	// Business Methoden
	// -----------------

	// common methods
	FractionInterface parseBruch(String bruchStr);

	// Operations
	FractionInterface operation(String opStr);

	FractionInterface add(FractionInterface summand2);

	FractionInterface sub(FractionInterface minuend);

	FractionInterface mul(FractionInterface factor2);

	FractionInterface div(FractionInterface divisor);

	FractionInterface kuerzen();

	FractionInterface kehrwert();
}
