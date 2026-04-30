# FactoryEntity

## Función

Sirve como factoría para las entidades creando a entidades básicas (jugadores) o Cpus.

## Atributos

Esta clase no contiene ningún atributo.

## Constructor

Esta clase no tiene ningún constructor.

## Métodos

- public IEntity buildEntity: Este método recibe un número y en base a eso devuelve una entidad plana o una Cpu.

    ```java
    public IEntity buildEntity(int entityTipe, String nickname) {
		if (entityTipe == 1) {
			return new Entity(nickname);
		} else {
			return new Cpu(nickname);
		}
	}
    ```

## Relaciones con otras clases

Esta clase usa la interfaz IEntity y aquellas clases que la implementan.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)