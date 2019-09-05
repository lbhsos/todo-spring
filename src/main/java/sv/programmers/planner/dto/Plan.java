package sv.programmers.planner.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//CREATE TABLE plan ( id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, title VARCHAR(255) NOT NULL, priority INT(1) NOT NULL, 
//type VARCHAR(20) DEFAULT 'TODO', regdate DATETIME DEFAULT NOW(), 
//deadline DATETIME NOT NULL, PRIMARY KEY (id) );
public class Plan {
	private int id;
	private String title;
	private int priority;
	private String regdate;
	private String deadline;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
        //Date date = regdate;
       
        this.regdate = regdate.substring(0,10);
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
        //Date date = regdate;
        
        
		this.deadline = deadline.substring(0,10);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Plan [title=" + title + ", priority=" + priority + ", deadline=" + deadline + "]";
	}
	
	
}
