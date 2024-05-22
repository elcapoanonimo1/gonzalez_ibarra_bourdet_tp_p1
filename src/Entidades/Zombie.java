package Entidades;

import entorno.Entorno;
import java.awt.Color;

public class Zombie {
    private double x;
    private double y;
    private int alto_zombie;
    private int ancho_zombie;
    protected double velocidad;

    public Zombie(double x, double y, int alto_zombie, int ancho_zombie, double velocidad){
        this.x = x;
        this.y = y;
        this.alto_zombie = alto_zombie;
        this.ancho_zombie = ancho_zombie;
        this.velocidad = velocidad;

    }

    public void dibujarse(Entorno e) {
        e.dibujarRectangulo(x, y, ancho_zombie, alto_zombie, 0, Color.green);
    }

    public void moverDerecha(Entorno e) {
        x += velocidad;
    }

    public void moverIzquierda(Entorno e) {
        x -= velocidad;
    }

    private void eliminarEnemigo() {
        
        
    }
    
    


    public boolean colicionaBorde(String borde, Entorno e){
        if (borde == "arriba"){
            if((this.y - alto_zombie/2) > 0){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "abajo"){
            if((this.y + alto_zombie/2) < e.alto()){
                return true;
            } else {
                return false;
            }
        }
        if (borde == "izquierda"){
            if((this.x - ancho_zombie/2) > 0){
                return false;
            } else {
                return true;
            }
        }
        if (borde == "derecha"){
            if((this.x + ancho_zombie/2) < e.ancho()){
                return false;
            } else {
                return true;
            }
        }

        throw new IllegalArgumentException("Borde invalido");
    }

    public void actualizar(Entorno e) {
		this.dibujarse(e);
        /*if(colicionaBorde("abajo", e)){
            eliminarEnemigo();
        }else{*/
            if(colicionaBorde("izquierda", e)){
                moverDerecha(e);
            }else if(colicionaBorde("derecha", e)){
                moverIzquierda(e);
            }else{
                moverIzquierda(e);
            }
            
        }
    
     
}

    


   
