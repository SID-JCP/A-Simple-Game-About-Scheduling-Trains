package RenderingElements.Draw;

import java.awt.Graphics2D;

import RenderingElements.Controller.MapController;
import RenderingElements.Tracks.Maps.Example.TrackStationDesign;

public class MapElements {

	//Default Map design 
	TrackStationDesign defaultStation = new TrackStationDesign();
	
	
	
	//One controller for all trains , points and signals . For all different maps use this only 
	MapController Controller = new MapController();
	
	
	
	private int mapSelected = 0;
	private int WIDTH = 0;
	private int HEIGHT = 0;
	
	
	/*
	 *  1 - THE COUNT OF MAX NO OF TRACKS SIDE BY SIDE WHICH CAN BE ON SCREEN
	 *  
	 *  2 - THE COUNT OF MAX NO OF SIGNALS / POINTS WHICH CAN BE ON SCREEN 
	 */
	private int MAX_VERTICAL_ELM =  11;	
	private int MAX_HORIZONTAL_ELM  = 11;
	
	
	
	//Update Controller with the CANVAS THREAD 
	
	public void update(long clockTime , int WIDTH , int HEIGHT) 
	{
		// Case 0 to N where the N is the map number 
		
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		switch(mapSelected) 
		{
			case 0:
				
				defaultStation.getSections();
				defaultStation.getController();
				
				
				
				Controller.update(clockTime);
				
				break;
			case 2: 
				break;
		}
		
		
		
		
		
		
		
	}
	
	
	private void positionGrid(Graphics2D g2d) 
	{
		int vGap = (int)HEIGHT/MAX_VERTICAL_ELM;
		int hGap = (int)WIDTH/MAX_HORIZONTAL_ELM;
		
		int xPos = 0;
		int yPos = 0;
		
		for(int j = 0 ; j < MAX_HORIZONTAL_ELM; j++) 
		{
			g2d.drawLine(xPos , 0 , xPos, HEIGHT);
			xPos += hGap;
		}
		
		for(int i = 0 ; i < MAX_VERTICAL_ELM; i++) 
		{
			g2d.drawLine(0, yPos, WIDTH, yPos);
			yPos += vGap;
		}
	}
	
	
	
	
	//draw method which will draw the tracks , signals , platform from the provided data 
		//into the paint class for the canvas [This is cap is imp fr]
	public void draw(Graphics2D g2d) 
	{
		positionGrid(g2d);
		
		defaultStation.drawTracks(g2d);		
		Controller.drawTrain(g2d);
	}
	

}
