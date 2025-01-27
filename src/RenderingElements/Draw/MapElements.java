package RenderingElements.Draw;

import java.awt.Color;
import java.awt.Graphics2D;

import RenderingElements.Controller.MapController;
import RenderingElements.Tracks.Maps.TestTrackDesign;
import RenderingElements.Tracks.Maps.TrackStationDesign;

public class MapElements {

	//Default Map design 
	TrackStationDesign defaultStation = new TrackStationDesign();
	
	TestTrackDesign testTrack = new TestTrackDesign();
	
	
	
	//One controller for all trains , points and signals . For all different maps use this only 
	MapController Controller = new MapController();
	
	
	
	private int mapSelected = 1;
	private int WIDTH = 0;
	private int HEIGHT = 0;
	
	private int trackOffset = 50;
	private int lengthOffset = 0;
	
	
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
	
	public void update(long clockTime , int WIDTH , int HEIGHT) 
	{
		// Case 0 to N where the N is the map number 
		
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		xCENTER = (int)WIDTH/2;
		yCENTER = (int)HEIGHT/2;
		
		lengthOffset = (int)WIDTH/MAX_HORIZONTAL_ELM;
		
		switch(mapSelected) 
		{
			case 0:
				
				if(!trackElementsCompiled) 
				{
					defaultStation.addListToController();	
					//..
					//..
					//..
					
					trackElementsCompiled = true;
					
					
				}
							
				Controller.update(clockTime);
				
				break;
			case 1: 
				
				if(!trackElementsCompiled) 
				{
					testTrack.addListToController();
					//..
					//..
					//..
					
					trackElementsCompiled = true;
					
					
				}
				
				Controller.update(clockTime);
				
				break;
				
		}
		
		
		
		
		
		
		
	}
	
	
	private void positionGrid(Graphics2D g2d) 
	{
		
		lengthOffset = (int)WIDTH/MAX_HORIZONTAL_ELM;
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
		
		
		
		Controller.drawTracks(xCENTER , yCENTER , WIDTH , HEIGHT ,  trackOffset , lengthOffset ,  g2d);
		Controller.drawSignals(g2d , lengthOffset);
		
		
		Controller.drawTrain(g2d);
	}
	

}
