package Entidades;

import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;

import Estructuras.Bloque;

public class Esqueleto {
    private double x;
    private double y;
    private int alto_esqueleto;
    private int ancho_esqueleto;
    protected double velocidad;
    protected String destino;
    protected String seMueveA;
    protected Image img;
    protected double velocidadCaida = 1;

    public Esqueleto(double x, double y, int alto_esqueleto, int ancho_esqueleto, double velocidad,int destino){
        this.x = x;
        this.y = y;
        this.alto_esqueleto = alto_esqueleto;
        this.ancho_esqueleto = ancho_esqueleto;
        this.velocidad = velocidad;
        
        if (destino % 2== 0){
            this.destino = "d";
            this.seMueveA = "d";
        }else{
            this.destino = "i";
            this.seMueveA = "i";
        }
        

    }

    public void dibujarse(Entorno e) {
        switch (seMueveA) {
            case "d":
            img = Herramientas.cargarImagen("recursos/imagenes/Esqueleto/Esqueleto - corriendod.gif");
            e.dibujarImagen(img, x, y-5,0,3);
                break;
            
            case "i":
            img = Herramientas.cargarImagen("recursos/imagenes/Esqueleto/Esqueleto - corriendoi.gif");
            e.dibujarImagen(img, x, y-5,0,3);
                break;

            default:
            e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Steve/Steve - quieto.png"), x, y-10,0,3);
                break;
        }
    }
    public void mover(Entorno e, Bloque[] b) {
        if(destino == "d" && !colisionoDerecha(b,e)) {
            x += velocidad;
            seMueveA = "d";
        } else {
            if (colisionoAbajo(b)) {
                destino = "i";
                seMueveA = "i";
            }
        }
        if (destino == "i" && !colisionoIzquierda(b)) {
            x -= velocidad;
        } else if(destino == "i"){
            if(colisionoAbajo(b)){
                destino = "d";
            }
        }
    }
            
    
    public Proyectil disparar() {
        return new Proyectil(x, y-10, 10, 10, destino, "esq");
    }


    public boolean colisionoDerecha(Bloque[] b, Entorno e) {
        for (Bloque bloque : b) {
            if (bloque != null) {
                if ((getDerecha() + velocidad >= bloque.ObtenerLadoIzquierdo() &&
                    getDerecha() <= bloque.ObtenerLadoIzquierdo() + velocidad &&
                    getAbajo() > bloque.ObtenerLadoSuperior() &&
                    getArriba() < bloque.ObtenerLadoInferior()) || getDerecha() >= e.ancho()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean colisionoIzquierda(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null) {
                if ((getIzquierda() - velocidad <= bloque.ObtenerLadoDerecho() &&
                    getIzquierda() >= bloque.ObtenerLadoDerecho() - velocidad &&
                    getAbajo() > bloque.ObtenerLadoSuperior() &&
                    getArriba() < bloque.ObtenerLadoInferior()) || getIzquierda() <= 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    public double getArriba() {
        return (this.y - alto_esqueleto / 2);
    }

    public double getAbajo() {
        return (this.y + alto_esqueleto / 2);
    }

        public double getIzquierda() {
        return (this.x - ancho_esqueleto / 2);
    }

    public double getDerecha() {
        return (this.x + ancho_esqueleto / 2);
    }

    public void caer(Entorno e) {
        if (getAbajo() >= e.alto()) {
            this.y = (this.y + alto_esqueleto/2);
        } else {
            this.y = y + velocidadCaida;
        }
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

    public void actualizar(Entorno e, Bloque[] bloques) {
		
        this.dibujarse(e);
        mover(e, bloques);
        if (!this.colisionoAbajo(bloques)) {
            this.caer(e);
        }
    }
    
     
}
