package reto5a;

import java.util.Iterator;


/**
 * Imprime estadisticas en pantalla sobre la actividad de los filosofos y la pertenencia de los tenedores
 */
public class Estadistica {
	
	/**
	 * array de veces que se ha estado habriento
	 */
	private static int[] vecesHambriento;
	
	/**
	 * array del total de tiempo que se ha estado hambriento
	 */
	private static long[] totalHambriento;
	
	/**
	 * array del total de veces que se ha comido
	 */
	private static int[] vecesComido;
	
	/**
	 * array que registra que filosofo es dueño de cada tenedor
	 */
	private static int[] duenoTenedor;
	
	/**
	 * estado actual de los filosofos
	 */
	private static int[] estadoFilosofo;//0 pensando, 1 habmriento, 2 comiendo
	
	/**
	 * etiquetas de los estados
	 */
	private static final String[] etiquetas= {"pensando","hambriento","comiendo"};
	
	
	/**
	 * Crear los arrays segun el numero de filosofos
	 */
	public static void configurar() {
		vecesHambriento=new int[Config.N_FILOSOFOS];
		totalHambriento=new long[Config.N_FILOSOFOS];
		vecesComido=new int[Config.N_FILOSOFOS];
		duenoTenedor=new int[Config.N_FILOSOFOS];
		duenoTenedor=new int[Config.N_FILOSOFOS];
		estadoFilosofo=new int[Config.N_FILOSOFOS];
	}


	/**
	 * Registra el tiempo que ha estado un filosofo hambriento
	 * @param id Id del filosofo
	 * @param tiempo Tiempo a agregar
	 */
	public static void tiempoHambriento(int id, long tiempo) {
		vecesHambriento[id]++;
		totalHambriento[id]+=tiempo;
	}
	
	/**
	 * Defive un filosofo como comiendo
	 * @param id Id del filosofo
	 */
	public static void comiendo(int id) {
		vecesComido[id]++;
		estadoFilosofo[id]=2;
		imprimirEstadistica();
	}


	/**
	 * Define un filosofo como pensando
	 * @param id Id del filosofo
	 */
	public static void pensando(int id) {
		estadoFilosofo[id]=0;
		imprimirEstadistica();
	}
	
	/**
	 * Define un filosofo como pensando
	 * @param idfilosofo Id del filosofo
	 * @param idtenedor Id del tenedor
	 */
	public static void cogerTenedor(int idfilosofo, int idtenedor) {
		duenoTenedor[idtenedor]=idfilosofo;
		imprimirEstadistica();
	}
	/**

	 * Define un filosofo como pensando
	 * @param idtenedor Id del tenedor
	 */
	public static void soltarTenedor(int idtenedor) {
		duenoTenedor[idtenedor]=-1;
		imprimirEstadistica();
	}
	
	/**
	 * Define un filosofo como hambriento
	 * @param id Id del filosofo
	 */
	public static void hambriento(int id) {
		estadoFilosofo[id]=1;
		imprimirEstadistica();
	}
	
	
	
	/**
	 * Impime la estadistica segun los datos actuales
	 */
	synchronized private static void imprimirEstadistica() {
		System.out.println("\n\n\n\n\n\n\n######################################################################");
		for (int i = 0; i < estadoFilosofo.length; i++) {
			String tenedor="(tenedor: "+i+" )\n\n";
			if (duenoTenedor[i]==i)
				tenedor="\n\n(tenedor: "+i+" )";
			if (duenoTenedor[i]==-1)
				tenedor="\n(tenedor: "+i+" )\n";
			System.out.println(tenedor);
 			System.out.println("Filosofo "+i+" Media hambriento: "+((vecesHambriento[i]==0)?0:(totalHambriento[i]/vecesHambriento[i]))+"ms -- Ha comido:"+vecesComido[i]+" --- Estado:"+etiquetas[estadoFilosofo[i]]);
		}
		
		String tenedor="(tenedor: 0 )\n\n";
		if (duenoTenedor[0]==0)
			tenedor="\n\n(tenedor: 0 )";
		if (duenoTenedor[0]==-1)
			tenedor="\n(tenedor: 0 )\n";
		System.out.println(tenedor);
	}
	
	
}
