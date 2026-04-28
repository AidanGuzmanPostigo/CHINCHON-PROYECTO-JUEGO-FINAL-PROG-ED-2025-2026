package app;
import java.util.Scanner;
import java.util.InputMismatchException;
public class ConsoleInput {
	private Scanner keyboard;
	private static ConsoleInput instance;
	private ConsoleInput() {
		keyboard = new Scanner(System.in);
	}
	public static ConsoleInput getInstance() {
		if (instance == null) {
			instance = new ConsoleInput();
		}
		return instance;
	}
	private void cleanInput() {
		keyboard.nextLine();
	}
	public void close() {
		keyboard.close();
	}
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
	public String readEmptyString(String info) {
		writeLine(info);
		String value = "";
		value = keyboard.nextLine();
		return value;
	}
	public void writeLine(String info) {
		System.out.println(info);
	}
	public void writeError(String info) {
		System.out.println(String.format("%s%s%s","\u001B[31m",info,"\u001B[0m"));
	}
}