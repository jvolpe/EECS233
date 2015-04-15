import java.util.Hashtable;

/**
 * @author josephvolpe Represents a phonebook of generic type T that represents
 *         how a person is represented. A person can be represented by name (string) or
 *         SSN (Integer).
 * @param <T>
 */
public class PhoneBook<T> {

	/** Represents the phonebook */
	private Hashtable<T, Integer> book = new Hashtable<T, Integer>();

	/** Looks up a person in the phonebook 
	 *  @return the person's phone number or null if they are not in the phonebook
	 */
	public Integer findPerson(T person) {
		return book.get(person);
	}

	/** Adds a person to the phonebook */
	public void addPerson(T person, int phoneNum) {
		book.put(person, phoneNum);
	}

	/** Deletes the inputted person from the phonebook */
	public boolean deletePerson(T person) {
		if (book.containsKey(person)) {
			book.remove(person);
			return true;
		} else
			return false;
	}

	/**
	 * Main method for part ii.
	 * @param args
	 */
	 public static void main(String[] args) {
		PhoneBook<String> namePhoneBook = new PhoneBook<String>();
		PhoneBook<Integer> ssnPhoneBook = new PhoneBook<Integer>();
		namePhoneBook.addPerson("Toni", 2738255);
		ssnPhoneBook.addPerson(1234, 7842433);
		System.out.println(namePhoneBook.findPerson("Toni"));
		System.out.println(ssnPhoneBook.findPerson(1234));
	 }				

	 /*public static void main(String[] args) {
		 PhoneBook<String> namePhoneBook = new PhoneBook<String>();
		 PhoneBook<Integer> ssnPhoneBook = new PhoneBook<Integer>();
		 namePhoneBook.addPerson(1234, 2738255);
	 }*/
}
