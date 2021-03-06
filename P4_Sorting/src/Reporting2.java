import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Test and research for an input file of a random array
 * @author josephvolpe
 *
 */
public class Reporting2 {

	/**
	 * Converts an ArrayList<Integer> to a primitive int[] array
	 * @param list the ArrayList
	 * @return the primitive int[] array
	 */
	public static int[] convertList(ArrayList<Integer> list) {
		int[] intList = new int[list.size()];
		for (int i = 0; i < intList.length; i++)
			intList[i] = list.get(i);
		return intList;
	}
	
	/**
	 * Read an input file of ints to then create a 2D array that has 9 subarrays, 9
	 * arrays because the 3 sorting algorithm has to be performed three times.
	 * @param inputFile the input file with an int on each line
	 * @return the array created by scanning the input file
	 * @throws FileNotFoundException
	 */
	public static int[][] readInputFile(String inputFile) 
			throws FileNotFoundException{
		File input = new File(inputFile);
		Scanner scan = new Scanner(input);
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (scan.hasNextInt())               //while there are more ints
			list.add(scan.nextInt());			//add ints to ArrayList
		scan.close();
		int[][] testArrays = new int[9][];      //9 chosen to have 3 arrays for each
		for (int i = 0; i < 9; i++)             //sorting algorithm  
			testArrays[i] = convertList(list);
		return testArrays;
	}
	
	/**
	 * Write the sorted array to an output file for each algorithm
	 * @param int[][] the 2D arrays that hold the sorted arrays
	 */
	public static void writeOutputFile(int[][] array) {
		try {
			File hsTimes = new File("jlv38HS.txt");  //file for HS times
			hsTimes.createNewFile();
			File qsTimes = new File("jlv38QS.txt");  //file for QS times
			qsTimes.createNewFile();
			File msTimes = new File("jlv38MS.txt");  //file for MS times
			msTimes.createNewFile();
			BufferedWriter hsWriter = new BufferedWriter(new FileWriter(hsTimes));
			BufferedWriter qsWriter = new BufferedWriter(new FileWriter(qsTimes));
			BufferedWriter msWriter = new BufferedWriter(new FileWriter(msTimes));
			//Write to file the first array that HS sorted
			for (int i = 0; i < array[0].length; i++) { 
				hsWriter.write("" + array[0][i] + "\n");
			}
			//Write to file the first array quicksort sorted
			for (int i = 0; i < array[3].length; i++) {
				qsWriter.write("" + array[3][i] + "\n");
			}
			//Write to file the first array mergesort sorted
			for (int i = 0; i < array[6].length; i++) {
				msWriter.write("" + array[6][i] + "\n");
			}
			hsWriter.close();
			qsWriter.close();
			msWriter.close();
		}
		catch (IOException e) { //Do nothing if IOException, but there shouldn't be
		}						//because the files were created
	}
	
	/**
	 * Main method to read an array from an input file to sort and print median
	 * times it took to sort the arrays
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String inputFile = "/Users/josephvolpe/Downloads/test.txt";			//inputFile is first argument
			//create array that will have 9 times, 3 for each algorithm
			float[] times = new float[9]; 		
			int[][] array = readInputFile(inputFile);
			for (int i = 0; i < 9; i++) {      
				if (i < 3) {					//first 3 arrays use HeapSort
					times[i] = Sorting.heapSort(array[i]);
				}
				else if (i < 6) {				//Second 3 use QuickSort
					times[i] = Sorting.quickSort(array[i]);
				}
				else {							//Last 3 use MergeSort
					times[i] = Sorting.mergeSort(array[i]);
				}
			}
			writeOutputFile(array);
			//First three are HS times so find median of those three
			float hsMedianTime = Reporting1.median(times, 0);
			//Next three are QS times so find median of those
			float qsMedianTime = Reporting1.median(times, 3);
			//Final three are MS times so find median of those
			float msMedianTime = Reporting1.median(times, 6);
			//Print median times
			System.out.println("HSjlv38 = " + hsMedianTime + "ns; QSjlv38 = " 
					+ qsMedianTime + "ns; MSjlv38 = " +  msMedianTime + "ns");
		}
		catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}
}
