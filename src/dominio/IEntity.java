package dominio;
import java.util.List;
public interface IEntity {
	void cleanHand();
	String showHand();
	void draw(Card c);
	int updatePuntuation();
	String getNickname();
	int getPuntuation();
	List<Card> getHand();
	Card discard(int i);
	boolean combinate(String combination);
	void restartCombinations();
}