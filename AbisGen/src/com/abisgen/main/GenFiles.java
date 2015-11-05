package com.abisgen.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.abisgen.main.Product.ProductTypes;

public class GenFiles
      implements Runnable
{
    private String filespath;
    private Date abzugdatum;
    private int Count;
    private String KD_Caption;
    private String KT_Caption;
    private String ST_Caption;

    private Logger log = Logging.getLogger(GenFiles.class.getName());
    
    public GenFiles(String filespath, Date abzugdatum,int count)
    {
        this.filespath = filespath;
        this.abzugdatum = abzugdatum;
        this.Count = count;
        ST_Caption = GetCaptions(Account.class);
        KD_Caption = GetCaptions(Client.class);
        KT_Caption = GetCaptions(Product.class);
    }

    private String GetCaptions(@SuppressWarnings("rawtypes") Class c){
        String s = "";
        if (c == null) return s;
        for (Field m : c.getDeclaredFields()){
            if (m.isAnnotationPresent(FieldCaption.class))
                s = s +(s==""?"":";") + m.getAnnotation(FieldCaption.class).value();
        }
        return s;
    }
    
    public void generate()
    {
        
        GregorianCalendar abzug = new GregorianCalendar();
        abzug.setTime(abzugdatum);
        log.info(String.format("Generatig data for 01/%02d/%d was started!",abzug.get(Calendar.MONTH)+1,abzug.get(Calendar.YEAR)));
        
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
               Client cl = new Client(this.abzugdatum,i);
               cl.generate_values();
               
               Product prod11 = new Product(this.abzugdatum,cl,ProductTypes.Active,1);
               Product prod12 = new Product(this.abzugdatum,cl,ProductTypes.Active,2);
               Product prod21 = new Product(this.abzugdatum,cl,ProductTypes.Passive,1);
               Product prod22 = new Product(this.abzugdatum,cl,ProductTypes.Passive,2);
               
               prod11.generate_values();
               prod12.generate_values();
               prod21.generate_values();
               prod22.generate_values();
               
               cl.save_to_file(abiskd);
               
               prod11.save_to_file(abiskt);
               prod12.save_to_file(abiskt);
               prod21.save_to_file(abiskt);
               prod22.save_to_file(abiskt);
              
               prod11.getAccount().save_to_file(abisst);
               prod12.getAccount().save_to_file(abisst);
               prod21.getAccount().save_to_file(abisst);
               prod22.getAccount().save_to_file(abisst);
            }
        }
        finally
        {
            abiskd.close();
            abiskt.close();
            abisst.close();
        }
        log.info(String.format("Data for 01/%02d/%d has been generated!",abzug.get(Calendar.MONTH)+1,abzug.get(Calendar.YEAR)));
    }

    @Override
    public void run() {
        generate();
    }
}
