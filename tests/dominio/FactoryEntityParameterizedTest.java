package dominio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FactoryEntityParameterizedTest {

	@ParameterizedTest
    @CsvSource({
        "1, J1, Entity",
        "1, J2, Entity",
        "1, J3, Entity",
        "1, J4, Entity",
        "2, CPU1, Cpu",
        "2, CPU2, Cpu",
        "2, CPU3, Cpu",
        "2, CPU, Cpu"
    })
	void buildEntityTest(int entityTipe, String nickname, String expected) {
		FactoryEntity factory = new FactoryEntity();
		IEntity entityResult = factory.buildEntity(entityTipe, nickname);
		assertEquals(expected,entityResult.getClass().getSimpleName());
	}

}
