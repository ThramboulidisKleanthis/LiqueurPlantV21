package mtcGeneric;

public interface ControllerIf {

	public abstract void setProcessCtrlDevicePort(
			processControllers.Process2SiloIf port);
	public void updateState();

}