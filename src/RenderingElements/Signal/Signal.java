package RenderingElements.Signal;

import java.awt.Color;
import java.awt.Graphics2D;

import InputManager.MouseInputManager;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Train.Train;

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
	
	private int trackCircuitDistance = 50; //the gap between signal and train detect area on left or right
	
	private int detectBoxSize = 15; //size of the box for visual purpos
	
	
	public signalType signal;
	
	private TrackSection track;
	
	private int verticlPosFlag = 1;    // 1 = above the tracks , -1 = below the tracks  
	private int horizontalPosFlag = 1; // 1 = left of switch for up direction , - 1 = right of switch for down line 


	private int startEndFlag = 0;      //0 = start of the switch from right , 1 = end of switch 
	
	private boolean alone = false;    // is the signal is a home signal and is not on any switch 
	
	//size of the rectangle
	public static int containerLength = 28;
	public static int containerWidth = 12;
	
	//horizontal and vertical gap from the track 
	private int vGap = 5;
	private int hGap = 3;
	
	//light circle radius 
	private int radius = 6;
	
	//train detect box
	private int x1,y1,x2,y2,x3,y3,x4,y4;
	
	
	/*
	 * ---------------------------------------Working elements----------------------
	 */
	
	//signal states , green = 0, double yellow = 1 , yellow = 2 , red =  3
	private int STATE  = 0;


	private int blockNo;
	private int blockOffset;

	
	private boolean hover = false;

	private Signal prevSignal;
	
	private Train clockTrain;
	
	
	//use for switches 
	public Signal(signalType signal , TrackSection track , int horizontalPosFlag , int verticlPosFlag , int startEndFlag) 
	{
		this.signal = signal;
		this.track = track;
		this.horizontalPosFlag = horizontalPosFlag;
		this.verticlPosFlag = verticlPosFlag;
		this.startEndFlag = startEndFlag;
	}
	
	//use for block section signals 
	public Signal(signalType signal , TrackSection track , Signal prevSignal , int blockNo  , int horizontalPosFlag , int verticlPosFlag) 
	{
		this.signal = signal;
		this.track = track;
		this.prevSignal = prevSignal;
		this.blockNo = blockNo;
		this.horizontalPosFlag = horizontalPosFlag;
		this.verticlPosFlag = verticlPosFlag;
	}
	
	//use for home signals without switches [IMPORTANT THIS IS ONLY TO MAKE ALONE TRUE]
	public Signal(signalType signal , TrackSection track , int blockNo  , int horizontalPosFlag , int verticlPosFlag , boolean alone) 
	{
		this.signal = signal;
		this.track = track;
		this.blockNo = blockNo;
		this.horizontalPosFlag = horizontalPosFlag;
		this.verticlPosFlag = verticlPosFlag;
		this.alone = alone;
		
	}
	
	
	
		
	public void clock(int flag, Train currectTrain) 
	{
	
		
		if(signal.equals(signalType.HOME)) 
		{
			//gets clicked
			if(flag == 3) 
			{
				if(STATE <= 3 && STATE != 0) {STATE--;}else {STATE = 3;}
			}else {STATE = 3;}
			
			
		}
		
		
		if(signal.equals(signalType.BLOCK)) 
		{
			//train passes , clocked by a train 
			if(flag == 0) 
			{
				STATE = 3;
				
				//set the clock by the train which passed over it 
				clockTrain = currectTrain;
				
				if(prevSignal != null) 
				{
					prevSignal.clock(1  , clockTrain);
					
				}
				
			}
			
			//gets clocked by the next signal 
			if(flag == 1)
			{
				
				
				if(clockTrain == currectTrain) 
				{
					if(STATE != 0) {STATE--;}
					
					if(prevSignal != null) 
					{
						prevSignal.clock(1  , clockTrain);
						
					}
					
				}

		
			}
			
			//clicked the the person 
			if(flag == 3) 
			{
				if(STATE == 3){STATE = 2;}
			}
			
		}

		
	}
	
	
	
	
	
	
	
	private void computeRectangel() 
	{
		
		if(signal.equals(signalType.HOME)) 
		{
			if(alone) 
			{
				trackX = (int)(blockNo * blockOffset);
				trackY = track.getY1();
				
			}else {
				
				if(startEndFlag == 0) 
				{
				
					trackX = track.getX1();
					trackY = track.getY1();
					
				}else {
					
					trackX = track.getX2();
					trackY = track.getY2();
				}
				
			}
			

		}else {
			
			trackX = (int)(blockNo * blockOffset);
			trackY = track.getY1();
			
		}
		
		if(horizontalPosFlag == 1) 
		{	
			sigX = trackX - hGap - containerLength;		
		}else {				
			sigX = trackX + hGap;		
		}
		
		
		
		if(verticlPosFlag == 1) 
		{
			sigY = trackY - vGap - containerWidth;
						
		}else {
			
			sigY = trackY + vGap;
			
		}
	}
	
	
	
	
	
	//display hint such as showing a box around to show mouse is hovering over the singal 
	public boolean isCursorInside(int mouseX , int mouseY) 
	{
		
		int x1 = sigX;
		int y1 = sigY;
		
		int x2 = sigX + containerLength;
		int y2 = sigY;
		
		int x3 = sigX + containerLength;
		int y3 = sigY + containerWidth;
		
		int x4 = sigX;
		int y4 = sigY + containerWidth;
		
		// Vectors AB and AD
        int ABx = x2 - x1;
        int ABy = y2 - y1;
        int ADx = x4 - x1;
        int ADy = y4 - y1;
        
        // Vector AM
        int AMx = mouseX - x1;
        int AMy = mouseY - y1;
		
        
        
        int AM_AB = AMx * ABx + AMy * ABy;
        int AB_AB = ABx * ABx + ABy * ABy;
        int AM_AD = AMx * ADx + AMy * ADy;
        int AD_AD = ADx * ADx + ADy * ADy;
        
        
        
        
        if((0 <= AM_AB && AM_AB <= AB_AB) && (0 <= AM_AD && AM_AD <= AD_AD))
        {
        	hover = true;
        	
        	return true;
        	
        }else {hover = false;}
        
      return false;
	}
	
	public boolean detectTrain(int x , int y) 
	{
		
		
		
		if(horizontalPosFlag == 1) 
		{
			 x1 = trackX - trackCircuitDistance - detectBoxSize/2;
			 y1 = trackY - detectBoxSize/2;
			
			 x2 = x1 + detectBoxSize;
			 y2 = y1;
			
			 x3 = x1 + detectBoxSize;
			 y3 = y1 + detectBoxSize;
			
			 x4 = x1;
			 y4 = y1 + detectBoxSize;
			
		}else{
			
			 x1 = trackX + trackCircuitDistance - detectBoxSize/2;
			 y1 = trackY - detectBoxSize/2;
			
			 x2 = x1 + detectBoxSize;
			 y2 = y1;
			
			 x3 = x1 + detectBoxSize;
			 y3 = y1 + detectBoxSize;
			
			 x4 = x1;
			 y4 = y1 + detectBoxSize;
			
		}
		
		
		// Vectors AB and AD
        int ABx = x2 - x1;
        int ABy = y2 - y1;
        int ADx = x4 - x1;
        int ADy = y4 - y1;
        
        // Vector AM
        int AMx = x - x1;
        int AMy = y - y1;
		
        
        
        int AM_AB = AMx * ABx + AMy * ABy;
        int AB_AB = ABx * ABx + ABy * ABy;
        int AM_AD = AMx * ADx + AMy * ADy;
        int AD_AD = ADx * ADx + ADy * ADy;
        
        
		
		
        if((0 <= AM_AB && AM_AB <= AB_AB) && (0 <= AM_AD && AM_AD <= AD_AD))
        {
        	
        	
        	return true;
        	
        }
        
        return false;
	}
	

	
	
	public void draw(Graphics2D g2d) 
	{
		computeRectangel();
		
		
		
		if(hover) 
		{
			g2d.setColor(Color.WHITE);
			g2d.fillRoundRect(sigX - 2 , sigY - 2, containerLength + 4, containerWidth + 3, 15 ,15);
		}
		
		//up line signal 
		if(horizontalPosFlag == 1) 
		{
			g2d.setColor(Color.GRAY);
			g2d.fillRoundRect(sigX, sigY, containerLength, containerWidth , 10 ,10);
			
			
		}else {
			
			g2d.setColor(Color.GRAY.darker());
			g2d.fillRoundRect(sigX, sigY, containerLength, containerWidth , 10 ,10);
			
		}
		
		
		
		
		
		int recVerticalGap = containerWidth/2;
		int recHorizontalGap = containerLength/3;
		
		
		for(int i = 1; i <= 3; i++) 
		{
			int cX = sigX + (recHorizontalGap * i) - radius;
			int cY = sigY + recVerticalGap - (radius/2);
			
			
			
			if(horizontalPosFlag == 1) 
			{
//				g2d.setColor(Color.red.darker().darker());
//				g2d.fillRect(
//						x1, 
//						y1,
//						detectBoxSize,
//						detectBoxSize);
				
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
				
//				g2d.setColor(Color.red.brighter().brighter());
//				g2d.fillRect(
//						trackX + trackCircuitDistance - detectBoxSize/2, 
//						trackY - detectBoxSize/2,
//						detectBoxSize,
//						detectBoxSize);
				
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
	
	public int getSTATE() {
		return STATE;
	}


	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}
	
	public int getHorizontalPosFlag() {
		return horizontalPosFlag;
	}


	public void setHorizontalPosFlag(int horizontalPosFlag) {
		this.horizontalPosFlag = horizontalPosFlag;
	}
}
