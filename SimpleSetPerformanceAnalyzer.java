package hashset;

import hashset.utils.Ex4Utils;

import java.util.*;

/**
 * This class runs a script to check run time of different kind of sets
 */
public class SimpleSetPerformanceAnalyzer {

	/**
	 * Select which measurements to perform
	 */
	private static final boolean MEASURE_ADD_DATA_1 = true;
	private static final boolean MEASURE_ADD_DATA_2 = true;
	private static final boolean MEASURE_CONTAIN_HI_1 = true;
	private static final boolean MEASURE_CONTAIN_NUMBER_1 = true;
	private static final boolean MEASURE_CONTAIN_NUMBER_2 = true;
	private static final boolean MEASURE_CONTAIN_HI_2 = true;


	/**
	 * Select data structures to measure
	 */
	private static final boolean MEASURE_CLOSED_HASH_SET = true;
	private static final boolean MEASURE_OPEN_HASH_SET = true;
	private static final boolean MEASURE_HASH_SET = true;
	private static final boolean MEASURE_TREE_SET = true;
	private static final boolean MEASURE_LINKED_LIST = true;

	private static final String FIRST_NUM = "-13170890158";
	private static final String SECOND_NUM = "23";


	/**
	 * Initializes an array of SimpleSets with the required data structures according to the
	 * constants
	 * @param sets an array of SimpleSets
	 */
	private static void initializeEmpty(SimpleSet[] sets) {
		if (MEASURE_CLOSED_HASH_SET) {
			sets[0] = new ClosedHashSet();
		}
		if (MEASURE_OPEN_HASH_SET) {
			sets[1] = new OpenHashSet();
		}
		if (MEASURE_HASH_SET) {
			sets[2] = new CollectionFacadeSet(new HashSet<>());
		}
		if (MEASURE_TREE_SET) {
			sets[3] = new CollectionFacadeSet(new TreeSet<>());
		}
		if (MEASURE_LINKED_LIST) {
			sets[4] = new CollectionFacadeSet(new LinkedList<>());
		}
	}

	/**
	 * runs the required hashset.tests.
	 * @param args program arguments
	 */
	public static void main(String[] args) {


		SimpleSet[] sets = new SimpleSet[5];

		String[] wordsData1 = Ex4Utils.file2array("src/data1.txt");
		String[] wordsData2 = Ex4Utils.file2array("src/data2.txt");

		System.out.println("set 0 = hashset.ClosedHashSet\n" +
						   "set 1 = hashset.OpenHashSet\n" +
						   "set 2 = HashSet\n" +
						   "set 3 = TreeSet\n" +
						   "set 4 = LinkedList\n");

		initializeEmpty(sets);
		/* Runs measurements with data1*/
		if (MEASURE_ADD_DATA_1) {
			System.out.println("---data 1----");
			measureAddData(sets, wordsData1);
		}
		if (MEASURE_CONTAIN_HI_1) {
			if (!MEASURE_ADD_DATA_1) {
				System.out.println("---data 1----");
				enterData(sets, wordsData1);
			}
			measureContain(sets, "hi");
		}
		if (MEASURE_CONTAIN_NUMBER_1) {
			if (!MEASURE_ADD_DATA_1 && !MEASURE_CONTAIN_HI_1) {
				System.out.println("---data 1----");
				enterData(sets, wordsData1);
			}
			measureContain(sets, FIRST_NUM);
		}

		/* Runs measurements with data1*/

		initializeEmpty(sets);

		if (MEASURE_ADD_DATA_2) {
			System.out.println("\n---data 2----\n");
			measureAddData(sets, wordsData2);
		}
		if (MEASURE_CONTAIN_HI_2) {
			if (!MEASURE_ADD_DATA_2) {
				System.out.println("\n---data 2----\n");
				enterData(sets, wordsData2);
			}
			measureContain(sets, "hi");
		}
		if (MEASURE_CONTAIN_NUMBER_2) {
			if (!MEASURE_ADD_DATA_2 && !MEASURE_CONTAIN_HI_2) {
				System.out.println("\n---data 2----\n");
				enterData(sets, wordsData2);
			}
			measureContain(sets, SECOND_NUM);
		}
	}

	/**
	 * A function that measures the time it takes to insert values into the sets
	 * @param sets an array of SimpleSets
	 * @param data the data to check with
	 */
	private static void measureAddData(SimpleSet[] sets, String[] data) {
		for (int i = 0; i < sets.length; i++) {
			if (sets[i] != null) {
				long timeBefore = System.nanoTime();
				for (int j = 0; j < data.length; j++) {
					sets[i].add(data[j]);
					if (j % 25000 == 0 || j == 99998) {
						System.out.print(j / 1000 + "% ");
					}
				}
				long difference = (System.nanoTime() - timeBefore) / 1000000;
				System.out
						.println("add data to set " + i + " took " + difference + " " +
								 "milliseconds");
			}
		}
		System.out.println();
	}

	/**
	 * A function that measures the search time for a word in a sets
	 * @param sets an array of SimpleSets
	 * @param toSearch a String to search
	 */
	private static void measureContain(SimpleSet[] sets, String toSearch) {
		for (int i = 0; i < sets.length - 1; i++) {
			if (sets[i] != null) {
				for (int j = 0; j < 70000; j++) {
					sets[i].contains(toSearch);
				}
				long timeBefore = System.nanoTime();
				for (int j = 0; j < 70000; j++) {
					sets[i].contains(toSearch);
				}
				long difference = (System.nanoTime() - timeBefore) / 70000;
				System.out
						.println("Contains(" + toSearch + ") in set " + i + " took " + difference +
								 " nanoseconds");
			}
		}
		if (sets[4] != null) {
			long timeBefore = System.nanoTime();
			for (int j = 0; j < 7000; j++) {
				sets[4].contains(toSearch);
			}
			long difference = (System.nanoTime() - timeBefore) / 7000;
			System.out.println(
					"Contains(" + toSearch + ") in set LinkedList<String> took " + difference
					+ " nanoseconds");
		}
		System.out.println();
	}

	/**
	 * Enter the data into the sets we want to test
	 * @param sets an array of simpleSet
	 * @param data The data entered
	 */
	private static void enterData(SimpleSet[] sets, String[] data) {
		for (SimpleSet set : sets){
			if (set != null){
				for (String s : data) {
					set.add(s);
				}
			}
		}
	}


}
