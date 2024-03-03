package plantVisualization;
//import java.awt.Button;
import guis.LpButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
//import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import plantSimulator.PlantSimFrame;
//import Sim.Mixer;
// Sim.Resistor;
//import Sim.VSilo;
//import Sim.Valve;
import system.PlantController;



public class PlantsVisFrame extends Frame{
	public static PlantController pc;
	public static  PlantSimFrame itsPlant;
	public VisSilo [] s = new VisSilo[4];
	public LpButton[] cr = new LpButton[2];
	public static TextArea textArea;
				
	private static final long serialVersionUID = 1L;

	public PlantsVisFrame(PlantSimFrame plant){
		super("LiqueurPlant v2.1 : Visualization Frame");
		//PlantSimulatorsGui.pc = pc;
		PlantsVisFrame.itsPlant = plant;
		this.setLayout(null);
	   	this.setFont(new Font("TimesRoman", Font.PLAIN, 12));
	   	this.setBackground(Color.white);
		this.setSize(new Dimension(800,450));
		this.setLocation(390,200);      
	   	this.setVisible(true);
	   	this.toFront();            
	   	this.setResizable(false);  
	   	this.addWindowListener(new CloseWindowAndExit());
	   	   	
		
		textArea = new TextArea();
        textArea.setColumns(100);
        textArea.setRows(2);
        textArea.setBounds(10, 30, 380, 410);
    	this.add(textArea);
    	report("Instantiate Cyber-Physical components (CPCs) or MTCs.\nActivate the Controller and then \n"
    			+ "Press cyan T button to triger Temp sensor \nPress StopMixing at Operator's panel to terminate mixing\n\n");    	
    	    	
    	
	}
    		
	
/*
	class TempSensorActionListener implements ActionListener {
		int id;
		public TempSensorActionListener(int i){
			id =i;
		}
		public void actionPerformed(ActionEvent e){ 
			((siloGeneric.MHSiloSim)(PlantsVisFrame.itsPlant.s[id])).t.trigger();   
			  }
	}
	class MixSensorActionListener implements ActionListener {
		int id;
		public MixSensorActionListener(int i){
			id =i;
		}
		public void actionPerformed(ActionEvent e){ 
			((siloGeneric.MHSiloSim)(PlantsVisFrame.itsPlant.s[id])).m.turnOff();
			  }
	}
		*/

	class CloseWindowAndExit extends WindowAdapter  {
	    public void windowClosing (WindowEvent closeWindowAndExit){
	      System.exit(0);
	     }
	}

		
	public static void report(String message) {
		textArea.append(message+"\n");
		}

	public void addMtcVis(int i, VisSilo itsVis) {
		// TODO Auto-generated method stub
		s[i]=itsVis;
	}

	public void addResVis(int i, LpButton itsVis) {
		// TODO Auto-generated method stub
		cr[i]=itsVis;
	}
	/*public void addMtcS0() {
		// TODO Auto-generated method stub
		s[0] = new VisSilo(0,0,0, false, null, null,this);
	}
	public void addMtcS1() {
		s[1] = new VisSilo(1,200,0, true, new TempSensorActionListener(1), null,this);
	}
	public void addMtcS2() {
		s[2] = new VisSilo(2,0,200, false, null, new MixSensorActionListener(2),this);
	}
	public void addMtcS3() {
		s[3] = new VisSilo(3,200,200, true, new TempSensorActionListener(3), new MixSensorActionListener(3),this);
	}


	public void Pipe() {
		// TODO Auto-generated method stub
		cr[0]=new LpButton("Pipe",50+400, 50+140, 270, 40,Color.WHITE,Color.BLACK,this);
		
	}


	public void Power() {
		// TODO Auto-generated method stub
		cr[1]=new LpButton("Power",740, 180, 50, 65,Color.WHITE,Color.black,this);
	}
*/

	
}




	