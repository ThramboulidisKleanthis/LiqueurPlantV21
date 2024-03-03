package plantSimulator;

public class PlantMonitor {
		private boolean run;
		
		public PlantMonitor(){
			run = false;
		}
		
		synchronized public void doUpdate(){
			this.run = true;
			notifyAll();
		}
		
		synchronized void check4Update(){
			while(!run){
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			run = false;
			notifyAll();
		}

	}
