package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import security.Encrypt;
import security.SystemUnavailableException;

import logging.Logger;
import model.Admin;
import model.DbmUser;
import model.User;
//Forms the panel for the login screen.
public class Login extends JPanel implements ActionListener {

	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	protected JButton ok = new JButton("Ok");
	protected JButton cancel = new JButton("Cancel");
	protected JTextField username = new JTextField(30);
	protected JPasswordField password = new JPasswordField(20);
	JComboBox<String> loginType = null;
	
	
	protected static User user;
	
	
	public Login(JFrame fr){
		loginType = new JComboBox<String>(new String[]{"DBM-USER", "ADMIN"});
		setLayout(new BorderLayout(30, 30));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel north = new JPanel();
		JPanel central = new JPanel();
		JPanel south = new JPanel();
		
		add(BorderLayout.NORTH, north);
		add(BorderLayout.CENTER, central);
		add(BorderLayout.SOUTH, south);
		
		central.setLayout(new GridLayout(10,2,10,10));
		central.add(new JLabel("Username"));
		central.add(username);
		central.add(new JLabel("Password"));
		central.add(password);
		central.add(new JLabel("Login as:"));
		central.add(loginType);
		
		south.setLayout(new FlowLayout());
		south.add(ok);
		south.add(cancel);
		
		north.add(new JLabel(new ImageIcon("src/gui/gokart_logo.jpg")));
		
		ok.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						String usn = username.getText();
						char[] pass = null;
						
						/*try {
							pass = (Encrypt.getInstance().hash("SHA-512", new String(password.getPassword()), true)).toCharArray();
						} catch (SystemUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}*/
						
						if(((String)loginType.getSelectedItem()).equals("DBM-USER")) {
							user = new DbmUser();
							try {
								pass = (Encrypt.getInstance().hash("SHA-512", new String(password.getPassword()), true)).toCharArray();
							} catch (SystemUnavailableException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							if(user.connect(usn, pass) == true) {
								
								Logger logger = new Logger(user);
								logger.updateLog();
								
								LoginWindow.frame.dispose();
								JFrame frame = new JFrame("Welcome DBM-User");
								JFrame.setDefaultLookAndFeelDecorated(true);
								DbmUserFacilities panel = new DbmUserFacilities(frame);
								frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
								frame.setContentPane(panel);
								frame.setResizable(true);
								frame.setSize(297,404);
								frame.setVisible(true);
							}
							else
								JOptionPane.showMessageDialog(null, "Invalid Username or Password");
						}
						else if(((String)loginType.getSelectedItem()).equals("ADMIN")) { 
							
							user = new Admin();
							if(user.connect(usn, password.getPassword()) == true) {
								
								Logger logger = new Logger(user);
								logger.updateLog();
								
								LoginWindow.frame.dispose();
								JFrame frame1 = new JFrame("Welcome Administrator");
								JFrame.setDefaultLookAndFeelDecorated(true);
								AdminUserFacilities panel1 = new AdminUserFacilities(frame1);
								frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
								frame1.setContentPane(panel1);
								frame1.setResizable(true);
								frame1.pack();
								//frame1.setSize(392,392);
								frame1.setVisible(true);
							}
							else
								JOptionPane.showMessageDialog(null, "Invalid Username or Password");
							
					}
				}
			});
		cancel.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
