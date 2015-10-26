package midterm.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import midterm.model.TaskManager;

@WebServlet("/TaskManagerDisplay")
public class TaskManagerDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TaskManagerDisplay() {
		super();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<TaskManager> taskList = new ArrayList<TaskManager>();
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		try {
			taskList.add(new TaskManager(1,"Buy grocery",format1.parse("02/22/2014")));
			taskList.add(new TaskManager(2,"watch the lego movie",format1.parse("02/16/2014")));
			taskList.add(new TaskManager(3,"Meeting with Dr. Pamula",format1.parse("02/20/2014")));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
		getServletContext().setAttribute("taskList", taskList);
	}
	
	
	private TaskManager getTask(int id) {
		@SuppressWarnings( "unchecked" )
		List<TaskManager> taskList= (List<TaskManager>) getServletContext().getAttribute("taskList");
		
		for (TaskManager taskentry : taskList)
			if(taskentry.getId()==id)
					return taskentry;
		
		return null;
	}
	

	List<TaskManager> completedtaskList = new ArrayList<TaskManager>();
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		@SuppressWarnings( "unchecked" )
		List<TaskManager> taskList= (List<TaskManager>) getServletContext().getAttribute("taskList");
		
		//Todays Date
		
		Calendar cal = Calendar.getInstance();
		String calstr=dateFormat.format(cal.getTime());
		Date todaydate=cal.getTime();
		System.out.println("dateFormat.format(cal.getTime())"+dateFormat.format(cal.getTime()));
		request.setAttribute("todaydate", todaydate);
		request.setAttribute("calstr", calstr);
		
		//Tommarows date
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE, 1);
		Date tmrwdate=cal1.getTime();
		String tmrwDate=dateFormat.format(cal1.getTime());  
		System.out.println("tmrwDate date"+tmrwDate);
		getServletContext().setAttribute("tmrwDate", tmrwDate);
		request.setAttribute("tmrwdate", tmrwdate);
		
		//Yesterdays Date
		Calendar cal2=Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterdayDate=dateFormat.format(cal.getTime());  
		Date yesdate=cal.getTime();
		System.out.println("yesterday date"+yesterdayDate);
		getServletContext().setAttribute("yesterdayDate", yesterdayDate);
		request.setAttribute("yesdate", yesdate);
		String str1=request.getParameter("name");
		String idstr=request.getParameter("id");
		
		if(str1!=null) {
			switch (str1) {
				case "completed": System.out.println("in completed");
						int id=Integer.parseInt(idstr);
						TaskManager task1=getTask(id);
						completedtaskList.add(task1);
						System.out.println(completedtaskList.size());
						
				
				break;
				
				case "current": System.out.println("in current");
				 id=Integer.parseInt(idstr);
				 
				break;
				
				case "remove": System.out.println("in remove");
				 id=Integer.parseInt(idstr);
							taskList.remove(id-1);
							getServletContext().setAttribute("taskList", taskList);
				break;
				
				case "completedlink": System.out.println("in completedlink");
				request.setAttribute("completedtaskList", completedtaskList);
									request.setAttribute("completedlink", true);
												 					
				 
				break;
				
				case "currentlink": System.out.println("in completedlink");
				
									request.setAttribute("completedlink", false);
												 					
				 
				break;
				
			default:
				break;
			}
		
		}
		
		request.getRequestDispatcher("/WEB-INF/TaskManagerDisplay.jsp").forward(request, response);
		}



	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings( "unchecked" )
		List<TaskManager> taskList= (List<TaskManager>) getServletContext().getAttribute("taskList");
System.out.println("in post");
	
	String msg=request.getParameter("message");
	String dueDate=request.getParameter("dueDate");
	
	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	try {
		Date d1=format1.parse(dueDate);
		
		
		taskList.add(new TaskManager((taskList.size()+1), msg, d1));
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	
	getServletContext().setAttribute("taskList",taskList );
	response.sendRedirect("TaskManagerDisplay");

	}

}
