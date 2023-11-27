package reto5c;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Test implements Runnable {

	int id;
	
	public Test(int id) {
		this.id=id;
	}

	@Override
	public void run() {


		for (int x=0;x<5;x++){
			try {
				System.out.println(id+" inicia");
				Thread.currentThread().sleep((id==4)?0:10000);
				System.out.println(id+" terminar");
				//MainReto5C.cb.await();
				MainReto5C.cb.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			//} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
//			if (MainReto5C.cb.isBroken()) {
//				//MainReto5C.cb.reset();
			
				System.out.println("----------");
//			}
			
		}
	}

	
	
}
