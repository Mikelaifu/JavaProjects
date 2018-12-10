package employeeapp;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane; // creat different small sub windews/panel in one big frame
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;

public class EmployeeApp extends JFrame {
        
//        static JFrame frame = new JFrame("Show Data by id");
        static JFrame frame2 = new JFrame("Show all Data");
        static String[][] rowData;
        static String[][] rowData1;
        static JTable table;
        static JTabbedPane jtp = new JTabbedPane();
        static JPanel jp1 = new JPanel();
        static JPanel jp2 = new JPanel();
        static JPanel jp3 = new JPanel();
        static JPanel jp4 = new JPanel();
        static JLabel Stitle= new JLabel("New Employee");
        static JLabel Stitle2= new JLabel("Employee Search");
        static JLabel Stitle3= new JLabel("Employee Delete");
        static JLabel Stitle4= new JLabel("Employee Update");
        
         // for add new employee info into table for update
        static JLabel Sid = new JLabel ("Employee ID: ");
        static JLabel Sname = new JLabel("Employee name: ");
        static JLabel Spass = new JLabel ("Employee Password: ");
        static JLabel Semail = new JLabel ("Employee email: ");
        static JLabel Scountry = new JLabel ("Employee country: ");
        // upate : Update tablename SET name=<nm>,password=<pw>,email=<em>,country=<cty> where id=<id>  ;
        static JLabel U_by_id  = new JLabel ("Update By ID: ");
        static JLabel U_Sname = new JLabel("Employee name: ");
        static JLabel U_Spass = new JLabel ("Employee Password: ");
        static JLabel U_Semail = new JLabel ("Employee email: ");
        static JLabel U_Scountry = new JLabel ("Employee country: ");
        
        // for search , delete
        // search : select * from tablelname where id = <Value>;
        // delete : delete from tablelname where id = <Value>;
        static JLabel D_by_id  = new JLabel ("Delete By ID: ");
        
        static List<Emp> EmpList = new ArrayList<>();
        
        //add && Update
        static JTextField SIid = new JTextField(10);
        static JTextField SIname = new JTextField(10);
        static JTextField SIpass = new JTextField(10);
        static JTextField SIemail = new JTextField(10);
        static JTextField SIcountry = new JTextField(10);
 
        static JTextField U_SIname = new JTextField(10);
        static JTextField U_SIpass = new JTextField(10);
        static JTextField U_SIemail = new JTextField(10);
        static JTextField U_SIcountry = new JTextField(10);
        static JTextField U_SIid = new JTextField(10);
        
        // delete 
        static JTextField D_id = new JTextField(10);
       
       
        static JButton Sb1=new JButton("SAVE");
        static JButton Sb2=new JButton("SEARCH");
        static JButton Sb3=new JButton("SEARCH All");
        static JButton Sb4=new JButton("DELETE");
        static JButton Sb5=new JButton("UPDATE");
        static JLabel Sstatus= new JLabel("Message...");
        static JLabel Sstatus2= new JLabel("Message...");
        static JLabel Sstatus3= new JLabel("Message...");
        static JLabel Sstatus4= new JLabel("Message...");
        
        // search 
        static JTextField S_id = new JTextField(10);
        static JTextField S_SIname = new JTextField(10);
        static JTextField S_SIpass = new JTextField(10);
        static JTextField S_SIemail = new JTextField(10);
        static JTextField S_SIcountry = new JTextField(10);
        static JLabel S_by_id  = new JLabel ("Search By ID: ");
        static JLabel S_Sname = new JLabel("Emaployee Name: ");
        static JLabel S_Spass = new JLabel("Emaployee PassWord: ");
        static JLabel S_Semail = new JLabel("Emaployee e-mail: ");
        static JLabel S_Scountry = new JLabel("Emaployee country: ");
        
        static JMenu menu;
        static JMenuItem i1, i2, i3, i4;
        static JMenuBar mb = new JMenuBar();
        
        
        
    public static void main(String[] args) {
        
        EmployeeApp tp = new EmployeeApp();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
    }
    
