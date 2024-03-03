package processControllers;

public interface Process2SiloIf {
	void pouringLevelReached();
	void fillingLevelReached();
	void heatingCompleted();
	void mixingCompleted();
}
