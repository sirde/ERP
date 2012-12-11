/**
 * 
 */
package staff;

import java.io.Serializable;

/**
 * @author sirde
 * 
 */
public class Salesman extends HourlyEmploye implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7752299715893555100L;
	private final static double DEFAULT_COMMISSION = 0;
	private final static double DEFAULT_SALES = 0;
	/**
	 * 
	 */
	private double commission;
	private double sales;

	/**
	 * 
	 */
	public Salesman() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param theName
	 * @param theRate
	 * @param theHours
	 */
	public Salesman(String theName, double theRate, double theHours) {
		super(theName, theRate, theHours);
		commission = DEFAULT_COMMISSION;
		sales = DEFAULT_SALES;
	}

	/**
	 * @param theName
	 * @param theRate
	 * @param theHours
	 * @param theCommission
	 * @param theSales
	 */
	public Salesman(String theName, double theRate, double theHours,
			double theCommission, double theSales) {
		super(theName, theRate, theHours);
		commission = theCommission;
		sales = theSales;
	}

	/**
	 * @param otherObject
	 */
	public Salesman(Salesman otherObject) {
		super(otherObject);
		commission = otherObject.commission;
		sales = otherObject.sales;
	}

	/**
	 * @return the commission
	 */
	public double getCommission() {
		return commission;
	}

	/**
	 * @param commission
	 *            the commission to set
	 */
	public void setCommission(double commission) {
		this.commission = commission;
	}

	/**
	 * @return the sales
	 */
	public double getSales() {
		return sales;
	}
	
	@Override
	public double getPay()
	{
		return super.getPay() + commission * sales;
	}

	/**
	 * @param sales
	 *            the sales to set
	 */
	public void setSales(double sales) {
		this.sales = sales;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salesman other = (Salesman) obj;
		if (Double.doubleToLongBits(commission) != Double
				.doubleToLongBits(other.commission))
			return false;
		if (Double.doubleToLongBits(sales) != Double
				.doubleToLongBits(other.sales))
			return false;
		return true;
	}

	public Salesman clone() {
		return new Salesman(this);

	}

}
