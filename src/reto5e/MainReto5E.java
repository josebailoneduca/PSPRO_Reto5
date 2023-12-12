package reto5e;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * Clase principal del Reto5E implementar la interfaz Deque con 
 * capacidad de concurrencia.
 * 
 * El ejemplo consiste en varias hebras que van cogiendo los numeros de 0 a LIMITE_INSERCIONES(no incluido)
 * y escriben el numero sobre una DequeconConcurrente.
 * A la vez otras hebras van borrando y obteniendo los numeros de la DequeConcurrente.
 * Usando el número obtenido ponen a true la posición del array boolean[]resultado.
 * 
 * Al terminar el proceso se han debido de producir la misma cantidad de inserciones que de borrados
 * y todas las posiciones del array resultado deben estar a true. En el informe aparecerían como 
 * "errores de traspaso" las banderas que estan a false.
 * 
 * Se hace la prueba de dos maneras. En una ocasión sin limitar el tamaño de la DequeConcurrente
 * Y en otra ocasion limitando el tamaño de la DequeConcurrente al valor definido en LIMITE_LONGITUD_DEQUE
 * 
 */
public class MainReto5E {
	
	//CONFIGURACION
	
	
	/**
	 * Numeros a insertar en la DequeConcurrente
	 * de 0 a este valor (no incluid)
	 */
	public static final int LIMITE_INSERCIONES = 1000000;
	
	/**
	 * Numero de hilos escritores y numero de hilos lectores/borradores que se generan
	 */
	public static final int N_HILOS = 100;
	
	/**
	 * Limite maximo permitido en al DequeConcurrente para la prueba con límite
	 */
	public static final int LIMITE_LONGITUD_DEQUE= 5;

	
	
	//VARIABLES COMPARTIDAS DURANTE LAS PRUEBAS
	
	/**
	 * Contiene el proximo numero a insertar durante el desarrollo de cada prueba
	 */
	
	public static AtomicInteger numeroActual;
	
	/**
	 * DequeConcurrente a usar durante el desarrollo de cada prueba
	 */

	public static DequeConcurrente<Integer> deque;
	
	/**
	 * Array de banderas en la que anotar los numero borrados de la DequeConcurrente
	 */
	public static boolean[] resultado;
	
	
	/**
	 * Bandera de aviso de que la escritura ha terminado. Es usado por los hilos lectores/borradores
	 * para dar el trabajo finalizado cuando encuentren la deque vacía
	 */
	public static AtomicBoolean escrituraTerminada;

	
	/**
	 * Contador de inserciones realizadas en la DequeConcurrente
	 */
	public static AtomicInteger inserciones;

	/**
	 * Contador de eliminaciones realizadas en la DequeConcurrente
	 */
	public static AtomicInteger eliminados;

	
	
	/**
	 * MAIN
	 * Se encarga de lanzar la prueba sin límite y la prueba con límite
	 * @param args
	 */
	public static void main(String[] args) {
		pruebaSinLimite();
		System.out.println();
		System.out.println();
		pruebaConLimite();
	}

	
	/**
	 * Ejecuta la prueba creando una DequeConcurrente
	 * sin límite de tamaño.
	 */
	private static void pruebaSinLimite() {

		//configurar valores iniciales
		deque = new DequeConcurrente<Integer>();
		numeroActual = new AtomicInteger(0);
		resultado = new boolean[LIMITE_INSERCIONES];
		escrituraTerminada = new AtomicBoolean(false);
		inserciones = new AtomicInteger(0);
		eliminados = new AtomicInteger(0);

		//crear hilos escritores y lectores
		HiloEscritor[] escritores = new HiloEscritor[N_HILOS];
		HiloLectorBorrador[] lectores = new HiloLectorBorrador[N_HILOS];
		for (int i = 0; i < N_HILOS; i++) {
			escritores[i] = new HiloEscritor();
			lectores[i] = new HiloLectorBorrador();
		}
		
		//lanzar los hilos
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
		//levantar la bandera de escritura terminada
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

	
	/**
	 * Ejecuta la prueba creando una DequeConcurrente
	 * CON límite de tamaño máximo igual a LIMITE_LONGITUD_DEQUE
	 */
	private static void pruebaConLimite() {
		//configurar valores iniciales
		deque = new DequeConcurrente<Integer>(LIMITE_LONGITUD_DEQUE);
		numeroActual = new AtomicInteger(0);
		resultado = new boolean[LIMITE_INSERCIONES];
		escrituraTerminada = new AtomicBoolean(false);
		inserciones = new AtomicInteger(0);
		eliminados = new AtomicInteger(0);

		//crear hilos escritores y lectores
		HiloEscritor[] escritores = new HiloEscritor[N_HILOS];
		HiloLectorBorrador[] lectores = new HiloLectorBorrador[N_HILOS];
		for (int i = 0; i < N_HILOS; i++) {
			escritores[i] = new HiloEscritor();
			lectores[i] = new HiloLectorBorrador();
		}
		
		//lanzar los hilos
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
		//levantar la bandera de escritura terminada
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
