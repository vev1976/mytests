package com.abisgen.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class genfiles {
	private String filespath;
	private Date abzugdatum;
	private int Count;
	private db2interface db;
	private String KD_Caption = "Abzugdatum;Kundennummer;Anredeschlessel;AP Kundenbetreuer;AP Sachbearbeiter;Bankint. Klassifiz;Berufszw.Arbeitgb.;Branche/Beruf;Datum Kundenanlage;Devisenstatusschl.;Familienstand;Geburtsdatum;Gemeinschaftskunde;Geschlecht;Grendung am;Geterstand;Konditions-Gruppe;Kundenart;Kundenbonitet;Kundenfiliale;Kundengruppe;Kundensegment;Kundentyp;LanderschlǬssel;Nachname;Name 1.Teil;Name 2.Teil;Ort;PLZ;Rechtsformschless.;Risikogruppenschl.;Sortiername;Staatsangeherigk.;Status;Steuerinlander;Stlg. Erwerbsleben;Vorname;Organ/Mitarb.-Sl.;Employee_key;Kunden-Status;Kz Groekredit;Kz Millionenkredit";
	private String KT_Caption = "Abzugdatum;Kontonummer;Skontronummer;Aderungsdatum;BVR-Kto.-Nr.;Dat. lzt. Buchung;Dat. lzt. Umsatz;Datum der Anlage;Datum Auflaung;EURO Konto;Filiale;Frist vereinb. LFZ;Fristigkeit Restl.;Geschatsart;INA Kr.Suival.sum;Jur. Laufzeitende.;Kapitalsaldo;Kontostatus;Kundennummer;Laufzeit Jahre;Laufzeit Tage;Laufzeitende echt;negativer Kap-Sald;positiver Kap-Sald;Produktbereich;Produktnummer;Sachbearbeiter;Warung des Kontos;Warung;Kurs FW fo 1 EURO";
	private String ST_Caption = "Abzugdatum;Kontonummer;Skontronummer;Hauptbuchkontonr.;Warung Hauptbuch;Depot-Kontonummer;DepotSkontronummer;WP Kennr.Depot;Derivat BBS-Konto;Datum Kt-Zeitr.bis;Schl. Bilanz-Seite;BBS-Sts Konto;BVR-Kto.-Nr.Ztr;DS.Eff.Saldo Jahr;DS.Eff.Saldo Mon.;DS.Nom.Saldo Jahr;DS.Nom.Saldo Mon.;DS.Val.Saldo Jahr;DS.Val.Saldo Mon.;DS.Val.Sld 30 Tg;DS-Zins Val. Jhr;DS-Zins Val. Mon;Eff. Saldo;Eff.Zins DS Jahr;Eff.Zins DS Monat;Eff-Zinsen Jahr;Eff-Zinsen Mon.;Eff-Zinsen STG;Effektivzinss.Ztr;Fremdmittelbtr.Ztr;K.-marge DS Jahr;K.-marge DS Monat;Kz Festzins Ztr.;Kz Kontokorrektur;Val-Zinsen Jahr;Val-Zinsen Mon.;Val-Zinsen STG;Valutasaldo;Zinssatz nominell;Nominal-Saldo";

	public genfiles(String filespath, Date abzugdatum,int count,db2interface db)
	{
		this.filespath = filespath;
		this.abzugdatum = abzugdatum;
		this.Count = count;
		this.db = db;
	}

	public void generate()
	{
	    PrintWriter abiskd;
	    PrintWriter abiskt;
	    PrintWriter abisst;
	    
	    SimpleDateFormat fmt = new SimpleDateFormat("yyMM");
	    
		try {		
			
			abiskd = new PrintWriter(filespath+"ABISKD"+fmt.format(abzugdatum)+".csv", "ASCII");
			abiskt = new PrintWriter(filespath+"ABISKT"+fmt.format(abzugdatum)+".csv", "ASCII");
			abisst = new PrintWriter(filespath+"ABISST"+fmt.format(abzugdatum)+".csv", "ASCII");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
				
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(abzugdatum);
		cal.add(Calendar.MONTH, 1);
		abzugdatum=cal.getTime();
		
		try {
			abiskd.append(KD_Caption+'\n');
			abiskt.append(KT_Caption+'\n');
			abisst.append(ST_Caption+'\n');			
			
			for (int i=1;i<=Count;i++)
			{
			   client cl = new client(this.abzugdatum,i);
			   cl.generate_values(db);
			   
			   product prod11 = new product(this.abzugdatum,cl,1,1);
			   product prod12 = new product(this.abzugdatum,cl,1,2);
			   product prod21 = new product(this.abzugdatum,cl,2,1);
			   product prod22 = new product(this.abzugdatum,cl,2,2);
			   
			   prod11.generate_values();
			   prod12.generate_values();
			   prod21.generate_values();
			   prod22.generate_values();
			   
			   abiskd.append(cl.toString()+'\n');
			   
			   abiskt.append(prod11.toString()+'\n');
			   abiskt.append(prod12.toString()+'\n');
			   abiskt.append(prod21.toString()+'\n');
			   abiskt.append(prod22.toString()+'\n');
			   
			   abisst.append(prod11.getAccount().toString()+'\n');
			   abisst.append(prod12.getAccount().toString()+'\n');
			   abisst.append(prod21.getAccount().toString()+'\n');
			   abisst.append(prod22.getAccount().toString()+'\n');			   
			   
			}
		} 
		finally
		{
			abiskd.close();
			abiskt.close();
			abisst.close();			
		}
		
		
	}
}
