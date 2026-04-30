# CardType

## Función

Es un enum que representa los valores numéricos de las cartas de la baraja española.

## Atributos

- int **value**: Representación númerica de la carta y valor de la misma (1-7 y 10-12).

## Constantes

- CERO (1) 

- DOS(2) 

- TRES(3)

- CUATRO(4)

- CINCO(5)

- SEIS(6)

- SIETE(7)

- SOTA(10)

- CABALLO(11)

- REY(12)

- ERROR(99): Es una instacia especial usada en caso de errores.

## Constructor

El constructor del enum es el siguiente:

```java
CardType(int value){
	this.value = value;
}
```

## Métodos

- public int getValue: Getter para tener el valor de la carta.

    ```java
    public int getValue() {
		return value;
	}
    ```

## Relaciones con otras clases

Este enum es usado por el record Card, la clase Deck y la clase Entity.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)