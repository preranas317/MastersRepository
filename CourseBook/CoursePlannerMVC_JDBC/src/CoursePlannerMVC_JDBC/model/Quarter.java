package CoursePlannerMVC_JDBC.model;

import java.util.*;

public class Quarter {

	String name;
	int year;
	List<CourseBookEntry> subjlist = new ArrayList<CourseBookEntry>();
CourseBookEntry c;
	public CourseBookEntry getC() {
	return c;
}

public void setC(CourseBookEntry c) {
	this.c = c;
}

	public Quarter() {
		super();
	}

	public Quarter(String name, int year,List<CourseBookEntry> subjlist) {
		super();
		this.name = name;
		this.year=year;
		this.subjlist = subjlist;
	}
	
	public Quarter(String name,List<CourseBookEntry> subjlist) {
		super();
		this.name = name;
		//this.year=year;
		this.subjlist = subjlist;
	}

	public Quarter(String name,CourseBookEntry c) {
		super();
		this.name = name;
		//this.year=year;
		this.c = c;
	}
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseBookEntry> getSubjlist() {
		return subjlist;
	}

	public void setSubjlist(List<CourseBookEntry> subjlist) {
		this.subjlist = subjlist;
	}

}
