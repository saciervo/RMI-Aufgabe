package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FractionInterface extends Remote {

	// Setter and getter
	public int getZaehler() throws RemoteException;

	public int getNenner() throws RemoteException;

	public void setZaehler(int zaehler) throws RemoteException;

	public void setNenner(int nenner) throws RemoteException;

	// toString methods
	public String toFractionString() throws RemoteException;

	public String toStringGekuerzt() throws RemoteException;

	public double toStringAsDecimal() throws RemoteException;

	public String toFractionString(FractionInterface a) throws RemoteException; // should be static

	public String toStringGekuerzt(FractionInterface a) throws RemoteException; // should be static

	public double toStringAsDecimal(FractionInterface a) throws RemoteException; // should be static

	// Business Methoden
	// -----------------

	// common methods
	public FractionInterface parseBruch(String bruchStr) throws RemoteException;

	// Operations
	public FractionInterface operation(String opStr) throws RemoteException;

	public FractionInterface add(FractionInterface summand2) throws RemoteException;

	public FractionInterface add(FractionInterface summand1, FractionInterface summand2) throws RemoteException; // should
																							// be
																							// static

	public FractionInterface sub(FractionInterface minuend) throws RemoteException;

	public FractionInterface sub(FractionInterface sutrahend, FractionInterface minuend) throws RemoteException; // should
																							// be
																							// static

	public FractionInterface mul(FractionInterface factor2) throws RemoteException;

	public FractionInterface mul(FractionInterface factor1, FractionInterface factor2) throws RemoteException; // should
																						// be
																						// static

	public FractionInterface div(FractionInterface divisor) throws RemoteException;

	public FractionInterface div(FractionInterface dividend, FractionInterface divisor) throws RemoteException; // should
																							// be
																							// static

	public FractionInterface kuerzen() throws RemoteException;

	public FractionInterface kuerzen(FractionInterface a) throws RemoteException; // should be static

	public FractionInterface kehrwert() throws RemoteException;

	public FractionInterface kehrwert(FractionInterface a) throws RemoteException; // should be static

	public FractionInterface createFraction(int zaehler, int nenner) throws RemoteException;
}
