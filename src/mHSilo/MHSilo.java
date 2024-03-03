package mHSilo;


import processControllers.Process2SiloIf;
import siloGeneric.HSiloIf;
import siloGeneric.MHSiloDriver2ControllerIf;
import siloGeneric.MSiloIf;
import siloGeneric.SiloController2DriverIf;
import simpleSilo.SimpleSilo;
import simpleSilo.SimpleSiloState;
import system.LiqueurPlantSystem;


	public class MHSilo extends SimpleSilo implements SiloController2DriverIf {
	public ProcessPort itsProcessPort; 
	
	public MHSilo(MHSiloDriver2ControllerIf siloDriver) {
		super(siloDriver);
		itsProcessPort = new ProcessPort(this);
		id =4;
	}

	
	
		
	public void tempReached() {
		// TODO Auto-generated method stub
		tempReached = true;
		itsDriver.heaterOff();
		ncState=SimpleSiloState.FULL;
	}
		
	public void mixTimeCompleted() {
		// TODO Auto-generated method stub
		if(cState==SimpleSiloState.MIXING){
			mixCompleted=true;
			itsDriver.mixerOff();
			ncState=SimpleSiloState.FULL;
		}
	}
	
	public class ProcessPort extends SimpleSilo.ProcessPort implements HSiloIf, MSiloIf {
		//private HSilo itsComp;
		Process2SiloIf itsProcess;
		
		public ProcessPort(MHSilo mhs) {
			super(mhs);
			//itsComp = hs;
		}

		public void empty() {
			// TODO Auto-generated method stub
			if(cState==SimpleSiloState.FULL){
				itsDriver.openOutValve();
				//setPouring();
				ncState=SimpleSiloState.POURING;
				tempReached = false;		// set just for the subclasses
				mixCompleted = false;		// set just for the subclasses
			}
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
			return tempReached? true:false;
		}

		@Override
		public void startMixing(int sec) {
			// TODO Auto-generated method stub
			if(cState==SimpleSiloState.FULL){
				mixCompleted = false;
				itsDriver.mixerOn();
				ncState=SimpleSiloState.MIXING;
			}
			
		}
		@Override
		public void stopMixing() {
			// TODO Auto-generated method stub
			LiqueurPlantSystem.opPanel.report("Not implemented method");
		}

		@Override
		public boolean isMixCompleted() {
			// TODO Auto-generated method stub
			return mixCompleted? true: false;
		}
	}
}
