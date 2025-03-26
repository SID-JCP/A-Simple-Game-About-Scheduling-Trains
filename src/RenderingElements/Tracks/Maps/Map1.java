package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackControllerRequirments;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public class Map1 implements TrackControllerRequirments{

	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	private List<Signal> listOfSignals = new ArrayList<>();
		
	private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	private  List<TrackSection> deployMainDownLine = new ArrayList<>();
		

	private  List<Signal> upLineStartSignals = new ArrayList<>();
	private  List<Signal> downLineStartSignals = new ArrayList<>();
	
	
	
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	
	
	//up Direction signals 
	Signal mainUP_1 = new Signal(signalType.BLOCK , mainUp1 , null , 1 , 1 , -1);
	Signal mainUP_2 = new Signal(signalType.BLOCK , mainUp1 , mainUP_1 , 3 , 1 , -1);
	
	Signal mainUP_3 = new Signal(signalType.HOME , mainUp1 , mainUP_2 , 5 , 1 , -1);
	Signal mainUP_4 = new Signal(signalType.HOME , mainUp1 , mainUP_3 , 8 , 1 , -1);
	
	Signal mainUP_5 = new Signal(signalType.BLOCK , mainUp1 , mainUP_4 , 10 , 1 , -1);
	Signal mainUP_6 = new Signal(signalType.BLOCK , mainUp1 , mainUP_5 , 12 , 1 , -1);
	
	//down line signals
	Signal mainDOWN_1 = new Signal(signalType.BLOCK , mainUp1 , null , 12 , -1 , -1);
	Signal mainDOWN_2 = new Signal(signalType.BLOCK , mainUp1 , mainDOWN_1 , 10 , -1 , -1);
	
	Signal mainDOWN_3 = new Signal(signalType.HOME , mainUp1 , mainDOWN_2 , 8 , -1 , -1);
	Signal mainDOWN_4 = new Signal(signalType.HOME , mainUp1 , mainDOWN_3 , 5 , -1 , -1);
	
	Signal mainDOWN_5 = new Signal(signalType.BLOCK , mainUp1 , mainDOWN_4 , 3 , -1 , -1);
	Signal mainDOWN_6 = new Signal(signalType.BLOCK , mainUp1 , mainDOWN_5 , 1 , -1 , -1);
	
	
	
	@Override
	public List<TrackSection> getSections() 
	{
		
		Collections.addAll(listOfTrackSections , 
				
				
				mainUp1
				
				);
		
		
		
		return listOfTrackSections;
		
		
	}

	@Override
	public List<Signal> getSignals() {
		
		Collections.addAll(listOfSignals , 
				
				mainUP_1,
				mainUP_2,
				mainUP_3,
				mainUP_4,
				mainUP_5,
				mainUP_6,
				
				mainDOWN_1,
				mainDOWN_2,
				mainDOWN_3,
				mainDOWN_4,
				mainDOWN_5,
				mainDOWN_6

				);
		
		
		
		
		return listOfSignals;
	}

	@Override
	public void addListToController() {
		
		SimulationController.listOfTrackSections = getSections();
		SimulationController.listOfSignals = getSignals();
		
		initialiseDeployList();
	}

	@Override
	public void initializeInterlocking() {
		
		//No switches , so no need 
		
	}

	@Override
	public void initialiseDeployList() {
		
		Collections.addAll(deployMainUpLine, 
				
				mainUp1
				
				);
		
		
		
		Collections.addAll(deployMainDownLine, 
				
				mainUp1
				
				);
		
		
		
		Collections.addAll(upLineStartSignals, 
				
				mainUP_1);
		
		
		
		Collections.addAll(downLineStartSignals, 
				
				mainDOWN_1
				
				);
		
		SimulationController.deployMainUpLine = deployMainUpLine;
		SimulationController.deployMainDownLine = deployMainDownLine;
		SimulationController.upLineStartSignals = upLineStartSignals;
		SimulationController.downLineStartSignals = downLineStartSignals;
		
	}

}
