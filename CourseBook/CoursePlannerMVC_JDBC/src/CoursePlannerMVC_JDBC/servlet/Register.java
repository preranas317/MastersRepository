package CoursePlannerMVC_JDBC.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
	}

	@SuppressWarnings("unused")
	private boolean getuname(String uname, List<UserEntry> list) {
		for (int i = 0; i < list.size(); i++)
			if (uname.equals(list.get(i).getUname()))
				return true;

		return false;
	}
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<UserEntry> list = new ArrayList<UserEntry>();
		
		Connection c = null;
		try
		{
			String url = "databaseURL";
			String username = "UserName";
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
		  request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(
		  request, response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// JDBC
					String sql;
				 Connection c = null;
			        try
			        {
			        	String url = "databaseURL";
			            String username = "username";
			            String password = "password";
			            sql = "insert into login_users values (?,?,?,?)";

			            c = DriverManager.getConnection( url, username, password );
			        }
			        catch( SQLException e )
			        {
			            throw new ServletException( e );
			        }
			        
				// JDBC over
	
		Map<String, String> message=new HashMap<String,String>();
	
		@SuppressWarnings("unchecked")
		List<UserEntry> list = (List<UserEntry>) getServletContext()
				.getAttribute("list");

	
		String uname = request.getParameter("uname");
		String pswd = request.getParameter("pswd");
		String repswd = request.getParameter("repswd");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");

		System.out.println("uname : " + uname + "\npswd : " + pswd
				+ "\nrepswd : " + repswd + "\nfname : " + fname + "\nlname : "
				+ lname);

		System.out.println("list size" + list.size());
		
		try
		{
			   Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from login_users" );
	            	
	            boolean flag=false;
	            while( rs.next() )
	            {
	            	flag=false;
	                if(uname.equalsIgnoreCase(rs.getString("username")))
	                {
	                	System.out.println("UserName already exists.please fill up data with different username");
	                	message.put("m5", "UserName already exists.please fill up data with different username");
	                	request.setAttribute("message", message);
	                	flag=true;
	                	doGet(request, response);
	                	break;
	                }
	            }
	                if(flag==false)
	                {
	                	if (uname.length() < 4 || pswd.length() < 4 || !(pswd.equals(repswd))
	            				|| uname.trim().length() == 0
	            				|| pswd.trim().length() == 0) {

	            			if (uname.trim().length() == 0 || pswd.trim().length() == 0
	            					|| repswd.trim().length() == 0) {
	            				System.out
	            						.println("Please Fill up all entries and Enter the data again.");
	            				message.put("m1", "Please Fill up all entries and Enter the data again.");
	            				
	            			}

	            			else{
	            				if (uname.length() < 4) {
	            					System.out
	            							.println("UserName should be at least 4 characters.Please Enter the data again.");
	            					message.put("m2", "UserName should be at least 4 characters.Please Enter the data again.");
	            			
	            				}

	            				if (pswd.length() < 4){
	            					System.out
	            							.println("Password should be at least 4 characters.Please Enter the data again.");
	            					message.put("m3", "Password should be at least 4 characters.Please Enter the data again.");
	            				
	            					}

	            				if (!(pswd.equals(repswd))){
	            					System.out
	            							.println("Password and re-typed Password does not match.Please Enter the data again.");
	            					message.put("m4", "Password and re-typed Password does not match.Please Enter the data again.");
	            					//out.println("\nPassword and re-typed Password does not match.Please Enter the data again.");
	            					}
		            			}
	            			request.setAttribute("message", message);
            				request.getRequestDispatcher("/WEB-INF/Register.jsp").forward(
            						  request, response);
            				return;
            				
	            		} else {
	            		
	            			 PreparedStatement pstmt;
	            			try {
	            				pstmt = c.prepareStatement( sql );
	            				 pstmt.setString( 1, uname );
	            		            pstmt.setString( 2, pswd );
	            		            pstmt.setString( 3, fname );
	            		            pstmt.setString( 4, lname );
	            		            
	            		            pstmt.executeUpdate();
	            			} catch (SQLException e) {
	            				
	            				e.printStackTrace();
	            			}
	            	           
	            			System.out.println("list size" + list.size());
	            			response.sendRedirect("CourseBook_JDBC");
	            			return;
	            		}
	                }
	                
	            }
	       
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
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

				
	}
}
