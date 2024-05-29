package Scores;

import entorno.Entorno;
import java.awt.Color;



public class Puntuaciones {
    private int puntos = 0;
    private int tamaño;
    private double posx;
    private double posy;
    private Color color = Color.white;


    public Puntuaciones(int tamaño, double posx, double posy){
        this.tamaño = tamaño;
        this.posx = posx;
        this.posy = posy;
    }

    public void addPuntos(int num){
        this.puntos += num;
    }

    

    public void dibujarse(Entorno entorno){
        entorno.cambiarFont("Minecraft", tamaño, color);
        entorno.escribirTexto("Enemigos Eliminados: " + puntos, posx, posy);
        entorno.escribirTexto("Puntos: " + puntos*2, posx, posy + posy);

    }

    public void actualizar(Entorno e){
        this.dibujarse(e);
    }
}
