package RenderingElements.Xml;

import java.util.ArrayList;
import java.util.List;

import RenderingElements.Signal.Signal;
import RenderingElements.Signal.Signal.signalType;
import RenderingElements.Tracks.TrackSection;
import RenderingElements.Tracks.TrackSection.trackType;
import RenderingElements.Tracks.Maps.CustomMap;
import RenderingElements.Xml.Node.Node;
import RenderingElements.Xml.Node.NodeAttribute;


public class TrainLoader {
	
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
	private Signal prevSignal;
	private Signal blockSignal;
	
	public TrainLoader(List<TrackSection> listOfTrackSections, List<Signal> listOfSignals,
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
		

			//later load map data also 
			if(node.getTag().equals("Map")) 
			{
				node.attributes.forEach(a -> {
					if("sections".equals(a.getKey())) 
					{
						CustomMap.gridGap = Integer.parseInt(a.getValue());
					}
				});
				
			}
			
			//get all lines which are track sections 
			if(node.getTag().equals("lines") || 
			   node.getTag().equals("switches") || 
			   node.getTag().equals("signals")) 
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
					
					int trackNumber = Integer.parseInt(node.attributes.get(1).getValue());
					int length= Integer.parseInt(node.attributes.get(2).getValue());

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
			
			
			
			//block signals
			if(node.getTag().equals("block")) 
			{
				
				node.children.forEach(signal -> 
				{
					if(!signal.getTag().equals("signal")) 
					{
						
						//throw exception
					}
					
					signal.attributes.forEach(a -> 
					{
						if("track".equals(a.getKey()))
						{
									s =  listOfTrackSections.stream().filter((TrackSection t) -> 
									
										a.getValue().equals(t.getId())
									
									).findFirst().orElseThrow();
						}
						
						if("previous".equals(a.getKey())) 
						{
							
							prevSignal = listOfSignals.stream().filter((Signal s) -> 
								
								a.getValue().equals(s.getId())
								
								).findFirst().orElse(null);

						}
						
						if("position".equals(a.getKey())) 
						{
							signalPos = Integer.parseInt(a.getValue());
						}
						
						if("H".equals(a.getKey())) 
						{
							if(a.getValue().equals("left")) {signalHPos = 1;}else {signalHPos = -1;}
						}
						
						if("V".equals(a.getKey())) 
						{
							if(a.getValue().equals("up")) {signalVPos = 1;}else {signalVPos = -1;}
						}
						
						
					});
					
					
					
					if(signal.attributes.stream().anyMatch(n -> "alone".equals(n.getKey()) && "true".equals(n.getValue()))) 
					{
						blockSignal = new Signal(signalType.BLOCK , true ,  s , prevSignal  ,  signalPos , signalHPos , signalVPos);
					}else {
						
						blockSignal = new Signal(signalType.BLOCK ,  s , prevSignal  ,  signalPos , signalHPos , signalVPos);
					}
					
					
					blockSignal.setId(signal.getText());
					
					if(signal.attributes.stream().anyMatch(n -> "F".equals(n.getKey()) && "UP".equals(n.getValue()))) 
					{
						upLineStartSignals.add(blockSignal);
					}
					
					if(signal.attributes.stream().anyMatch(n -> "F".equals(n.getKey()) && "DOWN".equals(n.getValue()))) 
					{
						downLineStartSignals.add(blockSignal);
					}
					
					listOfSignals.add(blockSignal);
					
					
				});

				
				
				
				
			
			}
			
			
			//alone home signals 
			if(node.getTag().equals("home")) 
			{
				
				node.children.forEach(signal -> 
				{
					
					if(!signal.getTag().equals("signal")) 
					{
						//throw exception
					}
					
					signal.attributes.forEach(a -> 
					{
						if("track".equals(a.getKey()))
						{
									s =  listOfTrackSections.stream().filter((TrackSection t) -> 
									
										a.getValue().equals(t.getId())
									
									).findFirst().orElseThrow();
						}
						
						if("position".equals(a.getKey())) 
						{
							signalPos = Integer.parseInt(a.getValue());
						}
						
						if("H".equals(a.getKey())) 
						{
							if(a.getValue().equals("left")) {signalHPos = 1;}else {signalHPos = -1;}
						}
						
						if("V".equals(a.getKey())) 
						{
							if(a.getValue().equals("up")) {signalVPos = 1;}else {signalVPos = -1;}
						}
						
					});
					
					Signal homeSignal = new Signal(signalType.HOME , s , signalPos , signalHPos , signalVPos , true);
					homeSignal.setId(signal.getText());
					
					if(signal.attributes.stream().anyMatch(n -> "F".equals(n.getKey()) && "UP".equals(n.getValue()))) 
					{
						upLineStartSignals.add(homeSignal);
					}
					
					if(signal.attributes.stream().anyMatch(n -> "F".equals(n.getKey()) && "DOWN".equals(n.getValue()))) 
					{
						downLineStartSignals.add(homeSignal);
					}
					
					listOfSignals.add(homeSignal);
					
					
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
