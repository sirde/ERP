/**
 * 
 */
package erp;

/**
 * @author sirde
 *
 */

//TODO include assert function and add a lot of comment ;)

public abstract class Employe {
	
	private String name;
	
	public Employe()
	{
		name = "sans name";
	}

	public Employe(String theName)
	{
		if(theName == null)
			theName = "sans name";
		
		name = theName;
	}
	
	public Employe(Employe otherObject)
	{
		name = otherObject.name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String newName)
	{
		if(newName == null)
			return;
		
		name = newName;
		
	}
	
	public String toString()
	{
		return (name);
	}
	
	public boolean equals(Employe otherEmploye)
	{
		return(name.equals(otherEmploye));
	}
	
	public abstract double getPay();
	

}
