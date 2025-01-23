package RenderingElements.Controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
	
	
	
	
	public static List<TrackSection> listOfTrackSections;
	
	
	private static long secondsOfDay = 0l;
	
	
	
	
	public void update(long time) 
	{
		MapController.secondsOfDay = time;
		
//		if(!MapController.allTrain.isEmpty()) 
//		{
//			for(Train train : allTrain) 
//			{
//				if(!train.hasSection()) 
//				{
//					//assign section to train on start , determined by the direction of train and if its alive or not 
//					train.assignSection(trackSection.get(1));
//				}
//				
//				train.updateTrainPosition();
//			}
//		}
		
		
	}
	
	
	public void drawTracks(int xCenter , int yCenter , int width , int height ,  int trackOffset , Graphics2D g2d) 
	{
		
		if(!listOfTrackSections.isEmpty()) 
		{
			listOfTrackSections.forEach(trackSection -> 
			{
				
				int y , x1 , x2;
				
				if(trackSection.getTrackType() == TrackSection.trackType.UP) 
				{
					//its main line 
					if(trackSection.getTrackNum() == - 1) 
					{
						y = yCenter - (trackOffset);
						x1 = 0;
						x2 = width;
						
					}else {
						
						
						y = yCenter - (trackSection.getTrackNum() * trackOffset);
						
						x1 = xCenter - ((int)trackSection.getTrackLength() / 2);
						x2 = xCenter + ((int)trackSection.getTrackLength() / 2);
						
					
					}
					
					
					g2d.setColor(Color.GRAY);
					g2d.setStroke(new BasicStroke(3));
					g2d.drawLine(x1, y, x2, y);
					
					
					
				}
				
				
				
				
			});
			
			

		}
		
	}
	
	public void drawTrain(Graphics2D g2d) 
	{

//		if(!MapController.allTrain.isEmpty()) 
//		{
//			allTrain.forEach(train -> 
//			{
//				train.drawTrain(g2d);
//			});
//			
//		}
	}

}
