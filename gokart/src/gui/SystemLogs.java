package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SystemLogs extends JPanel implements ActionListener {
	
	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	protected JButton adminLog = new JButton("Admin Logs");
	protected JButton dbmLog = new JButton("DBM-User Logs");

	public SystemLogs() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(30, 30));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel north = new JPanel();
		JPanel central = new JPanel();
		
		add(BorderLayout.NORTH, north);
		add(BorderLayout.CENTER, central);
		
		north.add(new JLabel("Select Log File:"));
		
		central.setLayout(new GridLayout(2,1,100,100));
		central.add(adminLog);
		central.add(dbmLog);
		adminLog.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String path = "./logs/Admin.log";
						try {
							Runtime.getRuntime().exec("notepad" + " " + path);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					}
				});
		dbmLog.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String path = "./logs/DbmUser.log";
						try {
							Runtime.getRuntime().exec("notepad" + " " + path);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					}
				});
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
