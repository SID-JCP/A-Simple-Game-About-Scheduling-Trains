package RenderingElements.Train;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Train.Traffic.Traffic1;
import RenderingElements.Train.Traffic.Traffic2;
import RenderingElements.Train.Traffic.Traffic3;
import RenderingElements.Train.Traffic.Traffic4;

public class TrafficContainer {

	
	//|-----------------------List of Traffic-----------------------------|
	public static List<List<Train>> TrafficList = new ArrayList<>();
	
	
	
	
	
	private void createTraffic() 
	{
		
		TrafficRequirments.addTrainList(Traffic1.trainList);
		TrafficRequirments.addTrainList(Traffic2.trainList);
		TrafficRequirments.addTrainList(Traffic3.trainList);
		TrafficRequirments.addTrainList(Traffic4.trainList);

		
	}
	
	//index specify to choose the traffic from traffic list 
	public void addTrafficListToController(int index) 
	{
		createTraffic();

		SimulationController.listOfTrainTraffic = TrafficList.get(index);
		
	}
	
	
	
}
