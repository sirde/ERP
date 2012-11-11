/**
 * 
 */
package erp;

/**
 * @author sirde
 *
 */

//TODO include assert function

public class Employe {
	
	private String nom;
	
	public Employe()
	{
		nom = "sans nom";
	}

	public Employe(String leNom)
	{
		if(leNom == null)
			leNom = "sans nom";
		
		nom = leNom;
	}
	
	public Employe(Employe objetACopier)
	{
		nom = objetACopier.nom;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setNom(String nouveauNom)
	{
		if(nouveauNom == null)
			return;
		
		nom = nouveauNom;
		
	}
	
	public String toString()
	{
		return (nom);
	}
	
	public boolean equals(Employe autreEmploye)
	{
		return(nom.equals(autreEmploye));
	}
	

}
