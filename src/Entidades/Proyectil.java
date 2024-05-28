package Entidades;

import java.awt.Image;

import entorno.Herramientas;
import entorno.Entorno;

public class Proyectil {

    private double x;
    private double y;

    
	private int alto;
	private String direccion;
	private String tipoDePro;
	private Image img;


    public Proyectil(double x, double y, int alto, String direccion, String tipoDePro) {
        this.x = x;
		this.y = y;
		this.alto = alto;
		this.direccion = direccion;
		this.tipoDePro = tipoDePro;

        switch (tipoDePro){
			case "esq":
			if (direccion == "d") {
				this.img = Herramientas.cargarImagen("recursos/imagenes/Proyectil/Flecha der.gif");
			} else if (direccion == "i") {
				this.img = Herramientas.cargarImagen("recursos/imagenes/Proyectil/Flecha izq.gif");
			}
			break;

			case "ste":
				this.img = Herramientas.cargarImagen("recursos/imagenes/Steve/Bolas Nieve.png");
			break;

			case "cre":
			if (direccion == "d") {
				this.img = Herramientas.cargarImagen("recursos/imagenes/Proyectil/Proyectil TNTd.gif");
			} else if (direccion == "i") {
				this.img = Herramientas.cargarImagen("recursos/imagenes/Proyectil/Proyectil TNTi.gif");
			}
			break;

		
		}
        
    }

    public void dibujar(Entorno e) {
		if (tipoDePro =="cre"){
			e.dibujarImagen(img, x, y, 0, 0.09);
		}else{
			e.dibujarImagen(img, x, y, 0, 2);
		}
		
  
	}

    public void mover() {
		if (direccion == "d") {
			x += 3;
		}
		if (direccion == "i") {
			x -= 3;
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
