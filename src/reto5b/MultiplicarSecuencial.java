package reto5b;

import java.util.concurrent.Semaphore;

/**
 * Hace la multiplicacion con un bucle triple anidado que va efectuando la
 * operacion de la multiplicación de las matrices
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class MultiplicarSecuencial {
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
	public MultiplicarSecuencial(int[][] matrizA, int[][] matrizB, int[][] resultado) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.resultado = resultado;

		// Registro del tiempo inicial
		tiempoInicial = System.currentTimeMillis();

		// multiplicación de las matrices
		for (int f = 0; f < matrizA.length; f++) {
			for (int cb = 0; cb < matrizB[0].length; cb++) {
				int resultadoTemp = 0;
				for (int fb = 0; fb < matrizB.length; fb++)
					resultadoTemp += matrizA[f][fb] * matrizB[fb][cb];
				resultado[f][cb] = resultadoTemp;
			}

		}
		// registro del tiempo final
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
