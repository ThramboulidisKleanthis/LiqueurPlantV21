package system;

import guis.OperatorsPanel;
import heatedSilo.HSilo;
import heatedSilo.HeatedSiloMtc;

import java.util.Timer;

import mHSilo.MHSilo;
import mHSilo.MixHeatedSiloMtc;
import mSilo.MSilo;
import mSilo.MixSiloMtc;
import plantSimulator.PlantSimFrame;
import processControllers.GenLiqueurA;
import processControllers.GenLiqueurB;
import resourceGeneric.ResourceController;
import resourcesMtc.PipeMtc;
import resourcesMtc.PowerMtc;
import siloGeneric.MHSiloDriver;
import simpleSilo.SimpleSilo;
import simpleSilo.SimpleSiloMtc;


public class PlantController {
	PlantSimFrame controlledPlant;
	OperatorsPanel itsOpPanel;
		
	SimpleSilo s1C;
	HSilo s2C;
	MSilo s3C;
	MHSilo s4C;
	
	ResourceController pipeC;
	ResourceController powerC;
	
	public GenLiqueurA genLiqueurA;
	public GenLiqueurB genLiqueurB;
	//Boolean loop;
	
	Timer controllerTimer;
	
	Command2Plant c2p;
	
	public PlantController(PlantSimFrame rPlant, OperatorsPanel opPanel, Command2Plant c2p,SimpleSiloMtc s1, HeatedSiloMtc s2, MixSiloMtc s3, MixHeatedSiloMtc s4, PipeMtc pi, PowerMtc po){
		controlledPlant = rPlant;
		itsOpPanel = opPanel;
		this.c2p = c2p;
		if(s1!=null) 	this.s1C= s1.itsController;
		if(s2!=null) this.s2C= s2.itsController;
		if(s3!=null) this.s3C= s3.itsController;
		if(s4!=null) this.s4C= s4.itsController;
		if(pi!=null) this.pipeC= pi.itsController;
		if(po!=null) this.powerC= po.itsController;
						
		
	}
	
	public void controllerStart(){
		// set up processes when ports introduced
		report("\nPlantController started - Activating Processes");
		genLiqueurA = new GenLiqueurA("GenLiquidA",pipeC, powerC,this);
		if((s1C!=null) && (s4C!=null)&&(pipeC!=null)&&(powerC!=null)){
			
			s1C.setProcessCtrlDevicePort(genLiqueurA.itsSimpleSiloControllerPort);
			genLiqueurA.itsSimpleSiloControllerPort.itsController = s1C.itsProcessPort;
			s4C.setProcessCtrlDevicePort(genLiqueurA.itsSimpleSiloControllerPort);	
			genLiqueurA.itsMHSiloControllerPort.itsController = s4C.itsProcessPort;
			genLiqueurA.activate();
			genLiqueurA.setResourcesAvailable();
			report("GenLiqueurA:Resources Available, Active, Started");
		}
		else
			report("GenLiqueurA:Resources not Available");
	
		genLiqueurB = new GenLiqueurB("GenLiquidB",pipeC, powerC,this);
		if((s2C!=null) && (s3C!=null)&&(pipeC!=null)&&(powerC!=null)){
			
			s2C.setProcessCtrlDevicePort(genLiqueurB.itsHSiloControllerPort);
			genLiqueurB.itsHSiloControllerPort.itsController = s2C.itsProcessPort;
			s3C.setProcessCtrlDevicePort(genLiqueurB.devicePort);
			genLiqueurB.itsMSiloControllerPort.itsController = s3C.itsProcessPort;
			genLiqueurB.activate();
			genLiqueurB.setResourcesAvailable();
			report("GenLiqueurB:Resources Available, Active, Started");
		}
		else {
			report("GenLiqueurB:Resources not Available");
			if(!genLiqueurA.isActive()) report("Close the program and run again to activate proper MTCs");
		}
		if(genLiqueurA.isActive())
			genLiqueurA.start();
			
		if(genLiqueurB.isActive())
			genLiqueurB.start();
	
		//s1C.fill();
		//s2C.fill();

		//s1C.itsDriver.write();
		//s2C.itsDriver.write();
		if(LiqueurPlantSystem.c2p.commandExists())
			LiqueurPlantSystem.pm.doUpdate();
		
		//setup periodic execution of controller
		controllerTimer = new Timer();
		controllerTimer.schedule(new controllerTimerTask(), 0,1000);
		
	}
	
	public void execute(){
		
		if(genLiqueurA.isActive()){
			s1C.itsDriver.read();
			((MHSiloDriver)(s4C.itsDriver)).read();
			genLiqueurA.genLiquid();
			//if(LiqueurPlantSystem.c2p.commandExists())
				//LiqueurPlantSystem.pm.doUpdate();
			}
		/*if(genLiqueurA.isActive()){
			pipeDriver.write();
			powerDriver.write();
			}*/
		
		
		if(genLiqueurB.isActive()){
			s2C.itsDriver.read();
			s3C.itsDriver.read();
			genLiqueurB.genLiquid();
			//if(LiqueurPlantSystem.c2p.commandExists())
				//LiqueurPlantSystem.pm.doUpdate();
			}
		if(LiqueurPlantSystem.c2p.commandExists()||(LiqueurPlantSystem.c2p.commandExists()))
			LiqueurPlantSystem.pm.doUpdate();
		/*if(genLiqueurA.isActive()){
			s1C.itsDriver.write();
			((MHSiloDriver)(s4C.itsDriver)).write();
			}
		if(genLiqueurB.isActive()){
			s2C.itsDriver.write();
			s3C.itsDriver.write();
			}
		
		if(genLiqueurA.isActive() ||genLiqueurB.isActive() ){
			pipeDriver.write();
			powerDriver.write();
			}*/
		
}
			
			
public void report(String msg){
	itsOpPanel.report(msg);
	}

}

