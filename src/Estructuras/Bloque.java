package Estructuras;

import entorno.Entorno;
import entorno.Herramientas;

public class Bloque {
    private double pos_x;
    private double pos_y;
    private int ancho_bloque;
    private int alto_bloque;
    private boolean se_rompe;

    public Bloque(double pos_x, double pos_y, int ancho_bloque, int alto_bloque, boolean se_rompe) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.ancho_bloque = ancho_bloque;
        this.alto_bloque = alto_bloque;
        this.se_rompe = se_rompe;
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
        for (int x = 20; x <= e.ancho(); x += ancho_bloque) {
            if (x == 0 || x == e.ancho()) { // barras laterales
                e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Cracked_Deepslate_Bricks.png"), x, pos_y, 0, 1.2);
            } else if (pos_y == e.alto()) { // base
                e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Cracked_Deepslate_Bricks.png"), x, pos_y, 0, 1.2);
            } else { // fondo
                e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Cracked_Deepslate_Bricks.png"), x, pos_y, 0, 1.2);
            }

        }

    }

    public void actualizar(Entorno e) {
        this.dibujarBloque(e);
    }

}