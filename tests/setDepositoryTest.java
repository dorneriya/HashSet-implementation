package hashset.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class is a test suit for the spaceship's classes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		OpenHashSetTest.class,
		BasicAPITests.class,
		ClosedHashSetTest1.class,
		ClosedHashSetTest2.class,
		EvyatarTest.class
					})
public class setDepositoryTest {
}
