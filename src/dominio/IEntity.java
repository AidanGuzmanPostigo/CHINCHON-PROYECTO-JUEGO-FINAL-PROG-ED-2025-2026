package dominio;
import java.util.List;
/**
 * Interfaz encargada de las entidades.
 */
public interface IEntity {
	/**
	 * Limpia la mano y la lista temporal de la entidad.
	 */
	void cleanHand();
	/**
	 * Prepara la mano de la entidad y la formatea para un formato legible.
	 * @return Cadena con la mano formateada preparada para imprimirse.
	 */
	String showHand();
	/**
	 * Añade una carta a la mano de la entidad.
	 * @param c Carta a añadir a la mano.
	 */
	void draw(Card c);
	/**
	 * Actualiza la puntuación de la entidad sumando la puntuación almacenada más las cartas restantes de la mano.
	 * @return Puntuación de la entidad actualizada.
	 */
	int updatePuntuation();
	/**
	 * Getter que devuelve el nombre de la entidad.
	 * @return Nombre de la entidad.
	 */
	String getNickname();
	/**
	 * Getter que devuelve la puntuación de la entidad.
	 * @return Puntuación de la entidad.
	 */
	int getPuntuation();
	/**
	 * Getter que devuelve la lista de cartas de la mano de la entidad.
	 * @return Lista de cartas de la mano de la entidad.
	 */
	List<Card> getHand();
	/**
	 * Descarta una carta.
	 * @param i Índice de la carta a descartar.
	 * @return Carta descartada.
	 */
	Card discard(int i);
	/**
	 * Realiza una combinación, si es válida la guarda en temporal y si no la mano se reinicia y no realiza la combinación.
	 * @param combination Combinación sin parsear con los índices de la mano.
	 * @return True si la combinación es correcta y se ha efectuado o false si no.
	 */
	boolean combinate(String combination);
	/**
	 * Añade las cartas de temporal a la mano y limpia temporal.
	 */
	void restartCombinations();
}