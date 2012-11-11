package erp;

/**
 * @author sirde
 *
 */
public class EmployeTemporaire extends Employe {

	
	static final double DEFAULT_TARIF = 0;
	static final int DEFAULT_HEURE = 0;
	
	private double tarif;
	private int heure;
	/**
	 * 
	 */
	public EmployeTemporaire() {
		super();
		tarif = DEFAULT_TARIF;
		heure = DEFAULT_HEURE;
		
	}

	/**
	 * @param leNom
	 */
	public EmployeTemporaire(String leNom, double leTarif, int lHeure) {
		super(leNom);
		if(leTarif < 0)
				leTarif = DEFAULT_TARIF;
		
		if(lHeure < 0)
			lHeure = DEFAULT_HEURE;

		tarif = leTarif;
		heure = lHeure;
		
	}

	/**
	 * @param objetACopier
	 */
	public EmployeTemporaire(EmployeTemporaire objetACopier) {
		super(objetACopier);
		tarif = objetACopier.tarif;
		heure = objetACopier.heure;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		if(tarif >= 0)
			this.tarif = tarif;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		
		if(heure >= 0)
			this.heure = heure;
	}
	
	public double getPaye()
	{
		return tarif * heure;
	}

	@Override
	public String toString() {
		return (getNom() + "tarif=" + tarif + ", heure=" + heure + "]");
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeTemporaire other = (EmployeTemporaire) obj;
		if (heure != other.heure)
			return false;
		if (Double.doubleToLongBits(tarif) != Double
				.doubleToLongBits(other.tarif))
			return false;
		return true;
	}
	
	
	




}
