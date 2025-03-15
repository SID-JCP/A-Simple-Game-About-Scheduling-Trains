package RenderingElements.Train.Traffic;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Train.Train;

public class Traffic {

	
	
	public List<List<Train>> TrafficList = new ArrayList<>();
	
	
	
	private List<Train> traffic1 = new ArrayList<>();
	Train train11 = new Train(0 , 10);
	Train train12 = new Train(0 , 120);
	Train train13 = new Train(0 , 250);
	
	private List<Train> traffic2 = new ArrayList<>();
	
	
	
	private void createTraffic() 
	{
		traffic1.add(train11);
		traffic1.add(train12);
		traffic1.add(train13);
		
		
		
	}
	
	
	public void addTrafficListToController(int index) 
	{
		createTraffic();
		
		TrafficList.add(0, traffic1);
		TrafficList.add(1, traffic2);
		
		SimulationController.listOfTrainTraffic = TrafficList.get(index);
		
	}
	
	
	
}
