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

public class Map4 implements TrackControllerRequirments{
	
	
	
	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	private List<Signal> listOfSignals = new ArrayList<>();
		
	private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	private  List<TrackSection> deployMainDownLine = new ArrayList<>();
		

	private  List<Signal> upLineStartSignals = new ArrayList<>();
	private  List<Signal> downLineStartSignals = new ArrayList<>();
	
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	
	TrackSection mainUp2 = new TrackSection(trackType.UP , 2 , -1);
	
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);

	
	
	//left and right switches 
	TrackSection switchMainUp_MainDown_L = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  1);	
	
	TrackSection switchMainDown_MainUp_L = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 3);
	
	TrackSection switchMainUp_MainDown_R = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  12);
	
	TrackSection switchMainDown_MainUp_R = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 14);
	
	
	//|-------------------------------------------Signals---------------------------------------------------------------
	
	
	//left switches
	Signal signal_mainUp_mainDown_U_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , 1 , 1 , 0);
	Signal signal_mainUp_mainDown_U_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , -1 , 1 , 0);
	
	
	Signal signal_mainUp_mainDown_D_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , -1 , -1 , 1);
	Signal signal_mainUp_mainDown_D_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_L , 1 , -1 , 1);
	
	
	Signal signal_mainDown_mainUp_U_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , -1 , 1 , 1);
	Signal signal_mainDown_mainUp_U_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , 1 , 1 , 1);
	
	
	Signal signal_mainDown_mainUp_D_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , -1 , -1 , 0);
	Signal signal_mainDown_mainUp_D_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_L , 1 , -1 , 0);
	
	
	
	Signal Fin_signal_mainUp_mainDown_U_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , 1 , 1 , 0);
	Signal Fin_signal_mainUp_mainDown_U_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , -1 , 1 , 0);
	
	
	Signal Fin_signal_mainUp_mainDown_D_L = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , 1 , -1 , 1);
	Signal Fin_signal_mainUp_mainDown_D_R = new Signal(Signal.signalType.HOME , switchMainUp_MainDown_R , -1 , -1 , 1);
	
	
	
	
	Signal Fin_signal_mainDown_mainUp_U_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , -1 , 1 , 1);
	Signal Fin_signal_mainDown_mainUp_U_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , 1 , 1 , 1);
	
	Signal Fin_signal_mainDown_mainUp_D_L = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , -1 , -1 , 0);
	//|---------------DOWN LINE START SIGNAL-------------------------|
	Signal Fin_signal_mainDown_mainUp_D_R = new Signal(Signal.signalType.HOME , switchMainDown_MainUp_R , 1 , -1 , 0);
		
	
	//-------------------------------------------goods line block signals-------------------------------------------------------
	Signal mainUP_2_1 = new Signal(signalType.BLOCK , mainUp2 , null , 1 , 1 , -1);
	Signal mainUP_2_2 = new Signal(signalType.BLOCK , mainUp2 , mainUP_2_1 , 3 , 1 , -1);
	Signal mainUP_2_3 = new Signal(signalType.BLOCK , mainUp2 , mainUP_2_2 , 5 , 1 , -1);
	Signal mainUP_2_4 = new Signal(signalType.BLOCK , mainUp2 , mainUP_2_3 , 7 , 1 , -1);
	Signal mainUP_2_5 = new Signal(signalType.BLOCK , mainUp2 , mainUP_2_4 , 9 , 1 , -1);
	Signal mainUP_2_6 = new Signal(signalType.BLOCK , mainUp2 , mainUP_2_5 , 11 , 1 , -1);
	Signal mainUP_2_7 = new Signal(signalType.BLOCK , mainUp2 , mainUP_2_6 , 13 , 1 , -1);
	Signal mainUP_2_8 = new Signal(signalType.BLOCK , mainUp2 , mainUP_2_7 , 15 , 1 , -1);
	//------up beyond signals-------
	Signal mainUP_2_9 = new Signal(signalType.BLOCK , true , mainUp2 , mainUP_2_8 , 17 , 1 , -1);
	Signal mainUP_2_10 = new Signal(signalType.BLOCK , true , mainUp2 , mainUP_2_9 , 19 , 1 , -1);
	Signal mainUP_2_11 = new Signal(signalType.BLOCK , true , mainUp2 , mainUP_2_10 , 21 , 1 , -1);
	
	
	
	Signal mainUPR_2_1 = new Signal(signalType.BLOCK , mainUp2 , null , 15 , -1 , -1);
	Signal mainUPR_2_2 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_1 , 13 , -1 , -1);
	Signal mainUPR_2_3 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_2 , 11 , -1 , -1);
	Signal mainUPR_2_4 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_3 , 9 , -1 , -1);
	Signal mainUPR_2_5 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_4 , 7 , -1 , -1);
	Signal mainUPR_2_6 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_5 , 5 , -1 , -1);
	Signal mainUPR_2_7 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_6 , 3 , -1 , -1);
	Signal mainUPR_2_8 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_7 , 1 , -1 , -1);
	//-----up reverse beyond signals-----
	Signal mainUPR_2_9 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_8 , -1 , -1 , -1);
	Signal mainUPR_2_10 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_9 , -3 , -1 , -1);
	Signal mainUPR_2_11 = new Signal(signalType.BLOCK , mainUp2 , mainUPR_2_10 , -5 , -1 , -1);
	
	
	@Override
	public List<TrackSection> getSections() {
	
		Collections.addAll(listOfTrackSections , 
				
				
				mainUp1,
				mainUp2,
				mainDown1,
			
				
				switchMainUp_MainDown_L,
				switchMainDown_MainUp_L,
				
				switchMainUp_MainDown_R,
				switchMainDown_MainUp_R
				
				);
		
		
		
		return listOfTrackSections;
	}

	@Override
	public List<Signal> getSignals() {
		
		
		Collections.addAll(listOfSignals , 
				
				signal_mainUp_mainDown_U_L,
				signal_mainUp_mainDown_U_R,
				signal_mainUp_mainDown_D_L,
				signal_mainUp_mainDown_D_R,
				
				
				signal_mainDown_mainUp_U_L,
				signal_mainDown_mainUp_U_R,
				signal_mainDown_mainUp_D_L,
				signal_mainDown_mainUp_D_R,
				
				
				Fin_signal_mainUp_mainDown_U_L,
				Fin_signal_mainUp_mainDown_U_R,
				Fin_signal_mainUp_mainDown_D_L,
				Fin_signal_mainUp_mainDown_D_R,
				
				
				Fin_signal_mainDown_mainUp_U_L,
				Fin_signal_mainDown_mainUp_U_R,
				Fin_signal_mainDown_mainUp_D_L,
				Fin_signal_mainDown_mainUp_D_R,
				
				mainUP_2_1,
				mainUP_2_2,
				mainUP_2_3,
				mainUP_2_4,
				mainUP_2_5,
				mainUP_2_6,
				mainUP_2_7,
				mainUP_2_8,
				mainUP_2_9,
				mainUP_2_10,
				mainUP_2_11,
				
				mainUPR_2_1,
				mainUPR_2_2,
				mainUPR_2_3,
				mainUPR_2_4,
				mainUPR_2_5,
				mainUPR_2_6,
				mainUPR_2_7,
				mainUPR_2_8,
				mainUPR_2_9,
				mainUPR_2_10,
				mainUPR_2_11
				
								
				
				
				);
		
		
		return listOfSignals;
	}

	@Override
	public void initialiseDeployList() {
		
		Collections.addAll(deployMainUpLine, 
				
				mainUp1,
				mainUp2);
		
		Collections.addAll(deployMainDownLine, 
				
				mainDown1);
		
		Collections.addAll(upLineStartSignals, 
				
				signal_mainUp_mainDown_U_L,
				mainUP_2_1);
		
		Collections.addAll(downLineStartSignals, 
				
				Fin_signal_mainDown_mainUp_D_L);
		
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
		
		switchMainUp_MainDown_L.setSignals(
				signal_mainUp_mainDown_U_L, 
				signal_mainUp_mainDown_U_R, 
				signal_mainUp_mainDown_D_L, 
				signal_mainUp_mainDown_D_R);
		
		switchMainDown_MainUp_L.setSignals(
				signal_mainDown_mainUp_U_L, 
				signal_mainDown_mainUp_U_R, 
				signal_mainDown_mainUp_D_L, 
				signal_mainDown_mainUp_D_R);
		
		switchMainUp_MainDown_R.setSignals(
				Fin_signal_mainUp_mainDown_U_L, 
				Fin_signal_mainUp_mainDown_U_R, 
				Fin_signal_mainUp_mainDown_D_L, 
				Fin_signal_mainUp_mainDown_D_R);
		
		switchMainDown_MainUp_R.setSignals(
				Fin_signal_mainDown_mainUp_U_L,
				Fin_signal_mainDown_mainUp_U_R, 
				Fin_signal_mainDown_mainUp_D_L, 
				Fin_signal_mainDown_mainUp_D_R);
		
	}

	@Override
	public void createStation() {
		
		Simulator.stationGraphicVerticalPos = 1;
		Simulator.stationGraphicHorizontalPos = 2;
		
	}

}
