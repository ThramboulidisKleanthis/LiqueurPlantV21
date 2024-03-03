package siloGeneric;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import mtcGeneric.Driver2DeviceIf;
import guis.LpButton;
import plantSimulator.Device;
import plantSimulator.actuators.Mixer;
import plantSimulator.actuators.Resistor;
import plantSimulator.actuators.Valve;
import plantSimulator.sensors.EmptyingLevelSensor;
import plantSimulator.sensors.FillingLevelSensor;
import plantSimulator.sensors.TempSensor;
import plantVisualization.PlantsVisFrame;
import plantVisualization.VisSilo;
import system.Command2Silo;

public class MHSiloSim extends Device implements MHSiloSimIf {
	public MHSiloDriver2DeviceIf itsDriver;
	
	int id;
	Valve in;
	Valve out;
	FillingLevelSensor f;
	EmptyingLevelSensor e;
	public Mixer m;
	Resistor r;
	public TempSensor t;
	
	Timer []fillingTimer = new Timer[4]; 
	Timer []pouringTimer=new Timer[4];
	private int []fillingTime = {5000,6000,7000,6000};
	private int []pouringTime = {6000,7000,8000,9000}; 
	
	//private boolean filling, pouring, full, empty;	
	private MHSiloSimState cState= MHSiloSimState.EMPTY;
	
	public MHSiloSim(int id, String inValve, String outValve, String fSensor, String eSensor, String r, String t, String m,LpButton itsGui){
		super("S"+id);
		this.id = id;
		in  = new Valve(inValve);
		in.setSilo(this);
		out  = new Valve(outValve);
		out.setSilo(this);
		f = new FillingLevelSensor(fSensor,this);
		//f.setSilo(this);
		e = new EmptyingLevelSensor(eSensor,this);
		//e.setSilo(this);
		this.itsGui=itsGui;
		itsDriver = (MHSiloDriver2DeviceIf)(super.itsDriver);
		
		this.m = new Mixer(m);
		this.m.setSilo(this);
		this.r = new Resistor(r);
		this.r.setSilo(this);
		this.t = new TempSensor(t,this);
		this.t.setSilo(this);
		}

	public void setDriver(siloGeneric.MHSiloDriver2DeviceIf msif) {
		// TODO Auto-generated method stub
		itsDriver = msif;
		super.itsDriver = (Driver2DeviceIf)msif;
	}
	
	public void setGuirefs(VisSilo s){ //, Button in, Button out, Button r, Button t, Button m) {
		itsGui = s;
		this.in.itsGui = s.in;
		this.out.itsGui = s.out;
		this.r.itsGui = s.r;
		this.t.itsGui = s.t;
		this.m.itsGui = s.m;
	}
	
	public void dispatchCommand(Command2Silo c2s){
		if (!c2s.commandExists()) return;
		
		if(c2s.openInValve) 
			if(cState==MHSiloSimState.EMPTY){
				//openInValve();
				in.open();
				cState=MHSiloSimState.FILLING;
				setVis2Filling();
				fillingTimer[id-1] = new Timer();
				fillingTimer[id-1].schedule(new FillingTimerTask(this.id), fillingTime[id-1]);
			}
		if(c2s.closeInValve){ 
			//if(cState==MHSiloSimState.FILLING){
				//closeInValve();
				in.close();
				setVis2Full();
				cState=MHSiloSimState.FULL;
			}
		if(c2s.openOutValve) 
			if(cState==MHSiloSimState.FULL){
				//openOutValve();
				out.open();
				cState=MHSiloSimState.POURING;
				setVis2Pouring();
				pouringTimer[id-1] = new Timer();
				pouringTimer[id-1].schedule(new PouringTimerTask(this.id), pouringTime[id-1]);
			}
		
		if(c2s.closeOutValve) {
			//if(cState==MHSiloSimState.POURING){
				//closeOutValve();
				out.close();
				cState=MHSiloSimState.EMPTY;
				setVis2Empty();
			}
		if(c2s.mixerOn){ 
			//if(cState==MHSiloSimState.FULL){
				mixerOn();
				cState=MHSiloSimState.FULL;
			}
		if(c2s.stopMixer) {
			//if(cState==MHSiloSimState.MIXING){
				mixerOff();
				cState=MHSiloSimState.FULL;
			}
		if(c2s.heaterOn){ 
			//if(cState==MHSiloSimState.FULL){
				heaterOn();
				cState=MHSiloSimState.FULL;
			}
		if(c2s.stopHeater){ 
			//if(cState==MHSiloSimState.HEATING){
				heaterOff();
				cState=MHSiloSimState.FULL;
			}
		//update();
	}

	@Override
	public void mixerOn() {
		// TODO Auto-generated method stub
		m.turnOn();
		PlantsVisFrame.report("s" +this.id +" Mixing");
		
	}

	/* (non-Javadoc)
	 * @see plantSimulator.MixedHeatedSiloIf#mixerOff()
	 */
	@Override
	public void mixerOff() {
		// TODO Auto-generated method stub
		m.turnOff();
		PlantsVisFrame.report("s" +this.id +" Mixing terminated");
	}

	/* (non-Javadoc)
	 * @see plantSimulator.MixedHeatedSiloIf#heaterOn()
	 */
	@Override
	public void heaterOn() {
		// TODO Auto-generated method stub
		r.turnOn();
		PlantsVisFrame.report("s" +this.id +" Heating");
		
	}

	/* (non-Javadoc)
	 * @see plantSimulator.MixedHeatedSiloIf#heaterOff()
	 */
	@Override
	public void heaterOff() {
		// TODO Auto-generated method stub
		r.turnOff();
		PlantsVisFrame.report("s" +this.id +" Heating terminated");
	}

	private void setVis2Filling(){
		itsGui.setBackground(Color.cyan);
	}
	
	private void setVis2Full(){
		itsGui.setBackground(Color.blue);
	}	
	
	private void setVis2Pouring(){
		itsGui.setBackground(Color.LIGHT_GRAY);
	}
	
	private void setVis2Empty(){
		itsGui.setBackground(Color.white);
	}

	class FillingTimerTask extends TimerTask {
		private int siloId;
		public FillingTimerTask(int id){
			this.siloId = id;
		}
        public void run() {
        	PlantsVisFrame.itsPlant.s[siloId-1].f.trigger();
        	fillingTimer[siloId-1].cancel(); //Terminate the timer thread
        }
	}  
	
	class PouringTimerTask extends TimerTask {
 		private int siloId;
 		public PouringTimerTask(int id){
 			this.siloId = id;
 		}
         public void run() {
         	PlantsVisFrame.itsPlant.s[siloId-1].e.trigger();
         	pouringTimer[siloId-1].cancel(); //Terminate the timer thread
         }
	}
	
}
