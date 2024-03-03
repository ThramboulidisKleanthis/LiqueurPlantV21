package siloGeneric;

public interface MixerIf {
	void startMixing(int sec);
	void stopMixing();
	boolean isMixCompleted();
}
