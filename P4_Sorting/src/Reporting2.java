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
	 * Read input file of ints
	 * @param inputFile the input file with an int on each line
	 * @return the array created by scanning the input file
	 */
	/*public static int[] readInputFile(String inputFile) throws FileNotFound{
		File input = new File(inputFile);
		input.createNewFile();
		Scanner scan = new Scanner(input);
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (scan.hasNextInt()) {
			list.add(scan.nextInt());
		}
		//return list.toArray(new Integer[list.size()]);
	}
	
	public static void main(String[] args) {
		String inputFile = args[0];
		//Integer[] array = readInputFile(inputFile);
		//Sorting.heapSort(array);
	}*/
}