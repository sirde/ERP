/**
 * 
 */
package utility;

import java.io.Serializable;

/**
 * Generic Linked List class.
 * 
 * @author C. Gerber & O.Guédat
 * @version 
 * 
 */
public class LinkedList<T_LinkedList> implements Serializable
{
	// ------------------------------------------------------
	// Private members
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Private generic cell class
	 * This class contain a field of the generic cell type which represent
	 * the element content by the cell, and a second which is a reference
	 * to a the next generic cell.
	 */
	private class Cell<T_Cell> implements Serializable, Cloneable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private T_Cell content;
		private Cell<T_Cell> next;

		
		protected Cell(T_Cell content, Cell<T_Cell> next)
		{
			this.content = content;
			this.next = next;
		}
	}


	private Cell<T_LinkedList> headCell;
	private int size;

	// ------------------------------------------------------
	// Public members

	/**
	 * Default constructor
	 */
	public LinkedList()
	{
		headCell = null;
		size = 0;
	}

	/**
	 * Predicate : check if the list is empty
	 * @return true if the list is empty, else false.
	 */
	public boolean isEmpty()
	{
		return (headCell == null);
	}
	
	/**
	 * Getter : get the size of the list
	 * @return the number of elements in this list
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Mutator : add a new element at the end of the list
	 * 
	 * Precondition :
	 * - element must not be null
	 * Postcondition :
	 * - list size field is updated and the list contain the new element at its end
	 * 
	 * @param element
	 */
	public void addAtEnd(T_LinkedList element)
	{
		assert(element!=null);
		
		Cell<T_LinkedList> newCellule = new Cell<>(element, null);

		if (headCell == null)
		{
			headCell = newCellule;
			size++;
		}
		else
		{
			Cell<T_LinkedList> a = headCell;

			while (a.next != null)
			{
				a = a.next;
			}

			a.next = newCellule;
			size++;
		}
	}

	/**
	 * Returns the element at the indexed position in this list.
	 * 
	 * Precondition : 	
	 * - LinkedList is not empty
	 * - index value must be in the range from 1 to size
	 * Postcondition :	
	 * - Due to generic class limitation (we can't create an instance 
	 * of the generic type in the class), this method return a 
	 * reference to the indexed element.
	 * User must be careful with private leakage if the generic type is a mutable class !
	 * 
	 * @param index of the element to return  (index range is from 1 to size)
	 * @return a reference to the element content a the chosen index
	 */
	public T_LinkedList get(int index)
	{
		assert(size != 0);							// list must not be empty
		assert( (index >= 1) && ( index <= size));	// index range must be from 1 to size
		
		Cell<T_LinkedList> a = headCell;
		int i = 1;
		while (i < index && a.next != null)
		{
			a = a.next;
			i++;
		}
		if (index == i) return a.content;	// return a reference to content and not a copy due to generic class limitations
		else return null;
	}

	/**
	 * Delete the element at the indexed position
	 * 
	 * Precondition :
	 * - LinkedList is not empty
	 * - index value must be in the range from 1 to size
	 * 
	 * @param index
	 */
	public void delete(int index)
	{
		assert(size != 0);							// list must not be empty
		assert( (index >= 1) && ( index <= size));	// index range must be from 1 to size			

		if (index == 1 && headCell != null)
		{
			headCell = headCell.next;
			size--;
		}
		else
		{
			Cell<T_LinkedList> previousCell = headCell;
			Cell<T_LinkedList> deletedCell = headCell.next;
			
			int i = 2;

			while (i < index && deletedCell.next != null)
			{
				previousCell = deletedCell;
				deletedCell = deletedCell.next;

				i++;
			}
			if (index == i)
			{
				previousCell.next = deletedCell.next;
				size--;
			}
		}
	}

	/**
	 * Replace the indexed element with the new one give in parameter
	 * 	 * 
	 * Precondition :
	 * - LinkedList is not empty
	 * - index value must be in the range from 1 to size
	 * - element must not be null
	 * 
	 * @param index
	 * @param element
	 */
	public void replace(int index, T_LinkedList element)
	{
		assert(size != 0);							// list must not be empty
		assert((index >= 1) && ( index <= size));	// index range must be from 1 to size
		assert(element != null);					// element must be null

		Cell<T_LinkedList> a = headCell;

		int i = 1;

		while (index != i && a.next != null)
		{
			a = a.next;
			i++;
		}
		if (index == i) a.content = element;
	}
}
