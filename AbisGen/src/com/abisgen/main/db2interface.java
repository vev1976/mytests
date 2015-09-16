package com.abisgen.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class db2interface {
    private String driver = "jdbc:db2://";
    private String server = "/";
    private String database = "";
    private String UID = "";
    private String PWD = "";
    
    private Connection con;
    
    public db2interface() throws SQLException
    {
        con = DriverManager.getConnection(driver+server+database, UID, PWD);
    }
    
    public void ExecSQL(String sql) throws SQLException
    {
        Statement stm = con.createStatement();
        stm.executeUpdate(sql);
        stm.close();
        con.commit();
    }
    
    public String[] SelectOneRow(String sql)
    {
        Statement stm = null;
        String[] s = {};
        
        
        try {
            stm = con.createStatement();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (stm!=null)
                    stm.close();
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        return s;
    }

}

