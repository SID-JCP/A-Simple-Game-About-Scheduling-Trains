package RenderingElements.Xml.Node;

import java.util.ArrayList;
import java.util.List;

public class Node
{

	public Node parent;
	
	// <tag>
	String tag;

	//<tag> text </tag>
	String text;
	
	//<tag key = "value" key2 = "value2">
	public List<NodeAttribute> attributes = new ArrayList<>();
	
	//<A> <B></B> </A>
	public List<Node> children = new ArrayList<>();
	
	
	public Node(Node parent) 
	{
		this.parent = parent;
		
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
