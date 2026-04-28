package dominio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Deck implements IDeck{
	private List<Card> cardsInDeck;
	private List<Card> discardPile;
	public Deck () {
		cardsInDeck = new ArrayList<>();
		discardPile = new ArrayList<>();
	}
	@Override
	public void start(int numberOfPlayers) {
		CardType ct;
		Suit st;
		int id=0;
		cardsInDeck.clear();
		discardPile.clear();
		for (int i = 0; i < (numberOfPlayers<3?1:2);i++) {
			for (int j = 1; j<=4;j++) {
				st = switch (j) {
				case 1 -> Suit.ORO;
				case 2 -> Suit.ESPADAS;
				case 3 -> Suit.COPAS;
				case 4 -> Suit.BASTOS;
				default -> Suit.ERROR;
				};
				for (int k = 1; k<=10; k++) {
				ct = switch (k) {
				case 1 -> CardType.UNO;
				case 2 -> CardType.DOS;
				case 3 -> CardType.TRES;
				case 4 -> CardType.CUATRO;
				case 5 -> CardType.CINCO;
				case 6 -> CardType.SEIS;
				case 7 -> CardType.SIETE;
				case 8 -> CardType.SOTA;
				case 9 -> CardType.CABALLO;
				case 10 -> CardType.REY;
				default -> CardType.ERROR;
				};
				addCardToPrincipalDeck(new Card(ct,st,++id));
				}
			}
		}
		shuffle();
	}
	@Override
	public Card drawFromPrincipalDeck() {
		Card c;
		if (cardsInDeck.size() == 0) {
			shuffle();
		}
		c = cardsInDeck.get(0);
		cardsInDeck.remove(0);
		return c;
	}
	@Override
	public Card drawFromDiscardPile() {
		Card c = discardPile.get(discardPile.size()-1);
		discardPile.remove(discardPile.size()-1);
		return c;
	}
	@Override
	public void addCardToPrincipalDeck(Card ... c) {
		cardsInDeck.addAll(Arrays.asList(c));
	}
	@Override
	public void addCardToDiscardPile(Card c) {
		discardPile.add(c);
	}
	@Override
	public void shuffle() {
		Card c;
		if (discardPile.size() != 0) {
			c = discardPile.get(discardPile.size()-1);
			discardPile.remove(c);
			cardsInDeck.addAll(discardPile);
			discardPile.clear();
			discardPile.add(c);
		}
		Collections.shuffle(cardsInDeck);
	}
	@Override
	public List<Card> getCardsInDeck() {
		return cardsInDeck;
	}
	@Override
	public List<Card> getDiscardPile() {
		return discardPile;
	}
}