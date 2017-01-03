package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DbmUser;

public class ProductAdd extends JPanel implements ActionListener {
	
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
	protected JTextField productName = new JTextField(30);
	protected JTextField  category= new JTextField(30);
	protected JTextField  status= new JTextField(30);
	protected JTextField  quantity= new JTextField(30);
	protected JFileChooser productImage = new JFileChooser();
	protected JButton browse = new JButton("Browse");
	protected JTextField  basePrice= new JTextField(30);
	protected JTextField  discount= new JTextField(30);
	protected JTextArea  briefDescription= new JTextArea();
	protected JScrollPane pane = new JScrollPane(briefDescription); 
	java.awt.Component parent;
	JLabel fileName = null;
	public ProductAdd(JFrame frame){
			
			setLayout(new BorderLayout(10, 10));
			setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			
			JPanel central = new JPanel();
			JPanel south = new JPanel();
			JPanel p = new JPanel();
			fileName = new JLabel();
			p.setLayout(new FlowLayout());
			add(BorderLayout.CENTER, central);
			add(BorderLayout.SOUTH, south);
			
			central.setLayout(new GridLayout(8,2,0,10));
			central.add(new JLabel("Product Name"));
			central.add(productName);
			central.add(new JLabel("Category"));
			central.add(category);
			central.add(new JLabel("Status"));
			central.add(status);
			central.add(new JLabel("Quantity"));
			central.add(quantity);
			central.add(new JLabel("Image"));
			p.add(fileName);
			p.add(browse);
			central.add(p);
			central.add(new JLabel("Base-Price"));
			central.add(basePrice);
			central.add(new JLabel("Discount"));
			central.add(discount);
			central.add(new JLabel("Brief-Description"));
			central.add(pane);
			
			
			south.setLayout(new FlowLayout());
			south.add(ok);
			south.add(cancel);
			
		
			
			ok.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ArrayList<Object> productDetails = new ArrayList<Object>();
							productDetails.add(productName.getText());
							productDetails.add(category.getText());
							String imagePath = productImage.getSelectedFile().getPath();
							productDetails.add(imagePath);
							
							String desc = briefDescription.getText();
							/*File tmp = null;
							try {
								tmp = File.createTempFile("desc", null, null);
								FileWriter writer = new FileWriter(tmp);
								writer.write(desc);
								writer.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							productDetails.add(tmp);*/
							
							productDetails.add(desc);
							
							productDetails.add(0.0);
							productDetails.add("");
							productDetails.add(quantity.getText());	
							productDetails.add(basePrice.getText());
							productDetails.add(discount.getText());
							productDetails.add(status.getText());
							
							if(productName.getText().equals("")||category.getText().equals("")||quantity.getText().equals("")||basePrice.getText().equals("")||discount.getText().equals("")||status.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Missing fields");
								return;
							}

							if(((DbmUser)Login.user).addProduct(productDetails)){
								JOptionPane.showMessageDialog(null, "Product added successfully");
								DbmUserFacilities.frame.dispose();
							}
							else
								JOptionPane.showMessageDialog(null, "Error:Product name already exists!");
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
			browse.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
						    productImage.setFileFilter(filter);
						    int returnVal = productImage.showOpenDialog(parent);
						    if(returnVal == JFileChooser.APPROVE_OPTION) {
						    	String fname = productImage.getSelectedFile().getName();
						        if(fname.length() > 10)
						        	fname = fname.substring(0, 10) + "..";
						        fileName.setText(fname);
						    	   
						    }
						}
					}
				);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
