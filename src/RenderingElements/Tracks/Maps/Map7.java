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
	
	
	Signal mainUP_1_1 = new Signal(signalType.BLOCK , mainUp1 , null , 1 , 1 , 1);
	
	
	Signal mainDown_1_1 = new Signal(signalType.BLOCK , mainDown1 , null , 1 , 1 , 1);
	

//|||-------------------------------------UP MAIN LINES Switches---------------------------------------------------------|||
	
	TrackSection switchMainUp2_MainUp1_L_1 = new TrackSection(trackType.DOWN_START , mainUp2 , mainUp1 ,  1);
	
	TrackSection switchMainUp1_MainUp2_L_1 = new TrackSection(trackType.UP_START , mainUp1 , mainUp2 ,  5);
	TrackSection switchMainUp1_MainUp2_L_2 = new TrackSection(trackType.UP_START , mainUp1 , mainUp2 ,  8);
	
	TrackSection switchMainUp1_MainDown1_L_1 = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  3);
	TrackSection switchMainUp1_MainDown1_L_2 = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  10);
	
	TrackSection switchMainDown1_MainUp1_L_1 = new TrackSection(trackType.UP_START , mainDown1 , mainUp1 ,  18);
	TrackSection switchMainDown1_MainUp1_L_2 = new TrackSection(trackType.UP_START , mainDown1 , mainUp1 ,  6);
	
	
	//-------RIGHT SWITHCES 
	
	TrackSection switchMainUp2_MainUp1_R_1 = new TrackSection(trackType.UP_END , mainUp2 , mainUp1 ,  24);
	
		
//|||-------------------------------------UP LOOP LINES Switches---------------------------------------------------------|||
	
	
	TrackSection switchMainUp2_LoopUp1_1 = new TrackSection(trackType.UP_START , mainUp2 , loopUp1 ,  7);	
	TrackSection switchMainUp2_LoopUp1_2 = new TrackSection(trackType.UP_START , mainUp2 , loopUp1 ,  10 , true);
	
	TrackSection switchLoopUp1_LoopUp2_1 = new TrackSection(trackType.UP_START , loopUp1 ,loopUp2 ,  10);
	TrackSection switchLoopUp1_LoopUp2_2 = new TrackSection(trackType.UP_START , loopUp1 ,loopUp2 ,  12 , true);
	
	
//|||-------------------------------------DOWN MAIN LINES Switches---------------------------------------------------------|||
	
	TrackSection switchMainDown1_MainDown2_L_1 = new TrackSection(trackType.DOWN_START , mainDown1 , mainDown2 ,  7);
	TrackSection switchMainDown1_MainDown2_L_2 = new TrackSection(trackType.DOWN_START , mainDown1 , mainDown2 ,  5);
	
	TrackSection switchMainDown2_MainDown1_L_1 = new TrackSection(trackType.DOWN_START , mainDown2 , mainDown1 ,  1);

//|||-------------------------------------DOWN LOOP LINES Switches---------------------------------------------------------|||
	
	TrackSection switchMainDown2_LoopDown1_1 = new TrackSection(trackType.DOWN_START , mainDown2 , loopDown1 ,  9);
	TrackSection switchLoopDown1_LoopDown2_1 = new TrackSection(trackType.DOWN_START , loopDown1 , loopDown2 ,  11 , true);
	
	
	
	
	
	
