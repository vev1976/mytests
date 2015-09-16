package com.abisgen.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class account {

private	Date Abzugdatum;
private Integer Kontonummer;
private Integer Skontronummer;
private Integer Hauptbuchkontonr;
private String Werung_Hauptbuch;
private Integer Depot_Kontonummer;
private Integer DepotSkontronummer;
private Integer WP_Kennr_Depot;
private Integer Derivat_BBS_Konto;
private	Date Datum_Kt_Zeitr_bis;
private char Schl_Bilanz_Seite;
private Integer BBS_Sts_Konto;
private Integer BVR_Kto_Nr_Ztr;
private float DS_Eff_Saldo_Jahr;
private float DS_Eff_Saldo_Mon;
private float DS_Nom_Saldo_Jahr;
private float DS_Nom_Saldo_Mon;
private float DS_Val_Saldo_Jahr;
private float DS_Val_Saldo_Mon;
private float DS_Val_Sld_30_Tg;
private float DS_Zins_Val_Jhr;
private float DS_Zins_Val_Mon;
private float Eff_Saldo;
private float Eff_Zins_DS_Jahr;
private float Eff_Zins_DS_Monat;
private float Eff_Zinsen_Jahr;
private float Eff_Zinsen_Mon;
private float Eff_Zinsen_STG;
private float Effektivzinss_Ztr;
private float Fremdmittelbtr_Ztr;
private float K_marge_DS_Jahr;
private float K_marge_DS_Monat;
private char Kz_Festzins_Ztr;
private char Kz_Kontokorrektur;
private float Val_Zinsen_Jahr;
private float Val_Zinsen_Mon;
private float Val_Zinsen_STG;
private float Valutasaldo;
private float Zinssatz_nominell;
private float Nominal_Saldo;

public account(Date abzugdatum,int acc_id,char a_p)
{
	this.Abzugdatum = abzugdatum;
	this.Kontonummer = acc_id;
	this.Schl_Bilanz_Seite = a_p;	
}

public void generate_values()
{
	Random rnd = new Random();
	int ActPass = (Schl_Bilanz_Seite=='A'?-1:1);
	
	Skontronummer = 0;
	Hauptbuchkontonr = 0;
	Werung_Hauptbuch = "";
	Depot_Kontonummer = 0;
	DepotSkontronummer = 0;
	WP_Kennr_Depot = 0;
	Derivat_BBS_Konto = 0;
	Datum_Kt_Zeitr_bis = Abzugdatum;
	
	int[] BBS_Sts_Konto_vals = {12,15,30,37}; 
	BBS_Sts_Konto = BBS_Sts_Konto_vals[rnd.nextInt(BBS_Sts_Konto_vals.length)];
	
	int[] BVR_Kto_Nr_Ztr_vals = {1100,1400,1405,1430,1434,1772,1831,2000,2110,2140,3311,5102,6025,6200};
	BVR_Kto_Nr_Ztr = BVR_Kto_Nr_Ztr_vals[rnd.nextInt(BVR_Kto_Nr_Ztr_vals.length)];
	
	DS_Eff_Saldo_Jahr = (float) ((float) ActPass*(rnd.nextInt(100000000)/100.0));
	DS_Eff_Saldo_Mon = (float) ((float) ActPass*(rnd.nextInt(100000000)/100.0));;
	DS_Nom_Saldo_Jahr = (float) (DS_Eff_Saldo_Jahr*(1.05));
	DS_Nom_Saldo_Mon = DS_Eff_Saldo_Mon;
	DS_Val_Saldo_Jahr = DS_Nom_Saldo_Jahr;
	DS_Val_Saldo_Mon = DS_Eff_Saldo_Mon;
	DS_Val_Sld_30_Tg = DS_Eff_Saldo_Mon;
	DS_Zins_Val_Jhr = (float) ((rnd.nextInt(80000)+10000)/10000.0);
	DS_Zins_Val_Mon = (float) (DS_Zins_Val_Jhr*0.75);
	Eff_Saldo = DS_Eff_Saldo_Mon;
	Eff_Zins_DS_Jahr = (float)((rnd.nextInt(60000)+10000)/10000.0);
	Eff_Zins_DS_Monat = Eff_Zins_DS_Jahr;
	Eff_Zinsen_Jahr = (float)((rnd.nextInt(3000000)+1000000)/100.0);
	Eff_Zinsen_Mon = (float)((rnd.nextInt(900000)+100000)/100.0);
	Eff_Zinsen_STG = (float)((rnd.nextInt(90000)+10000)/100.0);
	Effektivzinss_Ztr = (float)(rnd.nextInt(1000)/100.0);
	Fremdmittelbtr_Ztr = (float)0.0;
	K_marge_DS_Jahr = (float)(rnd.nextInt(1000)/100.0);
	K_marge_DS_Monat = (float)(rnd.nextInt(1000)/100.0);
	Kz_Festzins_Ztr = (rnd.nextBoolean()?'N':'J');
	Kz_Kontokorrektur = 'N';
	Val_Zinsen_Jahr = Eff_Zinsen_Jahr;
	Val_Zinsen_Mon = Eff_Zinsen_Mon;
	Val_Zinsen_STG = (float)0.0;
	Valutasaldo = DS_Eff_Saldo_Mon;
	Zinssatz_nominell = Effektivzinss_Ztr;
	Nominal_Saldo = Valutasaldo;
	
}

public int getBVR_Kto_Nr_Ztr()
{
	return BVR_Kto_Nr_Ztr;
}

public String toString()
{
  String s = "";
  SimpleDateFormat frm = new SimpleDateFormat("dd.MM.yyyy");

  s =   frm.format(Abzugdatum) + ";" +
		Kontonummer + ";" +
		Skontronummer + ";" +
		Hauptbuchkontonr + ";" +
		Werung_Hauptbuch + ";" +
		Depot_Kontonummer + ";" +
		DepotSkontronummer + ";" +
		WP_Kennr_Depot + ";" +
		Derivat_BBS_Konto + ";" +
		frm.format(Datum_Kt_Zeitr_bis) + ";" +
		Schl_Bilanz_Seite + ";" +
		BBS_Sts_Konto + ";" +
		BVR_Kto_Nr_Ztr + ";" +
		DS_Eff_Saldo_Jahr + ";" +
		DS_Eff_Saldo_Mon + ";" +
		DS_Nom_Saldo_Jahr + ";" +
		DS_Nom_Saldo_Mon + ";" +
		DS_Val_Saldo_Jahr + ";" +
		DS_Val_Saldo_Mon + ";" +
		DS_Val_Sld_30_Tg + ";" +
		DS_Zins_Val_Jhr + ";" +
		DS_Zins_Val_Mon + ";" +
		Eff_Saldo + ";" +
		Eff_Zins_DS_Jahr + ";" +
		Eff_Zins_DS_Monat + ";" +
		Eff_Zinsen_Jahr + ";" +
		Eff_Zinsen_Mon + ";" +
		Eff_Zinsen_STG + ";" +
		Effektivzinss_Ztr + ";" +
		Fremdmittelbtr_Ztr + ";" +
		K_marge_DS_Jahr + ";" +
		K_marge_DS_Monat + ";" +
		Kz_Festzins_Ztr + ";" +
		Kz_Kontokorrektur + ";" +
		Val_Zinsen_Jahr + ";" +
		Val_Zinsen_Mon + ";" +
		Val_Zinsen_STG + ";" +
		Valutasaldo + ";" +
		Zinssatz_nominell + ";" +
		Nominal_Saldo; 
  
  return s;
}


}
