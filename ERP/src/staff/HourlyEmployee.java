package staff;

/**
 * @author sirde
 * 
 */
public class HourlyEmployee extends Employee
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4132032299334681517L;
	static final double DEFAULT_RATE = 0;
	static final int DEFAULT_HOURS = 0;
	public final static String CLASS_NAME = "Temporaire";

	private double rate;
	private double hours;

	/**
	 * 
	 */
	public HourlyEmployee()
	{
		super();
		rate = DEFAULT_RATE;
		hours = DEFAULT_HOURS;
	}

	/**
	 * @param theName
	 * @param theRate
	 * @param theHours
	 * @param leNom
	 */
	public HourlyEmployee(String theName, double theRate, double theHours)
	{
		super(theName);
		if (theRate < 0) theRate = DEFAULT_RATE;

		if (theHours < 0) theHours = DEFAULT_HOURS;

		rate = theRate;
		hours = theHours;
	}

	/**
	 * @param otherObject
	 * @param objetACopier
	 */
	public HourlyEmployee(HourlyEmployee otherObject)
	{
		super(otherObject);
		rate = otherObject.rate;
		hours = otherObject.hours;
	}

	/**
	 * @return the rate
	 */
	public double getRate()
	{
		return rate;
	}

	/**
	 * @param rate
	 */
	public void setRate(double rate)
	{
		if (rate >= 0) this.rate = rate;
	}

	/**
	 * @return the hours
	 */
	public double getHours()
	{
		return hours;
	}

	/**
	 * @param hours
	 */
	public void setHours(int hours)
	{

		if (hours >= 0) this.hours = hours;
	}

	public double getPay()
	{
		return rate * hours;
	}

	@Override
	public String toString()
	{
		return (getName() + "rate" + rate + ", hours=" + hours + "]");
	}

	@Override
	public HourlyEmployee clone()
	{
		return new HourlyEmployee(this);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(hours);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		HourlyEmployee other = (HourlyEmployee) obj;
		if (Double.doubleToLongBits(hours) != Double
				.doubleToLongBits(other.hours)) return false;
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate)) return false;
		return true;
	}

}
