package plantSimulator.sensors;

import java.awt.Color;

import plantSimulator.Device;
import siloGeneric.MHSiloSim;


public class TempSensor extends Sensor{
	public Device itsUnit;
	Boolean tempReached;
	double tempToReach;
	boolean active=false;
	
	public TempSensor(String str, Device unit){
		super(str, unit);
		tempReached = false;
	}
	
	public void setSilo(MHSiloSim itsSilo){
		this.itsUnit = itsSilo;
		
	}
	
	public void trigger() {
		if(active)
			((siloGeneric.MHSiloDriver)itsUnit.itsDriver).tempReached();
	}
	
	public void setActive(boolean b){
		active=b;
		itsGui.setBackground(b?Color.cyan:Color.white);
	}
}
