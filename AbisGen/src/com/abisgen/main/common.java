package com.abisgen.main;



public class common {
    

    public static void main(String[] args) {
        
        MainWindow mw = new MainWindow("Abis files generating");
        WinLogin wLogin = new WinLogin(mw);
        
        if (wLogin.modalresult == 1)
                {
                  try {
                      db2interface.getInstance(wLogin.result);
                      mw.inputparams = wLogin.result;
                      mw.setVisible(true);
                  }
                  catch (Exception e)
                  {
                      e.printStackTrace();
                  }
                }
        else
                {
                  mw.dispose();
                };
      
    }
    

    
}
