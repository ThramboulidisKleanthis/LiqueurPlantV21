package siloGeneric;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import plantVisualization.PlantsVisFrame;

public class TempSensorActionListener implements ActionListener {
		int id;
		public TempSensorActionListener(int i){
			id =i;
		}
		public void actionPerformed(ActionEvent e){ 
			((siloGeneric.MHSiloSim)(PlantsVisFrame.itsPlant.s[id])).t.trigger();   
			  }
	}