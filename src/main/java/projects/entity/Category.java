/**
 * 
 */
package projects.entity;

/**
 * @author Promineo
 *
 */
public class Category 
{	
	
// Variables
		  private Integer categoryId;
		  private String categoryName;
		  

// Methods
	  public Integer getCategoryId() 
	  {	return categoryId;	}
	
	  
	  public void setCategoryId(Integer categoryId) 
	  {	this.categoryId = categoryId;	}
	
	  
	  public String getCategoryName() 
	  {	return categoryName;	}
	
	  
	  public void setCategoryName(String categoryName) 
	  {	this.categoryName = categoryName;	}
	
	  
	  @Override
	  public String toString() 
	  {	return "ID=" + categoryId + ", categoryName=" + categoryName;	}


} // End Class
