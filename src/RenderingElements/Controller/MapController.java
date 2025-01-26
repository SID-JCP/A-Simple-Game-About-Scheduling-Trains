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
	public static List<Signal> listOfSignals;
	
	
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
	
	
	public void drawTracks(int xCenter , int yCenter , int width , int height ,  int trackOffset , int lengthOffset , Graphics2D g2d) 
	{
		
		if(!listOfTrackSections.isEmpty()) 
		{
			TrackSection trackSection;
			
			
			for(int i = 0; i < listOfTrackSections.size(); i++)
			{
				
				
				trackSection = listOfTrackSections.get(i);
				
				int y , x1 , x2;
				
				if(trackSection.getTrackType() == TrackSection.trackType.UP) 
				{
					//its main line , length is full
					if(trackSection.getTrackLength() == - 1) 
					{
						y = yCenter - (trackOffset * trackSection.getTrackNum());
						x1 = 0;
						x2 = width;
						
						
						trackSection.setX1(x1);
						trackSection.setY1(y);
						trackSection.setX2(x2);
						trackSection.setY2(y);
						
					}else {
						
						
						y = yCenter - (trackSection.getTrackNum() * trackOffset);
						
						x1 = xCenter - (int)((trackSection.getTrackLength() / 2) * lengthOffset);
						x2 = xCenter + (int)((trackSection.getTrackLength() / 2) * lengthOffset);
						
						trackSection.setX1(x1);
						trackSection.setY1(y);
						trackSection.setX2(x2);
						trackSection.setY2(y);
					
					}
					
					
					g2d.setColor(Color.GRAY);
					g2d.setStroke(new BasicStroke(TrackSection.trackWidth));
					g2d.drawLine(x1, y, x2, y);
					
					
					
				}
				
				
				if(trackSection.getTrackType() == TrackSection.trackType.DOWN) 
				{
					if(trackSection.getTrackLength() == - 1) 
					{
						y = yCenter + (trackOffset * trackSection.getTrackNum());
						x1 = 0;
						x2 = width;
						
						trackSection.setX1(x1);
						trackSection.setY1(y);
						trackSection.setX2(x2);
						trackSection.setY2(y);
					}else {
						
						y = yCenter + (trackSection.getTrackNum() * trackOffset);
						
						x1 = xCenter - (int)((trackSection.getTrackLength() / 2) * lengthOffset);
						x2 = xCenter + (int)((trackSection.getTrackLength() / 2) * lengthOffset);
						
						trackSection.setX1(x1);
						trackSection.setY1(y);
						trackSection.setX2(x2);
						trackSection.setY2(y);
					}
					
					g2d.setColor(Color.GRAY.darker());
					g2d.setStroke(new BasicStroke(TrackSection.trackWidth));
					g2d.drawLine(x1, y, x2, y);
				}
				
				
				
				if(trackSection.getTrackType() == TrackSection.trackType.UP_START) 
				{
					
					
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1) 
					{
						
						trackSection.setX1((int)(trackSection.getStartBlockNo() * lengthOffset));
						trackSection.setY1(  trackSection.getS1().getY1() );
						
						trackSection.setX2((int)((trackSection.getStartBlockNo()  + 1 ) * lengthOffset));
						trackSection.setY2(  trackSection.getS2().getY1() );
						
						
						
						
					}else{
						
						trackSection.setX1((int)(trackSection.getStartBlockNo() * lengthOffset));
						trackSection.setY1(  trackSection.getS1().getY1() );
						
						trackSection.setX2(trackSection.getS2().getX1());
						trackSection.setY2(  trackSection.getS2().getY1() );
						
					}
					
					
					
					g2d.setColor(Color.RED);
					
					g2d.setStroke(new BasicStroke(TrackSection.trackWidth));
					g2d.drawLine(trackSection.getX1(), 
								trackSection.getY1(), 
								trackSection.getX2(),
								trackSection.getY2());
					
				} 
				
				if(trackSection.getTrackType() == TrackSection.trackType.UP_END) 
				{
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1) 
					{
						trackSection.setX1((int)(trackSection.getStartBlockNo() * lengthOffset));
						trackSection.setY1(  trackSection.getS1().getY1() );
						
						trackSection.setX2((int)((trackSection.getStartBlockNo()  + 1 ) * lengthOffset));
						trackSection.setY2(  trackSection.getS2().getY1() );
						
					}else {
						
						trackSection.setX1(trackSection.getS1().getX2());
						trackSection.setY1(  trackSection.getS1().getY2() );
						
						trackSection.setX2((int)(trackSection.getStartBlockNo() * lengthOffset));
						trackSection.setY2(  trackSection.getS2().getY1() );
						
					}
					
					
					g2d.setColor(Color.RED);
					
					g2d.setStroke(new BasicStroke(TrackSection.trackWidth));
					g2d.drawLine(trackSection.getX1(), 
								trackSection.getY1(), 
								trackSection.getX2(),
								trackSection.getY2());
					
				} 
				
				
				
				
				if(trackSection.getTrackType() == TrackSection.trackType.DOWN_START) 
				{
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1) 
					{
						
						trackSection.setX1((int)(trackSection.getStartBlockNo() * lengthOffset));
						trackSection.setY1(  trackSection.getS1().getY1() );
						
						trackSection.setX2((int)((trackSection.getStartBlockNo()  + 1 ) * lengthOffset));
						trackSection.setY2(  trackSection.getS2().getY1() );
						
						
					}else {
						
						trackSection.setX1((int)(trackSection.getStartBlockNo() * lengthOffset));
						trackSection.setY1(  trackSection.getS1().getY1() );
						
						trackSection.setX2(trackSection.getS2().getX1());
						trackSection.setY2(  trackSection.getS2().getY1() );				
						
					}
					
					
					g2d.setColor(Color.RED);
					
					g2d.setStroke(new BasicStroke(TrackSection.trackWidth));
					g2d.drawLine(trackSection.getX1(), 
								trackSection.getY1(), 
								trackSection.getX2(),
								trackSection.getY2());
				}
				
				
				if(trackSection.getTrackType() == TrackSection.trackType.DOWN_END) 
				{
					
					
					
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1) 
					{
						
						trackSection.setX1((int)(trackSection.getStartBlockNo() * lengthOffset));
						trackSection.setY1(  trackSection.getS1().getY1() );
						
						trackSection.setX2((int)((trackSection.getStartBlockNo()  + 1 ) * lengthOffset));
						trackSection.setY2(  trackSection.getS2().getY1() );
						
						
						
					}else {
						
						
						trackSection.setX1( trackSection.getS1().getX2() );
						trackSection.setY1(  trackSection.getS1().getY2() );
						
						trackSection.setX2( (int)(trackSection.getStartBlockNo() * lengthOffset)   );
						trackSection.setY2(  trackSection.getS2().getY1() );	
						
					}
					
					
					g2d.setColor(Color.RED);
					
					g2d.setStroke(new BasicStroke(TrackSection.trackWidth));
					g2d.drawLine(trackSection.getX1(), 
								trackSection.getY1(), 
								trackSection.getX2(),
								trackSection.getY2());
					
				}
				
				
			}
			
			

		}
		
	}
	
	
	public void drawSignals(Graphics2D g2d) 
	{
		if(!listOfSignals.isEmpty()) 
		{
			Signal signal;
			
			for(int i = 0; i < listOfSignals.size(); i++) 
			{
				signal = listOfSignals.get(i);
				
				signal.draw(g2d);
			}
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