     public EmployeeApp(){
        
        menu = new JMenu("Select a SQL Function here");
        menu.setMnemonic(KeyEvent.VK_M);
        
        i1 = new JMenuItem("Add");
        i2 = new JMenuItem("Search");
        i3 = new JMenuItem("Delete");
        i4 = new JMenuItem("Update");
        
        KeyStroke keyAbout = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyAbout2 = KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyAbout3 = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyAbout4 = KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK);
        
        i1.setAccelerator(keyAbout);
        i2.setAccelerator(keyAbout2);
        i3.setAccelerator(keyAbout3);
        i4.setAccelerator(keyAbout4);
        

//        
        menu.add(i1); menu.add(i2); menu.add(i3); menu.add(i4);
        mb.add(menu);
        setJMenuBar(mb);
        
        setTitle("Employee CURD");
        setSize(500, 400);
        
        getContentPane().add(jtp);
        jp1.setLayout(null);
        jp2.setLayout(null);
        jp3.setLayout(null);
        jp4.setLayout(null);
        
        // set text label for ADD
        Stitle.setFont(new Font("verdana", Font.BOLD, 16));
        Stitle.setBounds(170, 20, 200, 20);
        Sid.setBounds(90, 70, 140, 20);
        Sname.setBounds(90, 100, 140, 20);
        Spass.setBounds(90, 130, 140, 20);
        Semail.setBounds(90, 160, 140, 20);
        Scountry.setBounds(90, 190, 140, 20);
        
        // set text label + textfiled for delete
        Stitle3.setFont(new Font("verdana", Font.BOLD, 16));
        Stitle3.setBounds(170, 20, 200, 20);
        D_by_id.setBounds(110, 70, 140, 20);
        D_id.setBounds(225, 70, 140, 20);
        
        // set labels for updates
        Stitle4.setFont(new Font("verdana", Font.BOLD, 16));
        Stitle4.setBounds(170, 20, 200, 20);
        U_Sname.setBounds(90, 70, 140, 20);
        U_Spass.setBounds(90, 100, 140, 20);
        U_Semail.setBounds(90, 130, 140, 20);
        U_Scountry.setBounds(90, 160, 140, 20);
        U_by_id.setBounds(90, 190, 140, 20);
        
        
        // set Add Textfield Bounds
        SIid.setBounds(225, 70, 100, 20);
        SIname.setBounds(225, 100, 140, 20);
        SIpass.setBounds(225, 130, 110, 20);
        SIemail.setBounds(225, 160, 140, 20);
        SIcountry.setBounds(225, 190, 110, 20);
        
        // set textfiled bounds for Update
        
        U_SIname.setBounds(225, 70, 140, 20);
        U_SIpass.setBounds(225, 100, 140, 20);
        U_SIemail.setBounds(225, 130, 160, 20);
        U_SIcountry.setBounds(225, 160, 130, 20);
        U_SIid.setBounds(225, 190, 140, 20);
        
        // set textfield & Label for Search 
        Stitle2.setFont(new Font("verdana", Font.BOLD, 16));
        Stitle2.setBounds(170, 20, 200, 20);
        S_by_id.setBounds(90, 190, 140, 20);
        S_id.setBounds(225, 190, 140, 20);
        S_SIname.setBounds(225, 70, 140, 20);
        S_SIpass.setBounds(225, 100, 160, 20);
        S_SIemail.setBounds(225, 130, 130, 20);
        S_SIcountry.setBounds(225, 160, 140, 20);
        
        S_Sname.setBounds(90, 70, 140, 20);
        S_Spass.setBounds(90, 100, 140, 20);
        S_Semail.setBounds(90, 130, 140, 20);
        S_Scountry.setBounds(90, 160, 140, 20);
       
        
        // set button
        Sb1.setBounds(180, 230, 140, 30);
        Sb1.addActionListener(new MikeWu1());
        // button for search
        Sb2.setBounds(170, 230, 140, 30);
        Sb2.addActionListener(new MikeWu2());
        //button for search all
        Sb3.setBounds(170, 255, 140, 30);
        Sb3.addActionListener(new MikeWu3());
        // button for delete
        Sb4.setBounds(170, 110, 140, 30);
        Sb4.addActionListener(new MikeWu4());
        // button for Update
        Sb5.setBounds(170, 230, 140, 30);
        Sb5.addActionListener(new MikeWu5());
        
