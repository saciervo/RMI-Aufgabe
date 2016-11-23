package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fraction_RMI_Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(Fraction_RMI_Definitions.RMI_ServerHost, Fraction_RMI_Definitions.RMI_PORT);
		FractionInterface remote = (FractionInterface) registry.lookup(Fraction_RMI_Definitions.RMI_ID);

		// Tests
		System.out.println("Some Test-Cases");
		FractionInterface b1 = remote.createFraction(1, 2);
		System.out.println("b1              =" + b1.toStringReadable());
		System.out.println("b1 (as decimal) =" + b1.toStringAsDecimal());
		System.out.println("b1 (gekuerzt)   =" + b1.toStringGekuerzt() + "\n");

		FractionInterface b2 = remote.createFraction(3, 4);
		System.out.println("b2              =" + b2.toStringReadable());
		System.out.println("b2 (as decimal) =" + b2.toStringAsDecimal());
		System.out.println("b2 (gekuerzt)   =" + b2.toStringGekuerzt() + "\n");

		FractionInterface b3 = remote.createFraction(4, 6);
		System.out.println("b3              =" + b3.toStringReadable());
		System.out.println("b3 (as decimal) =" + b3.toStringAsDecimal());
		System.out.println("b3 (gekuerzt)   =" + b3.toStringGekuerzt() + "\n");

		FractionInterface b4 = remote.createFraction(6, 3);
		System.out.println("b4              =" + b4.toStringReadable());
		System.out.println("b4 (as decimal) =" + b4.toStringAsDecimal());
		System.out.println("b4 (gekuerzt)   =" + b4.toStringGekuerzt() + "\n");

		String op1Str = "5/6";
		String op2Str = "12/9";
		FractionInterface dummyObject = remote;

		String operationStr = op1Str + "+" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr).toStringReadable() + "   Expected: 13/6");

		operationStr = op1Str + "-" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr).toStringReadable() + "   Expected: -1/2");

		operationStr = op1Str + "*" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr).toStringReadable() + "   Expected: 10/9");

		operationStr = op1Str + ":" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr).toStringReadable() + "   Expected: 5/8");
		System.out.println("");

		// Bruch-Rechner
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String answer = "";

		do {

			System.out.print("Operation [e.g. 1/3 + 4/5]: ");
			try {
				operationStr = inFromUser.readLine();
				System.out.println(operationStr + " = " + dummyObject.operation(operationStr).toStringReadable());
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Aufhören? [Y/N]");
			try {
				answer = inFromUser.readLine().toUpperCase();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while ((!answer.equals("Y")));
	}
}