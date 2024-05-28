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
    private double alturaMaximaSalto;
    private boolean estaAgachado = false;
    private boolean estaSaltando = false;
    @SuppressWarnings("unused")
    protected double velocidad;
    protected double velocidadSalto = 1.0;
    protected double velocidadCaida = 1.0;
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

    public void setAlturaMaximaSalto(double alturamax) {
        this.alturaMaximaSalto = alturamax;
    }

    public double getAlturaMaximaSalto() {
        return this.alturaMaximaSalto;
    }

    public void setEstaSaltando(boolean saltando){
        this.estaSaltando = saltando;
    }

    public boolean getEstaSaltando(){
        return estaSaltando;
    }

    public void setMira(String m){
        this.mira = m;
    }

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

    public void moverAbajo(Entorno e) {
        this.y += velocidadCaida;
    }

    public void moverArriba(Entorno e) {
        this.y -= velocidadSalto;
    }

    public void moverDerecha(Entorno e) {
        this.x += velocidad;
    }

    public void moverIzquierda(Entorno e) {
        this.x -= velocidad;
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
                    return true;
                }
            }
        }
        return false;
    }

    public boolean colisionoDerecha(Bloque[] b, Entorno e ) {
        for (Bloque bloque : b) {
            if (bloque != null){
                if (getDerecha() == bloque.ObtenerLadoIzquierdo()){
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
                if ((getIzquierda() == bloque.ObtenerLadoDerecho())){
                    System.err.println("COL IZ");
                    return true;
                }
            }
        }
        return false;
    }

    
}
