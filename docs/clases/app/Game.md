# Game

## Función

Es la clase que controla el flujo de una partida.

## Atributos

- private IDeck **d**: La baraja que se usa en la partida.

- private List<IEntity> **players**: Lista de jugadores de la partida.
	
- private int **maxPoints**: Límite de puntos de la partida.
	
- private FactoryEntity **f**: Factoria de las entidades.
	
- private Menu **m**: Menú usado para la comunicación con el usuario.
	
- private IEntity **winnerChinchon**: Entidad usada en el caso de que alguien gane con chinchon.

## Constructor

El constructor de esta clase inicializa todos sus atributos salvo el límite de puntos y la entidad especial para cuando alguien gana con chinchón.

```java
public Game() {
	m = Menu.getInstance();
	f = new FactoryEntity();
	players = new ArrayList<>();
	d = new Deck();
}
```

## Métodos

- public void startConfiguration: Inicia la configuración de la partida y solicita al usuario diferentes parámetros como el valor límite de puntos de la partida.

	```java
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
	```

- private List<IEntity> preparePlayers: Prepara las entidades de la partida, solicitando el número de entidades y los motes sin repetir de los mismos.

	```java
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
	```

- private playableLoop: Controla el flujo principal de la partida desde el inicio de la ronda 1 hasta el final de la partida.

	```java
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
	```

- private void startRound: Controla el flujo de una ronda de la siguiente manera:

    - Primera parte de la ronda -> Se roba o del mazo o la carta boca arriba de la pila de descartes.

    - Segunda parte de la ronda -> Se mete en un bucle al usuario hasta que, o descarte o realice combinaciones válidas de manera que pueda cerrar.

    - Finalización de la ronda -> En el caso de que haya un ganador sin chinchón llama a endRound para terminar la ronda y permitir al resto de entidades que realicen combinaciones.

	```java
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
	```

- private validateMove1: Valida el primer movimiento de un jugador en la ronda, comprobando que la combinación es correcta y que en la mano se quede un número de cartas que le permita o cerrar la ronda o realizar un segundo movimiento.

	```java
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
	```

- private validateMove2: Valida el segundo movimiento de un jugador en la ronda, comprobando que la combinación es correcta y que el número de cartas en la mano le permita cerrar la ronda.

	```java
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
	```

- private endGameWithChinchon: Limpia la lista de entidades y deja solo a la entidad que ha relizado el chinchon.

	```java
	private void endGameWithChinchon(IEntity e) {
		players.clear();
		players.add(e);
	}
	```

- private initialDraw: Reparte la mano incial a todas las entidades y coloca una carta boca arriba en la pila de descartes.

	```java
	private void initialDraw() {
		for (int i = 1; i<=7;i++) {
			players.stream()
			.forEach(e -> e.draw(d.drawFromPrincipalDeck()));
		}
		d.addCardToDiscardPile(d.drawFromPrincipalDeck());
	}
	```

- private boolean closePlayerRound: Comprueba que el cierre de la ronda es válido respecto a los requisitos del proyecto (que no se cierre con una carta mayor que 5 y que no llegue al límite al cerrar).

	```java
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
	```

- private void discardFailed: Método que devuelve la carta descartada al jugador si el cierre no es válido.

	```java
	private void discardFailed(IEntity player, Card aux) {
		player.draw(aux);
		player.restartCombinations();
	}
	```

- private cleanHands: Limpia las manos de todas las entidades.

	```java
	private void cleanHands() {
		players.stream().forEach(e -> e.cleanHand());
	}
	```

- private endRound: Termina la ronda si no ha ocurrido un chinchón, solicita a las entidades que o combinen o terminen su turno.

	```java
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
	```

- private updatePlayers: Actualiza y muestra las puntuaciones de las entidades y, en caso de que una entidad haya llegado al límite de puntos la elimina.

	```java
	private void updatePlayers() {
		players = players.stream()
		.peek(e -> e.updatePuntuation())
		.peek(System.out::println)
		.filter(e -> e.getPuntuation()<maxPoints)
		.collect(Collectors.toList());
	}
	```

- private isNicknameAvailable: Comprueba que un mote no ha sido asignado a una entidad anteriormente creada.

	```java
	private boolean isNicknameAvailable(String nickname, List<IEntity> aux) {
		return aux.stream()
				.noneMatch(e -> e.getNickname().equalsIgnoreCase(nickname));
	}
	```

## Relaciones con otras clases

Esta clase es usada por la clase Main y usa a la clase Menu, la clase Deck, FactoryEntity y Entity y subclases de la misma.

[Volver al índice de clases](../../indiceProyecto.md)

[Volver al README principal](../../README.md)