package RenderingElements.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;

public interface TrackControllerRequirments {
	
	/*
	 * If Switch goes from *UP to UP* or *DOWN to UP* then its UP_START
	 * If Switch goes from *UP to DOWN* OR *UP to lower UP* then its UP_END
	 * 
	 * If Switch goes from *Down to Down* or *UP to DOWN* then its DOWN_START
	 * If Switch goes from *DOWN to UP* OR *DOWN to Upper DOWN* then its DOWN_END
	 * 
	 */
	
	
	List<TrackSection> getSections();
	
	List<Signal> getSignals();

	void addListToController();
	
	void initializeInterlocking();
	
}
