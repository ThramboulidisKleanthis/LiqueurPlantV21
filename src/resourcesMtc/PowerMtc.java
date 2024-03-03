package resourcesMtc;

import guis.LpButton;

import java.awt.Color;

import plantSimulator.PlantSimFrame;
import resourceGeneric.ResourceController;
import resourceGeneric.ResourceDriver;
import resourceGeneric.ResourceSim;
import resourceGeneric.ResourceSimIf;
import system.Command2Plant;

public class PowerMtc {
	public LpButton itsVis;
	protected ResourceDriver itsDriver;
	protected ResourceSim itsSim;
	public ResourceController itsController;
	
	
	public PowerMtc(PlantSimFrame psf,Command2Plant c2p){
		
		itsVis = new LpButton("Power",740, 180, 50, 65,Color.WHITE,Color.black,psf.itsGui); 
		psf.itsGui.addResVis(1,itsVis);
		itsSim = new ResourceSim("Power",psf.itsGui.cr[1]); 
		itsSim.setGuiRef(psf.itsGui.cr[1]);
		psf.setPower(itsSim);
		
		itsDriver = new ResourceDriver((ResourceSimIf)psf.power, c2p.c2r[1]);
		itsController = new ResourceController(itsDriver);
	}
}
