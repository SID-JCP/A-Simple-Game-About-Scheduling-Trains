package RenderingElements.Train;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import RenderingElements.Canvas;
import RenderingElements.Tracks.TrackSection;

public class Train {
	
	
	
	public int deployState = 0;   // 0 = not deployed , 1 = deployed , 2 = deployment done and exited screen

	public long deployTime = 0l;  // time at which train need to be deployed
	
	public int moveDirection = 0; // 0 = up line , 1 = down line 
	
	public TrackSection currentSection;
	
	
	//|--------------------------Movement Data--------------------------------|
	
	//left side of the train
	public double x1,y1;
	
	//right side of the train 
	public int x2,y2;
	
	
	private double speed = 20.0;
	
	
	public Train(int movingDirection , long deployTime)
	{
		this.moveDirection = movingDirection;
		this.deployTime = deployTime;
	}
	
	
	public void move(long deltaTime) 
	{
		x1 = x1 + (speed * deltaTime/1000000000);
		
//		x1 += 5;
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
