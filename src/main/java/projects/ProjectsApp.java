package projects;

//import java.sql.Connection;
		import java.math.BigDecimal;
		import java.util.List;
		import java.util.Objects;
		import java.util.Scanner;
		import projects.entity.Project;
		import projects.exception.DbException;
		import projects.service.ProjectService;

// CLASS
		public class ProjectsApp 
		{
			private Scanner scanner = new Scanner(System.in);
			private ProjectService projectService = new ProjectService();
			
			//@formatter:off
			private List<String> operations = List.of("1) Add a project");
			// @formatter:on
			
	
// MAIN
			public static void main(String[] args) 
			{
		
				new ProjectsApp().processUserSelections();
				
			}// MAIN
		
			
			
//METHODS
			//Process User Selections
					private void       processUserSelections() 
					{	boolean done = false;
						while (!done) 
						{	try 
							{	int selection = getUserSelection("Enter a menu selection");	
							
								switch (selection) 
									{	case -1: 
											done = exitMenu();
											break;										
										case 1:
											createProject();
											break;
										default:
											System.out.println("\n" + selection + " is not a valid selection.  Try again.");
											break;
									}
								}
							catch(Exception e) { System.out.println("\nError: " + e + " Try again."); }
						}
					}


			// Create Project
					private void       createProject() 
					{	String     projectName    = getStringInput  ("Enter the project name");
						BigDecimal estimatedHours = getDecimalInput ("Enter the estimated hours");
						BigDecimal actualHours    = getDecimalInput ("Enter the actual hours");
						Integer    difficulty     = getUserSelection("Enter the project difficulty (1-5)");
						String     notes          = getStringInput  ("Enter the project notes");
						
						Project project = new Project();
						
						project.setProjectName   (projectName);
						project.setEstimatedHours(estimatedHours);
						project.setActualHours   (actualHours);
						project.setDifficulty    (difficulty);
						project.setNotes         (notes);
						
						Project dbProject = projectService.addProject(project);
						System.out.println("You have successfully created project: " + dbProject);							
					}

					
			// Get Decimal Input
					private BigDecimal getDecimalInput(String prompt) 
					{	String input = getStringInput(prompt);
						
						if (Objects.isNull(input))
							{ return null; }
						
			
						try { return new BigDecimal(input).setScale(2); }
						catch (NumberFormatException e) { throw new DbException(input + " is not a valid decimal number."); }
					}


			//Get User Selection
					private Integer    getUserSelection(String prompt) 
					{	if ( prompt.equals("Enter a menu selection"))
							{	printOperations();
							}
						String input = getStringInput(prompt);
						
						if(Objects.isNull(input))
							{	return -1; }
						try {	return Integer.valueOf(input); 	}
						catch(NumberFormatException e)	{ throw new DbException(input + " is not a valid number."); }
					}

			// Get Integer Input
					/*
					 * null value on return type INTEGER flag!!!  
					 * had to revise code to circumvent error.
					 * Deleted getIntegerInput method.
					 * Added code to catch difference in getUserSelection method
					 */

			// Get String Input
					private String     getStringInput(String prompt) 
					{	System.out.print(prompt + ": ");
						String input = scanner.nextLine();
						
						return input.isBlank() ? null : input.trim();
					}


			//Print Operations
					private void       printOperations() 
					{	System.out.println("\nThese are the available selections.  Press the Enter key to quit:");
						operations.forEach( operation -> System.out.println("   " + operation));
					}
					
			// Exit Menu
					private boolean    exitMenu() 
					{	System.out.println("Exiting the menu.");
						return true;
					}
					

}// END CLASS
