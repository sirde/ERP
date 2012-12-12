/**
 * 
 */
package staff;

import java.io.Serializable;

/**
 * @author sirde
 * 
 */

// TODO include assert function and add a lot of comment ;)

public abstract class Employe implements Serializable, Cloneable
{

	/**
	 * 
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public final static String CLASS_NAME = "Employé";
	private String name;

	/**
	 * Constructor without parameter
	 * 
	 */
	public Employe()
	{
		name = "sans name";
	}

	/**
	 * Constructor
	 * 
	 * @param theName
	 */
	public Employe(String theName)
	{
		if (theName == null) theName = "sans name";

		name = theName;
	}

	/**
	 * Copy constructor
	 * 
	 * @param otherObject
	 */
	public Employe(Employe otherObject)
	{
		name = otherObject.name;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name
	 * 
	 * @param newName
	 */
	public void setName(String newName)
	{
		if (newName == null) return;

		name = newName;

	}

	public String toString()
	{
		return(name);
	}

	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Employe other = (Employe) obj;
		if (name == null)
		{
			if (other.name != null) return false;
		}
		else if (!name.equals(other.name)) return false;
		return true;
	}

	/**
	 * abstract method: Has to be emplemented in the derived clases
	 * 
	 * @return the pay
	 */
	public abstract double getPay();

	abstract public Employe clone();


}
