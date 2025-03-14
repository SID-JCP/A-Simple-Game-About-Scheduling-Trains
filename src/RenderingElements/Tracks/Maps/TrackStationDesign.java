package RenderingElements.Tracks.Maps;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Point.Point;
import RenderingElements.Point.Point.pointType;
import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public  class TrackStationDesign {
	
	

	
	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	
	private List<Signal> listOfSignals = new ArrayList<>();
	
	
	
	//Main Up Line
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	
	//loop Up Line 
	TrackSection loopUp1 = new TrackSection(trackType.UP , 2 , 5);
	
	//switch from main UP line to loop 
	TrackSection switchMainUp1_loopUp1 = new TrackSection(trackType.UP_START , mainUp1 , loopUp1 , 5);
	
	//switch from loop to main Up line 
	TrackSection switchloopUp1_MainUp1 = new TrackSection(trackType.UP_END , loopUp1 , mainUp1 , 11);
	
	
	
	//------------------------- CENTER--------------------------------
	
	
	//Main Down Line
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	
	//loop Down Line 
	TrackSection loopDown1 = new TrackSection(trackType.DOWN , 2 , 5);
	
	TrackSection switchMainDown1_LoopDown1 = new TrackSection(trackType.DOWN_START , mainDown1 , loopDown1 , 5);
	
	TrackSection switchLoopDown1_MainDown1 = new TrackSection(trackType.DOWN_END , loopDown1 , mainDown1 , 11);
	
	
	
	//-----------------------MAIN LINE SWITCHES---------------------------
	
	
	
	TrackSection switchMainUp_MainDown_L = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  1);	
	
	TrackSection switchMainDown_MainUp_L = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 3);
	
	TrackSection switchMainUp_MainDown_R = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  12);
	
	TrackSection switchMainDown_MainUp_R = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 14);
	
	
	
	//-------------------------------SIGNALS-------------------------------------------
	
	
	//MAIN LINES BEFORE PLATFORM 
	Signal signal_mainUp_mainDown_U_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , 1 , 1 , 0);
	Signal signal_mainUp_mainDown_U_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , -1 , 1 , 0);
	
	
	Signal signal_mainUp_mainDown_D_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , -1 , -1 , 1);
	Signal signal_mainUp_mainDown_D_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , 1 , -1 , 1);
	
	
	
	
	Signal signal_mainDown_mainUp_U_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , -1 , 1 , 1);
	Signal signal_mainDown_mainUp_U_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , 1 , 1 , 1);
	
	
	Signal signal_mainDown_mainUp_D_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , -1 , -1 , 0);
	Signal signal_mainDown_mainUp_D_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , 1 , -1 , 0);
	
	
	
	
	//MAIN LINES AFTER PLATFORM 
	
	Signal Fin_signal_mainUp_mainDown_U_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , -1 , 1 , 0);
	Signal Fin_signal_mainUp_mainDown_U_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , 1 , 1 , 0);
	
	Signal Fin_signal_mainUp_mainDown_D_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , -1 , -1 , 1);
	Signal Fin_signal_mainUp_mainDown_D_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , 1 , -1 , 1);
	
	
	
	Signal Fin_signal_mainDown_mainUp_U_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , -1 , 1 , 1);
	Signal Fin_signal_mainDown_mainUp_U_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , 1 , 1 , 1);
	
	Signal Fin_signal_mainDown_mainUp_D_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , -1 , -1 , 0);
	Signal Fin_signal_mainDown_mainUp_D_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , 1 , -1 , 0);
	

	
	
	
	
	//------------------------------------------------------------------------------------------------
	
	
	//Loop line up start 
	Signal signal_mainUp1_loopUp1_U_L = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , -1 , 1 , 1);
	Signal signal_mainUp1_loopUp1_U_R = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , 1 , 1 , 1);
	
	Signal signal_mainUp1_loopUp1_D_L = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , -1 , -1 , 0);
	Signal signal_mainUp1_loopUp1_D_R = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , 1 , -1 , 0);
	
	
	//loop line up end 
	Signal signal_loopUp1_mainUp1_U_L = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , -1 , 1 , 0);
	Signal signal_loopUp1_mainUp1_U_R = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , 1 , 1 , 0);
	
	Signal signal_loopUp1_mainUp1_D_L = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , -1 , -1 , 1);
	Signal signal_loopUp1_mainUp1_D_R = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , 1 , -1 , 1);
	
	
	
	
	//loop line down start
	Signal signal_mainDown1_loopDown1_U_L = new Signal(Signal.signalType.HOME , switchMainDown1_LoopDown1 , -1 , 1 , 0);
	Signal signal_mainDown1_loopDown1_U_R = new Signal(Signal.signalType.HOME , switchMainDown1_LoopDown1 , 1 , 1 , 0);
	
	Signal signal_mainDown1_loopDown1_D_L = new Signal(Signal.signalType.HOME , switchMainDown1_LoopDown1 , -1 , -1 , 1);
	Signal signal_mainDown1_loopDown1_D_R = new Signal(Signal.signalType.HOME , switchMainDown1_LoopDown1 , 1 , -1 , 1);
	
	
	//loop line down end 
	Signal signal_loopDown1_mainDown1_U_L = new Signal(Signal.signalType.HOME , switchLoopDown1_MainDown1 , -1 , -1 , 0);
	Signal signal_loopDown1_mainDown1_U_R = new Signal(Signal.signalType.HOME , switchLoopDown1_MainDown1 , 1 , -1 , 0);
	
	Signal signal_loopDown1_mainDown1_D_L = new Signal(Signal.signalType.HOME , switchLoopDown1_MainDown1 , -1 , 1 , 1);
	Signal signal_loopDown1_mainDown1_D_R = new Signal(Signal.signalType.HOME , switchLoopDown1_MainDown1 , 1 , 1 , 1);
	
	
	private List<TrackSection> getSections() 
	{
		Collections.addAll(listOfTrackSections , 
				
				
				mainUp1,
				loopUp1,
				switchMainUp1_loopUp1,
				mainDown1,
				loopDown1,				
				switchloopUp1_MainUp1,				
				switchMainDown1_LoopDown1,
				switchLoopDown1_MainDown1,
				
				switchMainUp_MainDown_L,
				switchMainDown_MainUp_L,
				
				switchMainUp_MainDown_R,
				switchMainDown_MainUp_R
				
				);
		
		
		return listOfTrackSections;
	}
	
	
	private List<Signal> getSignals()
	{
		
		Collections.addAll(listOfSignals , 
				
				signal_mainUp_mainDown_U_L,
				signal_mainUp_mainDown_U_R,
				signal_mainUp_mainDown_D_L,
				signal_mainUp_mainDown_D_R,
				
				
				signal_mainDown_mainUp_U_L,
				signal_mainDown_mainUp_U_R,
				signal_mainDown_mainUp_D_L,
				signal_mainDown_mainUp_D_R,
				
				
				Fin_signal_mainUp_mainDown_U_L,
				Fin_signal_mainUp_mainDown_U_R,
				Fin_signal_mainUp_mainDown_D_L,
				Fin_signal_mainUp_mainDown_D_R,
				
				
				Fin_signal_mainDown_mainUp_U_L,
				Fin_signal_mainDown_mainUp_U_R,
				Fin_signal_mainDown_mainUp_D_L,
				Fin_signal_mainDown_mainUp_D_R,
				
								
				signal_mainUp1_loopUp1_U_L,
				signal_mainUp1_loopUp1_U_R,
				signal_mainUp1_loopUp1_D_L,
				signal_mainUp1_loopUp1_D_R,
				
				signal_loopUp1_mainUp1_U_L,
				signal_loopUp1_mainUp1_U_R,
				signal_loopUp1_mainUp1_D_L,
				signal_loopUp1_mainUp1_D_R,
				
				
				signal_mainDown1_loopDown1_U_L,
				signal_mainDown1_loopDown1_U_R,
				signal_mainDown1_loopDown1_D_L,
				signal_mainDown1_loopDown1_D_R,
				
				
				signal_loopDown1_mainDown1_U_L,
				signal_loopDown1_mainDown1_U_R,
				signal_loopDown1_mainDown1_D_L,
				signal_loopDown1_mainDown1_D_R
				
				);
		
		
		return listOfSignals;
	}
	
	
	public void addListToController() 
	{
		SimulationController.listOfTrackSections = getSections();
		SimulationController.listOfSignals = getSignals();
		
		//create a seperate method later
		SimulationController.upMainLine = mainUp1;
		SimulationController.downMainLine = mainDown1;
	}
	
	
	
	public void initializeInterlocking() 
	{
		switchMainUp_MainDown_L.setSignals(
				signal_mainUp_mainDown_U_L, 
				signal_mainUp_mainDown_U_R, 
				signal_mainUp_mainDown_D_L, 
				signal_mainUp_mainDown_D_R);
		
		switchMainDown_MainUp_L.setSignals(
				signal_mainDown_mainUp_U_L, 
				signal_mainDown_mainUp_U_R, 
				signal_mainDown_mainUp_D_L, 
				signal_mainDown_mainUp_D_R);
		
		switchMainUp1_loopUp1.setSignals(
				signal_mainUp1_loopUp1_U_L, 
				signal_mainUp1_loopUp1_U_R, 
				signal_mainUp1_loopUp1_D_L, 
				signal_mainUp1_loopUp1_D_R);
		
		switchloopUp1_MainUp1.setSignals(
				signal_loopUp1_mainUp1_U_L, 
				signal_loopUp1_mainUp1_U_R, 
				signal_loopUp1_mainUp1_D_L, 
				signal_loopUp1_mainUp1_D_R);
		
		switchMainDown1_LoopDown1.setSignals(
				signal_mainDown1_loopDown1_U_L,
				signal_mainDown1_loopDown1_U_R,
				signal_mainDown1_loopDown1_D_L, 
				signal_mainDown1_loopDown1_D_R);
		
		switchLoopDown1_MainDown1.setSignals(
				signal_loopDown1_mainDown1_U_L, 
				signal_loopDown1_mainDown1_U_R, 
				signal_loopDown1_mainDown1_D_L, 
				signal_loopDown1_mainDown1_D_R);
		
		switchMainUp_MainDown_R.setSignals(
				Fin_signal_mainUp_mainDown_U_L, 
				Fin_signal_mainUp_mainDown_U_R, 
				Fin_signal_mainUp_mainDown_D_L, 
				Fin_signal_mainUp_mainDown_D_R);
		
		switchMainDown_MainUp_R.setSignals(
				Fin_signal_mainDown_mainUp_U_L,
				Fin_signal_mainDown_mainUp_U_R, 
				Fin_signal_mainDown_mainUp_D_L, 
				Fin_signal_mainDown_mainUp_D_R);
	}
	

	
	
	
	
	
}
