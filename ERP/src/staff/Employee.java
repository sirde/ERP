/**
 * 
 */
package staff;

import java.lang.String;
import java.io.Serializable;

/**
 * 
 * Employee abstract class :
 * This class contain the common data members and
 * methods for an employee. This class implement the serializable interface to
 * allow its derived class to be easily save and restore to/from a file.
 * It implements the cloneable interface to allow to redefine the clone() method.
 * 
 * @author C. Gerber & O.Guédat
 * @version
 * 
 */


public abstract class Employee implements Serializable, Cloneable
{

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// TODO explain why we define the following constant
	public final static String CLASS_NAME = "Employé";

	private String name;

	/**
	 * Default constructor
	 */
	public Employee()
	{
		name = "sans name";
	}

	/**
	 * Constructor with one String parameter
	 * 
	 * Precondition : - theName String parameter must not be set to null
	 * Postcondition : - Employee name data members is set t theName
	 * 
	 * @param theName
	 */
	public Employee(String theName)
	{
		assert (theName != null);

		name = theName;
	}

	/**
	 * Copy constructor
	 * 
	 * Precondition : - originalObject must not be null PostCondition : - name
	 * data member are set to the same name than the originalObject
	 * 
	 * @param originalObject
	 */
	public Employee(Employee originalObject)
	{
		assert (originalObject != null);

		name = originalObject.name; // String class is immutable -> a new string
									// instance will be created implicitly
	}

	/**
	 * Getter : get the name of the employee
	 * 
	 * @return the name of the employee
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Setter : set the employee name
	 * 
	 * Precondition : - newName parameter must not be null
	 * 
	 * @param newName
	 */
	public void setName(String newName)
	{
		assert (newName != null);

		name = newName;
	}

	/**
	 * Getter : return the employee object formated in a string. The returned
	 * string contain the employee name data member
	 * 
	 * @return name
	 */
	@Override
	public String toString()
	{
		return name;
	}

	/**
	 * Predicate : check if the data members of "this" employee are equal to the
	 * one get in parameters
	 * 
	 * @return true if data members of both object are equal, else false.
	 */
	@Override
	public boolean equals(Object otherObject)
	{
		// return false if the otherObject is null (rule define by Java doc)
		if (otherObject == null) return false;
		// return true if both object refer to the same instance
		else if (this == otherObject) return true;
		// return false if both object have not been created with the same class
		if (getClass() != otherObject.getClass()) return false;
		// return true if the name of both object is the same
		else
		{
			Employee otherEmployee = (Employee) otherObject;
			return (name.equals(otherEmployee.name));
		}
	}
	
	/**
	 * Redefine the clone() method to allow to do a deep copy of an Employee
	 *  
	 *  @return a deep copy of the object how invoke the clone method
	 */	
	@Override
	public Employee clone()
	{
		try
		{
			return (Employee) super.clone(); // invoke the clone method of Object base class
		}
		catch(CloneNotSupportedException e)
		{ 	//This should not happen.
			return null; //To keep the compiler happy.
		}
	}

	// Abstract method

	/**
	 * abstract method getPay() must be implemented in derived classes of
	 * Employee class
	 * 
	 * @return the monthly salary of the employee
	 */
	public abstract double getPay();


}
