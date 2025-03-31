package RenderingElements.Tracks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import RenderingElements.Signal.Signal;

public interface TrackControllerRequirments {
	
	/*
	 * If Switch goes from *UP to UP* or *DOWN to UP* then its UP_START
	 * If Switch goes from *UP to DOWN* OR *UP to lower UP* then its UP_END
	 * 
	 * If Switch goes from *Down to Down* or *UP to DOWN* then its DOWN_START
	 * If Switch goes from *DOWN to UP* OR *DOWN to Upper DOWN* then its DOWN_END
	 * 
	 */
	
	//|--------------------CREATE THESE LISTS-------------------------------|
	
	//private List<TrackSection> listOfTrackSections = new ArrayList<>();
	//private List<Signal> listOfSignals = new ArrayList<>();
	
	//private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	//private  List<TrackSection> deployMainDownLine = new ArrayList<>();
	

	//private  List<Signal> upLineStartSignals = new ArrayList<>();
	//private  List<Signal> downLineStartSignals = new ArrayList<>();
	
	List<TrackSection> getSections();
	
	List<Signal> getSignals();
	
	void initialiseDeployList();

	void addListToController();
	
	void initializeInterlocking();
	
	void createStation();
	
}
