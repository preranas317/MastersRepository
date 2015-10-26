package CoursePlannerMVC_JDBC.model;

import java.util.ArrayList;
import java.util.List;

public class CourseBookEntry {
	int id = 0;

	public String scode;

	public String sname;

	public String spreq;
	List<CourseBookEntry> bookentry = new ArrayList<CourseBookEntry>();
	public CourseBookEntry() {
		
		List<UserEntry> list = new ArrayList<UserEntry>();
	}

	public CourseBookEntry(int id, String scode, String sname) {

		this.id = id;
		this.scode = scode;
		this.sname = sname;

	}

	public CourseBookEntry(int id, String scode, String sname, String spreq) {
		this.id = id;
		this.scode = scode;
		this.sname = sname;
		this.spreq = spreq;

	}
	public CourseBookEntry(String scode, String sname, String spreq) {
		//this.id = id;
		this.scode = scode;
		this.sname = sname;
		this.spreq = spreq;

	}
	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSpreq() {
		return spreq;
	}

	public void setSpreq(String spreq) {
		this.spreq = spreq;
	}

	@Override
	public String toString() {
		return "CourseBookEntry [id=" + id + ", scode=" + scode + ", sname="
				+ sname + ", spreq=" + spreq + "]";
	}

	public int getId() {
		
		return this.id;
	}

	

}
