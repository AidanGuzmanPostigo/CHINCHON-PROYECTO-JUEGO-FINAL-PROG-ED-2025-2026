package dominio;
public class FactoryEntity {
	public IEntity buildEntity(int entityTipe, String nickname) {
		if (entityTipe == 1) {
			return new Entity(nickname);
		} else {
			return new Cpu(nickname);
		}
	}
}