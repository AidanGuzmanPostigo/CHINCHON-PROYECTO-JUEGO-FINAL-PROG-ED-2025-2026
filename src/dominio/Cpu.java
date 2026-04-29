package dominio;
public class Cpu extends Entity implements ICpu{
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