/**
 * 
 */
package staff;

/**
 * Manager class (Cadre) : this class inherit from Employee class. In addition to
 * the data member defined in Employee class, it contain one more field : the monthly salary
 * 
 * @author C. Gerber & O.Guédat
 * @version
 */
public class Manager extends Employee
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	// Define default value for data member;
	// Although Java set the default value for double primitive type to 0 for
	// field defined inside a class, it could be useful to define the two
	//following constants, if for any reason we decide to set a default
	// value other than 0.
	public static final double DEFAULT_SALARY = 0.;
	
	// Variable used to define the type of the instantiated object.
	public final static String CLASS_NAME = "Manager";

	private double salary;	// monthly salary

	/**
	 * Default constructor
	 */
	public Manager()
	{
		super();
		salary = DEFAULT_SALARY;
	}

	/**
	 * Constructor allowing to fill explicitly all the data member of a Manager
	 *  
	 * Precondition : 
	 * - the name must not be null
	 * - the salary must be equal or higher than 0
	 * 
	 * @param theName
	 * @param theSalary
	 */
	public Manager(String theName, double theSalary)
	{
		// super have to be the first instruction. It will manage the
		// precondition for theName
		super(theName);
		
		assert(theSalary>=0);
		
		salary = theSalary;
	}

	/**
	 * Copy constructor
	 * 
	 * Precondition : - originalObject must not be null
	 * 
	 * @param originalObject
	 */
	public Manager(Manager originalObject)
	{
		// Precondition is managed in the copy constructor of Employee class, which is invoke by super
		super(originalObject);
		salary = originalObject.salary;
	}
	
	/**
	 * Getter : return the monthly salary of an manager
	 *  
	 *  @return salary  
	 */
	public double getSalary()
	{
		return salary;
	}
	
	
	/**
	 * Setter : allow to set the monthly salary of a manager
	 * 
	 * Precondition : the salary must be equal or higher than 0
	 * 
	 * @param salary
	 */
	public void setSalary(double salary)
	{
		assert(salary>=0);
		this.salary = salary;
	}

	/**
	 * Getter : define the getPay() method to return the pay of an manager, which correspond to its salary
	 *  
	 *  @return salary  
	 */
	@Override
	public double getPay()
	{
		return salary;
	}

	/**
	 * Redefine the clone() method to allow to do a deep copy of a Manager instance
	 *  
	 *  @return a deep copy of the object who invoke the clone method
	 */	
	@Override
	public Manager clone()
	{
		// No try catch for this invoke of clone method. It will be manage in Employee class.
		return (Manager)super.clone(); // invoke the clone method of Employee
	}

	/**
	 * 
	 * Predicate : redefine equals() method to check if the data members of "this" manager are equal to the
	 * one get in parameters
	 * 
	 * @return true if data members of both object are equal, else false.
	 */
	@Override
	public boolean equals(Object otherObject)
	{	
		if (!super.equals(otherObject)) return false;
		else
		{
			// If we are here, it means that both object have been created from
			// the same class.
			// So, we have to check if data member (salary) defined in Manager
			// is equals.
			// To do that, we have to cast the otherObject to HourlyEmployee
			// type
			return (salary == ((Manager) otherObject).salary);
		}
	}

}
