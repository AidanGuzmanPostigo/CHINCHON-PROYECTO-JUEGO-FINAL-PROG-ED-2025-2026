# Cpu

## Función

Sirve como una Cpu para jugar en el chinchon.

Es muy simple, no cierra las rondas ni realiza lógica compleja.

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
		if (discardPileCard.number().getValue() <= 4) {
			return 2;
		}
		return 1;
	}
	```

- public void choosePlayClose: Combina las cartas de la mano de la CPU para quitarse puntos, va de derecha a izquierda para quitarse la mayor cantidad de puntos posible.

	```java
	
	```

## Relaciones con otras clases

Esta clase extiende a Entidad, implementa la interfaz ICpu (que a su vez esta interfaz extiende la interfaz IEntity), usa el record Card y es usada por la clase Game.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)