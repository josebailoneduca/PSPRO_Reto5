package reto5b;

/**
 * Multiplica dos matrices generando un hilo por cada fila de la matriz A Espera
 * a que todas las hebras terminen para calcular el tiempo empleado
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class MultiplicarHebras {
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
	 * 
	 * @param matrizA   La matriz A de la multiplicacion A·B
	 * @param matrizB   La matriz B de la multiplicacion A·B
	 * @param resultado La matriz resultado
	 */
	public MultiplicarHebras(int[][] matrizA, int[][] matrizB, int[][] resultado) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.resultado = resultado;
		
		Thread[] hebras = new Thread[matrizA.length];
		tiempoInicial = System.currentTimeMillis();
		
		//calculo de la multiplicacion
		for (int filaA = 0; filaA < matrizA.length; filaA++) {
			hebras[filaA] = new Thread(new MultiplicaFilaPorColumnas(matrizA, matrizB, resultado, filaA));
			hebras[filaA].start();
		}

		// esperar a que terminen todas la hebras
		for (int filaA = 0; filaA < matrizA.length; filaA++) {
			try {
				hebras[filaA].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		tiempoFinal = System.currentTimeMillis();
	}

	/**
	 * Devuelve el tiempo empleado en la multipliacion
	 * 
	 * @return El tiempo
	 */
	public long getTiempo() {
		return tiempoFinal - tiempoInicial;
	}

}
