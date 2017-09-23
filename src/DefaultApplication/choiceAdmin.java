package DefaultApplication;

import DataAccess.DataAccess;
import static error.Error.checkEmail;
import static error.Error.checkName;
import static error.Error.checkNum;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class WindowSensor3 extends WindowAdapter{
	public void windowClosing(WindowEvent we){
		System.exit(0);    
	}
}

class KeySensor implements KeyListener{
    MainWindow app;
    choiceAdmin aThis;
    JTextField mainId, id2, name2, email4, contact4, address3;
    JComboBox<String> b3;
    String idCatch;
    KeySensor(MainWindow app, choiceAdmin aThis, JTextField id2, JTextField mainId, JTextField name2, JTextField email4, JTextField contact4, JTextField address3, JComboBox<String> b3, String idCatch) {
        this.app=app;
        this.aThis=aThis;
        this.mainId=mainId;
        this.id2=id2;
        this.name2=name2;
        this.email4=email4;
        this.contact4=contact4;
        this.address3=address3;
        this.b3=b3;
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
                mainId.setText("");
                name2.setText("");
                email4.setText("");
                contact4.setText("");
                address3.setText("");
                b3.setSelectedItem("Select");
        try{
           Integer.parseInt(id2.getText());
        }
        catch(Exception ex){
            if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                JOptionPane.showMessageDialog(null, "ID should be a numerical value", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        try{
            DataAccess da = new DataAccess();
            ResultSet rs = null;
            String sql = "SELECT * FROM `donortable` where `id` = "+Integer.parseInt(id2.getText());
            rs=da.getData(sql);
            while(rs.next()){
                mainId.setText(rs.getString("id"));
                name2.setText(rs.getString("name"));
                email4.setText(rs.getString("email"));
                contact4.setText(rs.getString("contactNumber"));
                address3.setText(rs.getString("address"));
                b3.setSelectedItem(rs.getString("bloodgroup"));
                e.consume();
            }
        }
        catch(Exception ex){
            
        }
    }
    
}

class ButtonSensor2 implements ActionListener{
	JTextField id, tf, tf2, tf3, tf4;
        String a, b, c, d, e, idCatch;
        JComboBox<String> tf5;
        MainWindow app;
        choiceAdmin cd;
        JTable donorTable;
        DefaultTableModel tm;
       
        ButtonSensor2(MainWindow app, choiceAdmin cd){
            this.app=app;
            this.cd=cd;
        }
        
        ButtonSensor2(JTextField t) {
            this.tf=t;
        }
        
        ButtonSensor2(JTable donorTable, DefaultTableModel tm){
            this.donorTable=donorTable;
            this.tm=tm;
        }

        ButtonSensor2(MainWindow app, choiceAdmin cd, JTextField t, JTextField t2, JTextField t3, JTextField t4, JComboBox<String> t5, JTable donorTable, DefaultTableModel tm) {
                this.tf=t;
                this.tf2=t2;
                this.tf3=t3;
                this.tf4=t4;
                this.tf5=t5;
                this.app = app;
                this.cd = cd;
                this.donorTable=donorTable;
                this.tm = tm;
        }

        ButtonSensor2(MainWindow app, choiceAdmin cd, JTextField t, JTextField t2, JTextField t3, JTable donorTable, DefaultTableModel tm){
                this.tf=t;
                this.tf2=t2;
                this.tf3=t3;
                this.app=app;
                this.cd=cd;
                this.donorTable=donorTable;
                this.tm = tm;
        }

        ButtonSensor2(MainWindow app, choiceAdmin cd, JTextField t, JTextField t2, JTextField t3, JComboBox<String> b2, JTable donorTable, DefaultTableModel tm) {
                this.tf=t;
                this.tf2=t2;
                this.tf3=t3;
                this.tf5=b2;
                this.app=app;
                this.cd=cd;
                this.donorTable=donorTable;
                this.tm = tm;
        }

    ButtonSensor2(JTextField id, JTextField t, JTextField t2, JTextField t3, JTextField t4, JComboBox<String> t5, JTable donorTable, DefaultTableModel tm, String idCatch) {
                this.id=id;
                this.tf=t;
                this.tf2=t2;
                this.tf3=t3;
                this.tf4=t4;
                this.tf5=t5;
                this.donorTable=donorTable;
                this.tm = tm;
    }

            public void actionPerformed(ActionEvent ae){
                    String ac=ae.getActionCommand();
                    
                    if(ac.equals("<html><font color=black>Logout</color></html>")){   ///Logout Button Text
                        cd.dispose();
                        app.setVisible(true);
                    }
                    
                    if(ac.equals("Reload Table")){
                        try{
                            DataAccess da = new DataAccess();
                            String q = "select * from `donortable`";
                            ResultSet rs = da.getData(q);
                            tm.setRowCount(0);
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int colNo = rsmd.getColumnCount();
                                while(rs.next()){
                                    Object[] objects = new Object[colNo];
                                    for(int i=0;i<colNo;i++){
                                        objects[i]=rs.getObject(i+1);
                                    }
                                    tm.addRow(objects);
                                }
                                donorTable.setModel(tm);
                        }
                        catch(Exception ex){
                            
                        }
                    }
                    
                    if(ac.equals("UPDATE")){
                        try {
                            int num = Integer.parseInt(id.getText());
                            a = tf.getText();
                            b = tf2.getText();
                            c = tf3.getText();
                            d = tf4.getText();
                            e = tf5.getSelectedItem().toString();
                            
                            if(checkEmail(b)==false){
                                throw new Exception("The email address '"+b+"' appears to be invalid");
                            }
                            if(checkName(a)==false){
                                throw new Exception("The name '"+a+"' appears to be invalid. Letters, period(.) and hyphens(-) are allowed");
                            }
                            if(checkNum(c)==false){
                                throw new Exception("The Contact '"+c+"' appears to be invalid.");
                            }
                            
                            String sql = "UPDATE `donortable` SET `name`='"+a+"', `email`='"+b+"', `contactNumber` = '"+c+"', `address`='"+d+"', `bloodgroup`='"+e+"' "+"WHERE `id`="+num;
                            System.out.println(sql);
                            DataAccess da=new DataAccess();
                            da.updateDB(sql);
                            
                            ResultSet rs = null;

                                String q = "select * from `donortable` WHERE `id`="+num;
                                rs = da.getData(q);
                                tm.setRowCount(0);
                                ResultSetMetaData rsmd = rs.getMetaData();
                                int colNo = rsmd.getColumnCount();
                                while(rs.next()){
                                    Object[] objects = new Object[colNo];
                                    for(int i=0;i<colNo;i++){
                                        objects[i]=rs.getObject(i+1);
                                    }
                                    tm.addRow(objects);
                                }
                                donorTable.setModel(tm);
                            
                            JOptionPane.showMessageDialog(null, "Selected entry updated successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                            
                            da.close();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "ID Required or Not Found. ID must be a numerical value", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
            ///Insert Begins here
                    if(ac.equals("INSERT")){
                        int num = 0;

                            try{
                                a = tf.getText();
                                b = tf2.getText();
                                c = tf3.getText();
                                d = tf4.getText();
                                e = tf5.getSelectedItem().toString();

                                if(a.length()==0 || b.length()==0 || c.length()==0 || d.length()==0 || e.length()==0 || e.equals("Select")){
                                    throw new Exception("All fields here are mendatory!");
                                }
                                if(checkEmail(b)==false){
                                    throw new Exception("The email address '"+b+"' appears to be invalid");
                                }
                                if(checkName(a)==false){
                                    throw new Exception("The name '"+a+"' appears to be invalid. Letters, period(.) and hyphens(-) are allowed");
                                }
                                if(checkNum(c)==false){
                                    throw new Exception("The Contact '"+c+"' appears to be invalid.");
                                }
                                else{
                                    DataAccess da = new DataAccess();
                                    ResultSet rs = null;

                                    String q="SELECT * FROM `donortable` ORDER BY id ASC";
                                    rs = da.getData(q);
                                        try{
                                            while(rs.next()){
                                                num = rs.getInt("id");
                                            }
                                        }
                                        catch(Exception ex){}

                                    q = "INSERT INTO "+"`donortable` (`id`, `name`, `email`, `contactNumber`, `address`, `bloodgroup`) "+"VALUES("+(++num)+","+"'"+ this.a+ "', " + "'"
                                            + this.b+"', "+ "'"+this.c+ "', "+ "'" +this.d+ "', "+"'"+this.e+"'"+ ")";
                                    System.out.println(q);
                                    int n = da.loadDB(q);
                                    
                                    q = "select * from `donortable`";
                                    rs = da.getData(q);
                                    tm.setRowCount(0);
                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int colNo = rsmd.getColumnCount();
                                    while(rs.next()){
                                        Object[] objects = new Object[colNo];
                                        for(int i=0;i<colNo;i++){
                                            objects[i]=rs.getObject(i+1);
                                        }
                                        tm.addRow(objects);
                                    }
                                    donorTable.setModel(tm);
                                    
                                    JOptionPane.showMessageDialog(null, n+" donor(s) added to the database", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    
                                    tf.setText("");
                                    tf2.setText("");
                                    tf3.setText("");
                                    tf4.setText("");
                                    tf5.setSelectedItem("Select");
                                    
                                    da.close();
                                }

                            }
                            catch(Exception ex){
                                System.out.println(ex.getMessage());
                                   JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            
                    }
            ///Delete beings here
                    if(ac.equals("DELETE")){
                        a = tf.getText();
                        b = tf2.getText();
                        c = tf3.getText();
                        
                        String q = null;

                        try{
                            DataAccess da = new DataAccess();
                            if(a.length()!=0){
                                Integer.parseInt(a);
                                q = "DELETE FROM `donortable` WHERE `id` = "+a;
                            }else{
                                if(b.length()!=0){
                                    if(checkEmail(b)==false){
                                        throw new Exception("The email address '"+b+"' appears to be invalid");
                                    }
                                    q = "DELETE FROM `donortable` WHERE `email` = '"+b+"'";
                                }
                                else{
                                    if(c.length()!=0){
                                        if(checkNum(c)==false){
                                            throw new Exception("The Contact Number '"+c+"' appears to be invalid");
                                        }
                                        q = "DELETE FROM `donortable` WHERE `contactNumber` = '"+c+"'";
                                    }
                                    else{
                                        throw new Exception("Input required");
                                    }
                                }
                            }
                            
                            int c = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning!", JOptionPane.OK_CANCEL_OPTION);
                            
                            if(c==JOptionPane.OK_OPTION){
                                int n = da.loadDB(q);

                                if(n>0){
                                    da = new DataAccess();
                                    ResultSet rs = null;

                                    try{
                                        q = "select * from `donortable`";
                                        rs = da.getData(q);
                                        tm.setRowCount(0);
                                        ResultSetMetaData rsmd = rs.getMetaData();
                                        int colNo = rsmd.getColumnCount();
                                        while(rs.next()){
                                            Object[] objects = new Object[colNo];
                                            for(int i=0;i<colNo;i++){
                                                objects[i]=rs.getObject(i+1);
                                            }
                                            tm.addRow(objects);
                                        }
                                        donorTable.setModel(tm);
                                    }
                                    catch(Exception ex){

                                    }
                                    JOptionPane.showMessageDialog(null, n+" entry deleted from the database", "Information", JOptionPane.INFORMATION_MESSAGE);
                                }else{
                                    JOptionPane.showMessageDialog(null, "Entry not found in the database", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            da.close();
                        }
                        catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "ID must be a numerical value", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }     
                    }
                    
           ///search begins here
                    if(ac.equals("SEARCH")){

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


public class choiceAdmin extends Application{
    MainWindow app;

    String insertOption = "Insert to Database";
    String deleteOption = "Delete from Database";
    String searchOption = "Search Database";
    String updateOption = "Update Entry";
    String idCatch = null;
    
    String bloodGroups[] = {"Select", "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
    JComboBox<String> b1= new JComboBox<>(bloodGroups);
    JComboBox<String> b2 = new JComboBox<>(bloodGroups);
    JComboBox<String> b3 = new JComboBox<>(bloodGroups);
    
    JTable donorTable = null;
    
    public choiceAdmin(MainWindow a){
            app = a;
            app.setVisible(false);
            setTitle("Database Access");
            

            JLabel ask = new JLabel("SEARCH, INSERT, MODIFY OR DELETE FROM DATABASE");
            ask.setFont(new Font("Serif", Font.BOLD, 20));
            ask.setForeground(Color.BLACK);
            appPanel.add(ask, BorderLayout.NORTH);

            JTabbedPane tabbedPane = new JTabbedPane();

            JTextField name = new JTextField(10);
            JTextField name2 = new JTextField(10);
            JTextField email = new JTextField(10);
            JTextField email2 = new JTextField(10);
            JTextField email3 = new JTextField(10);
            JTextField email4 = new JTextField(9);
            JTextField contact = new JTextField(10);
            JTextField contact2 = new JTextField(10);
            JTextField contact3 = new JTextField(10);
            JTextField contact4 = new JTextField(9);
            JTextField address = new JTextField(14);
            JTextField address2 = new JTextField(14);
            JTextField address3 = new JTextField(10);
            JTextField id = new JTextField(3);
            JTextField id2 = new JTextField(3);
            JTextField mainId = new JTextField(3);
            

            JButton Insert = new JButton("INSERT");
            JButton Delete = new JButton("DELETE");
            JButton Search = new JButton("SEARCH");
            JButton Update = new JButton("UPDATE");
            JButton Logout = new JButton("<html><font color=black>Logout</color></html>");
            Logout.setBackground(new Color(253, 75, 75));
            Logout.setForeground(Color.GRAY);
            
            JPanel insertPanel = new JPanel();

            insertPanel.add(new JLabel("Name : "));
            insertPanel.add(name);
            insertPanel.add(new JLabel("E-Mail : "));
            insertPanel.add(email);
            insertPanel.add(new JLabel("Contact : "));
            insertPanel.add(contact);
            insertPanel.add(new JLabel("Address : "));
            insertPanel.add(address);
            insertPanel.add(new JLabel("Blood Group : "));
            insertPanel.add(b1);
            insertPanel.add(Insert);



            JPanel deletePanel = new JPanel();
           
            deletePanel.add(new JLabel("ID : "));
            deletePanel.add(id);
            deletePanel.add(new JLabel("E-Mail : "));
            deletePanel.add(email2);
            deletePanel.add(new JLabel("Contact : "));
            deletePanel.add(contact2);
            deletePanel.add(Delete);
            deletePanel.add(new JLabel("<html><font color='red'>*Require only one field to be filled in, this action cannot be undone.</font></html>"));


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
            
            JPanel updatePanel = new JPanel();
            
            updatePanel.add(new JLabel("<html><font color='red'>ID :</font></html>"));
            updatePanel.add(id2);
            //updatePanel.add(new JLabel("ID : "));
            //updatePanel.add(mainId);
            updatePanel.add(new JLabel("Name : "));
            updatePanel.add(name2);
            updatePanel.add(new JLabel("E-Mail : "));
            updatePanel.add(email4);
            updatePanel.add(new JLabel("Contact : "));
            updatePanel.add(contact4);
            updatePanel.add(new JLabel("Address : "));
            updatePanel.add(address3);
            updatePanel.add(new JLabel("Blood Group : "));
            updatePanel.add(b3);
            updatePanel.add(Update);
            
            KeySensor ks = new KeySensor(app, this, id2, mainId, name2, email4, contact4, address3, b3, idCatch);
            id2.addKeyListener(ks);
            

            tabbedPane.addTab(insertOption, insertPanel);
            tabbedPane.addTab(deleteOption, deletePanel);
            tabbedPane.addTab(updateOption, updatePanel);
            tabbedPane.addTab(searchOption, searchPanel);
            
            donorTable = new JTable();
            
            DataAccess da = new DataAccess();
            ResultSet rs = da.getData("SELECT * FROM DONORTABLE");
            
            String[] colNames = null;
            DefaultTableModel aModel = (DefaultTableModel)donorTable.getModel();
            try{
                ResultSetMetaData mdata = rs.getMetaData();
                int colCount = mdata.getColumnCount();
                colNames = new String[colCount];
                for (int i = 1; i <= colCount; i++) {
                  colNames[i-1] = mdata.getColumnName(i);
                  colNames[i-1] = Character.toString(colNames[i-1].charAt(0)).toUpperCase()+colNames[i-1].substring(1);
                  System.out.println(colNames[i-1]);
                }
                
                aModel.setColumnIdentifiers(colNames);
            
            
                aModel.setRowCount(0);
                ResultSetMetaData rsmd = rs.getMetaData();
                int colNo = rsmd.getColumnCount();
                while(rs.next()){
                Object[] objects = new Object[colNo];
                    for(int i=0;i<colNo;i++){
                        objects[i]=rs.getObject(i+1);
                    }
                    aModel.addRow(objects);
                }
                donorTable.setModel(aModel);
            
            }catch(Exception ex){

            }
            
            JScrollPane tableContainer = new JScrollPane(donorTable);
            tableContainer.setPreferredSize(new Dimension(985,330));


            ButtonSensor2 ibs = new ButtonSensor2(app, this, name, email, contact, address, b1, donorTable, aModel);
            Insert.addActionListener(ibs);

            ButtonSensor2 dbs = new ButtonSensor2(app, this, id, email2, contact2, donorTable, aModel);
            Delete.addActionListener(dbs);
            
            ButtonSensor2 sds = new ButtonSensor2(app, this, email3, contact3, address2, b2, donorTable, aModel);
            Search.addActionListener(sds);
            
            ButtonSensor2 bs = new ButtonSensor2(app, this);
            Logout.addActionListener(bs);
            
            ButtonSensor2 ubs = new ButtonSensor2(mainId, name2, email4, contact4, address3, b3, donorTable, aModel, idCatch);
            Update.addActionListener(ubs);
            
            JButton Reset = new JButton("Reload Table");
            Reset.setBackground(new Color(102, 153, 255));
            ButtonSensor2 rst = new ButtonSensor2(donorTable, aModel);
            Reset.addActionListener(rst);
            Reset.setPreferredSize(new Dimension (985, 20));
            
            appPanel.add(tabbedPane, BorderLayout.CENTER);
            appPanel.add(Reset, BorderLayout.CENTER);
            appPanel.add(tableContainer, BorderLayout.PAGE_END);
            adminButtonPanel.add(Logout, BorderLayout.SOUTH);

            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}