package RenderingElements.Tracks;

import java.awt.Graphics2D;

public interface sectionDesignRequirments {
	
	int MAX_WIDTH = 1500; //Canvas Width Max 
	int MAX_HEIGHT = 600; //Canvas Height Max 
	
	int vCenter = 750; //Canvas Vertical Center 
	int hCenter = 300; //Canvas Vertical Center 
	
	int trackGap = 20;
	int platfromGap = 50;
	
	
	public void drawTracks(Graphics2D g);
	
	public void drawPoints(Graphics2D g);	
	
	public void drawSignals(Graphics2D g);
	

}
