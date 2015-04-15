import java.util.Iterator;

/**
 * An interface for a list of numbers
 * @author Joseph Volpe
 */
public interface NumList extends Iterable<Double> {
	
	/**
	 * Returns size of the list
	 * @return size of the list
	 */
	public int size();
	
	/**
	 * Inserts a value at position i
	 * @param i the index for the value to be inserted at
	 * @param value the value to be inserted
	 */
	public void insert(int i, double value);
	
	/**
	 * Removes value at index i 
	 * @param i the index to be removed
	 */
	public void remove(int i);
	
	/**
	 * Returns the value at index i
	 * @param i the index to be looked at
	 * @return the value at index i
	 */
	public double lookup(int i);
	
	/**
	 * An iterator to iterate throughout the list
	 * @return Iterator<Double> the iterator to be returned
	 */
	public Iterator<Double> iterator();
}
