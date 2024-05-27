package juego;

// Importaciones
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import entorno.Board;

// Importaciones adicionales
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Timer;

// Clases
import Entidades.Esqueleto;
import Entidades.Player;
import Entidades.Proyectil;
import Entidades.Zombie;
import Estructuras.Bloque;
import Estructuras.Fondo;
import Estructuras.Plataforma;
import Estructuras.Bloque;

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
	private Zombie zombie;
	private Fondo fondo;
	private Plataforma plataforma;
	private Esqueleto esqueleto;
	private Proyectil proyectil;
	private Bloque[] bloques;
	private ArrayList <Proyectil> proyectiles;

	//Fps
	private int temporizador = 0;
	private int fps = 0;
	//private long mTime;

	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);

		// Inicializar lo que haga falta para el juego

		this.jugador = new Player(ANCHO_JUEGO / 2, ALTO_JUEGO - 560, 40, 20, 5.0);
		this.zombie = new Zombie(ALTO_JUEGO / 2, ALTO_JUEGO - 214, 40, 20, 2.5);
		this.esqueleto = new Esqueleto(ALTO_JUEGO / 2, ALTO_JUEGO - 357, 40, 20, 2.0);
		this.fondo = new Fondo(ANCHO_JUEGO, ALTO_JUEGO, 1);
		this.plataforma = new Plataforma(ALTO_JUEGO, ANCHO_JUEGO);
		this.bloques = this.plataforma.getBloques();

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
		long tiempoAnterior = System.nanoTime();

		fondo.actualizar(entorno);
		jugador.actualizar(entorno, this.bloques);
		zombie.actualizar(entorno);
		esqueleto.actualizar(entorno);
		plataforma.actualizar(entorno, this.bloques);

		if (proyectil != null) {
			proyectil.dibujar(entorno);
			proyectil.mover();
			if (proyectil.estaFueraDEPantalla(entorno)) {
				proyectil = null;
			}
		}

		if(entorno.estaPresionada('x') && proyectil == null) {
			proyectil = jugador.disparar();
		}

	}

	/**
	 * La función principal crea una instancia de la clase Juego.
	 */
	
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
