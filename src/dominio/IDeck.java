package dominio;
import java.util.List;
public interface IDeck {
	void shuffle();
	void addCardToDiscardPile(Card c);
	void addCardToPrincipalDeck(Card... c);
	Card drawFromDiscardPile();
	Card drawFromPrincipalDeck();
	void start(int numberOfPlayers);
	List<Card> getCardsInDeck();
	List<Card> getDiscardPile();
}