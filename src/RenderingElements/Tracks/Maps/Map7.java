package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Draw.Simulator;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackControllerRequirments;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public class Map7 implements TrackControllerRequirments{
	
	
	
	/*
	 * ESSENTIALS
	 */
	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	private List<Signal> listOfSignals = new ArrayList<>();
		
	private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	private  List<TrackSection> deployMainDownLine = new ArrayList<>();
		

	private  List<Signal> upLineStartSignals = new ArrayList<>();
	private  List<Signal> downLineStartSignals = new ArrayList<>();
	
	
	
	
	//|--------------------------------TRACK OBJECTS--------------------------------------|
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	TrackSection mainUp2 = new TrackSection(trackType.UP , 2 , -1);
	
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	TrackSection mainDown2 = new TrackSection(trackType.DOWN , 2 , -1);
	
	TrackSection loopUp1 = new TrackSection(trackType.UP , 3 , 14);
	TrackSection loopUp2 = new TrackSection(trackType.UP , 4 , 8);
	
	TrackSection loopDown1 = new TrackSection(trackType.DOWN , 3 , 10);
	TrackSection loopDown2 = new TrackSection(trackType.DOWN , 4 , 6);
	
	
	Signal mainUP_1_1 = new Signal(signalType.BLOCK , mainUp1 , null , 2 , 1 , 1);
	//alone
	Signal mainUP_1_2 = new Signal(signalType.HOME , mainUp2 , 3 , 1 , 1 , true);
	
	
	Signal mainUP_2_1 = new Signal(signalType.BLOCK , mainUp2 , null , 1 , 1 , 1);
	
	Signal mainDown_1_1 = new Signal(signalType.BLOCK , mainDown1 , null , 1 , 1 , 1);
	Signal mainDown_2_1 = new Signal(signalType.BLOCK , mainDown2 , null , 1 , 1 , 1);

//|||-------------------------------------UP MAIN LINES Switches---------------------------------------------------------|||
	
	TrackSection switchMainUp1_MainDown1_L_1 = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  4);
	TrackSection switchMainDown1_MainUp1_L_1 = new TrackSection(trackType.UP_START , mainDown1 , mainUp1 ,  2);
	
//|||-------------------------------------UP LOOP LINES Switches---------------------------------------------------------|||
	
	
	TrackSection switchMainUp2_LoopUp1 = new TrackSection(trackType.UP_START , mainUp2 , loopUp1 ,  7);	
	TrackSection switchLoopUp1_LoopUp2 = new TrackSection(trackType.UP_START , loopUp1 ,loopUp2 ,  10);	
	
//|||-------------------------------------LEFT MAIN LINE SIGNALS---------------------------------------------------------|||
	
	Signal signal_MainUp1_MainDown1_L_1_U_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , 1 , -1 , 0);
	Signal signal_MainUp1_MainDown1_L_1_U_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , -1 , -1 , 0);
	Signal signal_MainUp1_MainDown1_L_1_D_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , -1 , 1 , 1);
	Signal signal_MainUp1_MainDown1_L_1_D_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , 1 , 1 , 1);
	
	
	
	Signal signal_MainDown1_MainUp1_L_1_U_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , 1 , -1 , 0);
	Signal signal_MainDown1_MainUp1_L_1_U_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , -1 , -1 , 0);
	Signal signal_MainDown1_MainUp1_L_1_D_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , -1 , 1 , 1);
	Signal signal_MainDown1_MainUp1_L_1_D_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , 1 , 1 , 1);
	
