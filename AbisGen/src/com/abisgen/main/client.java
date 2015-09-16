package com.abisgen.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;




public class client {

	private Date Abzugdatum;
	private Integer Kundennummer;
	private Integer Anredeschlessel;
	private Integer AP_Kundenbetreuer;
	private Integer AP_Sachbearbeiter;
	private Integer Bankint_Klassifiz;
	private Integer Berufszw_Arbeitgb;
	private Integer Branche_Beruf;
	private Date Datum_Kundenanlage;
	private Integer Devisenstatusschl;
	private Integer Familienstand;
	private Date Geburtsdatum;
	private char Gemeinschaftskunde;
	private Integer Geschlecht;
	private Date Grendung_am;
	private Integer Geterstand;
	private Integer Konditions_Gruppe;
	private char Kundenart;
	private Integer Kundenbonitat;
	private Integer Kundenfiliale;
	private Integer Kundengruppe;
	private Integer Kundensegment;
	private char Kundentyp;
	private Integer Landerschlessel;
	private String Nachname;
	private String Name1_Teil;
	private String Name2_Teil;
	private String Ort;
	private String PLZ;
	private Integer Rechtsformschless;
	private Integer Risikogruppenschl;
	private String Sortiername;
	private Integer StaatsangehUrigk;
	private Integer Status;
	private char Steuerinlander;
	private Integer Stlg_Erwerbsleben;
	private String Vorname;
	private Integer Organ_Mitarb_Sl;
	private Integer Kunden_Status;
	private int Employee_key;
	private char Kz_Groekredit;
	private char Kz_Millionenkredit;
	
	private int Last_Account_Number;
	
	public client(Date abzugdatum,int cl_num)
	{
		this.Kundennummer = cl_num;
		this.Abzugdatum = abzugdatum;
		this.Last_Account_Number = 0;
	}
	
	public int getLast_Account_Number()
	{
		return Last_Account_Number;
	}
	
	public void setLast_Account_Number(int num)
	{
		this.Last_Account_Number=num;
	}
	