        Sstatus.setForeground(Color.blue);
        Sstatus.setBounds(190, 280, 140, 20);
        Sstatus3.setForeground(Color.blue);
        Sstatus3.setBounds(190, 190, 140, 20);
        Sstatus2.setForeground(Color.blue);
        Sstatus2.setBounds(190, 300, 140, 20);
        Sstatus4.setForeground(Color.blue);
        Sstatus4.setBounds(190, 280, 140, 20);
        
       
        // jp1 for add
        jp1.add(Stitle);
        jp1.add(Sid);
        jp1.add(Sname);
        jp1.add(Spass);
        jp1.add(Semail);
        jp1.add(Scountry);
        jp1.add(SIid);
        jp1.add(SIname);
        jp1.add(SIpass);
        jp1.add(SIemail);
        jp1.add(SIcountry);
        jp1.add(Sb1);
        jp1.add(Sstatus);
        
        
       //jp2 for search 
        jp2.add(Stitle2);
        jp2.add(S_by_id);
        jp2.add(S_id);
        jp2.add(Sb2);
        jp2.add(Sb3);
        jp2.add(Sstatus2);
        
       
        jp2.add(S_SIname);
        jp2.add(S_SIpass);
        jp2.add(S_SIemail);
        jp2.add(S_SIcountry);
        jp2.add(S_Sname);
        jp2.add(S_Spass);
        jp2.add(S_Semail);
        jp2.add(S_Scountry);
        
       // jp3 for delete
        jp3.add(Stitle3);
        jp3.add(D_by_id);
        jp3.add(D_id);
        jp3.add(Sb4);
        jp3.add(Sstatus3);
        
        
        // jp4 for update
        jp4.add(Stitle4);
        jp4.add(U_Sname);
        jp4.add(U_Spass);
        jp4.add(U_Semail);
        jp4.add(U_Scountry);
        jp4.add(U_by_id);
        jp4.add(U_SIid);
        jp4.add(U_SIname);
        jp4.add(U_SIpass);
        jp4.add(U_SIemail);
        jp4.add(U_SIcountry);
        jp4.add(Sb5);
        jp4.add(Sstatus4);
        
        
        jtp.addTab("Insert Employee", jp1);
        jtp.addTab("Search Employee", jp2);
        jtp.addTab("Delete Employee", jp3);
        jtp.addTab("Update Employee", jp4);
        
//        jtp.getSelectedIndex(); // each tabbedpane will correspond to a a index from 0 to 3
        
        i1.addActionListener(new MIKE1());
        i2.addActionListener(new MIKE2());
        i3.addActionListener(new MIKE3());
        i4.addActionListener(new MIKE4());
        
    }
     
  // a function to create a new array   
//     public static String[][] newArray(int row, int col) {
//
//        return new String[row][col];
//
//}
     
     public static class MIKE1 implements ActionListener {
         
         @Override
         public void actionPerformed(ActionEvent ae) {
            
             jtp.setSelectedIndex(0);
         }
     }
     
      public static class MIKE2 implements ActionListener {
         
         @Override
         public void actionPerformed(ActionEvent ae) {
//             jtp.setSelectedComponent(i1);
             jtp.setSelectedIndex(1);
         }
     }
      
       public static class MIKE3 implements ActionListener {
         
         @Override
         public void actionPerformed(ActionEvent ae) {
             jtp.setSelectedIndex(2);
         }
         
     }
       
       public static class MIKE4 implements ActionListener {
         
         @Override
         public void actionPerformed(ActionEvent ae) {
             jtp.setSelectedIndex(3);
         }
     }
