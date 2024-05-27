package Entidades;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

public class Player {
    private double x;
    private double y;
    private int alto_personaje;
    private int ancho_personaje;
    private boolean estaAgachado = false;
    private boolean estaSaltando = false;
    private boolean puedeSaltar = true;
    protected double velocidad;
    protected double velocidadSalto = 5;
    protected double velocidadCaida = 10;
    protected double xAnterior= 0;
    protected String mira = "i";
    protected Image img;

    public Player(double x, double y, int alto_personaje, int ancho_personaje, double velocidad){
        this.x = x;
        this.y = y;
        this.alto_personaje = alto_personaje;
        this.ancho_personaje = ancho_personaje;
        this.velocidad = velocidad;

    }

    // DIBUJARSE
    public void dibujarse(Entorno e) {
        switch (se_mueve_a(e)) {
            case "d":
            img = Herramientas.cargarImagen("recursos/imagenes/Steve/Corriendo/Steve - corriendod.gif");
            e.dibujarImagen(img, x, y-10,0,3);
                break;
            
            case "i":
            img = Herramientas.cargarImagen("recursos/imagenes/Steve/Corriendo/Steve - corriendoi.gif");
            e.dibujarImagen(img, x, y-10,0,3);
                break;

            case "x":
            e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Steve/Steve - agachado.png"), x, y-10,0,3);
                break;
        
            default:
            e.dibujarImagen(Herramientas.cargarImagen("recursos/imagenes/Steve/Steve - quieto.png"), x, y-10,0,3);
                break;
    }
    }


    ///////// GETERS Y SETERS /////////

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getArriba() {
        return (this.y - alto_personaje/2);
    }

    public double getIzquierda() {
        return (this.x - ancho_personaje/2);
    }

    public double getDerecha() {
        return (this.x + ancho_personaje/2);
    }

    public double getAbajo() {
        return (this.y + alto_personaje/2);
    }

    //////// MOVIMIENTOS ////////

    public void moverDerecha(Entorno e) {
        this.x += velocidad;
    }

    public void moverIzquierda(Entorno e) {
        this.x -= velocidad;
    }

    public void caer(Entorno e) {
        if (getAbajo() >= e.alto()){
            this.y = ((e.alto())) ;
        } else {
            this.y = y+velocidadCaida;
        }   
        
    }


    public String se_mueve_a(Entorno e){
        if(e.estaPresionada(e.TECLA_DERECHA)) {
            return "d";
		} 

		if(e.estaPresionada(e.TECLA_IZQUIERDA)) {
			return "i";
		} 

        if (e.estaPresionada(e.TECLA_SHIFT)) {
            return "x";
        }

        if (e.sePresiono('c')) {
            return "c";
        } 

        /*if(e.estaPresionada(e.TECLA_ESPACIO) && !colicionaBorde("abajo", e)) {
            return "saltar";
        } else if (colicionaBorde("abajo", e) && !estaSaltando) { 
			return "caer";
		}*/
        return "null";
    }


/////////////////////////////////////////////////////////////////////////////
//////////////  ACCIONES    ////////////////////////////////////////////////

    public Proyectil disparar(){         
       return new Proyectil(x, y, this.alto_personaje, mira);
    }

    public void agachar(Entorno e, boolean presionadoAGACHAR) {

        if(presionadoAGACHAR && !estaAgachado) {
            this.estaAgachado = true;
            this.alto_personaje /= 2;   
        }

        if(!presionadoAGACHAR && estaAgachado) {
            this.estaAgachado = false;
            this.alto_personaje *= 2;
            this.y -= alto_personaje/4;
    
        }
    }

    public void saltar(Entorno e) {
        if(!estaSaltando && getArriba() > e.alto() - 100) {
            this.estaSaltando = true;
            this.y -= velocidadSalto;
        }
        if (estaSaltando) {
            this.y -= velocidadSalto;
        }

        if (getAbajo() > e.alto()- 100) {
            this.estaSaltando = false;
            this.puedeSaltar = false;
        }

    }
    
////////////////////////////////////    COLICIONES  /////////////////////////////////////////////////





     /*
      * El metodo actualizar() recibe un Entorno, y 
      * se encarga de actualizar todos elementos y/o acciones de la clase Player 
      * (Extencion de tick() de la clase Juego).
      */

    

    public void actualizar(Entorno e) {
		this.dibujarse(e);

		if(e.estaPresionada(e.TECLA_DERECHA) && (getDerecha() < e.ancho())) {
            mira = "d";
			this.moverDerecha(e);
		} 

		if(e.estaPresionada(e.TECLA_IZQUIERDA) && (getIzquierda() > 0))    {
			mira = "i";
            this.moverIzquierda(e);
		} 

        if (e.estaPresionada(e.TECLA_SHIFT)) {
            this.agachar(e, true);
        } else {
            this.agachar(e, false);
        }

        // if (e.sePresiono('c')) {
        //    Proyectil proyectil =this.disparar();
        // } 

        if((getAbajo() < e.alto() && !e.estaPresionada(e.TECLA_ESPACIO)) ) {
            this.caer(e);
        } else {
            this.puedeSaltar = true;
        }
        
        if(e.estaPresionada(e.TECLA_ESPACIO)) {
            this.saltar(e);
        }
    }


}   
