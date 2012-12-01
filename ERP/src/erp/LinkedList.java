/**
 * 
 */
package erp;

import java.io.Serializable;

/**
 * @author sirde
 * 
 */
public class LinkedList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 607643732520894909L;

	// ----------------------------------------------------------------------------
	/**
	 * Class Cellule interne de la class List
	 */

	class Cell implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7412202956505489139L;
		private Employe content;
		private Cell next;

		protected Cell(Employe content, Cell next) {
			this.content = content;
			this.next = next;
		}
	}

	// ----------------------------------------------------------------------------

	// Données privées
	// -----------------------------------------------------------

	private Cell firstCell;
	private int size;

	// Méthodes publiques
	// --------------------------------------------------------
	/**
	 * Constructeur.
	 */
	public LinkedList() {
		firstCell = null;
		size = 0;
	}

	/**
	 * Predicat : Retourne true si la liste est vide, false sinon.
	 */
	public boolean isEmpty() {
		return (firstCell == null);
	}

	/**
	 * Mutateur : Ajoute l'élément elem en tête de liste.
	 */
	public void addFirst(Employe elem) {
		Cell nlleCellule = new Cell(elem, firstCell);
		firstCell = nlleCellule;
		size++;
	}

	public void add(Employe elem) {
		Cell newCellule = new Cell(elem, null);

		if (firstCell == null) {
			firstCell = newCellule;
			size++;
		} else {
			Cell a = firstCell;

			while (a.next != null) {
				a = a.next;
			}

			a.next = newCellule;
			size++;
		}

	}

	/**
	 * Parcourt et affiche la liste. Précondition : Liste non-vide.
	 */
	public void afficherListe() {
		Cell a = firstCell;

		while (a != null) {
			System.out.println("Element : " + a.content.toString());
			a = a.next;
		}
	}

	public Employe get(int index) {
		Cell a = firstCell;
		int i = 1;
		while (i < index && a.next != null) {
			a = a.next;
			i++;
		}

		if (index == i)
			return a.content.clone();
		else
			return null;
	}

	public void delete(int index) {

		if(index == 1 && firstCell != null)
		{
			firstCell = firstCell.next;
			size--;
			if(Erp.DEBUG)
				System.out.println("First cell has been deleted");
		}
		else
		{
			Cell previousCell = firstCell;	
			Cell deletedCell = firstCell.next;
			
			int i = 2;
			
			while (i < index && deletedCell.next != null) {
				previousCell = deletedCell;
				deletedCell = deletedCell.next;
	
				i++;
			}
			if (index == i)
			{
				previousCell.next = deletedCell.next;
				size--;
				if(Erp.DEBUG)
					System.out.println("Cell "+ i + " has been deleted");
			}
			else
			{
				if(Erp.DEBUG)
					System.out.println("No cell has been deleted");
					
			}
		}
		
	}

	public void replace(int index, Employe elem) {
		
		Cell a = firstCell;	
		
		int i = 1;
		
		while (index != i && a.next != null) {
			a = a.next;
			i++;
		}
		if (index == i)
			a.content = elem;
	}
	
	public int getSize()
	{
		return size;
	}
}
