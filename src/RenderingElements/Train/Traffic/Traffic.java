package RenderingElements.Train.Traffic;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Train.Train;

public class Traffic {

	
	
	public List<List<Train>> TrafficList = new ArrayList<>();
	
	
	
	private List<Train> traffic1 = new ArrayList<>();
	
	Train train11 = new Train(1 ,-1 , 10 , false);
	Train train21 = new Train(1 ,-1 , 10 , false);
	Train train12 = new Train(1 ,-1 , 100 , true);
//	Train train13 = new Train(1 ,1 , 190, false);
//	Train train14 = new Train(1 ,1 , 280, false);
//	Train train15 = new Train(1 ,1 , 370, false);
//	Train train16 = new Train(1 ,1 , 460, false);
	
	private List<Train> traffic2 = new ArrayList<>();
	
	
	
	private void createTraffic() 
	{
		traffic1.add(train11);
		traffic1.add(train12);
//		traffic1.add(train13);
//		traffic1.add(train14);
//		traffic1.add(train15);
//		traffic1.add(train16);
		traffic1.add(train21);
		
	}
	
	
	public void addTrafficListToController(int index) 
	{
		createTraffic();
		
		TrafficList.add(0, traffic1);
		TrafficList.add(1, traffic2);
		
		SimulationController.listOfTrainTraffic = TrafficList.get(index);
		
	}
	
	
	
}
