package reto5b;

/**
 * Clase runable que implementa la multiplicacion de una fila de la matriz A por las columnas de la matriz B 
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class MultiplicaFilaPorColumnas implements Runnable {
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
	 * fila de la matriz A a multiplicar
	 */
	private int filaA;
 
	/**
	 * Constructor
	 * @param matrizA Matriz A de la cual coger una fila
	 * @param matrizB Matriz B para coger las columnas
	 * @param resultado Matriz para guardar el resultado
	 * @param filaA Fila de la matriz A a multiplicar
	 */
	public MultiplicaFilaPorColumnas(int[][] matrizA, int[][] matrizB, int[][] resultado, int filaA) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.resultado = resultado;
		this.filaA = filaA;
	}

	/**
	 * Efectua la multiplicacion de la fila de A por las columnas de B
	 */
	@Override
	public void run() {
		for (int colB = 0; colB < matrizB[0].length; colB++) {
			int resultadoTemp = 0;
			for (int filaB = 0; filaB < matrizB.length; filaB++)
				resultadoTemp += matrizA[filaA][filaB] * matrizB[filaB][colB];
			resultado[filaA][colB] = resultadoTemp;
		}
	} 
}
