package reto5e;

import java.util.Random;

public class HiloEscritor extends Thread {

	@Override
	public void run() {
		DequeConcurrente<Integer> lista= MainReto5E.deque;
		boolean terminar = false;
		int n=0;
		
		boolean escrito=true;
		while (!terminar) {
			
			if(escrito)
				n = MainReto5E.actual.getAndIncrement();
			
			if (n >= MainReto5E.LIMITE_INSERCIONES) {
				terminar = true;
			} else {
				int opcion = new Random().nextInt(6);
				try {
 				switch (opcion) {
					case 0 -> {lista.addFirst(n);escrito=true;}
					case 1 -> {lista.addLast(n);escrito=true;}
					case 2 -> {lista.add(n);escrito=true;}
					case 3 -> {escrito=lista.offerFirst(n);}
					case 4 -> {escrito=lista.offerLast(n);}
					case 5 -> {escrito=lista.offer(n);}
					default -> {lista.addFirst(n);}	
				}//fin switch
				}catch(IllegalStateException ex) {
					escrito=false;
				}
				if (escrito)
					MainReto5E.inserciones.incrementAndGet();
			}//fin else
		}//fin while
	}//fin run
}//fin clase
