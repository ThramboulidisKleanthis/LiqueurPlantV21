package plantSimulator;

import guis.LpButton;

import java.awt.Button;

import mtcGeneric.Driver2DeviceIf;
import plantVisualization.PlantsVisFrame;

public class Device {
	protected String name;
	public Driver2DeviceIf itsDriver;	// is defined by the constructor of the corresponding driver
	public static PlantsVisFrame itsPsg; 
	//public static PlantSimulatorsGui itsPsg; 
	public  Button itsGui;
	protected boolean updateVisual;
		
	protected Device(String str){
		name = str;
	}
	
	public void setDriver(Driver2DeviceIf driver){
		itsDriver = driver;
	}

	public void setGuiRef(LpButton itsGui){
		this.itsGui = itsGui;
	}
	
	public static void setPlantSimulatorsGui(PlantsVisFrame psgui) {
		// TODO Auto-generated method stub
		itsPsg = psgui;
	}
	
}
