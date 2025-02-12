package RenderingElements.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;

public interface TrackControllerRequirments {
	
	
	
	List<TrackSection> getSections();
	
	List<Signal> getSignals();

	void addListToController();
	
}
