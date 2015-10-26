package CoursePlannerMVC_JDBC.model;

public class UserEntry {
	private String uname;
	private String pswd;
	private String fname;
	private String lname;
	
	public UserEntry() {
		super();
	}

	public UserEntry(String uname, String pswd, String fname, String lname) {
		super();
		this.uname = uname;
		this.pswd = pswd;
		this.fname = fname;
		this.lname = lname;
	}

	public UserEntry(String uname, String pswd) {
		super();
		this.uname = uname;
		this.pswd = pswd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
	
	
	
	

}
