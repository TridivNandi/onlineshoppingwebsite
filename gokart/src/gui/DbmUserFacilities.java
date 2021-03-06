package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class DbmUserFacilities extends JPanel implements ActionListener {

	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
	protected JButton add = new JButton("Add Product");
	protected JButton remove = new JButton("Remove Product");
	protected JButton update = new JButton("Update Product");
	protected JLabel logOff = new JLabel("<html><u>Log Off</u></html>");
	protected static JFrame frame;
	public DbmUserFacilities(final JFrame frm) {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(30, 30));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel east = new JPanel();
		JPanel central = new JPanel();
		JPanel south = new JPanel();
		
		add(BorderLayout.EAST, east);
		add(BorderLayout.CENTER, central);
		add(BorderLayout.SOUTH, south);
		
		central.add(new JLabel("What do you want to do ?"));
		
		east.add(logOff);
		
		south.setLayout(new GridLayout(3,1,100,100));
		south.add(add);
		south.add(remove);
		south.add(update);
		
		add.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						frame = new JFrame("Add Product");
						JFrame.setDefaultLookAndFeelDecorated(true);
						ProductAdd panel = new ProductAdd(frame);
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						frame.setContentPane(panel);
						frame.setResizable(true);
						frame.pack();
						frame.setVisible(true);
					}
				});
		remove.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
			
						frame = new JFrame("Remove Product");
						JFrame.setDefaultLookAndFeelDecorated(true);
						ProductRemove panel = new ProductRemove(frame);
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						frame.setContentPane(panel);
						frame.setResizable(true);
						frame.pack();
						frame.setVisible(true);
					}
				});
		update.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						frame = new JFrame("Update Product");
						JFrame.setDefaultLookAndFeelDecorated(true);
						ProductUpdate panel = new ProductUpdate(frame);
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						frame.setContentPane(panel);
						frame.setResizable(true);
						frame.pack();
						frame.setVisible(true);
					}
				});
		logOff.addMouseListener(
				new MouseListener() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						frm.dispose();
						LoginWindow l = new LoginWindow();
						l.attachToFrame();
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						logOff.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						logOff.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
