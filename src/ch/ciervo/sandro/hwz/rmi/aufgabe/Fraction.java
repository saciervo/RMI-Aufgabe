package ch.ciervo.sandro.hwz.rmi.aufgabe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fraction extends UnicastRemoteObject implements FractionInterface {

	private static final long serialVersionUID = 1L;

	private int nenner;
	private int zaehler;

	// Setter and getter
	public int getZaehler() throws RemoteException {
		return zaehler;
	}

	public int getNenner() throws RemoteException {
		return nenner;
	}

	public void setZaehler(int zaehler) throws RemoteException {
		this.zaehler = zaehler;
	}

	public void setNenner(int nenner) throws RemoteException {
		this.nenner = nenner;
	}

	// internal methods
	protected void setBruch(int zaehler, int nenner) throws RemoteException {
		if ((zaehler < 0) && (nenner < 0)) {
			this.zaehler = Math.abs(zaehler);
			this.nenner = Math.abs(nenner);
		} else {
			this.zaehler = zaehler;
			this.nenner = nenner;
		}
	}

	protected static String toStringReadable(FractionInterface a) throws RemoteException {
		if (a.getNenner() == 1) {
			return "" + a.getZaehler();
		} else {
			return a.getZaehler() + "/" + a.getNenner();
		}
	}

	protected static String toStringGekuerzt(FractionInterface a) throws RemoteException {
		return toStringReadable(a.kuerzen());
	}

	protected static double toStringAsDecimal(FractionInterface a) throws RemoteException {
		return (1.0 * a.getZaehler()) / a.getNenner();
	}

	protected static FractionInterface add(FractionInterface summand1, FractionInterface summand2) throws RemoteException {
		int resNenner = summand1.getNenner() * summand2.getNenner();
		int resZaehler = summand1.getZaehler() * summand2.getNenner() + summand2.getZaehler() * summand1.getNenner();
		return new Fraction(resZaehler, resNenner);
	}

	protected static FractionInterface sub(FractionInterface sutrahend, FractionInterface minuend) throws RemoteException {
		int resNenner = sutrahend.getNenner() * minuend.getNenner();
		int resZaehler = sutrahend.getZaehler() * minuend.getNenner() - minuend.getZaehler() * sutrahend.getNenner();
		return new Fraction(resZaehler, resNenner);
	}

	protected static FractionInterface mul(FractionInterface factor1, FractionInterface factor2) throws RemoteException {
		int resZaehler = factor1.getZaehler() * factor2.getZaehler();
		int resNenner = factor1.getNenner() * factor2.getNenner();
		return new Fraction(resZaehler, resNenner);
	}

	protected static FractionInterface div(FractionInterface dividend, FractionInterface divisor) throws RemoteException {
		int resZaehler = dividend.getZaehler() * divisor.getNenner();
		int resNenner = dividend.getNenner() * divisor.getZaehler();
		return new Fraction(resZaehler, resNenner);
	}

	protected static FractionInterface kuerzen(FractionInterface a) throws RemoteException {
		int maxi = ((Math.abs(a.getZaehler()) / 2) + 1);
		if (maxi <= 2) {
			maxi++;
		}

		int i = 2;
		while (i <= maxi) {
			if (((Math.abs(a.getZaehler()) % i) == 0) && ((Math.abs(a.getNenner()) % i) == 0)) {
				a.setZaehler(a.getZaehler() / i);
				a.setNenner(a.getNenner() / i);
			} else {
				++i;
			}
		}
		return a;
	}

	protected static FractionInterface kehrwert(FractionInterface a) throws RemoteException {
		int tmp = a.getNenner();
		a.setNenner(a.getZaehler());
		a.setZaehler(tmp);
		return a;
	}

	// Konstruktoren
	public Fraction() throws RemoteException {
		setBruch(0, 1);
	}

	protected Fraction(FractionInterface a) throws RemoteException {
		setBruch(a.getZaehler(), a.getNenner());
	}

	protected Fraction(String a) throws RemoteException {
		FractionInterface b = parseBruch(a);
		setBruch(b.getZaehler(), b.getNenner());
	}

	protected Fraction(int zaehler, int nenner) throws RemoteException {
		setBruch(zaehler, nenner);
	}

	protected Fraction(int zaehler) throws RemoteException {
		setBruch(zaehler, 1);
	}

	// Factories
	@Override
	public FractionInterface createFraction(int zaehler, int nenner) throws RemoteException {
		return new Fraction(zaehler, nenner);
	}

	// toString methods
	public String toStringReadable() throws RemoteException {
		return toStringReadable(this);
	}

	public String toStringGekuerzt() throws RemoteException {
		return toStringGekuerzt(this);
	}

	public double toStringAsDecimal() throws RemoteException {
		return toStringAsDecimal(this);
	}

	// Business Methoden
	// -----------------

	// common methods
	public FractionInterface parseBruch(String bruchStr) throws RemoteException {
		bruchStr = bruchStr.replaceAll("\\s+", "");
		Pattern p = Pattern.compile("(\\d+)(?:/(\\d+))?");
		Matcher m = p.matcher(bruchStr);

		if (m.matches()) {
			String[] fractionParts = bruchStr.split("/");
			int zaehler = Integer.parseInt(fractionParts[0]);

			int nenner = 1;
			if (fractionParts.length >= 2) {
				nenner = Integer.parseInt(fractionParts[1]);
			}
			return new Fraction(zaehler, nenner);
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Operations
	public FractionInterface operation(String opStr) throws RemoteException {
		opStr = opStr.replaceAll("\\s+", "");
		Pattern p = Pattern.compile("(\\d+(?:/\\d+)?)([-+*/:])(\\d+(?:/\\d+)?)");
		Matcher m = p.matcher(opStr);

		if (m.matches()) {
			FractionInterface b = null;
			if (opStr.contains("+")) {
				String[] opStrParts = opStr.split("\\+");
				b = new Fraction(add(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));
			} else if (opStr.contains("-")) {
				String[] opStrParts = opStr.split("\\-");
				b = new Fraction(sub(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));
			} else if (opStr.contains("*")) {
				String[] opStrParts = opStr.split("\\*");
				b = new Fraction(mul(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));
			} else if (opStr.contains(":")) {
				String[] opStrParts = opStr.split("\\:");
				b = new Fraction(div(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));
			} else {
				System.err.println("ERROR: Bruch::opertion: Unknown operation:" + opStr);
			}
			return b.kuerzen();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public FractionInterface add(FractionInterface summand2) throws RemoteException {
		return add(this, summand2);
	}

	public FractionInterface sub(FractionInterface minuend) throws RemoteException {
		return sub(this, minuend);
	}

	public FractionInterface mul(FractionInterface factor2) throws RemoteException {
		return mul(this, factor2);
	}

	public FractionInterface div(FractionInterface divisor) throws RemoteException {
		return div(this, divisor);
	}

	public FractionInterface kuerzen() throws RemoteException {
		return kuerzen(this);
	}

	public FractionInterface kehrwert() throws RemoteException {
		return kehrwert(this);
	}

}
