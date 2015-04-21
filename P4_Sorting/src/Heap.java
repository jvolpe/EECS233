/**
 * Max on top Heap to be used with HeapSort
 * @author josephvolpe
 *
 */
public class Heap {
	
	/** Array to represent the heap */
	private int[] heap;
	
	/** Current number of items in the heap */
	private int numItems;
	
	/**
	 * Create heap with input array
	 * @param arr array to create heap with
	 */
	public Heap(int[] arr) {
		heap = arr;
		numItems = arr.length;
	}
	
	/**
	 * Build a max on top heap
	 */
	public void buildMaxOnTopHeap() {
		for (int i = numItems / 2; i >= 0; i--) //for all leaf nodes to the root
			maxHeapify(i);					 
	}
	
	/**
	 * Sifts down any node where the children are greater to be able to 
	 * keep the principle of a max on top heap
	 * @param i the index at the node that should be sifted down
	 */
	public void maxHeapify(int i) {
		int toSift = heap[i];                   //get value at index
		int parent = i;						    //index of parent
		int child = (2 * parent) + 1;	        //index of left child
		while (child < numItems) {
			if ((child + 1 < numItems) && (heap[child] < heap[child + 1]))
				child = child + 1;              //makes sure to use greater child
			if (heap[parent] < heap[child]) {   //swap child with parent if need be
				heap[parent] = heap[child];
				heap[child] = toSift;
				parent = child;
				child = parent * 2 + 1;
			}
			else  							    //break if the parent is greatest
				break;
		}
		heap[parent] = toSift;				    //put the element in the right place
	}
	
	/**
	 * Return max and remove it from the heap
	 * @return max
	 */
	public int removeMax() {
		int max = heap[0];              
		heap[0] = heap[numItems - 1];           //switch last node to first
		numItems--; 							
		maxHeapify(0);					        //heapify now that its out of order
		return max;
	}
}
