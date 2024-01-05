package reto5b;

import java.util.Random;

/**
 * Genera matrices necesarias para el programa
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Generador {

	/**
	 * Devuelve una matriz para ser multiplicada. 
	 * Si esta en modo debug devolvera una matriz de valores previsibles y si no se devolverá una matriz de valores aleatorios
	 * @return La matriz generada
	 */
	public static int[][]getMatriz(){
		if (Config.DEBUG)
			return matrizDebug();
		else 
			return matrizReal();
	}


	/**
	 * Genera una matriz NxN rellena de 0 donde N es o Config.dimensionDebug o Config.dimension
	 * dependiendo de si el modo debug está activado o no
	 * 
	 * @return La matriz generada
	 */
	public static int[][] getMatrizVacia() {
		if(Config.DEBUG)
			return new int[Config.DIMENSION_DEBUG][Config.DIMENSION_DEBUG];
		else
			return new int[Config.DIMENSION][Config.DIMENSION];

	}
	
	
	/**
	 * Devuelve una matriz para un uso real del programa. 
	 * Genera una matriz NxN donde N es Config.dimension
	 * y los valores son un entero aleatorio entre 0 y 100
	 * 
	 * @return  La matriz generada
	 */
	private static int[][] matrizReal() {
		Random r = new Random();
		int[][] salida = new int[Config.DIMENSION][Config.DIMENSION];
		for(int i=0;i<Config.DIMENSION;i++)
			for (int j = 0 ; j<Config.DIMENSION;j++)
				salida[i][j]=r.nextInt(0,100);
		return salida;
	}
	
	/**
	 * Devuelve una matriz previsible para debug del programa.
	 * Genera una matriz NxN donde N es Config.dimensionDebug
	 * y los valores numeros consecutivos empezando por 1
	 * 
	 * @return La matriz generada
	 */
	private static int[][] matrizDebug() {
		int[][] salida = new int[Config.DIMENSION_DEBUG][Config.DIMENSION_DEBUG];
		int numero = 0;
		for(int i=0;i<salida.length;i++)
			for (int j = 0 ; j<salida[i].length;j++) {
				numero=numero+1;
				salida[i][j]=numero;
			}
		return salida;
	}


}
