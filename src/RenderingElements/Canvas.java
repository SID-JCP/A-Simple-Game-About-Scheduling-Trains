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
import RenderingElements.Controller.InGameClock;
import RenderingElements.Draw.StationSelector;

public class Canvas extends JPanel implements Runnable 
{
	
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
	
	public  int clickX;
	public  int clickY;
	
	private int SCREEN_WIDTH = 0;
	private int SCREEN_HEIGHT = 0;
	
	
	private JFrame window;
	
		
	Thread thread = new Thread(this);
	
	StationSelector elements = new StationSelector();
	
	InGameClock gClock = new InGameClock();
	
	
	

	public Canvas(JFrame window) 
	{
		this.window = window;
		this.setBackground(Color.BLACK);
		
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(1500,600));
		
//		this.addKeyListener(keyInput);
		this.addMouseMotionListener(mouseInput);
		this.addMouseListener(mouseInput);
		this.setFocusable(true);
		
	
	}
	
	public void startThread() 
	{
		thread.start();
		gClock.clockStart();
	}
	
	

	@Override
	public void run() 
	{
		
		
		long actualRunTime = nano / FPS;
		
		
		while(thread != null)
		{
			
			SCREEN_WIDTH = window.getWidth();
			SCREEN_HEIGHT = window.getHeight();
			
			timeStart = System.nanoTime();
			
			Update();
			repaint();
			
			timeEnd = System.nanoTime();
			
			//IN nano seconds			
			deltaTime = timeEnd - timeStart ;
			
		
			
			if(actualRunTime > deltaTime) 
			{
				timeLeftToWait  = actualRunTime - deltaTime;
				
								
				try {
					Thread.sleep((long)timeLeftToWait/1000000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
						
			
			
		}
		
		
		
	}
	
	
	
	private void Update() 
	{
		moveX = mouseInput.getMoveX();
		moveY = mouseInput.getMoveY();
		
		clickX = mouseInput.getClickX();
		clickY = mouseInput.getClickY();
		
		elements.update(InGameClock.secondsOfDay , SCREEN_WIDTH , SCREEN_HEIGHT , moveX , moveY , clickX , clickY);
		
	}
	
	
	//this is draw ( repaint() )
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);		
		Graphics2D graphic2D = (Graphics2D) g;		
		graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        					RenderingHints.VALUE_ANTIALIAS_ON);
			
		
		graphic2D.setColor(Color.white);
		
		
		gClock.drawClock(graphic2D);
		
		
		
		
		/*
		 * -----------------------------------------------DEBUG ELEMETS---------------------------
		 */
		
		graphic2D.drawString("MS: " + String.valueOf(deltaTime), 20, 30);		
		graphic2D.drawString("Mouse X: " + String.valueOf(moveX) + 
							 " Mouse Y: " + String.valueOf(moveY)
							, 20, 60);
		
		graphic2D.drawString("WIDTH: " + SCREEN_WIDTH + " "+
							 "HEIGHT: " + SCREEN_HEIGHT, 20, 90);
		
		
		graphic2D.drawString("Mouse X: " + String.valueOf(clickX) + 
				 " Mouse Y: " + String.valueOf(clickY)
				, 20, 120);
		
		
		graphic2D.fillOval(moveX, moveY, 10, 10);
		
		elements.draw(graphic2D);
		

		
		graphic2D.dispose();
	}

	
	
	
	
	
	
	
}
