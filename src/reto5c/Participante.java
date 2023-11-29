package reto5c;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Participante implements Runnable {
	private CyclicBarrier cb;
	private int id;

	public Participante(int id, CyclicBarrier cb) {

		this.cb = cb;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.println("Inicio hebra: "+id);
			MainReto5C.arrayResultado[id]=id;
			Thread.sleep(new Random().nextLong(Config.MIN_ESPERA, Config.MAX_ESPERA));
			System.out.println("LLegada a la barrera hebra: "+id);
			cb.await();
			System.out.println("Paso de la barrera hebra: "+id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
