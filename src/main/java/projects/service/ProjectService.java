package projects.service;


// Imports
		import projects.entity.Project;
		import projects.dao.ProjectDao;

//Class
		public class ProjectService 
		{
		
			private ProjectDao projectDao = new ProjectDao();
			
			
//Methods			
			public Project addProject(Project project) 
			{	return projectDao.insertProject(project);
			}
		


}// End Class
