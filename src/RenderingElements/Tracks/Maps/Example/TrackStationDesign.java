package RenderingElements.Tracks.Maps.Example;

import java.awt.Graphics2D;
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
	
	

	
	
	private int trackGap = 20; //gap between parallel tracks 
	private int platfromGap = 50; //gap between parallel tracks for platform 
	
	
	private List<TrackSection> listOfTrackSections = new LinkedList<>();
	
	
	
	//Main Up Line
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	
	//loop Up Line 
	TrackSection loopUp1 = new TrackSection(trackType.UP , 2 , 5);
	
	TrackSection switchMainUp1_loopUp1 = new TrackSection(trackType.UP_START , mainUp1 , loopUp1 , 3);
	
	
	
	
	//Main Down Line
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	
	//loop Down Line 
	TrackSection loopDown1 = new TrackSection(trackType.DOWN , 2 , 5);
	
	

	
	
	
//	Point pointDownUp1 = new Point(changeDownUpMainStart , mainUp1  ,loopUp1 , pointType.DOWN_UP);
//	
	
	private List<TrackSection> getSections() 
	{
		Collections.addAll(listOfTrackSections , 
				
				
				mainUp1,
				loopUp1,
				switchMainUp1_loopUp1,
				mainDown1,
				loopDown1
				
				
				
				);
		
		
		return listOfTrackSections;
	}
	
	
	public void addListToController() 
	{
		MapController.listOfTrackSections = getSections();
	}
	
	
	
	public void drawTracks(Graphics2D g) 
	{
		
		
		
		
	}
	
	

	
	
	
	
	
}
