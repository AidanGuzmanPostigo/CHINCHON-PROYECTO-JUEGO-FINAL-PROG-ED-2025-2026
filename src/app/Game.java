package app;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dominio.Card;
import dominio.Deck;
import dominio.FactoryEntity;
import dominio.ICpu;
import dominio.IDeck;
import dominio.IEntity;
/**
 * Clase encargada del flujo de la partida.
 */
public class Game implements IGame {
	private IDeck d;
	private List<IEntity> players;
	private int maxPoints;
	private FactoryEntity f;
	private Menu m;
	private IEntity winnerChinchon;
	/**
	 * Constructor de la clase, inicializa el menú, la factoria de entidades, la lista de jugadores y el mazo.
	 */
	public Game() {
		m = Menu.getInstance();
		f = new FactoryEntity();
		players = new ArrayList<>();
		d = new Deck();
	}
	/**
	 * @inheritDoc
	 */
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
	/**
	 * Prepara las entidades de la partida, solicitando el número de entidades y los motes sin repetir de los mismos.
	 * @return Lista de entidades de la partida.
	 */
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
	/**
	 * Controla el flujo principal de la partida desde el inicio de la ronda 1 hasta el final de la partida.
	 */
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
	/**
	 * Controla el flujo de una ronda.
	 */
	private void startRound() {
		int turn = 0;
		boolean isClosed = false, validTurn = false;
		IEntity winner = null;
		String combination = "";
		cleanHands();
		d.start(players.size());
		initialDraw();
		do {
			turn++;
			for (IEntity e: players) {
				if (!isClosed) {
					validTurn  = false;
					if (e instanceof ICpu cpu) {
						switch(cpu.chooseWhereDraw(d.getDiscardPile().get(d.getDiscardPile().size()-1))) {
						case 1 -> cpu.draw(d.drawFromPrincipalDeck());
						case 2 -> cpu.draw(d.drawFromDiscardPile());
						}
						if (turn!=1 && cpu.canClose(maxPoints)) {
							isClosed = true;
							if (cpu.isChinchon()) {
								winnerChinchon = cpu;
							} else {
								winner = cpu;
							}
						} else {
							d.addCardToDiscardPile(cpu.discard(cpu.getHand().size()-1));
						}
						
					} else {
						switch (m.optionsMenu(d.getDiscardPile().get(d.getDiscardPile().size()-1).toString(), e.showHand())) {
						case 1 -> e.draw(d.drawFromPrincipalDeck());
						case 2 -> e.draw(d.drawFromDiscardPile());
						};
						do {
							switch (m.closeMenu(e.showHand())) {
							case 1 -> {
								d.addCardToDiscardPile(e.discard((m.discardCardMenu(e.getHand().size(), e.showHand()))));
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
				m.cleanConsoleForNewTurn();
			}
		} while (!isClosed);
		if (winnerChinchon == null) {
			endRound (winner);
		}
	}
	/**
	 * Valida el primer movimiento de un jugador en la ronda.
	 * @param combination Cadena de texto con formato X-X-X.
	 * @param player Entidad a la que se va a validar el movimiento 1.
	 * @return True si el movimiento es válido o false si no.
	 */
	private boolean validateMove1(String combination, IEntity player) {
		if (player.combinate(combination)) {
			switch (player.getHand().size()) {
			case 0,3 -> {
				m.specificCaseCombination();
				player.restartCombinations();
			}
			case 1,2 -> {
				if (player.isChinchon()) {
					winnerChinchon = player;
				}
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
	/**
	 * Valida el segundo movimiento de un jugador en la ronda.
	 * @param combination Cadena de texto con formato X-X-X.
	 * @param player Entidad a la que se va a validar el movimiento 2.
	 * @return True si el movimiento es válido o false si no.
	 */
	private boolean validateMove2(String combination, IEntity player) {
		if (player.combinate(combination)) {
			switch (player.getHand().size()) {
			case 1,2 -> {
				return closePlayerRound(player);
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
	/**
	 * Limpia la lista de entidades y deja solo a la entidad que ha relizado el chinchon.
	 * @param e Entidad que ha realizado un chinchon.
	 */
	private void endGameWithChinchon(IEntity e) {
		players.clear();
		players.add(e);
	}
	/**
	 * Reparte la mano incial a todas las entidades y coloca una carta boca arriba en la pila de descartes.
	 */
	private void initialDraw() {
		for (int i = 1; i<=7;i++) {
			players.stream()
			.forEach(e -> e.draw(d.drawFromPrincipalDeck()));
		}
		d.addCardToDiscardPile(d.drawFromPrincipalDeck());
	}
	/**
	 * Comprueba que el cierre de la ronda es válido.
	 * @param player Jugador al que se le valida la jugada.
	 * @return True si la jugada es correcta o false si no.
	 */
	private boolean closePlayerRound(IEntity player) {
		Card aux = player.discard(m.closeCardMenu(player.getHand().size(), player.showHand()));
		if (player.getHand().size()==1) {
			if (player.getHand().get(0).number().getValue()>5) {
				m.closeErrorPuntuation();
				discardFailed(player,aux);
				return false;
			} else if ((player.getHand().get(0).number().getValue() + player.getPuntuation())>=maxPoints){
				m.maxPointsClose();
				discardFailed(player,aux);
				return false;
			}
			return true;
		} 
		else {
			return true;
		}
	}
	/**
	 * Método que devuelve la carta descartada al jugador.
	 * @param player Jugador al que se le devuelve la carta.
	 * @param aux Carta devuelta al jugador.
	 */
	private void discardFailed(IEntity player, Card aux) {
		player.draw(aux);
		player.restartCombinations();
	}
	/**
	 * Limpia las manos de todas las entidades.
	 */
	private void cleanHands() {
		players.stream().forEach(e -> e.cleanHand());
	}
	/**
	 * Termina la ronda si no ha ocurrido un chinchón, solicita a las entidades que o combinen o terminen su turno.
	 * @param winner Entidad a la que no se le va a preguntar nada ya que es el ganador de la ronda.
	 */
	private void endRound(IEntity winner) {
		List<IEntity> auxPlayers = new ArrayList<>(players);
		auxPlayers.remove(winner);
		boolean valid = false;
		if (winner.getHand().size() == 0) {
			winner.applyMinus10();
		}
		for (IEntity e: auxPlayers) {
			if (e instanceof ICpu cpu) {
				cpu.choosePlayClose();
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
	/**
	 * Actualiza y muestra las puntuaciones de las entidades y, en caso de que una entidad haya llegado al límite de puntos la elimina.
	 */
	private void updatePlayers() {
		players = players.stream()
		.peek(e -> e.updatePuntuation())
		.peek(System.out::println)
		.filter(e -> e.getPuntuation()<maxPoints)
		.collect(Collectors.toList());
	}
	/**
	 * Comprueba que un mote no ha sido asignado a una entidad anteriormente creada.
	 * @param nickname Mote a comprobar.
	 * @param aux Lista con las entidades de la partida.
	 * @return True si el mote no ha sido usado con anterioridad o false si lo ha sido.
	 */
	private boolean isNicknameAvailable(String nickname, List<IEntity> aux) {
		return aux.stream()
				.noneMatch(e -> e.getNickname().equalsIgnoreCase(nickname));
	}
}