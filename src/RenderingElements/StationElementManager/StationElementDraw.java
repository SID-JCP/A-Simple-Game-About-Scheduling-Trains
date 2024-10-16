package RenderingElements.StationElementManager;

import java.awt.Graphics2D;

import RenderingElements.Controller.tspController;
import RenderingElements.Tracks.Maps.Example.TrackStationDesign;

public class StationElementDraw {

	//Default Map design 
	TrackStationDesign defaultStation = new TrackStationDesign();
	
	
	
	//One controller for all trains , points and signals . For all different maps use this only 
	tspController oneMainController = new tspController();
	
	private int mapSelected = 0;
	
	
	
	
	
	//Update TSP COntroller with the CANVAS THREAD 
	public void update(long clockTime) 
	{
		
		switch(mapSelected) 
		{
			case 0:
				defaultStation.getSections();
				defaultStation.getController();
				
				
				//update tspController and trains , points and switches inside it 
				oneMainController.update(clockTime);
				
				break;
			case 2: 
				break;
		}
		
		
		
		
		
		
		
	}
	
	
	
	//draw method which will draw the tracks , signals , platform from the provided data 
		//into the paint class for the canvas [This is cap is imp fr]
	public void draw(Graphics2D g2d) 
	{
		
		
		defaultStation.drawTracks(g2d);
		
		oneMainController.drawTrain(g2d);
	}
	

}
