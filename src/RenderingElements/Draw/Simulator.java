package RenderingElements.Draw;

import java.awt.Color;
import java.awt.Graphics2D;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Tracks.Maps.TestTrackDesign;
import RenderingElements.Tracks.Maps.TrackStationDesign;
import RenderingElements.Train.Traffic.Traffic;

public class Simulator {

	//Default Map design 
	TrackStationDesign defaultStation = new TrackStationDesign();
	
	TestTrackDesign testTrack = new TestTrackDesign();
	
	
	
	//One controller for all trains , points and signals . For all different maps use this only 
	SimulationController Controller = new SimulationController();
	
	Traffic traffic = new Traffic();
	
	
	
	private int mapSelected = 0;
	private int trafficSelected = 0;
	
	private int WIDTH = 0;
	private int HEIGHT = 0;
	
	private int trackOffset = 50;
	private int blockOffset = 0;
	
	
	/*
	 *  1 - THE COUNT OF MAX NO OF TRACKS SIDE BY SIDE WHICH CAN BE ON SCREEN
	 *  
	 *  2 - THE COUNT OF MAX NO OF SIGNALS / POINTS WHICH CAN BE ON SCREEN 
	 *  
	 *  MAX VERITICAL ELEMENTS SHOULD BE EVEN 
	 */
	private int MAX_VERTICAL_ELM =  4;	
	private int MAX_HORIZONTAL_ELM  = 16;
	
	
	private int xCENTER;
	private int yCENTER;
	
	
	private boolean trackElementsCompiled = false;
	
	
	
	//Update Controller with the CANVAS THREAD 
	
	public void update(long deltaTime , long clockTime , int WIDTH , int HEIGHT , int mouseMoveX , int mouseMoveY , int mouseClickX , int mouseClickY) 
	{
		// Case 0 to N where the N is the map number 
		
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		xCENTER = (int)WIDTH/2;
		yCENTER = (int)HEIGHT/2;
		
		blockOffset = (int)WIDTH/MAX_HORIZONTAL_ELM;
		
		if(!trackElementsCompiled) 
		{
			switch(mapSelected) 
			{
				case 0:
					
					defaultStation.addListToController();	
					defaultStation.initializeInterlocking();
					traffic.addTrafficListToController(trafficSelected);
					//..
					//..
					//..
						
					
					break;
				case 1: 
					
					
					testTrack.addListToController();
					//..
					//..

					break;
					
			}
			
			
			trackElementsCompiled = true;
		}
		
		
		
		
		
		Controller.update( deltaTime ,clockTime , mouseMoveX , mouseMoveY ,  mouseClickX ,  mouseClickY);
		
		
		
	}
	
	
	private void positionGrid(Graphics2D g2d) 
	{
		
		blockOffset = (int)WIDTH/MAX_HORIZONTAL_ELM;
		int hGap = (int)WIDTH/MAX_HORIZONTAL_ELM;
		int xPos = 0;
		
		
		
		int yCenter = (int)HEIGHT/2;
		int xCenter = (int)WIDTH/2;
		
		
		//Center Lines 
		g2d.drawLine(0, yCenter, WIDTH, yCenter);
		g2d.drawLine(xCenter , 0 , xCenter, HEIGHT);
		
		//Horizontal line for drawing points and fixing length of station tracks 
		g2d.setColor(Color.green);
		for(int j = 0 ; j < MAX_HORIZONTAL_ELM; j++) 
		{
			g2d.drawLine(xPos , 0 , xPos, HEIGHT);
			xPos += hGap;
		}

	}
	
	
	
	
	//draw method which will draw the tracks , signals , platform from the provided data 
	//into the paint class for the canvas [This is cap is imp fr]
	
	public void draw(Graphics2D g2d) 
	{
		//debug
//		positionGrid(g2d);
		
		
		
		Controller.drawTracks(xCENTER , yCENTER , WIDTH , HEIGHT ,  trackOffset , blockOffset ,  g2d);
		Controller.drawSignals(g2d , blockOffset);
		
		
		Controller.drawTrain(g2d);
	}
	

}
