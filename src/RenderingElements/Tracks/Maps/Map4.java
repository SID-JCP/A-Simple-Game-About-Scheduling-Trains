package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackControllerRequirments;
import RenderingElements.Tracks.TrackSection;

public class Map4 implements TrackControllerRequirments{
	
	
	
	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	private List<Signal> listOfSignals = new ArrayList<>();
		
	private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	private  List<TrackSection> deployMainDownLine = new ArrayList<>();
		

	private  List<Signal> upLineStartSignals = new ArrayList<>();
	private  List<Signal> downLineStartSignals = new ArrayList<>();
	

	@Override
	public List<TrackSection> getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Signal> getSignals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialiseDeployList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListToController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeInterlocking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createStation() {
		// TODO Auto-generated method stub
		
	}

}
