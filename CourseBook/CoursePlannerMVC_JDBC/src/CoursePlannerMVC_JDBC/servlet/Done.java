package CoursePlannerMVC_JDBC.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CoursePlannerMVC_JDBC.model.CourseBookEntry;
import CoursePlannerMVC_JDBC.model.Quarter;

@WebServlet("/Done")
public class Done extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Done() {
		super();

	}

	@SuppressWarnings({ "unchecked", "unused" })
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String v = request.getParameter("v");
	
			if (v.equalsIgnoreCase("done")) {

			getServletContext().setAttribute("session", session);
			System.out.println("session before : " + session);

			session.invalidate();

			System.out.println("session : " + session);
			request.getRequestDispatcher("/WEB-INF/Done.jsp").forward(request,
					response);
			return;
		}

		else if (v.equalsIgnoreCase("save")) {

			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			String uname = (String) request.getSession().getAttribute("uname");
			List<Quarter> finalquarterList = (List<Quarter>) session
					.getAttribute("finalquarterList");

			List<CourseBookEntry> list1 = new ArrayList<CourseBookEntry>();
			System.out.println("uname : " + uname);
			Connection c = null;
			try {
				String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs320stu24";
				String username = "cs320stu24";
				String password = "vNEVq!xy";

				c = DriverManager.getConnection(url, username, password);
				for (Quarter q : finalquarterList) {
					System.out.println("Data to add in courseplanner table");
					System.out.println("q.quartername " + q.getName());
					String qname = q.getName() + q.getYear();
					System.out.println("qname : " + qname);
					System.out.println("time : " + date);

					for (int i = 0; i < q.getSubjlist().size(); i++) {
						System.out.println("Data to add in subjlist table");
						System.out.println("q.getsubjlist.code : "
								+ q.getSubjlist().get(i).scode);
						try {
							PreparedStatement pstmt = c
									.prepareStatement("insert into course_planner(username,quarter_name,course_code,time_added) values(?,?,?,?)");
							pstmt.setString(1, uname);
							pstmt.setString(2, qname);
							pstmt.setString(3, q.getSubjlist().get(i).scode);
							pstmt.setTimestamp(4, date);
							pstmt.executeUpdate();
							System.out.println("data added");
						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}

			finally {
				try {
					if (c != null)
						c.close();
				} catch (SQLException e) {
					throw new ServletException(e);
				}
			}

			response.sendRedirect("CourseBook_JDBC");

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
