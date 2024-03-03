package siloGeneric;

import mtcGeneric.Controller2DriverIf;
import mtcGeneric.Driver;
import plantSimulator.Device;
import system.Command2Silo;

public class MHSiloDriver extends Driver implements MHSiloDriver2ControllerIf, MHSiloDriver2DeviceIf {
	SiloController2DriverIf itsController;
	MHSiloSimIf itsDevice;
	
	Command2Silo c2s;
	//boolean openInValve = false;
	//boolean closeInValve = false;
	//boolean openOutValve = false;
	//boolean closeOutValve = false;
	
	//private boolean heaterOn=false;
	//private boolean stopHeater=false;
	boolean tempReached = false;
		
	//private boolean mixerOn=false;
	//boolean mixCompleted = false;
	//private boolean stopMixer=false;
	
	boolean fillingLevelReached=false;
	boolean pouringLevelReached = false;
	
	public MHSiloDriver(MHSiloSimIf d, Command2Silo c2s) {
		super((Device)d);
		itsDevice=d;
		d.setDriver((MHSiloDriver2DeviceIf)this);		// so as units have access to their drivers 
		
		this.c2s = c2s;
	}
	
	//MHSiloDriver2ControllerIf
	@Override
	public void setController(Controller2DriverIf c){
		super.itsController = c;
		itsController = (SiloController2DriverIf)super.itsController;
	}
	
	@Override
	public void read(){		// added for scan based
		if (fillingLevelReached){
			itsController.fillingLevelReached();
			fillingLevelReached = false;
		}
		if(pouringLevelReached){
			itsController.pouringLevelReached();
			pouringLevelReached = false;
		}
		/*if(mixCompleted){
			itsController.mixTimeCompleted();
			mixCompleted = false;
		}*/
		if(tempReached){
			itsController.tempReached();
			tempReached = false;
		}
	}

		
	@Override
	public void openInValve() {
		c2s.openInValve = true;
		
		}
	
	@Override
	public void closeInValve() {
		c2s.closeInValve = true;
		
		}

	@Override
	public void openOutValve() {
		c2s.openOutValve = true;
		
		
	}

	@Override
	public void closeOutValve() {
		c2s.closeOutValve = true;
		
		
	}
	@Override
	public void mixerOn() {
		// TODO Auto-generated method stub
		c2s.mixerOn=true;
		
	}

	//@Override
	public void mixerOff() {
		// TODO Auto-generated method stub
		c2s.stopMixer= true;
		
	}

	@Override
	public void heaterOn() {
		// TODO Auto-generated method stub
		c2s.heaterOn=true;
		
	}

	@Override
	public void heaterOff() {
		// TODO Auto-generated method stub
		c2s.stopHeater= true;
		
	}
	
	//MHSiloDriver2DeviceIf
	//triggered by sensors
	@Override
	public void fillingLevelReached(){
		fillingLevelReached = true;
		}
		
	
	@Override
	public void pouringLevelReached(){
		pouringLevelReached = true;
			
		}
	
	@Override
	public void tempReached() {
		tempReached = true;
	}

	/*@Override
	public void mixCompleted() {
		mixCompleted = true;
	}*/

	@Override
	public void setDevice(Device d) { // not used since this task was assigned in this implementation to the constructor
		super.itsDevice = d;
		itsDevice= (MHSiloSimIf)d;
	}
		
	
	
	
}
