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
	 * @param sizeOfArray size of the array
	 * @return time in milliseconds to sort
	 */
	public static float testSortedHeapSort(int sizeOfArray) {
		int[] testArray = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			testArray[i] = i;
		}
		return Sorting.heapSort(testArray);
	}
	
	/**
	 * Test quicksort's timing for an already sorted array
	 * @param sizeOfArray size of an array
	 * @return time in milliseconds to sort
	 */
	public static float testSortedQuickSort(int sizeOfArray) {
		int[] testArray = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			testArray[i] = i;
		}
		return Sorting.quickSort(testArray);
	}
	
	/**
	 * Test mergesort's timing for an already sorted array
	 * @param sizeOfArray size of an array
	 * @return time in milliseconds to sort
	 */
	public static float testSortedMergeSort(int sizeOfArray) {
		int[] testArray = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			testArray[i] = i;
		}
		return Sorting.mergeSort(testArray);
	}
	
	/**
	 * Test heapsort's timing for a reverse sorted array
	 * @param sizeOfArray size of an array
	 * @return time in milliseconds to sort
	 */
	public static float testReverseHeapSort(int sizeOfArray) {
		int[] testArray = new int[sizeOfArray];
		for (int i = sizeOfArray - 1; i >= 0; i--) {
			testArray[(sizeOfArray - 1) - i] = i;
		}
		return Sorting.heapSort(testArray);
	}
	
	/**
	 * Test quicksort's timing for a reverse sorted array
	 * @param sizeOfArray size of an array
	 * @return time in milliseconds to sort
	 */
	public static float testReverseQuickSort(int sizeOfArray) {
		int[] testArray = new int[sizeOfArray];
		for (int i = sizeOfArray - 1; i >= 0; i--) {
			testArray[(sizeOfArray - 1) - i] = i;
		}
		return Sorting.quickSort(testArray);
	}
	
	/**
	 * Test mergesort's timing for a reverse sorted array
	 * @param sizeOfArray size of an array
	 * @return time in milliseconds to sort
	 */
	public static float testReverseMergeSort(int sizeOfArray) {
		int[] testArray = new int[sizeOfArray];
		for (int i = sizeOfArray - 1; i >= 0; i--) {
			testArray[(sizeOfArray - 1) - i] = i;
		}
		return Sorting.mergeSort(testArray);
	}
	
	/**
	 * Test heapsort's timing for a randomly sorted array
	 * @param sizeOfArray size of an array
	 * @param seed the seed number of the number generator
	 * @return time in milliseconds to sort
	 */
	public static float testRandomHeapSort(int sizeOfArray, int seed) {
		Random rand = new Random(seed);
		int[] testArray = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			testArray[i] = rand.nextInt();
		}
		return Sorting.heapSort(testArray);
	}
	
	/**
	 * Test quicksort's timing for a random sorted array
	 * @param sizeOfArray size of an array
	 * @param seed the seed number of the number generator
	 * @return time in milliseconds to sort
	 */
	public static float testRandomQuickSort(int sizeOfArray, int seed) {
		Random rand = new Random(seed);
		int[] testArray = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			testArray[i] = rand.nextInt();
		}
		return Sorting.quickSort(testArray);
	}
	
	/**
	 * Test mergesort's timing for a randomly sorted array
	 * @param sizeOfArray size of an array
	 * @param seed the seed number of the number generator
	 * @return time in milliseconds to sort
	 */
	public static float testRandomMergeSort(int sizeOfArray, int seed) {
		Random rand = new Random(seed);
		int[] testArray = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			testArray[i] = rand.nextInt();
		}
		return Sorting.mergeSort(testArray);
	}
	
	/**
	 * The median time of three values in an array using a beginning index
	 * @param arr the array to get the values from
	 * @param index the first index of a total of 3 values to find the median for
	 * @return the median value
	 */
	public static int medianTime(float[] arr, int index) {
		return Sorting.median((int)arr[index], (int)arr[index+ 1], 
				(int)arr[index + 2]);
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
		/**
		 * Test sorted in order arrays
		 */
		float[] sortedHeapSortResults = new float[12];
		int index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 3; i++, index++)
				sortedHeapSortResults[index] = testSortedHeapSort(arraySize);
		}
		float[] sortedQuickSortResults = new float[12];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 3; i++, index++) 
				sortedQuickSortResults[index] = testSortedQuickSort(arraySize);
		}
		float[] sortedMergeSortResults = new float[12];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 3; i++, index++) 
				sortedMergeSortResults[index] = testSortedMergeSort(arraySize);
		}
		/**
		 * Test sorted in reverse order arrays
		 */
		float[] reverseHeapSortResults = new float[12];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 3; i++, index++) 
				reverseHeapSortResults[index] = testReverseHeapSort(arraySize);
		}
		float[] reverseQuickSortResults = new float[12];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 3; i++, index++) 
				reverseQuickSortResults[index] = testReverseQuickSort(arraySize);
		}
		float[] reverseMergeSortResults = new float[12];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 3; i++, index++) 
				reverseMergeSortResults[index] = testReverseMergeSort(arraySize);
		}
		/**
		 * Test randomly generated arrays
		 */
		int seed = 0;
		float[] randomHeapSortResults = new float[40];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 10; i++, index++, seed++) 
				randomHeapSortResults[index] = testRandomHeapSort(arraySize, seed);
		}
		float[] randomQuickSortResults = new float[40];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 10; i++, index++, seed++) {
				randomQuickSortResults[index] 
						= testRandomQuickSort(arraySize, seed);
			}
		}
		float[] randomMergeSortResults = new float[40];
		index = 0;
		for (int arraySize = 1000; arraySize <= 1000000; arraySize = arraySize*10) {
			for (int i = 0; i < 10; i++, index++, seed++) {
				randomMergeSortResults[index] 
						= testRandomMergeSort(arraySize, seed);
			}
		}
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
						medianTime(sortedHeapSortResults, 0) + "ms" + '\n');
			writer.write("HeapSort  - Sorted Array  - 10000ints:     " + 
					medianTime(sortedHeapSortResults, 3) + "ms" + '\n');
			writer.write("HeapSort  - Sorted Array  - 100000ints:    " + 
					medianTime(sortedHeapSortResults, 6) + "ms" + '\n');
			writer.write("HeapSort  - Sorted Array  - 1000000ints:  " + 
					medianTime(sortedHeapSortResults, 9) + "ms" + '\n');
			writer.write("QuickSort - Sorted Array  - 1000ints:      " + 
					medianTime(sortedQuickSortResults, 0) + "ms" + '\n');
			writer.write("QuickSort - Sorted Array  - 10000ints:     " + 
					medianTime(sortedQuickSortResults, 3) + "ms" + '\n');
			writer.write("QuickSort - Sorted Array  - 100000ints:    " + 
					medianTime(sortedQuickSortResults, 6) + "ms" + '\n');
			writer.write("QuickSort - Sorted Array  - 1000000ints:  " + 
					medianTime(sortedQuickSortResults, 9) + "ms" + '\n');
			writer.write("MergeSort - Sorted Array  - 1000ints:      " + 
					medianTime(sortedMergeSortResults, 0) + "ms" + '\n');
			writer.write("MergeSort - Sorted Array  - 10000ints:     " + 
					medianTime(sortedMergeSortResults, 3) + "ms" + '\n');
			writer.write("MergeSort - Sorted Array  - 100000ints:    " + 
					medianTime(sortedMergeSortResults, 6) + "ms" + '\n');
			writer.write("MergeSort - Sorted Array  - 1000000ints:  " + 
					medianTime(sortedMergeSortResults, 9) + "ms" + '\n' + '\n');
			/**
			 * Writes reverse array results
			 */
			writer.write("HeapSort  - Reverse Array - 1000ints:      " + 
					medianTime(reverseHeapSortResults, 0) + "ms" + '\n');
			writer.write("HeapSort  - Reverse Array - 10000ints:     " + 
					medianTime(reverseHeapSortResults, 3) + "ms" + '\n');
			writer.write("HeapSort  - Reverse Array - 100000ints:    " + 
					medianTime(reverseHeapSortResults, 6) + "ms" + '\n');
			writer.write("HeapSort  - Reverse Array - 1000000ints:  " + 
					medianTime(reverseHeapSortResults, 9) + "ms" + '\n');
			writer.write("QuickSort - Reverse Array - 1000ints:      " + 
					medianTime(reverseQuickSortResults, 0) + "ms" +  '\n');
			writer.write("QuickSort - Reverse Array - 10000ints:     " + 
					medianTime(reverseQuickSortResults, 3) + "ms" + '\n');
			writer.write("QuickSort - Reverse Array - 100000ints:    " + 
					medianTime(reverseQuickSortResults, 6) + "ms" + '\n');
			writer.write("QuickSort - Reverse Array - 1000000ints:  " + 
					medianTime(reverseQuickSortResults, 9) + "ms" + '\n');
			writer.write("MergeSort - Reverse Array - 1000ints:      " + 
					medianTime(reverseMergeSortResults, 0) + "ms" + '\n');
			writer.write("MergeSort - Reverse Array - 10000ints:     " + 
					medianTime(reverseMergeSortResults, 3) + "ms" + '\n');
			writer.write("MergeSort - Reverse Array - 100000ints:    " + 
					medianTime(reverseMergeSortResults, 6) + "ms" + '\n');
			writer.write("MergeSort - Reverse Array - 1000000ints:  " + 
					medianTime(reverseMergeSortResults, 9) + "ms" + '\n' + '\n');
			/**
			 * Writes random array average results and variances
			 */
			writer.write("HeapSort  - Random Array  - 1000ints:     " + 
					averageTime(randomHeapSortResults, 0) + "ms" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 1000ints:    " + 
					variance(randomHeapSortResults, 0) + "ms" + '\n');
			writer.write("HeapSort  - Random Array  - 10000ints:    " + 
					averageTime(randomHeapSortResults, 10) + "ms" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 10000ints:   " + 
					variance(randomHeapSortResults, 10) + "ms" + '\n');
			writer.write("HeapSort  - Random Array  - 100000ints:   " + 
					averageTime(randomHeapSortResults, 20) + "ms" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 100000ints:  " + 
					variance(randomHeapSortResults, 20) + "ms" + '\n');
			writer.write("HeapSort  - Random Array  - 1000000ints:  " + 
					averageTime(randomHeapSortResults, 30) + "ms" + '\n');
			writer.write("HeapSort  - Random Array  - Variance for 1000000ints: " + 
					variance(randomHeapSortResults, 30) + "ms" + '\n');
			writer.write("QuickSort - Random Array  - 1000ints:     " + 
					averageTime(randomQuickSortResults, 0) + "ms" +  '\n');
			writer.write("QuickSort - Random Array  - Variance for 1000ints:    " + 
					variance(randomQuickSortResults, 0) + "ms" +  '\n');
			writer.write("QuickSort - Random Array  - 10000ints:    " + 
					averageTime(randomQuickSortResults, 10) + "ms" + '\n');
			writer.write("QuickSort - Random Array  - Variance for 10000ints:   " + 
					variance(randomQuickSortResults, 10) + "ms" +  '\n');
			writer.write("QuickSort - Random Array  - 100000ints:   " + 
					averageTime(randomQuickSortResults, 20) + "ms" + '\n');
			writer.write("QuickSort - Random Array  - Variance for 100000ints:  " + 
					variance(randomQuickSortResults, 20) + "ms" +  '\n');
			writer.write("QuickSort - Random Array  - 1000000ints:  " + 
					averageTime(randomQuickSortResults, 30) + "ms" + '\n');
			writer.write("QuickSort - Random Array  - Variance for 1000000ints: " + 
					variance(randomQuickSortResults, 30) + "ms" +  '\n');
			writer.write("MergeSort - Random Array  - 1000ints:     " + 
					averageTime(randomMergeSortResults, 0) + "ms" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 1000ints:    " + 
					variance(randomMergeSortResults, 0) + "ms" + '\n');
			writer.write("MergeSort - Random Array  - 10000ints:    " + 
					averageTime(randomMergeSortResults, 10) + "ms" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 10000ints:   " + 
					variance(randomMergeSortResults, 10) + "ms" + '\n');
			writer.write("MergeSort - Random Array  - 100000ints:   " + 
					averageTime(randomMergeSortResults, 20) + "ms" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 100000ints:  " + 
					variance(randomMergeSortResults, 20) + "ms" + '\n');
			writer.write("MergeSort - Random Array  - 1000000ints:  " + 
					averageTime(randomMergeSortResults, 30) + "ms" + '\n');
			writer.write("MergeSort - Random Array  - Variance for 1000000ints: " + 
					variance(randomMergeSortResults, 30) + "ms" + '\n' + '\n');
			
			writer.close();
		}
		catch (IOException e) {
		}
		
	}
}
