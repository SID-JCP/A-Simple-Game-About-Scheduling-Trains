package RenderingElements.Controller;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Point.Point;
import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Train.Train;

public class MapController {
	
	// t = train , s = signal , p = point	
	
	public static List<Train> allTrain = new LinkedList<>();
	public static List<Signal> allSignals = new LinkedList<>();
	public static List<Point> allPoints = new LinkedList<>();
	
	public static List<TrackSection> trackSection;
	
	
	private static long secondsOfDay = 0l;
	
	
	
	
	public void update(long time) 
	{
		MapController.secondsOfDay = time;
		
		if(!MapController.allTrain.isEmpty()) 
		{
			for(Train train : allTrain) 
			{
				if(!train.hasSection()) 
				{
					//assign section to train on start , determined by the direction of train and if its alive or not 
					train.assignSection(trackSection.get(1));
				}
				
				train.updateTrainPosition();
			}
		}
		
		
	}
	
	
	public void drawTrain(Graphics2D g2d) 
	{

		if(!MapController.allTrain.isEmpty()) 
		{
			allTrain.forEach(train -> 
			{
				train.drawTrain(g2d);
			});
			
		}
	}

}
