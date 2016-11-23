package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FractionFactory extends UnicastRemoteObject implements FractionFactoryInterface {

	private static final long serialVersionUID = 1L;

	protected FractionFactory() throws RemoteException {
		super();
	}

	@Override
	public FractionInterface createFraction() throws RemoteException {
		return new Fraction();
	}

	@Override
	public FractionInterface createFraction(FractionInterface a) throws RemoteException {
		return new Fraction(a);
	}

	@Override
	public FractionInterface createFraction(String a) throws RemoteException {
		return new Fraction(a);
	}

	@Override
	public FractionInterface createFraction(int zaehler, int nenner) throws RemoteException {
		return new Fraction(zaehler, nenner);
	}

	@Override
	public FractionInterface createFraction(int zaehler) throws RemoteException {
		return new Fraction(zaehler);
	}
}
