package reto5c;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * Cada participante tiene un id y una referencia al cyclickbarrier.
 * La carrera consiste en escribir su propia id en el array en indice=id
 * y esperar la cyclickbarrier
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Participante implements Runnable {
	
	/**
	 * Referencia a la cyclickbarrier
	 */
	private CyclicBarrier cb;
	
	/**
	 * Id del hilo
	 */
	private int id;

	
	/**
	 * Constructor
	 * @param id Id del hilo
	 * @param cb Referencia a la CyclickBarrier
	 */
	public Participante(int id, CyclicBarrier cb) {

		this.cb = cb;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.println("Inicio hebra: "+id);
			//escribir id en el array
			MainReto5C.arrayResultado[id]=id;
			//espera aleatoria
			Thread.sleep(new Random().nextLong(Config.MIN_ESPERA, Config.MAX_ESPERA));
			System.out.println("LLegada a la barrera hebra: "+id);
			//esperar la cyclickbarrier
			cb.await();
			System.out.println("Paso de la barrera hebra: "+id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
