package RenderingElements.Train;

import java.util.List;

public interface TrafficRequirments {
	
	public static void addTrainList(List<Train> traffic) {
		
		TrafficContainer.TrafficList.add(traffic);
	}

}
