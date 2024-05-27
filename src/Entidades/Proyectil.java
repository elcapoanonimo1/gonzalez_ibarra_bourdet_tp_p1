package Entidades;

import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Color;

public class Proyectil{
    private double x;
    private double y;
    private int alto_proyectil;
    private int ancho_proyectil;
    protected double velocidad;
    protected boolean proyectil_vivo = false;

    public Proyectil(double x, double y, int alto_proyectil, int ancho_proyectil, double velocidad){
        this.x = x;
        this.y = y;
        this.alto_proyectil = alto_proyectil;
        this.ancho_proyectil = ancho_proyectil;
        this.velocidad = velocidad;
        
    }

    public void dibujar(Entorno e) {
        //if (proyectil_vivo==false){ 
        e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Proyectil.png"), x, y-10,0,3);
        //proyectil_vivo = true;
        //}
    }

    public void mover(){
        //if (direccion=="i"){
                x-=velocidad;
        //}
        //if (direccion=="d"){     
        //        x+=velocidad;
        //}
        
        
    }

    private int getIzquierda() {
        return (int) (this.x - ancho_proyectil/2);
    }

    private int getDerecha() {
        return (int) (this.x + ancho_proyectil/2);
    }

    public static void actualizar(){

    }

    public boolean estaFueraDEPantalla(Entorno e) {
        if ((getDerecha() < e.ancho())){
            return true;
        }if ((getIzquierda() < 0)){
            return true;
        }

        return false;
    }

    }

