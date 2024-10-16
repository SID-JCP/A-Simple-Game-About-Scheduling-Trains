package RenderingElements.Controller;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Point.Point;
import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Train.Train;

public class tspController {
	
	// t = train , s = signal , p = point	
	
	public static List<Train> allTrain = new LinkedList<>();
	public static List<Signal> allSignals = new LinkedList<>();
	public static List<Point> allPoints = new LinkedList<>();
	
	public static List<TrackSection> trackSection;
	
	
	private static long secondsOfDay = 0l;
	
	
	
	
	public void update(long time) 
	{
		tspController.secondsOfDay = time;
		
		if(!tspController.allTrain.isEmpty()) 
		{
			for(Train train : allTrain) 
			{
				if(!train.hasSection()) 
				{
					train.assignSection(trackSection.get(3));
				}
				
				train.updateTrainPosition();
			}
		}
		
//		System.out.println(trackSection);
		
	}
	
	
	public void drawTrain(Graphics2D g2d) 
	{

		if(!tspController.allTrain.isEmpty()) 
		{
			for(Train train : allTrain) 
			{
				train.drawTrain(g2d);
			}
		}
	}

}
