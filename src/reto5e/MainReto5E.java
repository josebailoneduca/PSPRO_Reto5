package reto5e;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MainReto5E {
	public static final int LIMITE_INSERCIONES = 1000000;
	public static final int N_HILOS = 100;
	public static final int LIMITE_LONGITUD_DEQUE= 5;

	public static AtomicInteger actual;

	public static DequeConcurrente<Integer> deque;

	public static boolean[] resultado;

	public static AtomicBoolean escrituraTerminada;

	public static AtomicInteger inserciones;

	public static AtomicInteger eliminados;

	public static void main(String[] args) {
		pruebaSinLimite();
		System.out.println();
		System.out.println();
		pruebaConLimite();
	}

	private static void pruebaSinLimite() {

		actual = new AtomicInteger(0);
		deque = new DequeConcurrente<Integer>();
		resultado = new boolean[LIMITE_INSERCIONES];
		escrituraTerminada = new AtomicBoolean(false);
		inserciones = new AtomicInteger(0);
		eliminados = new AtomicInteger(0);

		HiloEscritor[] escritores = new HiloEscritor[N_HILOS];
		HiloLectorBorrador[] lectores = new HiloLectorBorrador[N_HILOS];
		for (int i = 0; i < N_HILOS; i++) {
			escritores[i] = new HiloEscritor();
			lectores[i] = new HiloLectorBorrador();
		}
		for (int i = 0; i < N_HILOS; i++) {
			escritores[i].start();
			lectores[i].start();
		}

		// esperar a fin de escrituras
		for (int i = 0; i < N_HILOS; i++) {
			try {
				escritores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		escrituraTerminada.set(true);
		// esperar a fin de lecturas
		for (int i = 0; i < N_HILOS; i++) {
			try {
				lectores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// comprobar resultado
		int erroresTraspaso = 0;
		for (int i = 0; i < LIMITE_INSERCIONES; i++) {
			if (!resultado[i])
				erroresTraspaso++;
		}
		System.out.println();
		System.out.println("DEQUE SIN LIMITE");
		System.out.println("-------------------------------------------------");
		System.out.println("Hilos escribiendo: \t"+N_HILOS);
		System.out.println("Hilos leyendo: \t\t"+N_HILOS);
		System.out.println("Elementos insertados: \t" + inserciones);
		System.out.println("Elementos borrados: \t" + eliminados);
		System.out.println("Elementos sin eliminar: " + deque.size());
		System.out.println("Errores de traspaso: \t" + erroresTraspaso);

	}

	private static void pruebaConLimite() {
		actual = new AtomicInteger(0);
		deque = new DequeConcurrente<Integer>(LIMITE_LONGITUD_DEQUE);
		resultado = new boolean[LIMITE_INSERCIONES];
		escrituraTerminada = new AtomicBoolean(false);
		inserciones = new AtomicInteger(0);
		eliminados = new AtomicInteger(0);

		HiloEscritor[] escritores = new HiloEscritor[N_HILOS];
		HiloLectorBorrador[] lectores = new HiloLectorBorrador[N_HILOS];
		for (int i = 0; i < N_HILOS; i++) {
			escritores[i] = new HiloEscritor();
			lectores[i] = new HiloLectorBorrador();
		}
		for (int i = 0; i < N_HILOS; i++) {
			escritores[i].start();
			lectores[i].start();
		}

		// esperar a fin de escrituras
		for (int i = 0; i < N_HILOS; i++) {
			try {
				escritores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		escrituraTerminada.set(true);
		// esperar a fin de lecturas
		for (int i = 0; i < N_HILOS; i++) {
			try {
				lectores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// comprobar resultado
		int erroresTraspaso = 0;
		for (int i = 0; i < LIMITE_INSERCIONES; i++) {
			if (!resultado[i])
				erroresTraspaso++;
		}
		System.out.println();
		System.out.println("DEQUE CON LIMITE MAXIMO DE "+LIMITE_LONGITUD_DEQUE+" ELEMENTOS");
		System.out.println("-------------------------------------------------");
		System.out.println("Hilos escribiendo: \t"+N_HILOS);
		System.out.println("Hilos leyendo: \t\t"+N_HILOS);
		System.out.println("Elementos insertados: \t" + inserciones);
		System.out.println("Elementos borrados: \t" + eliminados);
		System.out.println("Elementos sin eliminar: " + deque.size());
		System.out.println("Errores de traspaso: \t" + erroresTraspaso);

	}

}
