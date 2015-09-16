package com.abisgen.main;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



@SuppressWarnings("serial")
public class WinLogin extends Dialog
 {
    private int win_width = 450;
    private int win_hight = 190;
    
    private Label     lbLogin;
    private Label     lbPassword;
    public  TextField tfLogin;
    public  TextField tfPassword;
    private Label     lbConString;
    public  TextField tfConString;
    

    private Button btnOk;
    private Button btnCancel;
    
    public InputParams result;
    public int modalresult;
    private Dialog self;
    
    
    public WinLogin(Frame fr) throws HeadlessException {
        super(fr,true);
        
        this.setSize(win_width, win_hight);
        Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screen_dim.width-win_width)/2, (screen_dim.height-win_hight)/2);
        this.setLayout(new GridBagLayout());
        this.self = this;
        GridBagConstraints c = new GridBagConstraints();
        
        
        c.weightx=0.3;
        c.weighty=0.3;
        
        lbLogin = new Label();
        lbLogin.setAlignment(Label.RIGHT);
        lbLogin.setText("Login:");
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.fill=GridBagConstraints.HORIZONTAL;
        this.add(lbLogin,c);
        
        c.weightx=0.7;
        
        tfLogin = new TextField();
        tfLogin.setPreferredSize(new Dimension(100,25));
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 0;
        c.fill=GridBagConstraints.NONE;
        c.insets = new Insets(0,5,0,0);
        this.add(tfLogin,c);
 
        
        lbPassword = new Label();
        lbPassword.setAlignment(Label.RIGHT);
        lbPassword.setText("Password:");
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.fill=GridBagConstraints.HORIZONTAL;
        this.add(lbPassword, c);
        
        tfPassword = new TextField();
        tfPassword.setPreferredSize(new Dimension(100,25));
        tfPassword.setEchoChar('*');
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 1;
        c.fill=GridBagConstraints.NONE;
        this.add(tfPassword,c);
        
        lbConString = new Label();
        lbConString.setAlignment(Label.RIGHT);
        lbConString.setText("Connection string:");
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        c.fill=GridBagConstraints.HORIZONTAL;
        this.add(lbConString,c);
        
        tfConString = new TextField();
        tfConString.setPreferredSize(new Dimension(300,25));
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 2;
        c.fill=GridBagConstraints.NONE;
        c.insets = new Insets(0,5,0,0);
        this.add(tfConString,c);
 
        
        c.weighty=0.1;
       
        Panel pane = new Panel(new GridLayout(1,2));
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady=10;
        c.insets = new Insets(15,30,5,30);
        c.fill = GridBagConstraints.BOTH;
        this.add(pane,c);
        
        
        btnOk = new Button();
        btnOk.setLabel("Ok");
    //    btnOk.setPreferredSize(new Dimension(70,22));
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                result = new InputParams(tfLogin.getText(), tfPassword.getText(), tfConString.getText());
                modalresult = 1;
                self.setVisible(false);
            }
        });
 //       c.gridx=0;
 //       c.gridy=3;
 //       c.anchor=GridBagConstraints.CENTER;
        pane.add(btnOk);
        
        
        
        btnCancel = new Button();
        btnCancel.setLabel("Cancel");
   //     btnCancel.setPreferredSize(new Dimension(70,22));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modalresult = 0;
                self.setVisible(false);
           }
         });
//        c.gridx=1;
//        c.gridy=3;
//        c.anchor=GridBagConstraints.CENTER;
        pane.add(btnCancel);
       
        
        this.setVisible(true);
    }

    


}
