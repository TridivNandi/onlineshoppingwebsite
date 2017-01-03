package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class LoginWindow {
	
	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected static JFrame frame = new JFrame("Staff Login");
		
	public void setClose(JFrame fr) {
		fr.setVisible(false);
	}
	public void attachToFrame(){
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		Login panel = new Login(frame);
		//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
		
	}
}
