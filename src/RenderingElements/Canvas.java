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
import RenderingElements.Controller.SimulatorClock;
import RenderingElements.Draw.Simulator;
import Window.TrainListWindow;

public class Canvas extends JPanel implements Runnable 
{
	
	/**
	 * IDK WHAT IS THIS....Eclipse said so i did it 
	 */
	private static final long serialVersionUID = 1L;
	
	public static boolean debug = false;
	
	private long deltaTime = 0l;	
	private long timeStart = 0l;
	private long timeEnd = 0l;
	
	private long nano = 1000000000;
	
	private long timeLeftToWait = 0;
	
	
	/* |---------- FPS ---------------| */
	private int FPS =  120; //30 , 60 , 120 FPS
	
	private MouseInputManager mouseInput = new MouseInputManager();
	private KeyBoardInputManager keyInput = new KeyBoardInputManager();
	
	
	public  int moveX;
	public  int moveY;
	
	public  int clickX;
	public  int clickY;
	
	private int SCREEN_WIDTH = 0;
	private int SCREEN_HEIGHT = 0;
	
	
	private boolean trainListWindowOpen = false;
	
	private JFrame window;
	
	private TrainListWindow trainListWindow =  new TrainListWindow();
	
		
	Thread thread = new Thread(this);
	
	Simulator simulatior = new Simulator();
	
	SimulatorClock clock = new SimulatorClock();
	
	
	

	public Canvas(JFrame window) 
	{
		this.window = window;
		this.setBackground(Color.BLACK);
		
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(1500,600));
		
		this.addKeyListener(keyInput);
		this.addMouseMotionListener(mouseInput);
		this.addMouseListener(mouseInput);
		this.setFocusable(true);
		
	
	}
	
	public void startThread() 
	{
		thread.start();
		clock.clockStart();
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
		
		//close window on escape key press
		if(keyInput.keyCode == 27) 
		{
			window.dispose();
		}
		
		if(keyInput.keyCode == 79) 
		{
			if(!trainListWindowOpen) 
			{
				trainListWindow.openWindow();
				trainListWindowOpen =  true;
				
			}
			
		}
		
		
		simulatior.update(timeLeftToWait ,SimulatorClock.secondsOfDay , SCREEN_WIDTH , SCREEN_HEIGHT , moveX , moveY , clickX , clickY);
		clock.update(SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	
	//this is draw ( repaint() )
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);		
		Graphics2D graphic2D = (Graphics2D) g;		
		graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        					RenderingHints.VALUE_ANTIALIAS_ON);
			
		
		graphic2D.setColor(Color.white);
		
		
		clock.drawClock(graphic2D);
		
		if(debug) 
		{
			drawDebug(graphic2D);
		}
		
		
		simulatior.draw(graphic2D);
		
		graphic2D.dispose();
	}

	
	private void drawDebug(Graphics2D graphic2D) 
	{
		graphic2D.setColor(Color.red);
		graphic2D.setFont(new Font("Arial" , Font.BOLD , 24));
		
		graphic2D.drawString("MS: " + String.valueOf(deltaTime), 20, 30);		
		graphic2D.drawString("Mouse X: " + String.valueOf(moveX) + 
							 " Mouse Y: " + String.valueOf(moveY)
							, 20, 60);
		
		graphic2D.drawString("WIDTH: " + SCREEN_WIDTH + " "+
							 "HEIGHT: " + SCREEN_HEIGHT, 20, 90);
		
		
		graphic2D.drawString("Mouse X: " + String.valueOf(clickX) + 
				 " Mouse Y: " + String.valueOf(clickY)
				, 20, 120);
		
		graphic2D.drawString("Key Pressed: " + " " + keyInput.keyCode
				, 20, 150);
		
		graphic2D.drawString("Map_Id: " + simulatior.getMapSelected() + " " 	
							+ "Traffic_Id: " + simulatior.getTrafficSelected()
							, 20, 180);
		
		
		graphic2D.fillOval(moveX, moveY, 10, 10);
	}
	
	
	
	
	
}
