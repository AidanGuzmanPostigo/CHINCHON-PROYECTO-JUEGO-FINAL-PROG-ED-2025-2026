# Entity

## Función

Es la clase que representa a las entidades de la partida.

## Atributos

- private List<Card> **hand**: Lista de cartas de la mano de la entidad.

- private List<Card> **temporal**: Lista de cartas auxiliar que contendrá las combinaciones de cartas hasta que el turno de la entidad termine, se usa para evitar perder cartas al hacer combinaciones erróneas.

- private String **nickname**: Mote de la entidad, debe ser único para cada entidad.

- private int **puntuation**: Puntuación actual de la entidad

## Constructor

Esta clase tiene un constructor al que se le pasa un mote y se le asigna, inicializa la mano, la lista temporal de cartas y pone a 0 la puntuación.

```java
public Entity(String nickname) {
	hand = new ArrayList<>();
	temporal = new ArrayList<>();
	this.nickname = nickname;
	puntuation = 0;
}
```

## Métodos

- public int getPuntuation: Getter que devuelve la puntuación de la entidad.

	```java
	public int getPuntuation() {
		return puntuation;
	}
	```

- public boolean equals: El método equals compara por el nickname.

	```java
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return Objects.equals(nickname, other.nickname);
	}
	```

- public String getNickname: Getter que devuelve el nombre de la entidad.

	```java
	public String getNickname() {
		return nickname;
	}
	```

- public List<Card> getHand: Getter que devuelve la lista de cartas de la mano de la entidad.

	```java
	public List<Card> getHand(){
		return hand;
	}
	```

- public boolean combinate: Realiza una combinación, si es válida la guarda en temporal y si no la mano se reinicia y no realiza la combinación, devuelve true si la combinación se ha realizado o false si no.

	```java
	public boolean combinate(String combination) {
		List<Card> auxList;
		if (isCombinationValid(combination)) {
			auxList = parseCombination(combination);
			temporal.addAll(auxList);
			hand.removeAll(auxList);
			return true;
		}
		restartCombinations();
		return false;
	}
	```

- protected List<Card>parseCombination: Convierte una cadena de texto con formato X-X-X en una lista de cartas en base a los índices de la cadena.

	```java
	protected List<Card>parseCombination(String combination) {
		String [] aux = combination.split("-");
		List<Integer> auxIndex = new ArrayList<>();
		List<Card> cardAux = new ArrayList<>();
		for (String s: aux) {
			auxIndex.add(Integer.parseInt(s));
		}
		for (Integer i: auxIndex) {
			cardAux.add(hand.get(i-1));
		}
		return cardAux;
	}
	```

- protected boolean isCombinationValid: Comprueba que una combinación es completamente válida, realiza llamadas a diversas funciones para validarlo y comprueba que la combinación no sea de 8 cartas.

	```java
	protected boolean isCombinationValid(String combination) {
		if (simpleValidateCombination(combination)) {
			if (isCombinationClean(combination)) {
				if (isSameNumber(combination) || isStraight(combination)) {
					if (parseCombination(combination).size()==8) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	```

- private boolean isCombinationClean: Comprueba que la combinación no contiene índices que la mano no tiene o índices repetidos.

	```java
	private boolean isCombinationClean(String combination) {
		String [] aux = combination.split("-");
		Set<Integer> auxList = new HashSet<>();
		for (String s: aux) {
			if (!auxList.add(Integer.parseInt(s))) {
				return false;
			}
		}
		for (Integer i :auxList) {
			if (i <= 0 || i>hand.size()) {
				return false;
			}
		}
		return true;
	}
	```

- protected boolean isSameNumber: Comprueba que todas las cartas de la combinación son del mismo valor numérico.

	```java
	protected boolean isSameNumber(String combination) {
		List<Card> aux = parseCombination(combination);
		int value = aux.get(0).number().getValue();
		for (int i = 1; i<aux.size();i++) {
			if (aux.get(i).number().getValue() != value) {
				return false;
			}
		}
		return true;
	}
	```

