package reto5e;

import java.util.Random;


/**
 * Introduce numeros en la deque compartida eligiendo
 * aleatoriamente un método de entre:addfirst,addLast,add,offerFirst,offerLast,offer
 * El numero a introducir se va adquiriendo e incrementando atomicamente de la variable compartida
 * MainReto5E.numeroActual. En el momento en que que el número obtenido iguala o supera
 * a LIMITE_INSERCIONES el trabajo termina.
 * 
 * En cada ciclo de escritura se comprueba si realmente se ha conseguido introducir el número en la deque
 *  ya que en el caso de que la deque esté limitada en tamaño lanzará una excepción o devolverá false 
 *  (segun método)
 */
public class HiloEscritor extends Thread {

	@Override
	public void run() {
		//referencia a la deque
		DequeConcurrente<Integer> lista= MainReto5E.deque;
		
		//numero a insertar
		int n=0;
		
		//bandera que marca si debe terminar el bucle infinito
		boolean terminar = false;
		
		//almacena si se ha escrito el numero anterior
		boolean escrito=true;
		
		//bucle de insercion de numeros
		while (!terminar) {
			//si el numero anterior se ha escrito se coge uno nuevo
			if(escrito)
				n = MainReto5E.numeroActual.getAndIncrement();
			
			//si el numero recogido traspasa el limite se termina el trabajo
			if (n >= MainReto5E.LIMITE_INSERCIONES) {
				terminar = true;
			} else {
				//elije un metodo de insercion del valor y lo agrega
				int opcion = new Random().nextInt(6);
				//trycatch para contemplar las listas limitadas
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
