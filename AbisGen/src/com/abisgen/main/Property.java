package com.abisgen.main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Property {
  private static Property instance;
  private Properties prop;
  private String PROPERTY_FILE_NAME = "config.properties";
  private String DEFAULT_PROPERTY_FILE_NAME = "resources/default_config.properties";
    
  private Property()
  {
      prop = new Properties();
      if (!load()) {loadFromDefault();}
  }
  
  private boolean loadFromDefault()
  {
    boolean res = false;
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_PROPERTY_FILE_NAME);
    
    if (inputStream != null) {
        try {
            prop.load(inputStream);
            res = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
    //throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
    }
    return res;
  }
  
  private boolean load()
  {
    boolean res = false;
    try (FileInputStream inputStream = new FileInputStream(PROPERTY_FILE_NAME))
    {
        prop.load(inputStream);
        res = true;
    }
      catch (FileNotFoundException e)
       {}
      catch (IOException e)
       {}
    
    return res;
  }
  public static Property getInstance()
  {
      if (instance == null) {
          instance = new Property();
      }
      return instance;
  }
  
  public String getProperty(String propertyName, String defaultValue) {
      return System.getProperty(propertyName, prop.getProperty(propertyName, defaultValue));
  }

  public void setProperty(String propertyName, String propertyValue) {
      prop.setProperty(propertyName, propertyValue);
  }
  
  public void saveProperties()
  {
      if (prop != null)
      {
        try (FileOutputStream out = new FileOutputStream(PROPERTY_FILE_NAME))
        {
            prop.store(out, "test");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
      }
      
  }
  
  
  
}
