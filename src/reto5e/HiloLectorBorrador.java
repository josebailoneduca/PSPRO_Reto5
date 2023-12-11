package reto5e;

import java.util.NoSuchElementException;
import java.util.Random;

public class HiloLectorBorrador extends Thread {
	@Override
	public void run() {
		DequeConcurrente<Integer> lista = MainReto5E.deque;
		boolean terminar = false;
		while (!terminar) {
			boolean escrituraTerminada=MainReto5E.escrituraTerminada.get();
			
			Integer retornado =null;
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
				if (escrituraTerminada) {
					terminar=true;
				}
			}finally {
				if (retornado!=null) {
					MainReto5E.resultado[retornado]=true;
					MainReto5E.eliminados.incrementAndGet();
				}
				else if (escrituraTerminada) 
					terminar=true;
				
			}
		} // fin while

	}// fin run
}
