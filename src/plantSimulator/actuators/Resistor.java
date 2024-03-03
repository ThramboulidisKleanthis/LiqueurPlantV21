package plantSimulator.actuators;

import java.awt.Color;

import plantSimulator.Device;
import siloGeneric.MHSiloSim;

public class Resistor extends Device{
	MHSiloSim itsUnit;
	boolean on=false;
	
	public Resistor(String str){
		super(str);
	}
	
	public void turnOn(){
		on = true;
		itsGui.setBackground(Color.red);
		itsUnit.t.setActive(true);
		
		}
	
	public void setSilo(MHSiloSim itsSilo){
		this.itsUnit = itsSilo;
		
	}
	
	public void turnOff(){
		on = false;
		itsGui.setBackground(Color.green);
		itsUnit.t.setActive(false);
	
	}
}
