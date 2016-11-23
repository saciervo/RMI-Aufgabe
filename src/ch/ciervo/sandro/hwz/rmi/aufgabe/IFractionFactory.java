package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Das �ffentliche Interface f�r die Fraction Fabrik. Dieses Interface wird an
 * f�r andere Programmierer ver�ffentlicht, damit diese die Fraction-Fabrik �ber RMI
 * erstellen k�nnen.
 */
public interface IFractionFactory extends Remote {

	FractionInterface createFraction() throws RemoteException;

	FractionInterface createFraction(FractionInterface a) throws RemoteException;

	FractionInterface createFraction(String a) throws RemoteException;

	FractionInterface createFraction(int zaehler, int nenner) throws RemoteException;

	FractionInterface createFraction(int zaehler) throws RemoteException;
}