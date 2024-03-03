package mSilo;

import mtcGeneric.Mtc;
import plantSimulator.PlantSimFrame;
import plantVisualization.VisSilo;
import siloGeneric.MHSiloDriver;
import siloGeneric.MHSiloSim;
import siloGeneric.MHSiloSimIf;
import siloGeneric.MixSensorActionListener;
import system.Command2Plant;

public class MixSiloMtc extends Mtc{
	public MSilo itsController;
	
	
	public MixSiloMtc(PlantSimFrame psf,Command2Plant c2p){
		itsVis = new VisSilo(2,0,200, false, null, new MixSensorActionListener(2),psf.itsGui);
		psf.itsGui.addMtcVis(2,itsVis);
		itsSim = new MHSiloSim(3,"IN3","OUT3","F3","E3","R3","T3","M3",psf.itsGui.s[2]);
		itsSim.setGuirefs(psf.itsGui.s[2]);
		psf.addMtcSim(2,itsSim);
		
		itsDriver = new MHSiloDriver((MHSiloSimIf) psf.s[2],c2p.c2s[2]);
		itsController = new MSilo(itsDriver);
		
		}


	public void mixTimeCompleted() {
		// TODO Auto-generated method stub
		itsDriver.mixerOff();
	}


	
	
}
