package Entidades;

import entorno.Entorno;
import Estructuras.Bloque;
import entorno.Herramientas;
import java.awt.Image;

public class Player {
    private double x;
    private double y;
    private int alto_personaje;
    private int ancho_personaje;
    private boolean estaAgachado = false;
    private boolean estaSaltando = false;
    @SuppressWarnings("unused")
    private boolean puedeSaltar = true;
    protected double velocidad;
    protected double velocidadSalto = 1;
    protected double velocidadCaida = 1;
    protected double xAnterior = 0;
    protected String mira = "i";
    protected Image img;

    public Player(double x, double y, int alto_personaje, int ancho_personaje, double velocidad) {
        this.x = x;
        this.y = y;
        this.alto_personaje = alto_personaje;
        this.ancho_personaje = ancho_personaje;
        this.velocidad = velocidad;

    }

    // DIBUJARSE
    public void dibujarse(Entorno e) {
        switch (se_mueve_a(e)) {
            case "d":
                img = Herramientas.cargarImagen("recursos/imagenes/Steve/Corriendo/Steve - corriendod.gif");
                e.dibujarImagen(img, x, y - 10, 0, 3);
                break;

            case "i":
                img = Herramientas.cargarImagen("recursos/imagenes/Steve/Corriendo/Steve - corriendoi.gif");
                e.dibujarImagen(img, x, y - 10, 0, 3);
                break;

            case "x":
                e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Steve/Steve - agachado.png"), x, y - 10, 0,
                        3);
                break;

            default:
                e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Steve/Steve - quieto.png"), x, y - 10, 0,
                        3);
                break;
        }
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
        if (getAbajo() >= e.alto()) {
            this.y = (this.y + alto_personaje/2);
        } else {
            this.y = y + velocidadCaida;
        }
        // System.err.println(e.alto()-alto_personaje/2);
        // System.err.println(getAbajo());

    }

    public void saltar(Entorno e, Bloque[] b) {
        double alturaSalto = this.getY() - 100;
        if (!estaSaltando && !colisionoArriba(b)) {
            this.estaSaltando = true;
            this.y -= alturaSalto;
        }
        if (estaSaltando && !colisionoArriba(b)) {
            this.y -= velocidadSalto;
        }

        if (colisionoArriba(b)) {
            this.estaSaltando = false;
        }

    }

    public void agachar(Entorno e, boolean presionadoAGACHAR) {

        if (presionadoAGACHAR && !estaAgachado) {
            this.estaAgachado = true;
            this.alto_personaje /= 2;
        }

        if (!presionadoAGACHAR && estaAgachado) {
            this.estaAgachado = false;
            this.alto_personaje *= 2;
            this.y -= alto_personaje / 4;

        }
    }

    /////////////////////////////////////////////////////////////////////////////
    ////////////// ACCIONES ////////////////////////////////////////////////

    public String se_mueve_a(Entorno e) {
        if (e.estaPresionada(e.TECLA_DERECHA)) {
            return "d";
        }

        if (e.estaPresionada(e.TECLA_IZQUIERDA)) {
            return "i";
        }

        if (e.estaPresionada(e.TECLA_SHIFT)) {
            return "x";
        }

        if (e.sePresiono('c')) {
            return "c";
        }

        /*
         * if(e.estaPresionada(e.TECLA_ESPACIO) && !colicionaBorde("abajo", e)) {
         * return "saltar";
         * } else if (colicionaBorde("abajo", e) && !estaSaltando) {
         * return "caer";
         * }
         */
        return "null";
    }

    public Proyectil disparar() {
        return new Proyectil(x, y, this.alto_personaje, mira);
    }

    //////////////////////////////////// COLICIONES

    public boolean colisionoArriba(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null){
                if (getArriba() == bloque.ObtenerLadoInferior() && (getDerecha() >= bloque.ObtenerLadoIzquierdo() && getIzquierda() <= bloque.ObtenerLadoDerecho())){
                    System.err.println("COL AR");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean colisionoAbajo(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null){
                if (getAbajo() == bloque.ObtenerLadoSuperior() && (getDerecha() >= bloque.ObtenerLadoIzquierdo() && getIzquierda() <= bloque.ObtenerLadoDerecho())){
                    System.err.println("COL AB");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean colisionoDerecha(Bloque[] b  , Entorno e ) {
        for (Bloque bloque : b) {
            if (bloque != null){
                if (getDerecha() == bloque.ObtenerLadoIzquierdo() && (getAbajo() > bloque.ObtenerLadoInferior() && getArriba() < bloque.ObtenerLadoSuperior()) || (getDerecha() < e.ancho())){
                    System.err.println("COL DERE");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean colisionoIzquierda(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null){
                if ((getIzquierda() == bloque.ObtenerLadoDerecho() && (getAbajo() > bloque.ObtenerLadoInferior() && getArriba() < bloque.ObtenerLadoSuperior())) || (getIzquierda() > 0) ){
                    System.err.println("COL IZ");
                    return true;
                }
            }
        }
        return false;
    }

    /*public void estaColisionando(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null) {
                if (getAbajo() == bloque.ObtenerLadoSuperior()) {

                    // System.out.println("colisiono abajo");
                }
                if (getArriba() == bloque.ObtenerLadoInferior()) {

                    // System.out.println("colisiono arriba");
                }
                if (getDerecha() == bloque.ObtenerLadoIzquierdo()) {

                    // System.out.println("colisiono con la derecha");
                }
                if (getIzquierda() == bloque.ObtenerLadoDerecho()) {

                    // System.out.println("colisiono la izquierda");
                }
            }
        }
    }*/

    //////////////////////////////////// /////////////////////////////////////////////////

    /*
     * El metodo actualizar() recibe un Entorno, y
     * se encarga de actualizar todos elementos y/o acciones de la clase Player
     * (Extencion de tick() de la clase Juego).
     */

    public void actualizar(Entorno e, Bloque[] b) {
        this.dibujarse(e);

        if (e.estaPresionada(e.TECLA_DERECHA) && colisionoDerecha(b, e)) {
            System.out.println("me voy a la derecha");
            mira = "d";
            this.moverDerecha(e);
        }

        if (e.estaPresionada(e.TECLA_IZQUIERDA) && colisionoIzquierda(b)) {
            System.out.println("me voy a la izquierda");

            mira = "i";
            this.moverIzquierda(e);
        }

        if (e.estaPresionada(e.TECLA_SHIFT)) {
            this.agachar(e, true);
        } else {
            this.agachar(e, false);
        }

        if (e.sePresiono('c')) {
            this.disparar();
        }

 

        if (!colisionoAbajo(b) && !e.estaPresionada(e.TECLA_ESPACIO)) {
            this.caer(e);
        } else {
            this.puedeSaltar = true;
        }
        ;
    }
}
