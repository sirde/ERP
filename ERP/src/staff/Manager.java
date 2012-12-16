/**
 * 
 */
package staff;

/**
 * @author C.Gerber
 * 
 */
public class Manager extends Employee
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2179681792343172157L;
	private static final double DEFAULT_SALARY = 0;
	public final static String CLASS_NAME = "Manager";

	private double salary;

	/**
	 * 
	 */
	public Manager()
	{
		super();
		salary = DEFAULT_SALARY;

	}

	/**
	 * @param theName
	 */
	public Manager(String theName)
	{
		super(theName);
		salary = DEFAULT_SALARY;
	}

	/**
	 * @param theName
	 * @param theSalary
	 */
	public Manager(String theName, double theSalary)
	{
		super(theName);
		salary = theSalary;
	}

	/**
	 * @param otherObject
	 */
	public Manager(Manager otherObject)
	{
		super(otherObject);
		salary = otherObject.salary;
	}

	@Override
	public double getPay()
	{
		return salary;
	}

	/**
	 * Redefine the clone() method to allow to do a deep copy of a Manager instance
	 *  
	 *  @return a deep copy of the object how invoke the clone method
	 */	
	@Override
	public Manager clone()
	{
		// No try catch for this invoke of clone method. It will be manage in Employee class.
		return (Manager)super.clone(); // invoke the clone method of Employee
	}

	/**
	 * @return the salary
	 */
	public double getSalary()
	{
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(double salary)
	{
		this.salary = salary;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		Manager other = (Manager) obj;
		if (Double.doubleToLongBits(salary) != Double
				.doubleToLongBits(other.salary)) return false;
		return true;
	}

}
