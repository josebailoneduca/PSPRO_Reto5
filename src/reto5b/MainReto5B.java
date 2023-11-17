package reto5b;

public class MainReto5B {
	public static void main(String[] args) {
		int[][ ]matrizA=Generador.getMatriz();
		int[][] matrizB=Generador.getMatriz();
		int[][]resultadoSecuencial=Generador.getMatrizVacia();
		int[][]resultadoHebras=Generador.getMatrizVacia();
		int[][]resultadoPoolthread=Generador.getMatrizVacia();
		
		
		MultiplicarSecuencial ms =  new MultiplicarSecuencial(matrizA,matrizB,resultadoSecuencial);
		MultiplicarHebras mh = new MultiplicarHebras(matrizA,matrizB,resultadoHebras);
		MultiplicarThreadpool mtp = new MultiplicarThreadpool(matrizA,matrizB,resultadoHebras);
		if (Config.debug) {
		System.out.println("MATRIZ A");
		mostrarMatriz(matrizA);
		System.out.println("MATRIZ B");
		mostrarMatriz(matrizB);
		System.out.println();
		System.out.println("RESULTADO SECUENCIAL: "+ms.getTiempo()+" ms");
		mostrarMatriz(resultadoSecuencial);
		System.out.println("RESULTADO HEBRAS: "+(mh.getTiempo())+" ms");
		mostrarMatriz(resultadoHebras);
		System.out.println("RESULTADO THREADPOOL: "+(mtp.getTiempo())+" ms");
		mostrarMatriz(resultadoHebras);
		}else {
			System.out.println("RESULTADO SECUENCIAL: "+ms.getTiempo()+" ms");
			System.out.println("RESULTADO HEBRAS: "+(mh.getTiempo())+" s");
			System.out.println("RESULTADO THREADPOOL: "+(mtp.getTiempo())+" ms");
		}

	}

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
