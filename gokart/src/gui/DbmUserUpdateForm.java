package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import security.Encrypt;
import security.SystemUnavailableException;

import model.Admin;

public class DbmUserUpdateForm extends JPanel {

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
	protected JPasswordField  password= new JPasswordField(30);
	protected JTextField  firstName= new JTextField(30);
	protected JTextField  lastName= new JTextField(30);
	protected JTextField  phone= new JTextField(30);
	public DbmUserUpdateForm(final JFrame frame) {
		// TODO Auto-generated constructor stub
	
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel central = new JPanel();
		JPanel south = new JPanel();
		
		add(BorderLayout.CENTER, central);
		add(BorderLayout.SOUTH, south);
		
		central.setLayout(new GridLayout(5,2,0,10));
		central.add(new JLabel("Username"));
		central.add(username);
		central.add(new JLabel("Password"));
		central.add(password);
		central.add(new JLabel("First Name"));
		central.add(firstName);
		central.add(new JLabel("Last Name"));
		central.add(lastName);
		central.add(new JLabel("Phone"));
		central.add(phone);
		
		south.setLayout(new FlowLayout());
		south.add(ok);
		south.add(cancel);
		
		firstName.setText((String) DbmUserUpdate.user.get(0));
		lastName.setText((String) DbmUserUpdate.user.get(1));
		username.setText((String) DbmUserUpdate.user.get(2));
		//password.setText((String) DbmUserUpdate.user.get(3));
		phone.setText((String) DbmUserUpdate.user.get(4));
		
		username.setEditable(false);
		username.setBackground(Color.LIGHT_GRAY);
		
		ok.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						DbmUserUpdate.user = new ArrayList<Object>();
						
						DbmUserUpdate.user.add(firstName.getText());
						DbmUserUpdate.user.add(lastName.getText());
						DbmUserUpdate.user.add(username.getText());
						try {
							DbmUserUpdate.user.add(Encrypt.getInstance().hash("SHA-512", new String(password.getPassword()), true));
						} catch (SystemUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						DbmUserUpdate.user.add(phone.getText());
						if(firstName.getText().equals("")||lastName.getText().equals("")||username.getText().equals("")||(new String(password.getPassword())).equals("")){
							JOptionPane.showMessageDialog(null, "Insufficient information for user creation");
							return;
						}
						if(((Admin)Login.user).updateDbmUser(DbmUserUpdate.user)){
							JOptionPane.showMessageDialog(null, "User successfully updated");
							frame.dispose();
						}	
						else
							JOptionPane.showMessageDialog(null, "Username could not be updated");
					
					}
				}
			);
		cancel.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JFrame parent = ((JFrame)DbmUserUpdateForm.this.getParent().getParent().getParent());
						parent.dispose();
					
					}
				}
			);
	}
}
