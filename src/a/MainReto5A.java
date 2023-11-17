package a;

public class MainReto5A {


public static void main(String[] args) {
	Config.tenedores=new Tenedor[Config.nFilosofos];
	Config.filosofos=new Filosofo[Config.nFilosofos];
	Estadistica.configurar();
	
	for (int i=0;i<Config.nFilosofos;i++) {
	Config.tenedores[i]=new Tenedor();		
}
	for (int i=0;i<Config.nFilosofos;i++) {
		Config.filosofos[i]=new Filosofo(i);
		
	}
	for (int i=0;i<Config.nFilosofos;i++) {
			
		Config.filosofos[i].start();
	}
}
}
