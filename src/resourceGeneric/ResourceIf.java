package resourceGeneric;

public interface ResourceIf {
	public boolean acquire();
	public void release();
	public Boolean available();
	}
