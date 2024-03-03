package guis;
import heatedSilo.HeatedSiloMtc;

import java.awt.*;
import java.awt.event.*;

import mHSilo.MixHeatedSiloMtc;
import mSilo.MixSiloMtc;
import plantSimulator.PlantSimFrame;
import resourcesMtc.PipeMtc;
import resourcesMtc.PowerMtc;
import simpleSilo.SimpleSiloMtc;
import system.LiqueurPlantSystem;
import system.PlantController;

public class OperatorsPanel extends Frame{
	PlantController itsController;
	PlantSimFrame itsPlant;
	//public static PlantsGui plantSimulatorsGui;
	Button pStartButton, pStopButton;
	Button cStartButton, cStopButton;
	Button pAActivateButton;
	Button pBActivateButton;
	Button stopMixingButton;
	Button [] sMtc = new Button[4];
	Button pipe, power;
		
	TextArea textArea;
	
		
	private static final long serialVersionUID = 1L;

	public OperatorsPanel(){
		super("LiqueurPlant V2.1 : Operator's Panel");
		this.setLayout(null);
	   	this.setFont(new Font("TimesRoman", Font.PLAIN, 12));
	   	this.setBackground(Color.white);
		this.setSize(new Dimension(330,500));
		this.setLocation(50,100);      
	   	this.setVisible(true);
	   	this.toFront();            
	   	this.setResizable(false);  
	   	this.addWindowListener(new CloseWindowAndExit());
	   	//RealUnit.setPlantOperatorGui(this);
	   	
	   	// PlantSim GUI
	   	new LpTextField(4, 30, 120, 30, "Visualization Frame", this);
	   	
	   		
	   	pStartButton=new LpButton("START",124, 30, 55, 28,Color.green,Color.white,this);
		pStartButton.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   LiqueurPlantSystem.createPlantSimFrame();
					   pStartButton.setBackground(Color.red);
					   pStopButton.setBackground(Color.green);
				        }
				   }  
				);	
		
		pStopButton=new LpButton("STOP",184, 30, 55, 28,Color.red,Color.white, this);
		pStopButton.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   LiqueurPlantSystem.stopSimulator();
					   pStopButton.setBackground(Color.red);
					   pStartButton.setBackground(Color.green);
				        }
				   }  
				);	
		
		//Controller GUI
		new LpTextField(4, 104, 70, 30, "Controller", this);
		
	   	cStartButton=new LpButton("START",84, 104, 55, 28,Color.green,Color.white,this);
		cStartButton.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   if(LiqueurPlantSystem.createController()){
						   LiqueurPlantSystem.controller.controllerStart();
						   cStartButton.setBackground(Color.red);
					   }
					   //controller = new PlantController(simulator);
					   //simulator.setItsController(controller);
					   //controller.start();
				        }
				   }  
				);	
		
		cStopButton=new LpButton("STOP", 144, 104, 55, 28,Color.green,Color.white, this);
		cStopButton.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //OperatorsPanel.controller.stopPlantController();
					   //System.out.println("STOP deactivated");
					   //plantSimulatorsGui.textArea.append("STOP deactivated\n");
					   report("STOP is deactivated");
				       }
				   }  
				);
		
		//Process LiqA GUI
		new LpTextField(4, 144, 70, 30, "LiqueurA", this);
				
		pAActivateButton=new LpButton("(de)activate", 84, 144, 105, 28,Color.green,Color.white, this);
		pAActivateButton.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){
					   if(itsController == null) return;
					   if(itsController.genLiqueurA.isActive()){
						   itsController.genLiqueurA.stop();
						   pAActivateButton.setBackground(Color.red);
					   }
					   else
					   {
						   itsController.genLiqueurA.activate();
						   pAActivateButton.setBackground(Color.green);
					   }
					   //OperatorsPanel.simulator.start();
				        }
				   }  
				);	
		
	//	lAStopButton=new SimButton("STOP",144, 104, 55, 28,Color.red,Color.white, this );
	//	looplAButton=new SimButton("1Loop",204, 104, 55, 28,Color.YELLOW, Color.black, this );
		
		
		//	Process LiqB GUI
		new LpTextField(4, 184, 70, 30, "LiqueurB", this);
				
		pBActivateButton=new LpButton("(de)activate", 84, 184, 105, 28, Color.green,Color.white, this);
		pBActivateButton.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   	if(itsController == null) return;  
					   	if(itsController.genLiqueurB.isActive()){
							   itsController.genLiqueurB.stop();
							   pBActivateButton.setBackground(Color.red);
						   }
						   else
						   {
							   itsController.genLiqueurB.activate();
							   pBActivateButton.setBackground(Color.green);
						   }
				        }
				   }  
				);	
		stopMixingButton = new LpButton("StopMixing", 200, 164, 105, 28,Color.green,Color.white, this);
		stopMixingButton.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //OperatorsPanel.controller.stopPlantController();
					   //System.out.println("STOP deactivated");
					   //plantSimulatorsGui.textArea.append("STOP deactivated\n");
					   if(LiqueurPlantSystem.itsMixSiloMtc!=null)
						   LiqueurPlantSystem.itsMixSiloMtc.itsController.mixTimeCompleted();
					   if(LiqueurPlantSystem.itsMixHeatedSiloMtc!=null)
						   LiqueurPlantSystem.itsMixHeatedSiloMtc.itsController.mixTimeCompleted();
				       }
				   }  
				);
		
	//	lAStopButton=new Button("STOP",144, 144, 55, 28,Color.red, Color.white, this );
	//	looplBButton=new Button("1Loop",204, 144, 55, 28, Color.YELLOW, Color.black, this );
		
		new LpTextField(4, 64, 80, 30, "Create MTCs", this);
		sMtc[0]=new LpButton("S1",86, 64, 25, 28,Color.green,Color.white,this);
		sMtc[0].addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //LiqueurPlantSystem.plantSim.addMtcS0();
					   LiqueurPlantSystem.itsSimpleSiloMtc = new SimpleSiloMtc(LiqueurPlantSystem.pSimFrame,LiqueurPlantSystem.c2p);
					   sMtc[0].setBackground(Color.red);
				        }
				   }  
				);	
		
		sMtc[1]=new LpButton("S2",114, 64, 25, 28,Color.green,Color.white, this);
		sMtc[1].addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //LiqueurPlantSystem.pSimFrame.addMtcS1();
					   LiqueurPlantSystem.itsHeatedSiloMtc = new HeatedSiloMtc(LiqueurPlantSystem.pSimFrame,LiqueurPlantSystem.c2p);
					   sMtc[1].setBackground(Color.red);
				        }
				   }  
				);	
		sMtc[2]=new LpButton("S3",144, 64, 25, 28,Color.green,Color.white, this);
		sMtc[2].addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //LiqueurPlantSystem.pSimFrame.addMtcS2();
					   LiqueurPlantSystem.itsMixSiloMtc = new MixSiloMtc(LiqueurPlantSystem.pSimFrame,LiqueurPlantSystem.c2p);
					   sMtc[2].setBackground(Color.red);
				        }
				   }  
				);	
		sMtc[3]=new LpButton("S4",174, 64, 25, 28,Color.green,Color.white, this);
		sMtc[3].addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //LiqueurPlantSystem.pSimFrame.addMtcS3();
					   LiqueurPlantSystem.itsMixHeatedSiloMtc = new MixHeatedSiloMtc(LiqueurPlantSystem.pSimFrame,LiqueurPlantSystem.c2p);
					   sMtc[3].setBackground(Color.red);
				        }
				   }  
				);	
		
		pipe=new LpButton("Pipe",204, 64, 40, 28,Color.green,Color.white,this);
		pipe.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //LiqueurPlantSystem.pSimFrame.addPipe();
					   LiqueurPlantSystem.itsPipeMtc = new PipeMtc(LiqueurPlantSystem.pSimFrame,LiqueurPlantSystem.c2p);
					   pipe.setBackground(Color.red);
				        }
				   }  
				);	
		
		power=new LpButton("Power",250, 64, 50, 28,Color.green,Color.white,this);
		power.addActionListener( 
				new ActionListener(){
				   public void actionPerformed(ActionEvent e){ 
					   //LiqueurPlantSystem.pSimFrame.addPower();
					   LiqueurPlantSystem.itsPowerMtc = new PowerMtc(LiqueurPlantSystem.pSimFrame,LiqueurPlantSystem.c2p);
					   power.setBackground(Color.red);
				        }
				   }  
				);	
		
		textArea = new TextArea();
        textArea.setColumns(100);
        textArea.setRows(4);
        textArea.setBounds(10, 220, 300, 270);
        textArea.setBackground(Color.white);
    	this.add(textArea);
    	textArea.setEditable(false);
    	textArea.append("Developed by K.Thramboulidis based on the \ndesign given in the paper\n");
    	textArea.append("\"A Framework for the Implementation of \nIndustrial Automation Systems Based on PLCs\"\nThis is an educational version that\ncontains known bugs.   \n\n");
    	textArea.append("Press only Green buttons\nPress Start to create the Plant Visualization frame\nCreate MechaTronic Component (MTC) instances\nPress Start to start the Controler\n");
    	textArea.append("Press the Active Button to deactivate/activate processes\nPress the StopMixing button to stop Mixing\n");
		}

	public void setSimFrame(PlantSimFrame sim) {
		// TODO Auto-generated method stub
		itsPlant = sim;
	}

	public void setController(PlantController controller2) {
		// TODO Auto-generated method stub
		itsController= controller2;
	}
	
	public void report(String str){
		textArea.append(str+"\n");
	}
}

class CloseWindowAndExit extends WindowAdapter  {
    public void windowClosing (WindowEvent closeWindowAndExit){
      System.exit(0);
     }
}

