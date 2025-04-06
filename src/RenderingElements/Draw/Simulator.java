package RenderingElements.Draw;

import java.awt.Color;
import java.awt.Graphics2D;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Tracks.Maps.Map1;
import RenderingElements.Tracks.Maps.Map2;
import RenderingElements.Tracks.Maps.TestTrackDesign;
import RenderingElements.Tracks.Maps.TrackStationDesign;
import RenderingElements.Train.TrafficContainer;

public class Simulator {

	//Default Map design 
	TrackStationDesign defaultStation = new TrackStationDesign();
	
	//test map 
	TestTrackDesign testTrack = new TestTrackDesign();
	
	
	//|-------------------------------ACTUAL MAPS FOR USE--------------------------------------|
	Map1 map1 = new Map1();
	Map2 map2 = new Map2();
	
	
	
	//One controller for all trains , points and signals . For all different maps use this only 
	SimulationController Controller = new SimulationController();
	
	TrafficContainer traffic = new TrafficContainer();
	
	
	private int mapSelected = 3;
	private int trafficSelected = 0;
	
	private int WIDTH = 0;
	private int HEIGHT = 0;
	
	private int trackOffset = 50;
	private int blockOffset = 0;
	
	
	public static int stationGraphicVerticalPos;
	public static int stationGraphicHorizontalPos;
	
	
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
					defaultStation.createStation();
					//..
					//..
					//..
						
					
					break;
				case 1: 
					
					
					testTrack.addListToController();
					testTrack.initializeInterlocking();
					

					break;
				case 2:
					
					map1.addListToController();
					map1.initializeInterlocking();
					map1.createStation();
					
				case 3:
					
					map2.addListToController();
					map2.initializeInterlocking();
					map2.createStation();
					
					break;
					
			}
			
			traffic.addTrafficListToController(trafficSelected);
			trackElementsCompiled = true;
		}
		
		
		
		
		
		Controller.update(deltaTime ,clockTime , mouseMoveX , mouseMoveY ,  mouseClickX ,  mouseClickY);
		
		
		
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
	
	private void drawStation(Graphics2D g2d) 
	{
		int gap = 36;
		
		int StationXPos  = (xCENTER - stationGraphicHorizontalPos * blockOffset) +  gap;
		int StationYPos = (yCENTER - stationGraphicVerticalPos * trackOffset) - gap;
		
		int StationWidth = 2 *(stationGraphicHorizontalPos * blockOffset -  gap);
		int StationHeight = 2 * (stationGraphicVerticalPos * trackOffset + gap);
		
		g2d.setColor(Color.DARK_GRAY.darker().darker());
		g2d.fillRoundRect(StationXPos, StationYPos, StationWidth, StationHeight, 20 , 20);
		
	}
	
	
	//draw method which will draw the tracks , signals , platform from the provided data 
	//into the paint class for the canvas [This is cap is imp fr]
	
	public void draw(Graphics2D g2d) 
	{
		//debug
//		positionGrid(g2d);
		
		drawStation(g2d);
		
		
		Controller.drawTracks(xCENTER , yCENTER , WIDTH , HEIGHT ,  trackOffset , blockOffset ,  g2d);
		Controller.drawSignals(g2d , blockOffset);
		
		
		Controller.drawTrain(g2d);
	}
	

}
