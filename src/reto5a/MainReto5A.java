package reto5a;


/**
 * Punto de entrada del programa
 * Configura la mesa, los filosofos y los tenedores
 * Inicia la carrera de los filosofos
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class MainReto5A {


public static void main(String[] args) {
	Mesa.tenedores=new Tenedor[Config.N_FILOSOFOS];
	Mesa.filosofos=new Filosofo[Config.N_FILOSOFOS];
	Estadistica.configurar();

	//crear filosofos y tenedores
	for (int i=0;i<Config.N_FILOSOFOS;i++) {
		Mesa.tenedores[i]=new Tenedor();		
	}
	for (int i=0;i<Config.N_FILOSOFOS;i++) {
		Mesa.filosofos[i]=new Filosofo(i);
	}
	
	//iniciar actividad de los filosofos
	for (int i=0;i<Config.N_FILOSOFOS;i++) {		
		Mesa.filosofos[i].start();
	}
}
}
