package RenderingElements.Tracks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;


public class TrackSection {
	
	/*
	 * |-----------  What this class needs to do ---------------|
	 * 
	 * Have start and end of track coordinates 
	 * Store the type of track using enum 
	 * Have signals depending on the type of track 
	 * 
	 * 
	 */
	 
	
	/*
	 * |---------------------  Types of tracks need to be drawn  ------------------|
	 * 
	 * 1. Main lines that are starting from start to end 
	 * - draw a straignt line from start to end 
	 * 
	 * 2. Section of line like part of main line till point then another part (for signal simplification)
	 * 
	 * 3. Section with a tilt depending upon the track to join at and taking argument of the 
	 * 	  start point to determine the position of the point o9lli8o9o
	 */
	
	
	
	private int distanceBetweenControlPoints = 10; //Distance between Control Points
	
	
	
	private Point2D startPoint; //start of track section
	private Point2D endPoint;   //end of track section 	
	
	
	
	
	private int trackWidth = 3; //width of track	
	private trackType track; //type of track (up  , down , change ...)	
	
	
	//Use this for all tracks other than changes 
	public LinkedList<Point2D> sectionPoints = new LinkedList<>();
	
	//Use this for all tracks that are points and make in spline format 
	public LinkedList<Point2D> splinePoints = new LinkedList<>();
	
	
	public enum trackType
	{
		UP,  				   //tracks above center of canvas going from left to right 
		
		DOWN, 				   //tracks below center of canvas going  from right to left 
		
		CHANGE_FOR_UP_START,   //change from up to down or up to up, goes **left to right** start of station
		CHANGE_FOR_UP_END,	   //At end of station , from down loop to main line 
		
		CHANGE_FOR_DOWN_START, //change from down to up or down to down , goes from **right to left**
		CHANGE_FOR_DOWN_END    //at end , change from up loop to main line 
				
	}
	
	
	/*
	 * Draw a main line from one end to another 
	 * start point and end point
	 * 
	 * USE THIS FOR MAIL LINE 
	 */
	
	public TrackSection(int x1 , int y1 , int x2 , int y2 , trackType track) 
	{
		this.startPoint =  new Point2D.Double(x1,y1);
		this.endPoint =  new Point2D.Double(x2,y2);
		this.track = track;
		
		createSectionPoints();
				
	}	
	
	
	/*
	 * from track number find distance from center by multiplying to trackGap find x
	 * coordinate by adding and subtracting length from center
	 * 
	 * USE THIS FOR TRACKS BEFORE PLATFORM 
	 */
	
	public TrackSection(int trackNumber , int trackGap  , int length , int Hcenter , int Vcenter , trackType track)
	{
		int x1 = Vcenter - (length/2); //left end
		int x2 = Vcenter + (length/2); //right end 
		
		int y1 = trackNumber * trackGap; //y1 same as y2
		
		if(track.equals(trackType.UP)) 
		{
			y1 = Hcenter - y1;
		}
		
		if(track.equals(trackType.DOWN)) 
		{
			y1 = Hcenter + y1;
		}
		
				
		this.startPoint = new Point2D.Double(x1 , y1);
		this.endPoint = new Point2D.Double(x2 , y1);
		this.track = track;
		
		createSectionPoints();
		
	}
	
	/*
	 * from track number find distance from center by multiplying to trackGap find x
	 * coordinate by adding and subtracting length from center
	 * 
	 * USE THIS FOR TRACK FOR PLATFORM
	 */
	
