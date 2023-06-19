package javafiles;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Register")
public class RegistrationClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upassword = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/tejm32";
		String user = "root";
		String password = "Mysql@348";
		String query = "insert into user_details (user_name,user_email,user_password,user_mobile) values (?,?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,password);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, uemail);
			ps.setString(3, upassword);
			ps.setString(4, umobile);
			int result = ps.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(result!=0) {
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
	}

}
