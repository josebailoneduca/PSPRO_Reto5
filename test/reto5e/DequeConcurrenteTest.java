package reto5e;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DequeConcurrenteTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

 

	/**
	 * Test del constructor con limite
	 */
	@Test
	void testDequeConcurrenteInt() {
		assertThrows(IllegalArgumentException.class, () -> new DequeConcurrente<Object>(-1));
	}

	@Test
	void testAddFirst() {
		DequeConcurrente<String> lCorta= new DequeConcurrente<String>(1);
		assertThrows(NullPointerException.class, () -> lCorta.addFirst(null));
		lCorta.addFirst("Primera");
		assertEquals(1, lCorta.size());
		assertThrows(IllegalStateException.class, () -> lCorta.addFirst("Segundo"));
	}

	@Test
	void testRemoveFirst() {
		DequeConcurrente<String> lCorta= new DequeConcurrente<String>(2);
		lCorta.addFirst("Primera");
		assertEquals("Primera", lCorta.getFirst());
		lCorta.addFirst("Segunda");
		lCorta.removeFirst();
		assertEquals("Primera", lCorta.getFirst());

	}

	@Test
	void testGetFirst() {
		DequeConcurrente<String> lCorta= new DequeConcurrente<String>(2);
		lCorta.addFirst("Primera");
		assertEquals("Primera", lCorta.getFirst());
		lCorta.addFirst("Segunda");
		assertEquals("Segunda", lCorta.getFirst());
	}

	@Test
	void testOfferFirst() {
		fail("Not yet implemented");
	}

	@Test
	void testPollFirst() {
		fail("Not yet implemented");
	}

	@Test
	void testPeekFirst() {
		fail("Not yet implemented");
	}

	@Test
	void testAddLast() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveLast() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLast() {
		fail("Not yet implemented");
	}

	@Test
	void testOfferLast() {
		fail("Not yet implemented");
	}

	@Test
	void testPollLast() {
		fail("Not yet implemented");
	}

	@Test
	void testPeekLast() {
		fail("Not yet implemented");
	}

	@Test
	void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	void testOffer() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	void testPoll() {
		fail("Not yet implemented");
	}

	@Test
	void testElement() {
		fail("Not yet implemented");
	}

	@Test
	void testPeek() {
		fail("Not yet implemented");
	}

	@Test
	void testPush() {
		fail("Not yet implemented");
	}

	@Test
	void testPop() {
		fail("Not yet implemented");
	}

	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	void testToArray() {
		fail("Not yet implemented");
	}

	@Test
	void testToArrayTArray() {
		fail("Not yet implemented");
	}

	@Test
	void testContainsAll() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	void testRetainAll() {
		fail("Not yet implemented");
	}

	@Test
	void testClear() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveFirstOccurrence() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveLastOccurrence() {
		fail("Not yet implemented");
	}

	@Test
	void testAddAll() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveObject() {
		fail("Not yet implemented");
	}

	@Test
	void testContains() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	@Test
	void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	void testDescendingIterator() {
		fail("Not yet implemented");
	}

}
