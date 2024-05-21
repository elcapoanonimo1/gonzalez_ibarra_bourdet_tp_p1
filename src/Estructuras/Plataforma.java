package Estructuras;

import java.awt.Color;
import entorno.Entorno;
public class Plataforma {
    private double pos_x;
    private double pos_y;
    private int ancho_bloque;
    private int alto_bloque;
    private boolean es_de_acero;
    private Color color = Color.green;

    public Plataforma(double pos_x, double pos_y, int ancho_bloque, int alto_bloque, boolean es_de_acero, Color color){
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.ancho_bloque = ancho_bloque;
        this.alto_bloque = alto_bloque;
        this.es_de_acero = es_de_acero;
        this.color = color;
    }
    
    public void dibujarBloque(Entorno e){
        e.dibujarRectangulo(pos_x, pos_y, ancho_bloque, alto_bloque, 0, color);

    }
    public void actualizar(Entorno e) {
		this.dibujarBloque(e);
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
}

