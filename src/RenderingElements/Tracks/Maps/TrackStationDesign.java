package RenderingElements.Tracks.Maps;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Controller.MapController;
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
	
	Signal signal_1_L_S = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , -1 , 1 , 0);
	Signal signal_1_R_S = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , 1 , 1 , 0);
	
	Signal signal_1_L_E = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , -1 , -1 , 1);
	Signal signal_1_R_E = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , 1 , -1 , 1);
	
	
	
	Signal signal_2_L_S = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , -1 , -1 , 0);
	Signal signal_2_R_S = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , 1 , -1 , 0);
	
	Signal signal_2_L_E = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , -1 , 1 , 1);
	Signal signal_2_R_E = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , 1 , 1 , 1);
	
	
	
	
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
				
				signal_1_L_S,
				signal_1_R_S,
				signal_1_L_E,
				signal_1_R_E,
				
				
				signal_2_L_S,
				signal_2_R_S,
				signal_2_L_E,
				signal_2_R_E
				
				);
		
		
		return listOfSignals;
	}
	
	
	public void addListToController() 
	{
		MapController.listOfTrackSections = getSections();
		MapController.listOfSignals = getSignals();
	}
	
	
	
	public void initializeInterlocking() 
	{
		switchMainUp_MainDown_L.setSignals(signal_1_L_S, signal_1_R_S, signal_1_R_E, signal_1_L_E);
	}
	

	
	
	
	
	
}
