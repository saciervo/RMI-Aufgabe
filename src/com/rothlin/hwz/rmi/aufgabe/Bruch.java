package com.rothlin.hwz.rmi.aufgabe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//internal: Part of RMI_HWZ_Uebung - Project

public class Bruch implements BruchInterface {

	private int nenner;
	private int zaehler;
	
	// Setter and getter
	public int getZaehler() 			{	return zaehler; 		}
	public int getNenner() 				{	return nenner;	        }
	
	public void setZaehler(int zaehler) {	this.zaehler = zaehler; }
	public void setNenner(int nenner) 	{	this.nenner = nenner;   }

	// internal methods
	private void setBruch(int zaehler, int nenner) {
		if ((zaehler < 0) && (nenner < 0)) {
			this.zaehler      = Math.abs(zaehler);
			this.nenner       = Math.abs(nenner);
		} else {
			this.zaehler      = zaehler;
			this.nenner       = nenner;
		}
	}
	
	// Konstruktoren
	public Bruch()							{ setBruch(0, 1);	                                      			}
	public Bruch(BruchInterface a) 			{ setBruch(a.getZaehler(), a.getNenner());                			}
	public Bruch(String a) 					{ BruchInterface b = parseBruch(a); setBruch(b.getZaehler(), b.getNenner()); }
	public Bruch(int zaehler, int nenner) 	{ setBruch(zaehler, nenner); }
	public Bruch(int zaehler) 				{ setBruch(zaehler, 1);	}
	
	
	// toString methods
	public String toString() 				{ return toString(this);		                      }
	public String toStringGekuerzt() 		{ return toStringGekuerzt(this);	                  }
	public double toStringAsDecimal() 		{ return toStringAsDecimal(this);					  }
	
	public String toString(BruchInterface a) 	{
		if (a.getNenner() == 1) {
			return "" + a.getZaehler();
		} else {
			return a.getZaehler() + "/" + a.getNenner();
		}
	}
	
	public String toStringGekuerzt(BruchInterface a) {
		return toString(a.kuerzen());
	}
	
	public  double toStringAsDecimal(BruchInterface a) {
		return (1.0 * a.getZaehler()) /a.getNenner();
	}
	
	// Business Methoden
	// -----------------
	
	// common methods   
	public BruchInterface parseBruch(String bruchStr) {
		bruchStr = bruchStr.replaceAll("\\s+","");
        Pattern p = Pattern.compile("(\\d+)(?:/(\\d+))?");
        Matcher m = p.matcher(bruchStr);

        if (m.matches()) {
			String[] fractionParts = bruchStr.split("/");
			int zaehler = Integer.parseInt(fractionParts[0]);
			
			int nenner = 1;
			if (fractionParts.length >= 2) {
				nenner = Integer.parseInt(fractionParts[1]);
			}
			return new Bruch(zaehler,nenner);
        } else {
        	throw new IllegalArgumentException();
        }
	}
	
	// Operations
	public BruchInterface operation(String opStr) {
		opStr = opStr.replaceAll("\\s+","");
        Pattern p = Pattern.compile("(\\d+(?:/\\d+)?)([-+*/:])(\\d+(?:/\\d+)?)");
        Matcher m = p.matcher(opStr);

        if (m.matches()) {
			BruchInterface b = null;
			if (opStr.contains("+")) {
				String[] opStrParts = opStr.split("\\+");
				b = new Bruch(add(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));		
			} else if (opStr.contains("-")) {
				String[] opStrParts = opStr.split("\\-");
				b = new Bruch(sub(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));		
			} else if (opStr.contains("*")) {
				String[] opStrParts = opStr.split("\\*");
				b = new Bruch(mul(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));		
			} else if (opStr.contains(":")) {
				String[] opStrParts = opStr.split("\\:");
				b = new Bruch(div(parseBruch(opStrParts[0]), parseBruch(opStrParts[1])));		
			} else{
				System.err.println("ERROR: Bruch::opertion: Unknown operation:" + opStr);
			}
			return b.kuerzen();
        } else {
        	throw new IllegalArgumentException();
        }
	}
	

	public BruchInterface add(BruchInterface summand2) {
		return add(this, summand2);
	}

	public BruchInterface add(BruchInterface summand1, BruchInterface summand2) {
		int resNenner  = summand1.getNenner() * summand2.getNenner();
		int resZaehler = summand1.getZaehler() * summand2.getNenner() + summand2.getZaehler() * summand1.getNenner();
		return new Bruch(resZaehler, resNenner);		
	}
	

	public BruchInterface sub(BruchInterface minuend) {
		return sub(this, minuend);
	}
	
	public BruchInterface sub(BruchInterface sutrahend, BruchInterface minuend) {
		int resNenner  = sutrahend.getNenner() * minuend.getNenner();
		int resZaehler = sutrahend.getZaehler() * minuend.getNenner() - minuend.getZaehler() * sutrahend.getNenner();
		return new Bruch(resZaehler, resNenner);		
	}
	

	public BruchInterface mul(BruchInterface factor2) {
		return mul(this, factor2);
	}
	
	public BruchInterface mul(BruchInterface factor1, BruchInterface factor2) {
		int resZaehler = factor1.getZaehler() * factor2.getZaehler();
		int resNenner  = factor1.getNenner()  * factor2.getNenner();
		return new Bruch(resZaehler, resNenner);		
	}
	

	public BruchInterface div(BruchInterface divisor) {
		return div(this, divisor);
	}
	
	public BruchInterface div(BruchInterface dividend, BruchInterface divisor) {
		int resZaehler = dividend.getZaehler() * divisor.getNenner();
		int resNenner  = dividend.getNenner()  * divisor.getZaehler();
		return new Bruch(resZaehler, resNenner);		
	}
	
	public BruchInterface kuerzen() {
		return kuerzen(this);
	}
	
	public BruchInterface kuerzen(BruchInterface a) {
		int maxi = ((Math.abs(a.getZaehler()) / 2 ) + 1);
		if (maxi <= 2) { maxi++; }

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
	

	public BruchInterface kehrwert() {
		return kehrwert(this);
	}
	
	public BruchInterface kehrwert(BruchInterface a) {
		int tmp = a.getNenner();
		a.setNenner(a.getZaehler());
		a.setZaehler(tmp);
		return a;		
	}
}