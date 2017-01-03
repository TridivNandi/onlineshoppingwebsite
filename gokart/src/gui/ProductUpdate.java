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

public class ProductUpdate extends JPanel implements ActionListener {
	
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
	protected JButton submit = new JButton("Submit");
	protected JButton cancel = new JButton("Cancel");
	SearchEngine s = null;
	
	protected static ArrayList<Object> addedProduct;
	
	public ProductUpdate(final JFrame frame) {
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
						
						addedProduct = s.searchProduct((String)pBox.getSelectedItem());
						if(addedProduct == null){
							JOptionPane.showMessageDialog(null, "Product not found");
							return;
						}
						JFrame fr = new JFrame("Update Product");
						JFrame.setDefaultLookAndFeelDecorated(true);
						ProductUpdateForm panel = new ProductUpdateForm(fr);
						
						//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
						fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						fr.setContentPane(panel);
						fr.setResizable(true);
						fr.pack();
						fr.setVisible(true);
						frame.dispose();
						DbmUserFacilities.frame.dispose();
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
