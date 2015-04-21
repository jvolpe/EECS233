import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 * Test and research on the sorting algorithms in the Sorting class
 * @author josephvolpe
 *
 */
public class Reporting1 {
	
	/**
	 * Test heapsort's timing for an already sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testSortedHeapSort() {
		float[] times = new float[12];                 //array to hold times
		for (int arraySize = 1000, index = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {		       //test different size arrays
			for (int i = 0; i < 3; i++, index++) {     //3 tests for each size
				int[] testArray = fillSortedArray(arraySize); //fill array
				times[index] = Sorting.heapSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Test quicksort's timing for an already sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testSortedQuickSort() {
		float[] times = new float[12];                 //array to hold times
		for (int arraySize = 1000, index = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {		       //test different size arrays
			for (int i = 0; i < 3; i++, index++) {     //3 tests for each size
				int[] testArray = fillSortedArray(arraySize); //fill array
				times[index] = Sorting.quickSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Test mergesort's timing for an already sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testSortedMergeSort() {
		float[] times = new float[12];                 //array to hold times
		for (int arraySize = 1000, index = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {		       //test different size arrays
			for (int i = 0; i < 3; i++, index++) {     //3 tests for each size
				int[] testArray = fillSortedArray(arraySize); //fill array
				times[index] = Sorting.mergeSort(testArray);
			}
		}
		return times;
	}
	
	
	/**
	 * Test heapsort's timing for a reverse sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testReverseHeapSort() {
		float[] times = new float[12];                 //array to hold times
		for (int arraySize = 1000, index = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {			   //test different size arrays
			for (int i = 0; i < 3; i++, index++) {     //3 tests for each size
				int[] testArray = fillReverseArray(arraySize); //fill array
				times[index] = Sorting.heapSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Test quicksort's timing for a reverse sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testReverseQuickSort() {
		float[] times = new float[12];                 //array to hold times
		for (int arraySize = 1000, index = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {			   //test different size arrays
			for (int i = 0; i < 3; i++, index++) {     //3 tests for each size
				int[] testArray = fillReverseArray(arraySize); //fill array
				times[index] = Sorting.quickSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Test mergesort's timing for a reverse sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testReverseMergeSort() {
		float[] times = new float[12];                 //array to hold times
		for (int arraySize = 1000, index = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {			   //test different size arrays
			for (int i = 0; i < 3; i++, index++) {     //3 tests for each size
				int[] testArray = fillReverseArray(arraySize); //fill array
				times[index] = Sorting.mergeSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Test heapsort's timing for a randomly sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testRandomHeapSort() {
		float[] times = new float[40];					//array to hold times	
		for (int arraySize = 1000, index = 0, seed = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {				//test different size arrays
			for (int i = 0; i < 10; i++, index++, seed++) {
				//fill the array with different numbers each time by using a 
				//different seed number each time
				int[] testArray = fillRandomArray(arraySize, seed);
				times[index] = Sorting.heapSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Test quicksort's timing for a random sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testRandomQuickSort() {
		float[] times = new float[40];					//array to hold times	
		for (int arraySize = 1000, index = 0, seed = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {				//test different size arrays
			for (int i = 0; i < 10; i++, index++, seed++) {
				//fill the array with different numbers each time by using a 
				//different seed number each time
				int[] testArray = fillRandomArray(arraySize, seed);
				times[index] = Sorting.quickSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Test mergesort's timing for a randomly sorted array
	 * @return array of times in nanoseconds to sort
	 */
	public static float[] testRandomMergeSort() {
		float[] times = new float[40];					//array to hold times	
		for (int arraySize = 1000, index = 0, seed = 0; arraySize <= 1000000; 
				arraySize = arraySize*10) {				//test different size arrays
			for (int i = 0; i < 10; i++, index++, seed++) {
				//fill the array with different numbers each time by using a 
				//different seed number each time
				int[] testArray = fillRandomArray(arraySize, seed);
				times[index] = Sorting.mergeSort(testArray);
			}
		}
		return times;
	}
	
	/**
	 * Fills an array in sorted order to be used to test
	 * @param arraySize the size of array to be created
	 * @return the sorted test array
	 */
	public static int[] fillSortedArray(int arraySize) {
		int[] testArray = new int[arraySize];
		for (int j = 0; j < arraySize; j++) {
			testArray[j] = j;
		}
		return testArray;
	}
	
	/**
	 * Fill an array in reverse order to be used to test
	 * @param arraySize the size of array to be created
	 * @return the reverse order array
	 */
	public static int[] fillReverseArray(int arraySize) {
		int[] testArray = new int[arraySize];
		for (int j = arraySize - 1; j >= 0; j--) {
			testArray[(arraySize - 1) - j] = j; 
		}
		return testArray;
	}
	
