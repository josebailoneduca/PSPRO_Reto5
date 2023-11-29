package reto5c;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;


public class MainReto5C {
public static CyclicBarrier cb ;
public static int[]arrayResultado=new int[Config.N_HEBRAS];
	public static void main(String[] args) {
	 
		//creacion de la barrera, N+1 para incluir al hilo principal
		//paso del runable de accion final como expresion lambda que suma el contenido del arrayResultado
		cb= new CyclicBarrier(Config.N_HEBRAS+1,()->{
			int suma=0;
			for(int i=0;i<MainReto5C.arrayResultado.length;i++) {
				suma+=MainReto5C.arrayResultado[i];
			}
			System.out.println("\nEjecución de la barrierAction. Resultado de la suma del array:"+suma+"\n");
		});
		
		
		//lanzar los hilos	
		Thread[] ts = new Thread[Config.N_HEBRAS];
		for (int i=0;i<Config.N_HEBRAS;i++) {
			ts[i]=new Thread(new Participante(i, cb));
			ts[i].start();
		}

		//hacer que el hilo principal espera tambien a la barrera
		try {
			cb.await();
			System.out.println("Paso de la barrera por parte de main");
		} catch (InterruptedException e) {
		   e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}

