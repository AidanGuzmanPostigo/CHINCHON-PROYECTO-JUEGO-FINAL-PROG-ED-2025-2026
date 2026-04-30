# Deck

## Función

Es la clase encargada del mazo.

## Atributos

- private List<Card> **cardsInDeck**: Lista de cartas del mazo.

- private List<Card> **discardPile**: Lista de cartas de la pila de descartes.

## Constructor

Este es el constructor de la clase, instancia ambas listas de cartas

```java
public Deck () {
	cardsInDeck = new ArrayList<>();
	discardPile = new ArrayList<>();
}
```

## Métodos

- public void start: Prepara el mazo en base al número de jugadores de la ronda, si hay más de dos se preparan 2 barajas, de manera que añade todas las cartas de la baraja española y luego baraja el mazo.

	```java
	public void start(int numberOfPlayers) {
		CardType ct;
		Suit st;
		int id=0;
		cardsInDeck.clear();
		discardPile.clear();
		for (int i = 0; i < (numberOfPlayers<3?1:2);i++) {
			for (int j = 1; j<=4;j++) {
				st = switch (j) {
				case 1 -> Suit.ORO;
				case 2 -> Suit.ESPADAS;
				case 3 -> Suit.COPAS;
				case 4 -> Suit.BASTOS;
				default -> Suit.ERROR;
				};
				for (int k = 1; k<=10; k++) {
				ct = switch (k) {
				case 1 -> CardType.UNO;
				case 2 -> CardType.DOS;
				case 3 -> CardType.TRES;
				case 4 -> CardType.CUATRO;
				case 5 -> CardType.CINCO;
				case 6 -> CardType.SEIS;
				case 7 -> CardType.SIETE;
				case 8 -> CardType.SOTA;
				case 9 -> CardType.CABALLO;
				case 10 -> CardType.REY;
				default -> CardType.ERROR;
				};
				addCardToPrincipalDeck(new Card(ct,st,++id));
				}
			}
		}
		shuffle();
	}
	```

- public Card drawFromPrincipalDeck: Devuelve y elimina del mazo principal la primera carta, si el mazo está vacío al robar se baraja de nuevo.

	```java
	public Card drawFromPrincipalDeck() {
		Card c;
		if (cardsInDeck.size() == 0) {
			shuffle();
		}
		c = cardsInDeck.get(0);
		cardsInDeck.remove(0);
		return c;
	}
	```

- public Card drawFromDiscardPile: Devuelve y elimina la última carta de la pila de descartes (la que se muestra boca arriba).

	```java
	public Card drawFromDiscardPile() {
		Card c = discardPile.get(discardPile.size()-1);
		discardPile.remove(discardPile.size()-1);
		return c;
	}
	```

- private void addCardToPrincipalDeck: Añade una o más cartas al mazo principal.

	```java
	private void addCardToPrincipalDeck(Card ... c) {
		cardsInDeck.addAll(Arrays.asList(c));
	}
	```

- public void addCardToDiscardPile: Añade una carta a la pila de descartes.

	```java
	public void addCardToDiscardPile(Card c) {
		discardPile.add(c);
	}
	```

- private void shuffle: Método que baraja la baraja, si ocurre a mitad de la ronda garantiza que no se pierda la carta boca arriba de la pila de descartes.

	```java
	private void shuffle() {
		Card c;
		if (discardPile.size() != 0) {
			c = discardPile.get(discardPile.size()-1);
			discardPile.remove(c);
			cardsInDeck.addAll(discardPile);
			discardPile.clear();
			discardPile.add(c);
		}
		Collections.shuffle(cardsInDeck);
	}
	```

- public List<Card> getCardsInDeck: Getter para obtener la lista de cartas del mazo principal.

	```java
	public List<Card> getCardsInDeck() {
		return cardsInDeck;
	}
	```

- public List<Card> getDiscardPile: Getter para obtener la lista de descartes.

	```java
	public List<Card> getDiscardPile() {
		return discardPile;
	}
	```

## Relaciones con otras clases

Esta clase usa el record Card y es usada por la clase Game.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)