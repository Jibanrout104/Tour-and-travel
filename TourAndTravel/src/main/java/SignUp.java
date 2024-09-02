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
;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);

		String name = req.getParameter("name");
		String contact = req.getParameter("contact");
		String email = req.getParameter("email");
		String pass = req.getParameter("createpass");
		String pass2 = req.getParameter("confirmpass");
		PrintWriter out = resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour", "root", "root");

			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, contact);
			ps.setString(3, email);
			ps.setString(4, pass);
			ps.setString(5, pass2);
			int count = ps.executeUpdate();
			if (count > 0) {
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
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
