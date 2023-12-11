package reto5e;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import reto5a.Estadistica;

/**
 * Implementacion de la interfaz Deque con soporte para concurrencia y limite
 * máximo del tamaño de la colección.
 * 
 * @param <E> Tipo de dato almacenado en la coleccion
 */
public class DequeConcurrente<E> implements Deque<E> {

	private class Nodo<E> {
		Nodo<E> anterior;
		Nodo<E> siguiente;
		E valor;

		public Nodo(E valor) {
			this.valor = valor;
		}

	}

	AtomicReference<Nodo<E>> head;
	AtomicReference<Nodo<E>> tail;
	AtomicInteger limite;
	AtomicInteger cantidad = new AtomicInteger(0);
	Semaphore sEscritura = new Semaphore(1);

	public DequeConcurrente() {
		head = new AtomicReference<Nodo<E>>();
		tail = new AtomicReference<Nodo<E>>();
	}

	public DequeConcurrente(int limite) {
		if (limite < 1)
			throw new IllegalArgumentException();
		head = new AtomicReference<Nodo<E>>();
		tail = new AtomicReference<Nodo<E>>();
		this.limite = new AtomicInteger(limite);
	}

	// ********************************************************************************************************
	// METODOS PRINCIPALES DE LA INTERFAZ DEQUE
	// ********************************************************************************************************

	@Override
	public void addFirst(E e) throws IllegalStateException, NullPointerException {
		checkExNull(e);
		escrituraAquire();
		try {
		checkExLimiteAlcanzado();
		}catch(IllegalStateException ex) {
			escrituraRelease();
			throw ex;
		}
		
		
		Nodo<E> n = new Nodo<>(e);
		// enlazar nuevo con nodo cabeza actual
		n.siguiente = head.get();
		// poner nuevo como nueva cabeza
		head.set(n);
		// si siguiente es nulo es porque no habia, almacenarlo como tail tambien
		if (n.siguiente == null) {
			tail.set(n);
		} else {
			// en caso contrario enlazar la antigua cabeza con el nuevo
			n.siguiente.anterior = n;
		}
		cantidad.incrementAndGet();
		escrituraRelease();
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		escrituraAquire();
		Nodo<E> nodo = head.get();
		try {
			checkExElementoExiste(nodo);
		}catch (NoSuchElementException ex){
			escrituraRelease();
			throw ex;
		}
		E elemento = head.get().valor;
		// si head y tail eran lo mismo es que solo habia ese. Eliminar ambos
		if (nodo.equals(tail.get())) {
			head.set(null);
			tail.set(null);
		} else {
			head.set(head.get().siguiente);
			head.get().anterior = null;
		}
		cantidad.decrementAndGet();
		escrituraRelease();
		return elemento;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		Nodo<E> nodo = head.get();
		checkExElementoExiste(nodo);
		return nodo.valor;
	}

	@Override
	public boolean offerFirst(E e) throws NullPointerException {
		try {
			addFirst(e);
			return true;
		} catch (IllegalStateException ex) {
			return false;
		}
	}

