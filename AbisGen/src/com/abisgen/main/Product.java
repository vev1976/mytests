package com.abisgen.main;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Product {
    @FieldCaption("Abzugdatum")
    private Date Abzugdatum;

    @FieldCaption("Kontonummer")
    private int Kontonummer;

    @FieldCaption("Skontronummer")
    private int Skontronummer;
    
    @FieldCaption("Aderungsdatum")
    private Date Ederungsdatum;
    
    @FieldCaption("BVR-Kto.-Nr.")
    private int BVR_Kto_Nr;
    
    @FieldCaption("Dat. lzt. Buchung")
    private Date Dat_lzt_Buchung;
    
    @FieldCaption("Dat. lzt. Umsatz")
    private Date Dat_lzt_Umsatz;
    
    @FieldCaption("Datum der Anlage")
    private Date Datum_der_Anlage;
    
    @FieldCaption("Datum Auflaung")
    private Date Datum_Auflung;
    
    @FieldCaption("EURO Konto")
    private char EURO_Konto;
    
    @FieldCaption("Filiale")
    private int Filiale;
    
    @FieldCaption("Frist vereinb. LFZ")
    private int Frist_vereinb_LFZ;
    
    @FieldCaption("Fristigkeit Restl.")
    private int Fristigkeit_Restl;
    
    @FieldCaption("Geschatsart")
    private int Geschatsart;
    
    @FieldCaption("INA Kr.Suival.sum")
    private int INA_Kr_Auival_sum;
    
    @FieldCaption("Jur. Laufzeitende.")
    private Date Jur_Laufzeitende;
    
    @FieldCaption("Kapitalsaldo")
    private float Kapitalsaldo;
    
    @FieldCaption("Kontostatus")
    private int Kontostatus;
    
    @FieldCaption("Kundennummer")
    private int Kundennummer;
    
    @FieldCaption("Laufzeit Jahre")
    private int Laufzeit_Jahre;
    
    @FieldCaption("Laufzeit Tage")
    private int Laufzeit_Tage;
    
    @FieldCaption("Laufzeitende echt")
    private Date Laufzeitende_echt;
    
    @FieldCaption("negativer Kap-Sald")
    private float negativer_Kap_Sald;
    
    @FieldCaption("positiver Kap-Sald")
    private float positiver_Kap_Sald;
    
    @FieldCaption("Produktbereich")
    private int Produktbereich;
    
    @FieldCaption("Produktnummer")
    private int Produktnummer;
    
    @FieldCaption("Sachbearbeiter")
    private int Sachbearbeiter;
    
    @FieldCaption("Warung des Kontos")
    private String Werung_des_Kontos;
    
    @FieldCaption("Warung")
    private String Werung;
    
    @FieldCaption("Kurs FW fo 1 EURO")
    private float Kurs_FW_1_EURO;

    private Account acc;
    
    public Product(Date abzugdatum,Client cl, int prod_group, int prod_num)
    {
        int AccNumber;
        
        this.Abzugdatum = abzugdatum;
        this.Kundennummer = cl.getClientID();
        this.Produktbereich = prod_group;
        this.Produktnummer = prod_num;
        
        AccNumber = cl.getLast_Account_Number()+1;
        if (prod_group==1)
           acc = new Account(abzugdatum, this.Kundennummer*100+AccNumber,'A');
        else
           acc = new Account(abzugdatum, this.Kundennummer*100+AccNumber,'P');

        this.Kontonummer = this.Kundennummer*100 + AccNumber;
        cl.setLast_Account_Number(AccNumber);
        
        acc.generate_values();
    }
    
    public Account getAccount()
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
        String str = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        
        @SuppressWarnings("unchecked")
        Class<Product> c = (Class<Product>) this.getClass();
        
        for (Field f : c.getDeclaredFields()) {
            if (f.isAnnotationPresent(FieldCaption.class))
            {
                if (f.getType().getName() == "java.util.Date")
                {
                    try {
                        str = str + (str==""?"":";") + formatter.format((Date) f.get(this));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        str = str + (str==""?"":";") + f.get(this);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
   /*     s = frm.format(Abzugdatum) + ";" +
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
     */
        return str;
    }
    
    
}
