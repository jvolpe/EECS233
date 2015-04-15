/**
 * A HashTable to be used in WordCounter
 * 
 * @author Joseph Volpe
 */
public class HashTable {

	// HashTable size
	private int hashTableSize;

	//List of WordNodes to hold the counted words
	private LinkedList[] words;
	
	// Number of words in the HashTable
	private int numWords = 0;
	
	/**
	 * Generic constructor, 2 chosen as initial size to not collide with 31
	 */
	public HashTable() {
		hashTableSize = 13;
		words = new LinkedList[13];
	}
	
	/**
	 * Constructor for giving specific size, used with rehashing
	 * @param size
	 */
	public HashTable(int size) {
		hashTableSize = size;
		words = new LinkedList[size];	
	}
	
	/**
	 * The word to be hashed as a key using Java's String hashcode function
	 * @param word the word to be hashed
	 */
	public int hash(String word) {
		int hash = word.hashCode() % getHashTableSize();
		// if less than 0, then not a real index and therefore add table size
		if (hash < 0)                
			hash += getHashTableSize();
		return hash;
	}
	
	/** 
	 * Insert the word in the table. If already present, update frequency.
	 * @param word word to put in the table or update frequency
	 */
	public void insert(String word) {
		if (contains(word)) {             //if contains word then update freq.
			int index = hash(word);
			LinkedList list = words[index];
			WordNode nodeptr = list.getHead();
			boolean changed = false;
			//while there is more nodes and frequency has not changed yet
			while (nodeptr != null && !changed) {  
				if (word.equals(nodeptr.getWord())) {
					nodeptr.incrementFrequency();
					changed = true;
				}
				else
					nodeptr = nodeptr.getNext();
			}
		}
		else {						      //else add new node with word
			int index = hash(word);
			if (words[index] == null)     //create new list if this is first here
				words[index] = new LinkedList();
			LinkedList list = words[index];
			list.append(new WordNode(word));
			setNumWords(getNumWords() + 1);
			if (needsToResize())
				rehashAndResize();
		}
	}
	
	/**
	 * Check to see if the table currently holds the word
	 * @param word
	 */
	public boolean contains(String word) {
		int index = hash(word);
		LinkedList listToCheck = words[index];
		if (listToCheck == null)
			return false;
		else
			return listToCheck.contains(word);
	}
	
	/**
	 * Return whether the table needs resized. The following condition was chosen:
	 * Whether or not the load factor is greater than 4. Otherwise, this would 
	 * start to slow down the runtime it would take to insert one word greatly
	 * due to having a long buckets. 4 was chosen over 1 because having a load 
	 * factor of 1 would case chaining to be useless due to having an index for
	 * almost every spot. Having to iterate will take more time, but is still a 
	 * very small number in comparison.
	 * Not enough indices being used is not one of the criteria due to the size 
	 * of the table will only increase when there is not enough room, and 
	 * words will never be removed.
	 * @return whether the table needs resized
	 */
	public boolean needsToResize() {
		boolean needToResize = false;
		double loadfactor = ((double)getNumWords() / getHashTableSize());
		if (4 < loadfactor) 
			needToResize = true;
		return needToResize;
	}
	
	/**
	 * Rehash and resize the table but making the table twice its size and then copy
	 * over all of the words to the next list
	 */
	public void rehashAndResize() {
		int oldSize = getHashTableSize();
		HashTable newTable = new HashTable(oldSize * 2);  //New table is double size
		for (int i = 0; i < oldSize; i++) {               //for each bucket
			if (getBucket(i) != null) {
				LinkedList bucket = getBucket(i);
				WordNode currentNode = bucket.getHead();
				while (currentNode != null) {             //while more nodes 
					newTable.insert(currentNode);         //insert node
					currentNode = currentNode.getNext();
				}
			}
		}
		this.setArrayOfWords(newTable.getArrayOfWords()); //set this table to new one
		setHashTableSize(oldSize*2);                      //adjust size
	}
	
	/**
	 * Insert method for when the table is rehashed and resized to easily 
	 * copy over the entire WordNode with its word and its respective frequency 
	 * to the new array.
	 * @param node
	 */
	public void insert(WordNode node) {
		WordNode newNode = new WordNode(node.getWord(), node.getFrequency());
		String word = newNode.getWord();
		int index = hash(word);
		if (words[index] == null)     //create new list if this is first here
			words[index] = new LinkedList();
		LinkedList list = words[index];
		list.append(newNode);
	}
	
	/**
	 * Return the LinkedList "bucket" at a specified index
	 * @param index the index to get the bucket at
	 * @return LinkedList bucket
	 */
	public LinkedList getBucket(int index) {
		return words[index];
	}
	
	/**
	 * Return number of words
	 * @return number of words
	 */
	public int getNumWords() {
		return numWords;
	}

	/**
	 * Set number of words
	 * @param numWords to set
	 */
	public void setNumWords(int numWords) {
		this.numWords = numWords;
	}
	
	/**
	 * Return hashTableSize
	 * @return hashTableSize
	 */
	public int getHashTableSize() {
		return hashTableSize;
	}
	
	/**
	 * Set size
	 * @param size to set
	 */
	public void setHashTableSize(int size) {
		hashTableSize = size;
	}
	
	/**
	 * Return the array of words, used with rehashing
	 * @return the array of words
	 */
	public LinkedList[] getArrayOfWords() {
		return words;
	}
	
	/**
	 * Set the array of words with the inputted one, used with rehashing
	 * @param newArray new array of words
	 */
	public void setArrayOfWords(LinkedList[] newArray) {
		words = newArray;
	}
}