package adminlogin;

import DataAccess.DataAccess;
import DefaultApplication.MainWindow;
import DefaultApplication.choiceAdmin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class KeySensor implements KeyListener{
    MainWindow a;
    adminLogin ad;
    JTextField tf;
    JPasswordField tf2;
    JLabel a1;
    public KeySensor(MainWindow a, adminLogin ad, JTextField t, JPasswordField t2, JLabel a1){
        this.a=a;
        this.ad=ad;
        this.tf=t;
        this.tf2=t2;
        this.a1=a1;
    }

    public void keyPressed(KeyEvent e) {
        try{
         if(e.getKeyCode() == KeyEvent.VK_ENTER){
             String us=tf.getText();
                    String ps=new String(tf2.getPassword());
                
                    try{
                        DataAccess da = new DataAccess();
                        ResultSet rs = null;
                        
                        String q="select * from admintable";
                        rs = da.getData(q);
                        
                        while(rs.next()){
                            String u = rs.getString("username");
                            String p = rs.getString("password");
                            
                            if(us.equals(u) && ps.equals(p)){
                                a1.setText("Login Successful, Please Wait...");
                                ad.dispose();
                                new choiceAdmin(a);
                            }else{
                                a1.setText("Login Failed, Please check your credentials!");
                            }
                        }
                        da.close();
                    }
                    catch(Exception ex){
                        
                    }
                }
            }catch(Exception ex){
                 
         }
    }
    

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}
    
}

class WindowSensor2 extends WindowAdapter{
	public void windowClosing(WindowEvent we){
		System.exit(0);
	}
}
class ButtonSensor implements ActionListener{
	JTextField tf;
        JPasswordField tf2;
        MainWindow a;
        adminLogin ad;
        JLabel a1;
        public ButtonSensor(MainWindow a, adminLogin ad){
            this.a = a;
            this.ad = ad;
        }
	public ButtonSensor(MainWindow a, adminLogin ad, JTextField t, JPasswordField t2, JLabel a1){
		this.tf=t;
		this.tf2=t2;
                this.a = a;
                this.a1 = a1;
                this.ad = ad;
	}
        
	public void actionPerformed(ActionEvent ae){
		String ac=ae.getActionCommand();
                
                if(ac.equals("Login")){
                    String us=tf.getText();
                    String ps=new String(tf2.getPassword());
                
                    try{
                        DataAccess da = new DataAccess();
                        ResultSet rs = null;
                        
                        String q="select * from admintable";
                        rs = da.getData(q);
                        
                        while(rs.next()){
                            String u = rs.getString("username");
                            String p = rs.getString("password");
                            
                            if(us.equals(u) && ps.equals(p)){
                                System.out.println("Loginhueheuhehehueueheheuheuheuhue");
                                a1.setText("Login Successful, Please Wait...");
                                ad.dispose();
                                new choiceAdmin(a);
                            }else{
                                a1.setText("Login Failed, Please check your credentials!");
                            }
                        }
                        da.close();
                    }
                    catch(Exception e){
                        
                    }
                }else if(ac.equals("Cancel")){
                    ad.dispose();
                }    
	}
}

public class adminLogin extends JFrame{
    
    private final MainWindow app;
    
    public adminLogin(MainWindow a){
        
        super("Administrator Login");
        
        app = a;
        JPanel adminPanel = new JPanel();
        
        this.setLayout(new BorderLayout());
        JLabel userName = new JLabel("Username : ");
        JLabel password = new JLabel("Password : ");
        
        JTextField user = new JTextField(20);
        JPasswordField pass = new JPasswordField(20);
        
        JButton Login = new JButton("Login");
        JButton Cancel = new JButton("Cancel");
        
        JLabel message = new JLabel("");
        
        ButtonSensor lbs = new ButtonSensor(app, this, user, pass, message);
        ButtonSensor ebs = new ButtonSensor(app, this);
        Login.addActionListener(lbs);
        Cancel.addActionListener(ebs);
        
        KeySensor ks = new KeySensor(app, this, user, pass, message);
        user.addKeyListener(ks);
        pass.addKeyListener(ks);
        
        adminPanel.add(userName, BorderLayout.NORTH);
        adminPanel.add(user, BorderLayout.NORTH);
        adminPanel.add(password, BorderLayout.NORTH);
        adminPanel.add(pass, BorderLayout.NORTH);
        adminPanel.add(Login, BorderLayout.CENTER);
        adminPanel.add(Cancel, BorderLayout.CENTER);
        adminPanel.add(message, BorderLayout.SOUTH);
        
        add(adminPanel);
        
        setSize(290, 220);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
