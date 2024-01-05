package reto5c;

/**
 * Configuracion del programa
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Config {

	/**
	 * Cantidad de hebras, al margen de la del main que se generan
	 */
	public static final int N_HEBRAS=5;
	
	/**
	 * Minimo tiempo de espera entre el inicio de una hebra y su llegada a la barrera
	 */
	public static final long MIN_ESPERA=0;
	
	/**
	 * Maximo tiempo de espera entre el inicio de una hebra y su llegada a la barrera
	 */
	public static final long MAX_ESPERA=10000;
	
	
}
