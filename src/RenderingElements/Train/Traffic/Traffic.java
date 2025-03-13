package RenderingElements.Train.Traffic;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Controller.MapController;
import RenderingElements.Train.Train;

public class Traffic {

	
	
	public List<List<Train>> TrafficList = new ArrayList<>();
	
	
	
	private List<Train> traffic1 = new ArrayList<>();
	Train train11 = new Train();
	Train train12 = new Train();
	
	
	private List<Train> traffic2 = new ArrayList<>();
	Train train21 = new Train();
	Train train22 = new Train();
	
	
	private void createTraffic() 
	{
		traffic1.add(train11);
		traffic1.add(train12);
		
		traffic2.add(train21);
		traffic2.add(train22);
		
	}
	
	
	public void addTrafficListToController(int index) 
	{
		createTraffic();
		
		TrafficList.add(0, traffic1);
		TrafficList.add(1, traffic2);
		
		MapController.listOfTrainTraffic = TrafficList.get(index);
		
	}
	
	
	
}
