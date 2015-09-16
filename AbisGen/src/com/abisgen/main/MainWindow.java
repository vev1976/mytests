package com.abisgen.main;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainWindow extends Frame
{
    private static final long serialVersionUID = 6506372392773398608L;
    
    private int win_width = 800;
    private int win_hight = 600;
    private WinAdapter winadapter = new WinAdapter();
    private Frame self;
    
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
        
    }
}
