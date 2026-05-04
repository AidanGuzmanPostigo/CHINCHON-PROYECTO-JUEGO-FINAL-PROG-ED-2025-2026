package dominio;

/**
 * Clase encargada de la Cpu, es una IA muy simple.
 */
public class Cpu extends Entity implements ICpu{
	public Cpu(String nickname) {
		super(nickname);
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public int chooseWhereDraw(Card discardPileCard) {
		if (discardPileCard.number().getValue() <= 4) {
			return 2;
		}
		return 1;
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public void choosePlayClose() {
	}
	
}