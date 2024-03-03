package simpleSilo;

import mtcGeneric.Controller;
import mtcGeneric.Driver2ControllerIf;
import processControllers.Process2SiloIf;
import siloGeneric.MHSiloDriver2ControllerIf;
import siloGeneric.SiloController2DriverIf;
import siloGeneric.MixerTimerIf;
import system.LiqueurPlantSystem;

public class SimpleSilo extends Controller implements SiloController2DriverIf,MixerTimerIf  {
	public MHSiloDriver2ControllerIf itsDriver;
	protected int id;
		
	protected SimpleSiloState cState;
	protected SimpleSiloState ncState;
		
	//private Boolean full;
	//private Boolean empty;
	//Boolean filling;
	//Boolean pouring;
	
	// defined here to be inherited by 	
	//protected boolean heating=false;
	protected boolean tempReached = false;
	protected boolean mixCompleted = false;
	//protected double tempToHeat;
	//protected double currentTemp;
	
	//protected boolean mixing= false;
	protected int timeToMix;
	
	
	public SimpleSilo(MHSiloDriver2ControllerIf siloDriver){
		super((Driver2ControllerIf)siloDriver);
		super.itsDriver.setController(this);
		itsDriver = (MHSiloDriver2ControllerIf)super.itsDriver;
		cState = ncState=SimpleSiloState.EMPTY;
		//empty= true;
		//full = false;
		//filling=false;
		//pouring = false;
		itsProcessPort = new ProcessPort(this);
		id=1;
	}
	
	
	//private void setFilling(){empty = false;filling = true;}
	//protected void setPouring() {full = false;pouring = true;	}
	//private void setFull() {full = true;filling = false;}
	//private void setEmpty() {pouring = false; empty = true;	}
	
		
	public SimpleSilo getStatus() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
	@Override
	public void fillingLevelReached() {
		// TODO Auto-generated method stub
		itsDriver.closeInValve();
		//this.setFull();
		ncState=SimpleSiloState.FULL;
	}

	
	@Override
	public void pouringLevelReached() {
		// TODO Auto-generated method stub
		itsDriver.closeOutValve();
		//this.setEmpty();
		ncState=SimpleSiloState.EMPTY;
		//itsProcessCtrlPort.pouringLevelReached();
	}
	
		
	
	//@Override
	public void mixTimeCompleted() {		//in practice this should be triggered by a timer but it has been selected to be triggered by the operator panel
		// TODO Auto-generated method stub
		LiqueurPlantSystem.opPanel.report("Behaviour not supported by this silo");	
	}

	@Override
	public void tempReached() {
		// TODO Auto-generated method stub
		LiqueurPlantSystem.opPanel.report("Behaviour not supported by this silo");
	}

	
	
	public class ProcessPort implements SimpleSiloIf {
		private SimpleSilo itsComp;
		Process2SiloIf itsProcess;
		
		public ProcessPort(SimpleSilo ss) {
			itsComp = ss;
		}
		
		@Override
		public void setProcessCtrlDevicePort(Process2SiloIf port) {
			// TODO Auto-generated method stub
			itsProcess = port;
		}

		@Override
		public void fill() {
			// TODO Auto-generated method stub
			//itsComp.fill();
			if(cState==SimpleSiloState.EMPTY){
				itsDriver.openInValve();
				//setFilling();
				ncState=SimpleSiloState.FILLING;
			}
		}

		@Override
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
		public void stopPouring() {
			// TODO Auto-generated method stub
			pouringLevelReached();
		}

		@Override
		public SimpleSilo getStatus() {
			// TODO Auto-generated method stub
			return itsComp.getStatus();
		}

		@Override
		public boolean isFull() {
			// TODO Auto-generated method stub
			return (cState==SimpleSiloState.FULL)? true:false;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return (cState==SimpleSiloState.EMPTY)? true:false;
		}

		@Override
		public void updateDriver() {
			// TODO Auto-generated method stub
			itsDriver.write();
		}

		public boolean isMixCompleted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void updateState() {
			// TODO Auto-generated method stub
			cState=ncState;
		}

	}
	
}
