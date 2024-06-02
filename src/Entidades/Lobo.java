package Entidades;

import entorno.Entorno;
import entorno.Herramientas;

public class Lobo {
    private double y;
    private double x;
    private java.awt.Image img;

    private double velocidad = 4;
    private int alto = 20;
    private int ancho = 30;

    public Lobo(double y, double x) {
        this.x= x;
        this.y= y;

    }


    public void dibujarse(Entorno e) {
        img = Herramientas.cargarImagen("recursos/imagenes/Lobo/Lobo izq.gif");
        e.dibujarImagen(img, x, y, 0, 3);
    }

    public double getArriba() {
		return (this.y - alto / 2);
	}

	public double getIzquierda() {
		return (this.x - ancho / 2);
	}

	public double getDerecha() {
		return (this.x + ancho / 2);
	}

	public double getAbajo() {
		return (this.y + alto / 2);
	}


    public void mover(double xJugador, double yJugador) {
        x = x-velocidad;    
    }


}