package Entidades;

import entorno.Entorno;
import java.awt.Color;

public class Player {
    private double x;
    private double y;
    private int alto_personaje;
    private int ancho_personaje;
    private boolean estaAgachado = false;
    protected double velocidad;
    protected double velocidadSalto = 8;
    protected double velocidadCaida = 3;

    public Player(double x, double y, int alto_personaje, int ancho_personaje, double velocidad){
        this.x = x;
        this.y = y;
        this.alto_personaje = alto_personaje;
        this.ancho_personaje = ancho_personaje;
        this.velocidad = velocidad;

    }

    public void dibujarse(Entorno e) {
        e.dibujarRectangulo(x, y, ancho_personaje, alto_personaje, 0, Color.red);
    }

    public void moverDerecha(Entorno e) {
        this.x += velocidad;
    }

    public void moverIzquierda(Entorno e) {
        this.x -= velocidad;
    }

    public void caer(Entorno e) {
        this.y = y+velocidadCaida;
    }

    double yAnterior;
    public void saltar(Entorno e) {
       
        for(int i=0; i <= 16.5; i++){
            velocidadSalto= velocidadSalto-2.3;
            this.y = y+velocidadSalto;
        }
    }

    public void agachar(Entorno e, boolean presionado) {

        if(presionado && !estaAgachado) {
            this.estaAgachado = true;
            this.alto_personaje /= 2;
            velocidad =0;//Para que el personaje no se mueva mientas esta agachado sino muy OP
        }

        if(!presionado && estaAgachado) {
            this.estaAgachado = false;
            this.alto_personaje *= 2;
            this.y -= alto_personaje/4;
            this.velocidad = 5.0;//Para que el personaje se vuelva a mover a la misma velociadad, no se como hacer para que reciba la velocidad del constructor
    
        }
    }

    public void disparar(Entorno e){

        Proyectil proyectil = new Proyectil(x, y-7, 10, 10, velocidad+2);
        proyectil.dibujarse(e);
        proyectil.moverDerecha();

    }
    

/**
 * Esta función de Java comprueba si hay colisiones con un borde específico en un entorno de juego.
 * 
 * @param borde El parámetro "borde" en el método "colicionaBorde" representa el lado del borde en el
 * que desea comprobar si hay colisión. Puede tener valores de "arriba" (arriba), "abajo" (abajo),
 * "izquierda" (izquierda) o "derecha"
 * @param e El parámetro `e` en el método `colicionaBorde` es de tipo `Entorno`. Parece representar
 * algún tipo de entorno o contexto dentro del cual se realiza la detección de colisiones. El método
 * utiliza el parámetro `e` para verificar los límites de este entorno al determinar si un
 * @return El método `colicionaBorde` devuelve un valor booleano basado en si el objeto choca con el
 * borde especificado (`arriba`, `abajo`, `izquierda`, `derecha`) en el objeto `Entorno` dado `e`. Si
 * el objeto choca con el borde especificado, devuelve "verdadero"; de lo contrario, devuelve "falso".
 * Si no se reconoce el borde especificado, lanza una excepción de `IllegalArgumentException`.
 * 
 */
    public boolean colicionaBorde(String borde, Entorno e){
        if (borde == "arriba"){
            if((this.y - alto_personaje/2) > 0){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "abajo"){
            if((this.y + alto_personaje/2) < e.alto()){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "izquierda"){
            if((this.x - ancho_personaje/2) > 0){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "derecha"){
            if((this.x + ancho_personaje/2) < e.ancho()){
                return true;
            } else {
                return false;
            }
        }

        throw new IllegalArgumentException("Borde invalido");
    }

     /*
      * El metodo actualizar() recibe un Entorno, y 
      * se encarga de actualizar todos elementos y/o acciones de la clase Player 
      * (Extencion de tick() de la clase Juego).
      */

    public void actualizar(Entorno e) {
		this.dibujarse(e);

		if(e.estaPresionada(e.TECLA_DERECHA) && colicionaBorde("derecha", e)) {
			this.moverDerecha(e);
		} 

		if(e.estaPresionada(e.TECLA_IZQUIERDA) && colicionaBorde("izquierda", e)) {
			this.moverIzquierda(e);
		} 

        if (e.estaPresionada('x')) {
            this.agachar(e, true);
        } else {
            this.agachar(e, false);
        }

        if (e.estaPresionada('c')) {
            this.disparar(e);
        } 

        if(e.sePresiono(e.TECLA_ESPACIO) && !colicionaBorde("abajo", e)) {
            this.saltar(e);
            velocidadSalto = 8;
        }

        if (colicionaBorde("abajo", e)) { 
			this.caer(e);
		}
    }


}   
