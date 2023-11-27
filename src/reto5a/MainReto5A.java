package reto5a;


/**
 * Punto de entrada del programa
 * Configura la mesa, los filosofos y los tenedores
 * Inicia la carrera de los filosofos
 */
public class MainReto5A {


public static void main(String[] args) {
	Mesa.tenedores=new Tenedor[Config.nFilosofos];
	Mesa.filosofos=new Filosofo[Config.nFilosofos];
	Estadistica.configurar();

	//crear filosofos y tenedores
	for (int i=0;i<Config.nFilosofos;i++) {
		Mesa.tenedores[i]=new Tenedor();		
	}
	for (int i=0;i<Config.nFilosofos;i++) {
		Mesa.filosofos[i]=new Filosofo(i);
	}
	
	//iniciar actividad de los filosofos
	for (int i=0;i<Config.nFilosofos;i++) {		
		Mesa.filosofos[i].start();
	}
}
}
