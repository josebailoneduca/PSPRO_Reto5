package reto5d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Semaforo implementado usando n como valor atómico. Es un semáforo debil.
 * 
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Semaforo {

	/**
	 * Numero de hebras que pueden pasar el semaforo sin espera
	 */
	private AtomicInteger n = new AtomicInteger();

	/**
	 * Constructor
	 * 
	 * @param n Valor inicial del semaforo
	 */
	public Semaforo(int n) {
		this.n.set(n);

	}

	/**
	 * Espera a que el numero del semaforo sea positivo. Si y sigue siendo el valor
	 * de cuando se leyó, decrementa su valor en uno.
	 */
	public void esperar() {

		while (true) {
			int permiso = n.get();
			if (permiso > 0 && n.compareAndSet(permiso, permiso - 1)) {
				return;
			}
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Senala el semaforo incrementando n
	 */
	public void senalar() {
		// incrementar n
		n.incrementAndGet();
	}

}
