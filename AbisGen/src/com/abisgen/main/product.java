package com.abisgen.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class product {
	
	private Date Abzugdatum;
	private int Kontonummer;
	private int Skontronummer;
	private Date Ederungsdatum;
	private int BVR_Kto_Nr;
	private Date Dat_lzt_Buchung;
	private Date Dat_lzt_Umsatz;
	private Date Datum_der_Anlage;
	private Date Datum_Auflung;
	private char EURO_Konto;
	private int Filiale;
	private int Frist_vereinb_LFZ;
	private int Fristigkeit_Restl;
	private int Geschatsart;
	private int INA_Kr_Auival_sum;
	private Date Jur_Laufzeitende;
	private float Kapitalsaldo;
	private int Kontostatus;
	private int Kundennummer;
	private int Laufzeit_Jahre;
	private int Laufzeit_Tage;
	private Date Laufzeitende_echt;
	private float negativer_Kap_Sald;
	private float positiver_Kap_Sald;
	private int Produktbereich;
	private int Produktnummer;
	private int Sachbearbeiter;
	private String Werung_des_Kontos;
	private String Werung;
	private float Kurs_FW_1_EURO;
	
	private account acc;
	
	public product(Date abzugdatum,client cl, int prod_group, int prod_num)
	{
		int AccNumber;
		
		this.Abzugdatum = abzugdatum;
		this.Kundennummer = cl.getClientID();
		this.Produktbereich = prod_group;
		this.Produktnummer = prod_num;
        
		AccNumber = cl.getLast_Account_Number()+1;
		if (prod_group==1)
		   acc = new account(abzugdatum, this.Kundennummer*100+AccNumber,'A');
		else 
		   acc = new account(abzugdatum, this.Kundennummer*100+AccNumber,'P');

		this.Kontonummer = this.Kundennummer*100 + AccNumber;
		cl.setLast_Account_Number(AccNumber);		
		
		acc.generate_values();
	}
	
	public account getAccount()
	{
		return this.acc;
	}
	
	public void generate_values()
	{
		Calendar cal = Calendar.getInstance();
		Random rnd = new Random();
		
		Skontronummer = 0;
		
		cal.setTime(Abzugdatum);
		cal.add(Calendar.YEAR, -rnd.nextInt(5));
		cal.set(Calendar.MONTH, rnd.nextInt(12));
		cal.set(Calendar.DAY_OF_MONTH,1+rnd.nextInt(28));		
		Ederungsdatum = cal.getTime();
		
		BVR_Kto_Nr = acc.getBVR_Kto_Nr_Ztr();
		
		Dat_lzt_Buchung = Ederungsdatum;
		Dat_lzt_Umsatz = Ederungsdatum;		
		cal.add(Calendar.MONTH, rnd.nextInt(24));
		Datum_der_Anlage = cal.getTime() ;
		cal.add(Calendar.MONTH, rnd.nextInt(12));
		Datum_Auflung = cal.getTime() ;
		
		EURO_Konto = (rnd.nextBoolean()?'N':'J');
		Filiale = 1+rnd.nextInt(3);
		Frist_vereinb_LFZ = (rnd.nextBoolean()?1:0);
		Fristigkeit_Restl = 0;
		Geschatsart = 1+rnd.nextInt(3);;
		INA_Kr_Auival_sum = 0;
		
		cal.set(1999, 11, 31);
		Jur_Laufzeitende = cal.getTime();
		if (BVR_Kto_Nr==2140)
		   Kapitalsaldo = (float)(-1*rnd.nextInt(1000000)/100.0);
		else
		   Kapitalsaldo = (float)(rnd.nextInt(1000000)/100.0);
			 
		int[] Kontostatus_vals = {0,0,0,9}; 		
		Kontostatus = Kontostatus_vals[rnd.nextInt(Kontostatus_vals.length)];
		
		Laufzeit_Tage = 90+rnd.nextInt(1800);
		Laufzeit_Jahre = Laufzeit_Tage/365;
		Laufzeitende_echt = cal.getTime();
		
		if (Kapitalsaldo>0)
		{	
		   negativer_Kap_Sald = 0;
		   positiver_Kap_Sald = Kapitalsaldo;
		}
		else
		{
		   negativer_Kap_Sald = Kapitalsaldo;
		   positiver_Kap_Sald = 0;		
		}

		Sachbearbeiter = 20000+rnd.nextInt(20000);
		Werung_des_Kontos = "EUR";
		Werung = "EUR";
		Kurs_FW_1_EURO = 1;	
	}

	public String toString()
	{
		String s = "";
		SimpleDateFormat frm = new SimpleDateFormat("dd.MM.yyyy");
		
		s = frm.format(Abzugdatum) + ";" +
			Kontonummer + ";" +
			Skontronummer + ";" +
			frm.format(Ederungsdatum) + ";" +
			BVR_Kto_Nr + ";" +
			frm.format(Dat_lzt_Buchung) + ";" +
			frm.format(Dat_lzt_Umsatz) + ";" +
			frm.format(Datum_der_Anlage) + ";" +
			frm.format(Datum_Auflung) + ";" +
			EURO_Konto + ";" +
			Filiale + ";" +
			Frist_vereinb_LFZ + ";" +
			Fristigkeit_Restl + ";" +
			Geschatsart + ";" +
			INA_Kr_Auival_sum + ";" +
			frm.format(Jur_Laufzeitende) + ";" +
			Kapitalsaldo + ";" +
			Kontostatus + ";" +
			Kundennummer + ";" +
			Laufzeit_Jahre + ";" +
			Laufzeit_Tage + ";" +
			frm.format(Laufzeitende_echt) + ";" +
			negativer_Kap_Sald + ";" +
			positiver_Kap_Sald + ";" +
			Produktbereich + ";" +
			Produktnummer + ";" +
			Sachbearbeiter + ";" +
			Werung_des_Kontos + ";" +
			Werung + ";" +
			Kurs_FW_1_EURO;
		
		return s;
	}
	
	
}
