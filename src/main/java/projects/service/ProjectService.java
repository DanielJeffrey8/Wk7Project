package projects.service;

//Imports
		import java.util.List;
		import java.util.NoSuchElementException;
		import java.util.Optional;
		
		import projects.dao.ProjectDao;
		import projects.entity.Project;
import projects.exception.DbException;

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

		// Modify Project Details
				public void modifyProjectDetails(Project project) 
				{	if ( !projectDao.modifyProjectDetails(project))
						{	throw new DbException("Project with ID = " + project.getProjectId() + "does not exist.");	}
					
				}

		// Delete a Project
				public void deleteProject(Integer projectId) 
				{	if ( !projectDao.deleteProject(projectId))
					{	throw new DbException("Project with ID = " + projectId + " does not exist.");	}
				}


}// End Class
