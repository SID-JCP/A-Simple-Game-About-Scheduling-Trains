package RenderingElements.Controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Point.Point;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Train.Train;

public class MapController {
	
		
	
	public static List<Train> allTrain = new LinkedList<>();
	
	
	
	
	
	public static List<TrackSection> listOfTrackSections;
	public static List<Signal> listOfSignals;
	
	
	private static long secondsOfDay = 0l;
	
	private int mouseMoveX , mouseMoveY;
	private int clickX = 0, clickY = 0;
	
	private boolean newClick = false;
	
	public void update(long time , int mouseMoveX , int mouseMoveY , int mouseClickX , int mouseClickY) 
	{
		MapController.secondsOfDay = time;
		
		this.mouseMoveX = mouseMoveX;
		this.mouseMoveY = mouseMoveY;
		
		if(this.clickX == mouseClickX && this.clickY == mouseClickY) 
		{
			newClick = false;
		}else {newClick = true;}
		
		this.clickX = mouseClickX;
		this.clickY = mouseClickY;
		
		
		
		
		if(!listOfSignals.isEmpty()) 
		{
			Signal signal;
			
			for(int i = 0; i < listOfSignals.size(); i++) 
			{
				signal = listOfSignals.get(i);
				
				if(signal.isCursorInside(mouseClickX, mouseClickY) && newClick) 
				{
					signal.clock();
					continue;
				}
				
				
			}
			
			newClick = false;
		}
		
		
		
		if(!listOfTrackSections.isEmpty()) 
		{
			TrackSection trackSection;
			
			
			for(int i = 0; i < listOfTrackSections.size(); i++)
			{
				trackSection = listOfTrackSections.get(i);
				
				trackSection.isCursorInside(mouseMoveX, mouseMoveY);
				
			}
			
		}
		

		
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
					
					
					
				}
				
				
				
				
				
				if(trackSection.getTrackType() == TrackSection.trackType.DOWN_END || 
					trackSection.getTrackType() == TrackSection.trackType.DOWN_START ||
					trackSection.getTrackType() == TrackSection.trackType.UP_END || 
					trackSection.getTrackType() == TrackSection.trackType.UP_START) 
				{
					
					
					
					
					
					if(trackSection.isCursorInside(mouseMoveX, mouseMoveY)) 
					{
						g2d.setColor(Color.DARK_GRAY);
						
						int cirX = trackSection.getXc() - trackSection.getRadius();
						int cirY = trackSection.getYc() - trackSection.getRadius();
						
						
						g2d.fillOval(cirX, cirY, trackSection.getRadius() * 2, trackSection.getRadius() * 2);
						
						
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
	
	
	public void drawSignals(Graphics2D g2d , int lengthOffset) 
	{
		if(!listOfSignals.isEmpty()) 
		{
			Signal signal;
			
			for(int i = 0; i < listOfSignals.size(); i++) 
			{
				signal = listOfSignals.get(i);
				
				signal.isCursorInside(mouseMoveX, mouseMoveY);
				
				if(signal.signal.equals(signalType.BLOCK)) 
				{
					signal.setBlockOffset(lengthOffset);
				}
				
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
