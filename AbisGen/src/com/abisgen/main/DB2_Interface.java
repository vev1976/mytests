package com.abisgen.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DB2_Interface {
    private String jdbcConn_string = "";
    private String UID = "";
    private String PWD = "";
    private Logger log = Logging.getLogger(getClass().getName());
    private static DB2_Interface instance;
    private Connection con;
    
    private DB2_Interface()
    {
     }
    
    public static DB2_Interface getInstance() {
        if (instance==null) {
            instance = new DB2_Interface();
        }
           return instance;
    }
    
 
    public boolean tryConnect()
    {
        boolean result = false;
    
        UID = InputParams.getUserName();
        PWD = InputParams.getPassword();
        jdbcConn_string = InputParams.getDB_Connection_String();
        try
        {
          con = DriverManager.getConnection(jdbcConn_string, UID, PWD);
          result = (con != null);
        }
        catch (SQLException e)
        {
          log.error("The ERROR occurs during connecting to DB", e);
        }
        catch (Exception e)
        {
          log.error("Common ERROR occurs during connecting to DB", e);
        }

        return result;
    }
    
    private void checkConnection() throws SQLException
    {
           if (con == null)
        {
            SQLException e = new SQLException("Application was not connected to DataBase!");
            log.error("Application was not connected to DataBase!",e);
            throw e;
        }
    }
    
    public void execSQL(String sql) throws SQLException
    {
        checkConnection();
        try (Statement stm = con.createStatement()){
          stm.executeUpdate(sql);
          con.commit();
        }
    }
    
    public String[] selectOneRow(String sql) throws SQLException
    {
        String[] s = {};
        
        checkConnection();
        try (Statement stm = con.createStatement();)
        {
          
          ResultSet rs = stm.executeQuery(sql);
          if (rs.next())
            {
               ResultSetMetaData rsmd = rs.getMetaData();
               s = new String[rsmd.getColumnCount()];
                
               for(int i=0;i<rsmd.getColumnCount();i++)
               {
                   s[i] = rs.getString(i+1);
               }
            }
          con.commit();
        }
 
        return s;
    }

}

