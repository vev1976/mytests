package com.abisgen.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainWindow extends Frame
{
    private static final long serialVersionUID = 6506372392773398608L;
    
    private int win_width = 800;
    private int win_hight = 600;
    private WinAdapter winadapter = new WinAdapter();
    private Frame self;
    private TextArea ta_log;
    
    public InputParams inputparams;
    
    private class WinAdapter extends WindowAdapter
    {
        public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }
    }
    
    
    public MainWindow (String title) throws HeadlessException
    {
         super(title);
         self = this;
         this.setSize(win_width, win_hight);
         Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation((screen_dim.width-win_width)/2, (screen_dim.height-win_hight)/2);
         this.addWindowListener(winadapter);
         this.BuildFace();
         
    }
    
    private void BuildFace()
    {
         this.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();
         
         c.weightx = 0.8;
         c.weighty = 0.3;
         
         Panel p_input = new Panel();
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 0;
         c.gridy = 0;
         c.anchor = GridBagConstraints.NORTHWEST;
         this.add(p_input,c);
         
         Panel p_buttons = new Panel(new GridLayout(1,3));
         c.weightx = 0.2;
         c.weighty = 0.3;
         c.fill = GridBagConstraints.NONE;
         c.gridx = 1;
         c.gridy = 0;
         c.anchor = GridBagConstraints.NORTHEAST;
         this.add(p_buttons,c);
         
         Button btnExit = new Button();
         btnExit.setLabel("Exit");
    //     btnCancel.setPreferredSize(new Dimension(70,22));
         btnExit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 System.exit(0);
             }
          });
         p_buttons.add(btnExit);
         
         Panel p_log = new Panel(new GridLayout(1,1));
         c.weightx = 1;
         c.weighty = 0.7;
         c.fill = GridBagConstraints.BOTH;
         c.gridx = 0;
         c.gridy = 1;
         c.gridwidth = 2;
         c.anchor = GridBagConstraints.CENTER;
         this.add(p_log,c);
         
         ta_log = new TextArea();
         
         p_log.add(ta_log);
        
         
         
    }
}
