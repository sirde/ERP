/**
 * 
 */
package staff;

/**
 * SalesMan class : this class inherit from HourlyEmployee class. In addition to
 * the data member defined in HourlyEmployee class, it contain two more field :
 * the number of sales and the commission per sales.
 * 
 * @author C. Gerber & O.Guédat
 * @version
 */
public class SalesMan extends HourlyEmployee
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
	private final static double DEFAULT_COMMISSION = 0;
	private final static double DEFAULT_SALES = 0;
	
	// TODO explain why we define the following constant	
	public final static String CLASS_NAME = "Vendeur";
	/**
	 * 
	 */
	private double commission;	// Commission per sales
	private double sales;		// number of sales

	/**
	 * Default constructor
	 */
	public SalesMan()
	{
		super();
		commission = DEFAULT_COMMISSION;
		sales = DEFAULT_SALES;
	}

	/**
	 * Constructor allowing to fill explicitly all the data member of a
	 * SalesMan
	 * 
	 * Precondition : 
	 * - the name must not be null
	 * - the wageRate must be equal or higher than 0
	 * - the hours must be equal or higher than 0
	 * - the commission must be equal or higher than 0
	 * - the sales must be equal or higher than 0
	 * 
	 * @param theName
	 * @param theWageRate
	 * @param theHours
	 * @param theCommission
	 * @param theSales
	 */
	public SalesMan(String theName, double theWageRate, double theHours,
			double theCommission, double theSales)
	{
		// super have to be the first instruction. It will manage the
		// precondition for theName, theWageRate and theHours
		super(theName, theWageRate, theHours);
		
		assert(theCommission>=0 && sales>=0);
		
		commission = theCommission;
		sales = theSales;
	}

	/**
	 * Copy constructor
	 * 
	 * Precondition : - originalObject must not be null
	 * 
	 * @param originalObject
	 */
	public SalesMan(SalesMan otherObject)
	{
		// Precondition is managed in the copy constructor of Employee class
		super(otherObject);
		commission = otherObject.commission;
		sales = otherObject.sales;
	}

	/**
	 * Getter : allow to get the commission per sale
	 * 
	 * @return commission
	 */
	public double getCommission()
	{
		return commission;
	}

	/**
	 * Setter : allow to set the commission per sale
	 * 
	 * Precondition :
	 * - commission must be equal or higher than 0
	 * 
	 * @param commission
	 */
	public void setCommission(double commission)
	{
		assert(commission >= 0);
		this.commission = commission;
	}

	/**
	 * Getter : allow to get the number of sales
	 * 
	 * @return sales
	 */
	public double getSales()
	{
		return sales;
	}
	

	/**
	 * Setter : allow to get the number of sales
	 * 
	 * Precondition :
	 * - sales must be equal or higher than 0
	 * 
	 * @param sales
	 */
	public void setSales(double sales)
	{
		assert(sales >= 0);
		this.sales = sales;
	}

	/**
	 * Getter : redefine the getPay() method to return the pay of a sales man, which correspond to its monthly salary
	 *  
	 *  @return salary
	 */
	@Override
	public double getPay()
	{
		return super.getPay() + commission * sales;
	}
	
	/**
	 * Redefine the clone() method to allow to do a deep copy of a SalesMan instance
	 *  
	 *  @return a deep copy of the object who invoke the clone method
	 */	
	public SalesMan clone()
	{		
		// No try catch for this invoke of clone method. It will be manage in Employee class.
		return (SalesMan)super.clone(); // invoke the clone method of HourlyEmployee
	}

	/**
	 * 
	 * Predicate : redefine equals() method to check if the data members of "this" sales man are equal to the
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
			return (commission == ((SalesMan) otherObject).commission &&
					sales == ((SalesMan) otherObject).sales);
		}
	}
	
}
