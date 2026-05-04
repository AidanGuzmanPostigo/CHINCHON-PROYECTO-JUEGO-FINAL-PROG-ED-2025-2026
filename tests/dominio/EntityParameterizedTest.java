package dominio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EntityParameterizedTest {

	@ParameterizedTest
    @CsvSource({
        "1-2, False",
        "1-2-3, True",
        "1-2-3-4-5-6-7-8, True",
        "1-2-3-4-5-6-7-8-9, False",
        "9-9-9, False",
        "8-8-8, True",
        "7-8-9, False",
        "1-2-10, False"
    })
	void simpleValidateCombinationTest(String regexForValidation, boolean expected) {
		Entity e = new Entity("Test");
		boolean result = e.simpleValidateCombination(regexForValidation);
		assertEquals(result,expected);
	}
	
	@ParameterizedTest
    @CsvSource({
        "1-2, True",
        "1-2-3, True",
        "1-2-3-4-5-6-7-8, True",
        "1-2-3-4-5-6-7-8-9, False",
        "9-9-9, False",
        "8-8-8, False",
        "7-8-9, False",
        "1-2-10, False"
    })
	void isCombinationCleanTest(String regexForValidation, boolean expected) {
		Entity e = new Entity("Test");
		IDeck d = new Deck();
		d.start(1);
		for (int i = 1; i<=8;i++){
			e.draw(d.drawFromPrincipalDeck());
		}
		boolean result = e.isCombinationClean(regexForValidation);
		assertEquals(result,expected);
	}
	
	@ParameterizedTest
    @CsvSource({
        "1-2, True",
        "1-2-3, True",
        "1-2-3-4-5-6-7-8, False",
        "1-2-3-4-5-6-7-8-9, False",
        "9-9-9, False",
        "8-8-8, False",
        "7-8-9, False",
        "1-2-10, False"
    })
	void isCombinationCleanTestNotFirstMove(String regexForValidation, boolean expected) {
		Entity e = new Entity("Test");
		IDeck d = new Deck();
		d.start(1);
		for (int i = 1; i<=5;i++){
			e.draw(d.drawFromPrincipalDeck());
		}
		boolean result = e.isCombinationClean(regexForValidation);
		assertEquals(result,expected);
	}
	
	@ParameterizedTest
    @CsvSource({
        "1-2, False",
        "1-2-3, True",
        "1-2-3-4-5-6-7-8, True",
        "1-2-3-4-5-6-7-8-9, False",
        "9-9-9, False",
        "8-8-8, False",
        "7-8-9, False",
        "1-2-10, False"
    })
	void isStraightTest(String regexForValidation, boolean expected) {
		Entity e = new Entity("Test");
		boolean result;
		Suit st = Suit.ORO;
		CardType ct; 
		for (int i = 1; i<=8;i++){
			ct = switch(i) {
			case 1 -> CardType.UNO;
			case 2 -> CardType.DOS;
			case 3 -> CardType.TRES;
			case 4 -> CardType.CUATRO;
			case 5 -> CardType.CINCO;
			case 6 -> CardType.SEIS;
			case 7 -> CardType.SIETE;
			case 8 -> CardType.SOTA;
			default -> CardType.ERROR;
			};
			e.draw(new Card(ct,st,i));
		}
		if (e.simpleValidateCombination(regexForValidation) && e.isCombinationClean(regexForValidation)) {
			result = e.isStraight(regexForValidation);
		} else {
			result = false;
		}
		assertEquals(result,expected);
	}
	
	
	@ParameterizedTest
    @CsvSource({
        "1-2, False",
        "1-2-3, True",
        "1-2-3-4-5-6-7-8, True",
        "1-2-3-4-5-6-7-8-9, False",
        "9-9-9, False",
        "8-8-8, False",
        "7-8-9, False",
        "1-2-10, False"
    })
	void isSameNumberTest(String regexForValidation, boolean expected) {
		Entity e = new Entity("Test");
		boolean result;
		for (int i = 1; i<=8;i++){
			e.draw(new Card(CardType.SIETE,Suit.ESPADAS,i));
		}
		if (e.simpleValidateCombination(regexForValidation) && e.isCombinationClean(regexForValidation)) {
			result = e.isSameNumber(regexForValidation);
		} else {
			result = false;
		}
		assertEquals(result,expected);
	}

	
	@ParameterizedTest
	@CsvSource({
		"1-2, False",
        "1-2-3-4, True",
        "5-6-7, True",
        "5-6-7-8, False",
        "1-2-3-4-5-6-7-8, False",
        "1-2-3-4-5-6-7-8-9, False",
        "9-9-9, False",
        "8-8-8, False",
        "7-8-9, False",
        "1-2-10, False"
	})
	void isCombinationValidTest(String regexForValidation, boolean expected) {
		Entity e = new Entity("Test");
		boolean result;
		for (int i = 1; i<=3;i++) {
			e.draw(new Card(CardType.SIETE, Suit.ESPADAS,i));
		}
		e.draw(new Card(CardType.UNO, Suit.ORO, 1));
		e.draw(new Card(CardType.DOS, Suit.ORO, 2));
		e.draw(new Card(CardType.TRES, Suit.ORO, 3));
		e.draw(new Card(CardType.CUATRO, Suit.ORO, 4));
		e.draw(new Card(CardType.SOTA, Suit.ORO, 1));
		result = e.isCombinationValid(regexForValidation);
		assertEquals(result,expected);
	}
	
	@Test
	void isCombinationValidSpecialTest() {
		Entity e = new Entity("Test");
		boolean result;
		CardType ct;
		for (int i = 1; i<=8;i++) {
			ct = switch (i) {
			case 1 -> CardType.UNO;
			case 2 -> CardType.DOS;
			case 3 -> CardType.TRES;
			case 4 -> CardType.CUATRO;
			case 5 -> CardType.CINCO;
			case 6 -> CardType.SEIS;
			case 7 -> CardType.SIETE;
			case 8 -> CardType.SOTA;
			default -> CardType.ERROR;
			};
			e.draw(new Card(ct, Suit.ESPADAS,i));
		}
		result = e.isCombinationValid("1-2-3-4-5-6-7-8");
		assertEquals(result,false);
	}
}
