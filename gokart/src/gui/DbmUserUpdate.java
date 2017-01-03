package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import util.SearchEngine;

public class DbmUserUpdate extends JPanel implements ActionListener {

	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	JComboBox<String> userBox = null;
	protected JButton submit = new JButton("Submit");
	protected JButton cancel = new JButton("Cancel");
	SearchEngine s = null;
	protected static ArrayList<Object> user;
	
	public DbmUserUpdate(final JFrame frame) {
		s= new SearchEngine();
		SearchEngine.setCon(Login.user.getConnection());
		userBox = new JComboBox<String>(s.getAllDbmUser());
		// TODO Auto-generated constructor stub
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
						SearchEngine s= new SearchEngine();
						user = s.searchDbmUser((String)userBox.getSelectedItem());
						if(user != null) {
							JFrame fr = new JFrame("Update DBM-User");
							JFrame.setDefaultLookAndFeelDecorated(true);
							DbmUserUpdateForm panel = new DbmUserUpdateForm(fr);
							
							//fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
							fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							fr.setContentPane(panel);
							fr.setResizable(true);
							fr.pack();
							fr.setVisible(true);
							frame.dispose();
							AdminUserFacilities.frame.dispose();
						}
						else 
							JOptionPane.showMessageDialog(null, "User does not exist");
					}
				});
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
