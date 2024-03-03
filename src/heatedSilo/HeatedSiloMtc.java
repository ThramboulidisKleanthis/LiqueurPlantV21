package heatedSilo;

import mtcGeneric.Mtc;
import plantSimulator.PlantSimFrame;
import plantVisualization.VisSilo;
import siloGeneric.MHSiloDriver;
import siloGeneric.MHSiloSim;
import siloGeneric.MHSiloSimIf;
import siloGeneric.TempSensorActionListener;
import system.Command2Plant;

public class HeatedSiloMtc extends Mtc {
	public HSilo itsController;
	
	
	public HeatedSiloMtc(PlantSimFrame psf,Command2Plant c2p){
		itsVis = new VisSilo(1,200,0, true, new TempSensorActionListener(1), null,psf.itsGui);
		psf.itsGui.addMtcVis(1,itsVis);
		itsSim = new MHSiloSim(2,"IN2","OUT2","F2","E2","R2","T2","M2",psf.itsGui.s[1]);
		itsSim.setGuirefs(psf.itsGui.s[1]);
		psf.addMtcSim(1,itsSim);
		
		itsDriver = new MHSiloDriver((MHSiloSimIf) psf.s[1],c2p.c2s[1]);
		itsController = new HSilo(itsDriver);
		
		}
	
	
}
