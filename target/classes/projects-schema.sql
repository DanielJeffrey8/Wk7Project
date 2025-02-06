DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project;

CREATE TABLE project 
      ( project_id INT NOT NULL AUTO_INCREMENT,
		project_name VARCHAR(128) NOT NULL,
		estimated_hours  DECIMAL (7,2),
		actual_hours DECIMAL (7,2),
		difficulty INT,
		notes TEXT,
		PRIMARY KEY (project_id)
	  );
		
CREATE TABLE category 
	  ( category_id INT NOT NULL AUTO_INCREMENT,
		category_name VARCHAR(64) NOT NULL,
		PRIMARY KEY (category_id)		
	  );
	   

CREATE TABLE material
	  ( material_id INT NOT NULL AUTO_INCREMENT,
	    project_id INT NOT NULL,
	    material_name VARCHAR(128) NOT NULL,
	    num_required INT,
	    cost DECIMAL(7,2),
	    PRIMARY KEY (material_id),
	    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
	  );
	  
	  
CREATE TABLE step
      ( step_id INT NOT NULL AUTO_INCREMENT,
      	project_id INT NOT NULL,
      	step_text TEXT NOT NULL,
      	step_order INT NOT NULL,
      	PRIMARY KEY (step_id),
      	FOREIGN KEY (project_id) REFERENCES project(project_id)
      );
      

CREATE TABLE project_category
	  ( project_id INT NOT NULL,
	  	category_id INT NOT NULL,
	  	FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
	  	FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE,
	  	UNIQUE KEY (project_id, category_id)		
	  );
	  


	  
INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) 
	VALUES	('Hang a door.', 2, 1, 1, 'Used hinges from home depot project.'),
		('Install a window.', 2, 1.5, 2, 'Single hung vinyl with grid.'),
		('Break a window.', 1, .15, 1, 'Customer refused to pay for new window.'),
		('Install carpet.', 20, 15, 3, 'Used customers old carpet.');
	
	
INSERT INTO material (project_id, material_name, num_required, cost)
    VALUES 	(1, 'Door', 1, 365 ),
    		(1, 'Hinge', 2, 4.35),
    		(1, 'Door Set', 1, 65.38),
    		
    		(2, 'Glass', 1, 25 ),
		(2, 'Puddy', 1, 5),
			
		(3, 'Rock', 1, 0 ),
			
		(4, 'Carpet', 1, 0 ),
		(4, 'Tack strips', 10, 45.22);
	
	
INSERT INTO step (project_id, step_text, step_order)
	VALUES 	(1, 'Remove old glass', 1),
		(1, 'Clean off old putty.', 2),
		(1, 'Insert new glass.', 3),
		(1, 'Putty new glass.', 4),
		   
		(2, 'Install hinge on door jamb.', 1 ),
		(2, 'Install door on hinges.', 2 ),
		   
		(3, 'Throw ROCK through newlly installed window!!!!', 1 ),
		   
		(4, 'Clean floors.', 1 ),
		(4, 'Install tack strips.', 2 ),
		(4, 'Install carpet.', 3 );  
	
	
INSERT INTO category (category_id, category_name)
	VALUES  (1,'Doors and Windows'),
           	(2, 'Flooring'),
           	(3, 'Bad Customers!');

	 
INSERT INTO project_category (project_id, category_id)
	VALUES (1, 1),
	       (1, 2);
	
		 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
