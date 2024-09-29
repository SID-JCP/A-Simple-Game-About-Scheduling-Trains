package RenderingElements.Train;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Stack;

public class Train {
	
	
	public Stack<LinkedList<Point2D>> trainCurrentTrackSection = new Stack<>();
	
	public int movingDirection = 1; //1 -> front , 0 -> reverse
	
	public boolean moving = true;
	
	
	public void drawTrain() 
	{
		
	}

}
