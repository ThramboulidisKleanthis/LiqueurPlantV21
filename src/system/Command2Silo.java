package system;

public class Command2Silo {
	public boolean openInValve = false;
	public boolean closeInValve = false;
	public boolean openOutValve = false;
	public boolean closeOutValve = false;
	
	public boolean heaterOn=false;
	public boolean stopHeater=false;
	
	public boolean mixerOn=false;
	public boolean stopMixer=false;
	
	public boolean updateVisual=false;
	
	public boolean commandExists(){
		if(openOutValve || closeOutValve || openInValve || closeInValve || mixerOn || stopMixer || heaterOn || stopHeater)
			return true;
		else return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		openInValve = false;
		 closeInValve = false;
		 openOutValve = false;
		 closeOutValve = false;
		 heaterOn=false;
		 stopHeater=false;
		 mixerOn=false;
		stopMixer=false;
		updateVisual=false;
	}

	
}
