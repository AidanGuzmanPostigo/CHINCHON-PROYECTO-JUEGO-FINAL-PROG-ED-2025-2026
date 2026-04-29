package dominio;
/**
 * Record que almacena una carta de la baraja española.
 * @param number Representación númerica de la carta y valor de la misma (1-7 y 10-12).
 * @param suit Representación del símbolo de los palos de la baraja española.
 * @param id Id de la carta para diferenciar cartas con el mismo palo y número.
 */
public record Card(CardType number, Suit suit, int id) implements Comparable<Card> {
	@Override
	public String toString() {
		return String.format("%s%s", number.getValue(), suit.getSymbol());
	}

	@Override
	public int compareTo(Card c) {
		int comparation = Integer.compare(number.getValue(),c.number.getValue());
		if (comparation == 0) {
			return suit.compareTo(c.suit);
		} else {
			return comparation;
		}
	}
}