package RenderingElements.Tracks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;


public class TrackSection {
	


	 //width of track
	public static int trackWidth = 3;	

	 //width of track of switch part 
	public static int switchTrackWidth = 2;
	
	public static enum trackType
	{
		UP,  				   //tracks above center of canvas going from left to right 
		
		DOWN, 				   //tracks below center of canvas going  from right to left 
		
		UP_START,   		//change from up to down or up to up, goes **left to right** start of station
		UP_END,	   			//At end of station , from down loop to main line 
		
		DOWN_START, 		//change from down to up or down to down , goes from **right to left**
		DOWN_END    		//at end , change from up loop to main line 
				
	}
	
	
	
	//----------------COMMON---------------
	private trackType track;

	/*
	 * 1 - n for  lines 
	 * 
	 * 1 = before or after center 
	 *
	 */
	
	
	//--------------------FOR STRAIGHT TRACKS--------------------
	private int trackNum;
	
	/*
	 * NOT ACTUAL LENGTH , unit from horizontal elements 
	 * 
	 * -1 for main lines 
	 */
	private int trackLength;
	
	
	
	
	//---------------ONLY FOR SWITCHES--------------------------
	//start and end straight tracks  , getting connected by the switch
	private TrackSection s1;
	private TrackSection s2;
	
	//start of the switch from right of the screen
	private int startBlockNo;
	
	// 0 = means not active train can get on , 1 = active train can pass 
	private int STATE = 0; 
	
	//center of the hover area circle
	private int Xc , Yc = 0;
	
	


	//hover area circle 
	private int radius = 20;
	
	
	//-------------COMMON-----------------
	//coordinates of start and end of the line which is drawn 
	private int x1,y1,x2,y2 = 0;
	
	
	
	
	 
	//--------------WORKING ELEMENTS-------------------
	private boolean hover = false;
	
	
	
	
	
	
	


	//For normal straight tracks 
	public TrackSection(trackType type , int trackNum , int trackLength) 
	{
		this.track = type;
		this.trackNum = trackNum;
		this.trackLength = trackLength;
	}
	
	
	//For track section of a switch 
	public TrackSection(trackType type , TrackSection s1 , TrackSection s2 , int startBlockNo) 
	{
		this.track = type;
		this.s1 = s1;
		this.s2 = s2;
		this.startBlockNo = startBlockNo;
		
	}
	
	
	public boolean isCursorInside(int mouseX , int mouseY) 
	{
		
        
		Xc = (int)(x2 + x1)/2;
		Yc = (int)(y2 + y1)/2;
		
		int dx = mouseX - Xc;
		int dy = mouseY - Yc;
		int distanceSquared = dx * dx + dy * dy;
		
		
        
		if( distanceSquared <= radius * radius   )
        {
        	hover = true;
        	
        	return true;
        	
        }else {hover = false;}
        
      return false;
	}
	
	
	
	
	
	public trackType getTrackType() {
		return track;
	}

	
	public int getTrackNum() {
		return trackNum;
	}


	public int getTrackLength() {
		return trackLength;
	}

	public int getX1() {
		return x1;
	}


	public void setX1(int x1) {
		this.x1 = x1;
	}


	public int getY1() {
		return y1;
	}


	public void setY1(int y1) {
		this.y1 = y1;
	}


	public int getX2() {
		return x2;
	}


	public void setX2(int x2) {
		this.x2 = x2;
	}


	public int getY2() {
		return y2;
	}


	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	
	public TrackSection getS1() {
		return s1;
	}


	public void setS1(TrackSection s1) {
		this.s1 = s1;
	}


	public TrackSection getS2() {
		return s2;
	}


	public void setS2(TrackSection s2) {
		this.s2 = s2;
	}


	public int getStartBlockNo() {
		return startBlockNo;
	}


	public void setStartBlockNo(int startBlockNo) {
		this.startBlockNo = startBlockNo;
	}

	
	public boolean isHover() {
		return hover;
	}


	public void setHover(boolean hover) {
		this.hover = hover;
	}
	
	public int getXc() {
		return Xc;
	}


	public void setXc(int xc) {
		Xc = xc;
	}


	public int getYc() {
		return Yc;
	}


	public void setYc(int yc) {
		Yc = yc;
	}
	
	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


}
