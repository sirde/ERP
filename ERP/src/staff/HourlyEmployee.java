package staff;

/**
 * HourlyEmployee class This class inherit from Employee class. In addition to
 * the data member defined in Employee class, it contain two more field :
 * wageRate and hours
 * 
 * @author C. Gerber & O.Guédat
 * @version
 */
public class HourlyEmployee extends Employee
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Define default value for data member;
	// Although Java set the default value for double primitive type to 0 for
	// field defined inside a class,
	// it could be useful to define the two following constants, if for any
	// reason we decide to set a default value other than 0.
	static final double DEFAULT_RATE = 0;
	static final int DEFAULT_HOURS = 0;

	// TODO comment the why we define the following constant
	public final static String CLASS_NAME = "Temporaire";

	private double wageRate;
	private double hours;

	/**
	 * Default constructor
	 */
	public HourlyEmployee()
	{
		super(); // call default constructor of Employee Class to set the
					// default name field
		wageRate = DEFAULT_RATE;
		hours = DEFAULT_HOURS;
	}

	/**
	 * Constructor allowing to fill explicitly all the data member of an
	 * HourlyEmployee
	 * 
	 * Precondition : 
	 * - the name must not be null
	 * - the WageRate must be equal or higher than 0
	 * - the Hours must be equal or higher than 0
	 * 
	 * @param theName
	 * @param theWageRate
	 * @param theHours
	 */
	public HourlyEmployee(String theName, double theWageRate, double theHours)
	{
		// super have to be the first instruction. It will manage the
		// precondition for theName
		super(theName); // call default constructor of Employee Class to set the
						// default name field

		assert (theWageRate >= 0 && theHours >= 0);

		wageRate = theWageRate;
		hours = theHours;
	}

	/**
	 * Copy constructor
	 * 
	 * Precondition : - originalObject must not be null
	 * PostCondition : - data member are set to the same name than the originalObject
	 * 
	 * @param originalObject
	 */
	public HourlyEmployee(HourlyEmployee originalObject)
	{
		// Precondition is managed in the copy constructor of Employee class, which is invoke by super
		super(originalObject);
		wageRate = originalObject.wageRate;
		hours = originalObject.hours;
	}

	/**
	 * Getter : allow to get the wage rate
	 * 
	 * @return wageRate
	 */
	public double getWageRate()
	{
		return wageRate;
	}

	/**
	 * Setter : allow to set the wage rate
	 * 
	 * Precondition : the rate must be equal or higher than 0
	 * 
	 * @param wageRate
	 */
	public void setRate(double wageRate)
	{
		assert(wageRate >=0);
		
		this.wageRate = wageRate;
	}

	/**
	 * Getter : allow to get the working hours
	 * 
	 * @return hours
	 */
	public double getHours()
	{
		return hours;
	}

	/**
	 * Setter : allow to set the working hours
	 * 
	 * Precondition : the working hours must be equal or higher than 0
	 * 
	 * @param hours
	 */
	public void setHours(int hours)
	{
		assert(hours >=0);

		this.hours = hours;
	}

	/**
	 * Getter : allow to calculate the monthly salary of an employee
	 *  
	 *  @return the monthly salary  
	 */
	@Override
	public double getPay()
	{
		return wageRate * hours;
	}

	/**
	 * Redefine the toStirng method : return the HourlyEmployee object formated in a string.
	 * The returned string formated contain the employee name, wageRate and hours data members.
	 *  
	 *  @return the monthly salary  
	 */	
	@Override
	public String toString()
	{
		return (super.toString() + " ; rate" + wageRate + " ; hours=" + hours + "]");
	}

	/**
	 * Redefine the clone() method to allow to do a deep copy of an HourlyEmployee instance
	 *  
	 *  @return a deep copy of the object who invoke the clone method
	 */	
	@Override
	public HourlyEmployee clone()
	{
		// No try catch for this invoke of clone method. It will be manage in Employee class.
		return (HourlyEmployee)super.clone(); // invoke the clone method of Employee
	}
	
	/**
	 * 
	 * Predicate : redefine equals() method to check if the data members of "this" hourly employee are equal to the
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
			// So, we have to check if data members defined in HourlyEmployee
			// are equals.
			// To do that, we have to cast the otherObject to HourlyEmployee
			// type
			return (wageRate == ((HourlyEmployee) otherObject).wageRate &&
					hours == ((HourlyEmployee) otherObject).hours);
		}

	}

}
