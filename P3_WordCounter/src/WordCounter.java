import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Track and count the number of words in an inputted text file using
 * a Hashtable and write each word and their respective frequency into an output
 * file
 *
 * @author Joseph Volpe
 */
public class WordCounter {
	
	/**
	 * Count the number of words in an inputted file and print out into
	 * an inputted output file the
	 * @param input_file
	 * @param output_file
	 * @return
	 */
	public static String wordCount(String input_file, String output_file) {
		try {
			HashTable wordTable = new HashTable();
			File inputFile = new File(input_file);
			File outputFile = new File(output_file);
			scanFile(inputFile, wordTable);
			String okFileSuccess = writeToOutput(outputFile, wordTable);
			return okFileSuccess;
		}
		catch (FileNotFoundException e) {
			return "Input File Error";
		}
		catch (IOException e) {
			return "Input File Error";
		}
	}
	
	/**
	 * Scan an inputted file and update a HashTable with each word
	 * @param inputFile file to scan
	 * @param table HashTable to update
	 * @throws FileNotFoundException
	 */
	public static void scanFile(File input, HashTable table) 
			throws FileNotFoundException {
		Scanner scanner = new Scanner(input).useDelimiter("[.,:;()?!'\"\\s]+");
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			for (String word : line.split("[^a-zA-Z]")) {
				word = word.toLowerCase();
				if (!word.equals(""))
					table.insert(word);
			}
		}
		scanner.close();
	}
	
	/**
	 * Write to an inputted file the number of words and the respective frequencies.
	 * @param outputFile File to write to
	 * @param table HashTable to write the words form
	 * @return String with details about the HashTable's words, size, collisions
	 * @throws IOException
	 */
	public static String writeToOutput(File outputFile, HashTable table) 
			throws IOException {
		int wordTableSize = table.getHashTableSize(); 
		int lengthOfCollisionLists = 0;                //keep track of collisions
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		for (int i = 0; i < wordTableSize; i++) {      // For each bucket write word
			if (table.getBucket(i) != null) {
				LinkedList bucket = table.getBucket(i);
				WordNode currentNode = bucket.getHead();
				while (currentNode != null) {
					writer.write("(" + currentNode.getWord() + " " 
							+ currentNode.getFrequency() + ")" + " ");
					currentNode = currentNode.getNext();
				}
				//increases total length
				lengthOfCollisionLists += bucket.getLength(); 
			}
		}
		writer.close();
		// divide by total number of buckets for average
		lengthOfCollisionLists = lengthOfCollisionLists / wordTableSize;
		String okString = String.format("OK; Total words: %d, Hash table size:"
				+ " %d, Average length of collision lists: %d", table.getNumWords(), 
				wordTableSize, lengthOfCollisionLists);
		return okString;
	}
	
	/**
	 * Main method to call wordCount
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 2) {
			String inputFile = args[0];
			String outputFile = args[1];
			String okString = WordCounter.wordCount(inputFile, outputFile);
			System.out.println(okString);
		}
	}
}
