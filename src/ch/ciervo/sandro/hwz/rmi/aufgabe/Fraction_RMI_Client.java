package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fraction_RMI_Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(Fraction_RMI_Definitions.RMI_ServerHost, Fraction_RMI_Definitions.RMI_PORT);
		FractionInterface remote = (FractionInterface) registry.lookup(Fraction_RMI_Definitions.RMI_ID);
		System.out.println(remote.operation("1/2 + 1/3").toFractionString());
	}
}