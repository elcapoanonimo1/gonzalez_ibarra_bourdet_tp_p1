package Entidades;

import entorno.Entorno;
import java.awt.Color;

public class Esqueleto {
    private double x;
    private double y;
    private int alto_esqueleto;
    private int ancho_esqueleto;
    protected double velocidad;
    protected String destino= "i";

    public Esqueleto(double x, double y, int alto_esqueleto, int ancho_esqueleto, double velocidad){
        this.x = x;
        this.y = y;
        this.alto_esqueleto = alto_esqueleto;
        this.ancho_esqueleto = ancho_esqueleto;
        this.velocidad = velocidad;

    }

    public void dibujarse(Entorno e) {
        e.dibujarRectangulo(x, y, ancho_esqueleto, alto_esqueleto, 0, Color.DARK_GRAY);
    }
    public void mover(Entorno e){


        if(destino == "d" && !colicionaBorde("derecha", e)) {
            x += velocidad;
        } else {
            destino = "i";
        }
        if (destino == "i" && !colicionaBorde("izquierda", e)) {
            x -= velocidad;
        } else if(destino == "i"){
            destino = "d";
        }
    }


    private void eliminarEnemigo() {
        
        
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

    public void actualizar(Entorno e) {
		this.dibujarse(e);
        /*if(colicionaBorde("abajo", e)){
            eliminarEnemigo();
        }else{*/
        mover(e);
        }
    
     
}

    


   