- protected boolean isStraight: Comprueba que la combinación es una escalera restando la carta de la izquierda con la de la derecha o comprobando si es 7 y sota.

	```java
	protected boolean isStraight(String combination) {
		List<Card> aux = parseCombination(combination);
		Collections.sort(aux);
		Card value = aux.get(0);
		Suit suitOfStraight = aux.get(0).suit();
		if (!allAreSameSuit(aux, suitOfStraight)) {
			return false;
		}
		for (int i = 1; i<aux.size();i++) {
			if (!((value.number().getValue() - aux.get(i).number().getValue() == -1) || (value.number().name().equals("SIETE") && aux.get(i).number().name().equals("SOTA")))) {
				return false;
			}
			value = aux.get(i);
		}
		return true;
	}
	```

- protected boolean allAreSameSuit: Comprueba que todas las cartas de una lista de cartas sean del mismo palo que un palo concreto.

	```java
	protected boolean allAreSameSuit(List<Card>cards, Suit st) {
		for (Card c: cards) {
			if (c.suit() != st) {
				return false;
			}
		}
		return true;
	}
	```

- private boolean simpleValidateCombination: Comprueba que el formato de la combinación es correcto.

	```java
	private boolean simpleValidateCombination(String combination) {
		if (combination.matches("^([1-8]-){2,}[1-8]")) {
			return true;
		}
		return false;
	}
	```

- public void draw: Añade una carta a la mano de la entidad.

	```java
	public void draw(Card c) {
		hand.add(c);
		orderHand();
	}
	```

- public Card discard:  Descarta una carta de la mano de la entidad.

	```java
	public Card discard(int i) {
		Card aux = hand.get(i);
		hand.remove(i);
		orderHand();
		return aux;
	}
	```

- public void cleanHand: Limpia ambas listas de cartas de la entidad, se usa para empezar las rondas con una nueva lista de cartas en la mano y con la lista temporal reiniciada.

	```java
	public void cleanHand() {
		hand.clear();
		temporal.clear();
	}
	```

- protected int calculatePuntuation: Calcula el valor numérico de todas las cartas de la mano que no han sido combinadas en la ronda.

	```java
	protected int calculatePuntuation() {
		int puntuation = 0;
		if (hand.size()==0) {
			return -10;
		}
		for (Card c: hand) {
			puntuation+=c.number().getValue();
		}
		return puntuation;
	}
	```

- public void restartCombinations: Devuelve la lista temporal a la mano y limpia la lista temporal, se usa para que si una combinación es errónea la mano de la entidad no pierda cartas.

	```java
	public void restartCombinations(){
		hand.addAll(temporal);
		temporal.clear();
		orderHand();
	}
	```

- public int updatePuntuation: Suma a la puntuación actual el valor numérico de las cartas de la mano que no han sido combinadas en la ronda.

	```java
	public int updatePuntuation() {
		puntuation += calculatePuntuation();
		return puntuation;
	}
	```

- protected void orderHand: Ordena la lista de cartas de la mano de la entidad.

	```java
	protected void orderHand() {
		Collections.sort(hand);
	}
	```

- public boolean isChinchon:  Método que comprueba que una combinación es un Chinchón, se usa para evitar que al combinar 7 cartas del mismo valor se considere chinchón.

	```java
	public boolean isChinchon() {
		if (allAreSameSuit(temporal, temporal.get(0).suit()) && temporal.size() == 7) {
			return true;
		}
		return false;
	}
	```

- public String showHand: Formatea la mano en un formato legible para el usuario.  

	```java
	public String showHand() {
		StringBuilder sb = new StringBuilder("");
		sb.append(nickname + " - ");
		for (int i = 0; i< hand.size();i++) {
			if (i != 0 && i!= hand.size()-1) {
				sb.append(", ");
			} else if (i == hand.size()-1) {
				sb.append(" y ");
			}
			sb.append(String.format("%s [%d]",hand.get(i).toString(),i+1));
		}
		sb.append(".\n");
		return sb.toString();
	}
	```

- public String toString:  El toString de la entidad muestra su mote y la puntuación actual, se usa para mostrar al final de las rondas el estado de todas las entidades.

	```java
	public String toString() {
		return String.format("%s - %d", nickname,puntuation);
	}
	```

## Relaciones con otras clases

Esta clase implementa la interfaz IEntity, es la clase padre de la clase Cpu, usa el record Card y es usada por la clase Game. 

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)