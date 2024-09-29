package RenderingElements.StationElementManager;

import java.awt.Graphics2D;

import RenderingElements.Tracks.TrackStationDesign;

public class StationElementDraw {

	
	TrackStationDesign defaultStation = new TrackStationDesign();
	
	
	//draw method which will draw the tracks , signals , platform from the provided data 
	//into the paint class for the canvas [This is cap is imp fr]
	public void draw(Graphics2D g2d) 
	{
		defaultStation.drawTracks(g2d);
	}
	

}
