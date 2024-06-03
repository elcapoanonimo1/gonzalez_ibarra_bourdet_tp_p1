package Estructuras;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Bloque {
    private double pos_x;
    private double pos_y;
    private int ancho_bloque;
    private int alto_bloque;
    private boolean se_rompe;

    private Image bloque_normal = Herramientas.cargarImagen("recursos/imagenes/Bloque/bloque.png");
    private Image bloque_rompible = Herramientas.cargarImagen("recursos/imagenes/Bloque/bloque_rompible.png");

    public Bloque(double pos_x, double pos_y, int ancho_bloque, int alto_bloque, boolean se_rompe) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.ancho_bloque = ancho_bloque;
        this.alto_bloque = alto_bloque;
        this.se_rompe = se_rompe;
    }

    public double getX() {
        return pos_x;
    }

    public boolean getSeRompe() {
        return se_rompe;
    }

    public double getY() {
        return pos_y;
    }

    // Colisiones
    public double ObtenerLadoIzquierdo() {
        return pos_x - ancho_bloque / 2;
    }

    public double ObtenerLadoDerecho() {
        return pos_x + ancho_bloque / 2;
    }

    public double ObtenerLadoSuperior() {
        return pos_y - alto_bloque / 2;
    }

    public double ObtenerLadoInferior() {
        return pos_y + alto_bloque / 2;
    }

    public void dibujarBloque(Entorno e) {
        if (se_rompe == true) {
            e.dibujarImagen(bloque_rompible, pos_x, pos_y, 0, 1);
        } else {
            e.dibujarImagen(bloque_normal, pos_x, pos_y, 0, 1);
        }
    }

    public void actualizar(Entorno e) {
        this.dibujarBloque(e);
    }

}