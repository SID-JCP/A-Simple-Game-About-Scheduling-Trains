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
	
	
	
	public int deployState = 0;   // 0 = not deployed , 1 = deployed , 2 = deployment done and exited screen

	public long deployTime = 0l;  // time at which train need to be deployed
	
	public int moveDirection = 0; // 0 = left to right , 1 = right to left
	
	public TrackSection currentSection; //current track on which train is 
	
	public int lineTravelling = 0; // 0 = train should be on up line , 1 = train should be on down line 
	
	public boolean hasHault = true; // if the train should stop at the station or not 
	
	//|--------------------------Movement Data--------------------------------|
	
	//left side of the train
	public double x1,y1;
	
	//right side of the train 
	public double x2,y2;
	
	//green signal speed
	private double Gspeed = 35.0;
	
	//double yellow speed 
	private double YYspeed = 20.0;
	
	//single yellow speed
	private double Yspeed = 10.0;
	
	//|--------red is zero-------|
	
	private double currentSpeed = Gspeed;
	
	
	private int trainFrontX , trainFrontY , trainBackX , trainBackY;
	
	//--------------------------SIGNAL AND SECTION related data----------------------|
	
	//signal to see
	Signal nextSignal;
	
	
	Signal lastClockedSignal;
	
	
	public Train(int movingDirection , long deployTime)
	{
		this.moveDirection = movingDirection;
		this.deployTime = deployTime;
	}
	
	
	public void move(long deltaTime) 
	{
		//signal states , green = 0, double yellow = 1 , yellow = 2 , red =  3
		
		if(nextSignal != null) 
		{
			//red signal
			if(nextSignal.getSTATE() == 3) 
			{
				currentSpeed = (currentSpeed > 0) ? Math.max(0, currentSpeed - 0.5) : currentSpeed;

			}
			
			//yellow signal
			if(nextSignal.getSTATE() == 2) 
			{
				currentSpeed = (currentSpeed > Yspeed) 
					    ? Math.max(Yspeed, currentSpeed - 0.3) 
					    : Math.min(Yspeed, currentSpeed + 0.3);

					
					if (Math.abs(currentSpeed - Yspeed) < 0.2) {
					    currentSpeed = Yspeed;
					}

			}
			
			
			//double yellow signal
			if(nextSignal.getSTATE() == 1) 
			{
				if (currentSpeed > YYspeed) {
				    currentSpeed = Math.max(YYspeed, currentSpeed - 0.2);
				} else {
				    currentSpeed = Math.min(YYspeed, currentSpeed + 0.2);
				}

			}
			
			if(nextSignal.getSTATE() == 0) 
			{
				if(currentSpeed < Gspeed) 
				{
					currentSpeed += 0.3;
				}else {currentSpeed = Gspeed;}
			} 
			
			
		}
		
		
		x1 = x1 + (currentSpeed * deltaTime/1000000000);
		

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
			Signal signal;
			
			for(int j = 0; j < listOfSignals.size(); j++) 
			{
				signal = listOfSignals.get(j);
				
				//signal is for up line
				if(signal.getHorizontalPosFlag() == 1) 
				{
					if(signal.detectTrain(trainBackX , trainBackY)) 
					{
						setNextSignal(null);
						
						if(signal.signal.equals(Signal.signalType.HOME)){signal.setSTATE(3);}else 
						{
							if(lastClockedSignal == null || lastClockedSignal != signal) 
							{
								// clock flag , train which clocks 
								signal.clock(0, this);
								lastClockedSignal = signal;
							}
							
						}
						
						
					}
					
					
					if(signal.detectTrain(trainFrontX , trainFrontY)) 
					{
						setNextSignal(signal);
						
					}
					
					
				}
				
				continue;
			}
			
		}
	}
	
	
	
	private void setNextSignal(Signal signal) 
	{
		this.nextSignal = signal;
	}
	
	
	public void draw(Graphics2D g2d) 
	{
		x2 = x1 + 80.0;
		y2 = y1;
		
		
		
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		
		g2d.setColor(Color.pink);
		g2d.fillOval((int)x1 - 5, (int)y1 - 5, 10, 10);
		
		g2d.setColor(Color.red);
		g2d.fillOval((int)x2 - 5, (int)y2 - 5, 10, 10);
		
		
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
	
	
	
	
//	public void assignSection(TrackSection freeSection) 
//	{
//		currentSection = freeSection;
//		sectionPointLastIndex = currentSection.sectionPoints.size();
//	}
//	
//	
//	public boolean hasSection() 
//	{
//		if(currentSection == null) 
//		{
//			return false;
//		}		
//		return true;
//	}
//	
//	
//	
//	
//	public void updateTrainPosition() 
//	{
//		if(sectionPointIndex < sectionPointLastIndex) 
//		{
//			xPos = (int)currentSection.sectionPoints.get((int)sectionPointIndex).getX();
//			yPos = (int)currentSection.sectionPoints.get((int)sectionPointIndex).getY();
//			
//			sectionPointIndex += 0.5;		
//			return;
//		}
//		
//		reachedSectionEnd = true;
//	}
//	
//	
//	public void drawTrain(Graphics2D g2d) 
//	{
//		if(hasSection()) 
//		{
//			g2d.setColor(Color.BLUE);
//			g2d.fillOval(xPos - 10, yPos - 10 , 20, 20);
//		}
//		
//	}

}
