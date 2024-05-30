package juego;

// Importaciones
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.awt.Color;
import java.util.Random;

// Clases
import Entidades.Esqueleto;
import Entidades.Item;
import Entidades.Player;
import Entidades.Proyectil;
import Entidades.Creeper;
import Estructuras.Bloque;
import Estructuras.Fondo;
import Estructuras.Plataforma;
import Scores.Puntuaciones;

public class Juego extends InterfaceJuego {
	

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo (Principales)
	private String TITULO_JUEGO = "Escaping Steve, Volcano Edition - Gonzalez Ibarra Bourdet";
	private int ANCHO_JUEGO = 800;
	private int ALTO_JUEGO = 600;

	// Variables y métodos propios de cada grupo
	private Fondo fondo;
	private Bloque[] bloques;
	private Plataforma plataforma;
	private Player jugador;
	private Proyectil[] proyectilesCre;
	private Proyectil[] proyectilesEsq;
	@SuppressWarnings("unused")
	private Proyectil[] proyectilesEst;
	private Esqueleto[] Esqueletos;
	private Creeper[] Creepers;
	private Puntuaciones puntuaciones;	private Item Items[];
	private int tick=0;
	private int Barra_carga = 0;
	private boolean pantalla_carga = true;
	private Creeper creeper_cargando1;
	private Creeper creeper_cargando2;
	private java.awt.Image imagen_carga;
	private java.awt.Image imagen_gameOver;
	private java.awt.Image imagen_carga_fondo;
	private Random random = new Random();

	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);

		// Inicializar lo que haga falta para el juego

		// Instanciamos los Array de enemigos
		Random random = new Random();
		Creepers = new Creeper[4];
		Esqueletos = new Esqueleto[4];
		Items =new Item[4];

		for (int j = 0; j < Esqueletos.length; j++) {
			if (Esqueletos[j] == null) {
				Esqueletos[j] = new Esqueleto((random.nextInt(1) * 100), 532 - (random.nextInt(2) * 145), 40, 20, 1.0,
						random.nextInt());
				;
			}
		}

		for (int j = 0; j < Creepers.length; j++) {
			if (Creepers[j] == null) {
				Creepers[j] = new Creeper((random.nextInt(3) * 100), 532 - (random.nextInt(4) * 145), 40, 20, 1,
						random.nextInt());
			}

		}

		// Proyectiles
		proyectilesCre = new Proyectil[4];
		proyectilesEsq = new Proyectil[4];
		proyectilesEst = new Proyectil[1];

		this.jugador = new Player(ANCHO_JUEGO / 2, ALTO_JUEGO - 80, 40, 20, 5.0,3);
		this.fondo = new Fondo(ANCHO_JUEGO, ALTO_JUEGO, 1);
		this.plataforma = new Plataforma(ALTO_JUEGO, ANCHO_JUEGO);
		this.bloques = this.plataforma.getBloques();
		this.puntuaciones = new Puntuaciones(20, 15, 30);
		// ...

		// this.entorno.cambiarFont("Comics Sans", 500, Color.green);
		creeper_cargando1 = new Creeper(410, ALTO_JUEGO/2, 40, 20, 0.5, 2);
		creeper_cargando2 = new Creeper(390, ALTO_JUEGO/2, 40, 20, 0.5, 3);
		imagen_carga = Herramientas.cargarImagen("recursos/Volcano-Scape.png");
		imagen_carga_fondo = Herramientas.cargarImagen("recursos/Cargando.jpg");

		imagen_gameOver = Herramientas.cargarImagen("recursos/imagenes/GAME-OVER.png");
		
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
		//Pantalla de carga
		if (jugador.getVidas()>0){ 
			if (entorno.sePresiono(entorno.TECLA_ENTER )|| tick >500){
				pantalla_carga = false;
			}


			if (pantalla_carga==true){
				tick++;
				entorno.dibujarImagen(imagen_carga_fondo, ANCHO_JUEGO/2, ALTO_JUEGO/2, 0, 1.5);
				entorno.dibujarRectangulo( ANCHO_JUEGO/2, ALTO_JUEGO/2, Barra_carga, 15, 0, Color.GREEN);
				entorno.dibujarImagen(imagen_carga, ANCHO_JUEGO/2, 200, 0, 0.9);
				creeper_cargando1.Cargando(entorno);
				creeper_cargando2.Cargando(entorno);
				Barra_carga += 1;
			}else{ 

			//Juego 		System.out.println();
			fondo.actualizar(entorno);

			if (proyectilesEst[0] != null) {
				proyectilesEst[0].dibujar(entorno);
				proyectilesEst[0].mover();
				
				if (proyectilesEst[0].estaFueraDEPantalla(entorno)) {
					proyectilesEst[0] = null;
				}
			}

			// Actualizar enemigos y proyectiles de enemigos
			for (int e = 0; e < Creepers.length; e++) {
				if (Creepers[e] != null) {
					Creepers[e].actualizar(entorno, bloques);

					if (Creepers[e] != null && proyectilesEst[0] != null && proyectilesEst[0].getX() <= Creepers[e].getDerecha() && proyectilesEst[0].getX() >= Creepers[e].getIzquierda() && proyectilesEst[0].getY() <= Creepers[e].getAbajo() && proyectilesEst[0].getY() >= Creepers[e].getArriba()){
						proyectilesEst[0] = null;
						Creepers[e]=null;
					}

					if (proyectilesCre[e] == null && Creepers[e] != null) {
						proyectilesCre[e] = Creepers[e].disparar();
					
					}

					if (proyectilesCre[e] != null) {

						proyectilesCre[e].dibujar(entorno);
						proyectilesCre[e].mover();
						if (proyectilesCre[e] != null && proyectilesCre[e].getX() <= jugador.getDerecha() && 
							proyectilesCre[e].getX() >= jugador.getIzquierda() && 
							proyectilesCre[e].getY() >= jugador.getArriba() && 
							proyectilesCre[e].getY() <= jugador.getAbajo()){

							proyectilesCre[e] = null;
							jugador.setVidas(-1);
							jugador.reaparecer(ALTO_JUEGO, ANCHO_JUEGO);
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
				if (Esqueletos[e] != null && proyectilesEst[0] != null && 
					proyectilesEst[0].getX() <= Esqueletos[e].getDerecha() && 
					proyectilesEst[0].getX() >= Esqueletos[e].getIzquierda() && 
					proyectilesEst[0].getY() <= Esqueletos[e].getAbajo() && 
					proyectilesEst[0].getY() >= Esqueletos[e].getArriba()){

					proyectilesEst[0] = null;
					// int x = random.nextInt(0, 5);
					int x = random.nextInt(5);   
					if(x==4){
						Items[0]= new Item("hueso",Esqueletos[e].getX(), Esqueletos[e].getY());

						System.out.println("Tiene que aparecer un hueso");
					}

					Esqueletos[e]=null;
				}

				if (proyectilesEsq[e] == null && Esqueletos[e] != null) {
					proyectilesEsq[e] = Esqueletos[e].disparar();
	
				}

				if (proyectilesEsq[e] != null) {

					proyectilesEsq[e].dibujar(entorno);
					proyectilesEsq[e].mover();

					if (proyectilesEsq[e] != null && 
						proyectilesEsq[e].getX() <= jugador.getDerecha() && 
						proyectilesEsq[e].getX() >= jugador.getIzquierda() && 
						proyectilesEsq[e].getY() >= jugador.getArriba() && 
						proyectilesEsq[e].getY() <= jugador.getAbajo()){

						proyectilesEsq[e] = null;
						jugador.setVidas(-1);
						jugador.reaparecer(ALTO_JUEGO, ANCHO_JUEGO);
					}

					if (proyectilesEsq[e] != null && proyectilesEsq[e].estaFueraDEPantalla(entorno)) {
						proyectilesEsq[e] = null;
					}
				}

				
			}
			//Items
			Items[1]= new Item("Estrella",ANCHO_JUEGO/2, 10);

			for(int e = 0; e < Items.length; e++) {
				if (Items[e] != null) {
					Items[e].actualizar(entorno);
				}
			}


			
			jugador.dibujarse(entorno);
			plataforma.actualizar(entorno, this.bloques, jugador);


			

		/// JUGADOR

		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && !jugador.colisionoDerecha(this.bloques, entorno)
				&& jugador.getDerecha() < this.ANCHO_JUEGO) {
			jugador.setMira("d");
			jugador.moverDerecha(entorno);
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
			
			if(entorno.estaPresionada('x') && proyectilesEst[0] == null) {
				proyectilesEst[0] = jugador.disparar();
			}
			}

		}else{
			entorno.dibujarImagen(imagen_gameOver,ANCHO_JUEGO/2, ALTO_JUEGO/2, 0, 0.9);
			if(entorno.sePresiono('r')){
				jugador.setVidas(3);
			}
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
