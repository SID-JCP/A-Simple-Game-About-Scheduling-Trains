package RenderingElements.Xml;

import java.util.List;

import RenderingElements.Xml.Node.Node;
import RenderingElements.Xml.Node.NodeAttribute;


public class Viewer {
	
	static String gap = "";
	
	public static void displayAsList(XmlDoc doc) 
	{
		Node root = doc.getRootNode();
		
		recursiveDescent(root , "");
	}
	
	
	private static void recursiveDescent(Node node , String gap) 
	{
		

		StringBuilder attr = new StringBuilder("");
		
		if(!node.attributes.isEmpty()) 
		{
			List<NodeAttribute> nodeAttributeList = node.attributes;
			
			for(int i = 0; i < nodeAttributeList.size(); i++) 
				attr.append(" " + nodeAttributeList.get(i).getValue());
				
		}
		
		
		System.out.println(gap + "-" + node.getTag() + " =>" + attr.toString());
		
		if(node.getText() != null) 
		{
			System.out.println(gap + " " + node.getText());
		}
		
		if(node.children.isEmpty()) 
		{
			return;
		}else {
			
			List<Node> nodeList = node.children;
			
			for(int i = 0; i < nodeList.size(); i++) 
			{
				recursiveDescent(nodeList.get(i) , gap + " ");
			}
		}
		
		
	}
	
	

}
