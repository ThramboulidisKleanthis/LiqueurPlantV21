package plantSimulator.sensors;

import plantSimulator.Device;

public class Sensor extends Device{
	Device itsUnit;
	//String name;
	
	public Sensor(String name,Device u){
		super(name);
		itsUnit=u;
		}
}
