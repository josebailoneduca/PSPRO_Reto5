package reto5a;

import java.util.Iterator;

public class Estadistica {
	private static int[] vecesHambriento;
	private static long[] totalHambriento;
	private static int[] vecesComido;
	private static int[] duenoTenedor;
	private static int[] estadoFilosofo;//0 pensando, 1 habmriento, 2 comiendo
	private static final String[] etiquetas= {"pensando","hambriento","comiendo"};
	
	public static void configurar() {
		vecesHambriento=new int[Config.nFilosofos];
		totalHambriento=new long[Config.nFilosofos];
		vecesComido=new int[Config.nFilosofos];
		duenoTenedor=new int[Config.nFilosofos];
		duenoTenedor=new int[Config.nFilosofos];
		estadoFilosofo=new int[Config.nFilosofos];
	}


	public static void tiempoHambriento(int id, long tiempo) {
		vecesHambriento[id]++;
		totalHambriento[id]+=tiempo;
	}
	
	public static void comiendo(int id) {
		vecesComido[id]++;
		estadoFilosofo[id]=2;
		duenoTenedor[id]=id;
		duenoTenedor[(id+1)%Config.nFilosofos]=id;
		imprimirEstadistica();
	}


	public static void pensando(int id) {
		estadoFilosofo[id]=0;
		duenoTenedor[id]=-1;
		duenoTenedor[(id+1)%Config.nFilosofos]=-1;
		imprimirEstadistica();
	}
	
	public static void hambriento(int id) {
		estadoFilosofo[id]=1;
	}
	
	
	
	synchronized private static void imprimirEstadistica() {
		System.out.println("######################################################################");
		for (int i = 0; i < estadoFilosofo.length; i++) {
			String tenedor="----------------------------------------\n\n";
			if (duenoTenedor[i]==i)
				tenedor="\n\n----------------------------------------";
			if (duenoTenedor[i]==-1)
				tenedor="\n----------------------------------------\n";
			System.out.println(tenedor);
 			System.out.println("Filosofo "+i+"Media hambriento: "+((vecesHambriento[i]==0)?0:(totalHambriento[i]/vecesHambriento[i]))+"ms -- Ha comido:"+vecesComido[i]+" --- Estado:"+etiquetas[estadoFilosofo[i]]);
		}
		
		String tenedor="----------------------------------------\n\n";
		if (duenoTenedor[0]==0)
			tenedor="\n\n----------------------------------------";
		if (duenoTenedor[0]==-1)
			tenedor="\n----------------------------------------\n";
		System.out.println(tenedor);
	}
	
	
}
