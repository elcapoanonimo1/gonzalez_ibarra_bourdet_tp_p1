package Entidades;

import entorno.Entorno;
import entorno.Herramientas;
import javafx.scene.image.Image;

import java.awt.Color;

public class Creeper {
    private double x;
    private double y;
    private int alto_creeper;
    private int ancho_creeper;
    protected double velocidad;
    private String destino = "i";
    protected String seMueveA = "i";
    protected java.awt.Image img;
    protected Proyectil proyectil;

    public Creeper(double x, double y, int alto_creeper, int ancho_creeper, double velocidad){
        this.x = x;
        this.y = y;
        this.alto_creeper = alto_creeper;
        this.ancho_creeper = ancho_creeper;
        this.velocidad = velocidad;

    }

    public void dibujarse(Entorno e) {
        switch (seMueveA) {
            case "d":
            img = Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendod.gif");
            e.dibujarImagen(img, x, y,0,3);
                break;
            
            case "i":
            img = Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendoi.gif");
            e.dibujarImagen(img, x, y,0,3);
                break;

            default:
            e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendoi.gif"), x, y-10,0,3);
                break;
    }
    }

    public void moverDerecha(Entorno e) {
        x += velocidad;
    }

    public void moverIzquierda(Entorno e) {
        x -= velocidad;
    }

    /*public void mover(){
    
        int aux;
        if(x==800){
            aux=0;
        }else{
            aux=1;
        }
        if (aux == 0){
            x-=velocidad;
        }else{
            x+=velocidad;
        }
    }*/

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

    // private void eliminarEnemigo() {
        
        
    // }
    
    


    public boolean colicionaBorde(String borde, Entorno e){
        if (borde == "arriba"){
            if((this.y - alto_creeper/2) > 0){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "abajo"){
            if((this.y + alto_creeper/2) < e.alto()){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "izquierda"){
            if((this.x - ancho_creeper/2) > 0){
                return false;
            } else {
                return true;
            }
        }
        if (borde == "derecha"){
            if((this.x + ancho_creeper/2) < e.ancho()){
                return false;
            } else {
                return true;
            }
        }

        throw new IllegalArgumentException("Borde invalido");
    }

    public Proyectil disparar() {
        return new Proyectil(x, y, this.alto_creeper, destino,"cre");
    }

    public void actualizar(Entorno e) {
		this.dibujarse(e);

            mover(e);
            if(proyectil == null){
                proyectil = disparar();
            }
            
            if (proyectil != null) {
                proyectil.dibujar(e);
                proyectil.mover();
                if (proyectil.estaFueraDEPantalla(e)) {
                    proyectil = null;
                }
            }
        }
    
     
}

    


   