	/**
	 * Fill an array in random order to be used to test
	 * @param arraySize the size of array to be created
	 * @return the random array
	 */
	public static int[] fillRandomArray(int arraySize, int seed) {
		Random rand = new Random(seed);
		int[] testArray = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			testArray[i] = rand.nextInt();
		}
		return testArray;
	}
	
	/**
	 * The median time of three values in an array using a beginning index
	 * @param arr the array to get the values from
	 * @param index the first index of a total of 3 values to find the median for
	 * @return the median value
	 */
	public static float medianTime(float[] arr, int index) {
		float i = arr[index];
		float j = arr[index+ 1];
		float k = arr[index + 2];
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
	 * The average time of ten times from an array
	 * @param arr the array to get the average from
	 * @param index the index for the first of ten values
	 * @return the average
	 */
	public static float averageTime(float[] arr, int index) {
		float total = 0f;
		for (int i = index; i < index + 10 && i < arr.length; i++) {
			total += arr[i];
		}
		return total / arr.length;
	}
	
	/**
	 * Get the variance for ten values from an array
	 * @param arr the array holding the values to get the variance for
	 * @param index the index of the first of ten values
	 * @return the variance
	 */
	public static float variance(float[] arr, int index) {
		float mean = averageTime(arr, index);
		float numerator = 0f;
		for (int i = index; i < index + 10; i++) {
			numerator += ((arr[i] - mean) * (arr[i] - mean));
		}
		return numerator / arr.length;
	}
	
	/**
	 * Main method to do the tests
	 * @param args
	 */
	public static void main(String[] args) {
		//Test sorted in order arrays
		float[] sortedHeapSortResults = testSortedHeapSort();
		float[] sortedQuickSortResults = testSortedQuickSort();
		float[] sortedMergeSortResults = testSortedMergeSort();
		//Test sorted in reverse order arrays
		float[] reverseHeapSortResults = testReverseHeapSort();
		float[] reverseQuickSortResults = testReverseQuickSort();
		float[] reverseMergeSortResults = testReverseMergeSort();
		//Test randomly generated arrays
		float[] randomHeapSortResults = testRandomHeapSort();
		float[] randomQuickSortResults = testRandomQuickSort();
		float[] randomMergeSortResults = testRandomMergeSort();
		/**
		 * Write results to file
		 */
		try {
			File recordings = new File("recordings.txt");
			recordings.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(recordings));
			/**
			 * Writes sorted array results
			 */
			writer.write("HeapSort  - Sorted Array  - 1000ints:      " + 
						medianTime(sortedHeapSortResults, 0) + "ns" + '\n');
			writer.write("HeapSort  - Sorted Array  - 10000ints:     " + 
					medianTime(sortedHeapSortResults, 3) + "ns" + '\n');
			writer.write("HeapSort  - Sorted Array  - 100000ints:    " + 
					medianTime(sortedHeapSortResults, 6) + "ns" + '\n');
			writer.write("HeapSort  - Sorted Array  - 1000000ints:  " + 
					medianTime(sortedHeapSortResults, 9) + "ns" + '\n');
			writer.write("QuickSort - Sorted Array  - 1000ints:      " + 
					medianTime(sortedQuickSortResults, 0) + "ns" + '\n');
			writer.write("QuickSort - Sorted Array  - 10000ints:     " + 
					medianTime(sortedQuickSortResults, 3) + "ns" + '\n');
			writer.write("QuickSort - Sorted Array  - 100000ints:    " + 
					medianTime(sortedQuickSortResults, 6) + "ns" + '\n');
			writer.write("QuickSort - Sorted Array  - 1000000ints:  " + 
					medianTime(sortedQuickSortResults, 9) + "ns" + '\n');
			writer.write("MergeSort - Sorted Array  - 1000ints:      " + 
					medianTime(sortedMergeSortResults, 0) + "ns" + '\n');
			writer.write("MergeSort - Sorted Array  - 10000ints:     " + 
					medianTime(sortedMergeSortResults, 3) + "ns" + '\n');
			writer.write("MergeSort - Sorted Array  - 100000ints:    " + 
					medianTime(sortedMergeSortResults, 6) + "ns" + '\n');
			writer.write("MergeSort - Sorted Array  - 1000000ints:  " + 
					medianTime(sortedMergeSortResults, 9) + "ns" + '\n' + '\n');
			/**
			 * Writes reverse array results
			 */
			writer.write("HeapSort  - Reverse Array - 1000ints:      " + 
					medianTime(reverseHeapSortResults, 0) + "ns" + '\n');
			writer.write("HeapSort  - Reverse Array - 10000ints:     " + 
					medianTime(reverseHeapSortResults, 3) + "ns" + '\n');
			writer.write("HeapSort  - Reverse Array - 100000ints:    " + 
					medianTime(reverseHeapSortResults, 6) + "ns" + '\n');
			writer.write("HeapSort  - Reverse Array - 1000000ints:  " + 
					medianTime(reverseHeapSortResults, 9) + "ns" + '\n');
			writer.write("QuickSort - Reverse Array - 1000ints:      " + 
					medianTime(reverseQuickSortResults, 0) + "ns" +  '\n');
			writer.write("QuickSort - Reverse Array - 10000ints:     " + 
					medianTime(reverseQuickSortResults, 3) + "ns" + '\n');
			writer.write("QuickSort - Reverse Array - 100000ints:    " + 
					medianTime(reverseQuickSortResults, 6) + "ns" + '\n');
			writer.write("QuickSort - Reverse Array - 1000000ints:  " + 
					medianTime(reverseQuickSortResults, 9) + "ns" + '\n');
			writer.write("MergeSort - Reverse Array - 1000ints:      " + 
					medianTime(reverseMergeSortResults, 0) + "ns" + '\n');
			writer.write("MergeSort - Reverse Array - 10000ints:     " + 
					medianTime(reverseMergeSortResults, 3) + "ns" + '\n');
			writer.write("MergeSort - Reverse Array - 100000ints:    " + 
					medianTime(reverseMergeSortResults, 6) + "ns" + '\n');
			writer.write("MergeSort - Reverse Array - 1000000ints:  " + 
					medianTime(reverseMergeSortResults, 9) + "ns" + '\n' + '\n');
			/**
			 * Writes random array average results and variances
			 */
			writer.write("HeapSort  - Random Array  - 1000ints:     " + 
					averageTime(randomHeapSortResults, 0) + "ns" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 1000ints:    " + 
					variance(randomHeapSortResults, 0) + "ns" + '\n');
			writer.write("HeapSort  - Random Array  - 10000ints:    " + 
					averageTime(randomHeapSortResults, 10) + "ns" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 10000ints:   " + 
					variance(randomHeapSortResults, 10) + "ns" + '\n');
			writer.write("HeapSort  - Random Array  - 100000ints:   " + 
					averageTime(randomHeapSortResults, 20) + "ns" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 100000ints:  " + 
					variance(randomHeapSortResults, 20) + "ns" + '\n');
			writer.write("HeapSort  - Random Array  - 1000000ints:  " + 
					averageTime(randomHeapSortResults, 30) + "ns" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 1000000ints: " + 
					variance(randomHeapSortResults, 30) + "ns" + '\n');
			writer.write("QuickSort - Random Array  - 1000ints:     " + 
					averageTime(randomQuickSortResults, 0) + "ns" +  '\n');
			writer.write("QuickSort - Random Array  - Variance for 1000ints:    " + 
					variance(randomQuickSortResults, 0) + "ns" +  '\n');
			writer.write("QuickSort - Random Array  - 10000ints:    " + 
					averageTime(randomQuickSortResults, 10) + "ns" + '\n');
			writer.write("QuickSort - Random Array  - Variance for 10000ints:   " + 
					variance(randomQuickSortResults, 10) + "ns" +  '\n');
			writer.write("QuickSort - Random Array  - 100000ints:   " + 
					averageTime(randomQuickSortResults, 20) + "ns" + '\n');
			writer.write("QuickSort - Random Array  - Variance for 100000ints:  " + 
					variance(randomQuickSortResults, 20) + "ns" +  '\n');
			writer.write("QuickSort - Random Array  - 1000000ints:  " + 
					averageTime(randomQuickSortResults, 30) + "ns" + '\n');
			writer.write("QuickSort - Random Array  - Variance for 1000000ints: " + 
					variance(randomQuickSortResults, 30) + "ns" +  '\n');
			writer.write("MergeSort - Random Array  - 1000ints:     " + 
					averageTime(randomMergeSortResults, 0) + "ns" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 1000ints:    " + 
					variance(randomMergeSortResults, 0) + "ns" + '\n');
			writer.write("MergeSort - Random Array  - 10000ints:    " + 
					averageTime(randomMergeSortResults, 10) + "ns" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 10000ints:   " + 
					variance(randomMergeSortResults, 10) + "ns" + '\n');
			writer.write("MergeSort - Random Array  - 100000ints:   " + 
					averageTime(randomMergeSortResults, 20) + "ns" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 100000ints:  " + 
					variance(randomMergeSortResults, 20) + "ns" + '\n');
			writer.write("MergeSort - Random Array  - 1000000ints:  " + 
					averageTime(randomMergeSortResults, 30) + "ns" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 1000000ints: " + 
					variance(randomMergeSortResults, 30) + "ns" + '\n' + '\n');
			
			writer.close();
		}
		catch (IOException e) {
		}
		
	}
}
