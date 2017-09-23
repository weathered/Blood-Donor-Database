package DefaultApplication;

import DataAccess.DataAccess;
import adminlogin.adminLogin;
import static error.Error.checkEmail;
import static error.Error.checkNum;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class ButtonSensor3 implements ActionListener{
	JTextField tf, tf2, tf3, tf4;
        String a, b, c, d, e;
        JComboBox<String> tf5;
        Application app;
        MainWindow cd;
        JTable donorTable;
        DefaultTableModel tm;
       
        ButtonSensor3(Application app, MainWindow cd, JTextField t, JTextField t2, JTextField t3, JComboBox<String> b2, JTable donorTable, DefaultTableModel tm) {
                this.tf=t;
                this.tf2=t2;
                this.tf3=t3;
                this.tf5=b2;
                this.app=app;
                this.cd=cd;
                this.donorTable=donorTable;
                this.tm = tm;
        }

            public void actionPerformed(ActionEvent ae){
                    String ac=ae.getActionCommand();
                    
                    if(ac.equals("SEARCH")){

                        a = tf.getText();
                        b = tf2.getText();
                        c = tf5.getSelectedItem().toString();
                        d = tf3.getText();

                        String q = null;

                        try{
                            a = tf.getText();
                            b = tf2.getText();
                            c = tf5.getSelectedItem().toString();
                            d = tf3.getText();
                            
                            if(a.length()==0 && b.length()==0 && d.length()==0 && c.equals("Select")){
                                    throw new Exception("At least one catagory is required!");
                            }
                            
                            int flag=0;
                            q = "SELECT * FROM `donortable` WHERE (";
                            if(a.length()!=0){
                                if(checkEmail(a)==false){
                                    throw new Exception("The email address '"+a+"' appears to be invalid");
                                }
                                q = q+"`email` = '"+a+"'";
                                flag=1;
                            }
                            
                            if(b.length()!=0){
                                if(checkNum(b)==false){
                                    throw new Exception("The Contact number '"+b+"' appears to be invalid");
                                }
                                if(flag==1){
                                    q = q+" AND ";
                                }
                                q = q+"`contactNumber` = '"+b+"'";
                                flag=1;
                            }
                            
                            if(c.length()!=0 && !c.equals("Select")){
                                if(flag==1){
                                    q = q+" AND ";
                                }
                                q = q+"`bloodgroup` = '"+c+"'";
                                flag=1;
                            }
                            
                            if(d.length()!=0){
                                if(flag==1){
                                    q = q+" AND ";
                                }
                                q = q+"`address` = '"+d+"'";
                                flag=1;
                            }
                            q=q+")";
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        DataAccess da = new DataAccess();
                        ResultSet rs = null;
                        
                        try{
                            rs = da.getData(q);
                            tm.setRowCount(0);
                            int flag=0;
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int colNo = rsmd.getColumnCount();
                            while(rs.next()){
                                Object[] objects = new Object[colNo];
                                for(int i=0;i<colNo;i++){
                                    objects[i]=rs.getObject(i+1);
                                }
                                tm.addRow(objects);
                                flag=1;
                            }
                            donorTable.setModel(tm);

                            if(flag==0){
                                JOptionPane.showMessageDialog(null, "No Entry found in the Database", "Info not found", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        catch(Exception ex){

                        }

                    }
            }   
}

public class MainWindow extends Application{
    Application app;

    String searchOption = "Search Database";
    
    String bloodGroups[] = {"Select", "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
    JComboBox<String> b1= new JComboBox<>(bloodGroups);
    JComboBox<String> b2 = new JComboBox<>(bloodGroups);
    
    JTable donorTable = null;
    
    public MainWindow(){
            setTitle("Blood Donor Database");

            //JLabel ask = new JLabel("Blood Donor Information Database");
            //ask.setFont(new Font("Serif", Font.BOLD, 20));
            //ask.setForeground(Color.BLACK);
            //appPanel.add(ask, BorderLayout.NORTH);

            JTabbedPane tabbedPane = new JTabbedPane();

            JTextField email3 = new JTextField(10);
            JTextField contact3 = new JTextField(10);
            JTextField address2 = new JTextField(10);

            JButton Search = new JButton("SEARCH");

            JPanel searchPanel = new JPanel();

            searchPanel.add(new JLabel("E-Mail"));
            searchPanel.add(email3);
            searchPanel.add(new JLabel("Contact : "));
            searchPanel.add(contact3);
            searchPanel.add(new JLabel("Blood Group : "));
            searchPanel.add(b2);
            searchPanel.add(new JLabel("Address : "));
            searchPanel.add(address2);
            searchPanel.add(Search);

            tabbedPane.addTab(searchOption, searchPanel);
            
            donorTable = new JTable();
            
            DataAccess da = new DataAccess();
            ResultSet rs = da.getData("SELECT * FROM DONORTABLE");
            
            String[] colNames = null;
            try{
                ResultSetMetaData mdata = rs.getMetaData();
                int colCount = mdata.getColumnCount();
                colNames = new String[colCount];
                for (int i = 1; i <= colCount; i++) {
                  colNames[i-1] = mdata.getColumnName(i);
                  colNames[i-1] = Character.toString(colNames[i-1].charAt(0)).toUpperCase()+colNames[i-1].substring(1);
                  System.out.println(colNames[i-1]);
                }
            }catch(Exception ex){
                
            }
            
            DefaultTableModel aModel = (DefaultTableModel)donorTable.getModel();
            aModel.setColumnIdentifiers(colNames);
            
            
            
            JScrollPane tableContainer = new JScrollPane(donorTable);
            tableContainer.setPreferredSize(new Dimension(980,385));
            
            ButtonSensor3 sds = new ButtonSensor3(app, this, email3, contact3, address2, b2, donorTable, aModel);
            Search.addActionListener(sds);
            
            final MainWindow main = this;
            adminButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        adminLogin a = new adminLogin(main);
                    }
            });
            
            tabbedPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            tabbedPane.setPreferredSize(new Dimension(980,63));
                
            appPanel.add(tabbedPane, BorderLayout.CENTER);
            appPanel.add(tableContainer, BorderLayout.PAGE_END);
            setVisible(true);
            setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        }
    
    public static void main(String[] args){
            new MainWindow();
	}
}

