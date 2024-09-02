import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@WebServlet("/Book")
public class Book extends HttpServlet {
	@Override
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String zip = req.getParameter("zip");
		PrintWriter out = resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour", "root", "root");

			PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?,?,?)");
			ps.setString(1, fullname);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, city);
			ps.setString(6, zip);
			int count = ps.executeUpdate();
			if (count > 0) {
				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
				rd.include(req, resp);

			} else {
				resp.setContentType("text/html");
				out.print("<h3>Try Again ,not registered </h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/Home.jsp");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			out.print("<h1>" + e.getMessage() + "</h1>");
		}

	}
}
