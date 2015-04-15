import java.util.Iterator;

/**
 * List of numbers ordered by an array
 * @author Joseph Volpe
 */
public class NumArrayList implements NumList {

	/** Represents the list */
	private double[] listOfNumbers;
	
	/** Keeps track of the size of the list */
	private int currentSizeOfTheList = 0;
	
	/**
	 * Default constructor that sets a default size of the array to 10
	 */
	public NumArrayList() {
		listOfNumbers = new double[10];
	}
	
	
	/**
	 * Returns the size of the array
	 * Worst Case Run Time: O(1)
	 * @return the size of the array
	 */
	@Override
	public int size() {
		return currentSizeOfTheList;
	}

	/**
	 * Inserts a new double value at before position i. If the array is full, resizes
	 * the array to be able to hold one more value and then adds that value to the end.
	 * Worst Case Run Time: O(n)
	 * @param i the index to be inserted before
	 * @param value the value to be inserted
	 */
	@Override
	public void insert(int i, double value) {
		if (!isFull()) {
			//Checks to see if greater than the current size,
			//if so then i can just be added to the end of the current list
			if (i >= currentSizeOfTheList) {
				listOfNumbers[currentSizeOfTheList] = value;
			}
			else {
				for (int index = currentSizeOfTheList; index > i; index--) {
					listOfNumbers[index] = listOfNumbers[index - 1];
				}
				listOfNumbers[i] = value;
			}
			currentSizeOfTheList++;
		}
		//re-size array
		else {
			double[] newListOfNumbers = new double[listOfNumbers.length + 1];
			for (int index = 0; index < listOfNumbers.length; index++) {
				 newListOfNumbers[index] = listOfNumbers[index];
			}
			listOfNumbers = newListOfNumbers;
			listOfNumbers[listOfNumbers.length - 1] = value;
			currentSizeOfTheList++;
		}
	}

	/**
	 * Removes the double value at position i
	 * Calls the shrinkArray method to check if the array should be shrunk or not.
	 * Worst Case Run Time: O(n^2)
	 * @param i the index to be removed
	 */
	@Override
	public void remove(int i) {
		if (i < listOfNumbers.length) {
			//Moves entire array over to replace the index being replaced
			for (int index = i; index < listOfNumbers.length - 1; index++) {
				listOfNumbers[index] = listOfNumbers[index+1];
			}	
			listOfNumbers[listOfNumbers.length - 1] = 0; //sets the last index to 0 since it potentially moved
			currentSizeOfTheList--;
			shrinkArray(listOfNumbers); // Possibly adds another n to runtime
		}
	}

	/**
	 * Return the value at position i
	 * Worst Case Run Time: O(1)
	 * @param i the index to lookup
	 * @return the value at position i 
	 */
	@Override
	public double lookup(int i) {
		if (i < listOfNumbers.length){
				return listOfNumbers[i];
		}
		else {
			throw new IllegalArgumentException("Index does not exist");
		}
	}
	
	/**
	 * Checks if the list is full
	 * Worst Case Run Time: O(1)
	 * @return if the list is full
	 */
	public boolean isFull() {
		return (currentSizeOfTheList == listOfNumbers.length);
	}
	
	/**
	 * Shrinks the array if the array's size is too big compared to its actual size
	 * Worst Case Run Time: O(n)
	 * @param list an array of numbers to check if it should be shrunk
	 */
	public void shrinkArray(double[] list) {
		if (currentSizeOfTheList < (listOfNumbers.length / 2)) {
			int newSize = currentSizeOfTheList * 2;
			double[] newListOfNumbers = new double[newSize];
			for (int i = 0; i < newListOfNumbers.length; i++) {
				newListOfNumbers[i] = listOfNumbers[i];
			}
			listOfNumbers = newListOfNumbers;
		}
	}

	/**
	 * Returns an Iterator of type double to iterate the list
	 * Worst Case Run Time: O(1)
	 * @return Iterator<Double> the iterator to iterate the list
	 */
	@Override
	public Iterator<Double> iterator() {
		return new Iterator<Double>() {
			
			/** 
			 * Index of the iterator
			 * Starts at -1 because next() has not been called on the first value yet
			 */
			int index = -1;
			
			/**
			 * Returns a boolean whether there is a next value in the list
			 * Worst Case Run Time: O(1)
			 * @return a boolean whether there is a next value in the list
			 */
			public boolean hasNext() {
				return ((index+1) < currentSizeOfTheList);
			}
			
			/**
			 * Returns the next Double in the list
			 * Worst Case Run Time: O(1)
			 * @return the next Double in the list
			 */
			public Double next() {
				if (hasNext()) {
					index++;
					return lookup(index);
				}
				else {
					throw new IllegalArgumentException("Index does not exist");
				}
			}

			/** Overridden method but unnecessary */
			public void remove() {
			}
		};
	};
	
}
