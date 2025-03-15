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

public class SimulationController {
	
		
	
	public static List<Train> listOfTrainTraffic;
	public static List<TrackSection> listOfTrackSections;
	public static List<Signal> listOfSignals;
	
	//for drawing train which are for up line during deployment
	public static TrackSection upMainLine;
	
	//for drawing train which are for down line during deployment
	public static TrackSection downMainLine;
	
	private static long secondsOfDay = 0l;
	
	private int mouseMoveX , mouseMoveY;
	private int clickX = 0, clickY = 0;
	
	private boolean newClick = false;
	
	public void update( long deltaTime , long time , int mouseMoveX , int mouseMoveY , int mouseClickX , int mouseClickY) 
	{
		secondsOfDay = time;
		
		
		
		//---------------------------- Get Click Position----------------------------------
		
		this.mouseMoveX = mouseMoveX;
		this.mouseMoveY = mouseMoveY;
		
		if(this.clickX == mouseClickX && this.clickY == mouseClickY) 
		{
			newClick = false;
		}else {newClick = true;}
		
		
		
		this.clickX = mouseClickX;
		this.clickY = mouseClickY;
		
		
		
		//-----------------------------IF A SIGNAL IS CLICKED------------------------------------------
		
		if(!listOfSignals.isEmpty()) 
		{
			Signal signal;
			
			for(int i = 0; i < listOfSignals.size(); i++) 
			{
				signal = listOfSignals.get(i);
				
				if(signal.isCursorInside(mouseClickX, mouseClickY) && newClick) 
				{
					//clock(flag , nextSignalState)
					signal.clock(3 , 0);
					continue;
				}
				
				
			}
			
			
		}
		
		
		//---------------------------------IF A TRACK SWITCH IS CLICKED---------------------------------------
		
		if(!listOfTrackSections.isEmpty()) 
		{
			TrackSection trackSection;
			
			
			for(int i = 0; i < listOfTrackSections.size(); i++)
			{
				trackSection = listOfTrackSections.get(i);
				
				if(trackSection.isCursorInside(mouseMoveX, mouseMoveY) && newClick) 
				{
					trackSection.clock();
					continue;
				}
				
				
			}
			
		}
		

		
		
		
		//|----------------------------------MOVE THE TRAIN----------------------------------------------------
		
		if(!listOfTrainTraffic.isEmpty()) 
		{
			for(int i = 0; i < listOfTrainTraffic.size(); i++) 
			{
				Train train = listOfTrainTraffic.get(i);
				
				if(train.getDeployState() == 1) 
				{
					train.move(deltaTime);
					
					//up direction
					if(train.getMoveDirection() == 0) 
					{
						int trainFrontX = (int)train.x2;
						int trainFrontY = (int)train.y2;
						
						int trainBackX = (int)train.x1;
						int trainBackY = (int)train.y1;
						
						if(!listOfSignals.isEmpty()) 
						{
							Signal signal;
							
							for(int j = 0; j < listOfSignals.size(); j++) 
							{
								signal = listOfSignals.get(j);
								
								//signal is for up line
								if(signal.getHorizontalPosFlag() == 1) 
								{
									if(signal.detectTrain(trainFrontX , trainFrontY)) 
									{
										train.setNextSignal(signal);
										
									}
									
									if(signal.detectTrain(trainBackX , trainBackY)) 
									{
										signal.setSTATE(3);
										
									}
								}
								
								continue;
							}
							
						}
					}
					
					
					
					
					
					
				}
				
			}
			
		}
		
		
		newClick = false;
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
					
					
					
					if(trackSection.getSTATE() == 0) 
					{
						g2d.setColor(Color.RED);
						
					}else 
						
					if(trackSection.getSTATE() == 1){
						
						g2d.setColor(Color.GRAY.darker());
					}else {
						
						g2d.setColor(Color.GRAY);
					}
					
					
					
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
		
		
		if(!listOfTrainTraffic.isEmpty()) 
		{
			for(int i = 0; i < listOfTrainTraffic.size(); i++) 
			{
				Train train = listOfTrainTraffic.get(i);
				
				if(train.getDeployState() == 2) {continue;}
				
				if(train.getDeployState() == 0) 
				{
					if(train.getDeployTime()  <= secondsOfDay) 
					{
						train.setDeployState(1);
						
						//0 = up line
						if(train.getMoveDirection() ==  0) 
						{
							train.setCurrentSection(upMainLine);
							train.x1 = train.getCurrentSection().getX1() - 50;
							train.y1 = train.getCurrentSection().getY1();
							
						}else{
							
							train.setCurrentSection(downMainLine);
														
							
						}
						
					}
				}
				
				
				//active train 
				if(train.getDeployState() == 1) 
				{
					train.draw(g2d);
					
				}
				
				
			}
			
		}
	}

}
