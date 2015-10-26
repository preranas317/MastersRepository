package CoursePlannerMVC_JDBC.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CoursePlannerMVC_JDBC.model.*;

@WebServlet("/CourseBook_JDBC")
public class CourseBook_JDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CourseBook_JDBC() {
		super();

	}

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		/// JDBC Connection....
		
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			throw new ServletException(e);
		}


	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uname = (String) request.getAttribute("uname");
		request.setAttribute("uname", uname);
		
		List<CourseBookEntry> bookentry = new ArrayList<CourseBookEntry>();
	//	List<UserEntry> list = new ArrayList<UserEntry>();

		
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
				ResultSet rs = stmt.executeQuery("select * from courses");
				while (rs.next()) 
				{
					CourseBookEntry cb = new CourseBookEntry(rs.getInt("id"),rs.getString("scode"),rs.getString("stitle"),rs.getString("spreq"));
					System.out.println(" cb : "+cb);
					bookentry.add(cb);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		System.out.println("bookentry size :"+bookentry.size());	
		getServletContext().setAttribute("bookentry", bookentry);
		request.getRequestDispatcher("/WEB-INF/CourseBook_JDBC.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
