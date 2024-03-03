package siloGeneric;


public interface MHSiloSimIf {

	public abstract void mixerOn();

	public abstract void mixerOff();

	public abstract void heaterOn();

	public abstract void heaterOff();

	public abstract void setDriver(MHSiloDriver2DeviceIf mhts);

}