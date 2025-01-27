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
	
	/*
	 * -----------------------size specifications--------------------------
	 */
	private int sigX,sigY;     //coordinates for the start of the rectangle 
	
	private int trackX,trackY; //coordinates of the point of the track where it is drawn 
	
	public signalType signal;
	private TrackSection track;
	
	private int verticlPosFlag = 1;    // 1 = above the tracks , -1 = below the tracks  
	private int horizontalPosFlag = 1; // 1 = facing toward right, -1 = facing toward left 
	private int startEndFlag = 0;      //0 = start of the switch from right , 1 = end of switch 
	
	
	//size of the rectangle
	public static int containerLength = 28;
	public static int containerWidth = 12;
	
	//horizontal and vertical gap from the track 
	private int vGap = 5;
	private int hGap = 3;
	
	//light circle radius 
	private int radius = 6;
	
	
	/*
	 * ---------------------------------------Working elements----------------------
	 */
	
	//signal states , green = 0, double yellow = 1 , yellow = 2 , red =  3
	private int STATE  = 3;
	
	private int blockNo;
	private int blockOffset;


	private Signal prevSignal;
	
	public Signal(signalType signal , TrackSection track , int horizontalPosFlag , int verticlPosFlag , int startEndFlag) 
	{
		this.signal = signal;
		this.track = track;
		this.horizontalPosFlag = horizontalPosFlag;
		this.verticlPosFlag = verticlPosFlag;
		this.startEndFlag = startEndFlag;
	}
	
	
	public Signal(signalType signal , TrackSection track , Signal prevSignal , int blockNo  , int horizontalPosFlag , int verticlPosFlag) 
	{
		this.signal = signal;
		this.track = track;
		this.prevSignal = prevSignal;
		this.blockNo = blockNo;
		this.horizontalPosFlag = horizontalPosFlag;
		this.verticlPosFlag = verticlPosFlag;
	}
	
	
	
	public void clock() 
	{
		
	}
	
	
	
	
	
	
	
	private void computeRectangel() 
	{
		
		if(signal.equals(signalType.HOME)) 
		{
			if(startEndFlag == 0) 
			{
			
				trackX = track.getX1();
				trackY = track.getY1();
				
			}else {
				
				trackX = track.getX2();
				trackY = track.getY2();
			}
		}else {
			
			trackX = (int)(blockNo * blockOffset);
			trackY = track.getY1();
			
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
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRoundRect(sigX, sigY, containerLength, containerWidth , 10 ,10);
		
		
		int recVerticalGap = containerWidth/2;
		int recHorizontalGap = containerLength/3;
		
		
		for(int i = 1; i <= 3; i++) 
		{
			int cX = sigX + (recHorizontalGap * i) - radius;
			int cY = sigY + recVerticalGap - (radius/2);
			
			
			
			if(horizontalPosFlag == 1) 
			{
				if(STATE == 0 && i == 3) 
				{
					g2d.setColor(Color.GREEN);
				}else 
				
				if(STATE == 1 && i == 1 || STATE == 1 && i == 3) 
				{
					g2d.setColor(Color.YELLOW);
					
				}else 
				
				if(STATE == 2 && i == 2)
				{
					g2d.setColor(Color.YELLOW);
				}else 
					
				if(STATE == 3 && i == 1)
				{
					g2d.setColor(Color.RED);
				}else
				
				{g2d.setColor(Color.DARK_GRAY);}
				
				
				
			}else {
				
				if(STATE == 0 && i == 1) 
				{
					g2d.setColor(Color.GREEN);
					
				}else 
				
				if(STATE == 1 && i == 1 || STATE == 1 && i == 3) 
				{
					g2d.setColor(Color.YELLOW);
				}else 
				
				if(STATE == 2 && i == 2) 
				{
					g2d.setColor(Color.YELLOW);
				}else 
				
				if(STATE == 3 && i == 3) 
				{
					g2d.setColor(Color.RED);
				}else
					
				{g2d.setColor(Color.DARK_GRAY);}
				
				
			}
			
			g2d.fillOval(cX, cY, radius, radius);
		}
		
		
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

	public int getBlockOffset() {
		return blockOffset;
	}


	public void setBlockOffset(int blockOffset) {
		this.blockOffset = blockOffset;
	}
}
