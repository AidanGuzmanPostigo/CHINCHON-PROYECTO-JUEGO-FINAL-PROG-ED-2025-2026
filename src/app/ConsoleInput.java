package app;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * Clase encargada de la comunicación con el usuario (entrada-salida).
 */
public class ConsoleInput {
	private Scanner keyboard;
	private static ConsoleInput instance;
	/**
	 * Constructor privado de la clase que inicializa un scanner.
	 */
	private ConsoleInput() {
		keyboard = new Scanner(System.in);
	}
	/**
	 * Método estático que crea una instancia de la clase y la devuelve o si ya la hay solo la devuelve.
	 * @return Instancia de la clase.
	 */
	public static ConsoleInput getInstance() {
		if (instance == null) {
			instance = new ConsoleInput();
		}
		return instance;
	}
	/**
	 * Limpia el buffer del teclado.
	 */
	private void cleanInput() {
		keyboard.nextLine();
	}
	/**
	 * Cierra el recurso del escáner.
	 */
	public void close() {
		keyboard.close();
	}
	/**
	 * Lee y devuelve un número entero.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número entero introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número entero menor que upperBound.
	 * @param upperBound Valor máximo exclusivo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número entero introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número entero menor o igual que upperBound.
	 * @param upperBound Valor máximo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número entero introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número entero mayor que lowerBound.
	 * @param lowerBound Valor mínimo exclusivo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número entero introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número entero mayor o igual que lowerBound.
	 * @param lowerBound Valor mínimo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número entero introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número entero comprendido entre el rango loweBound-upperBound ambos incluidos.
	 * @param lowerBound Valor mínimo.
	 * @param upperBound Valor máximo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número entero introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número de tipo double.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número de tipo double introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número de tipo double menor que upperBound.
	 * @param upperBound Valor máximo exclusivo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número de tipo double introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número de tipo double menor o igual que upperBound.
	 * @param upperBound Valor máximo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número de tipo double introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número de tipo double mayor que lowerBound.
	 * @param lowerBound Valor mínimo exclusivo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número de tipo double introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número de tipo double mayor o igual que lowerBound.
	 * @param upperBound Valor mínimo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número de tipo double introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número de tipo double comprendido entre el rango loweBound-upperBound ambos incluidos.
	 * @param lowerBound Valor mínimo.
	 * @param upperBound Valor máximo.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número de tipo double introducido por el usuario.
	 */
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
	/**
	 * Lee y devuelve un número de tipo byte.
	 * @param info Mensaje informativo para el usuario.
	 * @return Número de tipo byte introducido por el usuario.
	 */
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
	/**
	 * Lee un carácter.
	 * @param info Mensaje informativo para el usuario.
	 * @return Carácter introducido por el usuario.
	 */
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
	/**
	 * Lee una cadena de texto no vacía.
	 * @param info Mensaje informativo para el usuario.
	 * @return Cadena de texto no vacía introducida por el usuario.
	 */
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
	/**
	 * Lee una cadena de texto no vacía de máximo maxLength caracteres.
	 * @param maxLength Cantida máxima de caracteres para la cadena.
	 * @param info Mensaje informativo para el usuario.
	 * @return Cadena de texto introducida por el usuario.
	 */
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
	/**
	  * Lee un caracter introducido por el usuario y lo transforma en un boolean.
	  * @param affirmativeValue Caracter que devuelve true.
	  * @param negativeValue Caracter que devuelve false.
	  * @param error Mensaje en caso de que ocurra un error.
	  * @param info Mensaje informativo para el usuario.
	  * @return True si es el primer caracter o false si es el segundo.
	  */
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
	/**
	 * Lee una cadena de texto que puede estar vacía.
	 * @param info Mensaje informativo para el usuario.
	 * @return Cadena de texto introducida por el usuario.
	 */
	public String readEmptyString(String info) {
		writeLine(info);
		String value = "";
		value = keyboard.nextLine();
		return value;
	}
	/**
	  * Imprime un mensaje y un salto de línea en consola.
	  * @param info Mensaje a imprimir.
	  */
	public void writeLine(String info) {
		System.out.println(info);
	}
	/**
	 * Imprime un mensaje de error en rojo y un salto de línea.
	 * @param info Mensaje de error a imprimir.
	 */
	public void writeError(String info) {
		writeLine(String.format("%s%s%s","\u001B[31m",info,"\u001B[0m"));
	}
}