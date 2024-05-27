package Entidades;

import java.awt.Image;

import entorno.Herramientas;
import entorno.Entorno;

public class Proyectil {

    private double x;
    private double y;

    
	private int alto;
	private String direccion;

	private Image img;


    public Proyectil(double x, double y, int alto, String direccion) {
        this.x = x;
		this.y = y;
		this.alto = alto;
		this.direccion = direccion;
        
        if (direccion == "d") {
            this.img = Herramientas.cargarImagen("recursos/imagenes/Proyectil/Flecha der.gif");
        } else if (direccion == "i") {
            this.img = Herramientas.cargarImagen("recursos/imagenes/Proyectil/Flecha izq.gif");
        }
    }

    public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 1);
  
	}

    public void mover() {
		if (direccion == "d") {
			x += 6;
		}
		if (direccion == "i") {
			x -= 6;
		}
	}

	public boolean estaFueraDEPantalla(Entorno e) {
		return x < 0 || x > e.ancho();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getAlto() {
		return alto;
	}
}
