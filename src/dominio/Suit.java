package dominio;
public enum Suit {
	ORO("\u001B[33m\uD83D\uDCB0"), ESPADAS("\u001B[34m\uD83D\uDDE1"), BASTOS("\u001B[32m\uD83C\uDF3F"), COPAS("\u001B[31m\uD83C\uDF77"), ERROR("??");
	private String symbol;
	Suit(String simbol){
		this.symbol = simbol+"\u001B[0m";
	}
	public String getSimbol() {
		return symbol;
	}
}