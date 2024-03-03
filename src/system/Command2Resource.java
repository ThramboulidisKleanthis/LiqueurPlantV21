package system;

public class Command2Resource {
	public boolean setAvailable = false;
	public boolean setAcquired = false;
	
	public boolean commandExists(){
		if(setAvailable || setAcquired )
			return true;
		else return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		setAvailable = false;
		setAcquired = false;
	}
}
