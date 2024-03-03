package simpleSilo;

import mtcGeneric.ControllerIf;

public interface SimpleSiloIf extends ControllerIf {
	void fill();
	//void stopFilling();
	void empty();
	void stopPouring();
	SimpleSilo getStatus();
	boolean isFull();	//	22/12
	boolean isEmpty();
	//boolean isFilling(); removed 11Apr as not used
	//boolean isPouring();
	void updateDriver();
	}
