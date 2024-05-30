package Entidades;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Item {
    private String tipoItem;
    private double x;
    private double y;
    private Image img;

    public Item(String tipoItem, double x, double y) {
        this.tipoItem = tipoItem;
        this.x = x;
        this.y = y;
    }

    public void dibujarse(Entorno e) {
        switch (tipoItem) {
            case "hueso":
                img = Herramientas.cargarImagen("recursos/imagenes/Items/Hueso.gif");
                e.dibujarImagen(img, x, y, 0, 0.2);
                break;

            case "Vida":
                img = Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendoi.gif");
                e.dibujarImagen(img, x, y, 0, 3);
                break;

            case "Estrella":
                img = Herramientas.cargarImagen("recursos/imagenes/Items/Nether_Star.gif");
                e.dibujarImagen(img, e.ancho() / 2, e.alto() * 0.05, 0, 0.4);
                break;

            default:
                e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendoi.gif"), x,
                        y - 10, 0, 3);
                break;
        }
    }

    public void actualizar(Entorno entorno) {
        dibujarse(entorno);
    }

}
