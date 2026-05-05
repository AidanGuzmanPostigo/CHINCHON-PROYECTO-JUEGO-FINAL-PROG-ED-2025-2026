# Cpu

## Función

Sirve como una Cpu para jugar en el chinchon.

## Atributos

Tiene los atributos heredados sin aportar ninguno nuevo.

## Constructor

Esta clase tiene un constructor que es similar al de la entidad, se le pasa un mote y se le asigna.

```java
public Cpu(String nickname) {
	super(nickname);
}
```

## Métodos

- public int chooseWhereDraw: Método que permite a la CPU elegir 1 si quiere robar del mazo o 2 si quiere robar de la pila de descartes.

	```java
	public int chooseWhereDraw(Card discardPileCard) {
		if (discardPileCard.number().getValue() <= 4 && discardPileCard.number().getValue() < hand.get(hand.size()-1).number().getValue()) {
			return 2;
		}
		return 1;
	}
	```

- public void choosePlayClose: Combina las cartas de la mano de la CPU para quitarse puntos de la ronda.

	```java
	public void choosePlayClose() {
		searchEquals(true);
		searchStraight(true);
    }
	```

- public boolean canClose: Comprueba si la Cpu puede cerrar.

	```java
	public boolean canClose(int maxpoints) {
		searchEquals(false);
		searchStraight(false);
		if (hand.size()<=2) {
			if ((hand.size() == 2 && hand.get(0).number().getValue() <=5) && (hand.get(0).number().getValue() + getPuntuation()) < maxpoints) {
				hand.remove(1);
				return true;
			} else {
				if (!hand.isEmpty()) {
					hand.remove(0);
				}
				return true;
			}
		}
		restartCombinations();
		return false;
	}
	```

- private void searchStraight: Comprueba si la Cpu tiene escaleras en la mano.

	```java
	private void searchStraight(boolean cleanTemporal) {
		int[] auxArray = new int[10];
		List<Integer> aux = new ArrayList<>();
		for (Suit st: Suit.values()) {
			aux.clear();
			auxArray = createArrayForSuit(st);
			for (int i = 9; i>=0; i--) {
				if (auxArray[i] != -1) {
					aux.add(auxArray[i]);
				} else {
					if (aux.size() >=3) {
						combinate(parseCombinations(aux));
						if (cleanTemporal) {
							temporal.clear();
						}
					}
					aux.clear();
				}
			}
			if (aux.size()>=3) {
				combinate(parseCombinations(aux));
				if (cleanTemporal) {
					temporal.clear();
				}
			}
		}
	}
	```

- private int [] createArrayForSuit: Crea un array auxiliar que ayuda a buscar las escaleras almacena -1 en todas las posiciones y si encuentra el número de ese palo cambia el -1 por el índice de la carta en la mano.

	```java
	private int [] createArrayForSuit(Suit st) {
		int[] auxArray = new int[10];
		Arrays.fill(auxArray, -1);
		for (int i = 0; i< hand.size();i++) {
			if (hand.get(i).suit() == st) {
				switch (hand.get(i).number()) {
				case UNO -> auxArray[0] = i+1;
				case DOS -> auxArray[1] = i+1;
				case TRES -> auxArray[2] = i+1;
				case CUATRO -> auxArray[3] = i+1;
				case CINCO -> auxArray[4] = i+1;
				case SEIS -> auxArray[5] = i+1;
				case SIETE -> auxArray[6] = i+1;
				case SOTA -> auxArray[7] = i+1;
				case CABALLO -> auxArray[8] = i+1;
				case REY -> auxArray[9] = i+1;
				}
			}
		}
		return auxArray;
	}
	```

- private void searchEquals: Comprueba si la Cpu tiene combinaciones de tipo iguales en la mano.

	```java
	private void searchEquals(boolean cleanTemporal) {
		List<Integer> aux = new ArrayList<>();
		for (CardType ct: CardType.values()){
			aux.clear();
			for (int i = 0; i< hand.size();i++) {
				if (hand.get(i).number() == ct) {
					aux.add(i+1);
				}
			}
			if (aux.size() >=3) {
				combinate(parseCombinations(aux));
				if (cleanTemporal) {
					temporal.clear();
				}
			}
		}
	}
	```

- private String parseCombinations: Parsea una lista de índices a una cadena con formato X-X-X.

	```java
	private String parseCombinations(List<Integer> index) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i<index.size();i++) {
			if (i == index.size()-1) {
				sb.append(index.get(i));
			} else {
				sb.append(String.format("%d-", index.get(i)));
			}
		}
		return sb.toString();
	}
	```

## Relaciones con otras clases

Esta clase extiende a Entidad, implementa la interfaz ICpu (que a su vez esta interfaz extiende la interfaz IEntity), usa el record Card y es usada por la clase Game.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)