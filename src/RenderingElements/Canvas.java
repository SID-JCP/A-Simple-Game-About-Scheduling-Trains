package RenderingElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import InputManager.KeyBoardInputManager;
import InputManager.MouseInputManager;
import RenderingElements.StationElementManager.StationElementDraw;

public class Canvas extends JPanel implements Runnable {
	
	/**
	 * IDK WHAT IS THIS....Eclipse said so i did it 
	 */
	private static final long serialVersionUID = 1L;
	
	private long deltaTime = 0l;
	private long timeStart = 0l;
	private long timeEnd = 0l;
	private long nano = 1000000000;
	
	private double timeLeftToWait = 0;
	
	/* |---------- FPS ---------------| */
	private int FPS =  30;
	
	private MouseInputManager mouseInput = new MouseInputManager();
	private KeyBoardInputManager keyInput = new KeyBoardInputManager();
	
	
	public  int moveX;
	public  int moveY;
	
	Thread thread = new Thread(this);
	
	StationElementDraw station1 = new StationElementDraw();
	
	
	

	public Canvas() 
	{
		this.setBackground(Color.BLACK);
		
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(1500,600));
		
//		this.addKeyListener(keyInput);
		this.addMouseMotionListener(mouseInput);
		this.setFocusable(true);
		
	
	}
	
	public void startThread() 
	{
		thread.start();
	}
	
	

	@Override
	public void run() 
	{
		
		
		long actualRunTime = nano / FPS;
		
		
		while(thread != null)
		{
			
			timeStart = System.nanoTime();
			
			Update();
			repaint();
			
			timeEnd = System.nanoTime();
			
			//IN nano seconds 
			
			deltaTime = timeEnd - timeStart ;
		
			
			timeLeftToWait = deltaTime/nano;			
			
			
			
			
			try {
				Thread.sleep((long)timeLeftToWait);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			
			//use for monitoring frames per second 
			
			
			
		}
		
		
		
	}
	
	
	
	private void Update() 
	{
		moveX = mouseInput.getMoveX();
		moveY = mouseInput.getMoveY();
		
	}
	
	
	//this is draw ( repaint() )
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);		
		Graphics2D graphic2D = (Graphics2D) g;
		
		graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        					RenderingHints.VALUE_ANTIALIAS_ON);
			
		
		graphic2D.setColor(Color.white);
		
		//FPS and mouse position debug display
		graphic2D.drawString("Ms " + String.valueOf(timeLeftToWait), 20, 30);		
		graphic2D.drawString("Mouse X: " + String.valueOf(moveX) + 
							 " Mouse Y: " + String.valueOf(moveY)
							, 20, 60);
		
		station1.draw(graphic2D);
		
		
		
		
		

		
		graphic2D.dispose();
	}

	
	
	
	
	
	
	
}
