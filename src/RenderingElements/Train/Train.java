package RenderingElements.Train;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
	
	public int haultDuration = 0; //time the train is supposed to stop on the platform 
	
	public String name = ""; //name of the train 
	
	public Color color = null; //optional color provided 
	
	//|--------------------------Movement Data--------------------------------|
	
	

	//left side of the train
	private double x1,y1;
	
	//right side of the train 
	private double x2,y2;
	
	//Distance between front and back points of train 
	private final double trainLength = 80.0;
	
	//green signal speed
	public static double Gspeed = 35.0;
	
	//double yellow speed 
	public static double YYspeed = 20.0;
	
	//single yellow speed
	public static double Yspeed = 10.0;
	
	//|--------red is zero-------|
	
	private double currentSpeed = Yspeed;
	
	
	private int trainFrontX , trainFrontY , trainBackX , trainBackY;
	
	
	//reverse queue unload , use at line 675
	private List<Point> pointsList = new ArrayList<>();
	
	//--------------------------SIGNAL AND SECTION related data----------------------|
	
	//signal to see
	Signal nextSignal;
	
	//signal which is just passed
	Signal lastClockedSignal;
	
	//no of signals the train has passed 
	public int clockCount = 0;

	//state of the signal which was before the train passed
	int currentSignalState = 0;
	
	//---------------------------SWITCH RELATED DATA---------------------------------|
	
	//used by the train front 
	TrackSection newSwitch;
	
	//used by the train back
	TrackSection oldSwitch;
	
	private boolean frontMovingOnSwitch = false;
	
	private boolean backMovingOnSwitch = false;
	
	
	//stores the points to draw train when different parts on different switches 
	Queue<Point> switchPointsQueue = new LinkedList<>();
	
	
	int switchStartPointX  = -1, switchStartPointY = -1, switchEndPointX  = -1, switchEndPointY = -1;
	
	
	
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
	
	public Train(int movingDirection , int trackNumber  , long deployTime  , int haultDuration , String name , Color color)
	{
		this.moveDirection = movingDirection;
		this.deployTime = deployTime;
		this.trackNumber = trackNumber;
		this.name = name;
		this.haultDuration = haultDuration; 
		this.color = color;
	}
	
	public void initialize() 
	{
		//x1 is left side of the train , y2 is right side of the train 
		if(moveDirection == 0) 
		{
			x2 = currentSection.getX1() - Train.deployGap;
			y2 = currentSection.getY1();
			y1  = y2;
			
			
		}else {
			
			x1 = currentSection.getX2() + Train.deployGap;
			y1 = currentSection.getY2();
			y2 = y1;	
			
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
	
	private void setSpeed() 
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
	}
	
	
	public void move(long deltaTime) 
	{
			
			setSpeed();
		
				
			//calculate x1 and y1 according to coordinates of track section  
			if(moveDirection == 0) 
			{
				if(!frontMovingOnSwitch && !backMovingOnSwitch) 
				{
					x2 = x2 + (currentSpeed * deltaTime/1000000000);
					x1 = x2 - trainLength;
					return;

				}
				
				if(frontMovingOnSwitch) 
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
					
					
					x2 = x2 + (normalDirectionX * currentSpeed * deltaTime / 1000000000);
				    y2 = y2 + (normalDirectionY * currentSpeed * deltaTime / 1000000000);
				}
				
				
				if(backMovingOnSwitch) 
				{
					
					
					int sX1 = oldSwitch.getX1();
					int sY1 = oldSwitch.getY1();
					
					int sX2 = oldSwitch.getX2();
					int sY2 = oldSwitch.getY2();
					
					double directionX = sX2 - sX1;
					double directionY = sY2 - sY1;
					
					double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);
					
					double normalDirectionX = directionX / magnitude;
					double normalDirectionY = directionY / magnitude;
					
					x1 = x1 + (normalDirectionX * currentSpeed * deltaTime / 1000000000);
				    y1 = y1 + (normalDirectionY * currentSpeed * deltaTime / 1000000000);
				}
				

				if(!frontMovingOnSwitch) 
				{
					x2 = x2 + (currentSpeed * deltaTime/1000000000);
				}
				
				if(!backMovingOnSwitch) 
				{
					x1 = x1 + (currentSpeed * deltaTime/1000000000);
				}
				
				
			}

			
			
			//calculation of both points for down line 
			if(moveDirection == 1) 
			{
				
				if(!frontMovingOnSwitch && !backMovingOnSwitch) 
				{
					x1 = x1 - (currentSpeed * deltaTime/1000000000);
					x2 = x1 + trainLength;
					return;

				}
				
				if(frontMovingOnSwitch) 
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
					
					
					x1 = x1 - (normalDirectionX * currentSpeed * deltaTime / 1000000000);
				    y1 = y1 - (normalDirectionY * currentSpeed * deltaTime / 1000000000);
				}
				
				
				if(backMovingOnSwitch) 
				{
					
					
					int sX1 = oldSwitch.getX1();
					int sY1 = oldSwitch.getY1();
					
					int sX2 = oldSwitch.getX2();
					int sY2 = oldSwitch.getY2();
					
					double directionX = sX2 - sX1;
					double directionY = sY2 - sY1;
					
					double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);
					
					double normalDirectionX = directionX / magnitude;
					double normalDirectionY = directionY / magnitude;
					
					x2 = x2 - (normalDirectionX * currentSpeed * deltaTime / 1000000000);
				    y2 = y2 - (normalDirectionY * currentSpeed * deltaTime / 1000000000);
				}
				
				
				if(!frontMovingOnSwitch) 
				{
					x1 = x1 - (currentSpeed * deltaTime/1000000000);
				}
				
				if(!backMovingOnSwitch) 
				{
					x2 = x2 - (currentSpeed * deltaTime/1000000000);
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
						
						if(track.detectStartOfUpSwitch(trainFrontX, trainFrontY)) 
						{
							//switch is off, making train go straight
							if(track.getSTATE() == 0)continue;
							
							if(newSwitch != track || newSwitch == null) 
							{
								newSwitch = track;
								
								frontMovingOnSwitch = true;
								
								switchPointsQueue.add(new Point(newSwitch.getX1() , newSwitch.getY1()));
								
								continue;
							}
											
						}
						
						
						if(track.detectStartOfUpSwitch(trainBackX, trainBackY)) 
						{
							if(track.getSTATE() == 0)continue;
							
							if(backMovingOnSwitch)continue;
							
							if(oldSwitch != track || oldSwitch == null) 
							{
								oldSwitch = track;
								backMovingOnSwitch = true;
								
								if(!switchPointsQueue.isEmpty())switchPointsQueue.remove();

								continue;
							}
						}
						
						//---------------------------front of the train has exited the up switch 
						if(track.detectEndOfUpSwitch(trainFrontX, trainFrontY))
						{
							if(!frontMovingOnSwitch)continue;
							
							frontMovingOnSwitch = false;
							currentSection = track.getS1();
							
							y2 = newSwitch.getY2();
							
							switchPointsQueue.add(new Point(newSwitch.getX2() , newSwitch.getY2()));
							
							newSwitch = null;
							
							continue;
						}
						
						//---------------------------back of train has exited the up switch 
						if(track.detectEndOfUpSwitch(trainBackX, trainBackY))
						{
							if(!backMovingOnSwitch)continue;
							
							backMovingOnSwitch = false;
							oldSwitch = null;
							
							y1 = track.getY2();
	
							if(!switchPointsQueue.isEmpty())switchPointsQueue.remove();
							
							continue;
						}
						
						
					}
					