//|||-------------------------------------LEFT MAIN LINE SIGNALS---------------------------------------------------------|||
	
	
	//Up-Up interaction
	Signal signal_MainUp2_MainUp1_L_1_U_L = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_L_1 , 1 , 1 , 0);
	Signal signal_MainUp2_MainUp1_L_1_U_R = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_L_1 , -1 , 1 , 0);
	Signal signal_MainUp2_MainUp1_L_1_D_R = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_L_1 , -1 , -1 , 1);
	Signal signal_MainUp2_MainUp1_L_1_D_L = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_L_1 , 1 , -1 , 1);
	
	Signal signal_MainUp1_MainUp2_L_1_U_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_1 , 1 , -1 , 0);
	Signal signal_MainUp1_MainUp2_L_1_U_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_1 , -1 , -1 , 0);
	Signal signal_MainUp1_MainUp2_L_1_D_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_1 , -1 , 1 , 1);
	Signal signal_MainUp1_MainUp2_L_1_D_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_1 , 1 , 1 , 1);
	
	Signal signal_MainUp1_MainUp2_L_2_U_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_2 , 1 , -1 , 0);
	Signal signal_MainUp1_MainUp2_L_2_U_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_2 , -1 , -1 , 0);
	Signal signal_MainUp1_MainUp2_L_2_D_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_2 , -1 , 1 , 1);
	Signal signal_MainUp1_MainUp2_L_2_D_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainUp2_L_2 , 1 , 1 , 1);
	
	//-----RIGHT---------
	
	Signal signal_MainUp2_MainUp1_R_1_U_L = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_R_1 , 1 , 1 , 0);
	Signal signal_MainUp2_MainUp1_R_1_U_R = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_R_1 , -1 , 1 , 0);
	Signal signal_MainUp2_MainUp1_R_1_D_R = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_R_1 , -1 , -1 , 1);
	Signal signal_MainUp2_MainUp1_R_1_D_L = new Signal(Signal.signalType.HOME , switchMainUp2_MainUp1_R_1 , 1 , -1 , 1);
	
	
	//Up-Down interaction
	Signal signal_MainUp1_MainDown1_L_1_U_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , 1 , 1 , 0);
	Signal signal_MainUp1_MainDown1_L_1_U_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , -1 , 1 , 0);
	Signal signal_MainUp1_MainDown1_L_1_D_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , -1 , -1 , 1);
	Signal signal_MainUp1_MainDown1_L_1_D_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_1 , 1 , -1 , 1);
	
	Signal signal_MainUp1_MainDown1_L_2_U_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_2 , 1 , 1 , 0);
	Signal signal_MainUp1_MainDown1_L_2_U_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_2 , -1 , 1 , 0);
	Signal signal_MainUp1_MainDown1_L_2_D_R = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_2 , -1 , -1 , 1);
	Signal signal_MainUp1_MainDown1_L_2_D_L = new Signal(Signal.signalType.HOME , switchMainUp1_MainDown1_L_2 , 1 , -1 , 1);
	
	Signal signal_MainDown1_MainUp1_L_1_U_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , 1 , -1 , 0);
	Signal signal_MainDown1_MainUp1_L_1_U_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , -1 , -1 , 0);
	Signal signal_MainDown1_MainUp1_L_1_D_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , -1 , 1 , 1);
	Signal signal_MainDown1_MainUp1_L_1_D_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_1 , 1 , 1 , 1);
	
	Signal signal_MainDown1_MainUp1_L_2_U_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_2 , 1 , -1 , 0);
	Signal signal_MainDown1_MainUp1_L_2_U_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_2 , -1 , -1 , 0);
	Signal signal_MainDown1_MainUp1_L_2_D_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_2 , -1 , 1 , 1);
	Signal signal_MainDown1_MainUp1_L_2_D_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainUp1_L_2 , 1 , 1 , 1);
	
//|||-------------------------------------UP LOOP LINES Signals---------------------------------------------------------|||
	
	Signal signal_mainUp2_loopUp1_1_U_L = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_1 , 1 , -1 , 0);
	Signal signal_mainUp2_loopUp1_1_U_R = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_1 , -1 , -1 , 0);	
	Signal signal_mainUp2_loopUp1_1_D_R = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_1 , -1 , 1 , 1);
	Signal signal_mainUp2_loopUp1_1_D_L = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_1 , 1 , 1 , 1);
	
	Signal signal_mainUp2_loopUp1_2_U_L = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_2 , 1 , -1 , 0);
	Signal signal_mainUp2_loopUp1_2_U_R = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_2 , -1 , -1 , 0);	
	Signal signal_mainUp2_loopUp1_2_D_R = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_2 , -1 , 1 , 1);
	Signal signal_mainUp2_loopUp1_2_D_L = new Signal(Signal.signalType.HOME , switchMainUp2_LoopUp1_2 , 1 , 1 , 1);
	
	
	Signal signal_LoopUp1_LoopUp2_1_U_L = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_1 , 1 , -1 , 0);
	Signal signal_LoopUp1_LoopUp2_1_U_R = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_1 , -1 , -1 , 0);	
	Signal signal_LoopUp1_LoopUp2_1_D_R = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_1 , -1 , 1 , 1);
	Signal signal_LoopUp1_LoopUp2_1_D_L = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_1 , 1 , 1 , 1);
	
	Signal signal_LoopUp1_LoopUp2_2_U_L = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_2 , 1 , -1 , 0);
	Signal signal_LoopUp1_LoopUp2_2_U_R = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_2 , -1 , -1 , 0);	
	Signal signal_LoopUp1_LoopUp2_2_D_R = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_2 , -1 , 1 , 1);
	Signal signal_LoopUp1_LoopUp2_2_D_L = new Signal(Signal.signalType.HOME , switchLoopUp1_LoopUp2_2 , 1 , 1 , 1);

	
	
