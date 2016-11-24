package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Der RMI-Client, aufgebaut wie der Test_RMI_Client aus den Kursunterlagen.
 */
public class Fraction_RMI_Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(Fraction_RMI_Definitions.RMI_ServerHost, Fraction_RMI_Definitions.RMI_PORT);
		IFractionFactory factory = (IFractionFactory) registry.lookup(Fraction_RMI_Definitions.RMI_ID);

		// Dem User ein Auswahlmenü anzeigen
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;

		do {
			System.out.println("Menü");
			System.out.println("----\n");
			System.out.println("[0] Client beenden");
			System.out.println("[1] Tests anzeigen");
			System.out.println("[2] Rechner starten");
			System.out.print("\nIhre Wahl: ");

			try {
				answer = Integer.parseInt(inFromUser.readLine());
				switch (answer) {
				case 0:
					System.out.println("Vielen Dank und auf Wiedersehen!");
					break;
				case 1:
					printTests(factory);
					break;
				case 2:
					runCalculator(inFromUser, factory);
					break;
				default:
					System.out.println(String.format("Menü '%d' nicht verfügbar\n", answer));
					break;
				}
			} catch (Exception ex) {
				System.out.println("Unbekannte Eingabe!\n");
			}
		} while ((answer != 0));
	}

	/**
	 * Zeigt diverse Tests mit dem Bruchrechner auf der Konsole an.
	 * 
	 * Ablauf ist aus BruchRechner.java von den Kursunterlagen entnommen und
	 * ergänzt.
	 *
	 * @param factory
	 *            Die Fraction Factory, instanziiert per RMI
	 * @throws RemoteException
	 *             Eine mögliche RemoteException
	 */
	private static void printTests(IFractionFactory remote) throws RemoteException {

		System.out.println("Some Test-Cases");
		FractionInterface b1 = remote.createFraction(1, 2);
		System.out.println("b1              =" + b1);
		System.out.println("b1 (as decimal) =" + b1.toStringAsDecimal());
		System.out.println("b1 (gekuerzt)   =" + b1.toStringGekuerzt());
		System.out.println("b1 (Kehrwert)   =" + b1.kehrwert() + "\n");

		FractionInterface b2 = remote.createFraction(3, 4);
		System.out.println("b2              =" + b2);
		System.out.println("b2 (as decimal) =" + b2.toStringAsDecimal());
		System.out.println("b2 (gekuerzt)   =" + b2.toStringGekuerzt());
		System.out.println("b2 (Kehrwert)   =" + b2.kehrwert() + "\n");

		FractionInterface b3 = remote.createFraction(4, 6);
		System.out.println("b3              =" + b3);
		System.out.println("b3 (as decimal) =" + b3.toStringAsDecimal());
		System.out.println("b3 (gekuerzt)   =" + b3.toStringGekuerzt());
		System.out.println("b3 (Kehrwert)   =" + b3.kehrwert() + "\n");

		FractionInterface b4 = remote.createFraction(6, 3);
		System.out.println("b4              =" + b4);
		System.out.println("b4 (as decimal) =" + b4.toStringAsDecimal());
		System.out.println("b4 (gekuerzt)   =" + b4.toStringGekuerzt());
		System.out.println("b4 (Kehrwert)   =" + b4.kehrwert() + "\n");

		String op1Str = "5/6";
		String op2Str = "12/9";

		FractionInterface dummyObject = remote.createFraction();

		String operationStr = op1Str + "+" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr) + "   Expected: 13/6");

		operationStr = op1Str + "-" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr) + "   Expected: -1/2");

		operationStr = op1Str + "*" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr) + "   Expected: 10/9");

		operationStr = op1Str + ":" + op2Str;
		System.out.println(operationStr + " = " + dummyObject.operation(operationStr) + "   Expected: 5/8");
		System.out.println("");
	}

	/**
	 * Startet den Bruchrechner, bei dem der User eine einfache Bruchoperation
	 * durchführen kann.
	 * 
	 * Logik ist aus BruchRechner.java von den Kursunterlagen
	 *
	 * @param inFromUser
	 *            Das BufferedReader Objekt um Eingaben aus der Konsole
	 *            entgegenzunehmen
	 * @param factory
	 *            Die Fraction Factory, instanziiert per RMI
	 * @throws RemoteException
	 *             Eine mögliche RemoteException
	 */
	private static void runCalculator(BufferedReader inFromUser, IFractionFactory remote) throws RemoteException {

		FractionInterface dummyObject = remote.createFraction();
		String operationStr;
		String answer = "";

		do {

			System.out.print("Operation [e.g. 1/3 + 4/5]: ");
			try {
				operationStr = inFromUser.readLine();
				System.out.println(operationStr + " = " + dummyObject.operation(operationStr));
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