package alchemytec.expenses.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import alchemytec.expenses.api.Expense;

public class ExpenseTest {
	Double d = new Double(5);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void equalExpense() {
		assertEquals("The JsonExpense should recognize a different object",
				new Expense( new Double(20.0), "2015-03-29T23:00:00.000Z",
						"some reason", new Double(4.0)), new Expense( new Double(20.0),
						"2015-03-29T23:00:00.000Z", "some reason", new Double(4.0)));

	}
	
	@Test
	public void notEqualExpense() {
		assertNotEquals("The JsonExpense should recognize a different object",
				new Expense( new Double(21.0), "2015-03-29T23:00:00.000Z",
						"some reason", new Double(4.0)), new Expense( new Double(20.0),
						"2015-03-29T23:00:00.000Z", "some reason", new Double(4.0)));

	}

}
