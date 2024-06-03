package juego;

// Importaciones
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.awt.Color;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

// Clases
import Entidades.Esqueleto;
import Entidades.Item;
import Entidades.Player;
import Entidades.Proyectil;
import Entidades.Lobo;
import Entidades.Creeper;
import Estructuras.Bloque;
import Estructuras.Fondo;
import Estructuras.Plataforma;
import Extras.Puntuaciones;
import Extras.counterFPS;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo (Principales)
	private String TITULO_JUEGO = "Volcano Scape - Minecraft Edition (Gonzalez, Ibarra,Bourdet)";
	private int ANCHO_JUEGO = 800;
	private int ALTO_JUEGO = 600;

	// Variables y métodos propios de cada grupo
	private Fondo fondo;
	private Bloque[] bloques;
	private Plataforma plataforma;
	private Player jugador;
	private Proyectil[] proyectilesCre;
	private Proyectil[] proyectilesEsq;
	private Proyectil[] proyectilesJug;
	private Esqueleto[] Esqueletos;
	private Creeper[] Creepers;
	private Puntuaciones puntuaciones;
	private Lobo lobo;
	private Item Items[];	
	private int tick = 0;
	private int Barra_carga = 0;
	private boolean pantalla_carga = true;
	private Creeper creeper_cargando1;
	private Creeper creeper_cargando2;
	private java.awt.Image imagen_carga;
	private java.awt.Image imagen_gameOver;
	private java.awt.Image imagen_gameWin;
	private java.awt.Image imagen_carga_fondo;
	private Random random = new Random();
	private counterFPS fps = new counterFPS();
	private boolean Game_win= false;

	private boolean musica = false;

	private Clip musica_r;

	private Clip musica_Win;
	private boolean Huevo_de_pascua= false;

	private Clip musica_egg;

	private DataLine musica_over;

	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);

		// Inicializar lo que haga falta para el juego

		// Instanciamos los Array de enemigos
		Random random = new Random();
		Creepers = new Creeper[4];
		Esqueletos = new Esqueleto[4];
		Items = new Item[4];
		
		for (int j = 0; j < Esqueletos.length; j++) {
			if (Esqueletos[j] == null) {
				Esqueletos[j] = new Esqueleto((random.nextInt(ANCHO_JUEGO - ANCHO_JUEGO/2)), j == 0 ? ALTO_JUEGO * 0.65 : j == 1 ? ALTO_JUEGO * 0.40 : j >= 2 ? ALTO_JUEGO * 0.15 : ALTO_JUEGO * 0.15, 40, 20, 1.0, "d");
			}
		}

		for (int j = 0; j < Creepers.length; j++) {
			if (Creepers[j] == null) {
				Creepers[j] = new Creeper((ANCHO_JUEGO/2 + random.nextInt( ANCHO_JUEGO/2)), j == 0 ? ALTO_JUEGO * 0.65 : j == 1 ? ALTO_JUEGO * 0.40 : j >= 2 ? ALTO_JUEGO * 0.15 : ALTO_JUEGO * 0.15, 40, 20, 1, "i");
			}

		}

		// Proyectiles
		proyectilesCre = new Proyectil[4];
		proyectilesEsq = new Proyectil[4];
		proyectilesJug = new Proyectil[1];

		this.jugador = new Player(ANCHO_JUEGO / 2, ALTO_JUEGO - 80, 40, 20, 5.0, 3);
		this.fondo = new Fondo(ANCHO_JUEGO, ALTO_JUEGO, 1);
		this.plataforma = new Plataforma(ALTO_JUEGO, ANCHO_JUEGO);
		this.bloques = this.plataforma.getBloques();
		this.puntuaciones = new Puntuaciones(20, 15, 30);

		creeper_cargando1 = new Creeper(410, ALTO_JUEGO / 2, 40, 20, 0.5, "d");
		creeper_cargando2 = new Creeper(390, ALTO_JUEGO / 2, 40, 20, 0.5, "i");
		imagen_carga = Herramientas.cargarImagen("recursos/imagenes/Pantallas/Loading/Volcano-Scape.png");
		imagen_carga_fondo = Herramientas.cargarImagen("recursos/imagenes/Pantallas/Loading/Cargando.jpg");
		imagen_gameWin = Herramientas.cargarImagen("recursos/imagenes/Pantallas/GameWin/GameWin.png");
		imagen_gameOver = Herramientas.cargarImagen("recursos/imagenes/Pantallas/GameOver/GAME-OVER.png");
		musica_r = Herramientas.cargarSonido("recursos/sonido/Minero 8-Bit.wav");
		musica_Win=Herramientas.cargarSonido("recursos/sonido/Ganar.wav");
		musica_egg = Herramientas.cargarSonido("recursos/sonido/GIGACHAD 8BIT.wav");
		musica_over = Herramientas.cargarSonido("recursos/sonido/Music Over.wav");
		

		

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
		fps.iniciar(System.nanoTime());

		// Pantalla de carga
		if (jugador.getVidas() > 0 && Game_win ==false) {
			if (entorno.sePresiono(entorno.TECLA_ENTER) || tick > 500) {
				pantalla_carga = false;
			}

			if (pantalla_carga == true) {
				tick++;
				entorno.dibujarImagen(imagen_carga_fondo, ANCHO_JUEGO / 2, ALTO_JUEGO / 2, 0, 1.5);
				entorno.dibujarRectangulo(ANCHO_JUEGO / 2, ALTO_JUEGO / 2, Barra_carga, 15, 0, Color.GREEN);
				entorno.dibujarImagen(imagen_carga, ANCHO_JUEGO / 2, 200, 0, 0.9);
				creeper_cargando1.Cargando(entorno);
				creeper_cargando2.Cargando(entorno);
				Barra_carga += 1;

				fps.terminar(System.nanoTime());
			} else {
				if (musica == false){
					musica_r.loop(10);
					musica = true;
				}
				
				fondo.actualizar(entorno);

				if (proyectilesJug[0] != null) {
					proyectilesJug[0].dibujar(entorno);
					proyectilesJug[0].mover();

					if (proyectilesJug[0].estaFueraDEPantalla(entorno)) {
						proyectilesJug[0] = null;
					}

					for (int i = 0; i < proyectilesEsq.length; i++) {
						if (proyectilesEsq[i] != null && proyectilesJug[0] != null) {
							if(proyectilesJug[0].getY() == proyectilesEsq[i].getY() 
							&& (proyectilesJug[0].getDerecha() >= proyectilesEsq[i].getIzquierda() && proyectilesJug[0].getDerecha() <= proyectilesEsq[i].getDerecha()
							|| proyectilesJug[0].getIzquierda() <= proyectilesEsq[i].getDerecha() && proyectilesJug[0].getIzquierda() >= proyectilesEsq[i].getIzquierda())){
								proyectilesEsq[i] = null;
								proyectilesJug[0] = null;
							}
						}
					}

					for (int i = 0; i < proyectilesCre.length; i++) {
						if (proyectilesCre[i] != null && proyectilesJug[0] != null) {
							if(proyectilesJug[0].getY() == proyectilesCre[i].getY() 
							&& (proyectilesJug[0].getDerecha() >= proyectilesCre[i].getIzquierda() && proyectilesJug[0].getDerecha() <= proyectilesCre[i].getDerecha()
							|| proyectilesJug[0].getIzquierda() <= proyectilesCre[i].getDerecha() && proyectilesJug[0].getIzquierda() >= proyectilesCre[i].getIzquierda())){
								proyectilesCre[i] = null;
								proyectilesJug[0] = null;
							}
						}
					}
				}

				// Actualizar enemigos y proyectiles de enemigos

				//Creeper
				for (int e = 0; e < Creepers.length; e++) {
					if (Creepers[e] != null) {
						Creepers[e].actualizar(entorno, bloques);

						if (Creepers[e] != null && jugador.getInvulnerable(System.nanoTime()) == false){
							if (Creepers[e].getIzquierda() <= jugador.getDerecha() &&
								Creepers[e].getDerecha() >= jugador.getIzquierda() &&
								Creepers[e].getAbajo() >= jugador.getArriba() &&
								Creepers[e].getArriba() <= jugador.getAbajo()) {
								jugador.setVidas(-1); // Restamos una vida al jugador
								jugador.setInvulnerable(System.nanoTime());
							   }
						}
						if (Creepers[e] != null && proyectilesJug[0] != null
								&& proyectilesJug[0].getX() <= Creepers[e].getDerecha()
								&& proyectilesJug[0].getX() >= Creepers[e].getIzquierda()
								&& proyectilesJug[0].getY() <= Creepers[e].getAbajo()
								&& proyectilesJug[0].getY() >= Creepers[e].getArriba()) {
							proyectilesJug[0] = null;
							int x = random.nextInt(5);
							if (x == 4) {
							Items[0] = new Item("manzana", Creepers[e].getX(), Creepers[e].getY());

							}
							Creepers[e] = null;
							puntuaciones.addPuntos(1);

						}

						if (proyectilesCre[e] == null && Creepers[e] != null) {
							proyectilesCre[e] = Creepers[e].disparar();

						}

						if (proyectilesCre[e] != null) {

							proyectilesCre[e].dibujar(entorno);
							proyectilesCre[e].mover();
							if (proyectilesCre[e] != null && jugador.getInvulnerable(System.nanoTime()) == false && 
									proyectilesCre[e].getX() <= jugador.getDerecha() &&
									proyectilesCre[e].getX() >= jugador.getIzquierda() &&
									proyectilesCre[e].getY() >= jugador.getArriba() &&
									proyectilesCre[e].getY() <= jugador.getAbajo()) {

								proyectilesCre[e] = null;
								jugador.setVidas(-1);
								jugador.setInvulnerable(System.nanoTime());
								jugador.reaparecer(ALTO_JUEGO, ANCHO_JUEGO);
							}

							if (proyectilesCre[e] != null && proyectilesCre[e].estaFueraDEPantalla(entorno)) {
								proyectilesCre[e] = null;
							}
						}
					}

				}

				//Esqueleto
				for (int e = 0; e < Esqueletos.length; e++) {
					if (Esqueletos[e] != null) {
						Esqueletos[e].actualizar(entorno, bloques);
					}
					if (Esqueletos[e] != null && jugador.getInvulnerable(System.nanoTime()) == false){
						if (Esqueletos[e].getIzquierda() <= jugador.getDerecha() &&
							Esqueletos[e].getDerecha() >= jugador.getIzquierda() &&
							Esqueletos[e].getAbajo() >= jugador.getArriba() &&
							Esqueletos[e].getArriba() <= jugador.getAbajo()) {
							jugador.setVidas(-1); // Restamos una vida al jugador
							jugador.setInvulnerable(System.nanoTime());
						}
					}

					if (Esqueletos[e] != null && proyectilesJug[0] != null &&
							proyectilesJug[0].getX() <= Esqueletos[e].getDerecha() &&
							proyectilesJug[0].getX() >= Esqueletos[e].getIzquierda() &&
							proyectilesJug[0].getY() <= Esqueletos[e].getAbajo() &&
							proyectilesJug[0].getY() >= Esqueletos[e].getArriba()) {

						proyectilesJug[0] = null;
						int x = random.nextInt(5);
						if (x == 4) {
						Items[0] = new Item("hueso", Esqueletos[e].getX(), Esqueletos[e].getY());
						}
						Esqueletos[e] = null;
						puntuaciones.addPuntos(1);
					}

					if (proyectilesEsq[e] == null && Esqueletos[e] != null) {
						proyectilesEsq[e] = Esqueletos[e].disparar();

					}

					if (proyectilesEsq[e] != null) {

						proyectilesEsq[e].dibujar(entorno);
						proyectilesEsq[e].mover();

						if (proyectilesEsq[e] != null && jugador.getInvulnerable(System.nanoTime()) == false &&
								proyectilesEsq[e].getX() <= jugador.getDerecha() &&
								proyectilesEsq[e].getX() >= jugador.getIzquierda() &&
								proyectilesEsq[e].getY() >= jugador.getArriba() &&
								proyectilesEsq[e].getY() <= jugador.getAbajo()) {

							proyectilesEsq[e] = null;
							jugador.setVidas(-1);
							jugador.setInvulnerable(System.nanoTime());
							jugador.reaparecer(ALTO_JUEGO, ANCHO_JUEGO);
						}

						if (proyectilesEsq[e] != null && proyectilesEsq[e].estaFueraDEPantalla(entorno)) {
							proyectilesEsq[e] = null;
						}
					}

				}

				// Items
				Items[1] = new Item("Estrella", ANCHO_JUEGO / 2, 10);

				for (int e = 0; e < Items.length; e++) {
					if (Items[e] != null) {
						Items[e].actualizar(entorno);

						if (Items[e] != null&& 
						Items[e].gettipoItem() == "hueso" &&
						Items[e].getIzquierda() <= jugador.getDerecha() &&
						Items[e].getDerecha() >= jugador.getIzquierda() &&
						Items[e].getAbajo() >= jugador.getArriba() &&
						Items[e].getArriba() <= jugador.getAbajo()) {
							Herramientas.cargarSonido("recursos/sonido/Agarra_objeto.wav").start();
							Items[e] = null;
							lobo = new Lobo(jugador.getY(), 900);

						}
						

						if (Items[e] != null&& 
						Items[e].gettipoItem() == "Estrella" &&
						Items[e].getIzquierda() <= jugador.getDerecha() &&
						Items[e].getDerecha() >= jugador.getIzquierda() &&
						Items[e].getAbajo() >= jugador.getArriba() &&
						Items[e].getArriba() <= jugador.getAbajo()) {

							Game_win = true;

						}

						if (Items[e] != null&& 
						Items[e].gettipoItem() == "manzana" &&
						Items[e].getIzquierda() <= jugador.getDerecha() &&
						Items[e].getDerecha() >= jugador.getIzquierda() &&
						Items[e].getAbajo() >= jugador.getArriba() &&
						Items[e].getArriba() <= jugador.getAbajo()) {
							Herramientas.cargarSonido("recursos/sonido/Agarra_objeto.wav").start();
							Items[e] = null;
							jugador.setVidas(1);

							
						}
					}
				}

				for(int e = 1; e <= jugador.getVidas(); e++){
					entorno.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Items/Vida.png"), ANCHO_JUEGO-20-(35*e), 25, 0, 0.2);
				}

				//Mascotas
				if (lobo != null){
					lobo.dibujarse(entorno);
					lobo.mover(jugador.getX(), jugador.getY());
					for(int e = 0; e < Creepers.length; e++){
						if(lobo != null && Creepers[e]!= null && Creepers[e].getIzquierda() <= lobo.getDerecha() &&
						Creepers[e].getDerecha() >= lobo.getIzquierda() &&
						Creepers[e].getAbajo() >= lobo.getArriba() &&
						Creepers[e].getArriba() <= lobo.getAbajo()){

							Creepers [e]=null;
						}
					}
					for(int e = 0; e < Esqueletos.length; e++){
						if(lobo != null && Esqueletos[e]!=null && Esqueletos[e].getIzquierda() <= lobo.getDerecha() &&
						Esqueletos[e].getDerecha() >= lobo.getIzquierda() &&
						Esqueletos[e].getAbajo() >= lobo.getArriba() &&
						Esqueletos[e].getArriba() <= lobo.getAbajo()){

							Esqueletos [e]=null;
						}
					}
				}


				//Elementos en pantalla
				jugador.dibujarse(entorno);
				plataforma.actualizar(entorno, this.bloques, jugador);
				puntuaciones.dibujarse(entorno);

				/// JUGADOR

				if (jugador.getInvulnerable(System.nanoTime()) == true) {
					entorno.dibujarRectangulo(jugador.getX(), jugador.getArriba()-5, jugador.getAncho(), 5, 0, Color.yellow);

				}				
				if (entorno.estaPresionada(entorno.TECLA_DERECHA) && !jugador.colisionoDerecha(this.bloques, entorno)
						&& jugador.getDerecha() < this.ANCHO_JUEGO) {
					jugador.setMira("d");
					jugador.moverDerecha(entorno);
				}

				if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !jugador.colisionoIzquierda(this.bloques)
						&& jugador.getIzquierda() > 0) {
					jugador.setMira("i");
					jugador.moverIzquierda(entorno);
				}

				if (entorno.estaPresionada(entorno.TECLA_SHIFT)) {
					jugador.agachar(entorno, true);
				} else {
					jugador.agachar(entorno, false);
				}

				if (entorno.estaPresionada('x') && jugador.colisionoAbajo(this.bloques) && !jugador.getEstaAgachado() || entorno.estaPresionada(entorno.TECLA_ESPACIO) && jugador.colisionoAbajo(this.bloques) && !jugador.getEstaAgachado()) {
					jugador.setAlturaMaximaSalto(jugador.getY() - 150);
					jugador.setEstaSaltando(true);
				}

				if (entorno.estaPresionada('x') && jugador.colisionoAbajo(this.bloques)
						&& !jugador.getEstaAgachado()) {
					jugador.setAlturaMaximaSalto(jugador.getY() - 150);
					jugador.setEstaSaltando(true);
				}

				if (jugador.getEstaSaltando() == true) {
					if (!jugador.colisionoArriba(this.bloques) && (jugador.getY() >= jugador.getAlturaMaximaSalto())) {
						jugador.moverArriba(entorno);
					} else {
						jugador.setEstaSaltando(false);
					}
				}

				if (jugador.getEstaAgachado()) {
					jugador.setEstaSaltando(false);
				}

				if (jugador.getEstaSaltando() == false) {
					if (!jugador.colisionoAbajo(this.bloques)) {
						jugador.moverAbajo(entorno);
					}
				}

				if (entorno.estaPresionada('c') && proyectilesJug[0] == null && jugador.getInvulnerable(System.nanoTime()) == false) {
					proyectilesJug[0] = jugador.disparar();
				}

			}

			fps.terminar(System.nanoTime());
		} else if(jugador.getVidas<=0 && Game_win == false){

			entorno.dibujarImagen(imagen_gameOver, ANCHO_JUEGO / 2, ALTO_JUEGO / 2, 0, 0.9);
			fps.terminar(System.nanoTime());
			musica_r.stop();
			musica=false;
			if (musica == false){
				musica_over.start();
				musica = true;
			}

		}else{
			entorno.dibujarImagen(imagen_gameWin, ANCHO_JUEGO / 2, ALTO_JUEGO / 2, 0, 0.9);
			fps.terminar(System.nanoTime());
			musica_r.stop();
			musica=false;
			if (musica == false){
				musica_Win.start();
				musica = true;
			}
			if (entorno.sePresiono('g')){
				Huevo_de_pascua=true;
				musica=false;
				musica_Win.stop();
			}
			if (Huevo_de_pascua == true && musica ==false){	
				if (musica == false){
					musica_egg.loop(10);
					musica = true;
			}
			}
			

		}

		fps.terminar(System.nanoTime());
		fps.dibujar(entorno, ANCHO_JUEGO - 80, ALTO_JUEGO - 40);

	}

	/**
	 * La función principal crea una instancia de la clase Juego.
	 */

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();

	}
}
