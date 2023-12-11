package reto5e;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class DequeConcurrenteTest {

 

	// ********************************************************************************************************
	// METODOS PRINCIPALES DE LA INTERFAZ DEQUE
	// ********************************************************************************************************

	/**
	 * Test del constructor con limite
	 */
	@Test
	void testDequeConcurrenteInt() {
		assertThrows(IllegalArgumentException.class, () -> new DequeConcurrente<Object>(-1));
	}

	@Test
	void testAddFirst() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(1);
		assertThrows(NullPointerException.class, () -> lista.addFirst(null));
		lista.addFirst("Primera");
		assertEquals(1, lista.size());
		assertThrows(IllegalStateException.class, () -> lista.addFirst("Segundo"));
	}

	@Test
	void testRemoveFirst() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		lista.addFirst("Primera");
		lista.addFirst("Segunda");
		assertEquals("Segunda",lista.removeFirst());
		assertEquals("Primera", lista.getFirst());
		assertEquals("Primera",lista.removeFirst());
		assertThrows(NoSuchElementException.class, () -> lista.removeFirst());
	}

	@Test
	void testGetFirst() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertThrows(NoSuchElementException.class, ()->lista.getFirst());
		lista.addFirst("Primera");
		assertEquals("Primera", lista.getFirst());
		lista.addFirst("Segunda");
		assertEquals("Segunda", lista.getFirst());
	}

	@Test
	void testOfferFirst() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertTrue(lista.offerFirst("primero"));
		assertTrue(lista.offerFirst("Segundo"));
		assertFalse(lista.offerFirst("tercero"));

	}

	@Test
	void testPollFirst() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		lista.addFirst("Primera");
		assertEquals("Primera", lista.getFirst());
		lista.addFirst("Segunda");
		assertEquals("Segunda",lista.pollFirst());
		assertEquals("Primera", lista.getFirst());
		assertEquals("Primera", lista.pollFirst());
		assertEquals(null, lista.pollFirst());
	}

	@Test
	void testPeekFirst() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertEquals(null, lista.peekFirst());
		lista.addFirst("Primera");
		lista.addFirst("Segunda");
		assertEquals("Segunda", lista.peekFirst());
	}

	@Test
	void testAddLast() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(1);
		assertThrows(NullPointerException.class, () -> lista.addLast(null));
		lista.addLast("Primera");
		assertEquals(1, lista.size());
		assertThrows(IllegalStateException.class, () -> lista.addFirst("Segundo"));
	}

	@Test
	void testRemoveLast() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		lista.addLast("Segunda");
		lista.addFirst("Primera");
		lista.addLast("Tercera");
		lista.addFirst("Cero");
		assertEquals("Tercera",lista.removeLast());
		assertEquals("Segunda",lista.removeLast());
		assertEquals("Primera",lista.removeLast());
		assertEquals("Cero",lista.removeLast());
		assertThrows(NoSuchElementException.class, () -> lista.removeLast());
	}

	@Test
	void testGetLast() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertThrows(NoSuchElementException.class, ()->lista.getLast());
		lista.addFirst("Primera");
		assertEquals("Primera", lista.getLast());
		lista.addLast("Segunda");
		assertEquals("Segunda", lista.getLast());
	}

	@Test
	void testOfferLast() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertTrue(lista.offerLast("primero"));
		assertTrue(lista.offerLast("Segundo"));
		assertFalse(lista.offerLast("tercero"));
		assertEquals("Segundo", lista.peekLast());
	}

	@Test
	void testPollLast() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		lista.addFirst("Primera");
		lista.addFirst("Segunda");
		assertEquals("Primera",lista.pollLast());
		assertEquals("Segunda", lista.getFirst());
		assertEquals("Segunda", lista.getLast());
		assertEquals("Segunda",lista.pollLast());
		assertEquals(null,lista.pollLast());
		
	}
	@Test
	void testPeekLast() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertEquals(null, lista.peekFirst());
		lista.addFirst("Primera");
		lista.addFirst("Segunda");
		assertEquals("Segunda", lista.peekFirst());
	}
	//*******************************************************************************
	// METODOS SECUNDARIOS
	//*******************************************************************************

	@Test
	void testAdd() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertThrows(NullPointerException.class, () -> lista.add(null));
		lista.add("Primera");
		lista.add("Segunda");
		assertEquals(2, lista.size());
		assertEquals("Segunda", lista.peekLast());
		assertEquals("Primera", lista.peekFirst());
		assertThrows(IllegalStateException.class, () -> lista.add("Segundo"));
		
	}

	@Test
	void testOffer() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertTrue(lista.offer("primero"));
		assertTrue(lista.offer("Segundo"));
		assertFalse(lista.offer("tercero"));
		assertEquals("Segundo", lista.peekLast());
	}

	@Test
	void testRemove() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		lista.addFirst("Primera");
		lista.addFirst("Segunda");
		assertEquals("Segunda",lista.remove());
		assertEquals("Primera", lista.getFirst());
		assertEquals("Primera",lista.remove());
		assertThrows(NoSuchElementException.class, () -> lista.remove());

	}

	@Test
	void testPoll() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		lista.addFirst("Primera");
		assertEquals("Primera", lista.getFirst());
		lista.addFirst("Segunda");
		assertEquals("Segunda",lista.poll());
		assertEquals("Primera", lista.getFirst());
		assertEquals("Primera", lista.poll());
		assertEquals(null, lista.poll());
	}

	@Test
	void testElement() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertThrows(NoSuchElementException.class, ()->lista.element());
		lista.addFirst("Primera");
		assertEquals("Primera", lista.element());
		lista.addFirst("Segunda");
		assertEquals("Segunda", lista.element());
	}

	@Test
	void testPeek() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		assertEquals(null, lista.peek());
		lista.addFirst("Primera");
		lista.addFirst("Segunda");
		assertEquals("Segunda", lista.peek());
	}

	@Test
	void testPush() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(3);
		assertThrows(NullPointerException.class, () -> lista.push(null));
		lista.push("Primera");
		lista.push("Segunda");
		lista.push("Tercera");
		assertEquals(3, lista.size());
		assertEquals("Tercera", lista.peekFirst());
		assertEquals("Primera", lista.peekLast());
		assertThrows(IllegalStateException.class, () -> lista.push("No cabe"));

	}

	@Test
	void testPop() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(2);
		lista.addFirst("Primera");
		lista.addFirst("Segunda");
		assertEquals("Segunda",lista.pop());
		assertEquals("Primera", lista.getFirst());
		assertEquals("Primera",lista.pop());
		assertThrows(NoSuchElementException.class, () -> lista.pop());
	}


	@Test
	void testRemoveObject() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>(5);
		lista.add("1");
		lista.add("2");
		lista.add("3");
		lista.add("4");
		lista.add("5");
		assertFalse(lista.remove("sadf"));
		assertTrue(lista.remove("1"));
		assertTrue(lista.remove("3"));
		assertTrue(lista.remove("5"));
		assertEquals("2",lista.pop());
		assertEquals("4",lista.pop());
		assertEquals(0,lista.size());
	}
	

	@Test
	void testContains() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		lista.add("1");
		lista.add("2");
		lista.add("3");
		assertFalse(lista.contains(null));
		assertFalse(lista.contains("5"));
		assertTrue(lista.contains("1"));
		assertTrue(lista.contains("2"));
		assertTrue(lista.contains("3"));
	}

	@Test
	void testRemoveFirstOccurrence() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		lista.addFirst("1");
		lista.addFirst("2");
		lista.addFirst("1");
		assertTrue(lista.removeFirstOccurrence("1"));
		assertEquals(2, lista.size());
		assertEquals("2", lista.peekFirst());
		assertEquals("1", lista.peekLast());
		
		assertTrue(lista.removeFirstOccurrence("1"));
		assertEquals(1, lista.size());
		assertEquals("2", lista.peekFirst());
		assertEquals("2", lista.peekLast());
		
		lista.addFirst("3");
		lista.addFirst("2");
		lista.addFirst("0");
		assertTrue(lista.removeFirstOccurrence("2"));
		assertEquals(3, lista.size());
		assertEquals("0", lista.peekFirst());
		assertEquals("2", lista.peekLast());
	}

	@Test
	void testRemoveLastOccurrence() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		lista.addFirst("1");
		lista.addFirst("2");
		lista.addFirst("1");
		assertTrue(lista.removeLastOccurrence("1"));
		assertEquals(2, lista.size());
		assertEquals("1", lista.peekFirst());
		assertEquals("2", lista.peekLast());
		
		assertTrue(lista.removeLastOccurrence("1"));
		assertEquals(1, lista.size());
		assertEquals("2", lista.peekFirst());
		assertEquals("2", lista.peekLast());
		
		lista.addLast("3");
		lista.addLast("2");
		lista.addLast("0");
		assertTrue(lista.removeLastOccurrence("2"));
		assertEquals(3, lista.size());
		assertEquals("2", lista.peekFirst());
		assertEquals("0", lista.peekLast());
	}
	
	
	@Test
	void testIterator() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		lista.addFirst("4");
		lista.addFirst("3");
		lista.addLast("5");
		lista.addFirst("2");
		lista.addFirst("1");
		Iterator<String> it = lista.iterator();
		String[] arr = {"1","2","3","4","5"};
		int i=0;
		while(it.hasNext()) {
			String el=it.next();
			assertEquals(arr[i], el);
			i++;
		}
	}

	@Test
	void testDescendingIterator() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		lista.addFirst("4");
		lista.addFirst("3");
		lista.addLast("5");
		lista.addFirst("2");
		lista.addFirst("1");
		Iterator<String> it = lista.descendingIterator();
		String[] arr = {"5","4","3","2","1"};
		int i=0;
		while(it.hasNext()) {
			String el=it.next();
			assertEquals(arr[i], el);
			i++;
		}
	}
	
	
	@Test
	void testSize() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		assertEquals(0, lista.size());

		lista.addFirst("4");
		assertEquals(1, lista.size());
		lista.addFirst("3");
		lista.addLast("5");
		assertEquals(3, lista.size());
		lista.addFirst("2");
		lista.addFirst("1");
		assertEquals(5, lista.size());

	}
	
	
	//*******************************************************************************
	// METODOS INTERFAZ COLLECTION
	//*******************************************************************************
	
	
	@Test
	void testIsEmpty() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		assertTrue(lista.isEmpty());
		lista.addFirst("4");
		assertFalse(lista.isEmpty());
		lista.remove();
		assertTrue(lista.isEmpty());

	}

	@Test
	void testAddAll() {
		ArrayList<String> coleccion= new ArrayList<>();
		coleccion.add("1");
		coleccion.add("2");
		coleccion.add("3");
		coleccion.add("4");
		coleccion.add("5");
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		lista.add("0");
		lista.addAll(coleccion);
		
		Iterator<String> it = lista.iterator();
		String[] arr = {"0","1","2","3","4","5"};
		int i=0;
		while(it.hasNext()) {
			String el=it.next();
			assertEquals(arr[i], el);
			i++;
		}		
	}

	@Test
	void testClear() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		for (int i=0;i<5;i++)
			lista.add(""+i);
		assertEquals(5, lista.size());
		lista.clear();
		assertEquals(0, lista.size());
	}

	@Test
	void testContainsAll() {
		ArrayList<String> arr= new ArrayList<String>();
		for (int i=0;i<5;i++)
			arr.add(""+i);
		ArrayList<String> arr2= new ArrayList<String>();
		for (int i=0;i<6;i++)
			arr2.add(""+i);
		
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		for (int i=0;i<5;i++)
			lista.add(""+i);
		
		assertTrue(lista.containsAll(arr));
		assertFalse(lista.containsAll(arr2));
	}
	

	@Test
	void testRemoveAll() {
		ArrayList<String> arr= new ArrayList<String>();
		for (int i=0;i<5;i++)
			arr.add(""+i);
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		for (int i=0;i<6;i++)
			lista.add(""+i);
		
		lista.removeAll(arr);
		assertEquals(1,lista.size());
		assertEquals("5",lista.peek());
		
	}

	
	@Test
	void testRetainAll() {
		ArrayList<String> arr= new ArrayList<String>();
		for (int i=1;i<5;i++)
			arr.add(""+i);
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		for (int i=0;i<6;i++)
			lista.add(""+i);
		
		lista.retainAll(arr);
		assertEquals(4,lista.size());
		assertEquals("1",lista.peekFirst());
		assertEquals("4",lista.peekLast());
	}

	
	
	
	
	@Test
	void testToArray() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		for (int i=0;i<6;i++)
			lista.add(""+i);
		Object[] arr = lista.toArray();
		for (int i=0;i<arr.length;i++)
			assertEquals(""+i,arr[i]);

	}

	@Test
	void testToArrayTArray() {
		DequeConcurrente<String> lista= new DequeConcurrente<String>();
		for (int i=0;i<6;i++)
			lista.add(""+i);
		String[] arr =new String[50];
		arr = lista.toArray(arr);
		for (int i=0;i<6;i++)
			assertEquals(""+i,arr[i]);
		assertEquals(null,arr[7]);
	}




}
