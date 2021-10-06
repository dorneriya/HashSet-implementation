package hashset;

/**
 * This class represent set based on close hashing.
 */
public class ClosedHashSet extends SimpleHashSet {

	private static final int NOT_FOUND = -1;
	private static final String DELETE = "deleted";

	private String[] stringCells;

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial
	 * capacity (16).
	 * @param upperLoadFactor - the upper load factor before rehashing
	 * @param lowerLoadFactor - the lower load factor before rehashing
	 */
	public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		this.stringCells = new String[INITIAL_CAPACITY];
	}


	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16),
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public ClosedHashSet() {
		super();
		this.stringCells = new String[INITIAL_CAPACITY];
	}


	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values
	 * should be ignored. The new table has the default values of initial capacity (16), upper load
	 * factor (0.75), and lower load factor (0.25).
	 * @param data- Values to add to the set.
	 */
	public ClosedHashSet(String[] data) {
		super();
		this.stringCells = new String[INITIAL_CAPACITY];
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
		stringCells[index] = newValue;
		return true;
	}

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True if searchVal is found in the set
	 */
	@Override
	public boolean contains(String searchVal) {
		return findIndex(searchVal) != NOT_FOUND;
	}

	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		int index = this.findIndex(toDelete);
		if (index == NOT_FOUND) {
			return false;
		}
		stringCells[index] = DELETE;
		this.decreaseSize();
		this.checkDecrease();
		return true;

	}


	/**
	 * Clamps hashing indices to fit within the current table capacity.
	 * @param index the index before clamping.
	 * @return an index properly clamped, -1 if there isn't such index.
	 */
	@Override
	protected int clamp(int index) {
		for (int i = 0; i < this.capacity();i++) {
			int tableIndex = (index + (i + i * i) / 2) & (this.capacity() - 1);
			if (stringCells[tableIndex] == null || stringCells[tableIndex] == DELETE) {
				return tableIndex;
			}
		}
		return NOT_FOUND;
	}

	/**
	 * Finding an index of a string in the array, if exist.
	 * @param searchVal the String to search
	 * @return the index if exist, -1 otherwise
	 */
	private int findIndex(String searchVal) {
		for (int i = 0; i < this.capacity();i++) {
			int index = (searchVal.hashCode() + (i + i * i) / 2) & (this.capacity() - 1);
			if (stringCells[index] == null) {
				return NOT_FOUND;
			}
			if (stringCells[index].equals(searchVal) && searchVal != DELETE) {
				return index;
			}
		}
		return NOT_FOUND;
	}

	/**
	 * Method for increasing the capacity of the set. Basically copies the array of the set to a
	 * new
	 * array with a larger capacity(by re-hashing), then switches between the arrays
	 * @param newCapacity the new capacity
	 */
	protected void changeCapacity(int newCapacity) {
		String[] newArray = new String[newCapacity];
		for (String word : this.stringCells) {
			if (word != null && word != DELETE) {
				int index = getNullIndexInArray(newArray, word);
				newArray[index] = word;
			}
		}
		this.stringCells = newArray;

	}

	/**
	 * A static function used to find an empty space in a hash-based array
	 * @param array an array of StringCell
	 * @param word The string we want to find an empty cell for
	 * @return the index of the empty cell
	 */
	private static int getNullIndexInArray(String[] array, String word) {
		int i = 0;
		while (true) {
			int index = (word.hashCode() + (i + i * i) / 2) & (array.length - 1);
			if (array[index] == null || array[index] == DELETE) {
				return index;
			}
			i++;
		}
	}

}
