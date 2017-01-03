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

public class ProductUpdateForm extends JPanel implements ActionListener{
	
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
	public ProductUpdateForm(JFrame fr){
			
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
			
			productName.setEditable(false);
			productName.setBackground(Color.LIGHT_GRAY);
			
			
			productName.setText((String) ProductUpdate.addedProduct.get(0));
			category.setText((String) ProductUpdate.addedProduct.get(1));
			
			//fileName.setText((String)ProductUpdate.addedProduct.get(2));
			
			briefDescription.setText((String) ProductUpdate.addedProduct.get(3));
			quantity.setText("" + ProductUpdate.addedProduct.get(6));
			basePrice.setText("" + ProductUpdate.addedProduct.get(7));
			discount.setText("" + ProductUpdate.addedProduct.get(8));
			status.setText("" + ProductUpdate.addedProduct.get(9));
			
			ok.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						
							ProductUpdate.addedProduct = new ArrayList<Object>();
							
							ProductUpdate.addedProduct.add(productName.getText());
							ProductUpdate.addedProduct.add(category.getText());
							String imagePath = productImage.getSelectedFile().getPath();
							ProductUpdate.addedProduct.add(imagePath);
							
							//addedProduct.add(briefDescription.getText());
							
							
							ProductUpdate.addedProduct.add(briefDescription.getText());
							//BufferedReader br = new BufferedReader(new InputStreamReader(is));
							/*String text = "";
							char t;
							try {
								while((t = (char) is.read()) != -1)
									text = text + t;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}*/
							
							
							ProductUpdate.addedProduct.add(null);
							ProductUpdate.addedProduct.add(null);
							
							ProductUpdate.addedProduct.add(quantity.getText());	
							ProductUpdate.addedProduct.add(basePrice.getText());
							ProductUpdate.addedProduct.add(discount.getText());
							ProductUpdate.addedProduct.add(status.getText());
							
							if(category.getText().equals("")||quantity.getText().equals("")||basePrice.getText().equals("")||discount.getText().equals("")||status.getText().equals("")){
								return;
							}
							
							if(((DbmUser)Login.user).updateProduct(ProductUpdate.addedProduct))
								JOptionPane.showMessageDialog(null, "Product successfully updated");
							else
								JOptionPane.showMessageDialog(null, "Product name already exists!");
						
						}
					}
				);
			
			
			cancel.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							JFrame parent = ((JFrame)ProductUpdateForm.this.getParent().getParent().getParent());
							parent.dispose();
						
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
		
			