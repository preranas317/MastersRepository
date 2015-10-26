package CoursePlannerMVC_JDBC.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CoursePlannerMVC_JDBC.model.CourseBookEntry;
import CoursePlannerMVC_JDBC.model.Quarter;

@WebServlet("/CoursePlanner")
public class CoursePlanner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoursePlanner() {
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

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("init...");
		
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

	private CourseBookEntry getEntry(String preq) {
		@SuppressWarnings("unchecked")
		List<CourseBookEntry> bookentry = (List<CourseBookEntry>) getServletContext()
				.getAttribute("bookentry");

		for (CourseBookEntry entry1 : bookentry) {

			String tmpstr[] = entry1.getSpreq().split(" ");
			for (int j = 0; j < tmpstr.length; j++)
				if (tmpstr[j].equals(preq))
					return entry1;
		}
		return null;
	}

	// doGet Starting....
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uname = (String) request.getAttribute("uname");
		request.setAttribute("uname", uname);
		
		List<CourseBookEntry> bookentry = new ArrayList<CourseBookEntry>();
		Connection c = null;
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
			
		
		Calendar cal = Calendar.getInstance();
	//	int week = cal.get(Calendar.WEEK_OF_YEAR);
		HttpSession session;
		//int weekYear = cal.getWeekYear();
		List<CourseBookEntry> tmplist = new ArrayList<CourseBookEntry>();
		List<Quarter> finalquarterList = new ArrayList<Quarter>();
		List<CourseBookEntry> chckList = new ArrayList<CourseBookEntry>();
		String button = request.getParameter("button");

		// session.setAttribute("flag", true);
		session = request.getSession();
		getServletContext().setAttribute("session", session);
		System.out.println("Session set.." + session);
		System.out.println("button is null..called for first time");
		session.setAttribute("bookentry", bookentry);
		
		session.setAttribute("cal", cal);
		session.setAttribute("tmplist", tmplist);
		session.setAttribute("finalquarterList", finalquarterList);
		session.setAttribute("chckList", chckList);

