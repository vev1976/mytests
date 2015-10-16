package com.abisgen.main;

import org.apache.log4j.Logger;

public class ABIS_DataGenerator {
   
    public static void main(String[] args) {
        Logger log = Logging.getLogger(ABIS_DataGenerator.class.getName());
        
        MainWindow mw = new MainWindow("Abis files generating");
        WinLogin win_login = new WinLogin(mw);
        // test comment
        if (win_login.modalresult != 1)
        {
           mw.dispose();
           return;
        }
         
        DB2_Interface db  = DB2_Interface.getInstance();
        if (!db.tryConnect())
        {
           mw.dispose();
           return;
        }
        
        log.info("Application has started!\n");
        mw.setVisible(true);
    }
    

    
}
