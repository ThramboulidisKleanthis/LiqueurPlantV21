package plantSimulator.sensors;

import plantSimulator.Device;
import siloGeneric.MHSiloDriver2DeviceIf;

public class EmptyingLevelSensor extends LevelSensor {

		
	public EmptyingLevelSensor(String str, Device u) {
		super(str, u);
		// TODO Auto-generated constructor stub
	}

	public void trigger(){
		//((SmartSilo)itsSilo.itsDriver.itsController).pouringLevelReached();
		//((MHSiloDriver)itsUnit.itsDriver).pouringLevelReached();
		((MHSiloDriver2DeviceIf)(itsUnit.itsDriver)).pouringLevelReached();
	}
}
