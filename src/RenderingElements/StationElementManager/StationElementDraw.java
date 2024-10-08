package RenderingElements.StationElementManager;

import java.awt.Graphics2D;

import RenderingElements.Controller.tspController;
import RenderingElements.Tracks.TrackStationDesign;

public class StationElementDraw {

	//Maps For all stations 
	TrackStationDesign defaultStation = new TrackStationDesign();
	
	
	//One controller for all trains , points and signals . For all different maps use this only 
	tspController oneMainController = new tspController();
	
	
	//draw method which will draw the tracks , signals , platform from the provided data 
	//into the paint class for the canvas [This is cap is imp fr]
	public void draw(Graphics2D g2d) 
	{
		
		defaultStation.drawTracks(g2d);
	}
	
	
	//Update TSP COntroller with the CANVAS THREAD 
	public void update(long clockTime) 
	{
		oneMainController.update(clockTime);
	}
	

}
