package system;

import java.util.TimerTask;

class controllerTimerTask extends TimerTask {
	public controllerTimerTask(){
		super();
	}
	public void run() {
    	LiqueurPlantSystem.controller.execute();
    	LiqueurPlantSystem.pm.doUpdate();
    }
}  
