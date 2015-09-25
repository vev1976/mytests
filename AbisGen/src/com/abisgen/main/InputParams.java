package com.abisgen.main;

public class InputParams {
    private static String username;
    private static String password;
    private static String db_connection_string;
    
    public InputParams()
    {
    }
   
 /*   public InputParams(String username, String password, String db_connection_string)
    {
        this.username = username;
        setPassword(password);
        this.db_connection_string = db_connection_string;
    }
 */

    public static String getUserName ()
    {
        return username;
    }

    public static void setUserName (String _username)
    {
        username = _username;
    }

    public static String getPassword ()
    {
        return password;
    }
    
    public static void setPassword (String _password)
    {
        password = _password;
    }
    
    public static String getDB_Connection_String()
    {
        return db_connection_string;
    }

    public static void setDB_Connection_String(String _db_Connection_String)
    {
        db_connection_string = _db_Connection_String;
    }

    
}