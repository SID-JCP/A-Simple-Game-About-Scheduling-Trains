package RenderingElements.Train;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import RenderingElements.Tracks.TrackSection;

public class Train {
	
	

	
	public int movingDirection = 1; //1 -> front , 0 -> reverse
	
	public boolean moving = true;	
	public boolean enteredPoint = false;
	
	
	/* TRAIN META DATA */
	
	private int Delay  = 0;
	
	private boolean hasStoppage = true;
	
	private int timeForStoppage = 0;
	
	private int arrivalTime = 0;
	
	private int departureTime = 0;
	
	
	//coordinates of head of train
	private int xPos = 0;
	private int yPos = 0;
	
	//length of section linked list and the distance travelled 
	private int sectionLength = 0;
	private int sectionLengthTraversed = 0;
	
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
	
	
	public void assignSection(TrackSection freeSection) 
	{
		currentSection = freeSection;
		sectionLength = currentSection.sectionPoints.size();
	}
	
	
	public boolean hasSection() 
	{
		if(currentSection == null) 
		{
			return false;
		}
		
		return true;
	}
	
	
	
	
	public void updateTrainPosition() 
	{
		if(sectionLengthTraversed < sectionLength) 
		{
			xPos = (int)currentSection.sectionPoints.get(sectionLengthTraversed).getX();
			yPos = (int)currentSection.sectionPoints.get(sectionLengthTraversed).getY();
			sectionLengthTraversed++;
			return;
		}
		
		reachedSectionEnd = true;
	}
	
	
	public void drawTrain(Graphics2D g2d) 
	{
		if(hasSection()) 
		{
			g2d.setColor(Color.orange);
			g2d.fillOval(xPos - 10, yPos - 10 , 20, 20);
		}
		
	}

}
