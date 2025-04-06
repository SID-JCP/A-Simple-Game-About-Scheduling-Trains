package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Draw.Simulator;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackControllerRequirments;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;

public class Map2 implements TrackControllerRequirments{
	
	
	
	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	private List<Signal> listOfSignals = new ArrayList<>();
		
	private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	private  List<TrackSection> deployMainDownLine = new ArrayList<>();
		

	private  List<Signal> upLineStartSignals = new ArrayList<>();
	private  List<Signal> downLineStartSignals = new ArrayList<>();
	
	
	///-------------------------------Tracks sections-----------------------------------------
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	
	TrackSection loopUp1 = new TrackSection(trackType.UP , 2 , 3);
	
	//switch from main UP line to loop 
	TrackSection switchMainUp1_loopUp1 = new TrackSection(trackType.UP_START , mainUp1 , loopUp1 , 6);
		
	//switch from loop to main Up line 
	TrackSection switchloopUp1_MainUp1 = new TrackSection(trackType.UP_END , loopUp1 , mainUp1 , 10);
	
	//--------------------------------Signals -----------------------------------------------------
	
	//up line block signals 
	Signal mainUP_1 = new Signal(signalType.BLOCK , mainUp1 , null ,    1 , 1 , -1);
	Signal mainUP_2 = new Signal(signalType.BLOCK , mainUp1 , mainUP_1 , 3 , 1 , -1);
	
	//alone home signal
	Signal mainUP_3 = new Signal(signalType.HOME , mainUp1 , 5 , 1 , -1 , true);
	Signal mainUP_4 = new Signal(signalType.HOME , mainUp1 , 11 , 1 , -1 , true);
	
	Signal mainUP_5 = new Signal(signalType.BLOCK , mainUp1 , mainUP_2 , 13 , 1 , -1);
	Signal mainUP_6 = new Signal(signalType.BLOCK , mainUp1 , mainUP_5 , 15 , 1 , -1);
	
	//-------------------------------UP Beyond Signals-------------------------------------
	Signal mainUP_7 = new Signal(signalType.BLOCK , true , mainUp1 , mainUP_6 , 17 , 1 , -1);
	Signal mainUP_8 = new Signal(signalType.BLOCK , true , mainUp1 , mainUP_7 , 19 , 1 , -1);
	Signal mainUP_9 = new Signal(signalType.BLOCK , true ,mainUp1 , mainUP_8 , 21 , 1 , -1);
	
	
	//loop line signals 
	Signal signal_mainUp1_loopUp1_U_L = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , -1 , 1 , 1);
	Signal signal_mainUp1_loopUp1_U_R = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , 1 , 1 , 1);
	
	Signal signal_mainUp1_loopUp1_D_L = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , -1 , -1 , 0);
	Signal signal_mainUp1_loopUp1_D_R = new Signal(Signal.signalType.HOME , switchMainUp1_loopUp1 , 1 , -1 , 0);
	
	Signal signal_loopUp1_mainUp1_U_L = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , 1 , 1 , 0);
	Signal signal_loopUp1_mainUp1_U_R = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , -1 , 1 , 0);
	
	Signal signal_loopUp1_mainUp1_D_L = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , 1 , -1 , 1);
	Signal signal_loopUp1_mainUp1_D_R = new Signal(Signal.signalType.HOME , switchloopUp1_MainUp1 , -1 , -1 , 1);
	
	
	//down line block signals 
	Signal mainDOWN_1 = new Signal(signalType.BLOCK , mainUp1 , null , 15 , -1 , -1);
	Signal mainDOWN_2 = new Signal(signalType.BLOCK , mainUp1 , mainDOWN_1 , 13 , -1 , -1);
	
	//alone home signal
	Signal mainDOWN_3 = new Signal(signalType.HOME , mainUp1 , 11 , -1 , -1 , true);
	Signal mainDOWN_4 = new Signal(signalType.HOME , mainUp1 , 5 , -1 , -1 , true);
	
	Signal mainDOWN_5 = new Signal(signalType.BLOCK , mainUp1 , mainDOWN_2 , 3 , -1 , -1);
	Signal mainDOWN_6 = new Signal(signalType.BLOCK , mainUp1 , mainDOWN_5 , 1 , -1 , -1);
	
	//-------------------------------DOWN Beyond Signals-------------------------------------
	Signal mainDOWN_7 = new Signal(signalType.BLOCK , true , mainUp1 , mainDOWN_6 , -1 , -1 , -1);
	Signal mainDOWN_8 = new Signal(signalType.BLOCK , true ,mainUp1 , mainDOWN_7 , -3 , -1 , -1);
	Signal mainDOWN_9 = new Signal(signalType.BLOCK , true ,mainUp1 , mainDOWN_8 , -5 , -1 , -1);
	

	@Override
	public List<TrackSection> getSections() {
		
		
	Collections.addAll(listOfTrackSections , 
					
					
					mainUp1,
					loopUp1,
					switchMainUp1_loopUp1,
					switchloopUp1_MainUp1
					
					
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
				mainUP_7,
				mainUP_8,
				mainUP_9,
				
								
				signal_mainUp1_loopUp1_U_L,
				signal_mainUp1_loopUp1_U_R,
				signal_mainUp1_loopUp1_D_L,
				signal_mainUp1_loopUp1_D_R,
				
				signal_loopUp1_mainUp1_U_L,
				signal_loopUp1_mainUp1_U_R,
				signal_loopUp1_mainUp1_D_L,
				signal_loopUp1_mainUp1_D_R,
				
				mainDOWN_1,
				mainDOWN_2,
				mainDOWN_3,
				mainDOWN_4,
				mainDOWN_5,
				mainDOWN_6,
				mainDOWN_7,
				mainDOWN_8,
				mainDOWN_9
				
				
				
				
				);
		
		
		return listOfSignals;

	}

	@Override
	public void initialiseDeployList() 
	{
		
		Collections.addAll(deployMainUpLine, 
				
				mainUp1);
		
		Collections.addAll(deployMainDownLine, 
				
				mainUp1);
		
		Collections.addAll(upLineStartSignals, 
				
				mainUP_1);
		
		Collections.addAll(downLineStartSignals, 
				
				mainDOWN_1);
		
		SimulationController.deployMainUpLine = deployMainUpLine;
		SimulationController.deployMainDownLine = deployMainDownLine;
		SimulationController.upLineStartSignals = upLineStartSignals;
		SimulationController.downLineStartSignals = downLineStartSignals;
	}

	@Override
	public void addListToController() {
		
		SimulationController.listOfTrackSections = getSections();
		SimulationController.listOfSignals = getSignals();
		
		initialiseDeployList();
		
	}

	@Override
	public void initializeInterlocking() {
		
		
		switchMainUp1_loopUp1.setSignals(
				signal_mainUp1_loopUp1_U_L, 
				signal_mainUp1_loopUp1_U_R, 
				signal_mainUp1_loopUp1_D_L, 
				signal_mainUp1_loopUp1_D_R);
		
		switchloopUp1_MainUp1.setSignals(
				signal_loopUp1_mainUp1_U_L, 
				signal_loopUp1_mainUp1_U_R, 
				signal_loopUp1_mainUp1_D_L, 
				signal_loopUp1_mainUp1_D_R);
		
	}

	@Override
	public void createStation() {
		
		Simulator.stationGraphicVerticalPos = 2;
		Simulator.stationGraphicHorizontalPos = 2;
		
	}

}
