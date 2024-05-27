package Extras;

import entorno.Entorno;

public class Fps {
    private Entorno entorno;
    protected static int fps =0;
    protected static int temporizador =0;

    public void Fps(Entorno e){
       this.entorno =e;
    }

    public static int contar(long nanoTime) {
        
        long tiempoActual = System.nanoTime();
        
        return temporizador;
    }

}
