package reto5c;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;


public class MainReto5C {
//public static CyclicBarrier cb ;
public static CountDownLatch cb ;
	public static void main(String[] args) {
	 
		//CountDownLatch 
		//CyclicBarrier 
		int total=5;
		Thread[] ts = new Thread[total];
		//cb= new CyclicBarrier(total);
		cb=new CountDownLatch(total);
			
		for (int i=0;i<total;i++)
			ts[i]=new Thread(new Test(i));

		for (int i=0;i<total;i++)
			ts[i].start();
			
	}

}

