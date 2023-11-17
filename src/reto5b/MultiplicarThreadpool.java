package reto5b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiplicarThreadpool {
	int[][]a;
	int[][]b;
	int[][]r;
	long tiempoInicial;
	long tiempoFinal;
	public MultiplicarThreadpool(int[][] a, int[][] b, int[][] r) {
	this.a = a;
	this.b = b;
	this.r = r;
	tiempoInicial=System.currentTimeMillis();
	ExecutorService threadpool =  Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors()); 
	for (int f=0;f<a.length;f++) {
		threadpool.execute(new MultiplicaFilaPorcolumnas(a, b, r, f));
	}
	threadpool.shutdown();
	while (!threadpool.isTerminated());
	tiempoFinal = System.currentTimeMillis();
}

public long getTiempo(){
	return tiempoFinal-tiempoInicial;
}
	
}
