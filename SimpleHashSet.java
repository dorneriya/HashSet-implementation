package hashset;

/**
 * This class represent a simple hash set.
 */
public abstract class SimpleHashSet implements SimpleSet {

	private int size;
	private int capacity;
	private final float upperLoadFactor;
	private final float lowerLoadFactor;

	/** the capacity of a newly created hash set. */
	protected static final int INITIAL_CAPACITY = 16;

	/** the lower load factor of a newly created hash set. */
	protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;

	/** the higher load factor of a newly created hash set. */
	protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;

	/**
	 * Constructs a new hash set with capacity INITIAL_CAPACITY.
	 * @param upperLoadFactor - the upper load factor before rehashing
	 * @param lowerLoadFactor - the lower load factor before rehashing
	 */
	protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
		this.capacity = INITIAL_CAPACITY;
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
	}

	/**
	 * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
	 * DEFAULT_HIGHER_CAPACITY.
	 */
	protected SimpleHashSet() {
		this.capacity = INITIAL_CAPACITY;
		this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
		this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
	}


	/**
	 * @return The higher load factor of the table.
	 */
	protected float getUpperLoadFactor() {
		return upperLoadFactor;
	}

	/**
	 * @return The lower load factor of the table.
	 */
	protected float getLowerLoadFactor() {
		return lowerLoadFactor;
	}

	/**
	 * Clamps hashing indices to fit within the current table capacity
	 * @param index the index before clamping.
	 * @return an index properly clamped.
	 */
	protected abstract int clamp(int index);

	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	@Override
	public abstract boolean add(String newValue);

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True if searchVal is found in the set
	 */
	@Override
	public abstract boolean contains(String searchVal);

	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	@Override
	public abstract boolean delete(String toDelete);

	/**
	 * A method that checks whether to increase capacity
	 */
	protected void checkIncrease() {
		if (size > capacity * this.upperLoadFactor) {
			int newCapacity = this.capacity * 2;
			this.capacity = newCapacity;
			this.changeCapacity(newCapacity);
		}
	}

	/**
	 * A method that checks whether to reduce capacity
	 */
	protected void checkDecrease() {
		if (size < capacity * this.lowerLoadFactor && capacity > 1) {
			int newCapacity = this.capacity / 2;
			this.capacity = newCapacity;
			this.changeCapacity(newCapacity);
		}
	}


	/**
	 * Method for increasing the capacity of the set. Basically copies the array of the set
	 * to a new array with a larger capacity(by re-hashing), then switches between the arrays
	 * @param newCapacity the new capacity
	 */
	protected abstract void changeCapacity(int newCapacity);

	/**
	 * @return The number of elements currently in the set
	 */
	@Override
	public int size(){
		return this.size;
	}

	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	public int capacity() {
		return capacity;
	}

	protected void increaseSize(){
		this.size++;
	}

	protected void decreaseSize(){
		this.size--;
	}

}
