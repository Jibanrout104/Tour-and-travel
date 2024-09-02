import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String username = req.getParameter("txtemail");
		String password = req.getParameter("txtpass");

		PrintWriter out = resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from register where emailid=? and password=?");

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				System.out.println("Login successful for user: " + username);
//				resp.sendRedirect("./index.html");
//			} else {
//				System.out.println("Login failed for user: " + username);
//				resp.sendRedirect("./login.html");
//			}

//			while (rs.next()) {
//				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
//				rd.include(req, resp);
//				String name = rs.getString(1);
//				String pass = rs.getString(2);
//				if (name.equals(username) && pass.equals(password)) {
//					resp.sendRedirect("./index.html");
//				} else {
//					resp.sendRedirect("./login.html");
//				}
//			}

			if (rs.next()) {

				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
				rd.include(req, resp);
			} else {
				resp.setContentType("text/html");
				out.print("<h2>Wrong Username and Password , Try again! </h2>");
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp);

			}

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
