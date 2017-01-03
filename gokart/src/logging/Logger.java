package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import model.Admin;
import model.User;

public class Logger {

	private User tracked;
	public Logger(User tracked) {
		// TODO Auto-generated constructor stub
		this.tracked = tracked;		
	}
// F:\Documents\programs\java\j2ee\gokart
	public void updateLog() {
		//System.out.println("java.home: " + System.getProperty("java.home"));
		//System.out.println("user.home: " + System.getProperty("user.home"));
		//System.out.println("user.dir: " + System.getProperty("user.dir"));
		String path ="./logs/";
		if(tracked instanceof Admin)
			path = path + "Admin.log";
		else
			path = path + "DbmUser.log";
		File file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			String usn = "";
			PrintWriter writer = new PrintWriter(new FileWriter(file , true));
			if(!tracked.getUserDetails().isEmpty())
				usn = (String)tracked.getUserDetails().get(0);
			Date date = new Date();
			writer.println(usn + " logged in @ " + date);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
