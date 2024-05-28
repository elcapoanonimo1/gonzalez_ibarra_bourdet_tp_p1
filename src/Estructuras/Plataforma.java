package Estructuras;

import entorno.Entorno;
import java.util.Random;

import Entidades.Player;

public class Plataforma {
    double alto_ventana;
    double ancho_ventana;
    int anchoBloque = 50;
    int altoBloque = anchoBloque;

    int cantidadPisos = 4;

    public Plataforma(double alto_ventana, double ancho_ventana) {
        this.alto_ventana = alto_ventana;
        this.ancho_ventana = ancho_ventana;
    }

    Bloque[] crearPlataforma(double y, boolean b) {
        int x = 25;
        double cantidadBloques = (ancho_ventana / anchoBloque) + 2;
        Random random = new Random();

        if (b == false) {

            boolean se_rompe = false;
            Bloque[] plataforma = new Bloque[(int) cantidadBloques + 1];
            for (int i = 0; i < cantidadBloques; i++) {
                plataforma[i] = new Bloque(x, y, anchoBloque, altoBloque, se_rompe);
                x += anchoBloque;
            }
            return plataforma;

        } else {
            int tresBloques = 0;
            boolean se_rompe = true;
            Bloque[] plataforma = new Bloque[(int) cantidadBloques + 1];
            for (int i = 0; i < cantidadBloques; i++) {
                se_rompe = random.nextBoolean();
                if (tresBloques == 3) {
                    se_rompe = false;
                    tresBloques = 0;
                }
                plataforma[i] = new Bloque(x, y, anchoBloque, altoBloque, se_rompe);
                if (se_rompe == true) {
                    tresBloques += 1;
                }
                x += anchoBloque;
            }
            return plataforma;
        }

    }

    Bloque[] juntarPlataformas() {
        double y = alto_ventana - 20;
        Bloque[] pepe0 = crearPlataforma(y, false);
        Bloque[] pepe1 = crearPlataforma(y * 0.75, true);
        Bloque[] pepe2 = crearPlataforma(y * 0.50, true);
        Bloque[] pepe3 = crearPlataforma(y * 0.25, true);

        Bloque[] res = new Bloque[pepe0.length + pepe1.length + pepe2.length + pepe3.length];

        System.arraycopy(pepe0, 0, res, 0, pepe0.length);
        System.arraycopy(pepe1, 0, res, pepe0.length, pepe1.length);
        System.arraycopy(pepe2, 0, res, pepe0.length + pepe1.length, pepe2.length);
        System.arraycopy(pepe3, 0, res, pepe0.length + pepe1.length + pepe2.length, pepe3.length);
        return res;

    }

    public Bloque[] getBloques() {
        return juntarPlataformas();
    }

    public void actualizar(Entorno e, Bloque[] bloques, Player jugador) {
        for (int i = 0; i < bloques.length;  i++){
            if(bloques[i] != null){
                if(bloques[i].getSeRompe() == true){
                    if(jugador.getArriba() == bloques[i].ObtenerLadoInferior() && (jugador.getDerecha() >= bloques[i].ObtenerLadoIzquierdo() && jugador.getIzquierda() <= bloques[i].ObtenerLadoDerecho())){
                        bloques[i] = null;
                        jugador.setEstaSaltando(false);
                    }
                }
                if(bloques[i] != null){
                    bloques[i].actualizar(e);
                }
            }
        }

    }
}