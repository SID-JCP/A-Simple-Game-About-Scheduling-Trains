package RenderingElements.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import RenderingElements.Train.Train;

public class SimulatorClock implements Runnable{

	
	
	/*
	 * |------------------- How Clock Should Count -----------------------|
	 *
	 * Its a 24 hrs clock 
	 *
	 * It counts upto 23:59 , in seconds that is 86400 - 1
	 * 
	 * After which it resets
	 * 
	 * every 15 mins of this clock needs to be 1 min for real world 
	 * 
	 * 15mins = 900 seconds , make thread Count 900 times every minute 900/60 = 15 times a second
	 * 
	 * FPS of This class is 15
	 * 
	 */
	
	
	/*
	 * |--------------------------- Purpose of this class ----------------------------|
	 * 
	 * Have a data of train timings for the whole day
	 * 
	 * When the time matches to the timing of provided train list , create a train
	 * object and put in list for tspController
	 * 
	 * Create a train with meta data , such as train delay , time of stoppage ,
	 * should stop or not
	 * 
	 * Have info of the first signals for the map , if not green then dont produce
	 * train and add to delay for the train
	 * 
	 * if train pass the station and last home signal then remove the train from
	 * train list of tspCOntroller
	 * 
	 */
	
	
	 
	//|------------------  TIME RELATED THINGS --------------------------|
	Thread clockThread = new Thread(this);	
	
	public static int secondsOfDay = 1;	
	
	//maintain 15 FPS 
	private long deltaTime = 0l;
	private long timeStart = 0l;
	private long timeEnd = 0l;
	
	private int countsPerSecond =  15; //15 is default 
	private long nano = 1000000000;	
	
	private double timeLeftToWait = 0;	
	
	private int HOUR = 8;	//start everyday at morning 8'O clock 
	private int MINUTES = 0;
	
	//UI elements 
	private int horizontalCenter = 0;
	private int clockBannerWidth = 0;
	private int clockBannerHeight = 0;
	
	

	public void clockStart() 
	{
		clockThread.start();
		
	}

	
	@Override
	public void run() {
		
		long actualRunTime = nano / countsPerSecond;
		
		while(clockThread != null) 
		{
			//start of process
			timeStart = System.nanoTime();
			
			
			//methods 
			changeTime();
			
			
			//end of process
			timeEnd = System.nanoTime();
			
						
			deltaTime = timeEnd - timeStart ;
			
			
		
			if(actualRunTime > deltaTime) 
			{
				timeLeftToWait = actualRunTime - deltaTime;		
				
								
				try {
					Thread.sleep((long)timeLeftToWait/1000000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
			
			
		}
		
	}
	
	
	private void changeTime() 
	{
		if(secondsOfDay >= 86400) 
		{
			secondsOfDay = 0;
			setMinute();

			return;
		}
		secondsOfDay++;		
		setMinute();

	}
	

	
	private void setMinute() 
	{
		 
		
		if(SimulatorClock.secondsOfDay % 60 == 0) 
		{
			MINUTES++;
		}
		
		if(MINUTES == 59) 
		{
			MINUTES = 0;
			if(HOUR == 23) {HOUR = 0;}else{
				HOUR++;
			};
			
		}
	}
	
	
	
	public void update(int screenWidth , int screenHeight) 
	{
		horizontalCenter = screenWidth/2;
		clockBannerWidth = screenWidth/5;
		clockBannerHeight = screenHeight/8;
	}
	
	
	
	//design a clock and show time in a graphical manner 	
	public void drawClock(Graphics2D g2) 
	{
		g2.setColor(Color.DARK_GRAY);
		g2.fillRoundRect(horizontalCenter - clockBannerWidth/2 , 20, clockBannerWidth, clockBannerHeight , 100 ,100);		
		
		g2.setColor(Color.WHITE);
		
		String hour = "";
		String mins = "";
		
		if(HOUR < 10) {hour +=  "0" + HOUR;}else {hour += HOUR;}
		if(MINUTES < 10) {mins +=  "0" + MINUTES;}else {mins += MINUTES;}
		
		g2.setFont(new Font("Arial" , Font.BOLD , 50));
		g2.drawString(hour + ":" + mins, horizontalCenter - 62, clockBannerHeight - clockBannerHeight/6);		
		
		
		
	}
	
	
	
}
