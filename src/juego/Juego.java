package juego;

// Importaciones
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import entorno.Board;

// Importaciones adicionales
import java.awt.Color;
import java.awt.Image;

import Entidades.Esqueleto;
// Clases
import Entidades.Player;
import Entidades.Proyectil;
import Entidades.Zombie;
import Estructuras.Bloque;
import Estructuras.Fondo;
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
	private Bloque[] bloques;
	private Esqueleto esqueleto;
	private Proyectil prooyectil;

	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);

		// Inicializar lo que haga falta para el juego

		this.jugador = new Player(ANCHO_JUEGO / 2, ALTO_JUEGO - 100, 40, 20, 5.0);
		this.zombie = new Zombie(ALTO_JUEGO / 2, ALTO_JUEGO - 230, 40, 20, 2.5);
		this.esqueleto = new Esqueleto(ALTO_JUEGO / 2, ALTO_JUEGO - 410, 40, 20, 2.0);
		this.fondo = new Fondo(ANCHO_JUEGO, ALTO_JUEGO, 1);
		this.bloques = new Bloque[] {
				new Bloque(20, ALTO_JUEGO - 25, 50, 50, false),
				new Bloque(20, ALTO_JUEGO * 0.70, 50, 50, false),
				new Bloque(20, ALTO_JUEGO * 0.40, 50, 50, false),
				new Bloque(20, ALTO_JUEGO * 0.10, 50, 50, false)

		};
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
		zombie.actualizar(entorno);
		esqueleto.actualizar(entorno);
		// prooyectil.dibujarse(entorno);
		for (Bloque bloque : bloques) {
			bloque.dibujarBloque(entorno);
		}
	}

	/**
	 * La función principal crea una instancia de la clase Juego.
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
