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

public class Map3 implements TrackControllerRequirments{
	
	
	
	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	private List<Signal> listOfSignals = new ArrayList<>();
		
	private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	private  List<TrackSection> deployMainDownLine = new ArrayList<>();
		

	private  List<Signal> upLineStartSignals = new ArrayList<>();
	private  List<Signal> downLineStartSignals = new ArrayList<>();
	
	
	//main up and down tracks 	
	TrackSection mainUp1 = new TrackSection(trackType.UP , 1 , -1);
	
	TrackSection mainDown1 = new TrackSection(trackType.DOWN , 1 , -1);
	
	//left and right switches 
	TrackSection switchMainUp_MainDown_L = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  5);	
	
	TrackSection switchMainDown_MainUp_L = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 7);
	
	TrackSection switchMainUp_MainDown_R = new TrackSection(trackType.UP_END , mainUp1 , mainDown1 ,  14);
	
	TrackSection switchMainDown_MainUp_R = new TrackSection(trackType.DOWN_END , mainDown1 ,  mainUp1 , 16);
	
	
	//|-------------------------------------------Signals Home---------------------------------------------------------------
	
	
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
	
	
	
	//|----------------------------------------Signal Block Up Line--------------------------------------------------------
	
	Signal mainUP_1 = new Signal(signalType.BLOCK , mainUp1 , null , 1 , 1 , -1);
	Signal mainUP_2 = new Signal(signalType.BLOCK , mainUp1 , mainUP_1 , 3 , 1 , -1);
	//starter signal 
	Signal mainUP_3 = new Signal(signalType.HOME , mainUp1 , 13 , 1 , -1 , true);
	//rest of the block signals
	Signal mainUP_4 = new Signal(signalType.BLOCK , mainUp1 , mainUP_2 , 19 , 1 , -1);
	Signal mainUP_5 = new Signal(signalType.BLOCK , mainUp1 , mainUP_4 , 21 , 1 , -1);
	
	//---------------------------Up beyond signals-------------------------
	Signal mainUP_6 = new Signal(signalType.BLOCK , true , mainUp1 ,  mainUP_5 , 23 , 1 , -1);
	Signal mainUP_7 = new Signal(signalType.BLOCK , true , mainUp1 ,  mainUP_6 , 25 , 1 , -1);
	Signal mainUP_8 = new Signal(signalType.BLOCK , true , mainUp1 ,  mainUP_7 , 27 , 1 , -1);
	
	//reverse
	Signal mainUPR_1 = new Signal(signalType.BLOCK , mainUp1 , null , 21 , -1 , -1);
	Signal mainUPR_2 = new Signal(signalType.BLOCK , mainUp1 , mainUPR_1 , 19 , -1 , -1);
	//starter
	Signal mainUPR_3 = new Signal(signalType.HOME , mainUp1 , 9 , -1 , -1 , true);
	//rest of block signals
	Signal mainUPR_4 = new Signal(signalType.BLOCK , mainUp1 , mainUPR_3 , 3 , -1 , -1);
	Signal mainUPR_5 = new Signal(signalType.BLOCK , mainUp1 , mainUPR_4 , 1 , -1 , -1);
	
	//---------------------------Up reverse beyond signals-------------------------
	Signal mainUPR_6 = new Signal(signalType.BLOCK , true , mainUp1 , mainUPR_4 , -1 , -1 , -1);
	Signal mainUPR_7 = new Signal(signalType.BLOCK , true , mainUp1 , mainUPR_4 , -3 , -1 , -1);
	Signal mainUPR_8 = new Signal(signalType.BLOCK , true , mainUp1 , mainUPR_4 , -5 , -1 , -1);
	
	//|----------------------------------------Signal Block Down Line--------------------------------------------------------
	
	Signal mainDown_1 = new Signal(signalType.BLOCK , mainDown1 , null , 1 , 1 , -1);
	Signal mainDown_2 = new Signal(signalType.BLOCK , mainDown1 , mainDown_1 , 3 , 1 , -1);
	//starter signal 
	Signal mainDown_3 = new Signal(signalType.HOME , mainDown1 , 13 , 1 , -1 , true);
	//rest of the block signals
	Signal mainDown_4 = new Signal(signalType.BLOCK , mainDown1 , mainDown_2 , 19 , 1 , -1);
	Signal mainDown_5 = new Signal(signalType.BLOCK , mainDown1 , mainDown_4 , 21 , 1 , -1);
	
	//---------------------------Down beyond signals-------------------------
	Signal mainDown_6 = new Signal(signalType.BLOCK , true , mainDown1 , mainDown_5 , 23 , 1 , -1);
	Signal mainDown_7 = new Signal(signalType.BLOCK , true ,mainDown1 , mainDown_6 , 25 , 1 , -1);
	Signal mainDown_8 = new Signal(signalType.BLOCK , true , mainDown1 , mainDown_7 , 27 , 1 , -1);
	
	//reverse
	Signal mainDownR_1 = new Signal(signalType.BLOCK , mainDown1 , null , 21 , -1 , -1);
	Signal mainDownR_2 = new Signal(signalType.BLOCK , mainDown1 , mainDownR_1 , 19 , -1 , -1);
	//starter
	Signal mainDownR_3 = new Signal(signalType.HOME , mainDown1 , 9 , -1 , -1 , true);
	//rest of block signals
	Signal mainDownR_4 = new Signal(signalType.BLOCK , mainDown1 , mainDownR_2 , 3 , -1 , -1);
	Signal mainDownR_5 = new Signal(signalType.BLOCK , mainDown1 , mainDownR_4 , 1 , -1 , -1);
	
	//---------------------------Down reverse beyond signals-------------------------
	Signal mainDownR_6 = new Signal(signalType.BLOCK , true,  mainDown1 , mainDownR_5 , -1 , -1 , -1);
	Signal mainDownR_7 = new Signal(signalType.BLOCK , true ,mainDown1 , mainDownR_6 , -3 , -1 , -1);
	Signal mainDownR_8 = new Signal(signalType.BLOCK , true , mainDown1 , mainDownR_7 , -5 , -1 , -1);
	
	public void setGrid() 
	{
		Simulator.MAX_HORIZONTAL_ELM = 22;
	}

	@Override
	public List<TrackSection> getSections() {
		
		Collections.addAll(listOfTrackSections , 
				
				
				mainUp1,
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
				
				mainUP_1,
				mainUP_2,
				mainUP_3,
				mainUP_4,
				mainUP_5,
				mainUP_6,
				mainUP_7,
				mainUP_8,
				
				mainUPR_1,
				mainUPR_2,
				mainUPR_3,
				mainUPR_4,
				mainUPR_5,
				mainUPR_6,
				mainUPR_7,
				mainUPR_8,
				
				//------down line-----------
				
				mainDown_1,
				mainDown_2,
				mainDown_3,
				mainDown_4,
				mainDown_5,
				mainDown_6,
				mainDown_7,
				mainDown_8,
				
				mainDownR_1,
				mainDownR_2,
				mainDownR_3,
				mainDownR_4,
				mainDownR_5,
				mainDownR_6,
				mainDownR_7,
				mainDownR_8
				
				
				
							
				
				
				);
		
		
		return listOfSignals;
	}

	@Override
	public void initialiseDeployList() {
	
		Collections.addAll(deployMainUpLine, 
				
				mainUp1);
		
		Collections.addAll(deployMainDownLine, 
				
				mainDown1);
		
		Collections.addAll(upLineStartSignals, 
				
				mainUP_1);
		
		Collections.addAll(downLineStartSignals, 
				
				mainDown_5);
		
		SimulationController.deployMainUpLine = deployMainUpLine;
		SimulationController.deployMainDownLine = deployMainDownLine;
		SimulationController.upLineStartSignals = upLineStartSignals;
		SimulationController.downLineStartSignals = downLineStartSignals;
	}

	@Override
	public void addListToController() {
		// TODO Auto-generated method stub
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
