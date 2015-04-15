import java.util.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;;

/**
 * Compresses a file using Huffman's Encoding method
 * @author Joseph Volpe
 */
public class HuffmanCompressor {
	
	/** Array to keep track of all the characters */
	private Character[] charactersInText;
	
	/** Array to keep track of the corresponding frequencies of the characters */
	private int[] frequenciesOfCharacters;
	
	/** HashMap containing the characters and their respective encoded values. */
	private HashMap<Character, String> encodedCharacters = new HashMap<Character, String>();
	
	/**
	 * Read and compresses an inputted file into a specified output file
	 * @param inputFileName The input file to be compressed
	 * @param outputFileName The output file to compress to
	 * @return A message detailing if the encoding was a success
	 */
	public static String huffmanCoder(String inputFileName, String outputFileName) {
		try {
			HuffmanCompressor huffCompressor = new HuffmanCompressor();
			int spaceSaved = huffCompressor.writeEncodedCharactersToFile(inputFileName, outputFileName);
			File frequencyAndSavings = new File("frequencyTableAndSavings.txt");
			frequencyAndSavings.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(frequencyAndSavings));
			for (int i = 0; i < huffCompressor.frequenciesOfCharacters.length; i++) {
				System.out.println(huffCompressor.charactersInText[i] + ": " + huffCompressor.frequenciesOfCharacters[i] 
						+ ": " + huffCompressor.encodedCharacters.get(huffCompressor.charactersInText[i]));
				writer.write((huffCompressor.charactersInText[i] + ": " + huffCompressor.frequenciesOfCharacters[i] 
						+ ": " + huffCompressor.encodedCharacters.get(huffCompressor.charactersInText[i])));
			}
			System.out.println("Calculated amount of savings: " + spaceSaved);
			writer.write("Calculated amount of savings: " + spaceSaved);
			writer.close();
			return "OK";
		}
		catch (IOException e) {
			return "File Error";
		}
		catch (NullPointerException e) {
			return "File Error";
		}
	}
	
	/**
	 * Returns an ArrayList of HuffmanNodes. An ArrayList was chosen over a LinkedList due to
	 * an ArrayList being able to add new entries to the end of the list and traverse quickly. The ArrayList also
	 * can be sorted using the collections method sort(). However, this does compromise running time 
	 * due to the array will have to resize when more characters are found compared to the initial size. 
	 * @param inputFileName The file to be have its characters changed into HuffmanNodes
	 * @return              An ArrayList of HuffmanNodes
	 * @throws IOException
	 */
	public ArrayList<HuffmanNode> generateListOfNodes(String inputFileName) throws IOException {
		// ArrayList to hold nodes
		// 30 is chosen as the initial size due to 26 letters, space, period, comma, and quotations.
		ArrayList<HuffmanNode> listOfNodes = new ArrayList<HuffmanNode>(30);	
		// Table to hold characters and their respective frequencies while reading file
		Hashtable<Character, Integer> frequencyTable = new Hashtable<Character, Integer>();
		File file = new File(inputFileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int currentCharacterIntValue = reader.read();
			// Continue reading until the reader has read the entire file
			while(currentCharacterIntValue != -1) {
				Character currentCharacter = (char)currentCharacterIntValue;
				// updates character frequency if in table
				if (frequencyTable.containsKey(currentCharacter)) {
					int currentFrequency = frequencyTable.get(currentCharacter);
					currentFrequency++;
					frequencyTable.put(currentCharacter, currentFrequency);
				}
				// or adds character to the frequency table since it has not appeared yet
				else {
					HuffmanNode newNode = new HuffmanNode(currentCharacter);
					listOfNodes.add(newNode);
					frequencyTable.put(currentCharacter, 1);
				}
				currentCharacterIntValue = reader.read();
			}
			reader.close();
			// creates the fields since the number of characters is known now
			charactersInText = new Character[listOfNodes.size()];
			frequenciesOfCharacters = new int[listOfNodes.size()];
			int index = 0;   // index for going through the arrays charactersInText and frequenciesOfCharacters
			// updates the correct frequency of all the nodes
			for (HuffmanNode node : listOfNodes) {
				Character tempCharacter = node.getCharacterValue();
				int newFrequency = frequencyTable.get(tempCharacter);
				node.setFrequency(newFrequency);
				charactersInText[index] = tempCharacter;
				frequenciesOfCharacters[index] = newFrequency;
				index++;
			}
			// Sorts in order of less appeared characters to more
			Collections.sort(listOfNodes, new HuffmanNode().getComparator());
			return listOfNodes;
		}
		// returns null if the input file was not found
		catch (FileNotFoundException exception) {
			return null;
		}
	}

	/**
	 * Creates a binary tree using Huffman's Algorithm 
	 * @param listOfNodes The list of Nodes to use to build the tree
	 * @return 			  A binary tree created by Huffman's Algorithm
	 */
	public BinaryHuffmanTree createBinaryHuffmanTree(ArrayList<HuffmanNode> listOfNodes) {
		// while the inputted list has more than one 1 node, combine
		// two of them at a time to slowly build the tree
		while (listOfNodes.size() != 1) {
			HuffmanNode firstNode = listOfNodes.remove(0);
			HuffmanNode secondNode = listOfNodes.remove(0);
			HuffmanNode newNode = new HuffmanNode(firstNode.getFrequency() + secondNode.getFrequency());
			newNode.setLeftChild(firstNode);
			newNode.setRightChild(secondNode);
			listOfNodes.add(newNode);
		}
		// now that the list has only one node, the node will be the root
		HuffmanNode root = listOfNodes.get(0);
		return new BinaryHuffmanTree(root);
	}
	
	/**
	 * Traverse the tree to encode each character in each node
	 * @param tree the tree to traverse to look at each node's characters
	 */
	public void produceCharacterEncoding(BinaryHuffmanTree tree) {
		// traverses the tree starting with the String code ""
		traverseTree(tree.getRoot(), "");
	}
	
	/**
	 * Traverse a root's children and inserts all leaf nodes' character
	 * values as the keys and the traced code as the encoded values into the Hashmap encodedCharacters
	 * @param root The HuffmanNode to act as the root
	 * @param code The current encoded string value of a character
	 */
	public void traverseTree(HuffmanNode root, String code) {
		if (root != null) {
			// if leaf node, then the encoded character is the current code
			if (root.getLeftChild() == null && root.getRightChild() == null) {
				encodedCharacters.put(root.getCharacterValue(), code);
			}
			// if not a leaf node, continue traversing through the tree
			else {
				traverseTree(root.getLeftChild(), code + "0");
				traverseTree(root.getRightChild(), code + "1");
			}
		}
	}
	
	/**
	 * Reads an inputted file and writes the encoded values to an output file. 
	 * Returns the saved space from compressing the file.
	 * @param inputFileName
	 * @param outputFileName
	 * @return the saved space from compressing the file
	 * @throws IOException 
	 */
	public int writeEncodedCharactersToFile(String inputFileName, String outputFileName) throws IOException {
		// Does Huffman Encoding to fill the field variables with chars and frequencies
		ArrayList<HuffmanNode> listOfNodes = generateListOfNodes(inputFileName);
		BinaryHuffmanTree huffmanTree = createBinaryHuffmanTree(listOfNodes);
		produceCharacterEncoding(huffmanTree);
		// Reads the input file character by character to then write to the output file character by character
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		// creates output file if it does not exist
		if (!outputFile.exists())
			outputFile.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getAbsoluteFile()));
		int currentCharacterIntValue = reader.read();
		int bitsOriginallyUsed = 0; 				      // keeps track of the bits the input file uses
		int bitsCompressionUses = 0;				      // keeps track of the bits the compressed file uses
		// continue while there are characters in the input file are read
		while(currentCharacterIntValue != -1) {
			Character currentCharacter = (char)currentCharacterIntValue;  
			String encodedValue = encodedCharacters.get(currentCharacter);
			writer.write(encodedValue);
			bitsOriginallyUsed += 8;                       // adds 8 due to the character encoding being 8bits
			bitsCompressionUses += encodedValue.length();  // adds the length of the String due to each char representing a bit
			currentCharacterIntValue = reader.read();
		}
		reader.close();
		writer.close();
		// returns space saved
		return (bitsOriginallyUsed - bitsCompressionUses);             
	}
	
	/**
	 * A Binary Tree to hold HuffmanNodes to use for the Huffman encoding
	 * @author Joseph Volpe
	 */
	private class BinaryHuffmanTree {
		
		/** Root of the tree */
		HuffmanNode root = null;
		
		/** 
		 * Creates a binary tree with an inputted root
		 * @param newRoot the root for the tree
		 */
		public BinaryHuffmanTree(HuffmanNode newRoot) {
			root = newRoot;
		}
		
		/**
		 * Returns root of the tree
		 * @return root of the tree
		 */
		public HuffmanNode getRoot() {
			return root;
		}
	}	
	
	/**
	 * A Node to hold a character and its frequency
	 * @author Joseph Volpe
	 */
	public class HuffmanNode {
	
		/** The character the node holds; It is null if not a leaf node. */
		Character inChar = null;
		
		/**
		 * The frequency of all nodes in the subtree rooted from this node. 
		 * Leaf nodes hold the frequency of that character.
		 */
		int frequency = 0;
		
		/** This node's left child */
		HuffmanNode left = null;
		
		/** This node's right child */
		HuffmanNode right = null;
		
		/** Creates HuffmanNode with a null character and no frequency */
		public HuffmanNode() {
		}
		
		/**
		 * Creates a HuffmanNode with a null character and a set frequency
		 * @param newFrequency the frequency of the node
		 */
		public HuffmanNode(int newFrequency) {
			frequency = newFrequency;
		}
		
		/**
		 * Creates a HuffmanNode with a set char and 0 frequency
		 * @param charValue the character of the node
		 */
		public HuffmanNode(Character charValue) {
			inChar = charValue;
		}
		
		/**
		 * Sets new frequency
		 * @param newFrequency the new frequency to set
		 */
		public void setFrequency(int newFrequency) {
			frequency = newFrequency;
		}
		
		/**
		 * Sets left child
		 * @param leftChild to be set
		 */
		public void setLeftChild(HuffmanNode leftChild) {
			left = leftChild;
		}
		
		/**
		 * Sets right child
		 * @param rightChild to be set
		 */
		public void setRightChild(HuffmanNode rightChild) {
			right = rightChild;
		}
		
		/**
		 * Returns frequency
		 * @return frequency
		 */
		public int getFrequency() {
			return frequency;
		}
		
		/**
		 * Returns character value the node holds
		 * @return character value the node holds
		 */
		public Character getCharacterValue() {
			return inChar;
		}
		
		/**
		 * Returns left child
		 * @return left child
		 */
		public HuffmanNode getLeftChild() {
			return left;
		}
		
		/**
		 * Returns right child
		 * @return right child
		 */
		public HuffmanNode getRightChild() {
			return right;
		}
		
		/**
		 * Returns a comparator for comparing HuffmanNodes by frequency
		 * @return a comparator for comparing HuffmanNodes by frequency
		 */
		public Comparator<HuffmanNode> getComparator() {
			return new Comparator<HuffmanNode>() {
				public int compare(HuffmanNode nodeOne, HuffmanNode nodeTwo) {
					if (nodeOne.getFrequency() > nodeTwo.getFrequency()) {
						return 1;
					}
					else if (nodeOne.getFrequency() == nodeTwo.getFrequency()) {
						return 0;
					}
					else {
						return -1;
					}
				}
			};
		}
	}
	
	/**
	 * Main method for calling the method huffmanCoder to compress 
	 * a given inputted file to a given output file.
	 * @param args two arguments, the input file and output file
	 */
	public static void main(String[] args) {
		if (args.length == 2) {
			String inputFile = args[0];
			String outputFile = args[1];
			huffmanCoder(inputFile, outputFile);
		}
	}
}