package processControllers;

public interface ProcessIf {
	void reset();
	void activate();
	void stop();	// deferred up to the completions of the current iteration (for iterative processes). Similar to urgent stop for non-iterative ones.
	boolean isActive();
	void start();	// added 22/12 as in my hand notes
	void suspend(); // added 22/12 as in my hand notes
	void resume();	// added 22/12 as in my hand notes
	void urgentStop();	// added 22/12 as in my hand notes see also statechart in hand notes
	}
