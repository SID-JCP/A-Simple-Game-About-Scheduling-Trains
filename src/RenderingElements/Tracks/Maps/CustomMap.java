package RenderingElements.Tracks.Maps;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Controller.SimulationController;
import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Xml.MapLoader;
import RenderingElements.Xml.XmlDoc;
import RenderingElements.Xml.Loader.XmlLoader;

public class CustomMap {

	private List<TrackSection> listOfTrackSections = new ArrayList<>();
	private List<Signal> listOfSignals = new ArrayList<>();
		
	private  List<TrackSection> deployMainUpLine = new ArrayList<>();
	private  List<TrackSection> deployMainDownLine = new ArrayList<>();
		
	private  List<Signal> upLineStartSignals = new ArrayList<>();
	private  List<Signal> downLineStartSignals = new ArrayList<>();
	
	private XmlDoc doc = new XmlDoc();
	
	
	public void loadData() 
	{
		XmlLoader.load(doc , "./Maps/map_1.xml");
		
		MapLoader mapLoader = new MapLoader(
									listOfTrackSections , 
									listOfSignals  , 
									deployMainUpLine , 
									deployMainDownLine ,
									upLineStartSignals, 
									downLineStartSignals);
		mapLoader.loadMap(doc);
	}
	
	
	public void addListToController() 
	{
		SimulationController.listOfTrackSections = listOfTrackSections;
		SimulationController.listOfSignals = listOfSignals;
		
		initialiseDeployList();
	}
	
	private void initialiseDeployList() 
	{
		SimulationController.deployMainUpLine = deployMainUpLine;
		SimulationController.deployMainDownLine = deployMainDownLine;
		SimulationController.upLineStartSignals = upLineStartSignals;
		SimulationController.downLineStartSignals = downLineStartSignals;
	}
}
