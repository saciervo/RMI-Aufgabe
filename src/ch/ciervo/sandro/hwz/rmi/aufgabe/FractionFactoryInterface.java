package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FractionFactoryInterface extends Remote {
	
	FractionInterface createFraction() throws RemoteException;

	FractionInterface createFraction(FractionInterface a) throws RemoteException;

	FractionInterface createFraction(String a) throws RemoteException;

	FractionInterface createFraction(int zaehler, int nenner) throws RemoteException;

	FractionInterface createFraction(int zaehler) throws RemoteException;
}