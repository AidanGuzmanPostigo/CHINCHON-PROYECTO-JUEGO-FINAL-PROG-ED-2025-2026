package app;
public class Main {
	public void show() {
		IGame g = new Game();
		g.startConfiguration();
	}
	public static void main(String[] args) {
		new Main().show();
	}
}