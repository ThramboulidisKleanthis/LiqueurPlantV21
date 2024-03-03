package processControllers;

import system.PlantController;

public abstract class Process implements ProcessIf{
	String name;
	protected PlantController itsController;
	Boolean active=false;
	Boolean completed = true;
	Boolean stopPending=false;
	boolean urgentStop;
	protected boolean resAvailable = false;
	
	public Process(String str,PlantController ctrl){
		name = str;	
		itsController = ctrl;
	}
	
	public void setResourcesAvailable() {
		// TODO Auto-generated method stub
		resAvailable = true;
	}
	abstract public void activate();
	
	public boolean isActive() {
		return (active && resAvailable) ;
	}
	public void stop() {
		stopPending = true;
		processPendingStop();
	}
	
	public void urgentStop() {
		this.deActivate();
	}
	
	public void reset(){
		active = true;
		completed = false;
		stopPending=false;
	}

	void processPendingStop(){
		if(this.completed && stopPending)
			this.deActivate();
	}
	
	void deActivate(){
		active = false;
	}
		
}
