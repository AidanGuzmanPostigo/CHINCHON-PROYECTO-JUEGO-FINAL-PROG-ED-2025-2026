# Main

## Función

Es la clase encargada de comenzar la ejecución del programa.

## Atributos

Esta clase no contiene ningún atributo.

## Constructor

Esta clase no tiene ningún constructor.

## Métodos

- public void show: Este método inicia una IGame.

    ```java
    public void show() {
		IGame g = new Game();
		g.startConfiguration();
	}
    ```

- public static void main: Método estático de la clase que permite la ejecución y llamada del método show para comenzar el programa.

    ```java
    public static void main(String[] args) {
		new Main().show();
	}
    ```

## Relaciones con otras clases

Esta clase usa la interfaz IGame (clase Game) para permitir el inicio de la partida.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)