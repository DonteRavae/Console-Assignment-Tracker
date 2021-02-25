/*
 * Written by Donte Littlejohn
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class AssignmentManager {
	private ArrayList<Assignment> assignments;
	public static final int FILE_COL_AMT = 3;
	public static final String DELIM = "\t";
	
	public AssignmentManager()
	{
		assignments = new ArrayList<Assignment>();
	}
	
	
	public Assignment addAssignment(Assignment anAssignment)
	{
		if(anAssignment == null)
			System.out.println("Invalid Assignment.");
		else
		{
			assignments.add(anAssignment);
			this.sortAssignmentsByDueDate();
			return anAssignment;
		}
		
		return null;
	}
	
	public void printAssignments()
	{
		System.out.println("TITLE\tDUE DATE\tTIME DUE\tCOMPLETED\n");
		for(Assignment a : assignments)
			System.out.println(a.getTitle() + DELIM + a.getDueDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + DELIM + a.getTimeDue() + DELIM + DELIM + a.isCompleted());
	}
	
	public void removeAssignment(String aTitle)
	{
		if(aTitle == null)
			System.out.println("Invalid Title");
		else
		{
			for(int i = 0; i < assignments.size(); i++)
			{
				if(assignments.get(i).getTitle().equalsIgnoreCase(aTitle))
				{
					assignments.remove(i);
					break;
				}
			}
		}
	}
	
	public void sortAssignmentsByDueDate()
	{		
		//Selection Sort
		for(int i = 0; i < assignments.size(); i++)
		{
			Assignment smallestIndex = assignments.get(i);
			for(int j = i + 1; j < assignments.size(); j++)
			{
				Assignment current = assignments.get(j);
				if(current.getDueDate().isBefore(smallestIndex.getDueDate()))
					smallestIndex = current;
			}
			
			if(!smallestIndex.equals(assignments.get(i)))
			{
				Assignment temp = assignments.get(i);
				assignments.set(i, smallestIndex);
				assignments.set(assignments.indexOf(smallestIndex), temp);
			}
		}
	}
	
	public void writeToFile(String aFileName)
	{
		try
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream(aFileName));

			for(Assignment a : assignments)
			{
				if(a == null)
					break;
				
				//Print Body
				pw.println(a.getTitle() + DELIM + a.getDueDate() + DELIM + a.getTimeDue() + DELIM + a.isCompleted());
			}
			pw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void readFromFile(String aFileName)
	{
		try
		{
			Scanner fs = new Scanner(new File(aFileName));
			
			while(fs.hasNextLine())
			{
				String fileLine = fs.nextLine();
				String[] lineSplits = fileLine.split(DELIM);
				
				String title = lineSplits[0];
				String dueDate = lineSplits[1];
				String timeDue = lineSplits[2];
				String completed = lineSplits[3];
				
				Assignment a = new Assignment(title, LocalDate.parse(dueDate), LocalTime.parse(timeDue));
				if(a.isCompleted() != Boolean.parseBoolean(completed))
					a.toggleCompleted();
				
				this.addAssignment(a);
			}
			
			fs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void completeAssignment(String aTitle)
	{
		if(aTitle == null)
			System.out.println("Invalid Input");
		else
			for(int i = 0; i < assignments.size(); i++)
			{
				var current = assignments.get(i);
				if(current.getTitle().equals(aTitle))
					current.toggleCompleted();
			}
	}
}