	public TrackSection(int trackNumber , int platfromNumber , int platformGap, int trackGap  , int length , int Hcenter , int Vcenter , trackType track)
	{
		int x1 = Vcenter - (length/2); //left end
		int x2 = Vcenter + (length/2); //right end 
		
		int y1 = trackNumber * trackGap; //y1 same as y2
		
		if(track.equals(trackType.UP)) 
		{
			y1 = y1 + ( platfromNumber * platformGap);
			y1 = Hcenter - y1;
			
			
		}
		
		if(track.equals(trackType.DOWN)) 
		{
			y1 += (platfromNumber * platformGap);
			y1 = Hcenter + y1;
			
		}
		
				
		this.startPoint = new Point2D.Double(x1 , y1);
		this.endPoint = new Point2D.Double(x2 , y1);
		this.track = track;
		
		createSectionPoints();
		}
	
	
	/*
	 * This is for track change , 
	 * going from up to up or down to down
	 * 
	 * 
	 * USE THIS FOR TRACK CHANGE 
	 */
	
	
	public TrackSection(int distanceFromCenter  , int startTrackHeight  , int Vcenter, int Hcenter ,  trackType track , int trackGap) 
	{
		int changeTiltDist = 60; //distance between the two coordinates for change determining the tilt 
		
		if(track.equals(trackType.CHANGE_FOR_UP_START)) 
		{
			int x1 = Vcenter - (distanceFromCenter/2);
			int y1 = Hcenter - startTrackHeight - trackGap;
			
			int x2 = x1 - changeTiltDist;
			int y2 = y1 + trackGap;
			
			this.startPoint = new Point2D.Double(x1 , y1);
			this.endPoint = new Point2D.Double(x2 , y2);
			this.track = track;
			
			createSectionSpline(track);
		}
		
		
		
		if(track.equals(trackType.CHANGE_FOR_UP_END)) 
		{
			int x1 = Vcenter + (distanceFromCenter/2);
			int y1 = Hcenter - startTrackHeight - trackGap;
			
			int x2 = x1 + changeTiltDist;
			int y2 = y1 + trackGap;
			
			this.startPoint = new Point2D.Double(x1 , y1);
			this.endPoint = new Point2D.Double(x2 , y2);
			this.track = track;
			
			
			createSectionSpline(track);
		}
		
		
		
		if(track.equals(trackType.CHANGE_FOR_DOWN_START)) 
		{
			int x1 = Vcenter - (distanceFromCenter/2);
			int y1 = Hcenter + startTrackHeight + trackGap;
			
			int x2 = x1 - changeTiltDist;
			int y2 = y1 - trackGap;
			
			this.startPoint = new Point2D.Double(x1 , y1);
			this.endPoint = new Point2D.Double(x2 , y2);
			this.track = track;
			
			
			createSectionSpline(track);
		}
		
		
		if(track.equals(trackType.CHANGE_FOR_DOWN_END)) 
		{
			int x1 = Vcenter + (distanceFromCenter/2);
			int y1 = Hcenter + startTrackHeight + trackGap;
			
			int x2 = x1 + changeTiltDist;
			int y2 = y1 - trackGap;
			
			this.startPoint = new Point2D.Double(x1 , y1);
			this.endPoint = new Point2D.Double(x2 , y2);
			this.track = track;
			
			
			createSectionSpline(track);
		}
		 
		
	}
	
	
	
	private void createSectionPoints() 
	{
		//Create points for allowing movement for train in smooth gap
		
		int length = (int)endPoint.getX() - (int)startPoint.getX();
		
		Point2D lastPoint = null;
		
		sectionPoints.add(startPoint);
		
		for(int i = 0; i < length/distanceBetweenControlPoints ; i++) 
		{
			lastPoint = sectionPoints.getLast();
			sectionPoints.add(new Point2D.Double(lastPoint.getX() + distanceBetweenControlPoints, lastPoint.getY()));
		}
		
		
		sectionPoints.add(endPoint);
	}
	
	
	
