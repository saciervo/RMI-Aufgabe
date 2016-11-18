package com.rothlin.hwz.rmi.simple;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.rothlin.hwz.rmi.simple.Test_RMI_Definitions;

public class Test_RMI_Server {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Test impl = new Test();
		Registry registry = LocateRegistry.createRegistry(Test_RMI_Definitions.RMI_PORT);
		registry.bind(Test_RMI_Definitions.RMI_ID,impl);
		System.out.println("Server has been started!");
	}
}