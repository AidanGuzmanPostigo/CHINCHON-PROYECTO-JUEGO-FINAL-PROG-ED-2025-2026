# ConsoleInput

## Función

Es la clase encargada de la comunicación con el usuario (entrada-salida).

## Atributos

- private Scanner **keyboard**: Recurso del escáner usado para leer información mediante el teclado.

- private static ConsoleInput **instance**: Única instancia de la clase.

## Constructor

El constructor es privado ya que se ha aplicado a esta clase el patrón Singleton, para evitar tener más de una consola.

```java
private ConsoleInput() {
	keyboard = new Scanner(System.in);
}
```

## Métodos

Este método sirve para conseguir la única instancia posible de la clase, en caso de que el atributo instance esté a nulo se instanciará la clase dentro de este atributo y se devolverá, si ya estaba instanciado entonces simplemente devolverá la instancia.

```java
public static ConsoleInput getInstance() {
	if (instance == null) {
		instance = new ConsoleInput();
	}
	return instance;
}
```

Este método limpia el buffer del teclado mediante un nextLine al recurso del escáner.

```java
private void cleanInput() {
	keyboard.nextLine();
}
```

Este método cierra el recurso del escáner.

```java
public void close() {
	keyboard.close();
}
```

- Métodos de entrada de datos:

    - Lectura de números enteros:

        - public int readInt: Este método lee un número de tipo entero sin restricciones.

            ```java
            public int readInt(String info) {
                writeLine(info);
                int value=0;
                boolean error;
                do {
                    try {
                        value = keyboard.nextInt();
                        error = false;
                    } catch (InputMismatchException e) {
                        writeError(String.format("El valor del integer debe ser de tipo númerico entero y comprendido entre el rango %d - %d.",Integer.MIN_VALUE, Integer.MAX_VALUE));
                        error = true;
                    } finally {
                        cleanInput();
                    }
		        } while (error);
		        return value;
	        }
            ```

        - public int readIntLessThan: Este método lee un número entero menor que el parámetro introducido.

            ```java
            public int readIntLessThan(int upperBound, String info){
                int value = 0;
                do {
                    value = readInt(info);
                    if (value >= upperBound) {
                        writeError(String.format("El valor del integer debe ser menor que %d.", upperBound));
                    }
                } while (value >= upperBound);
                return value;
            }
            ```

        - public int readIntLessOrEqualThan: Este método lee un número entero menor o igual que el parámetro introducido.

            ```java
            public int readIntLessOrEqualThan(int upperBound, String info) {
                int value = 0;
                do {
                    value = readInt(info);
                    if (value > upperBound) {
                        writeError(String.format("El valor del integer debe ser menor o igual que %d.", upperBound));
                    }
                } while (value > upperBound);
                return value;
            }
            ```

        - public int readIntGreaterThan: Este método lee un número entero mayor que el parámetro introducido.
        
            ```java
            public int readIntGreaterThan(int lowerBound, String info) {
                int value = 0;
                do {
                    value = readInt(info);
                    if (value <= lowerBound) {
                        writeError(String.format("El valor del integer debe ser mayor que %d.",lowerBound));
                    }
                } while (value <= lowerBound);
                return value;
            }
            ```

        - public int readIntGreaterOrEqualThan: Este método lee un número entero mayor o igual que el parámetro introducido.
        
            ```java
            public int readIntGreaterOrEqualThan(int lowerBound, String info) {
                int value = 0;
                do {
                    value = readInt(info);
                    if (value < lowerBound) {
                        writeError(String.format("El valor del integer debe ser mayor o igual que %d.", lowerBound));
                    }
                } while (value < lowerBound);
                return value;
            }
            ```

        - public int readIntInRange: Este método lee un número entero comprendido entre los dos parámetros introducidos (ambos incluidos).
        
            ```java
            public int readIntInRange(int lowerBound, int upperBound, String info) {
                int value = 0;
                do {
                    value = readInt(info);
                    if (value < lowerBound || value > upperBound) {
                        writeError(String.format("El valor del integer debe estar comprendido entre %d y %d (ambos incluidos).", lowerBound, upperBound));
                    }
                } while (value < lowerBound || value > upperBound);
                return value;
            }
            ```

    - Lectura de números de tipo double:

        - public double readDouble: Este método lee un número de tipo double sin restricciones.
        
            ```java
            public double readDouble(String info) {
                writeLine(info);
                double value=0;
                boolean error;
                do {
                    try {
                        value = keyboard.nextDouble();
                        error = false;
                    } catch (InputMismatchException e) {
                        writeError(String.format("El valor del double debe ser de tipo númerico decimal y comprendido entre el rango %f - %f.", Double.MIN_VALUE, Double.MAX_VALUE));
                        error = true;
                    } finally {
                        cleanInput();
                    }
                } while (error);
                return value;
            }
            ```

        - public double readDoubleLessThan: Este método lee un número double menor que el parámetro introducido.
        
            ```java
            public double readDoubleLessThan(double upperBound, String info) {
                double value = 0;
                do {
                    value = readDouble(info);
                    if (value >= upperBound) {
                        writeError(String.format("El valor del double debe ser menor que %f.", upperBound));
                    }
                } while (value >= upperBound);
                return value;
            }
            ```

        - public double readDoubleLessOrEqualThan: Este método lee un número double menor o igual que el parámetro introducido.
        
            ```java
            public double readDoubleLessOrEqualThan(double upperBound, String info) {
                double value = 0;
                do {
                    value = readDouble(info);
                    if (value > upperBound) {
                        writeError(String.format("El valor del double debe ser menor o igual que %f.", upperBound));
                    }
                } while (value > upperBound);
                return value;
            }
            ```

        - public double readDoubleGreaterThan: Este método lee un número double mayor que el parámetro introducido.
        
            ```java
            public double readDoubleGreaterThan(double lowerBound, String info) {
                double value = 0;
                do {
                    value = readDouble(info);
                    if (value <= lowerBound) {
                        writeError(String.format("El valor del double debe ser mayor que %f.",lowerBound));
                    }
                } while (value <= lowerBound);
                return value;
            }
            ```

        - public double readDoubleGreaterOrEqualThan: Este método lee un número double mayor o igual que el parámetro introducido.
        
            ```java
            public double readDoubleGreaterOrEqualThan(double lowerBound, String info) {
                double value = 0;
                do {
                    value = readDouble(info);
                    if (value < lowerBound) {
                        writeError(String.format("El valor del double debe ser mayor o igual que %f.", lowerBound));
                    }
                } while (value < lowerBound);
                return value;
            }
            ```

        - public double readDoubleInRange: Este método lee un número double comprendido entre los dos parámetros introducidos (ambos incluidos).
        
            ```java
            public double readDoubleInRange(double lowerBound, double upperBound, String info) {
                double value= 0;
                do {
                    value = readDouble(info);
                    if (value < lowerBound || value > upperBound) {
                        writeError(String.format("El valor del double debe estar comprendido entre %f y %f (ambos incluidos).", lowerBound, upperBound));
                    }
                } while (value < lowerBound || value > upperBound);
                return value;
            }
            ```

    - Lectura de números de tipo byte:

        - public byte readByte: Este método lee un número de tipo byte sin restricciones.
        
            ```java
            public byte readByte(String info) {
                writeLine(info);
                byte value=0;
                boolean error;
                do {
                    try {
                        value = keyboard.nextByte();
                        error = false;
                    } catch (InputMismatchException e) {
                        writeError(String.format("El valor del byte debe ser de tipo númerico entero y comprendido entre el rango %d - %d.", Byte.MIN_VALUE, Byte.MAX_VALUE));
                        error = true;
                    } finally {
                        cleanInput();
                    }
                } while (error);
                return value;
            }
            ```

    - Lectura de caracteres o cadenas:

        - public char readChar: Este método lee un carácter.
        
            ```java
            public char readChar(String info) {
                String value = "";
                do {
                    value = readString(info).toLowerCase();
                    if (value.trim().length() != 1) {
                        writeError("El valor del char debe ser de un único caracter.");
                    }
                } while (value.trim().length() != 1);
                return value.trim().charAt(0);
            }
            ```

        - public String readString: Este método lee una cadena no vacía.
        
            ```java
            public String readString(String info) {
                writeLine(info);
                String value = "";
                do {
                    value = keyboard.nextLine();
                    if (value.trim().isEmpty()) {
                        writeError("No puedes introducir una cadena vacía, debe contener al menos un caracter.");
                    }
                } while (value.trim().isEmpty());
                return value;
            }
            ```

        - public String readString: Este método es una versión sobrecargada del anterior que lee una cadena con una longitud máxima igual al parámetro pasado.
        
            ```java
            public String readString(int maxLength, String info) {
                String value = "";
                do {
                    value = readString(info);
                    if (value.length() > maxLength) {
                        writeError(String.format("El valor del String no puede superar los %d caracteres.", maxLength));
                    }
                } while (value.length() > maxLength);
                return value;
            }
            ```
        
        - public boolean readBooleanUsingChar: Este método lee un carácter y lo transforma a un boolean.
        
            ```java
            public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue, String error, String info) {
                char value = '¬';
                do {
                    value = readChar(info);
                    if (value != Character.toLowerCase(affirmativeValue) && value != Character.toLowerCase(negativeValue)) {
                        writeError(String.format("%s", error));
                    }
                } while (value != Character.toLowerCase(affirmativeValue) && value != Character.toLowerCase(negativeValue));
                if (value == Character.toLowerCase(affirmativeValue)) {
                    return true;
                } else {
                    return false;
                }
            }
            ```

        - public String readEmptyString: Este método lee una cadena que puede ser vacía.
        
            ```java
            public String readEmptyString(String info) {
                writeLine(info);
                String value = "";
                value = keyboard.nextLine();
                return value;
            }
            ```

- Métodos de salida de datos:

    - public void writeLine: Este método imprime el mensaje introducido por parámetro y un salto de línea.
    
        ```java
        public void writeLine(String info) {
            System.out.println(info);
        }
        ```

    - public void writeError: Este método imprime un mensaje introducido por parámetro con formato de error y un salto de línea.
    
        ```java
        public void writeError(String info) {
            writeLine(String.format("%s%s%s","\u001B[31m",info,"\u001B[0m"));
        }
        ```

## Relaciones con otras clases

Esta clase es usada por la clase Menu para realizar todas las comunicaciones con el usuario, mediante mensajes y menús por defecto.