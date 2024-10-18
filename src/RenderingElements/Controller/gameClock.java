package RenderingElements.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import RenderingElements.Train.Train;

public class gameClock implements Runnable{

	
	
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
	
	private int countsPerSecond =  15;
	private long nano = 1000000000;	
	
	private double timeLeftToWait = 0;	
	
	private int HOUR = 0;	
	private int MINUTES = 0;
	
	

	

	
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
			return;
		}
		secondsOfDay++;
		
		setHour();
		setMinute();
		
	
		
		createTrainOnTime();
	}
	
	
	
	public void clockStart() 
	{
		clockThread.start();
		readTrainFile();
	}
	
	private void setHour() 
	{
		HOUR = (int) gameClock.secondsOfDay/3600;
	}
	
	private void setMinute() 
	{
		 
		
		if(gameClock.secondsOfDay % 60 == 0) 
		{
			MINUTES++;
		}
		
		if(MINUTES == 59) 
		{
			MINUTES = 0;
		}
	}
	
	
	
	
	
	private void readTrainFile() 
	{
		
	}
	
	
	
	
	
	private void createTrainOnTime() 
	{
		//check the timings of trains available , if any match , create and add to tspController List 
		if(gameClock.secondsOfDay == 50) 
		{
			tspController.allTrain.add(new Train());			
			
		}
		
	}
	
	//design a clock and show time in a graphical manner 	
	public void drawClock(Graphics2D g2) 
	{
		g2.setColor(Color.orange);
		g2.drawRoundRect(650, 10, 200, 50 , 15 ,15);		
		
		g2.setColor(Color.red);
		
		g2.setFont(new Font("Arial" , Font.BOLD , 24));
		g2.drawString("TIME: " + HOUR + ":" + MINUTES, 700, 42);		
		
	}
	
	
	
}
