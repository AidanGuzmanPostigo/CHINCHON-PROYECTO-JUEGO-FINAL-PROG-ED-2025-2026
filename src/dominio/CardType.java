package dominio;
/**
 * Enum para el valor de la carta.<br>
 * Contiene la representación del 1-7 y del 10-12.
 */
public enum CardType {
	UNO(1),DOS(2),TRES(3),CUATRO(4),CINCO(5),SEIS(6),SIETE(7),SOTA(10),CABALLO(11),REY(12),ERROR(99);
	private int value;
	CardType(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}