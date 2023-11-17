package reto5b;

import java.util.concurrent.Semaphore;

public class MultiplicarSecuencial {
int[][]a;
int[][]b;
int[][]r;
long tiempoInicial;
long tiempoFinal;
public MultiplicarSecuencial(int[][] a, int[][] b, int[][] r) {
	this.a = a;
	this.b = b;
	this.r = r;
	tiempoInicial=System.currentTimeMillis();
	for (int f=0;f<a.length;f++) {
		for (int cb=0;cb<b[0].length;cb++) {
			int resultado=0;
			for(int fb=0;fb<b.length;fb++)
				resultado+=a[f][fb]*b[fb][cb];
			r[f][cb]=resultado;
		}
		
	}
	tiempoFinal = System.currentTimeMillis();
}

public long getTiempo(){
	return tiempoFinal-tiempoInicial;
}
	
	
}
