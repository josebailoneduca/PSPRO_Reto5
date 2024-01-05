package reto5b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Multiplica dos matrices usando un threadpool de tipo fixedThreadPool con
 * tantos hilos como procesadores tiene el sistema. El pool de hebras se nutre
 * de runables que se encargan cada uno de multiplicar una fila de la matriz A
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class MultiplicarThreadpool {
	/**
	 * matriz A de la cual coger una fila
	 */
	private int[][] matrizA;
	
	/**
	 * Matriz B para coger las columnas
	 */
	private int[][] matrizB;
	
	/**
	 * Matriz para guardar el resultado
	 */
	private int[][] resultado;
	
	/**
	 * Tiempo de inicio del calculo
	 */
	private long tiempoInicial;
	
	/**
	 * Tiempo final del calculo
	 */
	private long tiempoFinal;

	/**
	 * Constructor
	 * @param matrizA   La matriz A de la multiplicacion A·B
	 * @param matrizB   La matriz B de la multiplicacion A·B
	 * @param resultado La matriz resultado
	 */
	public MultiplicarThreadpool(int[][] matrizA, int[][] matrizB, int[][] resultado) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.resultado = resultado;
		
		tiempoInicial = System.currentTimeMillis();
		
		//calculo a traves de un threadlpool
		ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int filasA = 0; filasA < matrizA.length; filasA++) {
			threadpool.execute(new MultiplicaFilaPorColumnas(matrizA, matrizB, resultado, filasA));
		}
		threadpool.shutdown();
		while (!threadpool.isTerminated());
		
		tiempoFinal = System.currentTimeMillis();
	}

	/**
	 * Devuelve el tiempo empleado en la multiplicacion
	 * 
	 * @return El tiempo
	 */
	public long getTiempo() {
		return tiempoFinal - tiempoInicial;
	}

}
