package RenderingElements.Tracks;

import java.awt.Graphics2D;

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
	
	private int MAX_WIDTH = 1500; //Canvas Width Max 
	private int MAX_HEIGHT = 600; //Canvas Height Max 
	
	private int vCenter = 750; //Canvas Vertical Center 
	private int hCenter = 300; //Canvas Vertical Center 
	
	
	
	private int trackGap = 20; //gap between parallel tracks 
	private int platfromGap = 50; //gap between parallel tracks for platform 
	
	
	
	/*
	 * Station Design
	 *		_________ 			
	 * 	   /		 \
	 * ---------------------
	 *  \/              \/
	 * ---------------------
	 * 	  \__________/   
	 * 
	 * 
	 * WOW CUTE !!
	 */	
	
	
	//Main Up Lines 
	TrackSection mainUp1 = new TrackSection(1 , trackGap , MAX_WIDTH , hCenter , vCenter , trackType.UP);
	
	
	//change between down and up before start of station
	TrackSection changeDownUpMainStart = new TrackSection(900 , -20 , vCenter , hCenter , trackType.CHANGE_FOR_UP_START , 40);
	TrackSection changeUpDownMainStart = new TrackSection(-800 , -20 , vCenter , hCenter , trackType.CHANGE_FOR_UP_END , 40);
	
	//Up Loop Line
	TrackSection loopUp1 = new TrackSection(2 , 1 , 50 , trackGap , 400 , hCenter , vCenter , trackType.UP);	
	//Change for Up Loop1 start
	TrackSection changeUp1 = new TrackSection(400 , 20 , vCenter , hCenter , trackType.CHANGE_FOR_UP_START , 70);
	//Change for Up Loop1 end 
	TrackSection changeUpDown1 = new TrackSection(400 , 20 , vCenter , hCenter , trackType.CHANGE_FOR_UP_END , 70);
	
	//Main Down Lines 
	TrackSection mainDown1 = new TrackSection(1 , trackGap , MAX_WIDTH , hCenter , vCenter , trackType.DOWN);
	
	
	//change between down and up before start of station
	TrackSection changeDownUpMainEnd = new TrackSection(-800 , -20 , vCenter , hCenter , trackType.CHANGE_FOR_UP_START , 40);
	TrackSection changeUpDownMainEnd = new TrackSection(900 , -20 , vCenter , hCenter , trackType.CHANGE_FOR_UP_END , 40);
		
	
	//Down Loop Line
	TrackSection loopDown1 = new TrackSection(2 , 1 , 50 , trackGap , 400 , hCenter , vCenter , trackType.DOWN);
	//Change for Down Loop1 start
	TrackSection changeDown1 = new TrackSection(400 , 20 , vCenter , hCenter , trackType.CHANGE_FOR_DOWN_START , 70);
	//Change for Up Loop1 end 
	TrackSection changeDownUp1 = new TrackSection(400 , 20 , vCenter , hCenter , trackType.CHANGE_FOR_DOWN_END , 70);
	
	
	
	
	
	
	
	
	
	
	public void drawTracks(Graphics2D g) 
	{
		mainUp1.drawSection(g);
		
		changeDownUpMainStart.drawSection(g);
		changeUpDownMainStart.drawSection(g);
		
		loopUp1.drawSection(g);	
		changeUp1.drawSection(g);
		changeUpDown1.drawSection(g);		
		
		
		mainDown1.drawSection(g);
		
		changeDownUpMainEnd.drawSection(g);
		changeUpDownMainEnd.drawSection(g);
				
		loopDown1.drawSection(g);
		changeDown1.drawSection(g);
		changeDownUp1.drawSection(g);
		
	}
	
	

	
	
	
	
	
}
