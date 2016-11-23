package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Das öffentliche Interface für die Fraction Fabrik. Dieses Interface wird an
 * für andere Programmierer veröffentlicht, damit diese die Fraction-Fabrik über RMI
 * erstellen können.
 */
public interface IFractionFactory extends Remote {

	FractionInterface createFraction() throws RemoteException;

	FractionInterface createFraction(FractionInterface a) throws RemoteException;

	FractionInterface createFraction(String a) throws RemoteException;

	FractionInterface createFraction(int zaehler, int nenner) throws RemoteException;

	FractionInterface createFraction(int zaehler) throws RemoteException;
}