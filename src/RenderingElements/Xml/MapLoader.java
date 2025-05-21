package RenderingElements.Xml;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Signal.Signal;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;
import RenderingElements.Xml.Node.Node;
import RenderingElements.Xml.Node.NodeAttribute;


public class MapLoader {
	
	private List<TrackSection> listOfTrackSections;
	private List<Signal> listOfSignals;
		
	private  List<TrackSection> deployMainUpLine;
	private  List<TrackSection> deployMainDownLine;
		
	private  List<Signal> upLineStartSignals;
	private  List<Signal> downLineStartSignals;
	
	
	private List<Signal> tempSignalHolder = new ArrayList<>();
	
	private Integer signalPos = 0;
	private Integer signalHPos = 1;
	private Integer signalVPos = 1;
	
	private TrackSection s;
	
	public MapLoader(List<TrackSection> listOfTrackSections, List<Signal> listOfSignals,
			List<TrackSection> deployMainUpLine, List<TrackSection> deployMainDownLine, List<Signal> upLineStartSignals,
			List<Signal> downLineStartSignals) {
		
		this.listOfTrackSections = listOfTrackSections;
		this.listOfSignals = listOfSignals;
		this.deployMainUpLine = deployMainUpLine;
		this.deployMainDownLine = deployMainDownLine;
		this.upLineStartSignals = upLineStartSignals;
		this.downLineStartSignals = downLineStartSignals;
	}
	
	
	
	public void loadMap(XmlDoc doc) 
	{
		Node root = doc.getRootNode();
		
		recursiveDescent(root);
	}
	
	
	private void recursiveDescent(Node node) 
	{
		

		if(!node.attributes.isEmpty()) 
		{
			List<NodeAttribute> nodeAttributeList = node.attributes;
			
			//later load map data also 
			if(node.getTag().equals("Map")) 
			{
				nodeAttributeList.forEach(a -> {
					System.out.println(a.getKey());
				});
				
			}
			
			//get all lines which are track sections 
			if(node.getTag().equals("lines") || node.getTag().equals("switches")) 
			{
				List<Node> nodeList = node.children;
				
				for(int i = 0; i < nodeList.size(); i++) 
				{
					recursiveDescent(nodeList.get(i));
				}
				
				return;
			}
			
			
			
			if(node.getTag().equals("track")) 
			{
				if(node.parent.getTag().equals("lines")) 
				{
					String type =  node.attributes.get(0).getValue();
					
					int trackNumber = Integer.parseInt(nodeAttributeList.get(1).getValue());
					int length= Integer.parseInt(nodeAttributeList.get(2).getValue());

					if(type.equals("UP")) 
					{
						s = new TrackSection(trackType.UP , trackNumber , length);
						
						if(node.attributes.stream().anyMatch(n -> "deploy".equals(n.getKey()) && "true".equals(n.getValue()))) 
						{
							deployMainUpLine.add(s);
						}
						
					}else {
						
						s = new TrackSection(trackType.DOWN , trackNumber , length);
						
						if(node.attributes.stream().anyMatch(n -> "deploy".equals(n.getKey()) && "true".equals(n.getValue()))) 
						{
							deployMainDownLine.add(s);
						}
					}
					
					
					s.setId(node.getText());
					listOfTrackSections.add(s);
					
					
					return;
					
				}
				
				
				if(node.parent.getTag().equals("switches")) 
				{
					String type =  node.attributes.get(0).getValue();
					String startLine = node.attributes.get(1).getValue();
					String endLine = node.attributes.get(2).getValue();
					Integer position = Integer.parseInt(node.attributes.get(3).getValue());
					
					TrackSection startSection = listOfTrackSections.stream().filter((TrackSection t) -> 
						t.getId().equals(startLine)
					).findFirst().orElse(null);
					
					TrackSection endSection = listOfTrackSections.stream().filter((TrackSection t) -> 
						t.getId().equals(endLine)
					).findFirst().orElse(null);
					
					
					if(startSection == null || endSection == null) 
					{//throw error
						
					}
					
					switch(type) 
					{
						case "UP_START":
							s = new TrackSection(trackType.UP_START , startSection , endSection , position);
							break;
							
						case "UP_END":
							s = new TrackSection(trackType.UP_END , startSection , endSection , position);
							break;
							
						case "DOWN_START":
							s = new TrackSection(trackType.DOWN_START , startSection , endSection , position);
							break;
							
						case "DOWN_END":
							s = new TrackSection(trackType.UP_END , startSection , endSection , position);
							break;
						default:
							//throw error 
							break;
					}
					
					listOfTrackSections.add(s);
					tempSignalHolder.clear();
			
					
					//--------Fix inner statement causes a loop 
					
					
					
					
					//get signals of this switch 
					node.children.forEach(c -> {
						
						if(!c.getTag().equals("signal")) {
							//throw exception
						};
						
						c.attributes.forEach(a ->{

							if(a.getKey().equals("P")) 
							{
								if(a.getValue().equals("start")) {signalPos = 0;}else {signalPos = 1;}
							}
							
							if(a.getKey().equals("H")) 
							{
								if(a.getValue().equals("left")) {signalHPos = 1;}else {signalHPos = -1;}
							}
							
							if(a.getKey().equals("V")) 
							{
								if(a.getValue().equals("up")) {signalVPos = 1;}else {signalVPos = -1;}
							}
							

						});
						
						Signal signal = new Signal(Signal.signalType.HOME , s , signalHPos , signalVPos , signalPos);
						listOfSignals.add(signal);
						
						tempSignalHolder.add(signal);
						
						
						if(c.attributes.stream().anyMatch(n -> "F".equals(n.getKey()) && "UP".equals(n.getValue()))) 
						{
							upLineStartSignals.add(signal);
						}
						
						if(c.attributes.stream().anyMatch(n -> "F".equals(n.getKey()) && "DOWN".equals(n.getValue()))) 
						{
							downLineStartSignals.add(signal);
						}
						
					});
					
					//providing signals to switch 
					s.setSignals(
							tempSignalHolder.get(0), 
							tempSignalHolder.get(1), 
							tempSignalHolder.get(2),
							tempSignalHolder.get(3));
					
					tempSignalHolder.clear();
					
					return;
					
				}

			}
			
			if(node.getTag().equals("signals")) {return;}
			
			//block signals
			if(node.getTag().equals("block")) {return;}
			
			//alone home signals 
			if(node.getTag().equals("home")) {return;}
			
				
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
