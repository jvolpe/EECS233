import java.util.Iterator;

/**
 * Represents a list of numbers by a linked list
 * @author Joseph Volpe
 */
public class NumLinkedList implements NumList {

	/** Represents the first node */
	private Node<Double> head = new Node<Double>(0.0, null);
	
	/** Represents start of the list */
	private int currentSizeOfTheList = 0;
	
	/**
	 * Returns size of the list
	 * Worst Case Run Time: O(1)
	 * @return size of the list
	 */
	@Override
	public int size() {
		return currentSizeOfTheList;
	}

	/**
	 * Inserts a value at position i
	 * Worst Case Run Time: O(n)
	 * @param i the index position to add something at
	 * @param value the value to be added
	 */
	@Override
	public void insert(int i, double value) {
		int indexCount = 0; 	         //keep track of the current index
		boolean valueNotAddedYet = true; //keeps track if value was added
		Node<Double> nodeptr = head;     //A pointer to traverse the NumLinkedList
		//Checks to see if a node has been added yet to the LL
		if (currentSizeOfTheList == 0) {
			head = new Node<Double>(value, null);
			valueNotAddedYet = false;
		}
		//Inserts at beginning if index is 0
		if (valueNotAddedYet && i == 0) {
			head = new Node<Double>(value, nodeptr);
			valueNotAddedYet = false;
		}
		//Traverse LL to find the index to insert at
		while(valueNotAddedYet && nodeptr.hasNextNode()) {
			if (i == indexCount + 1) {
				Node<Double> temp = nodeptr.getNext();
				nodeptr.setNext(new Node<Double>(value, temp));
				valueNotAddedYet = false;
			}
			else {
				nodeptr = nodeptr.getNext();
				indexCount++;
			}
		}
		//Adds to end of the LL if the while loop did not insert the value
		if (valueNotAddedYet) {
			nodeptr.setNext(new Node<Double>(value, null));
		}
		currentSizeOfTheList++;
	}

	/**
	 * Removes a value at a certain index
	 * Worst Case Run Time: O(n)
	 * @param i the index to be removed
	 */
	@Override
	public void remove(int i) {
		int indexCount = 0; 	         //keep track of the current index
		Node<Double> nodeptr = head;     //A pointer to traverse the NumLinkedList
		boolean indexWasNotRemoved = true;    //Keep track if the index was removed
		//If the index to be removed is the first
		if (i == 0) {
			head = nodeptr.getNext();
			indexWasNotRemoved = false;
			currentSizeOfTheList--;
		}
		while(nodeptr.hasNextNode() && indexWasNotRemoved) {
			if (i == indexCount + 1) {
				Node<Double> temp = nodeptr.getNext().getNext();
				nodeptr.setNext(temp);
				indexWasNotRemoved = false;
				currentSizeOfTheList--;
			}
			else {
				nodeptr = nodeptr.getNext();
				indexCount++;  
			}
		}
	}

	/**
	 * Look up a value in the list
	 * Worst Case Run Time: O(n)
	 * @param i the index to lookup
	 * @return the value at index i
	 */
	@Override
	public double lookup(int i) {
		int indexCount = 0; 	         //keep track of the current index
		Node<Double> nodeptr = head;     //A pointer to traverse the NumLinkedList
		while(indexCount < currentSizeOfTheList) {
			if (i == indexCount) {
				return nodeptr.getElement();
			}
			else {
				nodeptr = nodeptr.getNext();
				indexCount++;
			}
		}
		throw new IllegalArgumentException("Index does not exist");
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
	}
	
	/**
	 * A Node to be used in the NumLinkedList
	 * @author Joseph Volpe
	 * @param <T>
	 */
	public class Node<T> {

		/** Represents the element the node holds */
		private T element;
		
		/** Represents the next node that is this node points to*/
		private Node<T> nextNode;
		
		/**
		 * Constructor
		 * @param element the element the node will hold
		 * @param nextNode the next node this node will point to
		 */
		public Node(T element, Node<T> nextNode) {
			this.element = element;
			this.nextNode = nextNode;
		}
		
		/**
		 * Returns the element of the node
		 * Worst Case Run Time: O(1)
		 * @return the element of the node
		 */
		public T getElement() {
			return element;
		}
		
		/**
		 * Returns the next node
		 * Worst Case Run Time: O(1)
		 * @return the next node
		 */
		public Node<T> getNext() {
			return nextNode;
		}
		
		/**
		 * Returns a boolean about if there is a next node
		 * Worst Case Run Time: O(1)
		 * @return whether there is a next node
		 */
		public boolean hasNextNode() {
			return (nextNode != null);
		}
		
		/**
		 * Sets the element of the current node
		 * Worst Case Run Time: O(1)
		 * @param element the element to be set
		 */
		public void setElement(T element) {
			this.element = element;
		}
		
		/**
		 * Sets where the node that is being pointed to
		 * Worst Case Run Time: O(1)
		 * @param nextNode the node that is being pointed to
		 */
		public void setNext(Node<T> nextNode) {
			this.nextNode = nextNode;
		}
	}

}
