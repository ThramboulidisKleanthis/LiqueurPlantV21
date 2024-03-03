package resourceGeneric;

import mtcGeneric.Controller;

public class ResourceController extends Controller implements ResourceController2DriverIf, ResourceControllerIf {
	public ResourceDriver2ControllerIf itsDriver;
	boolean available=true;
	
	public ResourceController(ResourceDriver2ControllerIf dr){
		super(dr);
		itsDriver = dr;
		}
	
	@Override
	public boolean isAvailable(){
		return available;
	}
	
	/* (non-Javadoc)
	 * @see deviceControllers.ResourceControllerIf#acquire()
	 */
	@Override
	public boolean acquire() {
		// TODO Auto-generated method stub
		if(available){
			itsDriver.setAcquired();
			available = false;
			return true;
			}
		else
			return false;
	}
		
	/* (non-Javadoc)
	 * @see deviceControllers.ResourceControllerIf#release()
	 */
	@Override
	public void release(){
		itsDriver.setAvailable();
		available = true;
	}
}
