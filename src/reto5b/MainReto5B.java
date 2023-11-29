package reto5b;





/**
 * Punto de entrada del programa del reto 5B. Realiza los siguientes pasos:
 * 1) Genera las matrices a multiplicar
 * 2) Genera las matrices para almacenar el resultado
 * 3) Lanza el calculo de cada una de las técnicas  (secuencial, hebra por fila y pool de hebras)
 * 4) Muestra el resultado de tiempo empleado por cada técnica
 */
public class MainReto5B {
	public static void main(String[] args) {
		//generar matrices a multiplicar 
		int[][ ]matrizA=Generador.getMatriz();
		int[][] matrizB=Generador.getMatriz();
		
		//generar matrices rellenas de 0 para contener el resultado
		int[][]resultadoSecuencial=Generador.getMatrizVacia();
		int[][]resultadoHebras=Generador.getMatrizVacia();
		int[][]resultadoPoolthread=Generador.getMatrizVacia();
		
		System.out.println("Multiplicando las matrices");
		//lanzar multiplicaciones
		MultiplicarSecuencial ms =  new MultiplicarSecuencial(matrizA,matrizB,resultadoSecuencial);
		MultiplicarHebras mh = new MultiplicarHebras(matrizA,matrizB,resultadoHebras);
		MultiplicarThreadpool mtp = new MultiplicarThreadpool(matrizA,matrizB,resultadoHebras);
		
		//si está activado el modo debug muestra las matrices multiplicadas y los resultados ademas de los tiempos
		if (Config.DEBUG) {
		System.out.println("MATRIZ A");
		mostrarMatriz(matrizA);
		System.out.println();
		System.out.println("MATRIZ B");
		mostrarMatriz(matrizB);
		System.out.println();
		System.out.println("RESULTADO SECUENCIAL: "+ms.getTiempo()+" ms");
		mostrarMatriz(resultadoSecuencial);
		System.out.println();
		System.out.println("RESULTADO HEBRA/FILA: "+(mh.getTiempo())+" ms");
		mostrarMatriz(resultadoHebras);
		System.out.println();
		System.out.println("RESULTADO THREADPOOL: "+(mtp.getTiempo())+" ms");
		mostrarMatriz(resultadoHebras);
		}else {
			//si no está activado el modo debug muestra solo los tiempos empleados para multiplicar
			System.out.println("RESULTADO SECUENCIAL: "+ms.getTiempo()+" ms");
			System.out.println("RESULTADO HEBRA/FILA: "+mh.getTiempo()+" ms");
			System.out.println("RESULTADO THREADPOOL: "+mtp.getTiempo()+" ms");
		}
	}

	
	/**
	 * Dibuja una matriz en pantalla
	 * @param matriz La matriz a dibujar
	 */
	public static void mostrarMatriz(int[][]matriz) {
		for (int[] fila : matriz) {
			for (int celda : fila) {
				System.out.print(celda+" | ");
			}
			System.out.println();
			System.out.println("---".repeat(matriz[0].length));
		}
	} 
}
