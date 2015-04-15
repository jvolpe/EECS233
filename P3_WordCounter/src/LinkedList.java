/**
 * A list of WordNodes to be used for the chaining process
 * 
 * @author Joseph Volpe
 */
public class LinkedList {

	/** Head of the list */
	private WordNode head = null;

	/** Length of list */
	private int length = 0;
	
	/**
	 * Append a new node to the end of the list
	 * @param newNode the node to append to end of the list
	 */
	public void append(WordNode newNode) {
		if (head == null) {	             //If head is null then set node as head 			
			setHead(newNode);
			setLength(getLength() + 1);
		}
		else {                          //Else traverse list and add to end
			WordNode nodeptr = head;
			while (nodeptr.getNext() != null) {
				nodeptr = nodeptr.getNext();
			}
			nodeptr.setNext(newNode);
			setLength(getLength() + 1);
		}
	}
	
	/**
	 * Check to see if the word is in the list
	 * @param word word to check
	 * @return boolean whether the word is in the list
	 */
	public boolean contains(String word) {
		boolean contained = false;
		WordNode nodeptr = getHead();
		while (nodeptr != null && !contained) {
			if (word.equalsIgnoreCase(nodeptr.getWord()))
				contained = true;
			else 
				nodeptr = nodeptr.getNext();
		}
		return contained;
	}

	/**
	 * Return length of the list
	 * @return length of the list
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Set length
	 * @param length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * Return the head
	 * @return the head
	 */
	public WordNode getHead() {
		return head;
	}
	
	/**
	 * Set head
	 * @param head to set
	 */
	public void setHead(WordNode head) {
		this.head = head;
	}
}