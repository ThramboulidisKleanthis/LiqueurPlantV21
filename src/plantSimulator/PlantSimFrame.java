package plantSimulator;
import plantVisualization.PlantsVisFrame;
import resourceGeneric.ResourceSim;
import siloGeneric.MHSiloSim;
import system.Command2Plant;
import system.PlantController;

public class PlantSimFrame extends Thread {
	public MHSiloSim [] s = new MHSiloSim[4];
	public ResourceSim pipe;
	public ResourceSim power; //just to reuse the functionality of pipe
	public PlantsVisFrame itsGui;
	PlantController itsController;
	PlantMonitor pm;
	Command2Plant c2p;
	
	
	public PlantSimFrame(PlantMonitor pm, Command2Plant c2p)  {
		//itsGui = new PlantsVisFrame(this);
		this.pm = pm;
		this.c2p = c2p;
		}
	
	public void setPlantVisFrame(PlantsVisFrame pVisFrame) {
		// TODO Auto-generated method stub
		itsGui = pVisFrame;
	}
	

	public void run() {
		while(true){
			pm.check4Update();
			for(int i=0; i<4;i++){
				if(s[i]!=null) 
						s[i].dispatchCommand(c2p.c2s[i]);
			}
				//LiqueurPlantSystem.plantSim.s[i].dispatchCommand(LiqueurPlantSystem.c2p.c2s[i]);
			if(pipe!=null)
				pipe.dispatchCommand(c2p.c2r[0]);
			if(power!=null)
				power.dispatchCommand(c2p.c2r[1]);
			c2p.reset();
		}
			
	}
	
	public void startPlant(){
		System.out.println("Plant started");
	}
	
	public void stopPlant(){
		System.out.println("Plant stoped");
	}

	public void setItsController(PlantController controller) {
		// TODO Auto-generated method stub
		itsController = controller;
		}

	public void addMtcSim(int i, MHSiloSim ss){
		s[i]=ss;
	}
	
	public void setPipe(ResourceSim p){
		pipe = p;
	}
	
	public void setPower(ResourceSim p){
		power = p;
	}
	/*public void addMtcS0() {
		// TODO Auto-generated method stub
		itsGui.addMtcS0();
		s[0] = new MHSiloSim(1,"IN1","OUT1","F1","E1","R1","T1","M1",itsGui.s[0]);
		s[0].setGuirefs(itsGui.s[0]);
	}
	
	public void addMtcS1() {
		// TODO Auto-generated method stub
		itsGui.addMtcS1();
		s[1] = new MHSiloSim(2,"IN2","OUT2","F2","E2","R2","T2","M2",itsGui.s[1]);
		s[1].setGuirefs(itsGui.s[1]);
	}

	public void addMtcS2() {
		// TODO Auto-generated method stub
		itsGui.addMtcS2();
		s[2] = new MHSiloSim(3,"IN3","OUT3","F3","E3","R3","T3","M3",itsGui.s[2]);
		s[2].setGuirefs(itsGui.s[2]);
	}
	public void addMtcS3() {
		// TODO Auto-generated method stub
		itsGui.addMtcS3();
		s[3] = new MHSiloSim(4,"IN4","OUT4","F4","E4","R4","T4","M4",itsGui.s[3]);
		s[3].setGuirefs(itsGui.s[3]);
	}

	public void addPipe(){
		itsGui.Pipe();
		pipe = new ResourceSim("Pipe",itsGui.cr[0]);
		pipe.setGuiRef(itsGui.cr[0]);
		
	}
	
	public void addPower(){
		itsGui.Power();
		power = new ResourceSim("Power",itsGui.cr[1]);
		power.setGuiRef(itsGui.cr[1]);
	}
*/
		
	/*public void update() {
		// TODO Auto-generated method stub
		for(int i=0; i<4;i++)
			LiqueurPlantSystem.plantSim.s[i].update();
		pipe.update();
		power.update();
		
	}*/

}