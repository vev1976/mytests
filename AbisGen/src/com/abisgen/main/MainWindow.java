package com.abisgen.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;


public class MainWindow extends JFrame
{
    private static final long serialVersionUID = 6506372392773398608L;
    private Logger log = Logging.getLogger(MainWindow.class.getName());
    
    private int win_width = 800;
    private int win_hight = 600;
    private WinAdapter winadapter = new WinAdapter();
    private Frame self;
    private TextArea ta_log;
    private int from_y = 2014,from_m = 1, to_y = 2014, to_m = 1;
    private String outfolder = "";
    private int clientNumber = 0;
    private RunThreads runthreads;
    
    private Button btnCalc = new Button();
    private Choice ch_from_y = new Choice();
    private Choice ch_from_m = new Choice();
    private Choice ch_to_y = new Choice();
    private Choice ch_to_m = new Choice();
    private TextField tfFolder = new TextField();
    private JSpinner tfClientNum = new JSpinner();
    
    
    
    private class WinAdapter extends WindowAdapter
    {
        public void windowClosing(WindowEvent windowEvent){
            onClose();
         }
    }
    
    private class RunThreads implements Runnable {
        public boolean stop_execition = false;
        @Override
        public void run() {
            Calendar abzugdatum = Calendar.getInstance();
            int y1 = from_y,m1 = from_m, y2 = to_y, m2 = to_m;
            
            DB2_Interface db = DB2_Interface.getInstance();
            try
            {
                runthreads = this;
                btnCalc.setLabel("Stop");
                btnCalc.setActionCommand("Stop");
                
              outcycle:
                while ((y1*100 + m1)<=(y2*100 + m2))
                {
                      int numofthreads = 2;
                      Thread[] threads = new Thread[numofthreads];
                      for (int i=0;i<threads.length;i++){
                             abzugdatum.set(y1, m1 - 1, 01);
                             GenFiles gn = new GenFiles(outfolder,abzugdatum.getTime(),clientNumber,db);
                             //gn.generate();
                             threads[i] = new Thread(gn,String.format("01/%02d/%d",m1,y1));
                             threads[i].start();
                             m1 = (m1==12? 1   :m1+1);
                             y1 = (m1==1? y1+1 : y1 );
                             if ((y1*100 + m1)>(y2*100 + m2)) break;
                             if (stop_execition) break outcycle;
                       }
                      for (int i=0; i<threads.length; i++)
                             if (threads[i] != null)
                               try {
                                       if (stop_execition) break outcycle;
                                       if (threads[i] != null) threads[i].join();
                                   } catch (InterruptedException e) {
                                       log.error(e.getMessage(),e);
                                   }
                 }
            }
            finally {
                btnCalc.setLabel("Calculate");
                btnCalc.setActionCommand("Calculate");
                runthreads = null;
                log.info("Calculation was finished!");
             }
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
         Logger.getRootLogger().addAppender(new TextAreaLog4jAppender(ta_log));
    }
    
    public void onClose()
    {
         Property prop = Property.getInstance();
         prop.setProperty("UserName", InputParams.getUserName());
         prop.setProperty("ConnectionString", InputParams.getDB_Connection_String());
         prop.setProperty("OutputFolder", outfolder);
         prop.setProperty("Number_of_clients", String.valueOf(clientNumber));
         prop.saveProperties();

         System.exit(0);
    }
    
    public void calculate()
    {
        log.info("Data "+ from_y + " " + from_m + " " + to_y + " " + to_m + " " + clientNumber);
        log.info("Folder: "+ outfolder);
        RunThreads rt = new RunThreads();
        Thread t = new Thread(rt,"common thread");
        t.start();
    }
    
