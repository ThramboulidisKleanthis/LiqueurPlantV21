// completed 02:00 1/2/2013
package processControllers;

import heatedSilo.HSilo;

import java.util.Timer;
import java.util.TimerTask;

import mSilo.MSilo;
import resourceGeneric.ResourceController;
import resourceGeneric.ResourceControllerIf;
import system.PlantController;


public class GenLiqueurB extends Process  {
	//HSiloIf s2;
	//MSiloIf s3;
	ResourceControllerIf pipe;
	ResourceControllerIf power;
	public Process2SiloIf devicePort;
	public HSiloControllerPort itsHSiloControllerPort;
	public MSiloControllerPort itsMSiloControllerPort;
	
	GenLiquidBState currentState;
	
	private int s2afTime = 400;
	
	public GenLiqueurB(String str,ResourceController pipe, ResourceController power,PlantController ctrl){
		super(str,ctrl);
		//s2 = hss;
		//s3 = mss;
		this.pipe = pipe;
		this.power = power;
		itsHSiloControllerPort = new HSiloControllerPort(this);
		itsMSiloControllerPort = new MSiloControllerPort(this);
	}
		
	
	@Override
	public void activate() {
		reset();
		//active = true;
		currentState = GenLiquidBState.WAITFORS2FULL;
		
	}

	public void genLiquid(){
		//while(loop){
		itsHSiloControllerPort.itsController.updateState();
		itsMSiloControllerPort.itsController.updateState();
			switch(currentState){
				case WAITFORS2FULL:	// known bug. Heating is not starting before the end of the process 
					if(itsHSiloControllerPort.itsController.isFull()){
						itsHSiloControllerPort.itsController.heatToTemp(110.0);
						completed = false;
						currentState = GenLiquidBState.HEATING;
					}
					break;
				case HEATING: 
					if(itsHSiloControllerPort.itsController.tempReached()){
						currentState = GenLiquidBState.S3EMPTYANDPIPE;
					}
					break;
				case S3EMPTYANDPIPE:
					if(itsMSiloControllerPort.itsController.isEmpty() && pipe.isAvailable()){
						pipe.acquire();
						itsHSiloControllerPort.itsController.empty();
						itsMSiloControllerPort.itsController.fill();
						currentState = GenLiquidBState.POURINGFILLING;
						}
					break;
				case POURINGFILLING:
					if(itsMSiloControllerPort.itsController.isFull()){
						pipe.release(); 
						itsHSiloControllerPort.itsController.stopPouring();	//added on 31 Mar 2014 to close out valve of S2
						Timer s2afTimer = new Timer();
						s2afTimer.schedule(new s2afTimerTask(),s2afTime);
						currentState = GenLiquidBState.WAITFORPOWER;
						}
					break;
				case WAITFORPOWER:
					if(power.isAvailable()){
						power.acquire();
						itsMSiloControllerPort.itsController.startMixing(32);
						currentState = GenLiquidBState.MIXING;
						}
					else {
						if(itsMSiloControllerPort.itsController.isEmpty()){ // added on 31 Mar 2014 to report the unexpected activation of empty level sensor while in this state 
							urgentStop();
							itsController.report("Unexpected event! process abnormal termination");
						}
					}
					break;
				case MIXING:
					if(itsMSiloControllerPort.itsController.isMixCompleted()){
						power.release(); 
						itsMSiloControllerPort.itsController.empty();
						currentState = GenLiquidBState.POURING;
					}
					else {
						if(itsMSiloControllerPort.itsController.isEmpty()){ // added on 31 Mar 2014 to report the unexpected activation of empty level sensor while in this state 
							urgentStop();
							itsController.report("Unexpected event!process abnormal termination");
						}
					}
					break;
				case POURING:
					if(itsMSiloControllerPort.itsController.isEmpty()){
						//s2.startFilling(); paralelized with timer 2 Apr
						currentState = GenLiquidBState.WAITFORS2FULL;
						completed = true;
					}
					break;
			}
		this.processPendingStop();	
		
	}

	class s2afTimerTask extends TimerTask {
		public void run() {
			itsHSiloControllerPort.itsController.fill();
        }
	}  
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		itsHSiloControllerPort.itsController.fill();
		itsHSiloControllerPort.itsController.updateDriver();
		}

	@Override
	public void suspend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	
		
	 public class HSiloControllerPort extends ControllerPort implements Process2SiloIf{
			//GenLiqueurA itsComp;
			public HSilo.ProcessPort itsController;
			
			public HSiloControllerPort(Process p) {
				// TODO Auto-generated constructor stub
				super(p);
				
			}

			public void setController(HSilo.ProcessPort c){
				itsController = c;
				
			}
			
			@Override
			public void pouringLevelReached() {
				// TODO Auto-generated method stub
				pipe.release();
				
			}

			@Override
			public void fillingLevelReached() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void heatingCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mixingCompleted() {
				// TODO Auto-generated method stub
				
			}

		}
	
	public class MSiloControllerPort extends HSiloControllerPort {
		public MSilo.ProcessPort itsController;
		
		public MSiloControllerPort(GenLiqueurB p) {
			// TODO Auto-generated constructor stub
			super((Process)p);
			
		}
		public void setController(MSilo.ProcessPort c){
			itsController = c;
			
		}
 }
	
}

