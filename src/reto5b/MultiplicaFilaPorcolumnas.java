package reto5b;

public class MultiplicaFilaPorcolumnas implements Runnable {
	int[][] a;
	int[][] b;
	int[][] r;
	int f;
	long tiempoInicial;
	long tiempoFinal;

	public MultiplicaFilaPorcolumnas(int[][] a, int[][] b, int[][] r, int f) {
		this.a = a;
		this.b = b;
		this.r = r;
		this.f = f;
	}

	@Override
	public void run() {
		for (int cb = 0; cb < b[0].length; cb++) {
			int resultado = 0;
			for (int fb = 0; fb < b.length; fb++)
				resultado += a[f][fb] * b[fb][cb];
			r[f][cb] = resultado;
		}

	}

	public long getTiempo() {
		return tiempoFinal - tiempoInicial;
	}
}
