package mHSilo;

import mtcGeneric.Mtc;
import plantSimulator.PlantSimFrame;
import plantVisualization.VisSilo;
import siloGeneric.MHSiloDriver;
import siloGeneric.MHSiloSim;
import siloGeneric.MHSiloSimIf;
import siloGeneric.MixSensorActionListener;
import siloGeneric.MixerTimerIf;
import siloGeneric.TempSensorActionListener;
import system.Command2Plant;

public class MixHeatedSiloMtc extends Mtc implements MixerTimerIf{
	public MHSilo itsController;
	
	public MixHeatedSiloMtc(PlantSimFrame psf,Command2Plant c2p){
		itsVis = new VisSilo(3,200,200, true, new TempSensorActionListener(3), new MixSensorActionListener(3),psf.itsGui);
		psf.itsGui.addMtcVis(3,itsVis);
		itsSim = new MHSiloSim(4,"IN4","OUT4","F4","E4","R4","T4","M4",psf.itsGui.s[3]);
		itsSim.setGuirefs(psf.itsGui.s[3]);
		psf.addMtcSim(3,itsSim);
		
		itsDriver = new MHSiloDriver((MHSiloSimIf) psf.s[3],c2p.c2s[3]);
		itsController = new MHSilo(itsDriver);
	}

	public void mixTimeCompleted() {
		// TODO Auto-generated method stub
		itsDriver.mixerOff();
	}
}
