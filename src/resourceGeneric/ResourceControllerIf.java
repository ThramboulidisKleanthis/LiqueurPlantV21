package resourceGeneric;

public interface ResourceControllerIf {

	public abstract boolean acquire();

	public abstract void release();
	public boolean isAvailable();

}