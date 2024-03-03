package plantSimulator.actuators;

import java.awt.Color;

import plantSimulator.Device;
import siloGeneric.MHSiloSim;

public class Mixer extends Device {
	MHSiloSim itsUnit;
	//boolean on;
	boolean active=false;
	
	public Mixer(String str){
		super(str);
	}
	
	public void setSilo(MHSiloSim itsSilo){
		this.itsUnit = itsSilo;
		
	}
	
	public void turnOn(){
		setActive(true);
		//startMixing();
				
	}
	
	
	public void turnOff(){  //it is instead of a timer for flexibility, this is why it reports directly to controller
		if(active) {
			setActive(false);
			
		}
	}

	

	public void setActive(boolean b){
		active=b;
		itsGui.setBackground(b?Color.red:Color.white);
	}
}
