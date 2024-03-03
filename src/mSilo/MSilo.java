package mSilo;


import processControllers.Process2SiloIf;
import siloGeneric.MHSiloDriver2ControllerIf;
import siloGeneric.MSiloIf;
import simpleSilo.SimpleSilo;
import simpleSilo.SimpleSiloState;
import system.LiqueurPlantSystem;

public class MSilo extends SimpleSilo {
	public ProcessPort itsProcessPort; 
	
	public MSilo(MHSiloDriver2ControllerIf siloDriver) {
		super(siloDriver);
		itsProcessPort = new ProcessPort(this);
		id=3;
	}

	
	public void mixTimeCompleted() {
		// TODO Auto-generated method stub
		if(cState==SimpleSiloState.MIXING){
			mixCompleted=true;
			itsDriver.mixerOff();
			ncState=SimpleSiloState.FULL;
		}
		
	}
	
	public class ProcessPort extends SimpleSilo.ProcessPort implements MSiloIf {
		//private HSilo itsComp;
		Process2SiloIf itsProcess;
		
		public ProcessPort(MSilo ms) {
			super(ms);
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
