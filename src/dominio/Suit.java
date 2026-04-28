package dominio;
/**
 * Enum para el palo de la carta.<br>
 * Contiene la representación de los 4 palos de la baraja española.
 */
public enum Suit {
	ORO("\u001B[33m\uD83D\uDCB0"), ESPADAS("\u001B[34m\uD83D\uDDE1"), BASTOS("\u001B[32m\uD83C\uDF3F"), COPAS("\u001B[31m\uD83C\uDF77"), ERROR("??");
	private String symbol;
	Suit(String simbol){
		this.symbol = simbol+"\u001B[0m";
	}
	public String getSymbol() {
		return symbol;
	}
}