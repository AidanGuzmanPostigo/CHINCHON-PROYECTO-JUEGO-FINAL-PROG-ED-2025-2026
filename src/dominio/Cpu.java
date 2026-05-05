package dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		if (discardPileCard.number().getValue() <= 4 && discardPileCard.number().getValue() < hand.get(hand.size()-1).number().getValue()) {
			return 2;
		}
		return 1;
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public boolean canClose(int maxpoints) {
		searchEquals(false);
		searchStraight(false);
		if (hand.size()<=2) {
			if ((hand.size() == 2 && hand.get(0).number().getValue() <=5) && (hand.get(0).number().getValue() + getPuntuation()) < maxpoints) {
				hand.remove(1);
				return true;
			} else {
				if (!hand.isEmpty()) {
					hand.remove(0);
				}
				return true;
			}
		}
		restartCombinations();
		return false;
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public void choosePlayClose() {
		searchEquals(true);
		searchStraight(true);
    }
	/**
	 * Busca si la cpu tiene escaleras en mano.
	 * @param cleanTemporal Boolean para saber si hay que limpiar el temporal al hacer una combinación o no.
	 */
	private void searchStraight(boolean cleanTemporal) {
		int[] auxArray = new int[10];
		List<Integer> aux = new ArrayList<>();
		for (Suit st: Suit.values()) {
			aux.clear();
			auxArray = createArrayForSuit(st);
			for (int i = 9; i>=0; i--) {
				if (auxArray[i] != -1) {
					aux.add(auxArray[i]);
				} else {
					if (aux.size() >=3) {
						combinate(parseCombinations(aux));
						if (cleanTemporal) {
							temporal.clear();
						}
					}
					aux.clear();
				}
			}
			if (aux.size()>=3) {
				combinate(parseCombinations(aux));
				if (cleanTemporal) {
					temporal.clear();
				}
			}
		}
	}
	/**
	 * Auxiliar que crea un array con todas las cartas y guarda los índices si coincide con cartas en la mano.
	 * @param st Palo que se quiere buscar.
	 * @return Array auxiliar con los índices de todas las cartas de la mano de un palo concreto.
	 */
	@SuppressWarnings("incomplete-switch")
	private int [] createArrayForSuit(Suit st) {
		int[] auxArray = new int[10];
		Arrays.fill(auxArray, -1);
		for (int i = 0; i< hand.size();i++) {
			if (hand.get(i).suit() == st) {
				switch (hand.get(i).number()) {
				case UNO -> auxArray[0] = i+1;
				case DOS -> auxArray[1] = i+1;
				case TRES -> auxArray[2] = i+1;
				case CUATRO -> auxArray[3] = i+1;
				case CINCO -> auxArray[4] = i+1;
				case SEIS -> auxArray[5] = i+1;
				case SIETE -> auxArray[6] = i+1;
				case SOTA -> auxArray[7] = i+1;
				case CABALLO -> auxArray[8] = i+1;
				case REY -> auxArray[9] = i+1;
				}
			}
		}
		return auxArray;
	}
	/**
	 * Comprueba si hay combinaciones de iguales en la mano.
	 * @param cleanTemporal Boolean para saber si hay que limpiar el temporal al hacer una combinación o no.
	 */
	private void searchEquals(boolean cleanTemporal) {
		List<Integer> aux = new ArrayList<>();
		for (CardType ct: CardType.values()){
			aux.clear();
			for (int i = 0; i< hand.size();i++) {
				if (hand.get(i).number() == ct) {
					aux.add(i+1);
				}
			}
			if (aux.size() >=3) {
				combinate(parseCombinations(aux));
				if (cleanTemporal) {
					temporal.clear();
				}
			}
		}
	}
	/**
	 * Parsea las combinaciones de una lista de carta a formato X-X-X.
	 * @param index Lista de cartas a parsear.
	 * @return String con formato X-X-X que forma una combinación.
	 */
	private String parseCombinations(List<Integer> index) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i<index.size();i++) {
			if (i == index.size()-1) {
				sb.append(index.get(i));
			} else {
				sb.append(String.format("%d-", index.get(i)));
			}
		}
		return sb.toString();
	}
}