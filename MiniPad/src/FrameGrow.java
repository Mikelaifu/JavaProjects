
package framegrow;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import com.sun.java.swing.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.awt.Color;


public class FrameGrow {
    
    static JFrame frame = new JFrame();
    
    static JPanel cen = new JPanel();
    static JTextArea textArea = new JTextArea();
    static JScrollPane areaScrollPane = new JScrollPane();
    
    static JMenuBar mb = new JMenuBar();
    static JMenu menu;
    static JMenuItem save, open; 
   
    static JMenu menu2;
    static JMenuItem copy, paste; 
    static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    
    static JMenu menu3;
    static JMenuItem about; 
    
    
    static ImageIcon icon1 = new ImageIcon("save.png");
    static Image img1 = icon1.getImage();
    static Image newsave = img1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    static ImageIcon Isave = new ImageIcon(newsave);
    
    static ImageIcon icon2 = new ImageIcon("open.png");
    static Image img2 = icon2.getImage();
    static Image newopen = img2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    static ImageIcon Iopen = new ImageIcon(newopen);
    
    static ImageIcon icon3 = new ImageIcon("copy.png");
    static Image img3 = icon3.getImage();
    static Image newcopy = img3.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    static ImageIcon Icopy = new ImageIcon(newcopy);
    
    static ImageIcon icon4 = new ImageIcon("paste.png");
    static Image img4 = icon4.getImage();
    static Image newpaste = img4.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    static ImageIcon Ipaste = new ImageIcon(newpaste);
    
    static ImageIcon icon5 = new ImageIcon("about.png");
    static Image img5 = icon5.getImage();
    static Image newabout = img5.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    static ImageIcon Iabout = new ImageIcon(newabout);
    
    public static void main(String[] args) {
        
        
        
        menu = new JMenu("File");
        save = new JMenuItem("save", Isave);
        open = new JMenuItem("open", Iopen);
        save.addActionListener(new Mike2());
        open.addActionListener(new Mike2());
        
        menu.add(save);
        menu.add(open);
        mb.add(menu);
        
        menu = new JMenu("Edit");
        copy = new JMenuItem("copy", Icopy);
        paste = new JMenuItem("paste", Ipaste);
        copy.addActionListener(new Copy());
        paste.addActionListener(new Paste());
        
    
        menu.add(copy);
        menu.add(paste);
        mb.add(menu);
        
        menu = new JMenu("About");
        about = new JMenuItem("about", Iabout);
        about.addActionListener(new Mike3());
        
        menu.add(about);   
        mb.add(menu);
        
        
        
        // can resize the text field 
        ComponentListener listener = new ComponentAdapter(){
            
            @Override
            public void componentResized(ComponentEvent evt){
                Component c = (Component) evt.getSource();
                
                System.out.println("Get new size");
                Dimension oldDim = c.getSize();
                
     int width = (int) oldDim.getWidth();
     int height = (int) oldDim.getHeight();
     
     if(width > 20)
         
         width -= 20;
     if(height > 40)
         
         height -= 40;
     
     
     
     Dimension newDim = new Dimension(width, height);
    
     textArea.setSize(newDim);
     textArea.setFont(new Font("Arial Black", Font.BOLD, 15));
     areaScrollPane.setSize(newDim);
     areaScrollPane.getBorder();
            }
        };
        
        cen.setLayout(new BorderLayout(1,1));
        
        
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(false);
        textArea.setEditable(true);
        
        // set scroll bars
        areaScrollPane = new JScrollPane(textArea);
       
        areaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        cen.add(areaScrollPane);
        
        
        ImageIcon img = new ImageIcon("editor.png");
        
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("editor.png"));
        frame.setJMenuBar(mb);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mini NotePad ver 1.0 - Document 1");
        frame.add(cen);
        frame.getContentPane();
        frame.setSize(800, 400);
        frame.addComponentListener(listener);
        frame.setVisible(true);
        
    } 
    
    
public static class Mike3 implements ActionListener{
      
      
        @Override
        public void actionPerformed(ActionEvent e) {
        
             JOptionPane.showMessageDialog( null, "Date: 12/05/2018\nCreator: Mike Wu\nVersion: 1.0\nClass: Java I");
       
    }

}
 
  
public static class Copy implements ActionListener{
      
      
        @Override
        public void actionPerformed (ActionEvent e) {
        String selection = textArea.getSelectedText();
        StringSelection data = new StringSelection(
         selection);
        clipboard.setContents(data, data);
      }
}

public static class Paste implements ActionListener{
      
      
        @Override
         public void actionPerformed (ActionEvent e) {
        Transferable clipData = clipboard.getContents
         (clipboard);
        if (clipData != null) {
          try {
            String s = (String)(clipData.getTransferData
             (
              DataFlavor.stringFlavor));
            textArea.replaceSelection (s);
          } catch (UnsupportedFlavorException ee) {
            System.err.println ("Unsupported flavor: " + ee);
          } catch (IOException ee) {
            System.err.println ("Unable to get data: " + ee);
          }
        }
      }
}
    
    
    
    
    public static class Mike2 implements ActionListener{
      
      
        @Override
        public void actionPerformed(ActionEvent e) {
            String com = e.getActionCommand();
          
          if(com.equals("save")) {
              
              //create an object of JFileChooser class
              
              JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
              
              // invoke the showSaveDialog function to show the save Dislog
              
              int r = j.showSaveDialog(null);
              
              // if the user selects a file and click save
              
              if(r == JFileChooser.APPROVE_OPTION){
                  
                  try {
                      //set the label to the path of the selected file

                      FileWriter w = new FileWriter(j.getSelectedFile().getAbsoluteFile());
                      StringReader sr= new StringReader(textArea.getText());
                      BufferedReader br = new BufferedReader(sr);
                      
            
                    String nextLine;
                    while ((nextLine = br.readLine()) != null){
                        w.write(nextLine+"\n");
                        frame.setTitle("Mini NotePad ver 1.0   " + j.getSelectedFile().getAbsolutePath());
             }
//             w.write(ta.getText());
             w.close();
                  } catch (IOException ex) {
                     ex.printStackTrace();
                  }
                 
                 
              }
              
            }
              
              else {
                  
                  // create a object of JFileChooser class
                  JFileChooser j2 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                  
                  //invoke the showOpenDialog function to show the save dialog
                  int r2 = j2.showOpenDialog(null);
                  
                  // if the user selecs the file
                  
                  if (r2 == JFileChooser.APPROVE_OPTION){
                      
                      FileReader reader = null;
                      try {
                          reader = new FileReader(j2.getSelectedFile().getAbsolutePath());
                          textArea.read(reader, j2.getSelectedFile().getAbsolutePath());
                          frame.setTitle("Mini NotePad ver 1.0   " + j2.getSelectedFile().getName());
                      }
                      catch (IOException ex) {
                     ex.printStackTrace();
                  }
                 
                  }
                  
              }
          

        }
      
    }
}
