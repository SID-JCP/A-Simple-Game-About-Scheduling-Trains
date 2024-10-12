package RenderingElements.Train;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Stack;

public class Train {
	
	
	public Stack<LinkedList<Point2D>> trainCurrentTrackSection = new Stack<>();
	
	public int movingDirection = 1; //1 -> front , 0 -> reverse
	
	public boolean moving = true;	
	public boolean enteredPoint = false;
	
	
	/* TRAIN META DATA */
	
	private int Delay  = 0;
	
	private boolean hasStoppage = true;
	
	private int timeForStoppage = 0;
	
	private int arrivalTime = 0;
	
	private int departureTime = 0;
	
	
	
	
	
	
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
	
	
	
	
	
	private void move() 
	{
		
	}
	
	public void updateTrainPosition() 
	{
		
	}
	
	
	public void drawTrain(Graphics2D g2d) 
	{
		move();
	}

}
