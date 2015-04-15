
/**
 * A Node to be used in the chaining process to hold each word and the word's
 * respective frequency.
 *
 * @author Joseph Volpe
 */
public class WordNode {

	/** The word this node holds */
	private String word;

	/** The number of times the word is counted. */
	private int frequency;

	/** The next node in the list */
	private WordNode next = null;

	/**
	 * Create the node with an inputted word. Since the node is created, then 
	 * there has to be at least 1 occurrence. Therefore, default is set to 1.
	 * @param word
	 */
	public WordNode(String word) {
		this.word = word;
		frequency = 1;
	}
	
	/**
	 * Create the node with an inputted word and frequency
	 * @param word
	 * @param frequency
	 */
	public WordNode(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	
	/**
	 * Return word
	 * @return word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Return word frequency 
	 * @return word frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/** 
	 * Increments frequency by one because that would be the only reason to change
	 * the frequency.
	 */
	public void incrementFrequency() {
		frequency++;
	}
	
	/**
	 * Return next
	 * @return next
	 */
	public WordNode getNext() {
		return next;
	}

	/**
	 * Set next
	 * @param next next node to be set
	 */
	public void setNext(WordNode next) {
		this.next = next;
	}
}