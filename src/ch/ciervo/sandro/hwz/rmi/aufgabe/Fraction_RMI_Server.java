package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Der RMI-Server, aufgebaut wie der Test_RMI_Server aus den Kursunterlagen.
 */
public class Fraction_RMI_Server {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		FractionFactory impl = new FractionFactory();
		Registry registry = LocateRegistry.createRegistry(Fraction_RMI_Definitions.RMI_PORT);
		registry.bind(Fraction_RMI_Definitions.RMI_ID, impl);
		System.out.println("Server has been started!");
	}
}