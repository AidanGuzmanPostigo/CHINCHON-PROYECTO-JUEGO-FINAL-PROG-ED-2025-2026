# Card

## Función

Es un record que representa una carta de la baraja española.

## Atributos

- CardType **number**: Representación númerica de la carta y valor de la misma (1-7 y 10-12).

- Suit **suit**: Representación del símbolo de los palos de la baraja española.

- int **id**: Id de la carta para diferenciar cartas con el mismo palo y número.

## Constructor

Este record tiene el constructor por defecto de los records, al que se le pasan sus 3 atributos.

## Métodos

- public String toString: Presenta la carta en un formato legible y entendible. 

    ```java
    public String toString() {
		return String.format("%s%s", number.getValue(), suit.getSymbol());
	}
    ```

- public int compareTo: Compara 2 cartas por el valor numérico y si son iguales ordena por el palo, esto se usa para ordenar luego las manos de las entidades.

    ```java
    public int compareTo(Card c) {
		int comparation = Integer.compare(number.getValue(),c.number.getValue());
		if (comparation == 0) {
			return suit.compareTo(c.suit);
		} else {
			return comparation;
		}
	}
    ```
## Relaciones con otras clases

Este record es usado por la clase Entity (y clases que extiendan de esta (Cpu)), por la clase Deck y por la clase Game.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)