package CoursePlannerMVC_JDBC.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CoursePlannerMVC_JDBC.model.CourseBookEntry;
import CoursePlannerMVC_JDBC.model.Quarter;

@WebServlet("/abc")
public class abc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public abc() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		String uname=(String) request.getSession().getAttribute("uname");
		String t=(String) request.getParameter("t");
		
		List<Timestamp> abc = new ArrayList<Timestamp>();
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs320stu24";
			String username = "cs320stu24";
			String password = "vNEVq!xy";

			c = DriverManager.getConnection(url, username, password);
			
			
			if(t==null){
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("select distinct time_added from course_planner where username='"+uname+"' order by time_added desc;");
				//int i=0;
				System.out.println("rs executed...");
				while (rs.next()) 
				{
					System.out.println("saved at : "+rs.getTimestamp(1));
					abc.add(rs.getTimestamp(1));
					//i++;
				}
			System.out.println("rs done.....");
			getServletContext().setAttribute("abc",abc);
			
			}
			else{
				
				List<Quarter> lista = new ArrayList<Quarter>();
			request.setAttribute("t", t);
				System.out.println("in else...");
				System.out.println("t : "+t);
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("select c.scode,c.stitle,c.spreq,p.quarter_name from courses c inner join course_planner p where c.scode=p.course_code and p.username='"+uname+"' and p.time_added='"+t+"'");
				System.out.println("select rs executed..");
				while(rs.next()){
					List<CourseBookEntry> list_disp = new ArrayList<CourseBookEntry>();
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
					System.out.println(rs.getString(4));
					list_disp.add(new CourseBookEntry(rs.getString(1),rs.getString(2),rs.getString(3)));
					System.out.println("list_disp siz : "+list_disp.size());
					String str1=rs.getString(4);
					System.out.println("str1 : "+str1);
					
					lista.add(new Quarter(rs.getString(4), list_disp));
				}
				List<Quarter> l2 = new ArrayList<Quarter>();
				System.out.println("select rs done..");
		
				boolean flag;
				for (int i=0;i<lista.size();i++) {
					flag=false;
					List<CourseBookEntry> l1 = new ArrayList<CourseBookEntry>();
					String s1 = lista.get(i).getName();
					
						
					while(((i+1)<lista.size()) && ((s1).equals(lista.get(i+1).getName()))){
						flag=true;
						l1.addAll(lista.get(i).getSubjlist());
						l1.addAll(lista.get(i+1).getSubjlist());
						i++;
					}
					if(flag==false)
						l1.addAll(lista.get(i).getSubjlist());
					
					l2.add(new Quarter(s1,l1));
				}
				
				request.setAttribute("l2",l2);
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

		
		request.getRequestDispatcher("/WEB-INF/abc.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post...");
	}

}
