package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import entorno.Board;

import Entidades.Player;



@SuppressWarnings("unused")
public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	private String TITULO_JUEGO = "Super Elizabeth Sis, Volcano Edition - Gonzalez Ibarra Bourdet";
	private int ANCHO_JUEGO = 800;
	private int ALTO_JUEGO = 600;


	private Player jugador;

	// Variables y métodos propios de cada grupo
	// ...
	
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);
		
		// Inicializar lo que haga falta para el juego
		// ...

		this.jugador = new Player(ANCHO_JUEGO/2,ALTO_JUEGO/2, 40, 20, 5.0);

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */


	public void tick() {
		jugador.dibujarse(entorno);
		jugador.caer(entorno);
		
		if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			jugador.moverDerecha(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			jugador.moverIzquierda(entorno);
		}
		
	}
	



	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
