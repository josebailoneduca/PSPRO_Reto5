package reto5b;

public class MultiplicarHebras {
	int[][]a;
	int[][]b;
	int[][]r;
	long tiempoInicial;
	long tiempoFinal;
	public MultiplicarHebras(int[][] a, int[][] b, int[][] r) {
	this.a = a;
	this.b = b;
	this.r = r;
	Thread[] hebras=new Thread[a.length];
	tiempoInicial=System.currentTimeMillis();
	
	for (int f=0;f<a.length;f++) {
		hebras[f]=new Thread(new MultiplicaFilaPorcolumnas(a, b, r, f));
		hebras[f].start();
	}
	for (int f=0;f<a.length;f++) {
		try {
			hebras[f].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	tiempoFinal = System.currentTimeMillis();
}

public long getTiempo(){
	return tiempoFinal-tiempoInicial;
}
	
}
