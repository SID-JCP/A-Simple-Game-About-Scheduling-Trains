package RenderingElements.Train;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Train.Traffic.Traffic1;
import RenderingElements.Train.Traffic.Traffic2;
import RenderingElements.Train.Traffic.Traffic3;
import RenderingElements.Train.Traffic.Traffic4;
import RenderingElements.Xml.Viewer;
import RenderingElements.Xml.XmlDoc;
import RenderingElements.Xml.Loader.XmlLoader;

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
	
	
	public void loadTrafficFromFile() 
	{
		XmlDoc doc = new XmlDoc();
		
		
		XmlLoader.load(doc , "./Maps/map_1.xml");
		
		
		Viewer.displayAsList(doc);
	}
	
	//index specify to choose the traffic from traffic list 
	public void addTrafficListToController(int index) 
	{
		loadTrafficFromFile();
		createTraffic();

		SimulationController.listOfTrainTraffic = TrafficList.get(index);
		
	}
	
	
	
}
