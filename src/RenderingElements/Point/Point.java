package RenderingElements.Point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import RenderingElements.Tracks.TrackSection;

public class Point {
	
	/*
	 * |------------------------What this need to do---------------------------|
	 * 
	 * It should have info of the two sections that it can send the train to
	 * that mean the section objects should be in this class also this would with drawn in the station section maps
	 * 
	 * there should be a collision area which is checked by tsap controller for all trains 
	 * 
	 * if train enter the collision area then the tsp controller will decide the next section  
	 * of train depending on the point status 
	 * 
	 */
	
	/*
	 * SOME MAIN THINGS FOR A POINT 
	 * 
	 * It either change the route , or it make it continue in the same directon
	 * 
	 * It makes train go to upline or downline 
	 * 
	 * A point can be CLICKED to change the route of train 
	 * 
	 * 
	 */
	
	/*
	 * POSITIONING OF POINT
	 * 
	 * point is at start or end of section
	 * 
	 * provide the section to change to or from and draw the point using the first
	 * or last point from that sectin
	 */
	
	
	
	public static enum pointType
	{
		DOWN_UP,
		UP_DOWN
		
	}

	//coordinate of first or last point of track change section 
	
	private Point2D pos;
	
//	coordinates of the point calculated from pos 
	int xPos;
	int yPos;
	
	//route = 0 if no change in line 
	//route = 1 if change in line 
	public int route = 0; 
	
	
	private final int pointTrackGap = 5;
	private final int pointSize = 15;
	
	
	TrackSection trackRoute1;
	TrackSection trackRoute2;
	
	
	TrackSection changeTrack;
	

	/* GET THE LAST AND FIRST DOTS OF THE CHANGE TRACK AND DRAW POINT ON THEM */
	
	public Point(TrackSection changeTrack , TrackSection trackRoute1 , TrackSection trackRoute2 , pointType point) 
	{
		//draw above track
		if(point.equals(pointType.DOWN_UP)) 
		{
			/*
			 * get the first point for the change track
			 * 
			 * draw the point some distance above the first coordinate
			 */
			
			//first for down to up 
			pos = changeTrack.sectionPoints.getFirst();
			
			xPos = (int)pos.getX() - (pointTrackGap - pointSize/2);
			yPos = (int)pos.getY() - (pointTrackGap - pointSize/2);
			
		}

		
		
		//draw below track
		if(point.equals(pointType.UP_DOWN)) 
		{

		}

		
	}
	
	
	
	
	public void draw(Graphics2D g) 
	{
		
		g.setColor(Color.RED);
		
		g.fillRect(xPos , yPos, pointSize , pointSize);
		
	}
}
