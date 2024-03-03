package heatedSilo;

import processControllers.Process2SiloIf;
import siloGeneric.HSiloIf;
import siloGeneric.MHSiloDriver2ControllerIf;
import siloGeneric.SiloController2DriverIf;
import simpleSilo.SimpleSilo;
import simpleSilo.SimpleSiloState;

public class HSilo extends SimpleSilo implements SiloController2DriverIf {
	public ProcessPort itsProcessPort; 
	
	public HSilo(MHSiloDriver2ControllerIf siloDriver) {
		super(siloDriver);
		itsProcessPort = new ProcessPort(this);
		id =2;
		// TODO Auto-generated constructor stub
	}

	
	/*
	@Override
	public void setCurrentTemp(double temp) {
		// TODO Auto-generated method stub
		
	}
	*/
		
	public void tempReached() {
		// TODO Auto-generated method stub
		tempReached=true;
		itsDriver.heaterOff();
		ncState=SimpleSiloState.FULL;
	}
		
	public class ProcessPort extends SimpleSilo.ProcessPort implements HSiloIf {
		//private HSilo itsComp;
		Process2SiloIf itsProcess;
		
		public ProcessPort(HSilo hs) {
			super(hs);
			//itsComp = hs;
		}
		
						
		@Override
		public void heatToTemp(double temp) {
			// TODO Auto-generated method stub
			//heating = true;
			//tempReached = false;
			if(cState==SimpleSiloState.FULL){
				itsDriver.heaterOn();
				ncState=SimpleSiloState.HEATING;
				tempReached=false;
			}
		}

		@Override
		public boolean tempReached() {
			// TODO Auto-generated method stub
			return tempReached? true: false;
		}
	}
}
