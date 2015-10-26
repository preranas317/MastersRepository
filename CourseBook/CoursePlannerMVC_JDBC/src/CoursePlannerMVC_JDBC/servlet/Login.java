package CoursePlannerMVC_JDBC.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CoursePlannerMVC_JDBC.model.UserEntry;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<UserEntry> list = new ArrayList<UserEntry>();
		Connection c = null;
		try
		{
			String url = "database url";
			String username = "username";
			String password = "password";
			
			c = DriverManager.getConnection(url, username, password);
		} 
		catch (SQLException e) {
			throw new ServletException(e);
		}
		
try {
			
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login_users");
			while (rs.next()) 
			{
				UserEntry user = new UserEntry(rs.getString("username"),rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

finally
{
    try
    {
        if( c != null ) c.close();
    }
    catch( SQLException e )
    {
        throw new ServletException( e );
    }
}
getServletContext().setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> message=new HashMap<String,String>();
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		String pswd = request.getParameter("pswd");
		boolean flag = false;

		List<UserEntry> list = (List<UserEntry>) getServletContext()
				.getAttribute("list");

		System.out.println("uname : " + uname + "pswd : " + pswd);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("i : " + i);
			System.out.println("list uname : " + list.get(i).getUname()
					+ "pswd : " + list.get(i).getPswd());

			if (list.get(i).getUname().equals(uname)
					&& list.get(i).getPswd().equals(pswd)) {
				System.out.println("Password Matched");
				request.getSession().setAttribute("uname", uname);
				flag = true;
				response.sendRedirect("CourseBook_JDBC");
				break;
			}

		}
		if (flag == false) {
			message.put("m1","Wrong Username or Password");
			request.setAttribute("message", message);
			doGet(request, response);
		}

	}

}
