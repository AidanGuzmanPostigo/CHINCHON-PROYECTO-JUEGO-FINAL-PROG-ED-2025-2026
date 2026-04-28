# PROYECTO FINAL DE CURSO 2025/2026 - CHINCHÓN

## Explicación conceptual del proyecto

El proyecto consiste en la realización de un programa en el que se pueda jugar al juego de cartas "Chinchón" (también conocido como "Mono"), que consiste en lo siguiente:

- Se define una puntuación que definirá el momento en el que un jugador que la alcance quedará eliminado.

- Un grupo de jugadores tiene una mano de 7 cartas, mediante la cual deberán realizar combinaciones para acabar teniendo la menor cantidad de puntos (definididos por los valores numéricos de las cartas) sumando todas las  cartas que no hayan podido combinar.

- Para formar combinaciones cada turno consistirá en 2 pasos, el robo y el descarte o cierre, el primero de estos consiste en robar la primera carta de la baraja o coger la carta boca arriba de la pila de descartes y luego descartar una carta o cerrar (usando una carta, siempre los jugadores se deben quedar con 7 cartas al finalizar el turno).

- Para cerrar tienes que tener al menos 6 de las 7 cartas combinadas y no ser el primer turno, al cerrar todos los jugadores usan las combinaciones que tengan y el resto de cartas se suman a los puntos de cada jugador.

- Hay 3 tipos de combinaciones (hay que tener en cuenta que se juega con una baraja española): Iguales (3 o más cartas del mismo valor numérico), Escalera (3 o más cartas consecutivas del mismo palo) y Chinchon (una escalera de 7 cartas).

- La partida termina cuando solo queda un jugador en juego.

## Reglas concretas del proyecto

El juego tiene muchas variantes, para este proyecto se han definido las siguientes características:

- La baraja no contiene ni 8 ni 9. 

- El valor numérico de las cartas de figuras coincide con el de la carta, es decir, el caballo vale 11 y el rey vale 12, no valen todas 10.

- El programa debe funcionar con un número de jugadores o Cpus de entre 2 a 5.

- Si algún jugador cierra con chinchón la partida termina y ese jugador gana.

- Para poder cerrar, si no has combinado las 7 cartas, la que te sobre debe valer 5 o menos y, al cerrar no puedes pasarte de los puntos.