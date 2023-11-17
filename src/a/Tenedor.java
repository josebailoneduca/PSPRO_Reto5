package a;

import java.util.concurrent.Semaphore;

public class Tenedor {

	Semaphore ocupado=new Semaphore(1);
	
	public void coger(){
		try {
			ocupado.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void dejar() {
		ocupado.release();
	}
	
	
}
