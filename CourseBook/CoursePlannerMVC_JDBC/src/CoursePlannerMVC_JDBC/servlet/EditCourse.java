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

@WebServlet(urlPatterns = "/EditCourse", loadOnStartup = 1)
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditCourse() {
		super();
	}

	private CourseBookEntry getEntry(int id) {
		@SuppressWarnings("unchecked")
		List<CourseBookEntry> bookentry = (List<CourseBookEntry>) getServletContext()
				.getAttribute("bookentry");

		for (CourseBookEntry entry : bookentry)
			if (entry.getId() == id)
				return entry;

		return null;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection c = null;
		List<CourseBookEntry> bookentry = new ArrayList<CourseBookEntry>();
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs320stu24";
			String username = "cs320stu24";
			String password = "vNEVq!xy";

			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		try {

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from courses");
			while (rs.next()) {
				CourseBookEntry cb = new CourseBookEntry(rs.getInt("id"),
						rs.getString("scode"), rs.getString("stitle"),
						rs.getString("spreq"));
				bookentry.add(cb);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		getServletContext().setAttribute("bookentry", bookentry);

		Integer id = Integer.valueOf(request.getParameter("id"));
		CourseBookEntry entry = getEntry(id);
		request.setAttribute("entry", entry);

		request.getRequestDispatcher("/WEB-INF/EditCourse.jsp").forward(
				request, response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sql;
		Connection c = null;
		try {
			String url = "database URL";
			String username = "username";
			String password = "password";
			sql = "update courses set scode=?,stitle=?,spreq=?";

			c = DriverManager.getConnection(url, username, password);

			// JDBC over
			Integer id = Integer.parseInt(request.getParameter("id"));
			String code = request.getParameter("code");
			String title = request.getParameter("title");
			System.out.println("id" + id);
			List<CourseBookEntry> bookentry = (List<CourseBookEntry>) getServletContext()
					.getAttribute("bookentry");
/*
			bookentry.get(id - 1).setScode(code);
			bookentry.get(id - 1).setSname(title);
*/
			String[] preq = request.getParameterValues("preq");
			String reqnme;
			if (preq != null) {
				reqnme = preq[0];
				for (int i = 1; i < preq.length; i++) {
					reqnme = reqnme + " " + preq[i];
				}
				System.out.println(reqnme);
			} else
				reqnme = "";

			// id=id-1;

			sql = "update courses set scode=?,stitle=?,spreq=? where id=" + id;
			PreparedStatement pstmt;

			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, title);
			pstmt.setString(3, reqnme);

			pstmt.executeUpdate();

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from courses where id="
					+ id);
			if (rs.next()) {
				CourseBookEntry entry = new CourseBookEntry(rs.getInt("id"),
						rs.getString("scode"), rs.getString("stitle"),
						rs.getString("spreq"));
				System.out.println("entry : " + entry);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
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
