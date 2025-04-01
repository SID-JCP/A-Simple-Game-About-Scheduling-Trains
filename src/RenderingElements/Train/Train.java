package RenderingElements.Train;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import RenderingElements.Canvas;
import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;

public class Train {
	
	
	public static final double deployGap = 150.0; // the distance before a signal where the train is to be spawned 
	
	public int deployState = 0;   // 0 = not deployed , 1 = deployed , 2 = deployment done and exited screen , 3 = in wait queue

	public long deployTime = 0l;  // time at which train need to be deployed
	
	public int moveDirection = 0; // 0 = left to right , 1 = right to left
	
	public int trackNumber = 1; // 1 to N denote Up main line tracks , -1 to -N denote Down line tracks 
	
	public TrackSection currentSection; //current track on which train is 
	
	public int lineTravelling = 0; //0 = train should be on up line , 1 = train should be on down line 
	
	public boolean hasHault = true; // if the train should stop at the station or not 
	
	public boolean nextSectionClear = true; // to be used for deployment of the train , check if the train before has passed 
	
	//|--------------------------Movement Data--------------------------------|
	
	

	//left side of the train
	private double x1,y1;
	
	//right side of the train 
	private double x2,y2;
	
	//Distance between front and back points of train 
	private double trainLength = 80.0;
	
	//green signal speed
	public static double Gspeed = 35.0;
	
	//double yellow speed 
	public static double YYspeed = 20.0;
	
	//single yellow speed
	public static double Yspeed = 10.0;
	
	//|--------red is zero-------|
	
	private double currentSpeed = Yspeed;
	
	
	private int trainFrontX , trainFrontY , trainBackX , trainBackY;
	
	//--------------------------SIGNAL AND SECTION related data----------------------|
	
	//signal to see
	Signal nextSignal;
	
	//signal which is just passed
	Signal lastClockedSignal;
	
	//no of signals the train has passed 
	public int clockCount = 0;

	//state of the signal which was before the train passed
	int currentSignalState = 0;
	
	//---------------------------SWITCH RELATED DATA------------------------------------|
	TrackSection newSwitch;
	
	private boolean frontMovingOnSwitch =  false;
	
	private boolean backMovingOnSwitch =  false;
	
	

	public Train(int movingDirection , long deployTime)
	{
		this.moveDirection = movingDirection;
		this.deployTime = deployTime;
	}
	
	public Train(int movingDirection , int trackNumber  , long deployTime  , boolean hault)
	{
		this.moveDirection = movingDirection;
		this.deployTime = deployTime;
		this.trackNumber = trackNumber;
		this.hasHault = hault;
	}
	
