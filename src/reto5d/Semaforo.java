package reto5d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Semaforo implementado usando valores atómicos para el mutex de las secciones criticas
 * Se trata de un semaforo fuerte que libera la hebra que lleva mas tiempo esperando.
 * 
 * Para el numero del semaforo usa AtomicInteger y para la lista de hilos bloqueados usa AtomicReference<LinkedList<Thread>>
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Semaforo{
	
	/**
	 * Hebras bloqueadas esperando al semaforo
	 */
	private AtomicReference<LinkedList<Thread>> listaBloqueados = new AtomicReference<>(new LinkedList<Thread>());

	/**
	 * Numero de hebras que pueden pasar el semaforo sin espera
	 */
	private AtomicInteger n = new AtomicInteger();
	


	
	/**
	 * Constructor
	 * @param n Valor inicial del semaforo
	 */
	public Semaforo(int n) {
		this.n.set(n);
		
	}

	
	/**
	 * Espera del semaforo decrementando n y comproboando si es negativo. En caso de serlo la hebra actual
	 * se agrega a la lista de hebras bloqueadas. Despues hace una espera ocupada mientras la hebra actual
	 * aparezca en la lista de hebras bloqueadas
	 */
	public void esperar() {
		
		//decrementar n y comprobar si es negativo
		if (n.decrementAndGet() < 0) 
			// en caso de se negativo agregar la hebra actual a la lista de hebras bloqueadas
			listaBloqueados.getAndUpdate(listaActual -> {
                LinkedList<Thread> nuevaLista = new LinkedList<>(listaActual);
                nuevaLista.add(Thread.currentThread());
                return nuevaLista;
            });
		

		
		// espera ocupada si la hebra actual esta en la lista de hebras bloqueadas
		while (listaBloqueados.get().contains(Thread.currentThread())); {
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Senala el semaforo incrementando n y sacando una hebra de la lista de hebras bloqueadas
	 */
	public void senalar() {
		
		//incrementar n
		n.incrementAndGet();
		
		//debloquear la hebra mas antigua que espera 
		listaBloqueados.getAndUpdate(listaActual -> {
            LinkedList<Thread> nuevaLista = new LinkedList<>(listaActual);
            if (nuevaLista.size()>0) nuevaLista.remove();
            return nuevaLista;
        });
		
	}

}
