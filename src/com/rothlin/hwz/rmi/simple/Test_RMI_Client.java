package com.rothlin.hwz.rmi.simple;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.rothlin.hwz.rmi.simple.Test_RMI_Definitions;
import com.rothlin.hwz.rmi.simple.TestInterface;

public class Test_RMI_Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(Test_RMI_Definitions.RMI_ServerHost, Test_RMI_Definitions.RMI_PORT);
		TestInterface remote = (TestInterface) registry.lookup(Test_RMI_Definitions.RMI_ID);
		System.out.println(remote.isLoginValid("test","test"));
		System.out.println(remote.isLoginValid("TEST","test"));
	}

}