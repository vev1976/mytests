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
    private String jdbcConn_string = "";
    private String UID = "";
    private String PWD = "";
    private static db2interface instance;
    private Connection con;
    
    private db2interface(InputParams inp) throws SQLException
    {
        UID = inp.getUserName();
        PWD = inp.getPassword();
        jdbcConn_string = inp.getDB_Connection_String();
        con = DriverManager.getConnection(jdbcConn_string, UID, PWD);
    }
    
    public static db2interface getInstance(InputParams inp) throws SQLException{
        if (instance==null) {
            instance = new db2interface(inp);
        }
           return instance;
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

