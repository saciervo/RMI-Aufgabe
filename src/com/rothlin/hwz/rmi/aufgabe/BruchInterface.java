package com.rothlin.hwz.rmi.aufgabe;

//internal: Part of RMI_HWZ_Uebung - Project

public interface BruchInterface {

	// Setter and getter
	public int getZaehler();
	public int getNenner();
	
	public void setZaehler(int zaehler);
	public void setNenner(int nenner);
	
		
	// toString methods
	public String toString();
	public String toStringGekuerzt();
	public double toStringAsDecimal();
	
	public String toString(BruchInterface a);				// should be static
	public String toStringGekuerzt(BruchInterface a);		// should be static
	public double toStringAsDecimal(BruchInterface a);		// should be static
	
	// Business Methoden
	// -----------------
	
	// common methods   
	public BruchInterface parseBruch(String bruchStr);
	
	// Operations
	public BruchInterface operation(String opStr);
	
	public BruchInterface add(BruchInterface summand2);
	public BruchInterface add(BruchInterface summand1, BruchInterface summand2);	// should be static

	public BruchInterface sub(BruchInterface minuend);
	public BruchInterface sub(BruchInterface sutrahend, BruchInterface minuend);	// should be static
	
	public BruchInterface mul(BruchInterface factor2);
	public BruchInterface mul(BruchInterface factor1, BruchInterface factor2);		// should be static
	
	public BruchInterface div(BruchInterface divisor);
	public BruchInterface div(BruchInterface dividend, BruchInterface divisor);		// should be static
	
	public BruchInterface kuerzen();
	public BruchInterface kuerzen(BruchInterface a);	  // should be static

	public BruchInterface kehrwert();
	public BruchInterface kehrwert(BruchInterface a);	  // should be static
}