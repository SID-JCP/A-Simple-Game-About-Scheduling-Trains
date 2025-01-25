package RenderingElements.Tracks.Maps;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Controller.MapController;
import RenderingElements.Point.Point;
import RenderingElements.Point.Point.pointType;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public  class TrackStationDesign {
	
	/*
	 * |-----------  What this class needs to do ---------------|
	 * 
	 * This class will also have the draw spline functionality from track section data 
	 * Will be used by train spline to draw the train spline for that need to store all the 
	 * spline point data such that the train class can choose any type or direction of section
	 * required 
	 * 
	 * Create track sections with specification of coordinates and type of tracks 
	 * 
	 * 
	 */

	
	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	
	
	
	//Main Up Line
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	
	//loop Up Line 
	TrackSection loopUp1 = new TrackSection(trackType.UP , 2 , 5);
	
	TrackSection switchMainUp1_loopUp1 = new TrackSection(trackType.UP_START , mainUp1 , loopUp1 , 5);
	
	TrackSection switchloopUp1_MainUp1 = new TrackSection(trackType.UP_END , loopUp1 , mainUp1 , 11);
	
	
	
	
	
	
	//Main Down Line
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	
	//loop Down Line 
	TrackSection loopDown1 = new TrackSection(trackType.DOWN , 2 , 5);
	
	TrackSection switchMainDown1_LoopDown1 = new TrackSection(trackType.DOWN_START , mainDown1 , loopDown1 , 5);
	
	TrackSection switchLoopDown1_MainDown1 = new TrackSection(trackType.DOWN_END , loopDown1 , mainDown1 , 11);
	
	
	
	
	
	
	TrackSection switchMainUp_MainDown_L = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  1);
	
	TrackSection switchMainDown_MainUp_L = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 3);
	
	
	TrackSection switchMainUp_MainDown_R = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  12);
	
	TrackSection switchMainDown_MainUp_R = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 14);
	
	
	
	
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
	
	
	public void addListToController() 
	{
		MapController.listOfTrackSections = getSections();
	}
	
	
	
	
	

	
	
	
	
	
}
