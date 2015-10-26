package midterm.model;

import java.util.Date;

public class TaskManager {
	
	int id;
	String message;
	Date dueDate;
	
	
	
	public TaskManager() {
		super();
		
	}
	public TaskManager(int id,String message, Date dueDate) {
		super();
		this.id=id;
		this.message = message;
		this.dueDate = dueDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
	

}
