package com.abisgen.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
    private int from_y,from_m,to_y,to_m;
    
    private Choice ch_from_y = new Choice();
    private Choice ch_from_m = new Choice();
    private Choice ch_to_y = new Choice();
    private Choice ch_to_m = new Choice();

    
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
         this.prepareGUI();
         
    }
    
    private void prepareGUI()
    {
         this.setLayout(new BorderLayout());
         GridBagConstraints c = new GridBagConstraints();
         
         Panel p_top = new Panel(new BorderLayout());
         this.add(p_top, BorderLayout.PAGE_START);
         Panel p_input = new Panel(new GridLayout(1,2));
         p_top.add(p_input, BorderLayout.CENTER);

         p_input.setMinimumSize(new Dimension(640,80));
         p_input.setPreferredSize(new Dimension(640,80));
         p_input.setMaximumSize(new Dimension(640,80));
         
               Panel p = new Panel(new FlowLayout(FlowLayout.LEADING));
               p_input.add(p);
               Label l = new Label("Period from ");
               l.setAlignment(Label.LEFT);
               p.add(l, c);
           
               for (Integer i=2014;i<2019;i++) {
                   ch_from_y.addItem(i.toString());
                   ch_to_y.addItem(i.toString());
               }
               
               for (Integer i=1;i<=12;i++) {
                   ch_from_m.addItem(i.toString());
                   ch_to_m.addItem(i.toString());
               }
               
               ch_from_y.addItemListener(new ItemListener(){
                      public void itemStateChanged(ItemEvent arg0) { from_y = Integer.valueOf(ch_from_y.getSelectedItem()); }
               });
               ch_from_m.addItemListener(new ItemListener(){
                      public void itemStateChanged(ItemEvent arg0) { from_m = Integer.valueOf(ch_from_m.getSelectedItem()); }
               });
               ch_to_y.addItemListener(new ItemListener(){
                      public void itemStateChanged(ItemEvent arg0) { to_y = Integer.valueOf(ch_to_y.getSelectedItem()); }
               });
               ch_to_m.addItemListener(new ItemListener(){
                      public void itemStateChanged(ItemEvent arg0) { to_m = Integer.valueOf(ch_to_m.getSelectedItem()); }
               });             
               p.add(ch_from_y, c);
               p.add(ch_from_m, c);
               p.add(new Label(" to "), c);
               p.add(ch_to_y, c);
               p.add(ch_to_m, c);                             
         
         Panel p_buttons = new Panel(new GridLayout(1,3));
         p_top.add(p_buttons, BorderLayout.EAST);
         p_buttons.setMinimumSize(new Dimension(60,80));
         p_buttons.setPreferredSize(new Dimension(60,80));
         p_buttons.setMaximumSize(new Dimension(60,80));
         
         p_buttons.getInsets().top = 50;

         Button btnExit = new Button();
         btnExit.setLabel("Exit");
         btnExit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 System.exit(0);
             }
          });
         p_buttons.add(btnExit);
         
         Panel p_log = new Panel(new BorderLayout());
         this.add(p_log,BorderLayout.CENTER);       
         ta_log = new TextArea();
         p_log.add(ta_log,BorderLayout.CENTER);
         
    }
    
    
}
