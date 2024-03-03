package resourceGeneric;

import mtcGeneric.Driver;
import plantSimulator.Device;
import system.Command2Resource;

public class ResourceDriver extends Driver implements ResourceDriver2ControllerIf, ResourceDriver2DeviceIf{
	ResourceSimIf itsUnit;
	//boolean setAvailable=false;
	//boolean setAcquired=false;
	Command2Resource c2r;
	
	public ResourceDriver(ResourceSimIf d,Command2Resource c2r){
		super((Device)d);
		this.itsUnit = d;
		d.setDriver((ResourceDriver2DeviceIf)this);		// so as units have access to their drivers 
		this.c2r = c2r;
	}
	
//ResourceDriver2ControllerIf
	public void setAvailable() {
		c2r.setAvailable=true;
		c2r.setAcquired=false;
		//itsUnit.setColor(Color.white);
	}
	
	public void setAcquired() {
		c2r.setAcquired = true;
		c2r.setAvailable=false;
		//itsUnit.setColor(Color.GREEN);
	}

	//Driver2ControllerIf
	public void setController(ResourceController2DriverIf c) {
		// TODO Auto-generated method stub
		super.itsController = c;
		itsController = (ResourceController2DriverIf)super.itsController;
	}
	
	//with the introduction of Command2Plant drivers write directly to this command. write is not required. 
	/*public void write(){		// added for scan based 
		// order changed from input to output to address the bug of not displaying filling when in valve open 
		boolean change=false;
		if(setAvailable){
			((ResourceSim)itsDevice).setAvalable();
			setAvailable= false;
			change = true;
		}
		if(setAcquired){
			((ResourceSim)itsDevice).setAcquired();
			setAcquired= false;
			change = true;
		}
		if(change)
			((ResourceSim)itsDevice).updateVisual();
			
	}
*/
	
//ResourceDriver2DeviceIf
	@Override
	public void setDevice(Device d) {
		// TODO Auto-generated method stub
		super.itsDevice = d;
		itsUnit= (ResourceSimIf)d;
	}


	
}
