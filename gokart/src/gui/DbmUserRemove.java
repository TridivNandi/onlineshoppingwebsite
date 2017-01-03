package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import util.SearchEngine;

import model.Admin;

public class DbmUserRemove extends JPanel implements ActionListener {

	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	protected JComboBox<String> userBox = null;
	protected JButton submit = new JButton("Submit");
	protected JButton cancel = new JButton("Cancel");
	SearchEngine s = null;
	
	public DbmUserRemove(JFrame frame) {
		// TODO Auto-generated constructor stub
		s= new SearchEngine();
		SearchEngine.setCon(Login.user.getConnection());
		userBox = new JComboBox<String>(s.getAllDbmUser());
		setLayout(new BorderLayout(30, 30));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel central = new JPanel();
		JPanel south = new JPanel();
		
		add(BorderLayout.CENTER, central);
		add(BorderLayout.SOUTH, south);
		
		central.setLayout(new FlowLayout());
		central.add(new JLabel("Enter Userame:"));
		central.add(userBox);
		
		south.setLayout(new FlowLayout());
		south.add(submit);
		south.add(cancel);
		
		submit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						boolean s = ((Admin)Login.user).removeDbmUser((String)userBox.getSelectedItem());
						if(!s){
							JOptionPane.showMessageDialog(null, "User not found");
							return;
						}
						JOptionPane.showMessageDialog(null, "Username: "+userBox.getSelectedItem()+" has been deleted");
						AdminUserFacilities.frame.dispose();
					}
				}
			);
		
		cancel.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AdminUserFacilities.frame.dispose();
					}
				}
			);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
