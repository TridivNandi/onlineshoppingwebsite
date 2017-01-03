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

import model.DbmUser;
import util.SearchEngine;

public class ProductRemove extends JPanel implements ActionListener{
	
	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	JComboBox<String> pBox = null;
	SearchEngine s = null;
	protected JButton submit = new JButton("Submit");
	protected JButton cancel = new JButton("Cancel");
	public ProductRemove(JFrame frame) {
		// TODO Auto-generated constructor stub
		s= new SearchEngine();
		SearchEngine.setCon(Login.user.getConnection());
		pBox = new JComboBox<String>(s.getAllProducts());
		setLayout(new BorderLayout(30, 30));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel central = new JPanel();
		JPanel south = new JPanel();
		
		add(BorderLayout.CENTER, central);
		add(BorderLayout.SOUTH, south);
		
		central.setLayout(new FlowLayout());
		central.add(new JLabel("Enter Product Name:"));
		central.add(pBox);
		
		south.setLayout(new FlowLayout());
		south.add(submit);
		south.add(cancel);
	
		submit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean r = ((DbmUser)Login.user).removeProduct((String)pBox.getSelectedItem());
						if (r == false){
							JOptionPane.showMessageDialog(null, "Product not found");
						}
						else{
							JOptionPane.showMessageDialog(null, "Product Name: "+(String)pBox.getSelectedItem()+" has been deleted");
							DbmUserFacilities.frame.dispose();
						}
					}
				}
			);
		cancel.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DbmUserFacilities.frame.dispose();
					}
				}
			);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

}
