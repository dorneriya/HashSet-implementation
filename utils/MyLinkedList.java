package hashset.utils;

import java.util.LinkedList;

/**
 * This class wraps up a linkedList<String>.
 */
public class MyLinkedList {

	private LinkedList<String> list;

	/**
	 * default constructor
	 */
	public MyLinkedList() {
		list = new LinkedList<String>();
	}

	/**
	 * @param toAdd string to add to the list
	 * @return true in success, false otherwise
	 */
	public boolean add(String toAdd) {
		return list.add(toAdd);
	}

	/**
	 * @param toSearch string to search
	 * @return true if the string in the list, false otherwise
	 */
	public boolean contain(String toSearch) {
		return list.contains(toSearch);
	}

	/**
	 * @param toDelete String to delete
	 * @return true in success, false otherwise
	 */
	public boolean delete(String toDelete) {
		return list.remove(toDelete);
	}


	/**
	 * @return return the list as array of strings
	 */
	public String[] toArray() {
		return list.toArray(new String[0]);
	}
}
