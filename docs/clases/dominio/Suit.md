# Suit

## Función

Es un enum que representa los 4 palos de la baraja española.

## Atributos

- String **symbol**: Representación del palo de la carta más el color representativo.

## Constantes

- ORO("\u001B[33m\uD83D\uDCB0"): Emoji representativo con el color amarillo. 

- ESPADAS("\u001B[34m\uD83D\uDDE1"): Emoji representativo con el color azul.  

- BASTOS("\u001B[32m\uD83C\uDF3F"): Emoji representativo con el color verde. 

- COPAS("\u001B[31m\uD83C\uDF77"): Emoji representativo con el color rojo. 

- ERROR("??"): Es una instacia especial usada en caso de errores.

## Constructor

El constructor del enum es el siguiente:

```java
Suit(String simbol){
	this.symbol = simbol+"\u001B[0m";
}
```

## Métodos

- public String getSymbol: Getter para obtener la cadena con el palo de la carta.

    ```java
    public String getSymbol() {
		return symbol;
	}
    ```

## Relaciones con otras clases

Este enum es usado por el record Card, la clase Deck y la clase Entity.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)