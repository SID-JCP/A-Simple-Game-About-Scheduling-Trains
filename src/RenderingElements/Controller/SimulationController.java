package RenderingElements.Controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import RenderingElements.Point.Point;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Train.Train;

public class SimulationController {
	

	
	public static List<Train> listOfTrainTraffic;
	public static List<TrackSection> listOfTrackSections;
	public static List<Signal> listOfSignals;
	
	//|------------------------List of main lines up or down on which train is to be spawned-----------|
	public static List<TrackSection> deployMainUpLine;
	public static List<TrackSection> deployMainDownLine;
	
	//|-------------------------List of Signals for up or down line which is first visible one---------|
	public static List<Signal> upLineStartSignals;
	public static List<Signal> downLineStartSignals;
		
	//train which is just deployed and is occupying the section 
	
	private HashMap<Integer , Train> upLineSet = new HashMap<>();
	private HashMap<Integer , Train> downLineSet = new HashMap<>();
	
	
	//the start signal of that section 
	private Signal startSignal;
	
	private static long secondsOfDay = 0l;
	
	private int mouseMoveX , mouseMoveY;
	private int clickX = 0, clickY = 0;
	
	private boolean newClick = false;
	
	public void update( long deltaTime , long time , int mouseMoveX , int mouseMoveY , int mouseClickX , int mouseClickY) 
	{
		secondsOfDay = time;
		
		
		
		//---------------------------- Get Click Position ----------------------------------
		
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
					//clock(flag , clocked by train (null for click))
					//3 means user
					signal.clock(3  , null);
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
					//looks at signals and changes speed accordingly
					train.signalLookout(listOfSignals);
					train.switchLookout(listOfTrackSections);
					
					//detect switches and change section accordingly 
					
					train.move(deltaTime);
					
					
					
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
				
				
				
				
				
				
				
				//|-----------------------------------Drawing of Lines----------------------------------|
				
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
					
					
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1 || trackSection.loopToLoop) 
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
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1 || trackSection.loopToLoop) 
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
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1 || trackSection.loopToLoop) 
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
					
					
					
					if(trackSection.getS1().getTrackLength() == -1 && trackSection.getS2().getTrackLength() == -1 || trackSection.loopToLoop) 
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
				
				
				//|-------------------------------------SWITCH ONLY PROGRAM-----------------------------------------------|
				
				if(!trackSection.getTrackType().equals(TrackSection.trackType.DOWN) && 
				   !trackSection.getTrackType().equals(TrackSection.trackType.UP))  
				{

					
					int switchRadius = trackSection.getSwitchRadius()/2;
					
					trackSection.setXs(trackSection.getX1() - switchRadius);
					trackSection.setYs(trackSection.getY1() - switchRadius);
					
					trackSection.setXe(trackSection.getX2() - switchRadius);
					trackSection.setYe(trackSection.getY2() - switchRadius);
						
//			|----------DEBUG TO VIEW SWITCH FRONT AND BACK---------------|
					
//					g2d.setColor(Color.blue);
//					g2d.fillOval(trackSection.getX1() - 6,
//								trackSection.getY1() - 6, 
//								trackSection.getSwitchRadius() * 2, 
//								trackSection.getSwitchRadius() * 2);
//					
//					g2d.setColor(Color.green);
//					g2d.fillOval(trackSection.getX2() -  6,
//							trackSection.getY2() - 6, 
//							trackSection.getSwitchRadius() * 2, 
//							trackSection.getSwitchRadius() * 2);
					
					
					
					//draw circle on hover inside center of switch
					if(trackSection.isCursorInside(mouseMoveX, mouseMoveY)) 
					{
						g2d.setColor(Color.DARK_GRAY);
						
						int cirX = trackSection.getXc() - trackSection.getRadius();
						int cirY = trackSection.getYc() - trackSection.getRadius();
						
						
						g2d.fillOval(cirX, cirY, trackSection.getRadius() * 2, trackSection.getRadius() * 2);
						
						
					}
					
					//change in states
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
				
				signal.setBlockOffset(lengthOffset);
				
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
				
				//the track number on which the train is to be deployed
				int lineSignalIndex = train.trackNumber;

				//the state of that signal 
				int startSignalState = 0;
				
				
				
				if(train.getDeployState() == 0) 
				{
					if(train.getDeployTime()  <= secondsOfDay) 
					{

						//get switch states and sections
						if(lineSignalIndex > 0) 
						{
							//get the line on which train need to be deployed
							train.setCurrentSection(deployMainUpLine.get(lineSignalIndex - 1));
							//get the first signal of that line 
							startSignal = upLineStartSignals.get(lineSignalIndex - 1);
							startSignalState = startSignal.getSTATE();
	
								
						}else {
								
							//get line for down
							train.setCurrentSection(deployMainDownLine.get(lineSignalIndex * - 1 - 1));
							//get first down line signal 
							startSignal = downLineStartSignals.get(lineSignalIndex * -1 - 1);
							startSignalState = startSignal.getSTATE();
							

						}
						
						
						
						//check deployed train for up line 
						if(lineSignalIndex > 0) 
						{
							if(upLineSet.isEmpty() || !upLineSet.containsKey(train.trackNumber)) 
							{
								
								deployTrain(train , startSignalState);
								upLineSet.put(train.trackNumber, train);
								
							}else {
								
								if(upLineSet.get(train.trackNumber).getClockCount() > 0) 
								{   
									deployTrain(train , startSignalState);
									upLineSet.put(train.trackNumber, train);
								}
								
							}
						}
						
						
						//check deployed train for down line 
						if(lineSignalIndex < 0) 
						{
							if(downLineSet.isEmpty() || !downLineSet.containsKey(train.trackNumber)) 
							{
								deployTrain(train , startSignalState);
								downLineSet.put(train.trackNumber, train);
							}else {
								
								if(downLineSet.get(train.trackNumber).getClockCount() > 0) 
								{
									deployTrain(train , startSignalState);
									downLineSet.put(train.trackNumber, train);
								}
						}
						
						


					}
				}
				
			}
				
				//active train [Draw it]
				if(train.getDeployState() == 1) 
				{
					train.draw(g2d);
					train.isCursorInside(mouseMoveX, mouseMoveY);
					
				}
				
				//passive train [Crossed the screen  , no need to draw ]
				if(train.getDeployState() == 2) {continue;}
			
		}
			
		}
	}
	
	
	
	private void deployTrain(Train train , int startSignalState) 
	{
		switch(startSignalState) 
		{
			//green 
			case 0:
				train.setCurrentSignalState(0);
				break;
			//double yellow 
			case 1:
				train.setCurrentSignalState(0);
				break;
			//yellow
			case 2:
				train.setCurrentSignalState(1);
				break;
			//red 
			case 3:
				train.setCurrentSignalState(2);
				break;
		}
		
		
		train.setDeployState(1);
		train.initialize();
		
		return;
	}

}
