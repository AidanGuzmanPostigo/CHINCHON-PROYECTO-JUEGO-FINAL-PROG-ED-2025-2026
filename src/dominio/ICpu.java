package dominio;
public interface ICpu extends IEntity{
	int choosePlay(int round, int maxPoints);
	int choosePlay(int maxPoints);
}