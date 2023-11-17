package a;

import java.util.Random;

public class Filosofo extends Thread{
	Random r;
	int id;

	public Filosofo(int id) {
		this.id = id;
		this.r=new Random();
	}

	@Override
	public void run() {
		while(true) {
		pensar();
		cogerTenedores();
		comer();
		soltarTenedores();
		}
		
		
	
	}

	private void soltarTenedores() {
		Estadistica.pensando(this.id);
		if (this.id%2==0) {
			Config.tenedores[id].dejar();
			Config.tenedores[(id+1)%Config.nFilosofos].dejar();
		}else {
			Config.tenedores[(id+1)%Config.nFilosofos].dejar();
			Config.tenedores[id].dejar();
		}
		
	}

	private void cogerTenedores() {
		long ini = System.currentTimeMillis();
		Estadistica.hambriento(id);
		if (this.id%2==0) {
			Config.tenedores[id].coger();
			Config.tenedores[(id+1)%Config.nFilosofos].coger();
		}else {
			Config.tenedores[(id+1)%Config.nFilosofos].coger();
			Config.tenedores[id].coger();
		}
		Estadistica.tiempoHambriento(id, System.currentTimeMillis()- ini);
	}

	private void comer() {
		Estadistica.comiendo(this.id);
		try {
			sleep(r.nextInt(Config.minComer, Config.maxComer));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void pensar() {
		try {

			sleep(r.nextInt(Config.minPensar, Config.maxPensar));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
}