//     
     // button call for adding new employee info into the table
     public static class MikeWu1 implements ActionListener {
       
       @Override
       public void actionPerformed(ActionEvent ae) {
           Emp e = new Emp();
           e.setId(Integer.parseInt(SIid.getText()));
           e.setName(SIname.getText());
           e.setPassword(SIpass.getText());
           e.setEmail(SIemail.getText());
           e.setCountry(SIcountry.getText());
           
           int status=EmpDao.save(e);
           if(status>0){
               Sstatus.setText("Record Saved Successfully");
               SIid.setText("");
               SIname.setText("");
               SIpass.setText("");
               SIemail.setText("");
               SIcountry.setText("");
           }
           else {
               Sstatus.setText("Sorry! unable to save record");
               
           }
           
           
       }
    };
    
    // button call for search employee info from the table
    public static class MikeWu2 implements ActionListener {
       
       @Override
       public void actionPerformed(ActionEvent ae) {
           
           Emp E = new Emp();
           Emp e = new Emp();
           int id = Integer.parseInt(S_id.getText());
           
           E = EmpDao.getEmployeeById(id);
//           rowData1=new String[1][5];
           
           
           String Name = E.getName();
           String PassWord = E.getPassword();
           String Email = E.getEmail();
           String Country = E.getCountry();
        
          if(E.getName() != null){
               Sstatus2.setText("Search Successfully");
               S_id.setText("");
            
                
               S_SIname.setText(Name);
               S_SIpass.setText(PassWord);
               S_SIemail.setText(Email);
               S_SIcountry.setText(Country);  
           }
           else {
               Sstatus2.setText("Sorry! unable to Search by ID ");
               
           }
         
       }
    };
    
    
    // button call for search all employee info from the table
    public static class MikeWu3 implements ActionListener  {
       
       @Override
       public void actionPerformed(ActionEvent ae) {
           
           EmpList = EmpDao.getAllEmployees();
           Iterator itr = EmpList.iterator();
           
           int count = EmpList.size();
           int index = 0;
           rowData=new String[count][5];
            
            while(itr.hasNext()){
                Emp sc = (Emp)itr.next();
                int ID = sc.getId();
                String Name = sc.getName();
                String PassWord = sc.getPassword();
                String Email = sc.getEmail();
                String Country = sc.getCountry();
                System.out.println(Name + " : " + PassWord + "," + Email + "," + Country + "," + ID );
                

                rowData[index][0] = Integer.toString(ID);
                rowData[index][1] = Name;
                rowData[index][2] = PassWord;
                rowData[index][3] = Email;
                rowData[index][4] = Country;
                index++; 

         }
            
          String[] columnTitles={"ID","Name","Password","E-mail","Country"}; 
          // how to get RowDate from a Arraylist???????????
          table = new JTable(rowData, columnTitles);

          table.setRowSelectionAllowed(true);
          ListSelectionModel rowSelectionModel = table.getSelectionModel();
          rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
      
          frame2.add(new JScrollPane(table));

          frame2.setSize(350, 200);
          frame2.setVisible(true);
       }
    };
     
    // button call for delete employee info from the table
    public static class MikeWu4 implements ActionListener {
       
       @Override
       public void actionPerformed(ActionEvent ae) {
           
           Emp e = new Emp();
           e.setId(Integer.parseInt(D_id.getText()));
           int id = e.getId();  
           int status=EmpDao.delete(id);
           if(status>0){
               Sstatus3.setText("Record deleted Successfully");
               D_id.setText("");
           }
           else {
               Sstatus3.setText("Record could not be deleted");
           }

       }
    };
    
     // button call for Update employee info from the table
    public static class MikeWu5 implements ActionListener {
       
       @Override
       public void actionPerformed(ActionEvent ae) {
           
           Emp e = new Emp();
           e.setName(U_SIname.getText());
           e.setPassword(U_SIpass.getText());
           e.setEmail(U_SIemail.getText());
           e.setCountry(U_SIcountry.getText());
           e.setId(Integer.parseInt(U_SIid.getText()));
           
           int statusupdate=EmpDao.update(e);
           if(statusupdate>0){
               
            Sstatus4.setText("Record updated Successfully");
            U_SIid.setText("");
            U_SIname.setText("");
            U_SIpass.setText("");
            U_SIemail.setText("");
            U_SIcountry.setText("");
           }
           else {
               Sstatus4.setText("Sorry! unable to update record");
               
           }
           

       }
    };


    
   
    
}
