// completed on 30/11/2013
package processControllers;

import java.util.Timer;
import java.util.TimerTask;

import mHSilo.MHSilo;
import resourceGeneric.ResourceControllerIf;
import simpleSilo.SimpleSilo;
import system.PlantController;

public class GenLiqueurA extends Process   {
	//AutoFillingSmartSilo s1;
	//SimpleSiloIf s1;
	//MHSiloIf s4;
	ResourceControllerIf pipe;
	ResourceControllerIf power;
	public SimpleSiloControllerPort itsSimpleSiloControllerPort;
	public MHSiloControllerPort itsMHSiloControllerPort;

	private int s1afTime = 500;
	
	
	GenLiquidAState currentState;
	
	public GenLiqueurA(String str,ResourceControllerIf pipe, ResourceControllerIf power,PlantController ctrl){
		super(str,ctrl);
		//s1 = afss;
		//s4 = mhss;
		this.pipe = pipe;
		this.power = power;
		itsSimpleSiloControllerPort = new SimpleSiloControllerPort(this);
		itsMHSiloControllerPort = new MHSiloControllerPort(this);
	}
		
	
	@Override
	public void activate() {
		reset();
		currentState = GenLiquidAState.WAITFORFULLEMPTYANDPIPE;
	}

	public void genLiquid(){
		//while(loop){
		itsSimpleSiloControllerPort.itsController.updateState();
		itsMHSiloControllerPort.itsController.updateState();
		switch(currentState){
				case WAITFORFULLEMPTYANDPIPE:
					if(itsSimpleSiloControllerPort.itsController.isFull() && itsMHSiloControllerPort.itsController.isEmpty() && pipe.isAvailable()){
						pipe.acquire();
						itsSimpleSiloControllerPort.itsController.empty();
						itsMHSiloControllerPort.itsController.fill();
						completed = false;
						currentState = GenLiquidAState.POURINGFILLING;
						}
					break;
				case POURINGFILLING:
					if(itsMHSiloControllerPort.itsController.isFull()){
						pipe.release();
						itsMHSiloControllerPort.itsController.heatToTemp(60.0);
						itsSimpleSiloControllerPort.itsController.stopPouring(); //added on 31 Mar 2014 to close out valve of S1 when s4 fills without emptying s1
						Timer s1afTimer = new Timer();
						s1afTimer.schedule(new s1afTimerTask(),s1afTime);
						currentState = GenLiquidAState.HEATING;
						}
					break;
				case HEATING:	
					if(itsMHSiloControllerPort.itsController.tempReached()){
						currentState = GenLiquidAState.WAITFORPOWER;
						}
					break;
				case WAITFORPOWER:
					if(power.isAvailable()){
						power.acquire();
						itsMHSiloControllerPort.itsController.startMixing(32);
						currentState = GenLiquidAState.MIXING;
						}
					break;
				case MIXING:
					if(itsMHSiloControllerPort.itsController.isMixCompleted()){
						power.release();
						itsMHSiloControllerPort.itsController.empty();
						currentState = GenLiquidAState.POURING;
					}
					break;
				case POURING:
					if(itsMHSiloControllerPort.itsController.isEmpty()){
						currentState = GenLiquidAState.WAITFORFULLEMPTYANDPIPE;
						//s1.startFilling(); paralelized with timer
						completed = true;
					}
					break;
			}
			this.processPendingStop();
	}

	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		itsSimpleSiloControllerPort.itsController.fill();
		itsSimpleSiloControllerPort.itsController.updateDriver();
		}

	@Override
	public void suspend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void urgentStop() {
		// TODO Auto-generated method stub
		
	}

	
	 class s1afTimerTask extends TimerTask {
			public void run() {
				itsSimpleSiloControllerPort.itsController.fill();
	        }
		}  
		
	
	 public class SimpleSiloControllerPort extends ControllerPort implements Process2SiloIf{
			//GenLiqueurA itsComp;
			public SimpleSilo.ProcessPort itsController;
			
			public SimpleSiloControllerPort(Process p) {
				// TODO Auto-generated constructor stub
				super(p);
				
			}

			public void setController(SimpleSilo.ProcessPort c){
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
	 
	 public class MHSiloControllerPort extends  ControllerPort implements Process2SiloIf {
			//GenLiqueurA itsComp;
			public MHSilo.ProcessPort itsController;
			
			public MHSiloControllerPort(Process p) {
				// TODO Auto-generated constructor stub
				super(p);
				}
			
			public void setController(MHSilo.ProcessPort c){
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

	
}

