package Estructuras;

import java.awt.Image;

import entorno.Entorno;
public class Fondo {
    private double ancho_fondo;
    private double alto_fondo;
    private double escala_fondo;
    private Image fondo;

    public Fondo(Image fondo, double ancho_fondo, double alto_fondo, double escala_fondo){
        this.ancho_fondo = 64;
        this.alto_fondo = 64;
        this.escala_fondo = escala_fondo;
        this.fondo = fondo;
    }

    public void dibujar(Entorno e) {
        e.dibujarImagen(fondo, ancho_fondo, alto_fondo, 0, escala_fondo);
        
    }

    
     /*
      * El metodo actualizar() recibe un Entorno, y 
      * se encarga de actualizar todos elementos y/o acciones de la clase Fondo 
      * (Extencion de tick() de la clase Juego).
      */
    public void actualizar(Entorno e) {
        this.dibujar(e);
    }

}
		
