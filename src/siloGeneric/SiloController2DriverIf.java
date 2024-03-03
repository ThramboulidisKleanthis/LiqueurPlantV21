package siloGeneric;

import mtcGeneric.Controller2DriverIf;

public interface SiloController2DriverIf extends Controller2DriverIf{
	void fillingLevelReached();
	void pouringLevelReached();
	void tempReached();
	//void setCurrentTemp(double temp); removed as not used
	}
