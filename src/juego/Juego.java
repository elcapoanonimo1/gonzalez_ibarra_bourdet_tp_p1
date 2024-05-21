package juego;

// Importaciones
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import entorno.Board;

// Importaciones adicionales
import java.awt.Color;
import java.awt.Image;

// Clases
import Entidades.Player;
import Estructuras.Fondo;



@SuppressWarnings("unused")
public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	

	// Variables y métodos propios de cada grupo (Principales)
	private String TITULO_JUEGO = "Super Elizabeth Sis, Volcano Edition - Gonzalez Ibarra Bourdet";
	private int ANCHO_JUEGO = 800;
	private int ALTO_JUEGO = 600;
	
	// Variables y métodos propios de cada grupo 
	private Player jugador;
	private Fondo fondo;
	
	// ...
	

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);
		
		// Inicializar lo que haga falta para el juego
		
		this.jugador = new Player(ANCHO_JUEGO/2,ALTO_JUEGO*0.96, 40, 20, 5.0);
		this.fondo = new Fondo(ANCHO_JUEGO, ALTO_JUEGO, 1);
		
		// ...

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
		fondo.actualizar(entorno);
		jugador.actualizar(entorno);
	}
	


	/**
	 * La función principal crea una instancia de la clase Juego.
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
