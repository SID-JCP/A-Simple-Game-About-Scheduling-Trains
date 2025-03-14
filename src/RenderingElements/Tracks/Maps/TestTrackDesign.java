package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackControllerRequirments;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public class TestTrackDesign implements TrackControllerRequirments{
	
	
	
	//|-------------------------Naming Convention For Switches [Use of Enum]----------------------------------|
	
	
	/*
	 * If Switch goes from *UP to UP* or *DOWN to UP* then its UP_START
	 * If Switch goes from *UP to DOWN* OR *UP to lower UP* then its UP_END
	 * 
	 * If Switch goes from *Down to Down* or *UP to DOWN* then its DOWN_START
	 * If Switch goes from *DOWN to UP* OR *DOWN to Upper DOWN* then its DOWN_END
	 * 
	 */
	

	
	//|-----------------------------LIST OF TRACK SECTIONS  CREATE IN ALL CLASES--------------------------------------|
	public List<TrackSection> listOfTrackSections = new ArrayList<>();
	

	//|-------------------------------LIST OF SIGNALS CREATE IN ALL CLASES-------------------------------------------------|
	private List<Signal> listOfSignals = new ArrayList<>();
	
	
	
	
	//|--------------------------------TRACK OBJECTS--------------------------------------|
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	TrackSection mainUp2 = new TrackSection(trackType.UP , 2 , -1);
	
	
	TrackSection loopUp1 = new TrackSection(trackType.UP , 3 , 13);
	TrackSection loopUp2 = new TrackSection(trackType.UP , 4 , 8);
	
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	TrackSection mainDown2 = new TrackSection(trackType.DOWN , 2 , -1);

	
	TrackSection switchMainUp2_loopUp1 = new TrackSection(trackType.UP_START , mainUp2 , loopUp1 , 1);
	TrackSection switchLoopUp1_loopUp2 = new TrackSection(trackType.UP_START , loopUp1 , loopUp2 , 3);
	
	
	
	//|--------------------------------SIGNAL OBJECTS------------------------------------
	Signal mainUP_1 = new Signal(signalType.BLOCK , mainUp1 , null , 1 , 1 , -1);
	Signal mainUP_2 = new Signal(signalType.BLOCK , mainUp1 , mainUP_1 , 3 , 1 , -1);
	Signal mainUP_3 = new Signal(signalType.BLOCK , mainUp1 , mainUP_2 , 5 , 1 , -1);
	Signal mainUP_4 = new Signal(signalType.BLOCK , mainUp1 , mainUP_3 , 7 , 1 , -1);
	Signal mainUP_5 = new Signal(signalType.BLOCK , mainUp1 , mainUP_4 , 9 , 1 , -1);
	Signal mainUP_6 = new Signal(signalType.BLOCK , mainUp1 , mainUP_5 , 11 , 1 , -1);
	Signal mainUP_7 = new Signal(signalType.BLOCK , mainUp1 , mainUP_6 , 13 , 1 , -1);
	
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
				
				mainUP_1,
				mainUP_2,
				mainUP_3,
				mainUP_4,
				mainUP_5,
				mainUP_6,
				mainUP_7,
				signal_MainUp2_S_L

				);
		
		
		
		
		return listOfSignals;
	}

	@Override
	public void addListToController()
	{
		

		//|--------------------------------------------------RUN PRIVATE METHODS TO ADD VALUE TO LISTS FOR BOTH SIGNALS AND TRACK SECTIONS------------------------------------------------|

		SimulationController.listOfTrackSections = getSections();
		SimulationController.listOfSignals = getSignals();
		
		
		
	}


	@Override
	public void initializeInterlocking() 
	{
		
		
	}

	

	

	

}
