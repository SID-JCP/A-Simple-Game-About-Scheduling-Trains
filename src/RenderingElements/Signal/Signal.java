package RenderingElements.Signal;

import java.awt.Color;
import java.awt.Graphics2D;

import RenderingElements.Tracks.TrackSection;

public class Signal {
	
	public static enum signalType
	{
		HOME,  //to be used for switches    
		
		BLOCK  //to be used for main routes 
				
	}
	
	
	private int sigX,sigY; //coordinates for the start of the rectangle 
	
	private int trackX,trackY; //coordinates of the point of the track where it is drawn 
	
	private signalType signal;
	private TrackSection track;
	
	private int verticlPosFlag = 1; // 1 = above the tracks , -1 = below the tracks  
	private int horizontalPosFlag = 1; // 1 = facing toward right, -1 = facing toward left 
	private int startEndFlag = 0; //0 = start of the switch from right , 1 = end of switch 
	
	
	//size of the rectangle
	public static int containerLength = 24;
	public static int containerWidth = 12;
	
	//horizontal and vertical gap from the track 
	private int vGap = 5;
	private int hGap = 3;
	
	
	public Signal(signalType signal , TrackSection track , int horizontalPosFlag , int verticlPosFlag , int startEndFlag) 
	{
		this.signal = signal;
		this.track = track;
		this.horizontalPosFlag = horizontalPosFlag;
		this.verticlPosFlag = verticlPosFlag;
		this.startEndFlag = startEndFlag;
	}
	
	private void computeRectangel() 
	{
		if(startEndFlag == 0) 
		{
		
			trackX = track.getX1();
			trackY = track.getY1();
			
		}else {
			
			trackX = track.getX2();
			trackY = track.getY2();
		}
		
		
		if(verticlPosFlag == 1) 
		{
			if(horizontalPosFlag == 1) 
			{				
				sigX = trackX + hGap;				
			}else {				
				sigX = trackX - hGap - containerLength;
			}
						
			sigY = trackY - vGap - containerWidth;
						
			
		}else {
			
			if(horizontalPosFlag == 1) 
			{				
				sigX = trackX + hGap;				
			}else {				
				sigX = trackX - hGap - containerLength;
			}
			
			sigY = trackY + vGap;
			
		}
	}
	
	
	
	public void draw(Graphics2D g2d) 
	{
		computeRectangel();
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(sigX, sigY, containerLength, containerWidth , 10,15);
	}
	
	
	public int getSigX() {
		return sigX;
	}

	public void setSigX(int sigX) {
		this.sigX = sigX;
	}

	public int getSigY() {
		return sigY;
	}

	public void setSigY(int sigY) {
		this.sigY = sigY;
	}

	public int getTrackX() {
		return trackX;
	}

	public void setTrackX(int trackX) {
		this.trackX = trackX;
	}

	public int getTrackY() {
		return trackY;
	}

	public void setTrackY(int trackY) {
		this.trackY = trackY;
	}

}
