package dominio;
public class Cpu extends Entity implements ICpu{
	private static int count = 0;
	public Cpu () {
		super(String.format("CPU-%d",++count));
	}
	public Cpu(String nickname) {
		super(nickname);
	}
	@Override
	public int choosePlay(int round, int maxPoints) {
		/**
		 * @TODO: Hacer la lógica de la CPU
		 */
		return 0;
	}
	@Override
	public int choosePlay(int maxPoints) {
		return 0;
	}
}