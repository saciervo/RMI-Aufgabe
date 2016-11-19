package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.rothlin.hwz.rmi.simple.TestInterface;

public class Test extends UnicastRemoteObject implements TestInterface {

	private static final long serialVersionUID = 1L;

	protected Test() throws RemoteException {
		super();
	}

	@Override
	public boolean isLoginValid(String username, String password) throws RemoteException {
		if (username.equals(password)) {
			return true;
		}
		return false;
	}
}