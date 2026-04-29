# Cpu **WIP**

## Función

Sirve como una Cpu para jugar en el chinchon.

## Atributos

## Constructor

Esta clase tiene un constructor que es similar al de la entidad, se le pasa un mote y se le asigna.

```java
public Cpu(String nickname) {
	super(nickname);
}
```

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
