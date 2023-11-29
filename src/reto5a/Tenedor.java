package reto5a;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Representa un tenedor. Contiene un semaforo binario indicando si el tenedor esta libre o no.
 */
public class Tenedor {

	/**
	 * Semaforo binario de acceso al tenedor
	 */
	private Semaphore libre=new Semaphore(1);

	/**
	 * Coger el tenedor. Espera hasta que se abra el semaforo
	 */
	public void cogerSeguro(){
		  try {
			libre.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
}
	
	/**
	 * Coge el tenedor si se abre el semáforo dentro del tiempo especificado en la configuracion
	 * @return True si se ha cogido el tenedor y False si no se ha cogido
	 */
	public boolean cogerSiSePuede(){
		 
				return libre.tryAcquire();
		 
	}
	
	
	/**
	 * Dejar el semaforo
	 */
	public void dejar() {
		libre.release();
	}
	
	
}
