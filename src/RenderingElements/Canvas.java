package RenderingElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import RenderingElements.StationElementManager.StationElementDraw;

public class Canvas extends JPanel implements Runnable{
	
	private long deltaTime = 0l;
	private long timeBefore = 0l;
	private long timeAfter = 0l;
	
	Thread thread = new Thread(this);
	
	StationElementDraw station1 = new StationElementDraw();
	

	public Canvas() 
	{
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(1500,600));
		this.setFocusable(true);
	
	}
	
	public void startThread() 
	{
		thread.start();
	}
	
	

	@Override
	public void run() {
		
		while(thread != null)
		{
			
			timeBefore = System.nanoTime();
			
			Update();
			repaint();
			
			
			//use for monitoring frames per second 
			deltaTime = System.nanoTime() - timeBefore ;
			
			
		}
		
		
		
	}
	
	
	
	private void Update() 
	{
		
	}
	
	
	//this is draw ( repaint() )
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);		
		Graphics2D graphic2D = (Graphics2D) g;
		

		
		
		
		station1.draw(graphic2D);
		

		
		graphic2D.dispose();
	}
	
	
	
	
	
	
}
