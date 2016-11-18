package com.rothlin.hwz.rmi.simple;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestInterface extends Remote {
	public boolean isLoginValid(String username, String password) throws RemoteException;
}