//|||-------------------------------------DOWN LOOP LINES Signals---------------------------------------------------------|||
	
	Signal signal_MainDown2_LoopDown1_1_U_L = new Signal(Signal.signalType.HOME , switchMainDown2_LoopDown1_1 , 1 , 1 , 0);
	Signal signal_MainDown2_LoopDown1_1_U_R = new Signal(Signal.signalType.HOME , switchMainDown2_LoopDown1_1 , -1 , 1 , 0);	
	Signal signal_MainDown2_LoopDown1_1_D_R = new Signal(Signal.signalType.HOME , switchMainDown2_LoopDown1_1 , -1 , -1 , 1);
	Signal signal_MainDown2_LoopDown1_1_D_L = new Signal(Signal.signalType.HOME , switchMainDown2_LoopDown1_1 , 1 , -1 , 1);
	
	Signal signal_LoopDown1_LoopDown2_1_U_L = new Signal(Signal.signalType.HOME , switchLoopDown1_LoopDown2_1 , 1 , 1 , 0);
	Signal signal_LoopDown1_LoopDown2_1_U_R = new Signal(Signal.signalType.HOME , switchLoopDown1_LoopDown2_1 , -1 , 1 , 0);	
	Signal signal_LoopDown1_LoopDown2_1_D_R = new Signal(Signal.signalType.HOME , switchLoopDown1_LoopDown2_1 , -1 , -1 , 1);
	Signal signal_LoopDown1_LoopDown2_1_D_L = new Signal(Signal.signalType.HOME , switchLoopDown1_LoopDown2_1 , 1 , -1 , 1);
	

