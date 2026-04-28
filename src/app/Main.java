package app;
/**
 * Clase Main, para iniciar el programa.
 */
public class Main {
	/**
	 * Método donde se crea la partida y se llama al método para empezarla.
	 */
	public void show() {
		IGame g = new Game();
		g.startConfiguration();
	}
	public static void main(String[] args) {
		new Main().show();
	}
}