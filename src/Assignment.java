/*
 * Written by Donte Littlejohn
 */

import java.time.*;

public class Assignment {
	private String title;
	private LocalDate dueDate;
	private LocalTime timeDue;
	private boolean completed;
	
	public Assignment()
	{
		this.setTitle("");
		this.setDueDate(LocalDate.now());
		completed = false;
	}
	
	public Assignment(String aTitle, LocalDate aDueDate)
	{
		this.setTitle(aTitle);
		this.setDueDate(aDueDate);
		this.setTimeDue(LocalTime.parse("23:59"));
		completed = false;
	}
	
	public Assignment(String aTitle, LocalDate aDueDate, LocalTime aTimeDue)
	{
		this.setTitle(aTitle);
		this.setDueDate(aDueDate);
		this.setTimeDue(aTimeDue);
		completed = false;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public LocalDate getDueDate()
	{
		return dueDate;
	}
	
	public LocalTime getTimeDue()
	{
		return timeDue;
	}
	
	public boolean isCompleted()
	{
		return completed;
	}
	
	public void setTitle(String aTitle)
	{
		if(aTitle == null || !(aTitle.length() > 0))
			title = "Untitled Document";
		else
			title = aTitle;
	}
	
	public void setDueDate(LocalDate aDueDate)
	{
		if(aDueDate == null || aDueDate.isBefore(LocalDate.now()))
			System.out.println("Invalid Date");
		else
			dueDate = aDueDate;
	}
	
	public void setTimeDue(LocalTime aTimeDue)
	{
		if(aTimeDue == null || aTimeDue.isBefore(timeDue))
			System.out.println("Invalid Time");
		else
			timeDue = aTimeDue;
	}
	
	public void toggleCompleted()
	{
		this.completed = !this.completed;
	}
	
	public String toString()
	{
		return "Title: " + this.title + "Due Date: " + this.dueDate + "Completed: " + this.completed;
	}
	
	public boolean equals(Assignment anAssignment)
	{
		return anAssignment != null &&
				anAssignment.title.equals(this.title) &&
				anAssignment.dueDate.equals(this.dueDate) &&
				anAssignment.timeDue.equals(this.timeDue) &&
				anAssignment.completed == this.completed;
	}
}
