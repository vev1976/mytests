package com.abisgen.main;

public class InputParams {
    private String username;
    private String password;
    private String db_connection_string;
    
    public InputParams()
    {
    }

    
    public InputParams(String username, String password, String db_connection_string)
    {
        this.username = username;
        setPassword(password);
        this.db_connection_string = db_connection_string;
    }
    
    public String getUserName ()
    {
        return username;
    }

    public void setUserName (String username)
    {
        this.username = username;
    }

    public String getPassword ()
    {
        return password;
    }
    
    public void setPassword (String password)
    {
        this.password = password;
    }
    
    public String getDB_Connection_String()
    {
        return this.db_connection_string;
    }

    public void setDB_Connection_String(String DB_Connection_String)
    {
        this.db_connection_string = DB_Connection_String;
    }

    
}