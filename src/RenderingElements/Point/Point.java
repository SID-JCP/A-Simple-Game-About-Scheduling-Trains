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
	
	public static enum pointType
	{
		DOWN_UP_START,
		DOWN_UP_END,
		UP_DOWN_START,
		UP_DOWN_END
		
	}

	//coordinate of the exact start of the track change 
	private Point2D pos;
	
	//route = 0 if no change in line 
	//route = 1 if change in line 
	public int route = 0; 
	
	
	private final int pointTrackGap = 20;
	private final int pointSize = 10;
	
	
	TrackSection trackRoute1;
	TrackSection trackRoute2;
	
	
	TrackSection changeTrack;
	

	/* GET THE LAST AND FIRST DOTS OF THE CHANGE TRACK AND DRAW POINT ON THEM */
	
	public Point(TrackSection changeTrack , TrackSection trackRoute1 , TrackSection trackRoute2 , pointType point) 
	{
		//draw above track
		if(point.equals(pointType.DOWN_UP_START)) 
		{
//			(int)changeTrack.sectionPoints.getFirst().getX();
		}
		
		//draw below track 
		if(point.equals(pointType.DOWN_UP_END)) 
		{
//			(int)changeTrack.sectionPoints.getFirst().getX();
		}
		
		
		//draw below track
		if(point.equals(pointType.UP_DOWN_START)) 
		{
//			(int)changeTrack.sectionPoints.getFirst().getX();
		}
		
		//draw above track 
		if(point.equals(pointType.UP_DOWN_END)) 
		{
//			(int)changeTrack.sectionPoints.getFirst().getX();
		}
		
		
	}
	
	
	
	
	public void draw(Graphics2D g) 
	{
		
		g.setColor(Color.RED);
		
		g.fillRect((int)pos.getX() , (int)pos.getY(),pointSize , pointSize);
		
	}
}
