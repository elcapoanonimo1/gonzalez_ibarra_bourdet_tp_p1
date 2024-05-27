package Entidades;

import entorno.Entorno;
import java.awt.Color;
import Estructuras.Bloque;

import Estructuras.Plataforma;

public class Player {
    private double x;
    private double y;
    private int alto_personaje;
    private int ancho_personaje;
    private boolean estaAgachado = false;
    private boolean estaSaltando = false;
    private boolean puedeSaltar = true;
    protected double velocidad;
    protected double velocidadSalto = 5;
    protected double velocidadCaida = 10;
    protected double xAnterior = 0;
    protected String mira = "i";

    public Player(double x, double y, int alto_personaje, int ancho_personaje, double velocidad) {
        this.x = x;
        this.y = y;
        this.alto_personaje = alto_personaje;
        this.ancho_personaje = ancho_personaje;
        this.velocidad = velocidad;

    }

    // DIBUJARSE
    public void dibujarse(Entorno e) {
        e.dibujarRectangulo(x, y, ancho_personaje, alto_personaje, 0, Color.red);
    }

    ///////// GETERS Y SETERS /////////

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getArriba() {
        return (this.y - alto_personaje / 2);
    }

    public double getIzquierda() {
        return (this.x - ancho_personaje / 2);
    }

    public double getDerecha() {
        return (this.x + ancho_personaje / 2);
    }

    public double getAbajo() {
        return (this.y + alto_personaje / 2);
    }

    //////// MOVIMIENTOS ////////

    public void moverDerecha(Entorno e) {
        this.x += velocidad;
    }

    public void moverIzquierda(Entorno e) {
        this.x -= velocidad;
    }

    public void caer(Entorno e) {
        if (getAbajo() > e.alto()) {
            this.y = ((e.alto() - alto_personaje / 2));
        }
        this.y = y + velocidadCaida;
    }

    public void saltar(Entorno e) {
        int alturaSalto = 250;
        if (!estaSaltando && getArriba() > e.alto() - alturaSalto) {
            this.estaSaltando = true;
            this.y -= velocidadSalto;
        }
        if (estaSaltando) {
            this.y -= velocidadSalto;
        }

        if (getAbajo() > e.alto() - alturaSalto) {
            this.estaSaltando = false;
            this.puedeSaltar = false;
        }

    }

    public void agachar(Entorno e, boolean presionadoAGACHAR) {

        if (presionadoAGACHAR && !estaAgachado) {
            this.estaAgachado = true;
            this.alto_personaje /= 2;
            velocidad = 0;// Para que el personaje no se mueva mientas esta agachado sino muy OP
        }

        if (!presionadoAGACHAR && estaAgachado) {
            this.estaAgachado = false;
            this.alto_personaje *= 2;
            this.y -= alto_personaje / 4;
            this.velocidad = 5.0;// Para que el personaje se vuelva a mover a la misma velociadad, no se como
                                 // hacer para que reciba la velocidad del constructor

        }
    }

    /////////////////////////////////////////////////////////////////////////////
    ////////////// ACCIONES ////////////////////////////////////////////////

    public void disparar(Entorno e) {

        Proyectil proyectil = new Proyectil(x, y - 7, 10, 10, velocidad + 2);
        proyectil.dibujarse(e);
        proyectil.mover(mira);

    }

    public Proyectil disparar() {

        return new Proyectil(x, y - 7, 10, 10, velocidad + 2);

    }

    //////////////////////////////////// COLICIONES

    public void estaColisionando(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null) {
                if (getAbajo() == bloque.ObtenerLadoSuperior()) {
                    System.out.println("colisiono abajo");
                }
                if (getArriba() == bloque.ObtenerLadoInferior()) {
                    System.out.println("colisiono arriba");
                }
                if (getDerecha() == bloque.ObtenerLadoIzquierdo()) {
                    System.out.println("colisiono con la derecha");
                }
                if (getIzquierda() == bloque.ObtenerLadoDerecho()) {
                    System.out.println("colisiono la izquierda");
                }
            }
        }
    }

    //////////////////////////////////// /////////////////////////////////////////////////

    /*
     * El metodo actualizar() recibe un Entorno, y
     * se encarga de actualizar todos elementos y/o acciones de la clase Player
     * (Extencion de tick() de la clase Juego).
     */

    public void actualizar(Entorno e, Bloque[] b) {
        this.dibujarse(e);
        estaColisionando(b);

        if (e.estaPresionada(e.TECLA_DERECHA) && (getDerecha() < e.ancho())) {
            mira = "d";
            this.moverDerecha(e);
        }

        if (e.estaPresionada(e.TECLA_IZQUIERDA) && (getIzquierda() > 0)) {
            mira = "i";
            this.moverIzquierda(e);
        }

        if (e.estaPresionada('x')) {
            this.agachar(e, true);
        } else {
            this.agachar(e, false);
        }

        if (e.sePresiono('c')) {
            this.disparar(e);
        }

        if (e.estaPresionada(e.TECLA_ESPACIO)) {
            this.saltar(e);
        }

        if ((getAbajo() < e.alto() && !e.estaPresionada(e.TECLA_ESPACIO))) {
            this.caer(e);
        } else {
            this.puedeSaltar = true;
        }
        ;
    }
}
