//[7May] V21_1. Check the comments on the interaction between controller and Sim. 
// [9May] Controller state is updated on the begining of the next
// scan cycle so the command from the process is send to the controller on the next cycle.  Several other bugs were fixed. However. when I
// introduce the strict statemachene at sim there is problem. So I reverted to the lose statemachine. Checked ok 10May 1:20. Frozen
//[2May] V21. Start the work towards Mechatronic components. Rearrange of packages. MTCs included and created from operators panel. Checked ok. Copy.
// (b)  Update controller to be compliant with changes. checked ok [4May 00:11]. Frozen. A bug was found. SiloSim
// was reconstructed in dispatch. problems with HEATING and MIXING states since two commands may received in one run. So 
// checking for these states was removed. checked ok. backup as c. Frozen. see my comments on the strict statemachine implementation of MHSiloSim in evolutionHistory_4.
// [6May] a few modifications on visual parts (guis) for generating the distribution version. Copy d. Frozen
//[2May] V20_4. Start the work towards a distributed implementation. a) Make the simulator Thread. ok checked.get copy. b) Prepare the execution of simulator to a different machine. Change 
// the way drivers interact with the sim. Command2Plant was introduced and is updated by drivers. PlantSimulator is updated based on Command2Plant. 
// a bug on reports was fixed- it was always s4. checked ok Copy b created. Frozen.
//[27Apr] V20_3. Implement the concept of port as described in ETFA paper.
// it was implemented with Port definition as independent class. Checked ok. Get a copy of _3. Continue with making Port class 
// nested. errors ... [28Apr] Errors fixed.Ports are implemented with nested classes (not in the best way). Checked ok operating. Get a copy as V20_3b. Frozen.  
//[11Apr] V20_2 Refactoring of sim to be ready for the 3+1 approach. Introduce Vsilo with all its devices. Mixer and TempSensor restructuring.
// ok checked. frozen.
// V20_1. Minor modifications in interfaces to meet system level specification e.g. Fill and not startFilling
//[9Apr] V18_4 renamed to V20 and released as V2.0 
//[9Apr] V18_4. Refinement of the design based on the UML model. Device Drivers ok.Controllers ok. Mixer bug was fixed. ckecked ok. Frozen
//[8Apr] V18_3. The back of using the pipe was addressed by restructuring the execute and removing a back from releasing the pipe. 
// it was relesed 2 times. One form the GenLiqueur and one from the controller. Buttons T and M are active when required.  
//[7Apr] V18_2. Resource has been modified to have the same behavior as drivers with read, write and update. ok V18_2 frozen.
//V18_1 Start of modifications based on the UML diagrams created. Re-engineering of Device Drivers package. The handling of Resource should be moved to
// controller level. ok checked. _1 frozen. Known bug at some time both processes take the pipe.
//[6Apr] Continue modifications as reported in EvolutionHistory. A bug exist 
//[5Apr] V18. Check interfaces and improve modularity. Separate SimulatorGuis from Simulator. It is the PlantVisualization.
// Rename of packages. Check attributes for proper visibility.
//[4Apr] V17 major modifications working on Ver16. V16_X are intermediate versions on this process.
// Silos in simulator all become MixedHeatedSilo, all drivers have become MixedHeatedSilo. Only controllers remain
// of different type. Mixer button handler is directly turning off the mixer. On the other side the TempSensor reports tempReached 
// and the controller manages to turn off the Heater. Heated and Mixed Silos from sim and drivers were removed. Checked ok.
//[3Apr] modifications on V16. VSilo was replaced by SimButton. Resource driver was connected with Resource Unit. Several improvements in gui refs update. Mixer has
//been improved but still the button handler is directly referring to the driver. 
//Still missing: A clear interface between the controller and the Plant. 
//[2Apr] V16. Decouple simulator from controller. It has been decoupled but there was an error (out valve of s1 and s2 did not close). Since the 
// reason was not found I decided to exclude the AutoFilling of S1 and s2 which was also incompatible with cycle execution and start it
// by the GenLiquid Processes. The problem was fixed by paralelezing filling with timers. But it appears that pipe is not green some time when used. 
//V15. Modify to periodically execute the controller by a timer. checked ok.
// V14. Correct the bug of not showing the filling of S1 and S2. There is still a known bug. THe start of S2 heating is postponed
// after the termination of the process. Checked ok.
//[1Apr] V13 start to removing timers to the simulator level. Fixing many bugs many to interdependencies between non adjacent
// layers. Completed checked ok
//V12 Comment: Timers were introduced for auto filling and pouring. However, they have been introduced in a wrong place (not in simulator).
//[31Mar] V12 Timers were introduced to automate filling and pouring. Tested ok. http://enos.itcollege.ee/~jpoial/docs/tutorial/essential/threads/timer.html
//V11. Minor modifications. Check for unexpected event was implemented (e.g. E sensor activation of S3 during Mixing) Urgent termination introduced. See 
// deadlock due to not release of the common resource
//[30Mar2014] V10 Start of adding a schematic Process simulator. I used as guide the LPSimV2. I have just expanded the frame and appended the visual sim. I came with the idea 
// of modularity in the simulator. A re-engineering is required to favor the replacement of text based silo with a visual one. this wiil 
// be done in a new version. Work finished on 30Mar2014. Version Closed.
//[2Jan14] Start of V7. Applying the Scan cycle based control. Read was implemented and checked ok.
//[3Jan14] write was also implemented and checked ok.
//[2Jan2014] V6. I have implemented the concept of Port in Process (see GenLiquidDevicePortIf and devicePort in GenLiquid A and B)
// sensors are triggered by the plantController and it is this thread that executes the behavior up to the device controller level. THis is not compliant with scan based.
//[22Dec] Version 6. Start of Modifications on Intarfaces to be compliant with the figure of 19/12. Checked down to the Driver  
//[22Dec] Version 5. Several minor corrections/modifications. TextArea was used for log. 
//Version 5. Major revision. Apply the 1131 cycle model. It was found that it is almost applied so there is no reason for this.
// Wait for further improvements.
//Version4. just a copy of V3 with the intention to do the following Action: Improvements for better use 
//of provided and required interfaces  Start modifications on 5Dec 17:10 Reorganization of packages and some minor modifications. 
// Major change in Operators Buttons. Insert functionality to activate and deactivate Liquid generation processes
// De-activation is pending until the completion of the current production process. Archive on 6Dec 18:15
//VersionV3 start modifications on 4Dec. Action: Separate the Simulator from the Controller. ok Done 5Dec 00:15. Version Archived.
//3/12 Version separation of operator interface from the simulator interface. Renaming of Packages and some classes to 
// better much the plant simulator concept. From Operators panel only START of Plant and Controller have been checked
// [1/12] this version is a modification of the fully manual one so as to run the PlantController in a loop (Thread is used) 1/12/2013

