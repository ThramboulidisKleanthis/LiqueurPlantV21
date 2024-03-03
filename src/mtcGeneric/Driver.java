package mtcGeneric;

import plantSimulator.Device;

public class Driver implements Driver2ControllerIf {
	protected Device itsDevice;
	protected Controller2DriverIf itsController;
	
	public Driver(Device dev){
		this.itsDevice = dev;
	}
	
	/* (non-Javadoc)
	 * @see deviceDrivers.Driver2ControllerIf#setController(deviceControllers.Controller)
	 */
	@Override
	public void setController(Controller2DriverIf c){
		itsController = c;
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}
	
	
}
