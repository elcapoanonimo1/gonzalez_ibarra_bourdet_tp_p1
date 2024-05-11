package juego;


// Las líneas `import entorno.Entorno;` e `import entorno.InterfaceJuego;` están importando clases del
// paquete `entorno`. Esto permite que la clase `Juego` use las clases `Entorno` e `InterfaceJuego`
// definidas en el paquete `entorno` sin tener que calificar completamente sus nombres cada vez que se
// usan en el código. Esta es una práctica común en Java para hacer que el código sea más legible y
// fácil de mantener organizando clases relacionadas en paquetes e importándolas según sea necesario.
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import entorno.Board;



@SuppressWarnings("unused")
public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();


		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...

	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
