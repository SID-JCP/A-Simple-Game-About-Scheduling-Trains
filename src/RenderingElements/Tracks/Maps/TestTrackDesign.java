package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RenderingElements.Controller.MapController;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackControllerRequirments;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public class TestTrackDesign implements TrackControllerRequirments{
	
	
	public List<TrackSection> listOfTrackSections = new ArrayList<>();
	
	private List<Signal> listOfSignals = new ArrayList<>();
	
	
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	TrackSection mainUp2 = new TrackSection(trackType.UP , 2 , -1);
	
	
	TrackSection loopUp1 = new TrackSection(trackType.UP , 3 , 13);
	TrackSection loopUp2 = new TrackSection(trackType.UP , 4 , 8);
	
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	TrackSection mainDown2 = new TrackSection(trackType.DOWN , 2 , -1);

	
	Signal signal_1_mainUP = new Signal(signalType.BLOCK , mainUp1 , null , 1 , 1 , -1);
	Signal signal_2_mainUP = new Signal(signalType.BLOCK , mainUp1 , null , 3 , 1 , -1);
	Signal signal_3_mainUP = new Signal(signalType.BLOCK , mainUp1 , null , 5 , 1 , -1);
	Signal signal_4_mainUP = new Signal(signalType.BLOCK , mainUp1 , null , 7 , 1 , -1);
	
	TrackSection switchMainUp2_loopUp1 = new TrackSection(trackType.UP_START , mainUp2 , loopUp1 , 1);
	TrackSection switchLoopUp1_loopUp2 = new TrackSection(trackType.UP_START , loopUp1 , loopUp2 , 3);
	
	
	Signal signal_MainUp2_S_L = new Signal(signalType.HOME , switchMainUp2_loopUp1 , -1 , -1 , 0);
	
	@Override
	public List<TrackSection> getSections() {
		
		
		Collections.addAll(listOfTrackSections , 
				
				
				mainUp1,
				mainUp2,
				mainDown1,
				mainDown2,
				loopUp1,
				loopUp2,
				switchMainUp2_loopUp1,
				switchLoopUp1_loopUp2
				
				
				
				
				
				);
		
		
		return listOfTrackSections;
		
		
	}
	
	
	@Override
	public List<Signal> getSignals() {
		
		
		
		Collections.addAll(listOfSignals , 
				
				signal_1_mainUP,
				signal_2_mainUP,
				signal_3_mainUP,
				signal_4_mainUP,
				signal_MainUp2_S_L
				);
		
		
		
		
		return listOfSignals;
	}

	@Override
	public void addListToController() {
		
		MapController.listOfTrackSections = getSections();
		MapController.listOfSignals = getSignals();
	}

	

	

	

}
