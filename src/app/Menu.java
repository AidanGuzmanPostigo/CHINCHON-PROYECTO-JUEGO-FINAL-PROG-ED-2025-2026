package app;
/**
 * Clase encargada de utilizar la consola para pedir datos o mostrar mensajes al usuario.
 */
public class Menu{
	private ConsoleInput ci;
	private static Menu instance;
	/**
	 * Constructor privado de la clase que inicializa la consola.
	 */
	private Menu () {
		this.ci = ConsoleInput.getInstance();
	}
	/**
	 * Método estático que crea una instancia de la clase y la devuelve o si ya la hay solo la devuelve.
	 * @return Instancia de la clase.
	 */
	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
	/**
	 * Pregunta cuántos jugadores se quieren tener en la partida.
	 * @return Número de jugadores de la partida.
	 */
	public int numberOfPlayersMenu() {
		return ci.readIntInRange(2, 5, "Introduce el número de jugadores.");
	}
	/**
	 * Pregunta que tipo de entidad se va a crear.
	 * @return 1 si es Jugador o 2 si es Cpu.
	 */
	public int typeOfEntityMenu() {
		return ci.readIntInRange(1,2,"Introduce 1 si quieres 1 jugador o 2 si quieres una CPU");
	}
	/**
	 * Pregunta el mote de la entidad.
	 * @return Mote de la entidad.
	 */
	public String nicknameMenu() {
		return ci.readString("Introduce el mote del jugador.");
	}
	/**
	 * Pregunta la cantidad de puntos en la que se elimina una entidad en la partida, al menos 1.
	 * @return Número de puntos tope para eliminar una entidad, siempre mayor o igual que 1.
	 */
	public int puntuationMenu() {
		return ci.readIntGreaterOrEqualThan(1, "Introduce la puntuación máxima para quedar eliminado.");
	}
	/**
	 * Menú de inicio del programa, pregunta si se quiere empezar una partida o abandonar el programa.
	 * @return 1 si se quiere empezar una partida o 2 si se quiere cerrar el programa.
	 */
	public int startMenu() {
		ci.writeLine(String.format("%12s\n______________________\n","MENÚ"));
		return ci.readIntInRange(1, 2, "1. Empezar Partida\n2. Salir del programa.");
	}
	/**
	 * Pregunta donde se quiere robar.
	 * @param card Carta boca arriba de la pila de descartes.
	 * @param hand Mano formateada de la entidad.
	 * @return 1 si se roba de la baraja principal o 2 para robar la carta boca arriba de la pila de descartes.
	 */
	public int optionsMenu(String card,String hand) {
		return ci.readIntInRange(1,2,String.format("%s1 - Robar carta de la baraja\n2 - Robar carta de la pila de descartes (%s)",hand,card));
	}
	/**
	 * Pregunta si se va a descartar o a cerrar la ronda.
	 * @param hand Mano formateada de la entidad.
	 * @return 1 si se va a descartar o 2 si se va a cerrar.
	 */
	public int closeMenu(String hand) {
		return ci.readIntInRange(1, 2, String.format("%s1 - Descartar una carta\n2 - Cerrar",hand));
	}
	/**
	 * Muestra a la entidad ganadora de la partida.
	 * @param player Mote de la entidad ganadora de la partida.
	 */
	public void winnerMenu(String player) {
		ci.writeLine(String.format("¡%s ha ganado!", player));
	}
	/**
	 * Muestra un mensaje avisando de que el mote no está disponible.
	 */
	public void nicknameNotAvailable() {
		ci.writeLine("Ya hay un jugador con ese mote, tienes que elegir otro.");
	}
	/**
	 * Muestra un error avisando de que no se puede cerrar en turno 1.
	 */
	public void closeErrorTurn1() {
		ci.writeError("No se puede cerrar en el turno 1.");
	}
	/**
	 * Pregunta por el índice de la carta a descartar (empezando por 1) y luego le resta -1.
	 * @param max Posición de la última carta de la mano de la entidad.
	 * @param hand Mano formateada de la entidad.
	 * @return Posición de la carta a descartar.
	 */
	public int discardCardMenu(int max, String hand) {
		return ci.readIntInRange(1, max, String.format("Elige la carta de la mano a descartar (%s-%s)\n%s", 1,max,hand))-1;
	}
	/**
	 * Pregunta por el índice de la carta que se usa para cerrar (empezando por 1) y luego le resta -1.
	 * @param max Posición de la última carta de la mano de la entidad.
	 * @param hand Mano formateada de la entidad.
	 * @return Posición de la carta que se va a usar para cerrar.
	 */
	public int closeCardMenu(int max, String hand) {
		return ci.readIntInRange(1, max, String.format("Elige la carta de la mano que vas a usar para cerrar (%s-%s)\n%s", 1,max,hand))-1;
	}
	/**
	 * Menú mostrado a las entidades no ganadoras de una ronda cuando esta ya se ha cerrado, pregunta si se quiere combinar cartas de la mano o se desea terminar de combinar.
	 * @param hand Mano formateada de la entidad.
	 * @return 1 si se quiere realizar una combinación o 2 si se quiere terminar de combinar.
	 */
	public int endRoundMenu(String hand) {
		return ci.readIntInRange(1,2,String.format("%s1 - Combinar cartas\n2 - Terminar.",hand));
	}
	/**
	 * Pide una cadena formateada de la siguiente manera X-X-X.
	 * @param hand Mano formateada de la entidad.
	 * @return Entrada introducida por el usuario, se espera una cadena con formato X-X-X.
	 */
	public String combinationsMenu(String hand){
		return ci.readString(String.format("Elige las cartas de tu mano que quieres usar para combinar, introduce la posición utilizando \"-\" para separar las posiciones\n%s",hand));
	}
	/**
	 * Muestra un mensaje de que la combinación es errónea.
	 */
	public void combinationError() {
		ci.writeError("No has introducido la combinación correctamente o no es válida.");
	}
	/**
	 * Muestra un mensaje de error si se combina un número incorrecto de cartas en la primera combinación (ejemplo, combinar 5 cartas).
	 */
	public void specificCaseCombination() {
		ci.writeError("No puedes cerrar con esa combinación ya que no puedes quedarte a una carta de esa manera.");
	}
	/**
	 * Muestra un mensaje de error si se intenta cerrar con una carta cuyo valor es mayor a 5.
	 */
	public void closeErrorPuntuation() {
		ci.writeError("No puedes cerrar teniendo una carta de + de 5 de valor.");
	}
	/**
	 * Muestra un mensaje si se intenta cerrar alcanzando el límite de puntos.
	 */
	public void maxPointsClose() {
		ci.writeError("No puedes cerrar porque llegas al máximo de puntos");
	}
	/**
	 * Simula una limpieza de consola para iniciar el turno de la siguiente entidad.
	 */
	public void cleanConsoleForNewTurn() {
		for (int i = 1; i<=50;i++) {
			ci.writeLine("");
		}
	}
}