	public void generate_values(db2interface db)
	{
		Random rnd = new Random();
		Calendar cal = new GregorianCalendar();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Anredeschlessel = rnd.nextInt(20);
		
		AP_Kundenbetreuer = 0;
		
		AP_Sachbearbeiter = 20000 + rnd.nextInt(10000);
		
		int[] bankint_klassifiz_vals = {0,100,200,300,400}; 
		Bankint_Klassifiz = bankint_klassifiz_vals[rnd.nextInt(bankint_klassifiz_vals.length)];
		
		Berufszw_Arbeitgb=0;
		
		Branche_Beruf = rnd.nextInt(10000);
		
		cal.set(Calendar.YEAR, 2000 + rnd.nextInt(10));
		cal.set(Calendar.MONTH, rnd.nextInt(12));
		cal.set(Calendar.DAY_OF_MONTH, 1 + rnd.nextInt(27));	
		Datum_Kundenanlage = cal.getTime(); 
		
		Devisenstatusschl = 0;
		
		Familienstand = rnd.nextInt(6);
		
		cal.set(Calendar.YEAR, 1960 + rnd.nextInt(25));
		cal.set(Calendar.MONTH, rnd.nextInt(12));
		cal.set(Calendar.DAY_OF_MONTH, 1 + rnd.nextInt(28));	
		Geburtsdatum = cal.getTime();
		
		
		Gemeinschaftskunde = (rnd.nextBoolean()?'N':'J');
		
		Geschlecht = rnd.nextInt(4);
		
		
		cal.set(1999, 11, 31);
		Grendung_am = cal.getTime();
		
		Geterstand = 0;
		
		Konditions_Gruppe = 0;
		
		Kundenart = (rnd.nextBoolean()?'P':'U');
		
		Kundenbonitat = 0;
		
		Kundenfiliale = 1 + rnd.nextInt(3);
		
		int[] Kundengruppe_vals = {499,550,950}; 
		Kundengruppe = Kundengruppe_vals[rnd.nextInt(Kundengruppe_vals.length)];
		
		Kundensegment = 0;
		
		Kundentyp = (rnd.nextBoolean()?'V':'G');
		
		Landerschlessel = 0;
		
		Nachname = "Nachname" + Kundennummer;
		
		Name1_Teil = "Teil1_" + Kundennummer;
		Name2_Teil = "Teil2_" + Kundennummer;
		
		String[] rs = db.SelectOneRow("select p.plz,left(p.ORTSTEILNAME,27) ORTSTEILNAME "+ 
			                        "from dwh.dwh_dim_plz p "+
			                        "join dwh.DWH_DIM_GEO g on p.ID_GEO=g.ID_GEO " +
			                        "where g.ID_HIERARCHY_LEVEL=6 " +
			                        "order by rand() limit 1  ");
		if (rs.length>0) 
		{
		  Ort = rs[1];
		  PLZ = rs[0];
		}
		
		int[] Rechtsformschless_vals = {0,55};
		Rechtsformschless = Rechtsformschless_vals[rnd.nextInt(Rechtsformschless_vals.length)];
		
		int[] Risikogruppenschl_vals = {0,10};
		Risikogruppenschl = Risikogruppenschl_vals[rnd.nextInt(Risikogruppenschl_vals.length)];
		
		Sortiername = "Sortiername_" + Kundennummer;
		
		StaatsangehUrigk = 0;
		
		Status = 0;
		
		Steuerinlander = 'J';
		
		Stlg_Erwerbsleben = rnd.nextInt(13);
		
		Vorname = "Vorname" + Kundennummer;
		
		Organ_Mitarb_Sl = 0;
		
		Kunden_Status = 1;   // 1,2,3,5
		
		Employee_key = 0;
		
		Kz_Groekredit = 'N';
		
		Kz_Millionenkredit = 'N';	
		
	}
	
	public void set_Kunden_Status (int status)
	{
		Kunden_Status = status;
	}
	
	public int getClientID()
	{
		return this.Kundennummer;
	}
	
	public String toString()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String str;
		
		str= formatter.format(Abzugdatum) + ';' +
			 Kundennummer + ';' +
			 Anredeschlessel + ";" +
			 AP_Kundenbetreuer + ";" +
			 AP_Sachbearbeiter + ";" +
			 Bankint_Klassifiz + ";" +
			 Berufszw_Arbeitgb + ";" +
			 Branche_Beruf + ";" +
			 formatter.format(Datum_Kundenanlage) + ";" +
			 Devisenstatusschl + ";" +
			 Familienstand + ";" +
			 formatter.format(Geburtsdatum) + ";" +
			 Gemeinschaftskunde + ";" +
			 Geschlecht + ";" +
			 formatter.format(Grendung_am) + ";" +
			 Geterstand + ";" +
			 Konditions_Gruppe + ";" +
			 Kundenart + ";" +
			 Kundenbonitat + ";" +
			 Kundenfiliale + ";" +
			 Kundengruppe + ";" +
			 Kundensegment + ";" +
			 Kundentyp + ";" +
			 Landerschlessel + ";" +
			 Nachname + ";" +
			 Name1_Teil + ";" +
			 Name2_Teil + ";" +
			 Ort + ";" +
			 PLZ + ";" +
			 Rechtsformschless + ";" +
			 Risikogruppenschl + ";" +
			 Sortiername + ";" +
			 StaatsangehUrigk + ";" +
			 Status + ";" +
			 Steuerinlander + ";" +
			 Stlg_Erwerbsleben + ";" +
			 Vorname + ";" +
			 Organ_Mitarb_Sl + ";" +
			 Employee_key + ";" +
			 Kunden_Status + ";" +		 
			 Kz_Groekredit + ";" +
			 Kz_Millionenkredit + ";";  
				
		return str;
		
	}
	
}
