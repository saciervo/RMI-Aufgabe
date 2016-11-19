package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fraction_RMI_Server {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Fraction impl = new Fraction();
		Registry registry = LocateRegistry.createRegistry(Fraction_RMI_Definitions.RMI_PORT);
		registry.bind(Fraction_RMI_Definitions.RMI_ID, impl);
		System.out.println("Server has been started!");
	}
}