//|||-------------------------------------UP LOOP LINES Signals---------------------------------------------------------|||
	
	Signal signal_mainUp2_loopUp1_U_L = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1 , 1 , -1 , 0);
	Signal signal_mainUp2_loopUp1_U_R = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1 , -1 , -1 , 0);	
	Signal signal_mainUp2_loopUp1_D_R = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1 , -1 , 1 , 1);
	Signal signal_mainUp2_loopUp1_D_L = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1 , 1 , 1 , 1);
	
	
	Signal signal_LoopUp1_LoopUp2_U_L = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2 , 1 , -1 , 0);
	Signal signal_LoopUp1_LoopUp2_U_R = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2 , -1 , -1 , 0);	
	Signal signal_LoopUp1_LoopUp2_D_R = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2 , -1 , 1 , 1);
	Signal signal_LoopUp1_LoopUp2_D_L = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2 , 1 , 1 , 1);
	
	@Override
	public List<TrackSection> getSections() {
		
		
		Collections.addAll(listOfTrackSections , 
				
				
				//main lines 
				mainUp1,
				mainUp2,
				mainDown1,
				mainDown2,
				
				//loop lines 
				loopUp1,
				loopUp2,
				loopDown1,
				loopDown2,
				
				//switches [ IN ORDER ]
				switchMainDown1_MainUp1_L_1,
				switchMainUp1_MainDown1_L_1,
				
				switchMainUp2_LoopUp1,
				switchLoopUp1_LoopUp2
				
				
				
				
				);
		
		
		
		return listOfTrackSections;
		
		
	}
	
	
	@Override
	public List<Signal> getSignals() {
		
		
		
		Collections.addAll(listOfSignals , 
				
				mainUP_1_1,
				mainUP_1_2,

				mainUP_2_1,
				mainDown_1_1,
				mainDown_2_1,
				
				signal_mainUp2_loopUp1_U_L,
				signal_mainUp2_loopUp1_U_R,
				signal_mainUp2_loopUp1_D_L,
				signal_mainUp2_loopUp1_D_R,
				
				signal_MainDown1_MainUp1_L_1_U_L,
				signal_MainDown1_MainUp1_L_1_U_R,
				signal_MainDown1_MainUp1_L_1_D_L,
				signal_MainDown1_MainUp1_L_1_D_R,
				
				signal_MainUp1_MainDown1_L_1_U_L, 
				signal_MainUp1_MainDown1_L_1_U_R, 
				signal_MainUp1_MainDown1_L_1_D_L, 
				signal_MainUp1_MainDown1_L_1_D_R,
				
				signal_LoopUp1_LoopUp2_U_L, 
				signal_LoopUp1_LoopUp2_U_R, 
				signal_LoopUp1_LoopUp2_D_L, 
				signal_LoopUp1_LoopUp2_D_R

				);
		
		
		
		
		return listOfSignals;
	}

	@Override
	public void addListToController()
	{
		

		//|--------------------------------------------------RUN PRIVATE METHODS TO ADD VALUE TO LISTS FOR BOTH SIGNALS AND TRACK SECTIONS------------------------------------------------|

		SimulationController.listOfTrackSections = getSections();
		SimulationController.listOfSignals = getSignals();
		
		
		
		initialiseDeployList();
	}


	@Override
	public void initializeInterlocking() 
	{
		switchMainUp1_MainDown1_L_1.setSignals(
				signal_MainUp1_MainDown1_L_1_U_L, 
				signal_MainUp1_MainDown1_L_1_U_R, 
				signal_MainUp1_MainDown1_L_1_D_L, 
				signal_MainUp1_MainDown1_L_1_D_R);
		
	
		switchMainDown1_MainUp1_L_1.setSignals(
				signal_MainDown1_MainUp1_L_1_U_L,
				signal_MainDown1_MainUp1_L_1_U_R,
				signal_MainDown1_MainUp1_L_1_D_L,
				signal_MainDown1_MainUp1_L_1_D_R);
		
		switchMainUp2_LoopUp1.setSignals(
				signal_mainUp2_loopUp1_U_L, 
				signal_mainUp2_loopUp1_U_R, 
				signal_mainUp2_loopUp1_D_L, 
				signal_mainUp2_loopUp1_D_R);
		
		switchLoopUp1_LoopUp2.setSignals(
				signal_LoopUp1_LoopUp2_U_L, 
				signal_LoopUp1_LoopUp2_U_R, 
				signal_LoopUp1_LoopUp2_D_L, 
				signal_LoopUp1_LoopUp2_D_R);
		
		
		
		
	}


	@Override
	public void initialiseDeployList() {
		
		Collections.addAll(deployMainUpLine, 
				
				mainUp1,
				mainUp2);
		
		
		
		Collections.addAll(deployMainDownLine, 
				
				mainDown1,
				mainDown2);
		
		
		
		Collections.addAll(upLineStartSignals, 
				
				mainUP_1_1,
				mainUP_2_1
				);
		
		
		
		Collections.addAll(downLineStartSignals, 
				
				mainDown_1_1,
				mainDown_2_1);
		
		SimulationController.deployMainUpLine = deployMainUpLine;
		SimulationController.deployMainDownLine = deployMainDownLine;
		SimulationController.upLineStartSignals = upLineStartSignals;
		SimulationController.downLineStartSignals = downLineStartSignals;
		
	}


	@Override
	public void createStation() {
		
		Simulator.stationGraphicVerticalPos = 1;
		Simulator.stationGraphicHorizontalPos = 2;
		
	}


	@Override
	public void setGrid() {
		
		Simulator.MAX_HORIZONTAL_ELM = 29;
		
	}

	

	

	

}
