package simpleSilo;

import mtcGeneric.Mtc;
import plantSimulator.PlantSimFrame;
import plantVisualization.VisSilo;
import siloGeneric.MHSiloDriver;
import siloGeneric.MHSiloSim;
import siloGeneric.MHSiloSimIf;
import system.Command2Plant;

public class SimpleSiloMtc extends Mtc{
	public SimpleSilo itsController;
	
	
	public SimpleSiloMtc(PlantSimFrame psf,Command2Plant c2p){
		itsVis = new VisSilo(0,0,0, false, null, null,psf.itsGui);
		psf.itsGui.addMtcVis(0,itsVis);
		itsSim = new MHSiloSim(1,"IN1","OUT1","F1","E1","R1","T1","M1",psf.itsGui.s[0]);
		itsSim.setGuirefs(psf.itsGui.s[0]);
		psf.addMtcSim(0,itsSim);
		
		itsDriver = new MHSiloDriver((MHSiloSimIf) psf.s[0],c2p.c2s[0]);
		itsController = new SimpleSilo(itsDriver);
	}
}
