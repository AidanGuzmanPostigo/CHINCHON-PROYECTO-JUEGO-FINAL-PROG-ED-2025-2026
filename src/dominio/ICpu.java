package dominio;
public interface ICpu extends IEntity{
	/**
	 * Encuentra y combina las combinaciones de su mano para restarse puntos en la ronda.
	 */
	void choosePlayClose();
	/**
	 * Hace que la Cpu pueda elegir entre robar del mazo o de la pila de descartes.
	 * @param discardPileCard Carta boca arriba de la pila de descartes.
	 * @return 1 roba del mazo o 2 si roba de la pila de descartes.
	 */
	int chooseWhereDraw(Card discardPileCard);
	/**
	 * Comprueba que la Cpu puede cerrar.
	 * @param maxpoints Puntuación límite de la partida.
	 * @return True si puede cerrar o False si no.
	 */
	boolean canClose(int maxpoints);
}