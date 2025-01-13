package projects.service;


//Imports
		import java.util.List;
		import java.util.NoSuchElementException;
		import java.util.Optional;
		
		import projects.dao.ProjectDao;
		import projects.entity.Project;

		@SuppressWarnings("unused")

//Class
		public class ProjectService 
		{
		
			private ProjectDao projectDao = new ProjectDao();
			
			
//Methods		
		// Add a project.
				public Project addProject(Project project) 
				{	return projectDao.insertProject(project);
				}
	
		// Fetch ALL projects.
				public List<Project> fetchAllProjects() 
				{	return projectDao.fetchAllProjects();
				}
		
		// Fetch Project ID
				public Project fetchProjectById(Integer projectId)
				{	return projectDao.fetchProjectById(projectId).orElseThrow(() 
						-> new NoSuchElementException("Project with project ID = " + projectId + " does not exist."));
				}


}// End Class
