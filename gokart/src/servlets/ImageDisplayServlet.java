package servlets;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.OracleDriver;

/**
 * Servlet implementation class ImageDisplayServlet
 */
@WebServlet("/ImageDisplayServlet")
public class ImageDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		System.out.println("Listened");
		Connection conn = null;
		try {
			OracleDriver driver = new OracleDriver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "tiger");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String pname = request.getParameter("Pname");
		System.out.println("Listened");
		String sql = "select IMAGE from ADMIN.PRODUCT where Pname=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pname);
			ResultSet rs = ps.executeQuery();
			rs.next();
			InputStream is = rs.getBinaryStream(1);
			ImageOutputStream os = new MemoryCacheImageOutputStream(response.getOutputStream());
			RenderedImage img = ImageIO.read(is);
			ImageIO.write(img, "image/jpeg", os);
			System.out.print("#Listened" + img);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
