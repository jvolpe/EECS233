import java.util.Date;
import java.util.Random;

/**
 * HeapSort, QuickSort, QuickSort
 * @author josephvolpe
 *
 */
public class Sorting {

	/**
	 * Sort an array using heapsort
	 * @param arr array to sort
	 * @return time to sort in milliseconds
	 */
	public static long heapSort(int[] arr) {
		long beginningTime = new Date().getTime();  //get current time
		Heap heap = new Heap(arr);                  
		heap.buildMaxOnTopHeap();
		for (int end = arr.length - 1; end >= 0; end--) {
			int num = heap.removeMax();
			arr[end] = num;
		}
		long endTime = new Date().getTime();        //get time after sorting
		return endTime - beginningTime;             //calculate difference in time
	}
	
	/**
	 * Sort an array using quicksort
	 * @param arr array to sort
	 * @return time to sort in milliseconds
	 */
	public static long quickSort(int[] arr) {
		long beginningTime = new Date().getTime();  //get current time 
		recursiveQuickSort(arr, 0, arr.length - 1);
		long endTime = new Date().getTime();        //get time after sorting
		return endTime - beginningTime;             //calculate difference in time
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
		int left = first - 1;             //left index
		int right = last + 1;             //right index
		while (left < right) {                    //always until return value
			do {
				left++;
			} while (arr[left] < pivot);
			do {
				right--;
			} while (arr[right] > pivot);
			if (left < right) 
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
	 * @return
	 */
	public static int median(int i, int j, int k) {
		if (i <= j) {
			if (k <= i)
				return i;
			else {
				if (j < k)
					return j;
				else 
					return k;
			}
		}
		else {
			if (k <= j)
				return j;
			else {
				if (i < k)
					return i;
				else 
					return k;
			}
		}
	}

	/**
	 * Sort an array using mergesort
	 * @param arr array to sort
	 * @return time to sort in milliseconds
	 */
	public static long mergeSort(int[] arr) {
		long beginningTime = new Date().getTime();
		int[] tempArray = new int[arr.length];
		//increase the size of each subarray by *2 for each loop iteration to 
		//continue merging until there is only the entire array left
		for (int subArraySize = 1; subArraySize < arr.length; subArraySize = 
				subArraySize * 2) {
			//For each subarray, merge with the next subarray.
			//Works in subarrays that have a power of 2, but will still sort any
			//leftover values that are not in a power of 2 subarray.
			for (int i = 0; i < arr.length - subArraySize; i += subArraySize * 2) {
				int left = i;
				int mid = i + subArraySize - 1;
				int end = Math.min(arr.length - 1, (subArraySize * 2) + i - 1);
				merge(arr, tempArray, left, mid, end);
			}
		}
		long endTime = new Date().getTime();
		return endTime - beginningTime;
	}
	
	/**
	 * Merge two subarrays in an array
	 * @param arr the array that has the two subarrays to merge
	 * @param temp the temp array to be used to help merge the array
	 * @param left the beginning index of the left subarray of mergeFrom
	 * @param mid the end index of the left subarray of mergeFrom
	 * @param end the last index of the right subarray of mergeFrom
	 */
	public static void merge(int[] arr, int[] temp, int left, 
			int mid, int end) {
		for (int k = left; k <= end; k++) {  //Copy from array to temp array
            temp[k] = arr[k]; 
        }
		int i = left;                        //i is beginning of left subarray
		int j = mid + 1;					 //j is beginning of right subarray
		for (int k = left; k <= end; k++) {  //for the length of both subarrays
			if (i > mid) {                   //if only right subarray is left
				arr[k] = temp[j];   
				j++;
			}
			else if (j > end) {				 //if only left subarray is left
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
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();
		int[] toni = new int[10000000];
		for (int i = 0; i < toni.length; i++) {
			int n = r.nextInt();
			if (n < 0)
				n = n*-1;
			n = n%1000000;
			toni[i] = n;
		}
		long time = mergeSort(toni);
		/**for (int i = 0; i < toni.length; i++) {
			System.out.println(toni[i]);
		}*/
		System.out.println("Time: " + time);
	}
}
