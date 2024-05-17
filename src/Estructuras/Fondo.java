package Estructuras;

import java.awt.Color;
import entorno.Entorno;
public class Fondo {
    private Color color;
    private double ancho;
    private double alto;

    public Fondo(Color color, double ancho, double alto){
        this.color = color;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Entorno e) {
        e.dibujarRectangulo(ancho/2, alto/2, ancho+ancho/2, alto+ancho/2, 0, color);
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
