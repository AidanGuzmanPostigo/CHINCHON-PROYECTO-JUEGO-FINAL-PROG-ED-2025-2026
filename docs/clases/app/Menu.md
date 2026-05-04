# Menu

## Función

Es la clase encargada de mostrar los menús y mensajes por defecto.

## Atributos

- private ConsoleInput **ci**: Consola usada para mosrtar y pedir datos al usuario.

- private static Menu **instance**: Atributo estático de la clase que contiene (si ha sido instanciada) la única instancia posible de la clase. 

## Constructor

El constructor de esta clase es privado ya que solo se llama en el getInstance para cumplir con el patrón Singleton.

```java
private Menu () {
	this.ci = ConsoleInput.getInstance();
}
```

## Métodos

- public static Menu getInstance: Método estático que crea una instancia de la clase y la devuelve o si ya la hay solo la devuelve.

	```java
	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
	```

- public int numberOfPlayersMenu: Pregunta cuántos jugadores se quieren tener en la partida.

	```java
	public int numberOfPlayersMenu() {
		return ci.readIntInRange(2, 5, "Introduce el número de jugadores.");
	}
	```

- public int typeOfEntityMenu: Pregunta que tipo de entidad se va a crear, 1 para un jugador o 2 para una Cpu.

	```java
	public int typeOfEntityMenu() {
		return ci.readIntInRange(1,2,"Introduce 1 si quieres 1 jugador o 2 si quieres una CPU");
	}
	```

- public String nicknameMenu: Pregunta el mote de la entidad.

	```java
	public String nicknameMenu() {
		return ci.readString("Introduce el mote del jugador.");
	}
	```

- public int puntuationMenu: Pregunta la cantidad de puntos en la que se elimina una entidad en la partida, al menos 1.

	```java
	public int puntuationMenu() {
		return ci.readIntGreaterOrEqualThan(1, "Introduce la puntuación máxima para quedar eliminado.");
	}
	```

- public int startMenu: Menú de inicio del programa, pregunta si se quiere empezar una partida o abandonar el programa.

	```java
	public int startMenu() {
		ci.writeLine(String.format("%12s\n______________________\n","MENÚ"));
		return ci.readIntInRange(1, 2, "1. Empezar Partida\n2. Salir del programa.");
	}
	```

- public int optionsMenu: Pregunta donde se quiere robar, 1 para el mazo o 2 para coger la carta superior de la pila de descartes.

	```java
	public int optionsMenu(String card,String hand) {
		return ci.readIntInRange(1,2,String.format("%s1 - Robar carta de la baraja\n2 - Robar carta de la pila de descartes (%s)",hand,card));
	}
	```

- public int closeMenu: Pregunta si se va a descartar o a cerrar la ronda, 1 para descartar y 2 para cerrar la ronda.

	```java
	public int closeMenu(String hand) {
		return ci.readIntInRange(1, 2, String.format("%s1 - Descartar una carta\n2 - Cerrar",hand));
	}
	```

- public void winnerMenu: Muestra a la entidad ganadora de la partida.

	```java
	public void winnerMenu(String player) {
		ci.writeLine(String.format("¡%s ha ganado!", player));
	}
	```

- public void nicknameNotAvailable: Muestra un mensaje avisando de que el mote no está disponible.

	```java
	public void nicknameNotAvailable() {
		ci.writeLine("Ya hay un jugador con ese mote, tienes que elegir otro.");
	}
	```

- public void closeErrorTurn1: Muestra un error avisando de que no se puede cerrar en turno 1.

	```java
	public void closeErrorTurn1() {
		ci.writeError("No se puede cerrar en el turno 1.");
	}
	```

- public int discardCardMenu: Pregunta por el índice de la carta a descartar (empezando por 1) y luego le resta -1.

	```java
	public int discardCardMenu(int max, String hand) {
		return ci.readIntInRange(1, max, String.format("Elige la carta de la mano a descartar (%s-%s)\n%s", 1,max,hand))-1;
	}
	```

- public int closeCardMenu: Pregunta por el índice de la carta que se usa para cerrar (empezando por 1) y luego le resta -1.

	```java
	public int closeCardMenu(int max, String hand) {
		return ci.readIntInRange(1, max, String.format("Elige la carta de la mano que vas a usar para cerrar (%s-%s)\n%s", 1,max,hand))-1;
	}
	```

- public int endRoundMenu: Menú mostrado a las entidades no ganadoras de una ronda cuando esta ya se ha cerrado, pregunta si se quiere combinar cartas de la mano o se desea terminar de combinar.

	```java
	public int endRoundMenu(String hand) {
		return ci.readIntInRange(1,2,String.format("%s1 - Combinar cartas\n2 - Terminar.",hand));
	}
	```

- public String combinationsMenu: Pide una cadena formateada de la siguiente manera X-X-X, uusada para recibir las combinaciones del usuario.

	```java
	public String combinationsMenu(String hand){
		return ci.readString(String.format("Elige las cartas de tu mano que quieres usar para combinar, introduce la posición utilizando \"-\" para separar las posiciones\n%s",hand));
	}
	```

- public void combinationError: Muestra un mensaje de que la combinación es errónea.

	```java
	public void combinationError() {
		ci.writeError("No has introducido la combinación correctamente o no es válida.");
	}
	```

- public void specificCaseCombination: Muestra un mensaje de error si se combina un número incorrecto de cartas en la primera combinación (ejemplo, combinar 5 cartas).

	```java
	public void specificCaseCombination() {
		ci.writeError("No puedes cerrar con esa combinación ya que no puedes quedarte a una carta de esa manera.");
	}
	```

- public void closeErrorPuntuation: Muestra un mensaje de error si se intenta cerrar con una carta cuyo valor es mayor a 5.

	```java
	public void closeErrorPuntuation() {
		ci.writeError("No puedes cerrar teniendo una carta de + de 5 de valor.");
	}
	```

- public void maxPointsClose: Muestra un mensaje si se intenta cerrar alcanzando el límite de puntos.

	```java
	public void maxPointsClose() {
		ci.writeError("No puedes cerrar porque llegas al máximo de puntos");
	}
	```

- public void cleanConsoleForNewTurn: Simula una limpieza de consola para iniciar el turno de la siguiente entidad.

	```java
	public void cleanConsoleForNewTurn() {
		for (int i = 1; i<=50;i++) {
			ci.writeLine("");
		}
	}
	```

## Relaciones con otras clases

Esta clase usa la clase ConsoleInput y es usada por la clase Game.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)