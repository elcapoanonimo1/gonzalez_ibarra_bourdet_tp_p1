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

    public void mover(Entorno e){
        if(destino == "d" && !colicionaBorde("derecha", e)) {
            x += velocidad;
            seMueveA = "d";
        } else {
            destino = "i";
            seMueveA = "i";
        }
        if (destino == "i" && !colicionaBorde("izquierda", e)) {
            x -= velocidad;
        } else if(destino == "i"){
            destino = "d";
        }
    }
            
    
    public Proyectil disparar() {
        return new Proyectil(x, y-10, this.alto_esqueleto, destino, "esq");
    }



    public boolean colicionaBorde(String borde, Entorno e){
        if (borde == "arriba"){
            if((this.y - alto_esqueleto/2) > 0){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "abajo"){
            if((this.y + alto_esqueleto/2) < e.alto()){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "izquierda"){
            if((this.x - ancho_esqueleto/2) > 0){
                return false;
            } else {
                return true;
            }
        }
        if (borde == "derecha"){
            if((this.x + ancho_esqueleto/2) < e.ancho()){
                return false;
            } else {
                return true;
            }
        }

        throw new IllegalArgumentException("Borde invalido");
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
                    System.err.println("COL AB");
                    return true;
                }
            }
        }
        return false;
    }

    public void actualizar(Entorno e, Bloque[] bloques) {
		
        this.dibujarse(e);
        mover(e);
        if (!this.colisionoAbajo(bloques)) {
            this.caer(e);
        }
    }
    
     
}
