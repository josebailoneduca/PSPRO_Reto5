package reto5e;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class DequeConcurrente<E> implements Deque<E> {
	
	private class Nodo<E>{
		Nodo<E> anterior;
		Nodo<E> siguiente;
		E valor;
		
		public Nodo(E valor) {
			this.valor = valor;
		}

	}
	
 
	
	
	int limite=0;
	boolean hayLimite=false;
	AtomicInteger cantidad=new AtomicInteger();
	Nodo<E> head;
	Nodo<E> tail;
	
	
	
	
	public DequeConcurrente() {
		hayLimite=true;
	}
	
	public DequeConcurrente(int limite) {
		if (limite<0)
			throw new IllegalArgumentException("No se puede poner un limite negativo");
		this.hayLimite=true;
		this.limite = limite;
		
	}

	@Override
	public void addFirst(E e) {
		checkExNullLimite(e);
		Nodo<E> n = new Nodo<>(e);
		//enlazar nuevo con nodo cabeza 
		n.siguiente=head;
		//poner nuevo como nueva cabeza
		head=n;
		//si siguiente es nulo es porque no habia, almacenarlo como tail tambien
		if (n.siguiente==null) {
			tail=n;
		}else {
			//en caso contrario enlazar la antigua cabeza con el nuevo
			n.siguiente.anterior=n;
		}
		cantidad.incrementAndGet();	
	}

	

	@Override
	public E removeFirst() {
		checkExVacia();
		E e=head.valor;
		if (cantidad.get()==1) {
			head=null;
			tail=null;
		}else {
			head=head.siguiente;
			head.anterior=null;
		}
		cantidad.decrementAndGet();
		return e;
	}


	@Override
	public E getFirst() {
		checkExVacia();
		return head.valor;
	}

	@Override
	public boolean offerFirst(E e) {
		try {
			addFirst(e);
			return true;
		}catch (IllegalStateException ex) {
			return false;
		}
	}

	@Override
	public E pollFirst() {
		try {
			return removeFirst();
		}catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public E peekFirst() {
		try {
			return getFirst();
		}catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public void addLast(E e) {
		checkExNullLimite(e);
		Nodo<E> n = new Nodo<>(e);
		//enlazar nuevo con nodo tail 
		n.anterior=tail;
		//poner nuevo como nueva tail
		tail=n;
		//si anteior es nulo es porque no habia, almacenarlo como head tambien
		if (n.anterior==null) {
			head=n;
		}else {
			//en caso contrario enlazar la antigua tail con el nuevo
			n.anterior.siguiente=n;
		}
		cantidad.incrementAndGet();
	}

	@Override
	public E removeLast() {
		checkExVacia();
		E e=tail.valor;
		if (cantidad.get()==1) {
			head=null;
			tail=null;
		}else {
			tail=tail.anterior;
			tail.siguiente=null;
		}
		cantidad.decrementAndGet();
		return e;
	}

	@Override
	public E getLast() {
		checkExVacia();
		return tail.valor;
	}

	@Override
	public boolean offerLast(E e) {
		try {
			addLast(e);
			return true;
		}catch (IllegalStateException ex) {
			return false;
		}
	}

	@Override
	public E pollLast() {
		try {
			return removeLast();
		}catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public E peekLast() {
		try {
			return getLast();
		}catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public boolean add(E e) {
 			addLast(e);
			return true;
 	}

	@Override
	public boolean offer(E e) {
		return offerLast(e);
	}

	@Override
	public E remove() {
		return this.removeFirst();
	}

	@Override
	public E poll() {
		return this.pollFirst();
	}

	@Override
	public E element() {
		return this.getFirst();
	}

	@Override
	public E peek() {
		return this.peekFirst();
	}

	@Override
	public void push(E e) {
		this.addFirst(e);
	}

	@Override
	public E pop() {
		LinkedList<String>e =new LinkedList<>();

		return this.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		return cantidad.get()==0;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[cantidad.get()];
		if (cantidad.get()==0)
			return array;
		int i=0;
		Nodo<E> n=head;
		do {
			array[i]=n.valor;
			i++;
		}while((n=n.siguiente)!=null);
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
 		Object[]arr=toArray();
 		if (a.length>=arr.length){
 			for (int i=0;i<a.length&&i<arr.length;i++) {
 				a[i]=(T) arr[i];
 			}
 			if (a.length>arr.length)
 				a[arr.length]=null;
 			return a;
 		}else {
		return (T[]) toArray();
 		}
	}

	@Override
	public void clear() {
		head=null;
		tail=null;
	}
	
	
	@Override
	public int size() {
		return cantidad.get();
	}

	
	
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean cambio=c.size()>0 && (!hayLimite || cantidad.get()<limite);
		try {
			c.iterator().forEachRemaining(t -> {addLast(t);});
		}catch(Exception ex) {	
		}
		return cambio;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}



	
	
	

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	private void checkExNullLimite(E e) {
		if (e==null)
			throw new NullPointerException();
		if (hayLimite&&limite==cantidad.get())
			throw new IllegalStateException("No queda sitio");
	}
	

	private void checkExVacia() {
		if (this.cantidad.get()==0)
			throw new NoSuchElementException("La lista esta vacia");
	}
	
}
