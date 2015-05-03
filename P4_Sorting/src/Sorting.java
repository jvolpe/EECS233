/**
 * HeapSort, QuickSort, MergeSort
 * @author josephvolpe
 *
 */
public class Sorting {

	/**
	 * Sort an array using heapsort
	 * @param arr array to sort
	 * @return time to sort in nanoseconds
	 */
	public static long heapSort(int[] arr) {
		long beginningTime = System.nanoTime();     //get current time
		Heap heap = new Heap(arr);                  //create Heap with the array
		heap.buildMaxOnTopHeap();					
		for (int end = arr.length - 1; end >= 0; end--) {
			int num = heap.removeMax();
			arr[end] = num;
		}        
		return System.nanoTime() - beginningTime;   //calculate difference in time
	}
	
	/**
	 * Sort an array using quicksort
	 * @param arr array to sort
	 * @return time to sort in nanoseconds
	 */
	public static long quickSort(int[] arr) {
		long beginningTime = System.nanoTime();     //get current time 
		recursiveQuickSort(arr, 0, arr.length - 1);       
		return System.nanoTime() - beginningTime;   //calculate difference in time
	}
	
	/**
	 * Recursive method that quicksort calls to call itself over a subarray
	 * @param arr array to sort
	 * @param first the first index of the array to sort
	 * @param last the last index of the array to sort
	 */
	public static void recursiveQuickSort(int[] arr, int first, int last) {
		if (first < last) {
			int split = partition(arr, first, last); //partition array by pivot
			recursiveQuickSort(arr, first, split);   //call QS on the left subarray
			recursiveQuickSort(arr, split + 1, last);//call QS on the right subarray
		}
	}
	
	/**
	 * Partition an array by using a pivot to sort the elements in the left subarray
	 * to be <= pivot and sort elements in the right subarray to be >= pivot.
	 * @param arr array to sort
	 * @param first first index of the array to sort
	 * @param last last index of the array to sort
	 * @return the end of the left subarray
	 */
	public static int partition(int[] arr, int first, int last) {
		//find pivot by using three elements
		int pivot = median(arr[first], arr[last], arr[(first + last) / 2]);
		int left = first - 1;                       //left index
		int right = last + 1;                       //right index
		while (left < right) {                      
			do {
				left++;								//increase left index if
			} while (arr[left] < pivot);			//less than pivot
			do {
				right--;							//decrease right index if
			} while (arr[right] > pivot);			//greater than pivot
			if (left < right) 						//swap if out of order
				swap(arr, left, right);
		}
		return right;
	}
	
	/**
	 * Swap elements at two specified indexes in an array
	 * @param arr the array to swap the elements in
	 * @param i the first index of an element to swap
	 * @param j the second index of an element to swap
	 */
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Find the median of three int values
	 * @param i first int
	 * @param j second int
	 * @param k third int
	 * @return median value
	 */
	public static int median(int i, int j, int k) {
		if (i <= j) {
			if (k <= i)
				return i;
			else {
				return (j < k) ? j : k;
			}
		}
		else {
			if (k <= j)
				return j;
			else {
				return (i < k) ? i : k;
			}
		}
	}

	/**
	 * Sort an array using mergesort
	 * @param arr array to sort
	 * @return time to sort in nanoseconds
	 */
	public static long mergeSort(int[] arr) {
		long beginningTime = System.nanoTime();     //get current time
		int[] tempArray = new int[arr.length];
		//increase the size of each subarray starting at size 1 by *2 for each loop 
		//iteration to continue merging until there is only the entire array left
		for (int subArraySize = 1; subArraySize < arr.length; subArraySize = 
				subArraySize * 2) {
			//For each subarray, merge with the next subarray.
			//Works in subarrays that have a power of 2, but will still sort any
			//leftover values that are not in a subarray of power of 2.
			for (int i = 0; i < arr.length - subArraySize; i += subArraySize * 2) {
				int left = i;
				int mid = i + subArraySize - 1;
				int end = Math.min(arr.length - 1, (subArraySize * 2) + i - 1);
				merge(arr, tempArray, left, mid, end);
			}
		}
		return System.nanoTime() - beginningTime;
	}
	
	/**
	 * Merge two subarrays in an array
	 * @param arr the array that has the two subarrays to merge
	 * @param temp the temp array to be used to help merge the array
	 * @param left the beginning index of the left subarray of mergeFrom
	 * @param mid the end index of the left subarray of mergeFrom
	 * @param end the last index of the right subarray of mergeFrom
	 */
	public static void merge(int[] arr, int[] temp, int left, int mid, int end) {
		for (int k = left; k <= end; k++) {  //Copy from array to temp array
            temp[k] = arr[k]; 
        }
		int i = left;                        //i is beginning of left subarray
		int j = mid + 1;					 //j is beginning of right subarray
		for (int k = left; k <= end; k++) {  //for the length of both subarrays
			if (i > mid) {                   //if only right subarray has elements
				arr[k] = temp[j];   
				j++;
			}
			else if (j > end) {				 //if only left subarray has elements
				arr[k] = temp[i];
				i++;
			}
			else if (temp[i] < temp[j]) {    //if element at i is less
				arr[k] = temp[i];
				i++;
			}
			else {							 //if element at j is less
				arr[k] = temp[j];
				j++;
			}
		}
	}
	
}