//-------------------------------------------------------Down Line--------------------------------------------------------------------			
					
					//right to left -- DOWN LINE 
					if(moveDirection == 1) 
					{
						//--------------------------Train front entered the switch going down to UP or down to down 
						if(track.detectStartOfDownSwitch(trainFrontX, trainFrontY)) 
						{
							if(track.getSTATE() == 0)continue;
							
							// && !backMovingOnSwitch
							if(newSwitch == null || newSwitch != track) 
							{
								newSwitch = track;
								frontMovingOnSwitch = true;
								
								switchPointsQueue.add(new Point(newSwitch.getX2() , newSwitch.getY2()));
								
								continue;
							}
										
						}
						
						if(track.detectStartOfDownSwitch(trainBackX, trainBackY)) 
						{
							
							
							if(track.getSTATE() == 0)continue;
							
							if(backMovingOnSwitch)continue;
							
							if(oldSwitch != track || oldSwitch == null) 
							{
								oldSwitch = track;
								backMovingOnSwitch = true;
								
								if(!switchPointsQueue.isEmpty())switchPointsQueue.remove();

								continue;
							}
										
						}
						
						//----------------------------Train front exited the switch----------------------------
						if(track.detectEndOfDownSwitch(trainFrontX, trainFrontY))
						{
							if(!frontMovingOnSwitch)continue;
							
							frontMovingOnSwitch = false;
							currentSection = track.getS1();
							
							switchPointsQueue.add(new Point(newSwitch.getX1() , newSwitch.getY1()));
							
							y1 = newSwitch.getY1();
							
							newSwitch = null;
							
							continue;
						}
						
						//----------------------------Train back exited the switch----------------------------
						if(track.detectEndOfDownSwitch(trainBackX, trainBackY))
						{
							if(!backMovingOnSwitch)continue;
							
							backMovingOnSwitch = false;
							oldSwitch = null;
							
							y2 = track.getY1();
	
							if(!switchPointsQueue.isEmpty())switchPointsQueue.remove();
							
							continue;
							
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
			if(signal.getSTATE() != 3 && !signal.isBeyond()) 
			{
				signal.clock(0, this);
				clockCount++;
			}
		}
	}
	
	
	
	public void draw(Graphics2D g2d) 
	{
		g2d.setColor(Color.blue);
		
		g2d.fillOval(trainFrontX - 5, trainFrontY - 5, 10, 10);
		
		g2d.setColor(Color.green);
		
		g2d.fillOval(trainBackX - 5, trainBackY - 5, 10, 10);
		
		
//--------------------------------------------------------------------------------------------
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(6 , BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		
		
		if(!switchPointsQueue.isEmpty()) 
		{
			//up line train going left to right
			if(moveDirection == 0) 
			{
				Point prev = new Point((int)x1, (int)y1);
				
				for (Point current : switchPointsQueue) 
				{
					
			        g2d.drawLine((int) prev.getX(), (int) prev.getY(),
			                   (int) current.getX(), (int) current.getY());
			        
			        prev = current;
			    }
				
				g2d.drawLine((int) prev.getX(), (int) prev.getY(), (int)x2, (int)y2);
			}
			
			//down line train going right to left 
			if(moveDirection == 1) 
			{
				Point prev = new Point((int) x1, (int) y1);

				pointsList.clear();
				pointsList.addAll(switchPointsQueue);

				
				Point current = pointsList.get(pointsList.size() - 1);
				g2d.drawLine((int) prev.getX(), (int) prev.getY(),
				             (int) current.getX(), (int) current.getY());
				prev = current;

				
				for (int i = pointsList.size() - 2; i >= 0; i--) {
				    current = pointsList.get(i);
				    g2d.drawLine((int) prev.getX(), (int) prev.getY(),
				                 (int) current.getX(), (int) current.getY());
				    prev = current;
				}

				
				g2d.drawLine((int) prev.getX(), (int) prev.getY(), (int) x2, (int) y2);
				
				
			}
			
		}else {
			
			g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}


		
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