// Known Bugs
//[6Apr] liquid transfer of B and then during the transfer of A all valves are open.

package system;

import mHSilo.MixHeatedSiloMtc;
import mSilo.MixSiloMtc;
import guis.OperatorsPanel;
import heatedSilo.HeatedSiloMtc;
import plantSimulator.PlantSimFrame;
import plantSimulator.PlantMonitor;
import plantVisualization.PlantsVisFrame;
import resourcesMtc.PipeMtc;
import resourcesMtc.PowerMtc;
import simpleSilo.SimpleSiloMtc;

public class LiqueurPlantSystem {
	public static PlantsVisFrame pVisFrame;
	public static PlantSimFrame pSimFrame;
	public static PlantController controller;
	public static OperatorsPanel opPanel;
	public static PlantMonitor pm;
	public static Command2Plant c2p;
	public static SimpleSiloMtc itsSimpleSiloMtc;
	public static HeatedSiloMtc itsHeatedSiloMtc;
	public static MixSiloMtc itsMixSiloMtc;
	public static MixHeatedSiloMtc itsMixHeatedSiloMtc;
	public static PipeMtc itsPipeMtc;
	public static PowerMtc itsPowerMtc;
	
	public static void main(String[] args) {
		opPanel = new OperatorsPanel();
		c2p= new Command2Plant();
	}

	public static void createPlantSimFrame(){
		if(pSimFrame== null){
			pm = new PlantMonitor();
			pSimFrame = new PlantSimFrame(pm,c2p);
			pVisFrame = new PlantsVisFrame(pSimFrame);
			pSimFrame.setPlantVisFrame(pVisFrame);
			opPanel.setSimFrame(pSimFrame);
			pSimFrame.start();
		}
	}
	
	public static boolean createController(){
		if(pSimFrame== null){
			opPanel.report("Start Plant");
			return false;
		}
		if(controller== null){
			controller = new PlantController(pSimFrame,opPanel,c2p,itsSimpleSiloMtc,itsHeatedSiloMtc,itsMixSiloMtc,itsMixHeatedSiloMtc,itsPipeMtc, itsPowerMtc);
			opPanel.setController(controller);
			pSimFrame.setItsController(controller);
			PlantsVisFrame.pc = controller;
			return true;
		}
		return false;
	
	}

	public static void stopSimulator() {
		// TODO Auto-generated method stub
		pSimFrame.itsGui.setVisible(false);
		pSimFrame = null;
	}
	
	
}

