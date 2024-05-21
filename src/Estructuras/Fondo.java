package Estructuras;

// import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
public class Fondo {
    private int ancho_fondo;
    private int alto_fondo;
    private int escala_fondo;


    public Fondo(int ancho_fondo, int alto_fondo, int escala_fondo){
        this.ancho_fondo = ancho_fondo;
        this.alto_fondo = alto_fondo;
        this.escala_fondo = escala_fondo;

    }

    public void dibujar(Entorno e) {
        for (int x = 0; x <= ancho_fondo; x += 100) { 
            for(int y = 0; y <= alto_fondo; y += 100) {
                if (x == 0 || x == ancho_fondo) { // barras laterales
                    e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/fondo2.png"), x, y, 0, escala_fondo);
                } else if (y == alto_fondo) { // base
                    e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/fondo3.png"), x, y, 0, escala_fondo);
                } else { // fondo
                    e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/fondo.png"), x, y, 0, escala_fondo);                    
                }
            }
        }

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
		
