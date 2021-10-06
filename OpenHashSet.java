package hashset;

import hashset.utils.MyLinkedList;

/**
 * This class represent set based on open hashing.
 */
public class OpenHashSet extends SimpleHashSet {
	private MyLinkedList[] listCells;

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial
	 * capacity (16).
	 * @param upperLoadFactor - The upper load factor of the hash table.
	 * @param lowerLoadFactor - The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		this.listCells = new MyLinkedList[INITIAL_CAPACITY];
	}

	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16),
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet() {
		super();
		this.listCells = new MyLinkedList[INITIAL_CAPACITY];
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values
	 * should be ignored. The new table has the default values of initial capacity (16), upper load
	 * factor (0.75), and lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public OpenHashSet(String[] data) {
		super();
		this.listCells = new MyLinkedList[INITIAL_CAPACITY];
		for (String word : data) {
			this.add(word);
		}
	}


	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	@Override
	public boolean add(String newValue) {
		if (this.contains(newValue)) {
			return false;
		}
		this.increaseSize();
		this.checkIncrease();
		int index = clamp(newValue.hashCode());
		if (listCells[index] == null) {
			listCells[index] = new MyLinkedList();
		}
		listCells[index].add(newValue);
		return true;
	}

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True if searchVal is found in the set
	 */
	@Override
	public boolean contains(String searchVal) {
		int index = clamp(searchVal.hashCode());
		if (this.listCells[index] == null) {
			return false;
		}
		return this.listCells[index].contain(searchVal);
	}

	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		if (!this.contains(toDelete)) {
			return false;
		}
		this.decreaseSize();
		this.checkDecrease();
		int index = clamp(toDelete.hashCode());
		return this.listCells[index].delete(toDelete);
	}


	/**
	 * Clamps hashing indices to fit within the current table capacity
	 * @param index the index before clamping.
	 * @return an index properly clamped.
	 */
	@Override
	protected int clamp(int index) {
		return index & (this.capacity() - 1);
	}

	/**
	 * Method for increasing the capacity of the set. Basically copies the array of the set to a
	 * new
	 * array with a larger capacity(by re-hashing), then switches between the arrays
	 * @param newCapacity the new capacity
	 */
	protected void changeCapacity(int newCapacity) {
		MyLinkedList[] newArr = new MyLinkedList[newCapacity];
		for (MyLinkedList myLinkedList : this.listCells) {
			if (myLinkedList != null) {
				String[] bucket = myLinkedList.toArray();
				for (String word : bucket) {
					int index = clamp(word.hashCode());
					if (newArr[index] == null) {
						newArr[index] = new MyLinkedList();
					}
					newArr[index].add(word);
				}
			}
		}
		this.listCells = newArr;
	}

}