	@Override
	public E pollFirst() {
		try {
			return removeFirst();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public E peekFirst() {
		try {
			return getFirst();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public void addLast(E e) throws NullPointerException, IllegalStateException {
		checkExNull(e);
		escrituraAquire();
		try {
		checkExLimiteAlcanzado();
		}catch(IllegalStateException ex) {
			escrituraRelease();
			throw ex;
		}
		Nodo<E> n = new Nodo<>(e);
		// enlazar nuevo con nodo tail
		n.anterior = tail.get();
		// poner nuevo como nueva tail
		tail.set(n);
		// si anteior es nulo es porque no habia, almacenarlo como head tambien
		if (n.anterior == null) {
			head.set(n);
		} else {
			// en caso contrario enlazar la antigua tail con el nuevo
			n.anterior.siguiente = n;
		}
		cantidad.incrementAndGet();
		escrituraRelease();
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		escrituraAquire();
		Nodo<E> nodo = tail.get();
		try {
		checkExElementoExiste(nodo);
		}catch(NoSuchElementException ex) {
			escrituraRelease();
			throw ex;
		}
		E e = nodo.valor;
		// si head y tail son lo mismo poner ambos a null
		if (nodo.equals(head.get())) {
			head.set(null);
			tail.set(null);
		} else {
			// En caso contrario poner como tail el nodo anterior
			tail.set(nodo.anterior);
			tail.get().siguiente = null;
		}
		cantidad.decrementAndGet();
		escrituraRelease();
		return e;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		Nodo<E> nodo = tail.get();
		checkExElementoExiste(nodo);
		return nodo.valor;
	}

	@Override
	public boolean offerLast(E e) throws NullPointerException {
		try {
			addLast(e);
			return true;
		} catch (IllegalStateException ex) {
			return false;
		}
	}

	@Override
	public E pollLast() {
		try {
			return removeLast();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public E peekLast() {
		try {
			return getLast();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	// ********************************************************************************************************
	// METODOS SECUNDARIOS DE LA INTERFAZ DEQUE
	// ********************************************************************************************************
	/**
	 * Uso FIFO. Equivalente a addLast.
	 * 
	 * @see DequeConcurrente#addLast(Object)
	 */
	@Override
	public boolean add(E e) throws NullPointerException, IllegalStateException {
		addLast(e);
		return true;
	}

	/**
	 * Uso FIFO. Equivalente a offerLast
	 * 
	 * @see DequeConcurrente#offerLast(Object)
	 */
	@Override
	public boolean offer(E e) throws NullPointerException {
		return offerLast(e);
	}

	/**
	 * Uso FIFO. Equivalente a removeFirst
	 * 
	 * @see DequeConcurrente#removeFirst()
	 */
	@Override
	public E remove() throws NoSuchElementException {
		return this.removeFirst();
	}

	/**
	 * Uso FIFO. Equivalente a pollFirst
	 * 
	 * @see DequeConcurrente#pollFirst()
	 */
	@Override
	public E poll() {
		return this.pollFirst();
	}

	/**
	 * Uso FIFO. Equivalente a getFirst
	 * 
	 * @see DequeConcurrente#getFirst()
	 */
	@Override
	public E element() {
		return this.getFirst();
	}

	/**
	 * Uso FIFO. Equivalente a peekFirst
	 * 
	 * @see DequeConcurrente#peekFirst()
	 */
	@Override
	public E peek() {
		return this.peekFirst();
	}

	/**
	 * Uso LIFO. Equivalente a addFirst
	 * 
	 * @see DequeConcurrente#addFirst(Object)
	 */
	@Override
	public void push(E e) {
		this.addFirst(e);
	}

	/**
	 * Uso LIFO. Equivalente a removeFirst
	 * 
	 * @see DequeConcurrente#removeFirst()
	 */

	@Override
	public E pop() {
		return this.removeFirst();
	}

	@Override
	public boolean remove(Object o) {
		escrituraAquire();
		boolean borrado = false;
		Nodo<E> n = head.get();
		while (n != null && !borrado) {
			if (n.valor.equals(o)) {
				Nodo<E> anterior = n.anterior;
				Nodo<E> siguiente = n.siguiente;
				// caso solo 1
				if (cantidad.get() == 1) {
					head.set(null);
					tail.set(null);
					// si es head
				} else if (head.get().equals(n)) {
					head.set(siguiente);
					siguiente.anterior = null;
					// si es tail
				} else if (tail.get().equals(n)) {
					tail.set(anterior);
					anterior.siguiente = null;
					// en otro caso enlazamos el anterior con el siguiente
				} else {
					anterior.siguiente = siguiente;
					siguiente.anterior = anterior;
				}
				// registar el borrado
				borrado = true;
				cantidad.decrementAndGet();
				// si no es igual pasamos al suguiente
			} else {
				n = n.siguiente;
			}
		}
		escrituraRelease();
		return borrado;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null)
			return false;
		boolean contiene = false;
		Nodo<E> n = head.get();
		while (n != null && !contiene) {
			if (n.valor.equals(o)) {
				contiene = true;
			} else {
				n = n.siguiente;
			}
		}
		return contiene;
	}

	/**
	 * Equivalente a remove(Object o)
	 * 
	 * @see DequeConcurrente#remove(Object)
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		return remove(o);
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		escrituraAquire();
		boolean borrado = false;
		Nodo<E> n = tail.get();
		while (n != null && !borrado) {
			if (n.valor.equals(o)) {
				Nodo<E> anterior = n.anterior;
				Nodo<E> siguiente = n.siguiente;
				// caso solo 1
				if (cantidad.get() == 1) {
					head.set(null);
					tail.set(null);
					// si es tail
				} else if (tail.get().equals(n)) {
					tail.set(anterior);
					anterior.siguiente = null;
					// si es head
				} else if (head.get().equals(n)) {
					head.set(siguiente);
					siguiente.anterior = null;
					// en otro caso enlazamos el anterior con el siguiente
				} else {
					anterior.siguiente = siguiente;
					siguiente.anterior = anterior;
				}
				// registar el borrado
				borrado = true;
				cantidad.decrementAndGet();
				// si no es igual pasamos al suguiente
			} else {
				n = n.anterior;
			}
		}
		escrituraRelease();
		return borrado;
	}

	@Override
	public Iterator<E> iterator() {
		LinkedList<E> lista = new LinkedList<>();
		Nodo<E> n = head.get();
		while (n != null) {
			lista.add(n.valor);
			n = n.siguiente;
		}
		return new DequeConcurrenteIterator(lista);
	}

	@Override
	public Iterator<E> descendingIterator() {
		LinkedList<E> lista = new LinkedList<>();
		Nodo<E> n = tail.get();
		while (n != null) {
			lista.add(n.valor);
			n = n.anterior;
		}
		return new DequeConcurrenteIterator(lista);
	}

	@Override
	public int size() {
		return cantidad.get();
	}

	// ********************************************************************************************************
	// METODOS DE LA INTERFAZ COLLECTION
	// ********************************************************************************************************

	/**
	 * Devuelve si la lista esta vacia
	 */
	@Override
	public boolean isEmpty() {
		return cantidad.get() == 0;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {

		Iterator<E> it = (Iterator<E>) c.iterator();
		boolean cambiado = false;
		boolean seguir=true;
		try {
			while (it.hasNext()&&seguir) {
				offerLast(it.next());
				cambiado = true;
			}
		}catch(Exception ex) {
		} 

		return cambiado;

	}

	@Override
	public void clear() {
		escrituraAquire();
		head.set(null);
		tail.set(null);
		cantidad.set(0);
		escrituraRelease();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> it = c.iterator();
		boolean contieneTodo = true;
		while (it.hasNext() && contieneTodo) {
			contieneTodo = contains(it.next());
		}
		return contieneTodo;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Iterator<?> it = c.iterator();
		boolean algunoBorrado = false;
		while (it.hasNext()) {
			Object elemento = it.next();
			boolean borrar = true;
			while (borrar) {
				borrar = remove(elemento);
				if (borrar)
					algunoBorrado = true;
			}
		}
		return algunoBorrado;
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		ArrayList<Object> lista = new ArrayList<>();
		c.iterator().forEachRemaining(t -> lista.add(t));

		Iterator<E> itLocal = iterator();
		boolean algunoBorrado = false;
		while (itLocal.hasNext()) {
			Object elemento = itLocal.next();
			boolean borrar = !lista.contains(elemento);
			while (borrar) {
				borrar = remove(elemento);
				if (borrar)
					algunoBorrado = true;
			}
		}
		return algunoBorrado;
	}

	/**
	 * Convierte la lista en un array de objetos.
	 */
	@Override
	public Object[] toArray() {
		LinkedList<E> lista = new LinkedList<>();
		if (cantidad.get() == 0)
			return lista.toArray();
		int i = 0;
		Nodo<E> n = head.get();
		do {
			if (n != null)
				lista.add(n.valor);
			// repetir mientras el siguiente no sea nulo y no sea el mismo nodo
			// (repeticion que se da en caso de 1 solo elemento)
		} while (n != null && (n = n.siguiente) != null && !n.equals(head));
		return lista.toArray();
	}

	/**
	 * Convierte la lista en un array de objetos.
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		LinkedList<T> lista = new LinkedList<>();
		if (cantidad.get() == 0)
			
			return lista.toArray(a);
		int i = 0;
		Nodo<E> n = head.get();
		do {
			if (n != null)
				lista.add((T) n.valor);
			// repetir mientras el siguiente no sea nulo y no sea el mismo nodo
			// (repeticion que se da en caso de 1 solo elemento)
		} while (n != null && (n = n.siguiente) != null && !n.equals(head));
		return lista.toArray(a);
	}

	// ********************************************************************************************************
	// METODO PRIVADOS
	// ********************************************************************************************************

	private void checkExNull(E e) {
		if (e == null)
			throw new NullPointerException();

	}

	/**
	 * Comprueba si se ha alcanzado el limite. Si se ha alcanzado se emite una
	 * excepcion IllegalArgumentException
	 */
	private void checkExLimiteAlcanzado() {
		if (limite != null && cantidad.get() == limite.get())
			throw new IllegalStateException();

	}

	private void checkExElementoExiste(Nodo<E> nodo) {
		if (nodo == null)
			throw new NoSuchElementException();
	}

	private void escrituraAquire() {
		try {
			sEscritura.acquire();
		} catch (InterruptedException e) {
		}

	}

	private void escrituraRelease() {
		sEscritura.release();
	}

	/**
	 * Clase para construir el iterador
	 */
	private class DequeConcurrenteIterator implements Iterator<E> {

		private LinkedList<E> lista;

		public DequeConcurrenteIterator(LinkedList<E> lista) {
			this.lista = lista;
		}

		@Override
		public boolean hasNext() {
			return lista.size() > 0;
		}

		@Override
		public E next() {
			return lista.removeFirst();
		}
	}
}
