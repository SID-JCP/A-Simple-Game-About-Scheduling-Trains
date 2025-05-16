package RenderingElements.Xml.Loader;

import RenderingElements.Xml.XmlDoc;
import RenderingElements.Xml.Node.Node;
import RenderingElements.Xml.Node.NodeAttribute;

public class Parser {
	
	
	XmlDoc doc;
	
	public Parser(XmlDoc doc) 
	{
		this.doc = doc;
	}
	
	
	public void parse() 
	{
		tokenize();
	}
	
	private void tokenize() 
	{
		char[] contents = doc.getContents();
		
		if(doc.getRootNode() == null) 
		{
			doc.setRootNode(new Node(null));
		}
		
		StringBuilder accum = new StringBuilder();
		
		
		Node cursor = null;
		
		//temp
		NodeAttribute temp = new NodeAttribute();
		
		for(int i = 0; i < contents.length; i++) 
		{
			
			
			
			if(contents[i] == '<') 
			{
				
				if(accum.length() != 0) 
				{
					if(cursor == null){return;
					}else {
						
						cursor.setText(accum.toString());
						accum.setLength(0);
					}
					
				}
				
				i++;
				
				if(contents[i] == '/') 
				{
					i++;
					
					
					if(cursor == null)return;
					
					
					while(contents[i] != '>') 
					{
						accum.append(contents[i]);
						i++;
					}
					
					if(!cursor.getTag().equals(accum.toString())) 
					{
						if(cursor.parent != null) 
						{
							if(!cursor.parent.getTag().equals(accum.toString()))
							{
								return;
							}
						}
						
						
					}
					
					
					if(cursor.parent != null) cursor = cursor.parent;
					
//					System.out.println(accum.toString());
//					
					accum.setLength(0);
					
					continue;
				}
				
				
				if(cursor == null) 
				{
					cursor = doc.getRootNode();	
					
				}else {				
					cursor = new Node(cursor);
					
					//adding the child to the child array of parent
					cursor.parent.children.add(cursor);
				}
				
				
				
				while(contents[i] != '>') 
				{
					if(contents[i] == ' ') 
					{
						if(cursor.getTag() == null) 
						{
							cursor.setTag(accum.toString());
							accum.setLength(0);
							i++;
							continue;
							
						}else {i++; continue;}
			
					}
					
					if(contents[i] == '='){
					
						//key found
						temp.setKey(accum.toString());
						accum.setLength(0);				
						i++;
						continue;
					}
					
					
					if(contents[i] == '"')
					{
						i++;						
						while(contents[i] != '"') 
						{							
							accum.append(contents[i]);
							i++;
						}
						
						temp.setValue(accum.toString());
						cursor.attributes.add(new NodeAttribute(temp.getKey() , temp.getValue()));
						accum.setLength(0);
						i++;
						continue;
					}
					
					accum.append(contents[i]);
					i++;
				}
				
				if(cursor.getTag() == null) 
				{
					cursor.setTag(accum.toString());
					accum.setLength(0);
					
				}
				
				continue;
				
			}else {
				
				if(contents[i] == ' ' && accum.isEmpty()) 
				{
					continue;
				}
				
				accum.append(contents[i]);
				
				
			}
			
			
			
		}
	}

}
