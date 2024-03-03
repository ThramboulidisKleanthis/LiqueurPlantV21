package resourcesMtc;

import guis.LpButton;

import java.awt.Color;

import plantSimulator.PlantSimFrame;
import resourceGeneric.ResourceController;
import resourceGeneric.ResourceDriver;
import resourceGeneric.ResourceSim;
import resourceGeneric.ResourceSimIf;
import system.Command2Plant;


public class PipeMtc {
	public LpButton itsVis;
	protected ResourceDriver itsDriver;
	protected ResourceSim itsSim;
	public ResourceController itsController;
	
	public PipeMtc(PlantSimFrame psf,Command2Plant c2p){
		itsVis = new LpButton("Pipe",50+400, 50+140, 270, 40,Color.WHITE,Color.BLACK,psf.itsGui); 
		psf.itsGui.addResVis(0,itsVis);
		itsSim = new ResourceSim("Pipe",psf.itsGui.cr[0]); 
		itsSim.setGuiRef(psf.itsGui.cr[0]);
		psf.setPipe(itsSim);
		
		itsDriver = new ResourceDriver((ResourceSimIf)psf.pipe, c2p.c2r[0]);
		itsController = new ResourceController(itsDriver);
	}
}
