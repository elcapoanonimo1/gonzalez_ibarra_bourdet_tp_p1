package entorno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Timer timer;
    private Graphics2D g2d;
    private InterfaceJuego juego;
    private boolean[] teclas;
    private boolean[] teclas_momento;    
    private boolean iniciado;

    public Board(InterfaceJuego j)
    {
    	iniciado = false;
    	juego = j;
    	teclas = new boolean[256];
    	teclas_momento = new boolean[256];
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);        
    }
    
    public void iniciar()
    {
    	iniciado = true;
        timer = new Timer(10, this);
        timer.start();        
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g2d = (Graphics2D)g;
        
        try
        {
        	if (iniciado)
        		juego.tick();
        	
        	liberarTeclasMomento();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        g2d = null;
    }

    private void liberarTeclasMomento() 
    {
    	for (int i = 0; i < teclas_momento.length; i++) 
    		teclas_momento[i] = false;
	}

	// Llamado cuando salta el timer
    public void actionPerformed(ActionEvent e)
    {
        repaint();  
    }

    private class TAdapter extends KeyAdapter
    {
        public void keyReleased(KeyEvent e)
        {
        	int key = e.getKeyCode();
        	if( 0 <= key && key < teclas.length )
        	{
        		teclas[key] = false;
        		teclas_momento[key] = false;
        	}
        }

        public void keyPressed(KeyEvent e)
        {
        	int key = e.getKeyCode();
        	if( 0 <= key && key < teclas.length )
        	{
        		teclas_momento[key] = false;
        		
        		if (!teclas[key])
        			teclas_momento[key] = true;
        		
        		teclas[key] = true;
        	}
        }
    }
    
    public Graphics2D getG2D()
    {
    	return g2d;
    }
    
//    public boolean[] getKeys()
//    {
//    	return teclas;
//    }

	public boolean estaPresionada(char key) 
	{
		if (97 <= key && key <= 122) // la paso a mayusculas
			key -= 32;
				
		if( key < 0 || key >= teclas.length )
			throw new RuntimeException( "Error! Se consultsi la tecla " + (int) key + " estpresionada, pero esa tecla no existe." );
		
		return teclas[key];
	}
	
	public boolean sePresiono(char key) 
	{
		if (97 <= key && key <= 122) // la paso a mayusculas
			key -= 32;
				
		if( key < 0 || key >= teclas_momento.length )
			throw new RuntimeException( "Error! Se consultsi la tecla " + (int) key + " estpresionada, pero esa tecla no existe." );
		
		return teclas_momento[key];
	}
}