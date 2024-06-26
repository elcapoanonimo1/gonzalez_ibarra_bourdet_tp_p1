package Extras;

import entorno.Entorno;

public class counterFPS {
    private int temporizador;
    private int fps;
    private long tiempoAnterior;
    private long tiempoActual;
    public int FullFPS;

    public counterFPS() {
        this.temporizador = 0;
        this.fps = 0;
        this.tiempoAnterior = 0;
        this.tiempoActual = 0;
    }

    public void iniciar(long t) {
        this.tiempoAnterior = t;
    }

    public void terminar(long t) {
        this.tiempoActual = t;
        this.temporizador += tiempoActual - tiempoAnterior;
        this.fps++;

    }

    public void reset() {
        this.fps = 0;
        this.temporizador = 0;
    }

    public int getFPS() {
        return this.fps;
    }

    public void dibujar(Entorno e, double x, double y) {
        {
            if (temporizador >= 1000000000) {
                FullFPS = fps;
                reset();
            }
            e.cambiarFont("Monospaced", 15, java.awt.Color.green);
            if (FullFPS != 0) {
                e.escribirTexto("FPS: " + FullFPS, x, y);
            }

        }
    }
}
