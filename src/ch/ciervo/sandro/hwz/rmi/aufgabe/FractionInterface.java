package ch.ciervo.sandro.hwz.rmi.aufgabe;

public interface FractionInterface {

	// Setter and getter
	public int getZaehler();

	public int getNenner();

	public void setZaehler(int zaehler);

	public void setNenner(int nenner);

	// toString methods
	public String toString();

	public String toStringGekuerzt();

	public double toStringAsDecimal();

	public String toString(FractionInterface a); // should be static

	public String toStringGekuerzt(FractionInterface a); // should be static

	public double toStringAsDecimal(FractionInterface a); // should be static

	// Business Methoden
	// -----------------

	// common methods
	public FractionInterface parseBruch(String bruchStr);

	// Operations
	public FractionInterface operation(String opStr);

	public FractionInterface add(FractionInterface summand2);

	public FractionInterface add(FractionInterface summand1, FractionInterface summand2); // should
																							// be
																							// static

	public FractionInterface sub(FractionInterface minuend);

	public FractionInterface sub(FractionInterface sutrahend, FractionInterface minuend); // should
																							// be
																							// static

	public FractionInterface mul(FractionInterface factor2);

	public FractionInterface mul(FractionInterface factor1, FractionInterface factor2); // should
																						// be
																						// static

	public FractionInterface div(FractionInterface divisor);

	public FractionInterface div(FractionInterface dividend, FractionInterface divisor); // should
																							// be
																							// static

	public FractionInterface kuerzen();

	public FractionInterface kuerzen(FractionInterface a); // should be static

	public FractionInterface kehrwert();

	public FractionInterface kehrwert(FractionInterface a); // should be static
}
