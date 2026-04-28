package app;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dominio.Deck;
import dominio.FactoryEntity;
import dominio.ICpu;
import dominio.IDeck;
import dominio.IEntity;
public class Game implements IGame {
	private IDeck d;
	private List<IEntity> players;
	private int maxPoints;
	private FactoryEntity f;
	private Menu m;
	private IEntity winnerChinchon;
	public Game() {
		m = Menu.getInstance();
		f = new FactoryEntity();
		players = new ArrayList<>();
		d = new Deck();
	}
	@Override
	public void startConfiguration() {
		int option = 0;
		do {
			option = m.startMenu();
			if (option == 1) {
				maxPoints = m.puntuationMenu();
				players = preparePlayers();
				winnerChinchon = null;
				playableLoop();
			}
		} while (option != 2);
	}
	private List<IEntity> preparePlayers() {
		List<IEntity> aux =new ArrayList<>();
		String nickname = "";
		int auxNumberOfPlayers = m.numberOfPlayersMenu();
		for (int i = 0; i< auxNumberOfPlayers;i++) {
			do {
				nickname = m.nicknameMenu();
			} while (!(isNicknameAvailable(nickname,aux)));
			aux.add(f.buildEntity(m.typeOfEntityMenu(), nickname));
		}
		return aux;
	}
	private void playableLoop() {
		do {
			startRound();
			if (winnerChinchon != null) {
				endGameWithChinchon(winnerChinchon);
			} else {
				updatePlayers();
			}
		} while (players.size() > 1);
		m.winnerMenu(players.get(0).getNickname());
	}
	private void startRound() {
		int turn = 0;
		boolean isClosed = false, validTurn = false;
		IEntity winner = null;
		String combination = "";
		cleanHands();
		d.start(players.size());
		initialDraw();
		d.addCardToDiscardPile(d.drawFromPrincipalDeck());
		do {
			turn++;
			for (IEntity e: players) {
				if (!isClosed) {
					validTurn  = false;
					if (e instanceof ICpu cpu) {
						cpu.choosePlay(turn, maxPoints);
					} else {
						switch (m.optionsMenu(d.getDiscardPile().get(d.getDiscardPile().size()-1).toString(), e.showHand())) {
						case 1 -> e.draw(d.drawFromPrincipalDeck());
						case 2 -> e.draw(d.drawFromDiscardPile());
						};
						do {
							switch (m.closeMenu(e.showHand())) {
							case 1 -> {
								d.addCardToDiscardPile(e.discard((m.discardCardMenu(1, e.getHand().size(), e.showHand()))));
								validTurn = true;
							}
							case 2 -> {
								if (turn == 1) {
									m.closeErrorTurn1();
								} else {
									combination = m.combinationsMenu(e.showHand());
									validTurn = validateMove1(combination,e); 
									if (validTurn) {
										isClosed = true;
									}
									if (validTurn && winnerChinchon == null) {
										winner = e;
									}
								}
							}
							}; 
						}while (!validTurn);
					}
				}
			}
		} while (!isClosed);
		if (winnerChinchon == null) {
			endRound (winner);
		}
	}
	private boolean validateMove1(String combination, IEntity player) {
		if (player.combinate(combination)) {
			switch (player.getHand().size()) {
			case 0,3 -> {
				m.specificCaseCombination();
				player.restartCombinations();
			}
			case 1 -> {
				winnerChinchon = player;
				return true;
			}
			case 2 -> {
				return true;
			}
			case 4, 5 -> {
				if (validateMove2(m.combinationsMenu(player.showHand()), player)) {
					return true;
				}
			}
			};
		} else {
			m.combinationError();
		}
		return false;
	}
	private boolean validateMove2(String combination, IEntity player) {
		if (player.combinate(combination)) {
			switch (player.getHand().size()) {
			case 1,2 -> {
				player.discard(m.closeCardMenu(1, player.getHand().size(), player.showHand()));
				if (player.getHand().size()==1 && player.getHand().get(0).number().getValue()>5) {
					if ((player.getHand().get(0).number().getValue() + player.getPuntuation())>=maxPoints) {
						m.maxPointsClose();
					} else {
						m.closeErrorPuntuation();
					}
					player.restartCombinations();
				} else {
					return true;
				}
			}
			case 0 -> {
				m.specificCaseCombination();
				player.restartCombinations();
			}
			};
		} else {
			m.combinationError();
		}
		return false;
	}
	private void endGameWithChinchon(IEntity e) {
		players.clear();
		players.add(e);
	}
	private void initialDraw() {
		for (int i = 1; i<=7;i++) {
			players.stream()
			.forEach(e -> e.draw(d.drawFromPrincipalDeck()));
		}
	}
	private void cleanHands() {
		players.stream().forEach(e -> e.cleanHand());
	}
	private void endRound(IEntity winner) {
		List<IEntity> auxPlayers = new ArrayList<>(players);
		auxPlayers.remove(winner);
		boolean valid = false;
		for (IEntity e: auxPlayers) {
			if (e instanceof ICpu cpu) {
				cpu.choosePlay(maxPoints);
			} else {
				do {
					valid = false;
					e.restartCombinations();
					switch(m.endRoundMenu(e.showHand())) {
					case 1 -> {
						if(e.combinate(m.combinationsMenu(e.showHand()))){
							switch(m.endRoundMenu(e.showHand())) {
							case 1 -> {
								if (e.combinate(m.combinationsMenu(e.showHand()))) {
									valid = true;
								} else {
									m.combinationError();
								}
							}
							case 2 -> valid = true;
							};
						} else {
							m.combinationError();
						}
					}
					case 2 -> valid = true;
					};
				} while (!valid);
			}
		}
	}
	private void updatePlayers() {
		players = players.stream()
		.peek(e -> e.updatePuntuation())
		.filter(e -> e.getPuntuation()<maxPoints)
		.peek(System.out::println)
		.collect(Collectors.toList());
	}
	private boolean isNicknameAvailable(String nickname, List<IEntity> aux) {
		return aux.stream()
				.noneMatch(e -> e.getNickname().equalsIgnoreCase(nickname));
	}
}