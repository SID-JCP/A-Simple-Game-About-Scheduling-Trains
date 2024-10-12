package RenderingElements.Controller;

import java.util.LinkedList;
import java.util.List;

import RenderingElements.Point.Point;
import RenderingElements.Signal.Signal;
import RenderingElements.Train.Train;

public class tspController {
	
	// t = train , s = signal , p = point	
	
	public static List<Train> allTrain = new LinkedList<>();
	public static List<Signal> allSignals = new LinkedList<>();
	public static List<Point> allPoints = new LinkedList<>();
	
	
	private static long secondsOfDay = 0l;
	
	public tspController() 
	{
		
	}
	
	
	public void update(long time) 
	{
		tspController.secondsOfDay = time;
		
//		if(!tspController.allTrain.isEmpty()) 
//		{
//			System.out.println(tspController.allTrain);
//		}
		
		System.out.println(tspController.allTrain);
		
	}
	
	
	public void drawTrain() 
	{
		
	}

}
