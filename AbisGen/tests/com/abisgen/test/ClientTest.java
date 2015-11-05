package com.abisgen.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.abisgen.main.Client;
import com.abisgen.main.DB2_Interface;
import com.abisgen.main.InputParams;

public class ClientTest {
    private String uid = "";
    private String pwd = "";
    private String con_str = "jdbc:db2://10.130.33.3:50008/testvev";
    private Date d = new Date();
    private Integer cl_number = 1;
    private Client cl;
    private DB2_Interface db;
    
    @Before
    public void precondition() {
        cl = new Client(d,cl_number);
        InputParams.setUserName(uid);
        InputParams.setPassword(pwd);
        InputParams.setDB_Connection_String(con_str);
        db = DB2_Interface.getInstance();
        
        if (!db.tryConnect()) org.junit.Assert.fail("DB connection error");
    }
    
    @Test
    public void testGenerating()
    {
        cl.generate_values();
        String res = cl.toString();
        String[] arr = res.split(";");
        for(int i=0;i<arr.length;i++)
        {
            org.junit.Assert.assertNotEquals(String.format("The field %n equal Null",i),"",arr[i]);
        }
        
    }

}
