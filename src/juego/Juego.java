package juego;

// Importaciones
import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;
import java.util.random.*;




// Clases
import Entidades.Esqueleto;
import Entidades.Player;
import Entidades.Proyectil;
import Entidades.Creeper;
import Estructuras.Bloque;
import Estructuras.Fondo;
import Estructuras.Plataforma;



public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo (Principales)
	private String TITULO_JUEGO = "Super Elizabeth Sis, Volcano Edition - Gonzalez Ibarra Bourdet";
	private int ANCHO_JUEGO = 800;
	private int ALTO_JUEGO = 600;

	// Variables y métodos propios de cada grupo
	private Fondo fondo;
	private Bloque[] bloques;
	private Plataforma plataforma;
	private Player jugador;
	private Proyectil proyectil;
	private Creeper zombie, zombie2;
	private Esqueleto esqueleto, esqueleto2;
	private Esqueleto[] Esqueletos;
	private Creeper[] Creepers;


	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);

		// Inicializar lo que haga falta para el juego
		Random random = new Random();
		Creepers = new Creeper[4];
		
		for (int j = 0; j < Creepers.length; j++){ 
			if (Creepers[j] == null) {
				Creepers[j] = new Creeper(ALTO_JUEGO / 2 + (random.nextInt(1, 8)* 100), ALTO_JUEGO - 214, 40, 20, 1);
		}
		}
		

		this.jugador = new Player(ANCHO_JUEGO / 2, ALTO_JUEGO - 150, 40, 20, 5.0);
		//this.creeper = new Creeper(ALTO_JUEGO / 2, ALTO_JUEGO - 214, 40, 20, 1);
		//this.creeper2 = new Creeper(ALTO_JUEGO / 2 + 300, ALTO_JUEGO - 214, 40, 20, 1);
		this.esqueleto = new Esqueleto(ALTO_JUEGO / 2, ALTO_JUEGO - 357, 40, 20, 1.0);
		this.esqueleto2 = new Esqueleto(ALTO_JUEGO / 2 + 300, ALTO_JUEGO - 357, 40, 20, 1.0);
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

		for (int e = 0; e < Creepers.length; e++) {
			if (Creepers[e] != null) {
				Creepers[e].actualizar(entorno);
			}
		}

		fondo.actualizar(entorno);
		//creeper.actualizar(entorno);
		//creeper2.actualizar(entorno);
		esqueleto.actualizar(entorno);
		esqueleto2.actualizar(entorno);
		plataforma.actualizar(entorno, this.bloques);





		if (proyectil != null) {
			proyectil.dibujar(entorno);
			proyectil.mover();
			if (proyectil.estaFueraDEPantalla(entorno)) {
				proyectil = null;
			}
		}




		/// JUGADOR

		jugador.dibujarse(entorno);

        if (entorno.estaPresionada(entorno.TECLA_DERECHA) && jugador.colisionoDerecha(this.bloques, entorno)) {
            System.out.println("me voy a la derecha");
            jugador.setMira("d");
            jugador.moverDerecha(entorno);
        }

        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && jugador.colisionoIzquierda(this.bloques)) {
            System.out.println("me voy a la izquierda");

            jugador.setMira("i");
            jugador.moverIzquierda(entorno);
        }

        if (entorno.estaPresionada(entorno.TECLA_SHIFT)) {
            jugador.agachar(entorno, true);
        } else {
            jugador.agachar(entorno, false);
        }

        if (!jugador.colisionoAbajo(this.bloques)) {
            jugador.caer(entorno);
        } else {
			jugador.setPuedeSaltar(true);
        }

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && !jugador.colisionoArriba(this.bloques) && jugador.colisionoAbajo(this.bloques)) {
            jugador.saltar(entorno, this.bloques);
        }

		if(entorno.estaPresionada('x') && proyectil == null) {
			proyectil = jugador.disparar();
		}

	}

	/**
	 * La función principal crea una instancia de la clase Juego.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
