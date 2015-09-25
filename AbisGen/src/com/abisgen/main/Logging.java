package com.abisgen.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public final class Logging {
    private static Logging instance;
    
    private Logging()
    {
      PropertyConfigurator.configure(getClass().getClassLoader().getResourceAsStream("resources/log4j.properties"));
    }
    
    public static Logger getLogger(String classname)
    {
        if (instance == null)
           {instance = new Logging();}
        
        return Logger.getLogger(classname);
    }
}



