package mtcGeneric;

import processControllers.Process2SiloIf;
import simpleSilo.SimpleSilo.ProcessPort;

public class Controller implements ControllerIf, Controller2DriverIf  {
	protected Driver2ControllerIf itsDriver;
	Process2SiloIf itsProcessCtrlPort;
	public ProcessPort itsProcessPort; 
	protected boolean updateState = false;
	
	public Controller(Driver2ControllerIf dr){
		itsDriver = dr;
		}
	
	/* (non-Javadoc)
	 * @see deviceControllers.ControllerIf#setProcessCtrlDevicePort(processControllers.GenLiquidDevicePortIf)
	 */
	@Override
	public void setProcessCtrlDevicePort(processControllers.Process2SiloIf port){
		itsProcessCtrlPort = port;
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		// will be defined by subclasses
		System.out.println("shou;d be defined by subclasses");
	}
	
	
}
