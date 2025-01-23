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
	
	
	public int movingDirection = 1; //1 -> front , 0 -> reverse
	
	public boolean moving = true;	
	public boolean enteredPoint = false;
	
	
	/* TRAIN META DATA */
	private boolean hasStoppage = true;
	private int Delay  = 0;	
	private int timeForStoppage = 0;	
	private int arrivalTime = 0;	
	private int departureTime = 0;
	
	
	private double speed = 0.5;	
	
	
	//coordinates of head of train
	private int xPos = 0;
	private int yPos = 0;

	
	//length of section linked list and the distance travelled 
	private double sectionPointIndex = 0;
	private int sectionPointLastIndex = 0;
	
	boolean reachedSectionEnd = false;		
	
	public TrackSection currentSection = null;
	
	/*
	 * |-------------------- What this class do ------------------------|
	 * 
	 * move the train in the direction of the linked list provided according to
	 * stack
	 * 
	 * increment or decrement according speed to the rate of addition or
	 * subtraction of linked list element window
	 * 
	 * the controller will provide the section to traverse using the stack , follow
	 * the stack top
	 * 
	 * CHECK IF ENTERING A POINT , SET STATUS ACCORDING TO THAT 
	 * 
	 * CHECK IF ENTERING A SIGNAL CHANGE SPEED ACCORDING TO THAT 
	 * 
	 */
	
	
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
