package erp;

/**
 * @author sirde
 * 
 */
public class HourlyEmploye extends Employe {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4132032299334681517L;
	static final double DEFAULT_RATE = 0;
	static final int DEFAULT_HOURS = 0;

	private double rate;
	private double hours;

	/**
	 * 
	 */
	public HourlyEmploye() {
		super();
		rate = DEFAULT_RATE;
		hours = DEFAULT_HOURS;
	}

	/**
	 * @param leNom
	 */
	public HourlyEmploye(String theName, double theRate, double theHours) {
		super(theName);
		if (theRate < 0)
			theRate = DEFAULT_RATE;

		if (theHours < 0)
			theHours = DEFAULT_HOURS;

		rate = theRate;
		hours = theHours;
	}

	/**
	 * @param objetACopier
	 */
	public HourlyEmploye(HourlyEmploye otherObject) {
		super(otherObject);
		rate = otherObject.rate;
		hours = otherObject.hours;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		if (rate >= 0)
			this.rate = rate;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(int hours) {

		if (hours >= 0)
			this.hours = hours;
	}

	public double getPay() {
		return rate * hours;
	}

	@Override
	public String toString() {
		return (getName() + "rate" + rate + ", hours=" + hours + "]");
	}

	@Override
	protected HourlyEmploye clone() {
		return new HourlyEmploye(this);
	}
}
