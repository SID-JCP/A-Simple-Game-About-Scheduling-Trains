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

	
	private int trackWidth = 3; //width of track	
	
	private trackType track;
	
	

	/*
	 * 1 - n for  lines 
	 * 
	 * 1 = before or after center 
	 *
	 */
	private int trackNum;
	
	

	/*
	 * NOT ACTUAL LENGTH , unit from horizontal elements 
	 * 
	 * -1 for main lines 
	 */
	private int trackLength;
	
	
	
	
	
	public static enum trackType
	{
		UP,  				   //tracks above center of canvas going from left to right 
		
		DOWN, 				   //tracks below center of canvas going  from right to left 
		
		CHANGE_FOR_UP_START,   //change from up to down or up to up, goes **left to right** start of station
		CHANGE_FOR_UP_END,	   //At end of station , from down loop to main line 
		
		CHANGE_FOR_DOWN_START, //change from down to up or down to down , goes from **right to left**
		CHANGE_FOR_DOWN_END    //at end , change from up loop to main line 
				
	}
	
	
	public TrackSection(trackType type , int trackNum , int trackLength) 
	{
		this.track = type;
		this.trackNum = trackNum;
		this.trackLength = trackLength;
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


	
	
	
	
	
	

}