    private void prepareGUI()
    {
         Property prop = Property.getInstance();
         
         getContentPane().setLayout(new BorderLayout());
         
         Panel p_top = new Panel(new BorderLayout());
         getContentPane().add(p_top, BorderLayout.PAGE_START);
         
         Panel p_input = new Panel(new GridLayout(3,1));
         p_top.add(p_input, BorderLayout.CENTER);

         p_input.setMinimumSize(new Dimension(640,100));
         p_input.setPreferredSize(new Dimension(640,100));
         p_input.setMaximumSize(new Dimension(640,100));
         
               Panel p = new Panel(new FlowLayout(FlowLayout.LEADING));
               p_input.add(p);
               Label l = new Label("Period from   ");
               l.setAlignment(Label.LEFT);
               p.add(l);
           
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
               ch_from_y.select(0);
               ch_from_m.select(0);
               ch_to_y.select(0);
               ch_to_m.select(0);
               
               p.add(ch_from_y);
               p.add(ch_from_m);
               p.add(new Label(" to "));
               p.add(ch_to_y);
               p.add(ch_to_m);
               
               Panel p_1 = new Panel(new FlowLayout(FlowLayout.LEADING));
               p_input.add(p_1);
               l = new Label("Output folder ");
               l.setAlignment(Label.LEFT);
               p_1.add(l);
               
               
               tfFolder.setPreferredSize(new Dimension(380,25));
               outfolder = prop.getProperty("OutputFolder","");
               tfFolder.addTextListener(new TextListener() {
                        public void textValueChanged(TextEvent arg0) { outfolder = tfFolder.getText(); }
               });
               tfFolder.setText(outfolder);
               p_1.add(tfFolder);
               
               Button btnFolderChooser = new Button("...");
               btnFolderChooser.addActionListener(new ActionListener(){
                      public void actionPerformed(ActionEvent arg0) {
                          FileDialog fd = new FileDialog(self, "Choose a folder", FileDialog.SAVE);
                          fd.setVisible(true);
                          String foldername = fd.getDirectory();
                          if (foldername!=null) {
                              tfFolder.setText(foldername);
                          }
                      }
               });
               p_1.add(btnFolderChooser);
               
               Panel p_2 = new Panel(new FlowLayout(FlowLayout.LEADING));
               p_input.add(p_2);
               l = new Label("Number of clients ");
               l.setAlignment(Label.LEFT);
               p_2.add(l);

               tfClientNum.setPreferredSize(new Dimension(75,25));
               clientNumber = Integer.parseInt(prop.getProperty( "Number_of_clients", "1000"));
               tfClientNum.setModel(new SpinnerNumberModel( (Number) clientNumber, 10, 100000, 1));
               tfClientNum.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {clientNumber = (int) tfClientNum.getValue();}
               });
         
               p_2.add(tfClientNum);
               
         Panel p_buttons = new Panel();
         p_buttons.setLayout(new BoxLayout(p_buttons,BoxLayout.PAGE_AXIS));
         p_top.add(p_buttons, BorderLayout.EAST);
         
         Dimension p_buttons_dim = new Dimension(120,80);
         Dimension button_dim    = new Dimension(110,30);
         
         p_buttons.setMinimumSize(p_buttons_dim);
         p_buttons.setPreferredSize(p_buttons_dim);
         p_buttons.setMaximumSize(p_buttons_dim);
       
         Button btnExit = new Button();
         btnExit.setLabel("Exit");
         btnExit.setMaximumSize(button_dim);
         btnExit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 onClose();
             }
          });
         
         Component verticalStrut = Box.createVerticalStrut(5);
         p_buttons.add(verticalStrut);
         p_buttons.add(btnExit);
         
         
         btnCalc.setLabel("Calculate");
         btnCalc.setActionCommand("Calculate");

         btnCalc.setMaximumSize(button_dim);
         btnCalc.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 if (e.getActionCommand() == "Calculate") {
                    calculate();
                 } else {
                    if (runthreads != null) {
                        runthreads.stop_execition = true;
                        log.warn("Calculation was stopped by User. Please wait for stoping...");
                    }
                 }
             }
          });
         
         Component verticalStrut_1 = Box.createVerticalStrut(5);
         p_buttons.add(verticalStrut_1);
         p_buttons.add(btnCalc);
         
         
         Panel p_log = new Panel(new BorderLayout());
        
         getContentPane().add(p_log,BorderLayout.CENTER);
         ta_log = new TextArea();
         ta_log.setEditable(false);
         p_log.add(ta_log,BorderLayout.CENTER);
 
    }
    
    
}
