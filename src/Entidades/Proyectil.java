package Entidades;

import entorno.Entorno;
import java.awt.Color;

public class Proyectil {
    private double x;
    private double y;
    private int alto_proyectil;
    private int ancho_proyectil;
    protected double velocidad;

    public Proyectil(double x, double y, int alto_proyectil, int ancho_proyectil, double velocidad) {
        this.x = x;
        this.y = y;
        this.alto_proyectil = alto_proyectil;
        this.ancho_proyectil = ancho_proyectil;
        this.velocidad = velocidad;
    }

    public void dibujarse(Entorno e) {
        e.dibujarRectangulo(x, y, alto_proyectil, ancho_proyectil, 5, Color.green);
    }

    public void mover(String direccion) {
        if (direccion == "i") {
            x -= velocidad;
        }
        if (direccion == "d") {
            x += velocidad;
        }
    }

}
