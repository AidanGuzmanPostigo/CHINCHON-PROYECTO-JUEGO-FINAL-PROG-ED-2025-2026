package dominio;
/**
 * Clase encargada de la factory de las entidades.
 */
public class FactoryEntity {
	/**
	 * Crea una instancia de le entidad en base al tipo introducido por el usuario.
	 * @param entityTipe Tipo de la entidad, si es un 1 devuelve una entidad base, si no devuelve una Cpu.
	 * @param nickname Mote de la entidad.
	 * @return La entidad creada, ya sea una entidad base o una Cpu.
	 */
	public IEntity buildEntity(int entityTipe, String nickname) {
		if (entityTipe == 1) {
			return new Entity(nickname);
		} else {
			return new Cpu(nickname);
		}
	}
}