	public void initialize() 
	{
		//when on up line x2 and Y2 are the front coordinates of the train 
		if(moveDirection == 0) 
		{
			x2 = currentSection.getX1() - Train.deployGap;
			y2 = currentSection.getY1();
			
			
		}else {
			
			x1 = currentSection.getX2() + Train.deployGap;
			y1 = currentSection.getY2();
			
		}
		
		
	}
	
	
	public void move(long deltaTime) 
	{
			//signal states => green = 0, double yellow = 1 , yellow = 2 , red =  3
			
		
			//red signal
			if(currentSignalState == 3) 
			{
				currentSpeed = (currentSpeed > 0) ? Math.max(0, currentSpeed - 0.5) : currentSpeed;

			}
			
			//yellow signal
			if(currentSignalState == 2) 
			{
				currentSpeed = (currentSpeed > Yspeed) 
					    ? Math.max(Yspeed, currentSpeed - 0.3) 
					    : Math.min(Yspeed, currentSpeed + 0.3);

					
					if (Math.abs(currentSpeed - Yspeed) < 0.2) {
					    currentSpeed = Yspeed;
					}

			}
			
			
			//double yellow signal
			if(currentSignalState == 1) 
			{
				if (currentSpeed > YYspeed) {
				    currentSpeed = Math.max(YYspeed, currentSpeed - 0.2);
				} else {
				    currentSpeed = Math.min(YYspeed, currentSpeed + 0.2);
				}

			}
			
			if(currentSignalState == 0) 
			{
				if(currentSpeed < Gspeed) 
				{
					currentSpeed += 0.3;
				}else {currentSpeed = Gspeed;}
			} 
			
			
		
		
			if(!frontMovingOnSwitch) 
			{
				if(moveDirection == 0) 
				{
					x2 = x2 + (currentSpeed * deltaTime/1000000000);
					
				}else {
					
					x1 = x1 - (currentSpeed * deltaTime/1000000000);
				}
				
			}else {
				
				//calculate x1 and y1 according to coordinates of track section  
				
				if(moveDirection == 0) 
				{
					
					int sX1 = newSwitch.getX1();
					int sY1 = newSwitch.getY1();
					
					int sX2 = newSwitch.getX2();
					int sY2 = newSwitch.getY2();
					
					
					double directionX = sX2 - sX1;
					double directionY = sY2 - sY1;
					
					double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);
					
					
					
					double normalDirectionX = directionX / magnitude;
					double normalDirectionY = directionY / magnitude;
					
					
				
					
					//change x2 and y2 to make train move diagonally 
//					x1 = x1 - (currentSpeed * deltaTime/1000000000);
					
					x2 = x2 + (normalDirectionX * currentSpeed * deltaTime / 1000000000);
				    y2 = y2 + (normalDirectionY * currentSpeed * deltaTime / 1000000000);
					
				}else {
					
					
					
				}
				
			}
		

	}
	
	
	
	public void signalLookout(List<Signal> listOfSignals) 
	{
		
	
		if(moveDirection == 0) 
		{
			trainFrontX = (int)x2;
			trainFrontY = (int)y2;
			
			trainBackX = (int)x1;
			trainBackY = (int)y1;
			
		}else {
			
			trainFrontX = (int)x1;
			trainFrontY = (int)y1;
			
			trainBackX = (int)x2;
			trainBackY = (int)y2;
		}
		
		
		if(!listOfSignals.isEmpty()) 
		{
			/*
			 * 	|-----Horizontal Position Flag----| 
			 * 
			 *   1 = up line 
			 *  -1 = down line 
			 */
			Signal signal;
			
			for(int j = 0; j < listOfSignals.size(); j++) 
			{
				signal = listOfSignals.get(j);
				
				if(this.moveDirection == 0) 
				{
					if(signal.getHorizontalPosFlag() == 1) 
					{
						//train direction match signal
						signalClock(signal);
					}
				}
				
				
				if(this.moveDirection == 1) 
				{
					if(signal.getHorizontalPosFlag() == -1) 
					{
						//train direction match signal
						signalClock(signal);
					}
				}

			}
			
		}
	}
	
	
	public void switchLookout(List<TrackSection> listOfTrackSections) 
	{
		if(!listOfTrackSections.isEmpty()) 
		{
			for(int i = 0; i < listOfTrackSections.size(); i++) 
			{
				TrackSection track = listOfTrackSections.get(i);
				
				//check if it is a switch 
				if(!track.getTrackType().equals(TrackSection.trackType.DOWN) && 
				   !track.getTrackType().equals(TrackSection.trackType.UP)) 
				{
					//left to right -- UP LINE 
					if(moveDirection == 0) 
					{
						//--------------------------Train front entered the switch going UP to UP
						if(track.detectStartOfUpSwitch(trainFrontX, trainFrontY)) 
						{
							
							if(track.getSTATE() == 0)continue;
							
							if(newSwitch == null || newSwitch != track) 
							{
								newSwitch = track;
								frontMovingOnSwitch = true;
								
								System.out.println("Entered Switch");
								continue;
							}
											
						}
						
						//-------------------------Train back entered the switch going UP to UP
						if(track.detectStartOfUpSwitch(trainBackX, trainBackY)) 
						{
							if(backMovingOnSwitch)continue;
							
							if(newSwitch != null && newSwitch == track) 
							{
								backMovingOnSwitch = true;
								continue;
							}
						}
						
						//---------------------------front of the train has exited the up switch 
						if(track.detectEndOfUpSwitch(trainFrontX, trainFrontY))
						{
							if(!frontMovingOnSwitch)continue;
							
							frontMovingOnSwitch = false;
							currentSection = track.getS1();
							continue;
						}
						
						//---------------------------back of train has exited the up switch 
						if(track.detectEndOfUpSwitch(trainBackX, trainBackY))
						{
							if(!backMovingOnSwitch)continue;
							
							backMovingOnSwitch = false;
							continue;
						}
						
						
					}
					
					//right to left -- DOWN LINE 
					if(moveDirection == 1) 
					{
						if(track.detectStartOfDownSwitch(trainFrontX, trainFrontY)) 
						{
							if(newSwitch == null || newSwitch != track) 
							{
								newSwitch = track;
								frontMovingOnSwitch = true;
								continue;
							}
							
																		
						}
					} 
	
					
				}
			}
		}
	}
	
	
	
	private void signalClock(Signal signal) 
	{
		if(signal.detectTrain(trainFrontX, trainFrontY)) 
		{

			if(lastClockedSignal == null || lastClockedSignal != signal) 
			{
				currentSignalState = signal.getSTATE();
				
				lastClockedSignal =  signal;
				
				if(currentSignalState != 3) 
				{
					signal.clock(0, this);
					clockCount++;
				}

			}else 
				
			if(lastClockedSignal == signal) 
			{
				if(signal.getSTATE() == 2 && currentSignalState == 3) 
				{
					currentSignalState = 2;
					
				}
			}
			
		}
		
		if(signal.detectTrain(trainBackX, trainBackY)) 
		{
			if(signal.getSTATE() != 3) 
			{
				signal.clock(0, this);
				clockCount++;
			}
		}
	}
	
	
	
	public void draw(Graphics2D g2d) 
	{
		
		//UP line , moving left to right 
		if(moveDirection == 0) 
		{
			x1 = x2 - trainLength;
			y1 = y2;
			
		//DOWN line , moving right to left (to remember i have to write same thing everywhere ig)	
		}else {
			
			x2 = x1 + trainLength;
			
			g2d.setColor(Color.red);
			g2d.fillOval((int)trainFrontX - 5, (int)trainFrontY - 5, 10, 10);
			y2 = y1;
		}
		

		
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		
	
		
		
	}
	
	
	public int getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(int moveDirection) {
		this.moveDirection = moveDirection;
	}

	public TrackSection getCurrentSection() {
		return currentSection;
	}

	public void setCurrentSection(TrackSection currentSection) {
		this.currentSection = currentSection;
	}

	public long getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(long deployTime) {
		this.deployTime = deployTime;
	}


	public int getDeployState() {
		return deployState;
	}

	public void setDeployState(int deployState) {
		this.deployState = deployState;
	}

	public void setStartSpeed(double speed) {
		
		this.currentSpeed = speed;
	}
	
	public int getClockCount() {
		return clockCount;
	}

	public void setClockCount(int clockCount) {
		this.clockCount = clockCount;
	}
	
	public int getCurrentSignalState() {
		return currentSignalState;
	}

	public void setCurrentSignalState(int currentSignalState) {
		this.currentSignalState = currentSignalState;
	}
	
	public boolean isNextSectionClear() {
		return nextSectionClear;
	}

	public void setNextSectionClear(boolean nextSectionClear) {
		this.nextSectionClear = nextSectionClear;
	}
	
	public Signal getLastClockedSignal() {
		return lastClockedSignal;
	}

	public void setLastClockedSignal(Signal lastClockedSignal) {
		this.lastClockedSignal = lastClockedSignal;
	}
	
	
}
