/*
 * Written by Donte Littlejohn
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class AssignmentManagerFE 
{
	private static Scanner keyboard = new Scanner(System.in);
	private static AssignmentManager assignmentManager = new AssignmentManager();
	private static final String BRAND = "Assignment Manager";
	private static final String DEF_DB_FILE = "assignments.txt"; 
	
	public static void main(String[] args) {
		//Greet User
		printGreeting();
		boolean quit = false;

		while(!quit)
		{
			printChoices();
			int choice = keyboard.nextInt();
			keyboard.nextLine();
			System.out.println();

			switch(choice)
			{
				case 1:
					addAssignment();
					break;
				case 2:
					removeAssignment();
					break;
				case 3:
					markAssignmentComplete();
				case 4:
					printAllAssignments();
					break;
				case 5:
					saveToFile();
					break;
				case 6:
					readFromFile();
					break;
				case 0:
					quit = true;
					break;
				default:
					System.out.println("Invalid Input");
			}
		}
		keyboard.close();
		System.out.println("Goodbye!");
		System.exit(0);
	}

	public static void printGreeting()
	{
		LocalDate today = LocalDate.now();
		String sToday = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
		System.out.println(BRAND.toUpperCase() + "\t\t\t\t\t" + sToday + "\n");
	}

	public static void printChoices()
	{
		System.out.println("- Enter 1 to ADD a new assignment.\n"
			+ "- Enter 2 to REMOVE an assignment.\n"
			+ "- Enter 3 to COMPLETE an assignment.\n"
			+ "- Enter 4 to PRINT all assignments.\n"
			+ "- Enter 5 to SAVE assignments.\n"
			+ "- Enter 6 to LOAD assignments.\n"
			+ "- Enter 0 to EXIT the program.\n"
		);
	}

	public static void addAssignment()
	{
		String sToday = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		//Get information from user
		System.out.print("Title: ");
		String title = keyboard.nextLine();
		System.out.print("Due Date: (" + sToday +") ");
		String sDueDate = keyboard.nextLine();
		if(!(sDueDate.length() > 0))
			sDueDate = sToday;
		LocalDate dueDate = LocalDate.parse(sDueDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")); //Switch user input to LocalDate object

		//Create Assignment object and add to assignmentManager array
		Assignment toBeAdded = new Assignment(title, dueDate);
		assignmentManager.addAssignment(toBeAdded);

		//Print assignment for testing/confirmation
		System.out.println(toBeAdded);
		System.out.println();
	}

	public static void removeAssignment()
	{
		System.out.print("Enter the title of the assignment you would like to remove: ");
		String title = keyboard.nextLine();
		assignmentManager.removeAssignment(title);
		System.out.println();
	}

	public static void printAllAssignments()
	{
		assignmentManager.printAssignments();
		System.out.println();
	}

	public static void saveToFile()
	{
		System.out.print("Enter file save location: ("+DEF_DB_FILE+") ");
		String fileName = keyboard.nextLine();
		if(!(fileName.length() > 0))
		{
			assignmentManager.writeToFile(DEF_DB_FILE);
			System.out.println();
		}
		else
		{
			assignmentManager.writeToFile(fileName);
			System.out.println();
		}
	}

	public static void readFromFile()
	{
		System.out.print("Enter file load location: ("+DEF_DB_FILE+") ");
		String fileName = keyboard.nextLine();
		System.out.println();
		if(!(fileName.length() > 0))
		{
			assignmentManager.readFromFile(DEF_DB_FILE);
			System.out.println();
		}
		else
		{
			assignmentManager.readFromFile(fileName);
			System.out.println();
		}
	}

	public static void markAssignmentComplete()
	{
		System.out.print("Enter title of assignment you would like to mark completed: ");
		String title = keyboard.nextLine();
		assignmentManager.completeAssignment(title);
		System.out.println();
	}
}

