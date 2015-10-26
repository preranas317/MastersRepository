package CoursePlannerMVC_JDBC.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CoursePlannerMVC_JDBC.model.CourseBookEntry;

@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCourse() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection c = null;
		List<CourseBookEntry> bookentry = new ArrayList<CourseBookEntry>();
		try
		{
			String url = "database URL";
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
		getServletContext().setAttribute("bookentry", bookentry);
		
		request.getRequestDispatcher("/WEB-INF/AddCourse.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Integer id = Integer.valueOf( request.getParameter( "id" ) );
		String sql;
		 Connection c = null;
	        try
	        {
	        	String url = "database URL";
	            String username = "username";
	            String password = "password";
	            sql = "update courses set scode=?,stitle=?,spreq=?";

	            c = DriverManager.getConnection( url, username, password );
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	          
		
		String code = request.getParameter("code");
		String title = request.getParameter("title");

		List<CourseBookEntry> bookentry = (List<CourseBookEntry>) getServletContext()
				.getAttribute("bookentry");

		String[] preq = request.getParameterValues("preq");
		String reqnme  ;
		if(preq!=null){
			reqnme=preq[0];
		for (int i=1;i<preq.length;i++) {
			reqnme = reqnme + " " + preq[i];
		}
		System.out.println(reqnme);
		}
		else
			reqnme="";
		
		 sql = "insert into courses(scode,stitle,spreq) values(?,?,?)";
			PreparedStatement pstmt;
			
			try {
				
				pstmt = c.prepareStatement( sql );
				 pstmt.setString( 1, code );
		            pstmt.setString( 2, title );
		            pstmt.setString( 3, reqnme );
		            
		            
		            pstmt.executeUpdate();
		            System.out.println("inserted...");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
		
		response.sendRedirect("CourseBook_JDBC");
	}

}
