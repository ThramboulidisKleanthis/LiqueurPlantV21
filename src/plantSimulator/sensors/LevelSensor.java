package plantSimulator.sensors;

import plantSimulator.Device;

public abstract class LevelSensor extends Sensor{
				
	LevelSensor(String name, Device u){
		super(name,u);
	}
	
	public abstract void trigger();
	
}
