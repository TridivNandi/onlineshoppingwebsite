package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainClass {

	
	static {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
			
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				LoginWindow l = new LoginWindow();
				l.attachToFrame();	
			}
		});
	}
}
