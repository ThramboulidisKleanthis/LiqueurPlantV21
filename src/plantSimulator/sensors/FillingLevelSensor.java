package plantSimulator.sensors;

import plantSimulator.Device;
import siloGeneric.MHSiloDriver2DeviceIf;

public class FillingLevelSensor extends LevelSensor {

	public FillingLevelSensor(String str, Device u) {
		super(str,u);
		// TODO Auto-generated constructor stub
	}

	public void trigger(){
		//((SmartSilo)itsSilo.itsDriver.itsController).fillingLevelReached();
		((MHSiloDriver2DeviceIf)itsUnit.itsDriver).fillingLevelReached();
	}
}
