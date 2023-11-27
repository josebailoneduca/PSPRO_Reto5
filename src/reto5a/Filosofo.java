package reto5a;

import java.util.Random;


/**
 * Representa a los filosofos. Guarda referencias a cual es el tenedor derecho e izquierdo
 * En su carrera pasa por la fase de pensar, coger tenedores, comer y soltar tenedores.
 */
public class Filosofo extends Thread{
	
	//Objto para hacer random
	private Random r;
	
	//id del filosofo
	private int id;
	
	//referencia al tenedor derecho
	private Tenedor tenedorDerecho = Mesa.tenedores[id];
	
	//referencia al tenedor izquierdo
	private Tenedor tenedorIzquierdo=Mesa.tenedores[(id+1)%Config.nFilosofos];

	
	/**
	 * Constructor
	 * @param id Id del filosofo
	 */
	public Filosofo(int id) {
		this.id = id;
		this.r=new Random();
	}

	/**
	 * Carrera. Bucle infinito
	 */
	@Override
	public void run() {
		while(true) {
		pensar();
		cogerTenedores();
		comer();
		soltarTenedores();
		}
		
		
	
	}

	/**
	 * Suelta los tenedores
	 */
	private void soltarTenedores() {
			tenedorDerecho.dejar();
			tenedorIzquierdo.dejar();
			//aviso a estadistica de que suelta ambos tenedores
			Estadistica.soltarTenedor(id);
			Estadistica.soltarTenedor((id+1)%Config.nFilosofos);
	}

	/**
	 * Coge los tenedores. Primero coge el derecho esperando hasta tenerlo
	 * Una vez tiene el derecho intenta coger el izquierdo. Si no puede obtenerlo suelta el tenedor derecho
	 * espera un tiempo aleatorio y vuelve a intentar cogerlos
	 */
	private void cogerTenedores() {
		long ini = System.currentTimeMillis();
		//avisa a estadistica que esta habmriento
		Estadistica.hambriento(id);
		
		boolean segundoTenedorCogido=false;
		//intentar coger ambos tenedores
		while (!segundoTenedorCogido) {
			//esperar a coger el primero
			tenedorDerecho.cogerSeguro();
			//avisa a estadistica que ha cogido el tenedor derecho
			Estadistica.cogerTenedor(id, id);
			//intentar coger el segundo
			segundoTenedorCogido=tenedorIzquierdo.cogerSiSePuede();
			//si el segundo no se ha cogido soltar el primero y esperar un tiempo aleatorio
			if (!segundoTenedorCogido) {
				tenedorDerecho.dejar();
				try {
					Thread.sleep(r.nextLong(Config.minEspera,Config.maxEspera));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		//Avisa a estadistica  que ha cogido el tenedor izquierdo
		Estadistica.cogerTenedor(id, (id+1)%Config.nFilosofos);
		//estadisitica aviso de tiempo hambriento
		Estadistica.tiempoHambriento(id, System.currentTimeMillis()- ini);
	}

	
	/**
	 * Simula el tiempo de comer
	 */
	private void comer() {
		//avisa a estadistica que esta comiendo
		Estadistica.comiendo(this.id);
		try {
			sleep(r.nextInt(Config.minComer, Config.maxComer));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Simula el tiempo de pensar
	 */
	private void pensar() {
		try {
			//avisa a estadistica que esta pensando
			Estadistica.pensando(this.id);
			sleep(r.nextInt(Config.minPensar, Config.maxPensar));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
}
