package RenderingElements.Tracks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;

import RenderingElements.Signal.Signal;


public class TrackSection {
	


	 //width of track
	public static int trackWidth = 3;	

	 //width of track of switch part 
	public static int switchTrackWidth = 2;
	
	public static enum trackType
	{
		UP,  				//tracks above center of canvas going from left to right 
		
		DOWN, 				//tracks below center of canvas going  from right to left 
		
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
	
	
	
	
	//---------------------ONLY FOR SWITCHES--------------------------
	//start and end straight tracks  , getting connected by the switch
	private TrackSection s1;
	private TrackSection s2;
	
	//start of the switch from right of the screen
	private int startBlockNo;
	
	// 0 = means not active train can not change , 1 = train change from Up to Down , 2 = train change from Down to Up
	private int STATE = 0; 
	
	//center of start circle
	private int Xs , Ys;
	
	//center of end circle
	private int Xe , Ye;
	
	private int switchRadius = 10;


    //---------------------------For clocking by clicking---------------------
	
	//center of the hover area circle
	private int Xc , Yc = 0;

	//hover area circle 
	private int radius = 20;
	
	
	//-------------COMMON-----------------
	//coordinates of start and end of the line which is drawn 
	private int x1,y1,x2,y2 = 0;
	

	
	 
	//--------------WORKING ELEMENTS-------------------
	private boolean hover = false;
	
	
	private Signal Signal_left_up;
	private Signal Signal_right_up;
	private Signal Signal_left_down;
	private Signal Signal_right_down;
	
	
	


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
	
	
	//check if train has entered the start of Up switch 
	public boolean detectStartOfUpSwitch(int trainX , int trainY) 
	{
		int distanceSquared = (trainX - x1) * (trainX - x1) + (trainY - y1) * (trainY - y1);
        return distanceSquared <= switchRadius * switchRadius;
		
	}
	
	//check if train has reached the end of Up switch 
	public boolean detectEndOfUpSwitch(int trainX , int trainY) 
	{
		int distanceSquared = (trainX - x2) * (trainX - x2) + (trainY - y2) * (trainY - y2);
        return distanceSquared <= switchRadius * switchRadius;
	}
	
	
	//check if train has entered the start of DOWN switch 
	public boolean detectStartOfDownSwitch(int trainX , int trainY) 
	{
		int distanceSquared = (trainX - x2) * (trainX - x2) + (trainY - y2) * (trainY - y2);
        return distanceSquared <= switchRadius * switchRadius;
		
	}
	
	
	public boolean isCursorInside(int mouseX , int mouseY) 
	{
		        
		Xc = (int)(x2 + x1)/2;
		Yc = (int)(y2 + y1)/2;
		
		int dx = mouseX - Xc;
		int dy = mouseY - Yc;
		int distanceSquared = dx * dx + dy * dy;
		
		
        
		if( distanceSquared <= radius * radius)
        {
        	hover = true;
        	
        	return true;
        	
        }else {hover = false;}
		
        
      return false;
	}
	
	
	public void clock() 
	{
		
		//----------------------------changing state---------------------
		
		// 0 = means not active train can not change , 1 = train change from Up to Down , 2 = train change from Down to Up
		if(STATE == 2) 
		{
			STATE  = 0;
			
		}else {STATE++;}
		
		//|---------------------------CHANGING SIGNALS----------------------
		
		
		if(STATE == 1) 
		{
			if(track.equals(trackType.UP_END) || track.equals(trackType.DOWN_START)) 
			{
				Signal_left_up.setSTATE(2);
				Signal_right_up.setSTATE(3);
				
				Signal_left_down.setSTATE(3);
				Signal_right_down.setSTATE(3);
			}
			
			// opposite direction 
			if(track.equals(trackType.UP_START) || track.equals(trackType.DOWN_END)) 
			{
				Signal_left_up.setSTATE(2);
				Signal_right_up.setSTATE(3);
				
				Signal_left_down.setSTATE(3);
				Signal_right_down.setSTATE(3);
			}
			

		}
		
		
		if(STATE == 2) 
		{
			//opposite
			
			if(track.equals(trackType.UP_END) || track.equals(trackType.DOWN_START)) 
			{
				Signal_left_up.setSTATE(3);
				Signal_right_up.setSTATE(3);
				
				Signal_left_down.setSTATE(3);
				Signal_right_down.setSTATE(2);
			}
			
			
			if(track.equals(trackType.UP_START) || track.equals(trackType.DOWN_END)) 
			{
				Signal_left_up.setSTATE(3);
				Signal_right_up.setSTATE(3);
				
				Signal_left_down.setSTATE(3);
				Signal_right_down.setSTATE(2);
			}
		}
		
		if(STATE == 0) 
		{
			Signal_left_up.setSTATE(3);
			Signal_right_up.setSTATE(3);
			
			Signal_left_down.setSTATE(3);
			Signal_right_down.setSTATE(3);		
			
		} 
		
		
		if(STATE == 1 || STATE == 2) 
		{
			Signal_left_up.setLock(true);
			Signal_right_up.setLock(true);
			Signal_left_down.setLock(true);
			Signal_right_down.setLock(true);
		}else {
			
			Signal_left_up.setLock(false);
			Signal_right_up.setLock(false);
			Signal_left_down.setLock(false);
			Signal_right_down.setLock(false);
		}
		
	}
	
	
	public void setSignals(Signal left_up , Signal right_up , Signal left_down , Signal right_down) 
	{
		this.Signal_left_up = left_up;
		this.Signal_right_up = right_up;
		
		this.Signal_left_down = left_down;
		this.Signal_right_down = right_down;
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

	
	public int getSTATE() {
		return STATE;
	}


	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}
	
	public int getXs() {
		return Xs;
	}


	public void setXs(int xs) {
		Xs = xs;
	}


	public int getYs() {
		return Ys;
	}


	public void setYs(int ys) {
		Ys = ys;
	}


	public int getXe() {
		return Xe;
	}


	public void setXe(int xe) {
		Xe = xe;
	}


	public int getYe() {
		return Ye;
	}


	public void setYe(int ye) {
		Ye = ye;
	}
	
	public int getSwitchRadius() {
		return switchRadius;
	}


	public void setSwitchRadius(int switchRadius) {
		this.switchRadius = switchRadius;
	}


}
