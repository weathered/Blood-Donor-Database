package DefaultApplication;

import DataAccess.DataAccess;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

class WindowSensor extends WindowAdapter{
	public void windowClosing(WindowEvent we){
		System.exit(0);
	}
}

public class Application extends JFrame{
    
    JPanel mainPanel = new JPanel();
    JPanel adminButtonPanel = new JPanel();
    JPanel appPanel = new JPanel();
    JButton adminButton = new JButton("Database");
    int n;
    String bg[] = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    int num[] = {0,0,0,0,0,0,0,0};
	
	public Application(){
		super("Blood Donor Information");
                //JPanel mainPanel = new JPanel();
                mainPanel.setBackground(Color.WHITE);
                
                ImageIcon img = new ImageIcon(getClass().getResource("/blood.jpg"));
                JLabel imgLabel = new JLabel(img);
                
                //JPanel adminButtonPanel = new JPanel();
                adminButtonPanel.setBackground(Color.WHITE);
                
                JPanel statusPanel = new JPanel();
                statusPanel.setBackground(Color.WHITE);
                
                //JPanel appPanel = new JPanel();
                appPanel.setBackground(Color.WHITE);
                
                Box statusBox = Box.createVerticalBox();
                
                mainPanel.setLayout(new BorderLayout());
                statusPanel.setLayout(new BorderLayout());
                adminButtonPanel.setLayout(new BorderLayout());
                
                adminButtonPanel.add(adminButton, BorderLayout.NORTH);
               
                //mainPanel.add(adminButtonPanel, BorderLayout.SOUTH);
                mainPanel.add(statusPanel, BorderLayout.WEST);
                mainPanel.add(appPanel, BorderLayout.CENTER);
                appPanel.add(imgLabel, BorderLayout.NORTH);
                
                this.add(mainPanel);
                
                DataAccess da = new DataAccess();
                ResultSet rs = null;
                
                String q="select * from donortable";
                rs = da.getData(q);
                try{
                        n = rs.last() ? rs.getRow() : 0;
                        da.close();
                }
                catch(Exception e){
                    
                }
                
                statusBox.setBorder(BorderFactory.createTitledBorder("Database Status"));
                JLabel donorTitle = new JLabel("Donors Available : "+n);
                
                JLabel groups[] = new JLabel[8];
                
                for (int i = 0; i < 8; i++){
                    q="select * from donortable where bloodgroup = '"+bg[i]+"'";
                    rs = da.getData(q);
                    try{
                        num[i] = rs.last() ? rs.getRow() : 0;
                    }
                    catch(Exception e){}
                    
                    groups[i] = new JLabel(bg[i]+" Donors  :   "+num[i]);
                    statusBox.add(groups[i]);
                }
                
                JLabel text = new JLabel("Created by Al-Amin");
                statusPanel.add(text, BorderLayout.SOUTH);
                statusPanel.add(statusBox, BorderLayout.NORTH);
                statusPanel.add(adminButtonPanel, BorderLayout.CENTER);
                statusBox.add(new JSeparator(SwingConstants.HORIZONTAL));
                statusBox.add(donorTitle);
                
                setSize(1150, 700);
                //setExtendedState(JFrame.MAXIMIZED_BOTH);
                setMinimumSize(new Dimension(1150, 700));
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
}