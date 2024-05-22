package Estructuras;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
public class Bloque{
    private int ancho_fondo;
    private int alto_fondo;
    private double pos_x;
    private double pos_y;
    private int ancho_bloque;
    private int alto_bloque;
    private boolean se_rompe;
    private Color color = Color.green;
    
    public Bloque(int ancho_fondo, int alto_fondo, double pos_x, double pos_y, int ancho_bloque, int alto_bloque, boolean se_rompe, Color color){
        this.alto_fondo = alto_fondo;
        this.ancho_fondo = ancho_fondo;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.ancho_bloque = ancho_bloque;
        this.alto_bloque = alto_bloque;
        this.se_rompe = se_rompe;
        this.color = color;
    }
    
    //Colisiones
    public double ObtenerLadoIzquierdo(){
        return pos_x - ancho_bloque / 2;
    }
    public double ObtenerLadoDerecho(){
        return pos_x + ancho_bloque / 2;
    }
    public double ObtenerLadoSuperior() {
        return pos_y - alto_bloque / 2;
    }
    public double ObtenerLadoInferior() {
        return pos_y + alto_bloque / 2;
    }
    
    public void dibujarBloque(Entorno e) {
        for (int x = 0; x <= ancho_fondo; x += ancho_bloque) { 
            for(int y = +20; y <= alto_fondo; y += 150) {
                if (x == 0 || x == ancho_fondo) { // barras laterales
                    e.dibujarRectangulo(x, y, ancho_bloque, alto_bloque, 0, color);
                } else if (y == alto_fondo) { // base
                    e.dibujarRectangulo(x, y, ancho_bloque, alto_bloque, 0, color);
                } else { // fondo
                    e.dibujarRectangulo(x, y, ancho_bloque, alto_bloque, 0, color);                    
                }
            }
        }
        
    }

    public void actualizar(Entorno e) {
        this.dibujarBloque(e);
    }

}