	private void createSectionSpline(trackType tt) 
	{
		/*
		 * USE FOR CHANGE SECTION 
		 * Create points for allowing movement for train in smooth gaps 
		 * 
		 * those points should be spline like smooth , no extra array for spline
		 */	
		
		//First add extra start and end points to draw the spline between them
		int extraControlPointOffset = 30; 
		
		//adding start to list 
		sectionPoints.add(startPoint);	
		
		if(tt.equals(trackType.CHANGE_FOR_UP_START) || tt.equals(trackType.CHANGE_FOR_DOWN_START)) 
		{
			sectionPoints.add(new Point2D.Double(sectionPoints.getFirst().getX() + extraControlPointOffset ,sectionPoints.getFirst().getY()));
			
			sectionPoints.add(endPoint);
			
			//add control point after end of change
			sectionPoints.add(new Point2D.Double(sectionPoints.getLast().getX() - extraControlPointOffset ,sectionPoints.getLast().getY() ));
						
						
		}
		
		
		
		if(tt.equals(trackType.CHANGE_FOR_UP_END) || tt.equals(trackType.CHANGE_FOR_DOWN_END)) 
		{
			sectionPoints.add(new Point2D.Double(sectionPoints.getFirst().getX() - extraControlPointOffset ,sectionPoints.getFirst().getY()));
			
			sectionPoints.add(endPoint);
			
			//add control point after end of change
			sectionPoints.add(new Point2D.Double(sectionPoints.getLast().getX() + extraControlPointOffset ,sectionPoints.getLast().getY() ));						
						
		}
		
		
		
		
		
		/*
		 * Spline math return 
		 * in a linked list for drawing
		 * 
		 * 
		 *|-------------------------  SPLINE MATH --------------------------|
		 */
		
		double P0X = sectionPoints.get(1).getX();
		double P0Y = sectionPoints.get(1).getY();
		
		double P1X = sectionPoints.get(0).getX();
		double P1Y = sectionPoints.get(0).getY();
		
		double P2X = sectionPoints.get(2).getX();
		double P2Y = sectionPoints.get(2).getY();
		
		double P3X = sectionPoints.get(3).getX();
		double P3Y = sectionPoints.get(3).getY();
		
		double QX;
		double QY;
		
				
		
		for (double t = 0; t <= 1.0; t+= 0.1) 
		{
			QX = 0.5 * ((2*P1X) + (-P0X + P2X)*t + (2*P0X - 5*P1X + 4*P2X - P3X)*t*t + (3*P1X - 3*P2X - P0X + P3X)*t*t*t);
			QY = 0.5 * ((2*P1Y) + (-P0Y + P2Y)*t + (2*P0Y - 5*P1Y + 4*P2Y - P3Y)*t*t + (3*P1Y - 3*P2Y - P0Y + P3Y)*t*t*t);
			
			splinePoints.add(new Point2D.Double(QX,QY));
		}
		
		
		
		
	}
	
	public void drawSection(Graphics2D g) 
	{
		
		g.setStroke(new BasicStroke(trackWidth));
		
		g.setColor(Color.gray);
		
		if(this.track.equals(trackType.UP) || 
			this.track.equals(trackType.CHANGE_FOR_UP_START) ||
			this.track.equals(trackType.CHANGE_FOR_UP_END))
		{
			g.setColor(Color.DARK_GRAY);
		}
		
		g.drawLine( (int)startPoint.getX(), 
				(int)startPoint.getY(), 
				(int)endPoint.getX(), 
				(int)endPoint.getY());
		
		
		//for main or loop lines Points for train navigation
		
		if(this.track.equals(trackType.UP) || this.track.equals(trackType.DOWN)) 
		{
			for(Point2D p : sectionPoints) 
			{
				g.setColor(Color.green);
				g.drawOval( (int)p.getX()- 1 , (int)p.getY() - 1 , 2 ,2 );
			}
		}
		
		
		//for track change lines train navigation , draws control point for spline
		
		if(this.track.equals(trackType.CHANGE_FOR_DOWN_START) || 
		   this.track.equals(trackType.CHANGE_FOR_DOWN_END) || 
           this.track.equals(trackType.CHANGE_FOR_UP_START)|| 
           this.track.equals(trackType.CHANGE_FOR_UP_END)) 
		{
			
			for(Point2D p : splinePoints) 
			{
				g.setColor(Color.red);
				g.drawOval( (int)p.getX()- 1 , (int)p.getY() - 1 , 2 ,2);
			}
		}
		
		
		
		
	}
	
	
	
	
	
	
	

}
