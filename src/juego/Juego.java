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

import Entidades.Esqueleto;
// Clases
import Entidades.Player;
import Entidades.Proyectil;
import Entidades.Zombie;
import Estructuras.Bloque;
import Estructuras.Fondo;
import Extras.Fps;
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
	private Proyectil proyectil;
	//private static ArrayList <Proyectil> proyectiles;
	
	//Fps
	private int temporizador = 0;
	private int fps = 0;
	//private long mTime;

	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, this.TITULO_JUEGO, ANCHO_JUEGO, ALTO_JUEGO);
		
		// Inicializar lo que haga falta para el juego
		
		this.jugador = new Player(ANCHO_JUEGO/2,ALTO_JUEGO*0.96, 40, 20, 5.0);
		this.zombie = new Zombie(ALTO_JUEGO/2, ALTO_JUEGO*0.97, 40, 20, 2.5);
		this.esqueleto = new Esqueleto(ALTO_JUEGO/2, ALTO_JUEGO*0.97, 40, 20, 2.0);
		this.fondo = new Fondo(ANCHO_JUEGO, ALTO_JUEGO, 1);
		this.bloques = new Bloque[]{
			new Bloque(ANCHO_JUEGO, ALTO_JUEGO, 20, ALTO_JUEGO*0.05, 50, 50, false, Color.green),
			new Bloque(ANCHO_JUEGO, ALTO_JUEGO, 20, ALTO_JUEGO*0.30, 50, 50, false, Color.green),
			new Bloque(ANCHO_JUEGO, ALTO_JUEGO, 20, ALTO_JUEGO*0.55, 50, 50, false, Color.green),
			new Bloque(ANCHO_JUEGO, ALTO_JUEGO, 20, ALTO_JUEGO*0.85, 50, 50, false, Color.green)

		};

		


		
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
		zombie.actualizar(entorno);
		esqueleto.actualizar(entorno);
		for (Bloque bloque : bloques) {
			bloque.dibujarBloque(entorno);
		}
		jugador.actualizar(entorno);

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




		// if (proyectil != null) {
		// 	proyectil.dibujar(entorno);
		// 	proyectil.mover();
		// 	if (proyectil.estaFueraDEPantalla(entorno)) {
		// 		proyectil = null;
		// 	}
		// }


	

	    
		//Fps
		long tiempoActual = System.nanoTime();
		temporizador +=tiempoActual-tiempoAnterior;
		fps++;
		
		if (temporizador >= 1000000000){
            System.out.println("fps: " + fps);
			fps = 0;
			temporizador = 0;
			
        }

		
	}
	


	/**
	 * La función principal crea una instancia de la clase Juego.
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
