package Entidades;

import entorno.Entorno;
import entorno.Herramientas;

import Estructuras.Bloque;

public class Creeper {
    private double x;
    private double y;
    private int alto_creeper;
    private int ancho_creeper;
    protected double velocidad;
    private String destino;
    protected String seMueveA;
    protected java.awt.Image img;
    protected double velocidadCaida = 1;

    public Creeper(double x, double y, int alto_creeper, int ancho_creeper, double velocidad, int destino){
        this.x = x;
        this.y = y;
        this.alto_creeper = alto_creeper;
        this.ancho_creeper = ancho_creeper;
        this.velocidad = velocidad;
        
        if (destino % 2== 0){
            this.destino = "d";
            this.seMueveA = "d";
        }else{
            this.destino = "i";
            this.seMueveA = "i";
        }

    }

    public void dibujarse(Entorno e) {
        switch (seMueveA) {
            case "d":
            img = Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendod.gif");
            e.dibujarImagen(img, x, y,0,3);
                break;
            
            case "i":
            img = Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendoi.gif");
            e.dibujarImagen(img, x, y,0,3);
                break;

            default:
            e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Creeper/Creeper - corriendoi.gif"), x, y-10,0,3);
                break;
    }
    }

    public void moverDerecha(Entorno e) {
        x += velocidad;
    }

    public void moverIzquierda(Entorno e) {
        x -= velocidad;
    }


    public void mover(Entorno e, Bloque[] b) {
        if(destino.equals("d") && !colisionoDerecha(b,e)) {
            x += velocidad;
            seMueveA = "d";
        } else {
            if (colisionoAbajo(b)) {
                destino = "i";
                seMueveA = "i";
            }
        }
        if (destino.equals("i") && !colisionoIzquierda(b)) {
            x -= velocidad;
        } else if(destino.equals("i")){
            if(colisionoAbajo(b)){
                destino = "d";
            }
        }
    }
    
    public Proyectil disparar() {
        return new Proyectil(x, y-10, 10, 10, destino,"cre");
    }

    public double getAbajo() {
        return (this.y + alto_creeper / 2);
    }

    public double getArriba() {
        return (this.y - alto_creeper / 2);
    }

    public double getIzquierda() {
        return (this.x - ancho_creeper / 2);
    }

    public double getDerecha() {
        return (this.x + ancho_creeper / 2);
    }

    public void caer(Entorno e) {
        if (getAbajo() >= e.alto()) {
            this.y = (this.y + alto_creeper/2);
        } else {
            this.y = y + velocidadCaida;
        }
    }
    public boolean colisionoAbajo(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null){
                if (getAbajo() == bloque.ObtenerLadoSuperior() && (getDerecha() >= bloque.ObtenerLadoIzquierdo() && getIzquierda() <= bloque.ObtenerLadoDerecho())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean colisionoDerecha(Bloque[] b, Entorno e) {
        for (Bloque bloque : b) {
            if (bloque != null) {
                if ((getDerecha() + velocidad >= bloque.ObtenerLadoIzquierdo() &&
                    getDerecha() <= bloque.ObtenerLadoIzquierdo() + velocidad &&
                    getAbajo() > bloque.ObtenerLadoSuperior() &&
                    getArriba() < bloque.ObtenerLadoInferior()) || getDerecha() >= e.ancho()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean colisionoIzquierda(Bloque[] b) {
        for (Bloque bloque : b) {
            if (bloque != null) {
                if ((getIzquierda() - velocidad <= bloque.ObtenerLadoDerecho() &&
                    getIzquierda() >= bloque.ObtenerLadoDerecho() - velocidad &&
                    getAbajo() > bloque.ObtenerLadoSuperior() &&
                    getArriba() < bloque.ObtenerLadoInferior()) || getIzquierda() <= 0) {
                    return true;
                }
            }
        }
        return false;
    }



    public void actualizar(Entorno e, Bloque[] bloques) {
		    this.dibujarse(e);
            mover(e, bloques);
            if (!this.colisionoAbajo(bloques)) {
                this.caer(e);
            }
        }

    public void Cargando(Entorno e) {
        this.dibujarse(e);
        if(this.destino.equals("d")) {
            this.x += this.velocidad;
        }
        if (this.destino.equals("i")) {
            this.x -= this.velocidad;
        }
    
    }
}


    


   
