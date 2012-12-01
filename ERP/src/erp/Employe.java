/**
 * 
 */
package erp;

import java.io.Serializable;

/**
 * @author sirde
 *
 */

//TODO include assert function and add a lot of comment ;)

public abstract class Employe implements Serializable, Cloneable {
	
	/**
	 * 
	 * 
	 */
	 
	private static final long serialVersionUID = 1L;	
	private String name;
	
	/**
	 * 
	 */
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
	


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employe other = (Employe) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public abstract double getPay();
	

	abstract protected Employe clone();
	

}
