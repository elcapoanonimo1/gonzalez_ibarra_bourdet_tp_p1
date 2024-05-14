package Entidades;

import entorno.Entorno;
import java.awt.Color;

public class Player {
    private double x;
    private double y;
    private int alto;
    private int ancho;
    private int movimientoY;
    private int movimientoX;
    private double desplazamiento;
    private double velocidad;

    public Player(double x, double y, int alto, int ancho, double desplazamiento/*, int movY, int movX, int vel */){
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.desplazamiento = desplazamiento;
        // this.movimientoX = movX;
        // this.movimientoY = movY;
        // this.velocidad = vel;
    }

    public void dibujarse(Entorno e) {
        e.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
    }

    public void moverDerecha(Entorno e) {
        if(this.x + ancho/2 < e.ancho()){
            this.x += desplazamiento;
        }
    }

    public void moverIzquierda(Entorno e) {
        if(this.x - ancho/2 > 0){
            this.x -= desplazamiento;
        }
    }

    public void caer(Entorno e) {
        if(this.y + alto/2 < e.alto()){
            this.y += 3;
        }
    }
}