		request.getRequestDispatcher("/WEB-INF/CoursePlanner.jsp").forward(
				request, response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post");

		HttpSession session = (HttpSession) getServletContext().getAttribute(
				"session");
		Calendar cal = (Calendar) session.getAttribute("cal");
		int week ;
		int weekYear = cal.getWeekYear();;
		List<CourseBookEntry> tmplist = (List<CourseBookEntry>) session
				.getAttribute("tmplist");
		List<Quarter> finalquarterList = (List<Quarter>) session
				.getAttribute("finalquarterList");
		List<CourseBookEntry> chckList = (List<CourseBookEntry>) session
				.getAttribute("chckList");

		String quartername = null;
		System.out.println("Session :" + session);
		
		Connection c = null;
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
		
		
		List<CourseBookEntry> bookentry = (List<CourseBookEntry>) session
				.getAttribute("bookentry");

		List<CourseBookEntry> tmpquarterList = new ArrayList<CourseBookEntry>();
		String button = request.getParameter("button");

		request.setAttribute("finish", button);
		boolean flag = false;
		// session.setAttribute("flag", false);
		week = cal.get(Calendar.WEEK_OF_YEAR);
		if (week >= 1 && week <= 12)
			quartername = "Winter";
		// Spring quarter
		else if (week >= 13 && week <= 24)
			quartername = "Spring";

		// summer quarter
		else if (week >= 25 && week <= 37)
			quartername = "Summer";

		// fall quarter
		else
			quartername = "Fall";
		int j;
		List<CourseBookEntry> nopreq = new ArrayList<CourseBookEntry>();
		for (CourseBookEntry nopre : bookentry) {
			if (nopre.getSpreq().equalsIgnoreCase("")) {
				System.out.println("data added in nopreq");
				nopreq.add(nopre);

			}
		}
		System.out.println("Size of nopreq list : " + nopreq.size());

		if (button.equalsIgnoreCase("next")) {

			System.out.println("in next");

			String str[] = request.getParameterValues("chkcourse");
			if (str != null) {
				if (chckList.size() < bookentry.size()) {
					tmplist.clear();
					for (int i = 0; i < str.length; i++) {
						j = Integer.parseInt(str[i]);
						String code = getEntry(j).getScode();
						CourseBookEntry quarterentry = getEntry(j);
						System.out.println("quarterentry" + quarterentry);

						tmpquarterList.add(quarterentry);
						chckList.add(quarterentry);
						CourseBookEntry entry = getEntry(code);
						// System.out.println("entry : " +
						// entry.toString());

						for (int k = 0; k < str.length; k++) {
							flag = false;
							int m = Integer.parseInt(str[k]);
							String code1 = getEntry(m).getScode();
							// System.out.println("code1 : " + code1);
							if (entry != null)
								if (entry.getScode().equalsIgnoreCase(code1)) {
									System.out
											.println("entry.getScode().equalsIgnoreCase(code1)");
									flag = true;
									break;
								}
						}

						if (flag == false) {
							System.out
									.println("nt already selected...and ready to add in list");
							if (tmplist.size() != 0) {
								for (int i1 = 0; i1 < tmplist.size(); i1++) {
									String s1 = tmplist.get(i1).getScode();
									System.out.println("s1" + s1);
									if(entry!=null)
									if (!(entry.getScode().equalsIgnoreCase(s1))) {
										System.out.println("in if..");
										if (chckList.size() != 0) {

											for (int m = 0; m < chckList.size(); m++) {

												if (!(entry.getScode()
														.equalsIgnoreCase(chckList
																.get(m)
																.getScode()))) {
													tmplist.add(entry);
													break;
												}
											}
										} else
											tmplist.add(entry);

										break;
									}
								}
							}

							else {

								if (chckList.size() != 0) {

									for (int m = 0; m < chckList.size(); m++) {
										if (entry != null)
											if (!(entry.getScode()
													.equalsIgnoreCase(chckList
															.get(m).getScode()))) {
												tmplist.add(entry);
												break;
											}
									}

								} else if (entry != null)
									tmplist.add(entry);
							}
						}
						
					}
				
					//Collections.sort();
					boolean flagtemp = false;
					if (tmplist != null)

						for (CourseBookEntry c1 : bookentry) {
							System.out.println("c1  : "+c1);
							flagtemp = false;
							for (CourseBookEntry c2 : tmplist) {
								System.out.println("c2  : "+c2);
								if (((c1.getScode().equalsIgnoreCase(c2
										.getScode())))) {
									flagtemp = true;
									break;
								}
							}
							boolean flag1 = false;
							if (flagtemp == false) {
								if (chckList.size() != 0) {
									for (int m = 0; m < chckList.size(); m++) {
										flag1 = false;
										if ((c1.getScode()
												.equalsIgnoreCase(chckList.get(
														m).getScode()))) {

											flag1 = true;
											break;
										}
									}
									if (flag1 == false)
										tmplist.add(c1);
								} else
									tmplist.add(c1);
							}

						}

					// chckList.addAll(tmpquarterList);
					if (tmplist.isEmpty()) {
						System.out.println("no rcords to display");
						request.setAttribute("tmpliststatus", null);
						System.out.println("tmpliststatus");

					} else{

						request.setAttribute("tmpliststatus", "notnull");
						
					}
					
				}
				finalquarterList.add(new Quarter(quartername, weekYear,
						tmpquarterList));

			} else {
				
				
				if (tmplist.isEmpty()) {
					System.out.println("no rcords to display");
					request.setAttribute("tmpliststatus", null);
					System.out.println("tmpliststatus");

				} else{
					tmpquarterList.addAll(tmplist);
					System.out.println("tmplist.size " + tmplist.size());
					request.setAttribute("tmpliststatus", "notnull");
				}
				System.out.println("no selection...");
				
			}
		
			
			if(!tmplist.isEmpty()){
				
				for(int m=0;m<tmplist.size();m++){
					CourseBookEntry chkentry =tmplist.get(m);
					flag=false;
					System.out.println("chkentry : "+chkentry);
					if(chkentry.getSpreq().equalsIgnoreCase("")){
						System.out.println("entry has no preq : "+chkentry);
					}
					else{
						System.out.println("in else");
						String[] abc=chkentry.getSpreq().split(" ");
						System.out.println("abc array size : "+abc.length);
						for(int k=0;k<tmplist.size();k++){
								for(int i=0;i<abc.length;i++){
										if(abc[i].equalsIgnoreCase(tmplist.get(k).getScode())){
												System.out.println("match");
												flag=true;
												break;
										}
								}
								
						}
						if(flag==true){
							System.out.println("tmplist entry is removed : "+chkentry );
						tmplist.remove(chkentry);
						/*if(m<tmplist.size())
							chkentry=tmplist.get(m+1);*/
							
						}
						System.out.println("at else");
						
					}
				}
				
				
			}
		} else if (button.equalsIgnoreCase("finish")) {
			String str[] = request.getParameterValues("chkcourse");

			if (str != null) {
				tmplist.clear();
				for (int i = 0; i < str.length; i++) {
					j = Integer.parseInt(str[i]);
					String code = getEntry(j).getScode();
					CourseBookEntry quarterentry = getEntry(j);
					System.out.println("quarterentry" + quarterentry);

					tmpquarterList.add(quarterentry);
					chckList.add(quarterentry);
					CourseBookEntry entry = getEntry(code);
					// System.out.println("entry : " + entry.toString());

					for (int k = 0; k < str.length; k++) {
						flag = false;
						int m = Integer.parseInt(str[k]);
						String code1 = getEntry(m).getScode();
						System.out.println("code1 : " + code1);
						if (entry != null)
							if (entry.getScode().equalsIgnoreCase(code1)) {
								System.out
										.println("entry.getScode().equalsIgnoreCase(code1)");
								flag = true;
								break;
							}
					}

					if (flag == false) {
						System.out
								.println("nt already selected...and ready to add in list");
						if (tmplist.size() != 0) {
							for (int i1 = 0; i1 < tmplist.size(); i1++) {
								String s1 = tmplist.get(i1).getScode();
								System.out.println("s1" + s1);
								if (entry != null)
									if (!(entry.getScode().equalsIgnoreCase(s1))) {
										System.out.println("in if..");
										if (chckList.size() != 0) {

											for (int m = 0; m < chckList.size(); m++) {

												if (!(entry.getScode()
														.equalsIgnoreCase(chckList
																.get(m)
																.getScode()))) {
													tmplist.add(entry);
													break;
												}
											}
										} else
											tmplist.add(entry);

										break;
									}
							}
						}

						else {

							if (chckList.size() != 0) {

								for (int m = 0; m < chckList.size(); m++) {
								if(entry!=null)
									if (!(entry.getScode()
											.equalsIgnoreCase(chckList.get(m)
													.getScode()))) {
										tmplist.add(entry);
										break;
									}
								}

							} else
								tmplist.add(entry);
						}
					}

				}
				boolean flagtemp = false;
				for (CourseBookEntry c1 : nopreq) {
					flagtemp = false;
					for (CourseBookEntry c2 : tmplist) {

						if ((!(c2.getScode().equalsIgnoreCase(c1.getScode())))) {
							flagtemp = true;
							break;
						}
					}
					boolean flag1 = false;
					if (flagtemp == true) {
						if (chckList.size() != 0) {
							for (int m = 0; m < chckList.size(); m++) {
								flag1 = false;
								if ((c1.getScode().equalsIgnoreCase(chckList
										.get(m).getScode()))) {

									flag1 = true;
									break;
								}
							}
							if (flag1 == false)
								tmplist.add(c1);
						} else
							tmplist.add(c1);
					}

				}

				finalquarterList.add(new Quarter(quartername, weekYear,
						tmpquarterList));

			} else {

				System.out.println("in finish..no selection...");
				System.out.println("tmplist.size " + tmplist.size());

			}

			System.out.println("in finish");
			request.setAttribute("finish", button);
			
			
if(!tmplist.isEmpty()){
				
				for(int m=0;m<tmplist.size();m++){
					CourseBookEntry chkentry =tmplist.get(m);
					flag=false;
					System.out.println("chkentry : "+chkentry);
					if(chkentry.getSpreq().equalsIgnoreCase("")){
						System.out.println("entry has no preq : "+chkentry);
					}
					else{
						System.out.println("in else");
						String[] abc=chkentry.getSpreq().split(" ");
						System.out.println("abc array size : "+abc.length);
						for(int k=0;k<tmplist.size();k++){
								for(int i=0;i<abc.length;i++){
										if(abc[i].equalsIgnoreCase(tmplist.get(k).getScode())){
												System.out.println("match");
												flag=true;
												break;
										}
								}
								
						}
						if(flag==true){
							System.out.println("tmplist entry is removed : "+chkentry );
						tmplist.remove(chkentry);
						/*if(m<tmplist.size())
							chkentry=tmplist.get(m+1);*/
							
						}
						System.out.println("at else");
						
					}
				}
				
				
			}

		}
		cal.add(Calendar.DATE, 84);
		week = cal.get(Calendar.WEEK_OF_YEAR);
		System.out.println("Current week" + week);
		if (week >= 1 && week <= 12)
			quartername = "Winter";
		// Spring quarter
		else if (week >= 13 && week <= 24)
			quartername = "Spring";

		// summer quarter
		else if (week >= 25 && week <= 37)
			quartername = "Summer";

		// fall quarter
		else
			quartername = "Fall";

		
		
		
		session.setAttribute("tmplist", tmplist);
		session.setAttribute("finalquarterList", finalquarterList);
		session.setAttribute("quartername", quartername);

		// Quarter Calculation
		weekYear = cal.getWeekYear();
		session.setAttribute("weekYear", weekYear);
		session.setAttribute("finalquarterList", finalquarterList);

		request.getRequestDispatcher("/WEB-INF/CoursePlanner.jsp").forward(
				request, response);
	}

}
