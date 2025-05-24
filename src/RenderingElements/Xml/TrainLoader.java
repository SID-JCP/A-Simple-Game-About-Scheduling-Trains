package RenderingElements.Xml;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import RenderingElements.Controller.SimulatorClock;
import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;
import RenderingElements.Tracks.Maps.CustomMap;
import RenderingElements.Train.Train;
import RenderingElements.Xml.Loader.XmlLoader;
import RenderingElements.Xml.Node.Node;
import RenderingElements.Xml.Node.NodeAttribute;


public class TrainLoader {
	
	
	
	private int startTime = 0;
	
	//train meta data 
	private int movingDirection = 0;
	private int trackNumber = 1;
	private int arrivalTime = 0;
	private int departureTime = 0;
	private String name = "";
	private int platform = 0;
	
	private Color colorBlue = Color.blue;
	private Color colorRed = Color.red;
	private Color colorOrange = Color.orange;
	private Color colorGray = Color.gray;
	
	private Color choosenColor = Color.white;
	
	
	
	private List<Train> listOfTrainTraffic = new ArrayList<>();
	
	private XmlDoc doc = new XmlDoc();
	
	public List<Train> loadTraffic(String trafficFilePath) 
	{
		XmlLoader.load(doc , trafficFilePath);
		
		Node root = doc.getRootNode();
		
		recursiveDescent(root);
		
		
		return listOfTrainTraffic;
	}
	
	
	private int parseTime(String time) 
	{
		String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return (hours * 3600) + (minutes * 60);
	}
	
	
	private void recursiveDescent(Node node) 
	{
		

			
			if(node.getTag().equals("Trains")) 
			{
				node.attributes.forEach(a -> {
					if("startHour".equals(a.getKey())) 
					{
						SimulatorClock.HOUR = Integer.parseInt(a.getValue().split(":")[0]);
						SimulatorClock.MINUTES = Integer.parseInt(a.getValue().split(":")[1]);
						
						startTime = parseTime(a.getValue());
					}
				});
				
				
				node.children.forEach(t -> 
				{
					if(!t.getTag().equals("train")) 
					{
						//throw exception
					}
					
					
					t.attributes.forEach(a -> 
					{
						if("direction".equals(a.getKey())) 
						{
							
							if(a.getValue().equals("UP")) {movingDirection = 0;}else {movingDirection = 1;}
							
						}
						
						
						
						if("trackNumber".equals(a.getKey())) 
						{
							trackNumber = Integer.parseInt(a.getValue());
						}
						
						if("arrival".equals(a.getKey())) 
						{
							arrivalTime = parseTime(a.getValue()) - startTime;
						}
						
						if("departure".equals(a.getKey())) 
						{
							departureTime = parseTime(a.getValue()) - startTime;
						}
						
						if("name".equals(a.getKey())) 
						{
							name = a.getValue();
						}
						
						if("platform".equals(a.getKey())) 
						{
							platform = Integer.parseInt(a.getValue());
						}
						
						if("color".equals(a.getKey())) 
						{
							switch(a.getValue())
							{
								case "red":
									
									choosenColor = colorRed;
									break;
									
								case "blue":
									
									choosenColor = colorBlue;
									break;
									
								case "orange":
									
									choosenColor = colorOrange;
									break;
									
								case "gray":
									
									choosenColor = colorGray;
									break;
							}
						}
						
					});
					
					
					
					Train train = new Train(movingDirection , trackNumber , arrivalTime  , departureTime , name , platform , choosenColor);
					listOfTrainTraffic.add(train);
					
					
				});
				
				return;
			}
			
			
			
			
			
			

		
		if(node.children.isEmpty()) 
		{
			return;
			
		}else {
			
			List<Node> nodeList = node.children;
			
			for(int i = 0; i < nodeList.size(); i++) 
			{
				recursiveDescent(nodeList.get(i));
			}
		}
		
		
	}
	
	

}
