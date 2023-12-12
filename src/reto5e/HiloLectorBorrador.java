package reto5e;

import java.util.NoSuchElementException;
import java.util.Random;


/**
 * Borra numeros de la deque y pone en true la posicion del array "MainReto5E.numeros" en el indice del numero recogido.
 * Elige aleatoriamente un método de entre:removeFirst,removeLast,remove,pollFirst, pollLast, poll,  y pop.
 * En cada ciclo de lectura/borrado si la deque tiene elementos y recibe un número anota el borrado  en la
 * variable "MainReto5E.resultado" además comprueba si los escritores ya han terminado comprobando
 * MainReto5E.escrituraTerminada y si es positivo y no recibe un numero tras intentar borrar entonces
 * termina su trabajo
 */
public class HiloLectorBorrador extends Thread {
	@Override
	public void run() {
		//referencia al adeque
		DequeConcurrente<Integer> lista = MainReto5E.deque;
		boolean terminar = false;
		//bucle de borrado
		while (!terminar) 
		{
			boolean escrituraTerminada=MainReto5E.escrituraTerminada.get();
			Integer retornado =null;
			//elegir metodo de borrado aleatoriamente
			int opcion = new Random().nextInt(7);
			try {
			switch (opcion) {
				case 0 -> {
					retornado = lista.removeFirst();
				}
				case 1 -> {
					retornado=lista.removeLast();
				}
				case 2 -> {
					retornado=lista.remove();
				}
				case 3 -> {
					retornado=lista.pollFirst();
				}
				case 4 -> {
					retornado=lista.pollLast();
				}
				case 5 -> {
					retornado=lista.poll();
				}
				case 6 -> {
					retornado=lista.pop();
				}
				default -> {
					retornado=lista.removeFirst();
				}
			}// fin switch
			}catch(NoSuchElementException ex) {
				//si la escritura esta terminada 
				//se termina el bucle
				if (escrituraTerminada) {
					terminar=true;
				}
			}finally {
				if (retornado!=null) {
					MainReto5E.resultado[retornado]=true;
					MainReto5E.eliminados.incrementAndGet();
				}
				//si retornado es null y la escritura ha terminado se termina el bulcle
				else if (escrituraTerminada) 
					terminar=true;
				
			}
		} // fin while

	}// fin run
}
