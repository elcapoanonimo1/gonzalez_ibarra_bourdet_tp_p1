package Entidades;

import entorno.Entorno;
import entorno.Herramientas;

public class Lobo {
    private double y;
    private double x;
    private java.awt.Image img;
    private String mira = "i";
    private double velocidad = 2;

    public Lobo(double y, double x) {
        this.x= x;
        this.y= y;

    }


    public void dibujarse(Entorno e) {
        switch (mira) {
            case "d":
                img = Herramientas.cargarImagen("recursos/imagenes/Lobo/Lobo der.gif");
                e.dibujarImagen(img, x, y+5, 0, 2.3);
                break;

            case "i":
                img = Herramientas.cargarImagen("recursos/imagenes/Lobo/Lobo izq.gif");
                e.dibujarImagen(img, x, y+5, 0, 2.3);
                break;

            case "quieto":
                img = Herramientas.cargarImagen("recursos/imagenes/Lobo/Lobo quieto.png");
                e.dibujarImagen(img, x, y+5, 0, 2.3);
            break;

            default:
                e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Lobo/Lobo quieto.png"), x,
                        y, 0, 3);
                break;
        }
    }


    public void mover(double xJugador, double yJugador) {
        if (x > xJugador-40){
            x -= velocidad;
            mira ="i";
            if (y < yJugador){
                y += velocidad;
            }else if(y > yJugador){
                y -= velocidad;
            }
        }else if(x < xJugador-40){
            x += velocidad;
            mira ="d";
            if (y < yJugador){
                y += velocidad;
            }else if(y > yJugador){
                y -= velocidad;
            }
        }else{
            mira= "quieto";
            if (y < yJugador){
                y += velocidad;
            }else if(y > yJugador){
                y -= velocidad;
            }
        }

        
    }


}
