package juego;

// Importaciones
import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.Random;




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
	private Proyectil[] proyectilesCre;
	private Proyectil[] proyectilesEsq;
	@SuppressWarnings("unused")
	private Proyectil[] proyectilesEst;
	private Esqueleto[] Esqueletos;
	private Creeper[] Creepers;


	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);

		// Inicializar lo que haga falta para el juego

		//Instanciamos los Array de enemigos
		Random random = new Random();
		Creepers = new Creeper[4];
		Esqueletos = new Esqueleto[4];

		for (int j = 0; j < Esqueletos.length; j++){ 
			if (Esqueletos[j] == null) {
				Esqueletos[j] = new Esqueleto((random.nextInt(1)* 100), 532-(random.nextInt(2)* 145), 40, 20, 1.0, random.nextInt());;
		}
		}
		
		for (int j = 0; j < Creepers.length; j++){ 
			if (Creepers[j] == null) {
				Creepers[j] = new Creeper((random.nextInt(3)* 100), 532-(random.nextInt(4)* 145), 40, 20, 1, random.nextInt());
		}
						
		}

		//Proyectiles
		proyectilesCre= new Proyectil[4];
		proyectilesEsq= new Proyectil[4];
		proyectilesEst= new Proyectil[1];
		
		this.jugador = new Player(ANCHO_JUEGO / 2, ALTO_JUEGO - 80, 40, 20, 5.0);
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


		fondo.actualizar(entorno);
		plataforma.actualizar(entorno, this.bloques, jugador);
		jugador.dibujarse(entorno);

		if (proyectil != null) {
			proyectil.dibujar(entorno);
			proyectil.mover();
			if (proyectil.estaFueraDEPantalla(entorno)) {
				proyectil = null;
			}
		}

		//Actualizar enemigos y proyectiles de enemigos
		for (int e = 0; e < Creepers.length; e++) {
			if (Creepers[e] != null) {
				Creepers[e].actualizar(entorno, bloques);

				if (proyectilesCre[e] == null && Creepers[e] != null) {
					proyectilesCre[e] = Creepers[e].disparar();
				
				}

				if (proyectilesCre[e] != null) {
					proyectilesCre[e].dibujar(entorno);
					proyectilesCre[e].mover();
					if (proyectilesCre[e] != null && proyectilesCre[e].getX() <= jugador.getX() + (jugador.getancho_personaje()/2) && proyectilesCre[e].getX() >= jugador.getX() - (jugador.getancho_personaje()/2) && proyectilesCre[e].getY() >= jugador.getY() - (jugador.getalto_personaje()/2) && proyectilesCre[e].getY() <= jugador.getY() + (jugador.getalto_personaje()/2)){
						proyectilesCre[e] = null;
					}
					if (proyectilesCre[e] != null && proyectilesCre[e].estaFueraDEPantalla(entorno)) {
						proyectilesCre[e] = null;
					}
				}
			}

		}

		for (int e = 0; e < Esqueletos.length; e++) {
			if (Esqueletos[e] != null) {
				Esqueletos[e].actualizar(entorno, bloques);
			}

			if (proyectilesEsq[e] == null && Esqueletos[e] != null) {
				proyectilesEsq[e] = Esqueletos[e].disparar();
			
			}

			if (proyectilesEsq[e] != null) {
				proyectilesEsq[e].dibujar(entorno);
				proyectilesEsq[e].mover();
				if (proyectilesEsq[e] != null && proyectilesEsq[e].getX() <= jugador.getX() + (jugador.getancho_personaje()/2) && proyectilesEsq[e].getX() >= jugador.getX() - (jugador.getancho_personaje()/2) && proyectilesEsq[e].getY() >= jugador.getY() - (jugador.getalto_personaje()/2) && proyectilesEsq[e].getY() <= jugador.getY() + (jugador.getalto_personaje()/2)){
					proyectilesEsq[e] = null;
				}
				if (proyectilesEsq[e] != null && proyectilesEsq[e].estaFueraDEPantalla(entorno)) {
					proyectilesEsq[e] = null;
				}
			}

			
		}


		




		/// JUGADOR
		
		
        if (entorno.estaPresionada(entorno.TECLA_DERECHA) && !jugador.colisionoDerecha(this.bloques, entorno) && jugador.getDerecha() < this.ANCHO_JUEGO) {
			jugador.setMira("d");
            jugador.moverDerecha(entorno);
        }

        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !jugador.colisionoIzquierda(this.bloques)&& jugador.getIzquierda() > 0) {
			jugador.setMira("i");
            jugador.moverIzquierda(entorno);
        }

        if (entorno.estaPresionada(entorno.TECLA_SHIFT)) {
            jugador.agachar(entorno, true);
        } else {
            jugador.agachar(entorno, false);
        }

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && jugador.colisionoAbajo(this.bloques)) {
			jugador.setAlturaMaximaSalto(jugador.getY() - 150);
			jugador.setEstaSaltando(true);
        }

		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && jugador.colisionoAbajo(this.bloques)) {
			jugador.setAlturaMaximaSalto(jugador.getY() - 150);
			jugador.setEstaSaltando(true);
        }
		
		if (jugador.getEstaSaltando() == true){
			if(!jugador.colisionoArriba(this.bloques) && (jugador.getY() >= jugador.getAlturaMaximaSalto())){
				jugador.moverArriba(entorno);
			} else {
				jugador.setEstaSaltando(false);
			}
		}

		if (jugador.getEstaSaltando() == false){
			if (!jugador.colisionoAbajo(this.bloques)){
				jugador.moverAbajo(entorno);
			}
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
