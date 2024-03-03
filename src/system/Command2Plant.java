package system;

public class Command2Plant {
	public Command2Silo [] c2s;
	public Command2Resource [] c2r;
	
	public Command2Plant(){
		c2s = new Command2Silo[4];
		for(int i=0;i<4;i++) 
			c2s[i] = new Command2Silo();
		c2r = new Command2Resource[2];
		c2r[0] = new Command2Resource();
		c2r[1] = new Command2Resource();
	}
	
	public boolean commandExists(){
		if(c2s[0].commandExists()||c2s[1].commandExists() || c2s[2].commandExists()|| c2s[3].commandExists() || c2r[0].commandExists()||c2r[1].commandExists() )
			return true;
		else
			return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		for(int i=0;i<4;i++)
			c2s[i].reset();
		c2r[0].reset();
		c2r[1].reset();
	}
}
