package com.abisgen.main;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.log4j.Logger;




public class Client implements DataObject{

    @FieldCaption("Abzugdatum")
    private Date Abzugdatum;
 
    @FieldCaption("Kundennummer")
    private Integer Kundennummer;
    
    @FieldCaption("Anredeschlessel")
    private Integer Anredeschlessel;
    
    @FieldCaption("AP Kundenbetreuer")
    private Integer AP_Kundenbetreuer;
    
    @FieldCaption("AP Sachbearbeiter")
    private Integer AP_Sachbearbeiter;
    
    @FieldCaption("Bankint. Klassifiz")
    private Integer Bankint_Klassifiz;
    
    @FieldCaption("Berufszw.Arbeitgb.")
    private Integer Berufszw_Arbeitgb;
    
    @FieldCaption("Branche/Beruf")
    private Integer Branche_Beruf;
    
    @FieldCaption("Datum Kundenanlage")
    private Date Datum_Kundenanlage;
    
    @FieldCaption("Devisenstatusschl.")
    private Integer Devisenstatusschl;
    
    @FieldCaption("Familienstand")
    private Integer Familienstand;
    
    @FieldCaption("Geburtsdatum")
    private Date Geburtsdatum;
    
    @FieldCaption("Gemeinschaftskunde")
    private char Gemeinschaftskunde;
    
    @FieldCaption("Geschlecht")
    private Integer Geschlecht;
    
    @FieldCaption("Grendung am")
    private Date Grendung_am;
    
    @FieldCaption("Geterstand")
    private Integer Geterstand;
    
    @FieldCaption("Konditions-Gruppe")
    private Integer Konditions_Gruppe;
    
    @FieldCaption("Kundenart")
    private char Kundenart;
    
    @FieldCaption("Kundenbonitet")
    private Integer Kundenbonitat;
    
    @FieldCaption("Kundenfiliale")
    private Integer Kundenfiliale;
    
    @FieldCaption("Kundengruppe")
    private Integer Kundengruppe;
    
    @FieldCaption("Kundensegment")
    private Integer Kundensegment;
    
    @FieldCaption("Kundentyp")
    private char Kundentyp;
    
    @FieldCaption("LanderschlǬssel")
    private Integer Landerschlessel;
    
    @FieldCaption("Nachname")
    private String Nachname;
    
    @FieldCaption("Name 1.Teil")
    private String Name1_Teil;
    
    @FieldCaption("Name 2.Teil")
    private String Name2_Teil;
    
    @FieldCaption("Ort")
    private String Ort;
    
    @FieldCaption("PLZ")
    private String PLZ;
    
    @FieldCaption("Rechtsformschless.")
    private Integer Rechtsformschless;
    
    @FieldCaption("Risikogruppenschl.")
    private Integer Risikogruppenschl;
    
    @FieldCaption("Sortiername")
    private String Sortiername;
    
    @FieldCaption("Staatsangeherigk.")
    private Integer StaatsangehUrigk;
    
    @FieldCaption("Status")
    private Integer Status;
    
    @FieldCaption("Steuerinlander")
    private char Steuerinlander;
    
    @FieldCaption("Stlg. Erwerbsleben")
    private Integer Stlg_Erwerbsleben;
    
    @FieldCaption("Vorname")
    private String Vorname;
    
    @FieldCaption("Organ/Mitarb.-Sl.")
    private Integer Organ_Mitarb_Sl;
    
    @FieldCaption("Kunden-Status")
    private Integer Kunden_Status;
    
    @FieldCaption("Employee_key")
    private int Employee_key;
    
    @FieldCaption("Kz Groekredit")
    private char Kz_Groekredit;
    
    @FieldCaption("Kz Millionenkredit")
    private char Kz_Millionenkredit;
  
    
    private int Last_Account_Number;
    private Logger log = Logging.getLogger(getClass().getName());
    
    public Client(Date abzugdatum,int cl_num)
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
    
    public void generate_values()
    {
        DB2_Interface db = DB2_Interface.getInstance();
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
        
        try {
            String[] rs = db.selectOneRow("select p.plz,left(p.ORTSTEILNAME,27) ORTSTEILNAME "+
                                        "from dwh.dwh_dim_plz p "+
                                        "join dwh.DWH_DIM_GEO g on p.ID_GEO=g.ID_GEO " +
                                        "where g.ID_HIERARCHY_LEVEL=6 " +
                                        "order by rand() limit 1  ");
            if (rs.length > 1)
            {
              Ort = rs[1];
              PLZ = rs[0];
            }

        }
        catch (SQLException e)
        {
            log.error("Can't get geo data from DB!", e);
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
        String str = "";
        @SuppressWarnings("unchecked")
        Class<Client> c = (Class<Client>) this.getClass();
        
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
     
        return str;
    }
    
    public void save_to_file(PrintWriter pw)
    {
        pw.append(this.toString()+'\n');
    }
    
}
