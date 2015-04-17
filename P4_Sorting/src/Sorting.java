import java.util.Random;
import java.util.Date;

/**
 * HeapSort, MergeSort, QuickSort
 * @author josephvolpe
 *
 */
public class Sorting {

	/**
	 * Sorts an array using heap sort
	 * @param arr Array to sort
	 * @return time to sort
	 */
	public static long heapSort(int[] arr) {
		long beginningTime = new Date().getTime();  //get current time
		Heap heap = new Heap(arr);                  
		heap.buildMaxOnTopHeap();
		for (int end = arr.length - 1; end >= 0; end--) {
			int num = heap.removeMax();
			arr[end] = num;
		}
		long endTime = new Date().getTime();        //get current time after sorting
		return endTime - beginningTime;             //calculate difference in time
	}
	
	//public static long mergeSort(int[] arr) {
		
	//}
	
	//public static long quickSort(int[] arr) {
		
	//}
	
	public static void main(String[] args) {
		Random r = new Random();
		int[] toni = new int[1000000];
		for (int i = 0; i < toni.length; i++) {
			int n = r.nextInt();
			if (n < 0)
				n = n*-1;
			n = n/10000;
			toni[i] = n;
		}
		long time = heapSort(toni);
		for (int i = 0; i < toni.length; i++) {
			System.out.println(toni[i]);
		}
		System.out.println("Time: " + time);
	}
}
