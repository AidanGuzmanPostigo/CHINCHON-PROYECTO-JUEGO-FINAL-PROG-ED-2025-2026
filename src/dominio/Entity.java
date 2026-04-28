package dominio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
public class Entity implements IEntity{
	private List<Card> hand;
	private List<Card> temporal;
	private String nickname;
	private int puntuation;
	public Entity(String nickname) {
		hand = new ArrayList<>();
		temporal = new ArrayList<>();
		this.nickname = nickname;
		puntuation = 0;
	}
	@Override
	public int getPuntuation() {
		return puntuation;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nickname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return Objects.equals(nickname, other.nickname);
	}
	@Override
	public String getNickname() {
		return nickname;
	}
	@Override
	public List<Card> getHand(){
		return hand;
	}
	@Override
	public boolean combinate(String combination) {
		List<Card> auxList;
		if (isCombinationValid(combination)) {
			auxList = parseCombination(combination);
			temporal.addAll(auxList);
			hand.removeAll(auxList);
			return true;
		}
		restartCombinations();
		return false;
	}
	private List<Card>parseCombination(String combination) {
		String [] aux = combination.split("-");
		List<Integer> auxIndex = new ArrayList<>();
		List<Card> cardAux = new ArrayList<>();
		for (String s: aux) {
			auxIndex.add(Integer.parseInt(s));
		}
		for (Integer i: auxIndex) {
			cardAux.add(hand.get(i-1));
		}
		return cardAux;
	}
	private boolean isCombinationValid(String combination) {
		if (simpleValidateCombination(combination)) {
			if (isCombinationClean(combination)) {
				if (isSameNumber(combination) || isStraight(combination)) {
					if (parseCombination(combination).size()==8) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	private boolean isCombinationClean(String combination) {
		String [] aux = combination.split("-");
		Set<Integer> auxList = new HashSet<>();
		for (String s: aux) {
			if (!auxList.add(Integer.parseInt(s))) {
				return false;
			}
		}
		for (Integer i :auxList) {
			if (i <= 0 || i>hand.size()) {
				return false;
			}
		}
		return true;
	}
	private boolean isSameNumber(String combination) {
		List<Card> aux = parseCombination(combination);
		int value = aux.get(0).number().getValue();
		for (int i = 1; i<aux.size();i++) {
			if (aux.get(i).number().getValue() != value) {
				return false;
			}
		}
		return true;
	}
	private boolean isStraight(String combination) {
		List<Card> aux = parseCombination(combination);
		Collections.sort(aux);
		Card value = aux.get(0);
		Suit suitOfStraight = aux.get(0).suit();
		if (!allAreSameSuit(aux, suitOfStraight)) {
			return false;
		}
		for (int i = 1; i<aux.size();i++) {
			if (!((value.number().getValue() - aux.get(i).number().getValue() == -1) || (value.number().name().equals("SIETE") && aux.get(i).number().name().equals("SOTA")))) {
				return false;
			}
			value = aux.get(i);
		}
		return true;
	}
	private boolean allAreSameSuit(List<Card>cards, Suit st) {
		for (Card c: cards) {
			if (c.suit() != st) {
				return false;
			}
		}
		return true;
	}
	private boolean simpleValidateCombination(String combination) {
		if (combination.matches("^([1-8]-){2,}[1-8]")) {
			return true;
		}
		return false;
	}
	@Override
	public void draw(Card c) {
		hand.add(c);
		orderHand();
	}
	@Override 
	public Card discard(int i) {
		Card aux = hand.get(i);
		hand.remove(i);
		orderHand();
		return aux;
	}
	@Override
	public void cleanHand() {
		hand.clear();
		temporal.clear();
	}
	private int calculatePuntuation() {
		int puntuation = 0;
		if (hand.size()==0) {
			return -10;
		}
		for (Card c: hand) {
			puntuation+=c.number().getValue();
		}
		return puntuation;
	}
	@Override 
	public void restartCombinations(){
		hand.addAll(temporal);
		temporal.clear();
		orderHand();
	}
	@Override
	public int updatePuntuation() {
		puntuation += calculatePuntuation();
		return puntuation;
	}
	private void orderHand() {
		Collections.sort(hand);
	}
	@Override
	public String showHand() {
		StringBuilder sb = new StringBuilder("");
		sb.append(nickname + " - ");
		for (int i = 0; i< hand.size();i++) {
			if (i != 0 && i!= hand.size()-1) {
				sb.append(", ");
			} else if (i == hand.size()-1) {
				sb.append(" y ");
			}
			sb.append(String.format("%s [%d]",hand.get(i).toString(),i+1));
		}
		sb.append(".\n");
		return sb.toString();
	}
	@Override
	public String toString() {
		return String.format("%s - %d", nickname,puntuation);
	}
}