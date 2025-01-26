package RenderingElements.Tracks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;


public class TrackSection {
	
	/*
	 * |--------------------  What this class needs to do -------------------------|
	 * 
	 *  
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

	
	public static int trackWidth = 3; //width of track	
	public static int switchTrackWidth = 2; //width of track of switch part 
	
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
	
	
	
	
	//-------------COMMON-----------------
	//coordinates of start and end of the line which is drawn 
	private int x1,y1,x2,y2 = 0;
	 
	
	
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

	
	
	
	
	
	

}
