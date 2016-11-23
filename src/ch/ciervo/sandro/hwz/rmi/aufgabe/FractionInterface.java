package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Das Interface analog zum BruchInterface aus den Kursunterlagen.
 * 
 * Wird über RMI instanziiert und hat bis auf ein paar Ausnahmen die gleichen
 * Methoden.
 * 
 * Alle Methoden die mit "should be static" kommentiert waren sind jetzt static
 * in der Fraction-Klasse. Damit sind diese Methoden im Interface überflüssig
 * geworden, weil der Client keinen Zugriff auf die Fraction-Klasse hat, sondern
 * nur auf Instanzen davon, die über RMI erzeugt wurden
 */
public interface FractionInterface extends Remote {

	// Setter and getter
	public int getZaehler() throws RemoteException;

	public int getNenner() throws RemoteException;

	public void setZaehler(int zaehler) throws RemoteException;

	public void setNenner(int nenner) throws RemoteException;

	/**
	 * Erstellt ein neues Fraction Objekt, also ein Bruch, mit dem spezifizerten
	 * Zähler und Nenner.
	 * 
	 * Bei der Erzeugung des Objekts über RMI wird der parameterlose Konstruktor
	 * benutzt. Wenn ich einen anderen Konstruktor verwenden will, mach ich das
	 * am besten über eine Factory-Methode wie diese hier
	 *
	 * @param zaehler
	 *            Der Zaehler des Bruchs
	 * @param nenner
	 *            Der Nenner des Bruchs
	 * @return Das erzeugte Fraction Objekt mit FractionInterface als "Vertrag"
	 * @throws RemoteException
	 *             Die mögliche RemoteException
	 */
	// Factories
	public FractionInterface createFraction(int zaehler, int nenner) throws RemoteException;

	// toString methods
	/**
	 * Dies ist der Ersatz für die toString() Methode die nicht überschrieben
	 * werden kann, weil UnicastRemoteObject diese Methode bereits ohne "throws
	 * RemoteException" definiert.
	 * 
	 * Zitat aus https://www.redbooks.ibm.com/redbooks/pdfs/sg246320.pdf (Seite
	 * 154):
	 * 
	 * If the extending class then declares the java.rmi.RemoteException in its
	 * throws clause, it broadens the signature of the method, which is not
	 * allowed in Java.
	 * 
	 * Die Methode brauche ich, um den Bruch als lesbaren String auf der Konsole
	 * auszugeben
	 *
	 * @return Der Bruch als lesbarer String
	 * @throws RemoteException
	 *             Die mögliche RemoteException
	 */
	public String toStringReadable() throws RemoteException;

	public String toStringGekuerzt() throws RemoteException;

	public double toStringAsDecimal() throws RemoteException;

	// Business Methoden
	// -----------------

	// common methods
	public FractionInterface parseBruch(String bruchStr) throws RemoteException;

	// Operations
	public FractionInterface operation(String opStr) throws RemoteException;

	public FractionInterface add(FractionInterface summand2) throws RemoteException;

	public FractionInterface sub(FractionInterface minuend) throws RemoteException;

	public FractionInterface mul(FractionInterface factor2) throws RemoteException;

	public FractionInterface div(FractionInterface divisor) throws RemoteException;

	public FractionInterface kuerzen() throws RemoteException;

	public FractionInterface kehrwert() throws RemoteException;
}
