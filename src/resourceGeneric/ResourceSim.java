package resourceGeneric;

import guis.LpButton;

import java.awt.Color;

import plantSimulator.Device;
import mtcGeneric.Driver2DeviceIf;
import system.Command2Resource;

public class ResourceSim extends Device implements ResourceSimIf {
	private boolean setAvailable, setAcquired;
	
	public ResourceSim(String str,LpButton itsGui) {
		super(str);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see plantSimulator.ResourceSimIf#setColor(java.awt.Color)
	 */
	
	public void setDriver(resourceGeneric.ResourceDriver2DeviceIf msif) {
		// TODO Auto-generated method stub
		itsDriver = msif;
		super.itsDriver = (Driver2DeviceIf)msif;
	}

	public void dispatchCommand(Command2Resource c2r){
		if (!c2r.commandExists()) return;
		if(c2r.setAvailable) setAvalable();
		if(c2r.setAcquired) setAcquired();
		update();
	}
	
	@Override
	public void setColor(Color c){
		this.itsGui.setBackground(c);
	}

	public void setAvalable() {
		// TODO Auto-generated method stub
		setAvailable = true;
		setAcquired = false;
		
	}

	public void setAcquired() {
		// TODO Auto-generated method stub
		setAcquired = true;
		setAvailable = false;
		
	}

	/*public void updateVisual() {
		// TODO Auto-generated method stub
		updateVisual= true;
	}*/
	
	public void update() {
		// TODO Auto-generated method stub
		//if(!updateVisual) return;
		if(setAvailable) setColor(Color.white);
		if(setAcquired) setColor(Color.GREEN);
		
		//updateVisual=false;
	
	}
}
