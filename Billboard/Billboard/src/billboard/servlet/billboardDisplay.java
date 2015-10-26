package billboard.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import billboard.model.Billboard;

@WebServlet("/billboardDisplay")
public class billboardDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public billboardDisplay() {
		super();

	}
	Integer id=0;
	
	
	private Billboard getEntry( int id )
    {
        List<Billboard> billboardList = (List<Billboard>) getServletContext().getAttribute(
            "billboardList" );

        for( Billboard entry : billboardList )
            if( entry.getRank()==id ) return entry;

        return null;
    }
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		List<Billboard> billboardList = new ArrayList<Billboard>();
		
		billboardList.add(new Billboard(1,"Dark Horse","Katy Perry Featuring Juicy J"));
		billboardList.add(new Billboard(2,"Drunk In Love","Beyonce Featuring Jay Z"));
		billboardList.add(new Billboard(3,"Timber","Pitbull Featuring Ke$ha"));
		billboardList.add(new Billboard(4,"Talk Dirty","Jason Derulo Featuring 2 Chainz"));
		billboardList.add(new Billboard(5,"Counting Stars","OneRepublic"));
		
		getServletContext().setAttribute("billboardList", billboardList);
	}
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Billboard> billboardList = (List<Billboard>) getServletContext().getAttribute(
	            "billboardList" );
		
		String str=request.getParameter("index");
		String str1=request.getParameter("nme");
		if(str!=null){
			id=Integer.parseInt(str);
		System.out.println("id: "+id);}
		else{
			System.out.println("id is null");
			request.getRequestDispatcher("/WEB-INF/billboardDisplay.jsp").forward(request, response);
			return;
			}
		if(str1!=null) {
		switch (str1) {
			case "up": System.out.println("in up");
				if(id!=1){
						Billboard entry1=getEntry(id);
						Billboard entry2=getEntry(id-1);
						
						entry1.setRank(id-1);
						entry2.setRank(id);
						Collections.swap(billboardList, id-2, id-1);
						System.out.println("Swapped");}
				
			
			break;
			
			case "down": System.out.println("in down");
			if(id!=billboardList.size()){
				Billboard entry11=getEntry(id);
				Billboard entry12=getEntry(id+1);
			//Collections.swap(billboardList, id-1, id);
			System.out.println(entry11);
			System.out.println(entry12);
				entry11.setRank(id+1);
				entry12.setRank(id);
				Collections.swap(billboardList, id-1, id);
				System.out.println("Swapped");}
			break;

		default:
			break;
		}
		}
		request.getRequestDispatcher("/WEB-INF/billboardDisplay.jsp").forward(request, response);	
	}

	

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post...");
		List<Billboard> billboardList=(List<Billboard>) getServletContext().getAttribute("billboardList");
		
		int size=billboardList.size();
		System.out.println(size);
		
		String song=request.getParameter("song");
		String artist=request.getParameter("artist");
		
		billboardList.add(new Billboard(size+1,song,artist));
		getServletContext().setAttribute("billboardList", billboardList);
		
		response.sendRedirect("billboardDisplay");
	}

}
