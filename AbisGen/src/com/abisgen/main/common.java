package com.abisgen.main;

import java.sql.SQLException;
import java.util.Calendar;



public class common {
    

    public static void main(String[] args) throws SQLException {
        
        int m_first = 6,
            y_first = 2015,
            m_last = 6,
            y_last = 2015;
        String path = "//Users//velichko//Documents//AbisFiles//Generated//";
        int client_count = 10000;
        Calendar abzugdatum = Calendar.getInstance();
        
        MainWindow mw = new MainWindow("Abis files generating");
        WinLogin wLogin = new WinLogin(mw);
        
        if (wLogin.modalresult == 1)
                {
                  mw.inputparams = wLogin.result;
                  mw.setVisible(true);
                }
        else
                {
                  mw.dispose();
                };
        
        /*
        db2interface db = new db2interface();
        
        
        while ((y_first*100 + m_first)<=(y_last*100 + m_last))
        {
        
         abzugdatum.set(y_first, m_first, 01);
         System.out.printf("Generatig data for %d/%d was started!\n",m_first+1,y_first);
         genfiles gn = new genfiles(path,abzugdatum.getTime(),client_count,db);
         gn.generate();
         System.out.printf("Data for %d/%d has generated!\n",m_first+1,y_first);

         m_first = (m_first==11?0:m_first+1);
         y_first = (m_first==0?y_first+1:y_first);
        }
        */
        //wLogin.dispose();
    }
    

    
}
