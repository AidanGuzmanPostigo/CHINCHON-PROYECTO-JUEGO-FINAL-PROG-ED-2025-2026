package app;
public class Menu{
	private ConsoleInput ci;
	private static Menu instance;
	private Menu () {
		this.ci = ConsoleInput.getInstance();
	}
	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
	public int numberOfPlayersMenu() {
		return ci.readIntInRange(2, 5, "Introduce el número de jugadores.");
	}
	public int typeOfEntityMenu() {
		return ci.readInt("Introduce 1 si quieres 1 jugador o 2 si quieres una CPU");
	}
	public String nicknameMenu() {
		return ci.readString("Introduce el mote del jugador.");
	}
	public int puntuationMenu() {
		return ci.readInt("Introduce la puntuación máxima para quedar eliminado.");
	}
	public int startMenu() {
		ci.writeLine(String.format("%12s\n______________________\n","MENÚ"));
		return ci.readIntInRange(1, 2, "1. Empezar Partida\n2. Salir del programa.");
	}
	public int optionsMenu(String card,String hand) {
		return ci.readIntInRange(1,2,String.format("%s1 - Robar carta de la baraja\n2 - Robar carta de la pila de descartes (%s)",hand,card));
	}
	public int closeMenu(String hand) {
		return ci.readIntInRange(1, 2, String.format("%s1 - Descartar una carta\n2 - Cerrar",hand));
	}
	public void winnerMenu(String player) {
		ci.writeLine(String.format("¡%s ha ganado!\n", player));
	}
	public void nicknameNotAvailable() {
		ci.writeLine("Ya hay un jugador con ese mote, tienes que elegir otro.");
	}
	public void closeErrorTurn1() {
		ci.writeError("No se puede cerrar en el turno 1.");
	}
	public void unexpectedError() {
		ci.writeError("Ha ocurrido un error inesperado.");
	}
	public int discardCardMenu(int min, int max, String hand) {
		return ci.readIntInRange(min, max, String.format("Elige la carta de la mano a descartar (%s-%s)\n%s", min,max,hand))-1;
	}
	public int closeCardMenu(int min, int max, String hand) {
		return ci.readIntInRange(min, max, String.format("Elige la carta de la mano que vas a usar para cerrar (%s-%s)\n%s", min,max,hand))-1;
	}
	public int endRoundMenu(String hand) {
		return ci.readIntInRange(1,2,String.format("%s1 - Combinar cartas\n2 - Terminar.",hand));
	}
	public String combinationsMenu(String hand){
		return ci.readString(String.format("Elige las cartas de tu mano que quieres usar para combinar, introduce la posición utilizando \"-\" para separar las posiciones\n%s",hand));
	}
	public void combinationError() {
		ci.writeError("No has introducido la combinación correctamente o no es válida.");
	}
	public void specificCaseCombination() {
		ci.writeError("No puedes cerrar con esa combinación ya que no puedes quedarte a una carta de esa manera.");
	}
	public void closeErrorPuntuation() {
		ci.writeError("No puedes cerrar teniendo una carta de + de 5 de valor.");
	}
	public void maxPointsClose() {
		ci.writeError("No puedes cerrar porque llegas al máximo de puntos");
	}
}