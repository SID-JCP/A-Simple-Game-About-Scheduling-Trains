package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RenderingElements.Controller.MapController;
import RenderingElements.Tracks.TrackControllerRequirments;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public class TestTrackDesign implements TrackControllerRequirments{
	
	
	public List<TrackSection> listOfTrackSections = new ArrayList<>();
	
	
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	TrackSection mainUp2 = new TrackSection(trackType.UP , 2 , -1);
	
	
	TrackSection loopUp1 = new TrackSection(trackType.UP , 3 , 13);
	TrackSection loopUp2 = new TrackSection(trackType.UP , 4 , 8);
	
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	TrackSection mainDown2 = new TrackSection(trackType.DOWN , 2 , -1);

	
	
	
	TrackSection switchMainUp2_loopUp1 = new TrackSection(trackType.UP_START , mainUp2 , loopUp1 , 1);
	TrackSection switchLoopUp1_loopUp2 = new TrackSection(trackType.UP_START , loopUp1 , loopUp2 , 3);
	
	
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
	public void addListToController() {
		
		MapController.listOfTrackSections = getSections();
		
	}

	

	

}