//|||-------------------------------------DOWN MAIN LINES Signals---------------------------------------------------------|||
	
	Signal signal_MainDown1_MainDown2_1_L_U_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_1 , 1 , 1 , 0);
	Signal signal_MainDown1_MainDown2_1_L_U_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_1 , -1 , 1 , 0);
	Signal signal_MainDown1_MainDown2_1_L_D_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_1 , -1 , -1 , 1);
	Signal signal_MainDown1_MainDown2_1_L_D_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_1 , 1 , -1 , 1);
	
	Signal signal_MainDown1_MainDown2_2_L_U_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_2 , 1 , 1 , 0);
	Signal signal_MainDown1_MainDown2_2_L_U_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_2 , -1 , 1 , 0);
	Signal signal_MainDown1_MainDown2_2_L_D_R = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_2 , -1 , -1 , 1);
	Signal signal_MainDown1_MainDown2_2_L_D_L = new Signal(Signal.signalType.HOME , switchMainDown1_MainDown2_L_2 , 1 , -1 , 1);
	
	Signal signal_MainDown2_MainDown1_1_L_U_L = new Signal(Signal.signalType.HOME , switchMainDown2_MainDown1_L_1 , 1 , -1 , 0);
	Signal signal_MainDown2_MainDown1_1_L_U_R = new Signal(Signal.signalType.HOME , switchMainDown2_MainDown1_L_1 , -1 , -1 , 0);
	Signal signal_MainDown2_MainDown1_1_L_D_R = new Signal(Signal.signalType.HOME , switchMainDown2_MainDown1_L_1 , -1 , 1 , 1);
	Signal signal_MainDown2_MainDown1_1_L_D_L = new Signal(Signal.signalType.HOME , switchMainDown2_MainDown1_L_1 , 1 , 1 , 1);
	
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
				
				//switches
				//------------Between Up Main and Down Main-----------------
				switchMainDown1_MainUp1_L_1,
				switchMainDown1_MainUp1_L_2,
				switchMainUp1_MainDown1_L_1,
				switchMainUp1_MainDown1_L_2,
				
				
				
				//------------Between Up Main Lines-----------
				switchMainUp1_MainUp2_L_1,
				switchMainUp1_MainUp2_L_2,
				switchMainUp2_MainUp1_L_1,
				
				switchMainUp2_MainUp1_R_1,
								
				//-----------Between Up Main and Up Loop--------------
				switchMainUp2_LoopUp1_1,
				switchMainUp2_LoopUp1_2,
				
				//-----------Between Up Loop Lines--------------------
				switchLoopUp1_LoopUp2_1,
				switchLoopUp1_LoopUp2_2,
				
				
				
				//------------Between Down Main Lines-----------
				switchMainDown1_MainDown2_L_1,
				switchMainDown1_MainDown2_L_2,
				
				switchMainDown2_MainDown1_L_1,
				
				//----------Between Down Main and Down Loop-----------
				switchMainDown2_LoopDown1_1,
				
				//----------Between Down Loop Lines 
				switchLoopDown1_LoopDown2_1
				
				
				
				
				);
		
		
		
		return listOfTrackSections;
		
		
	}
	
	
	@Override
	public List<Signal> getSignals() {
		
		
		
		Collections.addAll(listOfSignals , 
				
				mainUP_1_1,

				mainDown_1_1,
				
				
				signal_MainUp1_MainUp2_L_1_U_L, 
				signal_MainUp1_MainUp2_L_1_U_R, 
				signal_MainUp1_MainUp2_L_1_D_L, 
				signal_MainUp1_MainUp2_L_1_D_R,
				
				signal_MainUp1_MainUp2_L_2_U_L, 
				signal_MainUp1_MainUp2_L_2_U_R, 
				signal_MainUp1_MainUp2_L_2_D_L, 
				signal_MainUp1_MainUp2_L_2_D_R,
				
				signal_mainUp2_loopUp1_1_U_L,
				signal_mainUp2_loopUp1_1_U_R,
				signal_mainUp2_loopUp1_1_D_L,
				signal_mainUp2_loopUp1_1_D_R,
				
				
				signal_mainUp2_loopUp1_2_U_L, 
				signal_mainUp2_loopUp1_2_U_R, 
				signal_mainUp2_loopUp1_2_D_L, 
				signal_mainUp2_loopUp1_2_D_R,
				
				signal_MainDown1_MainUp1_L_1_U_L,
				signal_MainDown1_MainUp1_L_1_U_R,
				signal_MainDown1_MainUp1_L_1_D_L,
				signal_MainDown1_MainUp1_L_1_D_R,
				
				signal_MainDown1_MainUp1_L_2_U_L,
				signal_MainDown1_MainUp1_L_2_U_R,
				signal_MainDown1_MainUp1_L_2_D_L,
				signal_MainDown1_MainUp1_L_2_D_R,
				
				signal_MainUp1_MainDown1_L_1_U_L, 
				signal_MainUp1_MainDown1_L_1_U_R, 
				signal_MainUp1_MainDown1_L_1_D_L, 
				signal_MainUp1_MainDown1_L_1_D_R,
				
				signal_MainUp1_MainDown1_L_2_U_L, 
				signal_MainUp1_MainDown1_L_2_U_R, 
				signal_MainUp1_MainDown1_L_2_D_L, 
				signal_MainUp1_MainDown1_L_2_D_R,
				
				signal_MainUp2_MainUp1_L_1_U_L,
				signal_MainUp2_MainUp1_L_1_U_R,
				signal_MainUp2_MainUp1_L_1_D_R,
				signal_MainUp2_MainUp1_L_1_D_L,
				
				signal_MainUp2_MainUp1_R_1_U_L, 
				signal_MainUp2_MainUp1_R_1_U_R, 
				signal_MainUp2_MainUp1_R_1_D_L, 
				signal_MainUp2_MainUp1_R_1_D_R,
				
				signal_LoopUp1_LoopUp2_1_U_L, 
				signal_LoopUp1_LoopUp2_1_U_R, 
				signal_LoopUp1_LoopUp2_1_D_L, 
				signal_LoopUp1_LoopUp2_1_D_R,
				
				signal_LoopUp1_LoopUp2_2_U_L, 
				signal_LoopUp1_LoopUp2_2_U_R, 
				signal_LoopUp1_LoopUp2_2_D_L, 
				signal_LoopUp1_LoopUp2_2_D_R,
				
				
//---------------------------------------------------------DOWN------------------------------------------------------------
				
				signal_MainDown2_LoopDown1_1_U_L,
				signal_MainDown2_LoopDown1_1_U_R,
				signal_MainDown2_LoopDown1_1_D_R,
				signal_MainDown2_LoopDown1_1_D_L,
				
				signal_LoopDown1_LoopDown2_1_U_L,
				signal_LoopDown1_LoopDown2_1_U_R,
				signal_LoopDown1_LoopDown2_1_D_R,
				signal_LoopDown1_LoopDown2_1_D_L,
				
				signal_MainDown1_MainDown2_1_L_U_L,
				signal_MainDown1_MainDown2_1_L_U_R,
				signal_MainDown1_MainDown2_1_L_D_R,
				signal_MainDown1_MainDown2_1_L_D_L,
				
				signal_MainDown1_MainDown2_2_L_U_L,
				signal_MainDown1_MainDown2_2_L_U_R,
				signal_MainDown1_MainDown2_2_L_D_R,
				signal_MainDown1_MainDown2_2_L_D_L,
				
				signal_MainDown2_MainDown1_1_L_U_L,
				signal_MainDown2_MainDown1_1_L_U_R,
				signal_MainDown2_MainDown1_1_L_D_R,
				signal_MainDown2_MainDown1_1_L_D_L

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
		
		switchMainUp1_MainDown1_L_2.setSignals(
				signal_MainUp1_MainDown1_L_2_U_L, 
				signal_MainUp1_MainDown1_L_2_U_R, 
				signal_MainUp1_MainDown1_L_2_D_L, 
				signal_MainUp1_MainDown1_L_2_D_R);
		
	
		switchMainDown1_MainUp1_L_1.setSignals(
				signal_MainDown1_MainUp1_L_1_U_L,
				signal_MainDown1_MainUp1_L_1_U_R,
				signal_MainDown1_MainUp1_L_1_D_L,
				signal_MainDown1_MainUp1_L_1_D_R);
		
		switchMainDown1_MainUp1_L_2.setSignals(
				signal_MainDown1_MainUp1_L_2_U_L,
				signal_MainDown1_MainUp1_L_2_U_R,
				signal_MainDown1_MainUp1_L_2_D_L,
				signal_MainDown1_MainUp1_L_2_D_R);
		
		switchMainUp2_LoopUp1_1.setSignals(
				signal_mainUp2_loopUp1_1_U_L, 
				signal_mainUp2_loopUp1_1_U_R, 
				signal_mainUp2_loopUp1_1_D_L, 
				signal_mainUp2_loopUp1_1_D_R);
		
		switchMainUp2_LoopUp1_2.setSignals(
				signal_mainUp2_loopUp1_2_U_L, 
				signal_mainUp2_loopUp1_2_U_R, 
				signal_mainUp2_loopUp1_2_D_L, 
				signal_mainUp2_loopUp1_2_D_R);
		
		switchLoopUp1_LoopUp2_1.setSignals(
				signal_LoopUp1_LoopUp2_1_U_L, 
				signal_LoopUp1_LoopUp2_1_U_R, 
				signal_LoopUp1_LoopUp2_1_D_L, 
				signal_LoopUp1_LoopUp2_1_D_R);
		
		switchLoopUp1_LoopUp2_2.setSignals(
				signal_LoopUp1_LoopUp2_2_U_L, 
				signal_LoopUp1_LoopUp2_2_U_R, 
				signal_LoopUp1_LoopUp2_2_D_L, 
				signal_LoopUp1_LoopUp2_2_D_R);
		
		switchMainUp2_MainUp1_L_1.setSignals(
				signal_MainUp2_MainUp1_L_1_U_L, 
				signal_MainUp2_MainUp1_L_1_U_R, 
				signal_MainUp2_MainUp1_L_1_D_L, 
				signal_MainUp2_MainUp1_L_1_D_R);
		
		switchMainUp2_MainUp1_R_1.setSignals(
				signal_MainUp2_MainUp1_R_1_U_L, 
				signal_MainUp2_MainUp1_R_1_U_R, 
				signal_MainUp2_MainUp1_R_1_D_L, 
				signal_MainUp2_MainUp1_R_1_D_R);
		
		switchMainUp1_MainUp2_L_1.setSignals(
				signal_MainUp1_MainUp2_L_1_U_L, 
				signal_MainUp1_MainUp2_L_1_U_R, 
				signal_MainUp1_MainUp2_L_1_D_L, 
				signal_MainUp1_MainUp2_L_1_D_R);
		
		switchMainUp1_MainUp2_L_2.setSignals(
				signal_MainUp1_MainUp2_L_2_U_L, 
				signal_MainUp1_MainUp2_L_2_U_R, 
				signal_MainUp1_MainUp2_L_2_D_L, 
				signal_MainUp1_MainUp2_L_2_D_R);
		
//---------------------------------------------------------DOWN------------------------------------------------------------
		
		switchMainDown1_MainDown2_L_1.setSignals(
				signal_MainDown1_MainDown2_1_L_U_L,
				signal_MainDown1_MainDown2_1_L_U_R,
				signal_MainDown1_MainDown2_1_L_D_R,
				signal_MainDown1_MainDown2_1_L_D_L);
		
		switchMainDown1_MainDown2_L_2.setSignals(
				signal_MainDown1_MainDown2_2_L_U_L,
				signal_MainDown1_MainDown2_2_L_U_R,
				signal_MainDown1_MainDown2_2_L_D_R,
				signal_MainDown1_MainDown2_2_L_D_L);
		
		switchMainDown2_MainDown1_L_1.setSignals(
				signal_MainDown2_MainDown1_1_L_U_L,
				signal_MainDown2_MainDown1_1_L_U_R,
				signal_MainDown2_MainDown1_1_L_D_R,
				signal_MainDown2_MainDown1_1_L_D_L);
		
		
		switchMainDown2_LoopDown1_1.setSignals(
				signal_MainDown2_LoopDown1_1_U_L,
				signal_MainDown2_LoopDown1_1_U_R,
				signal_MainDown2_LoopDown1_1_D_R,
				signal_MainDown2_LoopDown1_1_D_L);
		
		switchLoopDown1_LoopDown2_1.setSignals(
				signal_LoopDown1_LoopDown2_1_U_L,
				signal_LoopDown1_LoopDown2_1_U_R,
				signal_LoopDown1_LoopDown2_1_D_R,
				signal_LoopDown1_LoopDown2_1_D_L);
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
				signal_MainUp2_MainUp1_L_1_U_L
				);
		
		
		
		Collections.addAll(downLineStartSignals, 
				
				mainDown_1_1,
				signal_MainDown2_MainDown1_1_L_U_L);
		
		SimulationController.deployMainUpLine = deployMainUpLine;
		SimulationController.deployMainDownLine = deployMainDownLine;
		SimulationController.upLineStartSignals = upLineStartSignals;
		SimulationController.downLineStartSignals = downLineStartSignals;
		
	}


	@Override
	public void createStation() {
		
		Simulator.stationGraphicVerticalPos = 4;
		Simulator.stationGraphicHorizontalPos = 2;
		
	}


	@Override
	public void setGrid() {
		
		Simulator.MAX_HORIZONTAL_ELM = 29;
		
	}

	

	

	

}
