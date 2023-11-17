package reto5b;

import java.util.Random;

public class Generador {

	
	public static int[][]getMatriz(){
		if (Config.debug)
			return matrizDebug();
		else 
			return matrizReal();
	}

	
	private static int[][] matrizReal() {
		Random r = new Random();
		int[][] salida = new int[Config.dimension][Config.dimension];
		for(int i=0;i<Config.dimension;i++)
			for (int j = 0 ; j<Config.dimension;j++)
				salida[i][j]=r.nextInt(0,100);
		return salida;
	}
	
	private static int[][] matrizDebug() {
		int[][] salida = new int[Config.dimensionDebug][Config.dimensionDebug];
		int numero = 0;
		for(int i=0;i<salida.length;i++)
			for (int j = 0 ; j<salida[i].length;j++) {
				numero=numero+1;
				salida[i][j]=numero;
			}
		return salida;
	}

	public static int[][] getMatrizVacia() {
		if(Config.debug)
			return new int[Config.dimensionDebug][Config.dimensionDebug];
		else
			return new int[Config.dimension][Config.dimension];

	}
}
