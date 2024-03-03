package siloGeneric;

import mtcGeneric.Driver2DeviceIf;

public interface MHSiloDriver2DeviceIf extends Driver2DeviceIf{
	
	public void pouringLevelReached();

	public void fillingLevelReached();

	//public void mixCompleted();	it was removed to operators Gui
	
	public void tempReached();
	
}
