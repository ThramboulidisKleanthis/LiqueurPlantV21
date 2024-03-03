package plantVisualization;

import guis.LpButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisSilo extends LpButton{
	private static final long serialVersionUID = 1L;
	
	public LpButton f,e,in,out;
	public LpButton r,t;
	public LpButton m;
			
	public VisSilo(int n, int x, int y, boolean r,ActionListener tal, ActionListener mal, PlantsVisFrame pg) {
		super("S"+(n+1),50+x+400, 50+y, 70, 120,Color.white,Color.black, pg);
				
		f=new LpButton("F",x+420, 60+y, 30, 20,Color.PINK,Color.black,pg);
		f.addActionListener(new FSensorButtonHandler(n)); 
				
		e=new LpButton("E",x+420, 140+y, 30, 20,Color.pink,Color.black,pg);
		e.addActionListener(new ESensorButtonHandler(n)); 
						
		in=new LpButton("", 70+x+400, 30+y, 20, 20,Color.red,Color.black,pg);
		out=new LpButton("", 70+x+400, 30+140+y, 20, 20,Color.red,Color.black,pg);
		
	if(r) 
		this.r=new LpButton("R4",120+x+400, 30+y+60, 40, 20,Color.green,Color.white,pg);
	if(tal!=null){
		t=new LpButton("T",120+x+400, 30+y+40, 20, 20,Color.white,Color.black,pg );
		t.addActionListener( tal);
		}

	if(mal!=null){
		m=new LpButton("M",120+x+400, 30+y+90, 30, 40,Color.white,Color.black,pg);
		m.addActionListener( mal);
		}
	
	}
	
	
	class ESensorButtonHandler implements ActionListener {
		int siloNo;
		public ESensorButtonHandler(int siloNo) {
			// TODO Auto-generated constructor stub
			this.siloNo = siloNo;
		}
		public void actionPerformed(ActionEvent pushingButton0){ 
			// disabled since timers were introduced
			//PlantSimulatorsGui.plant.s[siloNo].e.trigger();
			//PlantSimulatorsGui.s[siloNo].setBackground(Color.white);
			}
		}	
	
	class FSensorButtonHandler implements ActionListener {
		int siloNo;
		public FSensorButtonHandler(int siloNo) {
			// TODO Auto-generated constructor stub
			this.siloNo = siloNo;
		}
		public void actionPerformed(ActionEvent pushingButton0){ 
			// disabled since timers were introduced
			//PlantSimulatorsGui.plant.s[siloNo].f.trigger();
			//PlantSimulatorsGui.s[siloNo].setBackground(Color.cyan);
			}
		}	

}