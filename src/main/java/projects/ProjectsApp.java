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
			private Project curProject;
		
// OPERATIONS....
			//@formatter:off
			private List<String> operations = List.of(
					"1) Add a project", 
					"2) List projects",
					"3) Select a project",
					"4) Update current project details",
					"5) Delete a project",
					" **** Press the Enter key to quit ****"					
					);
			// @formatter:on
			
	
// MAIN
			public static void main(String[] args) 
			{
		
				new ProjectsApp().processUserSelections();
				
			}// MAIN
		
			

//METHODS
			
		// SWITCH
		//Process User Selections
					private void processUserSelections() 
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
										case 2:
											listProjects();
											break;
										case 3:
											selectProject();
											break;
										case 4:
											updateProjectDetails();
											break;
										case 5:
											deleteProject();
											break;
										default:
											System.out.println("\n" + selection + " is not a valid selection.  Try again.");
											break;
									}
								}
							catch(Exception e) { System.out.println("\nError: " + e + " Try again."); }
						}
					}
					

		//Get User Selection
					private Integer getUserSelection(String prompt) 
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
					private String getStringInput(String prompt) 
					{	System.out.print("\n" + prompt + ": ");
						String input = scanner.nextLine();
						
						return input.isBlank() ? null : input.trim();
					}

			
		// Get Decimal Input
					private BigDecimal getDecimalInput(String prompt) 
					{	String input = getStringInput(prompt);
						
						if (Objects.isNull(input))
							{ return null; }
						
			
						try { return new BigDecimal(input).setScale(2); }
						catch (NumberFormatException e) { throw new DbException(input + " is not a valid decimal number."); }
					}

					
		// Update Project Details
					private void updateProjectDetails() 
					{	if ( curProject != null )
							{	String     projectName    = getStringInput( "Enter the project name [" + curProject.getProjectName() + "]");
								BigDecimal estimatedHours = getDecimalInput ("Enter the estimated hours [" + curProject.getEstimatedHours() + "]");
								BigDecimal actualHours    = getDecimalInput ("Enter the actual hours [" + curProject.getActualHours() + "]" );
								Integer    difficulty     = getUserSelection("Enter the project difficulty (1-5) [" + curProject.getDifficulty() + "]");
								String     notes          = getStringInput  ("Enter the project notes ]" + curProject.getNotes() + "]");
									
								Project project = new Project();
								
								project.setProjectName   (Objects.isNull(projectName) ? curProject.getProjectName() : projectName );
								project.setEstimatedHours(Objects.isNull(estimatedHours) ? curProject.getEstimatedHours() : estimatedHours);
								project.setActualHours   (Objects.isNull(actualHours) ? curProject.getActualHours() : actualHours);
								project.setDifficulty    (Objects.isNull(difficulty) ? curProject.getDifficulty() : difficulty);
								project.setNotes         (Objects.isNull(notes) ? curProject.getNotes() : notes);
								
								project.setProjectId     ( curProject.getProjectId() );
								
								projectService.modifyProjectDetails(project);
								
								curProject = projectService.fetchProjectById(curProject.getProjectId());
						
						
							}	
						else 
							{	System.out.println("\nPlease select a project.");
								return;									
							}
					}

	
		// Select Project.
					private void selectProject() 
				{	listProjects();
					
				
					Integer projectId = getUserSelection("Enter a project ID to select a project");

					curProject = null;

					curProject = projectService.fetchProjectById(projectId);

					return ;
				}


		// Get a list of Projects.
					private void listProjects() 
					{	List<Project> projects = projectService.fetchAllProjects();
					
						System.out.println("\n\nThis is a Current list of Projects on file: [ Id,  Name ]");
						
						projects.forEach(project -> System.out.println
								("ID: \t" + project.getProjectId() + "\tProject:  " + project.getProjectName()));
					}


		// Create Project
					private void createProject() 
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

	
		// Delete a Project
					private void deleteProject() 
					{	listProjects();
						
					 	int projectId = getUserSelection("Enter the ID of the Project you would like to DELETE or press the ENTER key to return to the Main Menue )");
					 	
						if (projectId == -1)
							{	return;	}
						
						projectService.deleteProject(projectId);
						
						System.out.println("Project ID = " + projectId + " has been DELETED...");
						
						if ( curProject != null )
						{	if ( projectId == curProject.getProjectId())
							{	curProject = null;	}
						}

						return ;
					}

					
		//Print Operations
					private void printOperations() 
					{	System.out.println("\nThese are the available Options.");
						operations.forEach( operation -> System.out.println("   " + operation));
						
						if(Objects.isNull(curProject))
						{	System.out.println("\n**** No project is currently open.");
						}	else 
							{ System.out.println("\nThis project is currently open:" + curProject);}
					}
					
					
		// Exit Menu
					private boolean exitMenu() 
					{	System.out.println("\n\n\t\tExiting the menu.");
						return true;
					}
					

}// END CLASS
