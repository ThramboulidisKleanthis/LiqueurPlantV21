package plantSimulator.actuators;

import java.awt.Color;

import plantSimulator.Device;
import plantVisualization.PlantsVisFrame;
import siloGeneric.MHSiloSim;

public class Valve extends Device implements ValveIf{
	MHSiloSim itsUnit;
	
	public Valve(String str){
		super(str); 
	}
	
	public void setSilo(MHSiloSim itsSilo){
		this.itsUnit = itsSilo;
		
	}
	
	public void open(){
		//System.out.println("Valve " + name + " opened");
		PlantsVisFrame.report("Valve " + name + " opened");
		itsGui.setBackground(Color.green);
		
	}
	
	public void close(){
		//System.out.println("Valve " + name + " closed");
		PlantsVisFrame.report("Valve " + name + " closed");
		itsGui.setBackground(Color.red);
		
	}
}
