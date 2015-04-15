import java.util.Iterator;

/**
 * Demoes NumArrayList and NumLinkedList
 * @author Joseph Volpe
 */
public class NumListDemo {

	/**
	 * Returns the mean value of a list of doubles
	 * @param lst a NumList of values
	 * @return the mean value of a list of doubles
	 */
	public static double meanSequence(NumList lst) {
		double sizeOfLst = lst.size();
		double total = 0;
		Iterator<Double> iter = lst.iterator();
		while (iter.hasNext()) {
			total += iter.next();
		}
		return total/sizeOfLst;
	}
	
	/**
	 * Main method to call meanSequence on a NumArrayList and a NumLinkedList
	 * @param args
	 */
	public static void main(String[] args) {
		NumArrayList numberArrayLst = new NumArrayList();
		numberArrayLst.insert(0, 1.0);
		numberArrayLst.insert(1, 1.5);
		numberArrayLst.insert(0, 5.0);
		numberArrayLst.insert(2, 7.5);
		numberArrayLst.insert(3, 10.0);
		
		NumLinkedList numberLL = new NumLinkedList();
		numberLL.insert(0, 5.0);
		numberLL.insert(3, 2.5);
		numberLL.insert(1, 12.5);
		numberLL.insert(3, 10.0);
		numberLL.insert(2, 20.0);
		
		System.out.println(NumListDemo.meanSequence(numberArrayLst));
		System.out.println(NumListDemo.meanSequence(numberLL));
	}

}
