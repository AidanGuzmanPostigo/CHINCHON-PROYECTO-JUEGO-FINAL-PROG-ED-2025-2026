package dominio;
import java.util.List;
/**
 * Interfaz encargada de la baraja.
 */
public interface IDeck {
	/**
	 * Añade una carta a la pila de descartes.
	 * @param c Carta a añadir a la pila de descartes.
	 */
	void addCardToDiscardPile(Card c);
	/**
	 * Devuelve la carta posicionada en la última posición de la pila de descartes (carta boca arriba).
	 * @return Última carta de la pila de descartes.
	 */
	Card drawFromDiscardPile();
	/**
	 * Devuelve y elimina del mazo principal la primera carta, si el mazo está vacío al robar se baraja de nuevo.
	 * @return Primera carta del mazo principal.
	 */
	Card drawFromPrincipalDeck();
	/**
	 * Prepara el mazo en base al número de jugadores de la ronda, si hay más de dos se preparan 2 barajas.
	 * @param numberOfPlayers Número de jugadores de la ronda.
	 */
	void start(int numberOfPlayers);
	/**
	 * Getter de las cartas del mazo principal.
	 * @return Cartas en el mazo.
	 */
	List<Card> getCardsInDeck();
	/**
	 * Getter de las cartas de la pila de descartes.
	 * @return Cartas en la pila de descartes.
	 */
	List<Card> getDiscardPile();
}